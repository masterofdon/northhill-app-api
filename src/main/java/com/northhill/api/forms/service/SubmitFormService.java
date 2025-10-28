package com.northhill.api.forms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.northhill.api.forms.SubmitForm;
import com.northhill.api.forms.repository.SubmitFormRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubmitFormService {

  private final SubmitFormRepository submitFormRepository;

  public SubmitForm getById(String id) {
    return submitFormRepository.findById(id).orElse(null);
  }

  public Page<SubmitForm> getAll(Pageable pageable) {
    return submitFormRepository.findAll(pageable);
  }

  public SubmitForm create(SubmitForm form) {
    return submitFormRepository.save(form);
  }

  public void delete(SubmitForm form) {
    submitFormRepository.delete(form);
  }

}
