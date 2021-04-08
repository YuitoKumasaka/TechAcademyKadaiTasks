package controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Tasks;
import util.DBUtil;


@WebServlet("/show")
public class ShowController extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public ShowController() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();
        Tasks t = em.find(Tasks.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("task", t);


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/tasks/show.jsp");
        rd.forward(request, response);
    }



}
