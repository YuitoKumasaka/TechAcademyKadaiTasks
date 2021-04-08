package controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Tasks;
import util.DBUtil;


@WebServlet("/index")
public class IndexController extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public IndexController() {
        super();

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();

        List<Tasks> tasks = em.createNamedQuery("getAllTasks",Tasks.class).getResultList();

        response.getWriter().append(Integer.valueOf(tasks.size()).toString());

        em.close();

        request.setAttribute("tasks", tasks);

        /*if(request.getSession().getAttribute("flush") != null) {

            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }*/
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/tasks/index.jsp");
        rd.forward(request, response);
    }

}
