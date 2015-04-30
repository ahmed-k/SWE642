package survey.processor;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import survey.domain.DataBean;
import survey.util.SurveyStringUtils;

/**
 * Created by Ahmed Alabdullah on 3/31/15.
 */
public class DataProcessor {

    public static DataBean produceDataBean(String _raffle) {
        String[] raffle = SurveyStringUtils.convertStringToStringArray(_raffle);
        double[] vals = new double[raffle.length];
        for (int i = 0 ; i < raffle.length; i++) {
            try {
                vals[i] = Double.valueOf(raffle[i]);
            }
            catch(NumberFormatException nex) {
                System.out.println("Invalid argument given to Data processor, check raffle values");
                nex.printStackTrace();
            }
        }
        double mean=  new Mean().evaluate(vals);
        double stdDev = new StandardDeviation().evaluate(vals);
        return new DataBean(mean, stdDev);
    }
}
