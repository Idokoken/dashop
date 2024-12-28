package ndgroups.DAShop.Interface;

import ndgroups.DAShop.model.Category;

import java.util.List;

public interface ICategoryService {
    Category addCategory(Category category);
    Category getCategoryById(Integer id);
    Category getCategoryByName(String name);
    Category updateCategory(Category category, Integer id);
    void deleteCategory(Integer id);
    List<Category> getAllCategories();

}
