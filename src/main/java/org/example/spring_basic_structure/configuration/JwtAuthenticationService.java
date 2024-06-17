package org.example.spring_basic_structure.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.spring_basic_structure.exception.NotFoundExceptionClass;
import org.example.spring_basic_structure.repository.UserInfmDAO;
import org.example.spring_basic_structure.validation.ValidationConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtAuthenticationService implements Serializable {

    @Value("${spring.application.security.jwt}")
    private String SECRET_KEY;

    @Value("${spring.application.security.expiration}")
    private Long tokenExpire;
    private final UserInfmDAO userInfmDAO;

    public JwtAuthenticationService(UserInfmDAO userInfmDAO) {
        this.userInfmDAO = userInfmDAO;
    }

    // Information of user
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails){
        var user = userInfmDAO.findByEml(userDetails.getUsername());
        if(user.isPresent()){
            extractClaims.put("user", user.get().toResponse());
        }else{
            throw new NotFoundExceptionClass(ValidationConfig.USER_NOT_FOUND);
        }

        extractClaims.put("role", extractRoles(userDetails));
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpire))
                .signWith(getSignInKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    // Generate token with UserDetails
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    // Extract claim from token
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey()).build().parseClaimsJws(token)
                .getBody();
    }

    // Create SignInKey
    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Check Token is it valid
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Check Token is expired
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    // Extract token expired
    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    // Extract roles from token
    public Set<String> extractRoles(UserDetails userDetails) {
        return userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }
}
