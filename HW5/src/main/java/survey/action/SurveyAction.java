package survey.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import survey.dao.StudentDAO;
import survey.domain.DataBean;
import survey.domain.StudentBean;
import survey.processor.DataProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * Created by alabdullahwi on 4/30/2015.
 */
public class SurveyAction extends ActionSupport {

    private StudentBean studentBean;
    private DataBean dataBean;
    private String raffle;
    private String backLink = "/index.jsp";

    public String execute() throws Exception {
        HttpServletRequest req = ServletActionContext.getRequest();
        try {
            dataBean = DataProcessor.produceDataBean(raffle);
            StudentDAO.saveStudentBean(studentBean);
            List<String> studentIDs = StudentDAO.getAllStudentIDs();
            HttpSession session = req.getSession();
            session.setAttribute("studentIDs", studentIDs);
            if (dataBean.getMean() >= 90) {
                return "winner";
            } else {
                return "simple";
            }
        }
        catch(SQLIntegrityConstraintViolationException intex) {
            addActionError("StudentID already exists in the table, would not overwrite already existing record");
        }
        catch (Exception e) {
            addActionError(e.getMessage());
            e.printStackTrace();
        }
        return "error";
    }


    public DataBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(DataBean dataBean) {
        this.dataBean = dataBean;
    }

    public String getRaffle() {
        return raffle;
    }

    public void setRaffle(String raffle) {
        this.raffle = raffle;
    }

    public StudentBean getStudentBean() {
        return studentBean;
    }

    public void setStudentBean(StudentBean studentBean) {
        this.studentBean = studentBean;
    }
}
