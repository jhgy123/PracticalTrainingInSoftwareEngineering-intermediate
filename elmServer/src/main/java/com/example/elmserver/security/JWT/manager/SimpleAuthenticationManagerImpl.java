package com.example.elmserver.security.JWT.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
class Secret {

    private String secret;

    private long expiredTime;

}

/**
 * 基于内存的jwt凭据管理器
 */
@Service
public class SimpleAuthenticationManagerImpl extends AbstractAuthenticationManager {

    private final Map<String, Secret> map = new ConcurrentHashMap<>();

    @Override
    public void loginInner(String key, String value) {
        Secret secretItem = new Secret(value, System.currentTimeMillis() + loginSecurityProperties.getDefaultTimeout());
        map.put(key, secretItem);
    }

    @Override
    public void logoutInner(String key) {
        map.remove(key);
    }

    private String getSecret(String key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Secret secret = map.get(key);
        if (System.currentTimeMillis() > secret.getExpiredTime()) {
            map.remove(key);
            return null;
        }
        secret.setExpiredTime(System.currentTimeMillis() + loginSecurityProperties.getDefaultTimeout());
        map.put(key, secret);
        return secret.getSecret();
    }

    @Override
    public boolean validateInner(String key, String secret) {
        return secret != null && secret.equals(getSecret(key));
    }

    @Override
    protected List<String> getAllKey(String userName, String userId) {
        String key = generateKey(userName, userId, "");
        return map.keySet().stream().filter(i -> i.startsWith(key)).collect(Collectors.toList());
    }

}
