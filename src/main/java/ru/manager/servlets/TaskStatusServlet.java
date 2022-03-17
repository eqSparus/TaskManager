package ru.manager.servlets;

import ru.manager.models.StatusTask;
import ru.manager.services.ITaskStatusService;
import ru.manager.services.TaskStatusService;
import ru.manager.services.utilities.ParserJsonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TaskStatusServlet", urlPatterns = "/task/status")
public class TaskStatusServlet extends HttpServlet {

    private ITaskStatusService statusService;

    @Override
    public void init() throws ServletException {
        statusService = new TaskStatusService();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        var status = StatusTask.convert(request.getParameter("status"));
        var id = Long.valueOf(request.getParameter("id"));

        var task = switch (status) {
            case ACTIVE -> statusService.updateTaskActive(id);
            case DONE -> statusService.updateTaskDone(id);
            case FROZEN -> statusService.updateTaskFrozen(id);
            default -> throw new IllegalStateException("Unexpected value: " + status);
        };

        try (var writeResponse = response.getWriter()){
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            writeResponse.write(ParserJsonService.toJson(task));
        }


    }
}
