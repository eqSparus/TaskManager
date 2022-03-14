package ru.manager.servlets.auth;

import ru.manager.models.dto.UserDto;
import ru.manager.services.auth.IAuthService;
import ru.manager.services.auth.AuthUserService;
import ru.manager.services.json.ParserJsonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Сервлет для регистрации пользователя.
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = "/reg")
public class RegistrationServlet extends HttpServlet {

    private IAuthService service;

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

        Map<String, String> resp = new HashMap<>();

        try (var reader = request.getReader();
             var writerResponse = response.getWriter()) {

            var user = ParserJsonService.toObject(reader, UserDto.class);

            response.setContentType("application/json");
            if (service.registrationUser(user)) {
                response.setStatus(HttpServletResponse.SC_OK);
                resp.put("html", "http://localhost:8080/login");
            } else {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                resp.put("message", "This user already exists!");
            }
            writerResponse.write(ParserJsonService.toJson(resp));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
