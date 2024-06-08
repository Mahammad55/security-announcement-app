package az.ingress.msannouncementproject.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtService {
    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret-key}")
    private String secretKey;

    public String generateAccessToken(MyUserDetails user) {
        return Jwts.builder()
                .issuer("Mahammad Ilyazov")
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(Duration.ofMinutes(5))))
                .signWith(generateKey(secretKey))
                .header().empty().add(Map.of("type", "JWT")).and()
                .claim("authority", user.getAuthorities())
                .compact();
    }

    public Claims tokenParser(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) generateKey(secretKey))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith((SecretKey) generateKey(secretKey)).build().parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException expiredJwtException) {
            log.error("Jwt expired exception : {}", expiredJwtException.getMessage());
        } catch (IllegalArgumentException illegalArgumentException) {
            log.error("JwtToken is null,empty or only whitespace : {}", illegalArgumentException.getMessage());
        } catch (MalformedJwtException malformedJwtException) {
            log.error("Jwt is invalid : {}", malformedJwtException.getMessage());
        } catch (UnsupportedJwtException unsupportedJwtException) {
            log.error("Jwt is not supported : {}", unsupportedJwtException.getMessage());
        } catch (io.jsonwebtoken.security.SignatureException signatureException) {
            log.error("Signature validation failed : {}", signatureException.getMessage());
        }
        return false;
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsFunction) {
        Claims claims = tokenParser(token);
        return claimsFunction.apply(claims);
    }

    private Key generateKey(String secretKey) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public Authentication getAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
    }
}
