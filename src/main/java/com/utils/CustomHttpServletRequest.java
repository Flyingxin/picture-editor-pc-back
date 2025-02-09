package com.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

public class CustomHttpServletRequest  extends HttpServletRequestWrapper {
    private final Map<String, String> requestUser;

    public CustomHttpServletRequest(HttpServletRequest request, Map<String, ?> claims) {
        super(request);
        this.requestUser = new HashMap<>();
        claims.forEach((k, v) -> {
            this.requestUser.put(k, String.valueOf(v));
        });
    }

    // 编译器该方法覆盖了父类的getHeaders的方法。
    @Override
    public Enumeration<String> getHeaders(String name) {
        if (requestUser != null && requestUser.containsKey(name)) {
            return Collections.enumeration(Arrays.asList(requestUser.get(name)));
        }
        return super.getHeaders(name);
    }
}
