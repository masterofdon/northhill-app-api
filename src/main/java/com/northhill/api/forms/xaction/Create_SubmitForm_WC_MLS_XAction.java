package com.northhill.api.forms.xaction;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.northhill.api.forms.WorkType;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class Create_SubmitForm_WC_MLS_XAction {

  private String fullname;

  private Integer age;

  private String email;

  private String phonenumber;

  private String address;

  private String city;

  private Set<Create_BarterOption_WC_MLS_XAction> barters;

  private WorkType worktype;

  private String monthlynetincome;

  private String monthlyexternalincome;

  private String monthlyexpenses;

  private String requestedvalue;

  private Integer installment;
}
