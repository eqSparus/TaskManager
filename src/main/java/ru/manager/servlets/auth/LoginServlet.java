package ru.manager.servlets.auth;

import ru.manager.models.dto.UserDto;
import ru.manager.services.auth.AuthUserService;
import ru.manager.services.auth.IAuthService;
import ru.manager.services.json.ParserJsonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Сервлет для входа пользователя.
 */
@WebServlet(urlPatterns = "/login", name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    private IAuthService authService;

    @Override
    public void init() throws ServletException {
        this.authService = new AuthUserService();

    }


    /**
     * Возвращает страницу входа.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/page/login.html").forward(request, response);
    }

    /**
     * Обрабатывает post запрос с логином и паролем пользвоателя для входа.
     * В случае удачного вхожа создает cookie с логином и паролем пользователя
     * и возвращает url страницы main.
     * При неудаче возвращает сообщения об ошибке.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String> resp = new HashMap<>();

        response.setContentType("application/json");

        try (var reader = request.getReader();
             var responseWriter = response.getWriter()) {

            var user = ParserJsonService.toObject(reader, UserDto.class);

            if (authService.authorizationUser(user)) {
                var value = String.format("%s:%s", user.getLogin(), user.getPassword());

                var cookie = new Cookie("user", value);
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
                response.setStatus(HttpServletResponse.SC_OK);
                resp.put("html", "http://localhost:8080/main");
            } else {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                resp.put("message", "Incorrect login or password!");
            }


            responseWriter.write(ParserJsonService.toJson(resp));
        }

    }

}
