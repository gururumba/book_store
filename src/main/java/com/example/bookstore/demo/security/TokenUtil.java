package com.example.bookstore.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Timestamp;
import java.util.Date;


@Component
public class TokenUtil {

    @Value("APP")
    private String APP_NAME;

    @Value("secret")
    public String SECRET;

    @Value("3600000")
    private int EXPIRES_IN;

    @Value("Authorization")
    private String AUTH_HEADER;

    private static final String AUDIENCE_WEB = "web";
    private static final String AUDIENCE_MOBILE = "mobile";
    private static final String AUDIENCE_TABLET = "tablet";

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public String generateToken(String email, String role){
        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(email)
                .setAudience(generateAudience())
                .setIssuedAt(new Date())
                .setExpiration(generateExpiriatonDate())
                .claim("roles", role)
                .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
    }

    private Date generateExpiriatonDate() {
        return new Date(new Date().getTime() + EXPIRES_IN);
    }

    private String generateAudience() {
        return AUDIENCE_WEB;
    }

    public String refreshToken(String token){
        String refreshedToken;
        try{
            final Claims claims = this.getAllClaimsFromToken(token);
            claims.setIssuedAt(new Date());
            refreshedToken = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(generateExpiriatonDate())
                    .signWith(SIGNATURE_ALGORITHM,SECRET).compact();
        }catch (Exception e){
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public boolean canTokenBeRefreshed(String token, Timestamp lastPasswordReset){
        final Date created = this.getIssuedAtDateFromToken(token);
        return (!(this.isCreatedBeforeLastPasswordReset(created, lastPasswordReset))
            && (!(this.isTokenExpired(token)) || this.ignoreTokenExpiration(token)));
    }

    private boolean ignoreTokenExpiration(String token) {
        String audience = this.getAudienceFromToken(token);
        return (audience.equals(AUDIENCE_TABLET) || audience.equals(AUDIENCE_MOBILE));
    }

    private String getAudienceFromToken(String token) {
        String audience;
        try{
            final Claims claims = this.getAllClaimsFromToken(token);
            audience = claims.getAudience();
        }catch (Exception e){
            audience = null;
        }
        return audience;
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = this.getIssuedAtDateFromToken(token);
        return expiration.before(new Date());

    }

    private boolean isCreatedBeforeLastPasswordReset(Date created, Timestamp lastPasswordReset) {
        return (lastPasswordReset != null && created.before(new Date(lastPasswordReset.getTimestamp().getTime())));
    }

    public boolean validateToken(String token, UserDetails userDetails){
        UserDetails user = (UserDetails) userDetails;
        final String username = getEmailFromToken(token);
        final Date created = getIssuedAtDateFromToken(token);

        return (username != null && username.equals(user.getUsername()));
    }

    private Date getIssuedAtDateFromToken(String token) {
        Date issuedAt;
        try{
            final Claims claims = this.getAllClaimsFromToken(token);
            issuedAt = claims.getIssuedAt();
        }catch (Exception e){
            issuedAt = null;
        }
        return issuedAt;
    }

    private String getEmailFromToken(String token) {
        String email;
        try{
            final Claims claims = this.getAllClaimsFromToken(token);
            email = claims.getSubject();
        }catch (Exception e){
            email = null;
        }
        return email;
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try{
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            claims = null;
        }
        return claims;
    }

    public String getToken(HttpServletRequest request){
        String authHeader = getAuthHeaderFromHeader(request);
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }
        return null;
    }

    private String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }
}
