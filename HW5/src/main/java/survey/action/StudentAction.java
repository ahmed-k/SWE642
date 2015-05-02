package survey.action;

import com.opensymphony.xwork2.ActionSupport;
import survey.dao.StudentDAO;
import survey.domain.StudentBean;

/**
 * Created by alabdullahwi on 4/30/2015.
 */
public class StudentAction extends ActionSupport {

    String studentID;
    String backLink = "/survey";
    StudentBean student;

    public String execute() throws Exception {

        student = StudentDAO.getStudentBean(studentID);
        if (student == null) {
            addActionError("Requested Student ID" + studentID + "does not exist in the database");
            return "error";
        }
        else {
            return "student";
        }
    }


    public StudentBean getStudent() { return student; }
    public void setStudent(StudentBean student) { this.student = student; }

    public String getStudentID() {
        return studentID;
    }
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getBackLink() { return backLink; }
    public void setBackLink(String backLink) { this.backLink = backLink; }

}
