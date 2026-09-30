package com.gabriel.system_user.service.jwt;

import com.gabriel.system_user.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class Jwt {

    @Value("${system.user.security.token.secret-key}")
    private String systemUserSecretToken;

    private SecretKey cryptoGraphicKey;

    private static final Long EXPIRATION_TIME = 7200000L;

    public Jwt() {}

    @PostConstruct
    protected void init() {
        this.cryptoGraphicKey = Keys.hmacShaKeyFor(this.systemUserSecretToken.getBytes());
    }

    public String generatedToken(User user) {

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("idUser", user.getIdUser())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(cryptoGraphicKey)
                .compact();
    }

    public String extractEmail(String token) {

        return Jwts.parser()
                .verifyWith(cryptoGraphicKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
