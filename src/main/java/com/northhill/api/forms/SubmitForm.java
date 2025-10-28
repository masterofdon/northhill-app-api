package com.northhill.api.forms;

import java.util.Set;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class SubmitForm {

  @Id
  @UuidGenerator
  private String id;

  private String fullname;

  private Integer age;

  private String email;

  private String phonenumber;

  private String address;

  private String city;

  @Enumerated(EnumType.STRING)
  private WorkType worktype;

  private String monthlynetincome;

  private String monthlyexternalincome;

  private String monthlyexpenses;

  @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private Set<BarterOption> barters;

  private String requestedvalue;

  private Integer installment;
}
