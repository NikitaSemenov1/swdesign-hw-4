package com.swdesign.course.hw4.ticket.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtProvider {
    private final int JWT_TTL =  1000 * 60 * 60;
    private final String JWT_SECRET = "NOTTOOSHORTSECRET";// 1 hour

    public String generateToken(UUID user_id) {
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + JWT_TTL);

        return Jwts.builder().
                setSubject(user_id.toString()).
                setIssuedAt(currentDate).
                setExpiration(expiryDate).
                signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();

        return claims.getExpiration();
    }


    public boolean validateToken(String token) {
        try {

            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
