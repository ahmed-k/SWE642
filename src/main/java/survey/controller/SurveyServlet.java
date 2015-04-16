package survey.controller;

import org.apache.commons.beanutils.BeanUtils;
import survey.dao.StudentDAO;
import survey.domain.DataBean;
import survey.domain.StudentBean;
import survey.processor.DataProcessor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

/**
 * Created by Ahmed Alabdullah on 3/29/15.
 */
@WebServlet("/survey")
public class SurveyServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String nextPage = "";

        if (req.getParameter("reqStudentId")!= null) {
            String id = req.getParameter("reqStudentId");
            StudentBean requestedStudent = StudentDAO.getStudentBean(id);
            req.setAttribute("currentStudent", requestedStudent);
            nextPage = "WEB-INF/view/ShowParticularStudent.jsp";

        }

        RequestDispatcher dispatcher =  req.getRequestDispatcher(nextPage);
        dispatcher.forward(req, res);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String nextPage = "";
        //requested a particular student
            StudentBean newBean = new StudentBean();
            try {
                Map<String, String[]> params = req.getParameterMap();
                BeanUtils.populate(newBean, params);
                try {
                    StudentDAO.saveStudentBean(newBean);
                    String[] raffle = params.get("raffle");
                    DataBean dataBean = DataProcessor.produceDataBean(raffle);
                    HttpSession session = req.getSession();
                    session.setAttribute("dataBean", dataBean);

                    //get all Student IDs
                    List<String> studentIDs = StudentDAO.getAllStudentIDs();
                    req.setAttribute("studentIDs", studentIDs);

                    //redirect logic
                    if (dataBean.getMean() >= 90) {
                        //redirect to WinnerAcknolwedgment.jsp
                        nextPage = "WEB-INF/view/WinnerAcknowledgment.jsp";
                    } else {
                        //redirect to SimpleAcknowledgement.js
                        nextPage = "WEB-INF/view/SimpleAcknowledgment.jsp";
                    }
                } catch (SQLIntegrityConstraintViolationException intex) {
                    nextPage = "WEB-INF/view/errorPage.jsp";
                    req.setAttribute("error", "StudentID already exists in the table, would not overwrite already existing record");
                } catch (SQLException sqx) {
                    nextPage = "WEB-INF/view/errorPage.jsp";
                    req.setAttribute("error", "Database Error.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            RequestDispatcher dispatcher =  req.getRequestDispatcher(nextPage);
            dispatcher.forward(req, res);
    }


}
