package ru.manager.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Фильтр для запрета посещения авторизованых пользователей.
 * на страницы регистрации и авторизации.
 */
@WebFilter(urlPatterns = {"/registration", "/login"})
public class LoginRedirectFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;

        var cookies = request.getCookies();
        if (cookies != null) {

            var cookie = Arrays.stream(cookies)
                    .filter(c -> "user".equals(c.getName()))
                    .findAny();
            if (cookie.isPresent()) {
                response.sendRedirect("task");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
}
