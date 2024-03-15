package com.jwt;
import com.common.GlobalData;

import org.springframework.util.AntPathMatcher; //
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
 * 校验路由的Token类
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final PathMatcher pathMatcher = new AntPathMatcher();

    /***
     * 重写OncePerRequestFilter类的过滤器
     * @param request 请求
     * @param response 响应
     * @param filterChain 过滤链
     * @ throws ServletException
     * @ throws IOException
     * 重写(请求只进行一次过滤的方法)，
     * 来校验是否是受保护的 URL
     * 若被保护则过滤掉
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            if(isProtectedUrl(request) && !request.getMethod().equals("OPTIONS")) {
                request = JwtUtil.validateTokenAndAddUserIdToHeader(request);
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        filterChain.doFilter(request, response);
    }

    /***
     * 不受保护的 API URL 映射列表
     * @param request
     * @return
     */
    private boolean isProtectedUrl(HttpServletRequest request) {
        List<String> protectedPaths = new ArrayList<String>();
//        protectedPaths.add("/api/user/login");
//        protectedPaths.add("/api/user/register");

        boolean isProtected = false;
        for( String path : protectedPaths ) {
            isProtected = pathMatcher.match(path, GlobalData.API_PREFIX + '/' + request.getServletPath());
            if( isProtected ) break;
        }
        return isProtected; // 取反就不受保护了
    }

}