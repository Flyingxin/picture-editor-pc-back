package com.utils.jwt;
import com.constant.GlobalConstant;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher; // 用于匹配 URL 路径的工具类
// 这个类确保在一次请求中，请求只进行一次过滤，即使请求被转发到另一个 servlet 或 controller。
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * jwt校验路由与Token类
 * 暂时没用谢谢
 */
public class JwtFilter extends OncePerRequestFilter {

    private static final PathMatcher pathMatcher = new AntPathMatcher();


    /***
     * OncePerRequestFilter类的过滤器
     * todo: 用的时候开启注释
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        受保护api，解析token信息
//        if(!unprotectApi(request) && !request.getMethod().equals("OPTIONS")) {
//            request = JwtUtil.parserToken(request);
//        }
        filterChain.doFilter(request, response);
    }

    /***
     * 不受保护的 API URL 映射列表
     * @return boolean
     */
    private boolean unprotectApi(HttpServletRequest request) {
        List<String> apiList = new ArrayList<>();
        apiList.add("/api/user/login");
        apiList.add("/api/user/register");

        boolean unprotect = false;
        for( String api : apiList ) {
            unprotect = pathMatcher.match(api, GlobalConstant.API_PREFIX + '/' + request.getServletPath());
            if( unprotect ) break;
        }
        return unprotect;
    }

}