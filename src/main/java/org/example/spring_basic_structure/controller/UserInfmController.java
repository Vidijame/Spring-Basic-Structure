package org.example.spring_basic_structure.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.spring_basic_structure.common.GenericResponse;
import org.example.spring_basic_structure.model.response.UserInfmRes;
import org.example.spring_basic_structure.service.implementation.UserInfmServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@Tag(name = "UserInfm")

public class UserInfmController {
    private final UserInfmServiceImpl userInfmService;

    public UserInfmController(UserInfmServiceImpl userInfmService) {
        this.userInfmService = userInfmService;
    }

    @SecurityRequirement(name= "Auth")
    @GetMapping
    public ResponseEntity<GenericResponse<UserInfmRes>> getCurrentUser(){
        return GenericResponse.createResponse(HttpStatus.OK, "You have successfully gotten your information", userInfmService.getCurrentUser());
    }
}
