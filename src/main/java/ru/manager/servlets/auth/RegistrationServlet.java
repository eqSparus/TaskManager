package ru.manager.servlets.auth;

import ru.manager.models.dto.UserDto;
import ru.manager.services.auth.AuthService;
import ru.manager.services.auth.AuthUserService;
import ru.manager.services.json.ParserJsonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет для регистрации пользователя.
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = "/reg")
public class RegistrationServlet extends HttpServlet {

    private AuthService service;

    @Override
    public void init() throws ServletException {
        service = new AuthUserService();
    }

    /**
     * Возвращает страницу регистрации.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/page/registration.html").forward(request, response);
    }

    /**
     * Обрабатывает post запрос с логином и паролем пользвоателя для регистрации.
     * В случае удачи отправлет url страницы login.
     * При неудаче возвращает сообщения об ошибке.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        var parser = new ParserJsonService();

        try (var reader = request.getReader();
             var writerResponse = response.getWriter()) {

            var user = parser.toObject(reader, UserDto.class);

            var resp = service.registrationUser(user);

            response.setContentType("application/json");
            if (resp.containsKey("html")) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
            }
            writerResponse.write(parser.toJson(resp));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
