package survey.dao;

import survey.domain.StudentBean;

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
            properties.setProperty("user", properties.getProperty("db.username"));
            properties.setProperty("password", properties.getProperty("db.password"));
            driver = new oracle.jdbc.OracleDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(properties.getProperty("db.url"), properties);
            System.out.println (connection);
        } catch (Exception ex) {
            System.out.println("Failed to establish connection");
            ex.printStackTrace();

        }
    }


    public static String[] convertStringToStringArray(String str) {
       return str.split(",");
    }

    public static String convertStringArrayToString(String[] arr) {
       String retv ="";

       for (String str : arr) {
           retv+= str+",";
       }

       //trim trailing comma
       retv = retv.substring(0, retv.length()-1);
       return retv;
    }


    public static void saveStudentBean(StudentBean toSave) {
        String query = "INSERT INTO SURVEY (STUDENT_ID, FIRSTNAME, LASTNAME,STREET, CITY, ZIP, TELEPHONE, EMAIL, URL, REFERRAL_SOURCE, CAMPUS, GRADUATION_MONTH, GRADUATION_YEAR, RECOMMENDATION_LIKELIHOOD, ADDITIONAL_COMMENTS)"+
                            "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, toSave.getStudentID());
            pstmt.setString(2, toSave.getFirstName());
            pstmt.setString(3, toSave.getLastName());
            pstmt.setString(4, toSave.getStreet());
            pstmt.setString(5, toSave.getCity());
            pstmt.setString(6, toSave.getZip());
            pstmt.setString(7, toSave.getTelephone());
            pstmt.setString(8, toSave.getEmail());
            pstmt.setString(9, toSave.getUrl());
            pstmt.setString(10, toSave.getReferralSource());
            pstmt.setString(11, convertStringArrayToString(toSave.getCampus()));
            pstmt.setString(12, toSave.getGraduationMonth());
            pstmt.setString(13, toSave.getGraduationYear());
            pstmt.setString(14, toSave.getRecommendationLikelihood());
            pstmt.setString(15, toSave.getAdditionalComments());
            pstmt.executeUpdate(query);
        }
        catch(SQLException sqx) {
            System.out.println("Failed to save to database");
            sqx.printStackTrace();
        }
        finally {
            try {
                pstmt.close();
            }
            catch (Exception sqx) {
                sqx.printStackTrace();
            }
        }
    }
    public static StudentBean getStudentBean(String _ID) {
        StudentBean retv = new StudentBean();
        String query = "SELECT * from AMUHAMM3.SURVEY WHERE STUDENT_ID = '" + _ID + "';";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                retv.setStudentID(rs.getString("STUDENT_ID"));
                retv.setFirstName(rs.getString("FIRST_NAME"));
                retv.setLastName(rs.getString("LAST_NAME"));
                retv.setCity(rs.getString("CITY"));
                retv.setStreet(rs.getString("STREET"));
                retv.setZip(rs.getString("ZIP"));
                retv.setEmail(rs.getString("EMAIL"));
                retv.setUrl(rs.getString("URL"));
                retv.setRecommendationLikelihood(rs.getString("RECOMMENDATION_LIKELIHOOD"));
                retv.setGraduationMonth(rs.getString("GRADUATION_MONTH"));
                retv.setGraduationYear(rs.getString("GRADUATION_YEAR"));
                retv.setAdditionalComments(rs.getString("ADDITIONAL_COMMENTS"));
                retv.setCampus(convertStringToStringArray(rs.getString("CAMPUS")));
                retv.setReferralSource(rs.getString("REFERRAL_SOURCE"));
            }
        }
        catch (SQLException sqx) {
            sqx.printStackTrace();
        }
        finally {
            try {
                stmt.close();
                rs.close();
            }
            catch (SQLException sqx) {
                sqx.printStackTrace();
            }
        }


       return retv;
    }
}
