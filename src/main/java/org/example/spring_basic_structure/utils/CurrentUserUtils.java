package org.example.spring_basic_structure.utils;

import org.example.spring_basic_structure.exception.UnAuthorizedExceptionClass;
import org.example.spring_basic_structure.model.entity.UserInfm;
import org.example.spring_basic_structure.model.response.UserInfmRes;
import org.example.spring_basic_structure.repository.UserInfmDAO;
import org.example.spring_basic_structure.validation.ValidationConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CurrentUserUtils {
    private final UserInfmDAO userInfmDAO;

    public CurrentUserUtils(UserInfmDAO userInfmDAO) {
        this.userInfmDAO = userInfmDAO;
    }

    public String getEmail(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Boolean isAuthenticated(){
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    public List<String> getAuthorities(){
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
    }

    public UserInfmRes getPrincipal(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserInfmRes> userInfmRes = userInfmDAO.findByEml(email).map(UserInfm::toResponse);
        if(userInfmRes.isPresent()){
            return userInfmRes.get();
        }else{
            throw new UnAuthorizedExceptionClass(ValidationConfig.UNAUTHORIZED_RESPONSE);
        }
    }
}
