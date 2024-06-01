package net.interview.test.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import net.interview.test.pojo.CategoryInfo;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * @author Kglin
 * @version 1.0
 * @since 2024/6/1
 */
@Getter
@Service
public class CategoryUtils {
    private List<CategoryInfo> categoryList;

    @PostConstruct
    public void init() {
        String filePath = "support-files/files/category.json";
        this.categoryList = readFile(filePath);
    }

    private List<CategoryInfo> readFile(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        List<CategoryInfo> taxRates;
        try {
            taxRates = mapper.readValue(Paths.get(filePath).toFile(), new TypeReference<List<CategoryInfo>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return taxRates;
    }

}
