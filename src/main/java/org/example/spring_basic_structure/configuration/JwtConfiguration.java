package org.example.spring_basic_structure.configuration;

import org.example.spring_basic_structure.exception.NotFoundExceptionClass;
import org.example.spring_basic_structure.repository.UserInfmDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class JwtConfiguration {
    private final UserInfmDAO userInfmDAO;

    public JwtConfiguration(UserInfmDAO userInfmDAO) {
        this.userInfmDAO = userInfmDAO;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return account -> {
            // Assume input is either username or email
            return userInfmDAO.findByEmlOrTel(account, account)
                    .orElseThrow(() -> new NotFoundExceptionClass("Telephone or Email and Password wrong"));
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
