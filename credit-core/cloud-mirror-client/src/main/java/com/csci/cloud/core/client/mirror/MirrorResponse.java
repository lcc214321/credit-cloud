package com.csci.cloud.core.client.mirror;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MirrorResponse {


  @JsonAlias({"ret_Code", "Ret_Code"})
  private String retCode;


  @JsonAlias({"ret_Msg", "Ret_Msg"})
  private String retMsg;

}
