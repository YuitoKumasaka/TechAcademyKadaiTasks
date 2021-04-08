package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Tasks;
import util.DBUtil;


@WebServlet("/new")
public class NewController extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public NewController() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            EntityManager em = DBUtil.createEntityManager();


            Tasks t = new Tasks();

            String content = "sleep";
            t.setContent(content);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());     // 現在の日時を取得
            t.setCreated_at(currentTime);
            t.setUpdated_at(currentTime);

            // データベースに保存
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();

            // 自動採番されたIDの値を表示
            response.getWriter().append(Integer.valueOf(t.getId()).toString());

            em.close();

    }

}
