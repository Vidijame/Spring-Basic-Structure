package org.example.spring_basic_structure.service;

import org.example.spring_basic_structure.model.request.AuthenticationReq;
import org.example.spring_basic_structure.model.request.RegisterationReq;
import org.example.spring_basic_structure.model.response.AuthenticationRes;

public interface AuthenticationService {
    AuthenticationRes register(RegisterationReq request);
    AuthenticationRes authenticate(AuthenticationReq request);
}
