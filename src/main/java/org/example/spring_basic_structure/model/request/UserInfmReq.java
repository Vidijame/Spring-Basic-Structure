package org.example.spring_basic_structure.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfmReq {
    private String eml;
    private String usrPwd;
    private String tel;
}
