package com.northhill.api.forms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.northhill.api.forms.SubmitForm;

public interface SubmitFormRepository extends JpaRepository<SubmitForm, String> {

}
