package org.example.spring_basic_structure.model.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.spring_basic_structure.validation.ValidationConfig;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationReq {
    private String usrAccount;
    @Size(min = 8, message = ValidationConfig.PASSWORD_RESPONSE_MINIMUM_MESSAGE)
    @Pattern(regexp = ValidationConfig.PASSWORD_VALIDATION, message = ValidationConfig.PASSWORD_RESPONSE_MESSAGE)
    private String usrPwd;
}
