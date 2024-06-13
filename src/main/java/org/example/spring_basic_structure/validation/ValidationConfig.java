package org.example.spring_basic_structure.validation;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {
    public static final String VALIDATION_GENDER_MESSAGE = "gender must be male and female";
    public static final String EMAIL_REQUIRED_MESSAGE = "email is required";
    public static final String EMAIL_RESPONSE_MESSAGE = "email must be a valid email address";
    public final static String PASSWORD_RESPONSE_MESSAGE = "at least one uppercase letter, one lowercase letter, one number and one special character";
    public final static String PASSWORD_VALIDATION = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()\\-_=+{};:,<.>])(?!.*\\s).{8,}$";
    public final static String PASSWORD_RESPONSE_MINIMUM_MESSAGE = "password at least 8 character";
    public final static String USERNAME_VALIDATION = "username should not be empty";
    public final static String FIRSTNAME_VALIDATION = "firstname should not be empty";
    public final static String LASTNAME_VALIDATION = "lastname should not be empty";
    public final static String USERNAME_INCORRECT_RESPONSE = "username incorrect";
    public final static String EMAIL_INCORRECT_RESPONSE = "email incorrect";
    public final static String PASSWORD_INCORRECT_RESPONSE = "password incorrect";
    public final static String TELEPHONE_INCORRECT_RESPONSE = "telephone incorrect";
    public final static String ROLE_VALIDATION = "roles must be admin and user";
    public final static String ROLE_VALIDATION_SIZE_MESSAGE = "maximum roles must be 2, cannot more than 2";
    public final static String UNAUTHORIZED_RESPONSE = "You're not allowed to access this resource";

}
