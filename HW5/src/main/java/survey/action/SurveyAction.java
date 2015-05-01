package survey.action;

import com.opensymphony.xwork2.ActionSupport;
import survey.dao.StudentDAO;
import survey.domain.DataBean;
import survey.domain.StudentBean;
import survey.processor.DataProcessor;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * Created by alabdullahwi on 4/30/2015.
 */
public class SurveyAction extends ActionSupport {

    private StudentBean studentBean;
    private DataBean dataBean;
    private String raffle;
    private List<String> studentIDs;
    private String backLink = "/index.jsp";

    public String execute() throws Exception {
        try {
            dataBean = DataProcessor.produceDataBean(raffle);
            StudentDAO.saveStudentBean(studentBean);
            studentIDs = StudentDAO.getAllStudentIDs();
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


    public List<String> getStudentIDs() { return studentIDs; }
    public void setStudentIDs(List<String> studentIDs) { this.studentIDs = studentIDs; }
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
    public String getBackLink() {return backLink; }
    public void setBackLink(String backLink) { this.backLink = backLink; }
}
