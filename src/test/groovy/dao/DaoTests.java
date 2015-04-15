package dao;

import org.junit.Assert;
import org.junit.Test;
import survey.dao.StudentDAO;
import survey.domain.StudentBean;

import static junit.framework.Assert.assertTrue;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by alabdullahwi on 4/8/2015.
 */
public class DaoTests {




    @Test
    public void itShouldConvertStringArrayToAString() {

        String[] arr = { "hi", "my", "name", "is" ,"jude"};
        String str = StudentDAO.convertStringArrayToString(arr);
        Assert.assertEquals("hi,my,name,is,jude", str);

    }

    @Test
    public void itShouldConvertStringToStringArray() {

        String str = "hi,my,name,is,jude";
        String [] arr = StudentDAO.convertStringToStringArray(str);
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
    public void itShouldInsertARecordAndRetrieveItInTheDatabase() {

        StudentBean record = new StudentBean();
        record.setStudentID("FAKE_ID");
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

        StudentDAO.saveStudentBean(record);

        StudentBean recordBack = StudentDAO.getStudentBean("FAKE_ID");
        assertNotNull(recordBack);
        assertTrue(reflectionEquals(recordBack, record));


    }



}
