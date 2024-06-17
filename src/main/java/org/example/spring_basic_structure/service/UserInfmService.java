package org.example.spring_basic_structure.service;

import org.example.spring_basic_structure.model.request.UserInfmReq;
import org.example.spring_basic_structure.model.response.UserInfmRes;

import java.util.List;

public interface UserInfmService {
    UserInfmRes getCurrentUserInfm();
    List<UserInfmRes> getAllUserInfm();
    UserInfmRes getUserInfmById(String id);
    void deleteUserInfmById(String id);
}
