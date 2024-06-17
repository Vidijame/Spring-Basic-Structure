package org.example.spring_basic_structure.validation;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {
    public static final String EMAIL_REQUIRED_MESSAGE = "email is required";
    public static final String EMAIL_RESPONSE_MESSAGE = "email must be a valid email address";
    public final static String PASSWORD_RESPONSE_MESSAGE = "at least one uppercase letter, one lowercase letter, one number and one special character";
    public final static String PASSWORD_VALIDATION = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()\\-_=+{};:,<.>])(?!.*\\s).{8,}$";
    public final static String PASSWORD_RESPONSE_MINIMUM_MESSAGE = "password at least 8 character";
    public final static String EMAIL_INCORRECT_RESPONSE = "email incorrect";
    public final static String PASSWORD_INCORRECT_RESPONSE = "password incorrect";
    public final static String TELEPHONE_INCORRECT_RESPONSE = "telephone incorrect";
    public final static String ROLE_VALIDATION = "Roles must be admin and user";
    public final static String ROLE_VALIDATION_SIZE_MESSAGE = "maximum roles must be 2, cannot more than 2";
    public final static String UNAUTHORIZED_RESPONSE = "You're not allowed to access this resource";
    public final static String USER_NOT_FOUND = "User does not exist";

}
