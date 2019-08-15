package com.csci.cloud.core.client.mercury.model.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Builder
@JsonInclude(Include.NON_NULL)
public class AdminPenaltyReqVo extends BaseReqVo {

}
