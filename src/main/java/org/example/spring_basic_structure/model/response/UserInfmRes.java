package org.example.spring_basic_structure.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfmRes {
    private String eml;
    private String tel;
    private String regId;
    private String regDtm;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String modId;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String modDtm;
}
