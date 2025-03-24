package com.cogent.TweetApp.Security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtTokenProvider {

    // secret key
    private String jwtSecret = "cGxlYXNlIGVuY29kZSBteSBqd3Qgc2VjcmV0IGNvZGUgZm9yIG15IHR3aXR0ZXIgYXBwCg==";

    private Long jwtExpiration = 604800L;

    // generate token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + (jwtExpiration*1000));
        return Jwts.builder()
                .subject(username)
                .issuedAt(currentDate)
                .expiration(expiryDate)
                .signWith(key())
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsername(String token) {
        return Jwts
                .parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("Invalid Token");
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(null, null, "Jwt token is expired");
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtException("Unsupported token");
        } catch (RuntimeException e) {
            throw new RuntimeException("Something went wrong");
        }
    }

}
