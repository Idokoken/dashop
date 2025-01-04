package ndgroups.DAShop.service.impl;

import ndgroups.DAShop.service.Interface.ICategoryService;
import ndgroups.DAShop.exception.AlreadyExistException;
import ndgroups.DAShop.exception.ResourceNotFoundException;
import ndgroups.DAShop.model.Category;
import ndgroups.DAShop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category).filter(cat -> !categoryRepository.existsByName(cat.getName()))
                .map(categoryRepository::save)
                .orElseThrow(() -> new AlreadyExistException(category.getName() + " category already exist"));
    }
    @Override
    public Category getCategoryById(Integer id){
        return categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }


    @Override
    public Category updateCategory( Category category, Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setName(category.getName());
            return categoryRepository.save(existingCategory);
        }
        throw new ResourceNotFoundException("Category not found");
    }

    @Override
    public void deleteCategory(Integer id){
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
