package org.example.spring_basic_structure.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.spring_basic_structure.validation.ValidationConfig;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterationReq {
    @NotEmpty(message = ValidationConfig.EMAIL_REQUIRED_MESSAGE)
    @Email(message = ValidationConfig.EMAIL_RESPONSE_MESSAGE)
    private String eml;

    @Size(min = 8, message = ValidationConfig.PASSWORD_RESPONSE_MINIMUM_MESSAGE)
    @Pattern(regexp = ValidationConfig.PASSWORD_VALIDATION, message = ValidationConfig.PASSWORD_RESPONSE_MESSAGE)
    private String usrPwd;

    private String tel;

    @Size(min = 1, max = 2, message = ValidationConfig.ROLE_VALIDATION_SIZE_MESSAGE)
    private Set<String> roles;
}
