package com.northhill.api.forms;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class BarterOption {

  @Id
  @UuidGenerator
  private String id;

  @ManyToOne
  @JoinColumn(name = "form_id")
  @JsonIgnore
  private SubmitForm form;

  @Enumerated(EnumType.STRING)
  private BarterType type;

  private String value;
}
