package net.interview.test.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import net.interview.test.pojo.TaxRate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kglin
 * @version 1.0
 * @since 2024/6/1
 */
@Getter
@Service
public class TaxRateUtils {
    private Map<String, TaxRate> taxRates;

    @PostConstruct
    public void init() {
        String filePath = "support-files/files/taxRates.json";
        List<TaxRate> taxRateList = readFile(filePath);
        taxRates = new HashMap<>();
        for(TaxRate taxRate : taxRateList){
            taxRates.put(taxRate.getLocation(), taxRate);
        }
    }

    private List<TaxRate> readFile(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        List<TaxRate> taxRates;
        try {
            taxRates = mapper.readValue(Paths.get(filePath).toFile(), new TypeReference<List<TaxRate>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return taxRates;
    }

}
