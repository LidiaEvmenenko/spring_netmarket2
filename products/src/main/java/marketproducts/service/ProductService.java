package marketproducts.service;


import lombok.RequiredArgsConstructor;
import marketapi.ApiProductsView;
import marketproducts.entity.Category;
import marketproducts.entity.Manufacturer;
import marketproducts.entity.Product;
import marketproducts.mapper.product.ProductMapper;
import marketproducts.model.ProductDto;
import marketproducts.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;
    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> findByTitle(String title) {
        return productRepository.findByTitle(title);
    }

    @Transactional
    public void create(ApiProductsView dto) {
        byte[] bytes = new byte[1];
        Optional<Category> category = categoryService.findByTitle(dto.getCategoryTitle());
        Optional<Manufacturer> manufacturer = manufacturerService.findByTitle(dto.getManufacturerTitle());
        productRepository.insertProduct(dto.getDescription(),
                dto.getExpiration_date(), bytes,dto.getPrice(),dto.getTitle(),
                dto.getWeight(),category.get().getId(),manufacturer.get().getId());
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> findAll(int pageIndex, int pageSize){

        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    @Transactional
    public List<Product> findProductsByCategory(String title){
        Optional<Category> category = categoryService.findByTitle(title);
        if (!category.isEmpty()) {
            return productRepository.findAllByCategory_Id(category.get().getId());
        }
        return null;
    }

    @Transactional
    public List<Product> findByManufacturer(String title){
        Optional<Manufacturer> manufacturer = manufacturerService.findByTitle(title);
         return productRepository.findAllByManufacturer_Id(manufacturer.get().getId());
    }

}















