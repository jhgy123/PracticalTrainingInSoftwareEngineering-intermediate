package com.example.elmserver.security.JWT;

import cn.hutool.json.JSONUtil;
import com.example.elmserver.entities.User;
import com.example.elmserver.security.config.LoginSecurityProperties;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class JwtUtils implements InitializingBean {

    public static final String KEY_USER_ID = "userId";

    public static final String KEY_USERNAME = "username";

    public static final String KEY_REALM = "realm";

    public static final String KEY_CLIENT_ID = "clientId";

    public static final String KEY_RAND = "randNum";

    private LoginSecurityProperties loginSecurityProperties;

    private Key key;

    private JwtParser jwtParser;

    @Resource
    public void setLoginSecurityProperties(LoginSecurityProperties loginSecurityProperties) {
        this.loginSecurityProperties = loginSecurityProperties;
    }

    public String createUserToken(User userDetails, String secret) {
        Map<String, Object> dataMap = new HashMap<>(5);
        dataMap.put(KEY_USERNAME, userDetails.getUsername());
        dataMap.put(KEY_USER_ID, userDetails.getId());
        dataMap.put(KEY_RAND, secret);
        return createToken(dataMap);
    }

    public User parseUser(String token) {
        try {
            Map<String, Object> map = parseToken(token);
            return User.builder()
                    .id(map.get(KEY_USER_ID).toString())
                    .username(map.get(KEY_USERNAME).toString()).password("")
                    .build();
        } catch (NullPointerException e) {
            if (log.isDebugEnabled()) {
                log.error("token validation failed with missing required fields");
            }
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.error("parse token fail, {}", e);
            }
        }
        return null;
    }

    public User parseUser(Map<String, Object> map) {
        try {
            return User.builder()
                    .id(map.get(KEY_USER_ID).toString())
                    .username(map.get(KEY_USERNAME).toString()).password("")
                    .secret(map.get(KEY_RAND).toString())
                    .build();
        } catch (NullPointerException e) {
            if (log.isDebugEnabled()) {
                log.error("token validation failed with missing required fields");
            }
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.error("parse token fail, {}", e);
            }
        }
        return null;
    }

    String createToken(Map<String, Object> dataMap) {
        return Jwts.builder().setPayload(JSONUtil.toJsonStr(dataMap)).signWith(key).compact();
    }

    public Map<String, Object> parseToken(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    @Override
    public void afterPropertiesSet() {
        this.jwtParser = Jwts.parserBuilder().setSigningKey(loginSecurityProperties.getSecretKey().getBytes()).build();
        this.key = Keys.hmacShaKeyFor(loginSecurityProperties.getSecretKey().getBytes());
    }

}
