package com.northhill.api.forms.service;

import org.springframework.stereotype.Service;

import com.northhill.api.forms.BarterOption;
import com.northhill.api.forms.repository.BarterOptionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BarterOptionService {

  private final BarterOptionRepository barterOptionRepository;

  public BarterOption getById(String id) {
    return barterOptionRepository.findById(id).orElse(null);
  }

  public BarterOption create(BarterOption option) {
    return barterOptionRepository.save(option);
  }

  public void delete(BarterOption option) {
    barterOptionRepository.delete(option);
  }
}
