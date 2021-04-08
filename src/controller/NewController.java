package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Tasks;


@WebServlet("/new")
public class NewController extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public NewController() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());


            request.setAttribute("task", new Tasks());

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/tasks/new.jsp");
            rd.forward(request, response);

    }

}
