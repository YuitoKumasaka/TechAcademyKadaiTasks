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
import util.DBUtil;;



@WebServlet("/create")
public class CreateController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Tasks t = new Tasks();

            String content = request.getParameter("content");
            t.setContent(content);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            t.setCreated_at(currentTime);
            t.setUpdated_at(currentTime);


            List<String> errors = TaskValidator.validate(t);
            if(errors.size() > 0) {
                em.close();


                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("message", t);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/tasks/new.jsp");
                rd.forward(request, response);
            } else {

                em.getTransaction().begin();
                em.persist(t);
                em.getTransaction().commit();
                request.getSession().setAttribute("flush", "登録が完了しました。");
                em.close();


                response.sendRedirect(request.getContextPath() + "/index");
            }

        }

    }

}
