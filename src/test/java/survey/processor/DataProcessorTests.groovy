package survey.processor

import org.junit.Test
import survey.domain.DataBean

import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertTrue
/**
 * Created by alabdullahwi on 4/15/2015.
 */
class DataProcessorTests {



    @Test
    def void "it should produce a data bean provided correct input"() {

        String[] raffle  = new String[10]
        10.times {
            raffle[it] = (it + 1).toString()
        }
        DataBean dataBean = DataProcessor.produceDataBean(raffle)
        assertNotNull(dataBean)
        assertTrue((dataBean.stdDev as BigDecimal).setScale(2, java.math.RoundingMode.FLOOR) == 3.02 )
        assertTrue(dataBean.mean == 5.5)



    }
}

