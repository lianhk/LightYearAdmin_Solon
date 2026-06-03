package com.cms.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * JWT安全工具类
 */
public class SecurityUtils {

    private static final String SECRET = "cms-scaffold-jwt-secret-key-2024";
    private static final long EXPIRE_TIME = 2 * 60 * 60 * 1000L; // 2小时
    private static final String USER_ID = "userId";
    private static final String USERNAME = "username";

    /**
     * 生成JWT Token
     */
    public static String createToken(Long userId, String username) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + EXPIRE_TIME);

        return JWT.create()
                .withClaim(USER_ID, userId)
                .withClaim(USERNAME, username)
                .withIssuedAt(now)
                .withExpiresAt(expireDate)
                .sign(algorithm);
    }

    /**
     * 验证Token并返回解析结果
     */
    public static DecodedJWT verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

    /**
     * 从Token中获取用户ID
     */
    public static Long getUserId(String token) {
        try {
            DecodedJWT jwt = verifyToken(token);
            return jwt.getClaim(USER_ID).asLong();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 从Token中获取用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = verifyToken(token);
            return jwt.getClaim(USERNAME).asString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 验证Token是否有效
     */
    public static boolean isValid(String token) {
        try {
            verifyToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
