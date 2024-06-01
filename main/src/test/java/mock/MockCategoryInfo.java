package mock;

import net.interview.test.pojo.CategoryInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockCategoryInfo {
    public static List<CategoryInfo> getCategoryInfo()
    {
        CategoryInfo clothingInfo = new CategoryInfo();
        clothingInfo.setCategory("clothing");
        clothingInfo.setObject(Arrays.asList("shirt", "pants"));
        CategoryInfo foodInfo = new CategoryInfo();
        foodInfo.setCategory("food");
        foodInfo.setObject(Arrays.asList("pasta", "potato chips", "chocolate"));
        List<CategoryInfo> categoryInfo = new ArrayList<>();
        categoryInfo.add(clothingInfo);
        categoryInfo.add(foodInfo);
        return categoryInfo;
    }
}
