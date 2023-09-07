package zalooa.zalooa.service;

import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  @Value("${app.jwtSecret}")
  private String secret;

  @Value("${app.jwtExpirationMs}")
  private long expirationMs;

  private final RedisTemplate<String, Object> redisTemplate;

  private static final String TOKEN_PREFIX = "token:";

  @Autowired
  private JwtEncoder jwtEncoder;

  @Autowired
  private JwtDecoder jwtDecoder;

  @Autowired
  public TokenService(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public String generateJwt(Authentication authentication) {
    Instant nowInstant = Instant.now();
    Instant expirationInstant = nowInstant.plusMillis(expirationMs);

    String scope = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(
        Collectors.joining(""));

    JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer("self")
        .issuedAt(nowInstant)
        .subject(authentication.getName())
        .claim("roles", scope)
        .expiresAt(Date.from(expirationInstant).toInstant())
        .build();

    return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }

  public void saveToken(String userId, String token) {
    redisTemplate.opsForValue().set(TOKEN_PREFIX + userId, token);
  }

  public String getToken(String userId) {
    return (String) redisTemplate.opsForValue().get(TOKEN_PREFIX + userId);
  }

  public void deleteToken(String userId) {
    redisTemplate.delete(TOKEN_PREFIX + userId);
  }
}
