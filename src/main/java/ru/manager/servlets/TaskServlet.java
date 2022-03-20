package ru.manager.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет страницы задач
 */
@WebServlet(urlPatterns = "/task", name = "MainServlet")
public class TaskServlet extends HttpServlet {

    /**
     * Возвращает страницу задач
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/page/task.html").forward(request, response);
    }

}
