package com.utils.jwt;

import com.dto.CommonAuthInfoDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;  // 枚举定义了 JWT 可以使用的所有签名算法
import org.slf4j.Logger;  // 用于记录日志
import org.slf4j.LoggerFactory; // 用于记录日志

import java.lang.reflect.Field;
import java.util.*;
import java.util.Date;

public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    // 在 Java 中，下划线 _ 在数字字面量中的使用是为了提高可读性。你可以在数字中任意位置插入下划线，不会改变数字的值
    public static final long EXPIRATION_TIME = 1000 * 60 * 10 * 24; // 24 hour
    public static final String SECRET = "secret";//please change to your own encryption secret.
    public static final String TOKEN_PREFIX = "Bearer ";

    // 生成通用token
    public static String commonGenerateToken(CommonAuthInfoDto commonAuthInfoDto) {
        return Jwts.builder()
                // Header
                .setHeaderParam("type", "jwt")
                .setHeaderParam("alg","HS256")
                // payload 载荷
                .setClaims(convertToMap(commonAuthInfoDto))
                // 签名
                .signWith(SignatureAlgorithm.HS256, SECRET) // HS256 签名算法，SECRET 是签名密钥。签名确保 JWT 在传输过程中没有被篡改。
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // token过期时间=当前时间+有效期
                .setId(UUID.randomUUID().toString())
                .compact(); // 将 JWT 压缩成一个字符串。
        // token前面一般会加Bearer（此没加）
    }

    // 解析token
    // <T> 是一个类型参数参数参数参数参数声明，表示该方法是泛型方法。T 是一个占位符，用于代表将来在调用该方法时传入的具体类型。
    public static <T> T parseToken(String token, Class<T> dtoClass) {
        if (token == null) new HashMap<>();
        try {
            Map<String, Object> tokenInfo = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();

            return convertToDto(tokenInfo, dtoClass);
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

//    // filter过滤器专用解析token

//    public static final String HEADER_STRING = "Authorization";
//    public static HttpServletRequest parserToken(HttpServletRequest request) {
//        String token = request.getHeader(HEADER_STRING); // 获取请求头中的 token。
//        if (token == null) return request;
//        try {
//            Map<String, Object> tokenInfo = Jwts.parser()
//                    .setSigningKey(SECRET)
//                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
//                    .getBody();
//            return new CustomHttpServletRequest(request, tokenInfo);
//        } catch (Exception e) {
//            logger.info(e.getMessage());
//            throw new RuntimeException(e.getMessage());
//        }
//    }

    // DTO转map
    public static Map<String, Object> convertToMap(Object dto) {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = dto.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // 允许访问私有字段
            try {
                map.put(field.getName(), field.get(dto)); // 将字段名和字段值放入 map
            } catch (IllegalAccessException e) {
                e.printStackTrace(); // 处理异常
            }
        }
        return map;
    }

    // Map 转 DTO
    public static <T> T convertToDto(Map<String, Object> map, Class<T> dtoClass) {
        try {
            T dto = dtoClass.getDeclaredConstructor().newInstance(); // 创建 DTO 实例
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String fieldName = entry.getKey();
                Object value = entry.getValue();

                try {
                    Field field = dtoClass.getDeclaredField(fieldName);
                    field.setAccessible(true); // 允许访问私有字段
                    field.set(dto, value); // 设置字段值
                } catch (NoSuchFieldException e) {
                    // 忽略不存在的字段
                }
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace(); // 处理异常
            return null;
        }
    }


}