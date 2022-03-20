package ru.manager.filters;

import ru.manager.dao.IUserDao;
import ru.manager.dao.UserDaoImpl;
import ru.manager.services.crypt.SecurityService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Фильтр для запрета посещения страниц.
 * не авторизованым пользователям.
 */
@WebFilter(urlPatterns = {"/main", "/task/*"})
public class MainRedirectFilter implements Filter {

    private IUserDao dao;
    private SecurityService securityService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        this.dao = new UserDaoImpl();
        this.securityService = new SecurityService();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {


        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;

        try {
            var cookie = Arrays.stream(request.getCookies())
                    .filter(c -> "user".equals(c.getName()))
                    .findAny().orElseThrow(IllegalArgumentException::new);

            var str = cookie.getValue();
            var party = str.split(":");
            var user = dao.findUserByLogin(party[0]).orElseThrow(IllegalArgumentException::new);

            if (securityService.isMatchPassword(party[1], user.getPassword())) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                throw new RuntimeException();
            }

        } catch (Exception e) {
            response.sendRedirect("login");
        }


    }
}
