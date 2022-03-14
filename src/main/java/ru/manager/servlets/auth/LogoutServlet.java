package ru.manager.servlets.auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(urlPatterns = "/logout", name = "LogoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Arrays.stream(request.getCookies())
                .filter(cookie -> "user".equals(cookie.getName()))
                .forEach(cookie -> {
                    var c = new Cookie(cookie.getName(),"");
                    c.setMaxAge(0);
                    response.addCookie(c);
                });

        response.sendRedirect("login");
    }
}
