package com.northhill.api.sahibinden.service;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

@Service
public class SahibindenPlaywrightService {

  private final Random rnd = new Random();

  public String fetchPrice(String adUrl) {
    try (Playwright playwright = Playwright.create()) {

      BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
          .setHeadless(false) // test sırasında false yapabilirsin
          .setArgs(Arrays.asList("--no-sandbox", "--disable-setuid-sandbox"));

      Browser browser = playwright.chromium().launch(launchOptions);

      // Context ayarları: userAgent, locale, timezone, ek header'lar
      Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
          .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
              "AppleWebKit/537.36 (KHTML, like Gecko) " +
              "Chrome/118.0.0.0 Safari/537.36")
          .setLocale("tr-TR")
          .setTimezoneId("Europe/Istanbul")
          // extra headers örneği: accept-language gerçeğe yakın olsun
          .setExtraHTTPHeaders(Map.of("accept-language", "tr-TR,tr;q=0.9,en-US;q=0.8,en;q=0.7"));

      BrowserContext context = browser.newContext(contextOptions);

      Page page = context.newPage();

      // Page görünümü (gerçekçi bir masaüstü penceresi)
      page.setViewportSize(1280, 800);

      // Navigate
      page.navigate(adUrl, new Page.NavigateOptions().setTimeout(30_000));
      // Ağ trafiği sakinleşene kadar bekle
      page.waitForLoadState(LoadState.NETWORKIDLE);

      // Küçük rastgele bekleme — JS challenge'ların tamamlanması için
      page.waitForTimeout(1000 + rnd.nextInt(2000)); // 1-3s

      // İnsan-benzeri küçük mouse hareketleri (sayfada fareyi gezdir)
      humanLikeMouseMoves(page);

      // DOM'a eklenmesini bekle (visible yerine attached)
      page.waitForSelector(".classified-price-wrapper",
          new Page.WaitForSelectorOptions()
              .setState(WaitForSelectorState.ATTACHED)
              .setTimeout(15_000));

      Locator priceLocator = page.locator(".classified-price-wrapper");
      if (priceLocator.count() == 0) {
        // fallback: başka selector'ları dene
        String[] fallbacks = new String[] { ".classified-price", ".classifiedPrice", "span.price" };
        for (String sel : fallbacks) {
          if (page.locator(sel).count() > 0) {
            priceLocator = page.locator(sel);
            break;
          }
        }
      }

      if (priceLocator.count() == 0) {
        // Büyük ihtimalle bot/scrape testi veya selector değişti
        context.close();
        browser.close();
        return "Fiyat elemanı bulunamadı — muhtemel bot kontrolü veya DOM değişikliği.";
      }

      String price = priceLocator.first().innerText().trim().replaceAll("\\s+", " ");

      context.close();
      browser.close();

      return price.isEmpty() ? "Fiyat bulunamadı" : price;

    } catch (PlaywrightException pwEx) {
      // Playwright hatalarını ayrıştır
      return "Playwright hata: " + pwEx.getMessage();
    } catch (Exception e) {
      return "Hata: " + e.getMessage();
    }
  }

  // Basit insan benzeri mouse hareketleri
  private void humanLikeMouseMoves(Page page) {
    try {
      // birkaç küçük hareket
      page.mouse().move(100, 100);
      page.waitForTimeout(200 + rnd.nextInt(300));
      page.mouse().move(300, 120);
      page.waitForTimeout(150 + rnd.nextInt(400));
      page.mouse().move(500, 240);
      page.waitForTimeout(200 + rnd.nextInt(600));
    } catch (Exception ignored) {
    }
  }
}