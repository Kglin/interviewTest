package net.interview.test.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @author Kglin
 * @version 1.0
 * @since 2024/6/1
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxRate {

    private String location;
    private double tax;
    private List<String> exempt;

}
