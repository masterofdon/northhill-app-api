package com.northhill.api.forms.xaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.northhill.api.forms.BarterType;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class Create_BarterOption_WC_MLS_XAction {

  private BarterType type;

  private String value;
}
