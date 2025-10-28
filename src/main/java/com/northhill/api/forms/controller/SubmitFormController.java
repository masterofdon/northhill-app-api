package com.northhill.api.forms.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.northhill.api.forms.BarterOption;
import com.northhill.api.forms.SubmitForm;
import com.northhill.api.forms.service.SubmitFormService;
import com.northhill.api.forms.xaction.Create_SubmitForm_WC_MLS_XAction;
import com.northhill.api.generic.api.GenericApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/submit-forms")
@RequiredArgsConstructor
public class SubmitFormController {

  private final SubmitFormService submitFormService;

  @GetMapping
  public GenericApiResponse<Page<SubmitForm>> getAllForms(Pageable pageable) {
    Page<SubmitForm> forms = submitFormService.getAll(pageable);
    return new GenericApiResponse<>(
        HttpStatus.OK.value(),
        "Success",
        "200#10397",
        forms);
  }

  @PostMapping
  public GenericApiResponse<SubmitForm> submitForm(
      @RequestBody Create_SubmitForm_WC_MLS_XAction xAction) {

    SubmitForm form = new SubmitForm();
    form.setFullname(xAction.getFullname());
    form.setAge(xAction.getAge());
    form.setEmail(xAction.getEmail());
    form.setPhonenumber(xAction.getPhonenumber());
    form.setAddress(xAction.getAddress());
    form.setCity(xAction.getCity());
    form.setWorktype(xAction.getWorktype());
    form.setMonthlynetincome(xAction.getMonthlynetincome());
    form.setMonthlyexternalincome(xAction.getMonthlyexternalincome());
    form.setMonthlyexpenses(xAction.getMonthlyexpenses());
    form.setRequestedvalue(xAction.getRequestedvalue());
    form.setInstallment(xAction.getInstallment());

    if (xAction.getBarters() != null) {
      Set<BarterOption> options = xAction.getBarters().stream().map(e -> {
        BarterOption option = new BarterOption();
        option.setType(e.getType());
        option.setValue(e.getValue());
        option.setForm(form);
        return option;
      }).collect(Collectors.toSet());
      form.setBarters(options);
    }

    submitFormService.create(form);

    return new GenericApiResponse<>(
        HttpStatus.OK.value(),
        "Success",
        "200#10398",
        form);
  }
}
