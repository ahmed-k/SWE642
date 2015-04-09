package survey.controller;

import org.apache.commons.beanutils.BeanUtils;
import survey.dao.StudentDAO;
import survey.domain.DataBean;
import survey.domain.StudentBean;
import survey.processor.DataProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Ahmed Alabdullah on 3/29/15.
 */
@WebServlet("/survey")
public class SurveyServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        StudentBean newBean = new StudentBean();
        try {
            Map<String, String[]> params = req.getParameterMap();
            BeanUtils.populate(newBean, params);
            StudentDAO.saveStudentBean(newBean);
            String[] raffle = params.get("raffle");
            DataBean dataBean = DataProcessor.produceDataBean(raffle);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }


        private String[] getRaffle(Map<String, String[]> params) {
            String[] raw = params.get("raffle");
            return raw;
        }

}
