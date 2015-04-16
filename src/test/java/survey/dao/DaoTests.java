package survey.dao;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import survey.domain.StudentBean;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static survey.util.SurveyStringUtils.convertStringArrayToString;
import static survey.util.SurveyStringUtils.convertStringToStringArray;

/**
 * Created by alabdullahwi on 4/8/2015.
 */
public class DaoTests {

    @Rule
    public ExpectedException exception = ExpectedException.none();



    @Test
    public void itShouldConvertStringArrayToAString() {

        String[] arr = { "hi", "my", "name", "is" ,"jude"};
        String str = convertStringArrayToString(arr);
        Assert.assertEquals("hi,my,name,is,jude", str);

    }

    @Test
    public void itShouldConvertStringToStringArray() {

        String str = "hi,my,name,is,jude";
        String [] arr = convertStringToStringArray(str);
        assertNotNull(arr);
        Assert.assertEquals(arr.length, 5);
        Assert.assertEquals(arr[0], "hi");
        Assert.assertEquals(arr[1], "my");
        Assert.assertEquals(arr[2], "name");
        Assert.assertEquals(arr[3], "is");
        Assert.assertEquals(arr[4], "jude");
    }

    @Test
    public void selectStatementTest() {

        StudentBean test =StudentDAO.getStudentBean("A123");
        assertNotNull(test);

    }

    @Test
    public void itShouldInsertARecordAndRetrieveItInTheDatabase() throws SQLException {
        //restart database
        StudentDAO.delete("FAKE_ID");

        StudentBean record = createTestStudentBean("FAKE_ID");
        StudentDAO.saveStudentBean(record);
        StudentBean recordBack = StudentDAO.getStudentBean("FAKE_ID");
        assertNotNull(recordBack);
        //they should be identical
        //assertTrue(reflectionEquals(recordBack, record));

    }

    @Test
    public void itShouldProvideNullForANonExistentRecord() {

        StudentBean notFound = StudentDAO.getStudentBean("NO SUCH STUDENT");
        Assert.assertNull(notFound);


    }

    @Test
    public void itShouldBeAbleToDeleteRecords() throws SQLException{

        //create record and save it
        StudentBean toDelete = createTestStudentBean("TO_DELETE");
        StudentDAO.saveStudentBean(toDelete);

        //retrieve it back to make sure it was saved
        StudentBean fromDB = StudentDAO.getStudentBean("TO_DELETE");
        assertNotNull(fromDB);
        Assert.assertNotNull(toDelete);
        //assertTrue(reflectionEquals(toDelete, fromDB));
        //delete it
        StudentDAO.delete("TO_DELETE");
        //retrieve it again, should be null now
        StudentBean fromDBAgain = StudentDAO.getStudentBean("TO_DELETE");
        //should be gone and null now
        Assert.assertNull(fromDBAgain);





    }


    @Test
    public void itShouldNotPermitDuplicateStudentIDs() throws SQLException {
        StudentDAO.delete("FAKE_ID");
        StudentBean record = this.createTestStudentBean("FAKE_ID");
        //save first time, should be okay
        StudentDAO.saveStudentBean(record);
        //save again, should not be okay and should throw a SQLIntegrityConstraintException
        record.setLastName("Different");
        StudentDAO.saveStudentBean(record);
        StudentBean record2 = StudentDAO.getStudentBean("FAKE_ID");
        assertNotNull(record2);
        //it's equal to the first entry and not the second changed one which was never saved because
        //it violated integrity constraints
        Assert.assertEquals(record2.getLastName(), "Student");
    }

    @Test
    public void itShouldLoadAllStudentIDs() throws SQLException {

        List<String> ids = StudentDAO.getAllStudentIDs();
        assertNotNull(ids);
        for (String id :ids) {
            System.out.println(id);
        }
    }


    //utility method
    private StudentBean createTestStudentBean(String id) {
        StudentBean record = new StudentBean();
        record.setStudentID(id);
        record.setFirstName("Test");
        record.setLastName("Student");
        record.setStreet("Farazdaq");
        record.setCity("Vienna");
        record.setZip("22181");
        record.setTelephone("4259923");
        record.setEmail("ahmed@khalid.net");
        record.setUrl("www.ahmedkhalid.net");
        record.setReferralSource("internet");
        String[] campus = { "thing1", "thing2", "thing3"};
        record.setCampus(campus);
        record.setGraduationMonth("January");
        record.setGraduationYear("2015");
        record.setRecommendationLikelihood("Unlikely");
        record.setAdditionalComments("No Comments");
        return record;
    }

}
