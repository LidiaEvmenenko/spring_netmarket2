package marketfront.service;


import lombok.RequiredArgsConstructor;
import marketfront.entity.Category;
import marketfront.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    @Transactional
    public void create(String title) {
//        Category category = new Category();
//        category.setTitle(title);
        categoryRepository.insertCategory(title);
    }

}
