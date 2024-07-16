package com.karen.springsecurity.config;

import com.karen.springsecurity.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    private Long EXPIRATION_MINUTES = 30L;
    private  String SECRET_KEY = "VGhpcyBpcyBteSBzZWNyZXQga2V5IFRyeSBoYWNraW5nIGl0IGFuZCB5b3Ugd2lsbCBnZXQgaW4gYSBob3QgbWVzcw==";
    public String generateToken(User user, Map<String, Object> extraClaims) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiresAt = new Date(issuedAt.getTime() + (EXPIRATION_MINUTES * 60 * 1000));
       return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expiresAt)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();


    }
    private Key generateKey(){
        byte[] secretAsBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(secretAsBytes);

    }

    public String extractUserName(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder().setSigningKey(generateKey()).build()
                .parseClaimsJws(jwt).getBody();
    }
}
