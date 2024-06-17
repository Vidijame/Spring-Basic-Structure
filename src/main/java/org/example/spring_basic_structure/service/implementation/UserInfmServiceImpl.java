package org.example.spring_basic_structure.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.example.spring_basic_structure.exception.NotFoundExceptionClass;
import org.example.spring_basic_structure.model.entity.UserInfm;
import org.example.spring_basic_structure.model.request.UserInfmReq;
import org.example.spring_basic_structure.model.response.UserInfmRes;
import org.example.spring_basic_structure.repository.UserInfmDAO;
import org.example.spring_basic_structure.service.UserInfmService;
import org.example.spring_basic_structure.utils.CurrentUserUtils;
import org.example.spring_basic_structure.validation.ValidationConfig;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UserInfmServiceImpl implements UserInfmService {
    private final CurrentUserUtils currentUserUtils;

    private final UserInfmDAO userInfmDAO;

    public UserInfmServiceImpl(CurrentUserUtils currentUserUtils, UserInfmDAO userInfmDAO) {
        this.currentUserUtils = currentUserUtils;
        this.userInfmDAO = userInfmDAO;
    }

    @Override
    public List<UserInfmRes> getAllUserInfm() {
        return Optional.of(userInfmDAO.findAll()).filter(userInfms -> !userInfms.isEmpty())
                .orElseThrow(()-> new NotFoundExceptionClass(ValidationConfig.USER_NOT_FOUND))
                .stream()
                .map(UserInfm::toResponse)
                .toList();
    }


    @Override
    public void deleteUserInfmById(String id) {
        Optional.of(userInfmDAO.findById(id)).filter(Optional::isPresent).orElseThrow(() ->  new NotFoundExceptionClass(ValidationConfig.USER_NOT_FOUND));
        userInfmDAO.deleteById(id);
    }

    @Override
    public UserInfmRes getUserInfmById(String id) {
        return userInfmDAO.findById(id)
                .map(UserInfm::toResponse)
                .orElseThrow(() -> new NotFoundExceptionClass(ValidationConfig.USER_NOT_FOUND));
    }

    @Override
    public UserInfmRes getCurrentUserInfm() {
        return currentUserUtils.getPrincipal();
    }

}
