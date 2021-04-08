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


@WebServlet("/edit")
public class EditController extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public EditController() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        Tasks t = em.find(Tasks.class, Integer.parseInt(request.getParameter("id")));

        em.close();


        request.setAttribute("task", t);
        request.setAttribute("_token", request.getSession().getId());


        if(t != null) {
            request.getSession().setAttribute("task_id", t.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/tasks/edit.jsp");
        rd.forward(request, response);

    }



}
