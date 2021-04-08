package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Tasks;
import model.validator.TaskValidator;
import util.DBUtil;


@WebServlet("/update")
public class UpdateController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public UpdateController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();


            Tasks t = em.find(Tasks.class, (Integer)(request.getSession().getAttribute("task_id")));



            String content = request.getParameter("content");
            t.setContent(content);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            t.setUpdated_at(currentTime);


            List<String> errors = TaskValidator.validate(t);
            if(errors.size() > 0) {
                em.close();


                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("task", t);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/tasks/edit.jsp");
                rd.forward(request, response);
            } else {

                em.getTransaction().begin();
                em.getTransaction().commit();
                request.getSession().setAttribute("flush", "更新が完了しました。");
                em.close();


                request.getSession().removeAttribute("task_id");


                response.sendRedirect(request.getContextPath() + "/index");
    }

        }
    }
}
