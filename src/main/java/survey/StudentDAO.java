package survey;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Ahmed Alabdullah on 3/29/15.
 */
public class StudentDAO {


    private static Connection connection;
    private static Driver driver;

    static {
        InputStream _stream = StudentDAO.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(_stream);
            driver = new oracle.jdbc.OracleDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(properties.getProperty("db.url"), properties);
        } catch (Exception ex) {

        }
    }


    public static void saveStudentBean(StudentBean toSave) {
       String query = "INSERT INTO SURVEY (FIRSTNAME, LASTNAME, VALUES() ";

    }
    public static StudentBean getStudentBean(String _ID) throws SQLException {
        String query = "SELECT * from STUDENTS WHERE STUDENT_ID = '" + _ID + "';";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {


        }
        StudentBean retv = new StudentBean();


       return retv;
    }
}
