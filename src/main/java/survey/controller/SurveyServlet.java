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

    public static final String VIEW_ROOT = "WEB-INF/view/";

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String nextPage = "";


        if (req.getParameter("reqStudentId")!= null) {
            String id = req.getParameter("reqStudentId");
            StudentBean requestedStudent = StudentDAO.getStudentBean(id);
            if (requestedStudent == null) {
                nextPage = VIEW_ROOT + "ErrorPage.jsp";
                req.setAttribute("error", "Requested student ID " + id + " does not exist in the database.");
                req.setAttribute("backLink", "/survey?reqStudentId=" + id);
            }
            else {
                req.setAttribute("currentStudent", requestedStudent);
                nextPage = VIEW_ROOT + "ShowParticularStudent.jsp";
                req.setAttribute("backLink", "/survey?backToPreviousPage=true");
            }
        }
        else if (req.getParameter("backToPreviousPage").equals("true")) {
            nextPage = (String) req.getSession().getAttribute("previousPage");
        }

        RequestDispatcher dispatcher =  req.getRequestDispatcher(nextPage);
        dispatcher.forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
       HttpSession session = req.getSession();
        //clean session from all previous request params:
        session.removeAttribute("dataBean");
        session.removeAttribute("studentIDs");
        session.removeAttribute("previousPage");


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
                    session.setAttribute("dataBean", dataBean);

                    //get all Student IDs
                    List<String> studentIDs = StudentDAO.getAllStudentIDs();
                    session.setAttribute("studentIDs", studentIDs);

                    //redirect logic
                    if (dataBean.getMean() >= 90) {
                        //redirect to WinnerAcknolwedgment.jsp
                        nextPage = VIEW_ROOT + "WinnerAcknowledgment.jsp";
                        session.setAttribute("previousPage", nextPage);
                    } else {
                        //redirect to SimpleAcknowledgement.js
                        nextPage = VIEW_ROOT + "SimpleAcknowledgment.jsp";
                        session.setAttribute("previousPage", nextPage);
                    }
                } catch (SQLIntegrityConstraintViolationException intex) {
                    nextPage = VIEW_ROOT + "ErrorPage.jsp";
                    req.setAttribute("error", "StudentID already exists in the table, would not overwrite already existing record");
                    req.setAttribute("backLink", "/index.jsp");
                } catch (SQLException sqx) {
                    nextPage = VIEW_ROOT + "ErrorPage.jsp";
                    req.setAttribute("error", "Database Error.");
                    req.setAttribute("backLink", "/index.jsp");
                }
            } catch (Exception e) {
                nextPage = VIEW_ROOT + "ErrorPage.jsp";
                req.setAttribute("error", e.getMessage());
                req.setAttribute("backLink", "/index.jsp");
                //for debugging sake
                e.printStackTrace();
            }
            RequestDispatcher dispatcher =  req.getRequestDispatcher(nextPage);
            dispatcher.forward(req, res);
    }


}
