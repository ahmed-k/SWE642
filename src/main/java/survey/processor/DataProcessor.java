package survey.processor;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import survey.domain.DataBean;

/**
 * Created by Ahmed Alabdullah on 3/31/15.
 */
public class DataProcessor {

    public static DataBean produceDataBean(String [] raffle) {
        double sum = 0;
        double[] vals = new double[raffle.length];
        for (int i = 0 ; i < raffle.length; i++) {
            vals[i] = Double.valueOf(raffle[i]);
        }
        double mean=  new Mean().evaluate(vals);
        double stdDev = new StandardDeviation().evaluate(vals);
        return new DataBean(mean, stdDev);

    }
}
