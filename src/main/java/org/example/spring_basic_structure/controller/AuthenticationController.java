package org.example.spring_basic_structure.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring_basic_structure.common.GenericResponse;
import org.example.spring_basic_structure.model.request.AuthenticationReq;
import org.example.spring_basic_structure.model.request.RegisterationReq;
import org.example.spring_basic_structure.model.response.AuthenticationRes;
import org.example.spring_basic_structure.service.implementation.AuthenticationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponse<AuthenticationRes>> register(@Valid @RequestBody RegisterationReq request){
        return GenericResponse.createResponse(HttpStatus.CREATED, "You have successfully registered", authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<GenericResponse<AuthenticationRes>> authenticate(@Valid @RequestBody AuthenticationReq request){
        return GenericResponse.createResponse(HttpStatus.OK, "You have successfully signed in", authenticationService.authenticate(request));
    }
}