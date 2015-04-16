package survey.util;

/**
 * Created by alabdullahwi on 4/15/2015.
 */
public class SurveyStringUtils {
    public static String[] convertStringToStringArray(String str) {
        if (str == null) {
            return null;
        }
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
}
