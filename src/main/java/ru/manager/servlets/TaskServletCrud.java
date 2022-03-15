package ru.manager.servlets;

import ru.manager.models.dto.TaskDtoRequest;
import ru.manager.services.ITaskService;
import ru.manager.services.TaskService;
import ru.manager.services.json.ParserJsonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(urlPatterns = "/task/crud", name = "TaskServletCrud")
public class TaskServletCrud extends HttpServlet {

    private ITaskService taskService;

    @Override
    public void init() throws ServletException {
        taskService = new TaskService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (var reader = request.getReader();
             var writerResponse = response.getWriter()) {

            var login = Arrays.stream(request.getCookies())
                    .filter(cookie -> "user".equals(cookie.getName()))
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new)
                    .getValue()
                    .split(":")[0];

            var dtoTask = ParserJsonService.toObject(reader, TaskDtoRequest.class);

            System.out.println(dtoTask);

            var responseTask = taskService.createTask(dtoTask, login);

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_CREATED);
            writerResponse.write(ParserJsonService.toJson(responseTask));
        }


    }
}
