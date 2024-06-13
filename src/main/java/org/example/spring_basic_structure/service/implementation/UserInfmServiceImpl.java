package org.example.spring_basic_structure.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.example.spring_basic_structure.model.response.UserInfmRes;
import org.example.spring_basic_structure.service.UserInfmService;
import org.example.spring_basic_structure.utils.CurrentUserUtils;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserInfmServiceImpl implements UserInfmService {
    private final CurrentUserUtils currentUserUtils;

    public UserInfmServiceImpl(CurrentUserUtils currentUserUtils) {
        this.currentUserUtils = currentUserUtils;
    }

    @Override
    public UserInfmRes getCurrentUser() {
        log.info("Current User : {}", currentUserUtils.getPrincipal());
        return currentUserUtils.getPrincipal();
    }
}
