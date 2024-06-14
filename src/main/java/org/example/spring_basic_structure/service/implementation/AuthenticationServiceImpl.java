package org.example.spring_basic_structure.service.implementation;

import lombok.RequiredArgsConstructor;
import org.example.spring_basic_structure.configuration.JwtAuthenticationService;
import org.example.spring_basic_structure.enumeration.Role;
import org.example.spring_basic_structure.exception.BadRequestExceptionClass;
import org.example.spring_basic_structure.exception.NotFoundExceptionClass;
import org.example.spring_basic_structure.model.entity.UserInfm;
import org.example.spring_basic_structure.model.request.AuthenticationReq;
import org.example.spring_basic_structure.model.request.RegisterationReq;
import org.example.spring_basic_structure.model.response.AuthenticationRes;
import org.example.spring_basic_structure.repository.UserInfmDAO;
import org.example.spring_basic_structure.service.AuthenticationService;
import org.example.spring_basic_structure.validation.ValidationConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserInfmDAO userInfmDAO;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationService jwtAuthenticationService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationRes register(RegisterationReq request) {

        Set<String> roles = new HashSet<>();

        for(String role : request.getRoles()){
            if(role.equalsIgnoreCase(Role.ADMIN.name()) || role.equalsIgnoreCase(Role.USER.name())){
                roles.add(role.toUpperCase());
            }else{
                throw new BadRequestExceptionClass(ValidationConfig.ROLE_VALIDATION);
            }
        }

        var user = UserInfm.builder()
                .usrId(null)
                .eml(request.getEml())
                .usrPwd(passwordEncoder.encode(request.getUsrPwd()))
                .tel(request.getTel())
                .roles(roles)
                .build();
        userInfmDAO.save(user);
        var jwtToken = jwtAuthenticationService.generateToken(user);
        return AuthenticationRes
                .builder()
                .access_token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationRes authenticate(AuthenticationReq request) {
        var user = userInfmDAO.findByEmlOrTel(request.getUsrAccount(), request.getUsrAccount())
                .orElseThrow(() -> {
                    if (request.getUsrAccount().contains("@")) {
                        return new NotFoundExceptionClass(ValidationConfig.EMAIL_INCORRECT_RESPONSE);
                    } else {
                        return new NotFoundExceptionClass(ValidationConfig.TELEPHONE_INCORRECT_RESPONSE);
                    }

                });

        if(!passwordEncoder.matches(request.getUsrPwd(), user.getPassword())){
            throw new BadRequestExceptionClass(ValidationConfig.PASSWORD_INCORRECT_RESPONSE);
        }else{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsrAccount(),
                            request.getUsrPwd()
                    )
            );
            var jwtToken = jwtAuthenticationService.generateToken(user);
            return AuthenticationRes.builder()
                    .access_token(jwtToken)
                    .build();

        }

    }


}
