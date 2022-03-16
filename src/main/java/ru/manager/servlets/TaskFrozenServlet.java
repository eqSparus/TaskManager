package ru.manager.servlets;

import ru.manager.services.ITaskStatusService;
import ru.manager.services.TaskStatusService;
import ru.manager.services.utilities.ParserJsonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/task/frozen", name = "TaskFrozenServlet")
public class TaskFrozenServlet extends HttpServlet {

    private ITaskStatusService statusService;

    @Override
    public void init() throws ServletException {
        statusService = new TaskStatusService();
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        var id = Long.valueOf(request.getParameter("id"));

        try (var writeResponse = response.getWriter()) {

            var task = statusService.updateTaskFrozen(id);

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            writeResponse.write(ParserJsonService.toJson(task));
        }
    }
}
