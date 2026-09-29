package com.gabriel.system_user.service.jwt;

import com.gabriel.system_user.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class Jwt {

    private static final String SECRET_KEY = "chave_usuário";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    private static final Long EXPIRATION_TIME = 7200000L;

    protected Jwt() {}

    public String generatedToke(User user) {

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("idUser", user.getIdUser())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY)
                .compact();
    }

    public String extractEmail(String token) {

        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
