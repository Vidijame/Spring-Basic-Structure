package org.example.spring_basic_structure.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.spring_basic_structure.common.GenericResponse;
import org.example.spring_basic_structure.model.response.UserInfmRes;
import org.example.spring_basic_structure.service.implementation.UserInfmServiceImpl;
import org.example.spring_basic_structure.utils.MessageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@Tag(name = "UserInfm")

public class UserInfmController {
    private final UserInfmServiceImpl userInfmService;

    public UserInfmController(UserInfmServiceImpl userInfmService) {
        this.userInfmService = userInfmService;
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<UserInfmRes>>> getAllUserInfm(){
        return GenericResponse.createResponse(HttpStatus.OK, MessageUtils.GET_ALL_USER_INFM, userInfmService.getAllUserInfm());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<UserInfmRes>> getUserInfmById(@PathVariable String id){
        return GenericResponse.createResponse(HttpStatus.OK, MessageUtils.GET_USER_INFM_BY_ID, userInfmService.getUserInfmById(id));
    }

    @SecurityRequirement(name= "Auth")
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> deleteUserInfmById(@PathVariable String id){
        userInfmService.deleteUserInfmById(id);
        return GenericResponse.createResponse(HttpStatus.OK, MessageUtils.DELETE_USER_INFM_BY_ID, null);
    }
    @SecurityRequirement(name= "Auth")
    @GetMapping("/getCurrentUser")
    public ResponseEntity<GenericResponse<UserInfmRes>> getCurrentUserInfm(){
        return GenericResponse.createResponse(HttpStatus.OK, MessageUtils.GET_CURRENT_USER_INFM, userInfmService.getCurrentUserInfm());
    }
}
