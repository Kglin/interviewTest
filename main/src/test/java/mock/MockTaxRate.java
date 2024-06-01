package mock;

import net.interview.test.pojo.TaxRate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MockTaxRate {

    public static Map<String, TaxRate> getTaxRate() {
        TaxRate NYTaxRate = new TaxRate();
        NYTaxRate.setLocation("NY");
        NYTaxRate.setTax(0.08875);
        NYTaxRate.setExempt(Arrays.asList("food", "clothing"));

        TaxRate CATaxRate = new TaxRate();
        CATaxRate.setLocation("CA");
        CATaxRate.setTax(0.0975);
        CATaxRate.setExempt(Collections.singletonList("food"));

        Map<String, TaxRate> taxRateMap = new HashMap<>();
        taxRateMap.put("NY", NYTaxRate);
        taxRateMap.put("CA", CATaxRate);

        return taxRateMap;
    }

}
