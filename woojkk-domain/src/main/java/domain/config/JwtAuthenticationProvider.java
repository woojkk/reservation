package domain.config;

import domain.common.UserType;
import domain.common.UserVo;
import domain.util.Aes256Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Objects;

public class JwtAuthenticationProvider {
    private String secretKey = "secretKey";

    private final long tokenValidTime = 1000L * 60 * 60 * 24;

    public String createToken(String userPK, Long id, UserType userType) {
        Claims claims = Jwts.claims().setSubject(Aes256Util.encrypt(userPK))
                .setId(Aes256Util.encrypt(id.toString()));
        claims.put("roles", userType);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secretKey).parseClaimsJws(jwtToken);

            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public UserVo getUserVo(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();

        return new UserVo(Long.valueOf(Objects.requireNonNull(
                Aes256Util.decrypt(claims.getId()))),
                Aes256Util.decrypt(claims.getSubject()),
                UserType.valueOf(claims.get("roles",String.class)));
    }

}
