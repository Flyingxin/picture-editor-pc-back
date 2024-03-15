package com.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;  // 枚举定义了 JWT 可以使用的所有签名算法
import org.slf4j.Logger;  // 用于记录日志
import org.slf4j.LoggerFactory; // 用于记录日志

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper; // 主要用于扩展 HttpServletRequest 接口的功能
import java.util.*;

public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    // 在 Java 中，下划线 _ 在数字字面量中的使用是为了提高可读性。你可以在数字中任意位置插入下划线，不会改变数字的值
    public static final long EXPIRATION_TIME = 36_000_000L; // 10 hour (毫秒为单位)
    public static final String SECRET = "ThisIsASecret";//please change to your own encryption secret.
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String USER_NAME = "userName";

    public static String generateToken(String userId) {
        // 哈希映射，键是 String 类型，值是 Object 类型。
        HashMap<String, Object> map = new HashMap<>();
        map.put(USER_NAME, userId); // 存储 JWT 的声明。此处只声明用户的 ID。
        String jwt = Jwts.builder() // JWT 构建器。
                .setClaims(map) // 设置 JWT 的声明为 对应map。
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // token过期时间=当前时间+有效期
                .signWith(SignatureAlgorithm.HS512, SECRET) // HS512 签名算法，SECRET 是签名密钥。签名确保 JWT 在传输过程中没有被篡改。
                .compact(); // 将 JWT 压缩成一个字符串。
        return jwt; // token前面一般会加Bearer（此没加）
    }

    public static HttpServletRequest validateTokenAndAddUserIdToHeader(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING); // 获取请求头中的 token。
        if (token == null) throw new RuntimeException("Missing token");

        // parse 解析 token 用户的 ID算法。
        try {
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            return new CustomHttpServletRequest(request, body);
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public static class CustomHttpServletRequest extends HttpServletRequestWrapper {
        private Map<String, String> claims;

        public CustomHttpServletRequest(HttpServletRequest request, Map<String, ?> claims) {
            super(request);
            this.claims = new HashMap<>();
            claims.forEach((k, v) -> {
                this.claims.put(k, String.valueOf(v));
                System.out.println(k + " " + v);
            });
        }

        // 编译器该方法覆盖了父类的getHeaders的方法。
        @Override
        public Enumeration<String> getHeaders(String name) {
            if (claims != null && claims.containsKey(name)) {
                return Collections.enumeration(Arrays.asList(claims.get(name)));
            }
            return super.getHeaders(name);
        }

    }

    //token校验报错实现类
    //    static class TokenValidationException extends RuntimeException {
    //        public TokenValidationException(String msg) {
    //            super(msg);
    //        }
    //    }
}