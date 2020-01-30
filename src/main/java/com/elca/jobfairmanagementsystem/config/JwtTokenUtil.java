package com.elca.jobfairmanagementsystem.config;

import com.elca.jobfairmanagementsystem.dto.UserDto;
import com.elca.jobfairmanagementsystem.entity.User;
import com.elca.jobfairmanagementsystem.service.UserRoleService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.JwtMap;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;

import static com.elca.jobfairmanagementsystem.util.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
import static com.elca.jobfairmanagementsystem.util.Constants.SIGNING_KEY;

@Component
public class JwtTokenUtil {

    @Autowired
    private UserRoleService userRoleService;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDto user) {
        return doGenerateToken(user.getVisa());
    }

    private String doGenerateToken(String subject) {

        Claims claims = Jwts.claims().setSubject(subject);
        var role = userRoleService.getUserRoleByVisa(subject).getRole().getName();
        claims.put("role", "ROLE_"+role);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("http://elca.mu")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
                username.equals(userDetails.getUsername())
                        && !isTokenExpired(token));
    }

    public String getRoleFromToken(String token) {
       var claims = getAllClaimsFromToken(token);
        System.out.println(claims.get("role", String.class));
        return claims.get("role", String.class);
    }

    @Data
    class Authority{
        String authority;
    }
}
