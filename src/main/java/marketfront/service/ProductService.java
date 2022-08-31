package marketfront.service;


import lombok.RequiredArgsConstructor;
import marketfront.entity.Category;
import marketfront.entity.Manufacturer;
import marketfront.entity.Product;
import marketfront.mapper.product.ProductMapper;
import marketfront.model.ProductDto;
import marketfront.repository.ProductRepository;
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
    private final ProductMapper productMapper;


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
    public void create(ProductDto dto) {
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
    public Page<Product> findByCategory(int pageIndex, int pageSize, String title){
        Optional<Category> category = categoryService.findByTitle(title);
        return productRepository.findAllByCategory_Id(category.get().getId(), PageRequest.of(pageIndex, pageSize));
    }

    @Transactional
    public List<Product> findByManufacturer(String title){
        Optional<Manufacturer> manufacturer = manufacturerService.findByTitle(title);
         return productRepository.findAllByManufacturer_Id(manufacturer.get().getId());
    }
//    public List<ProductDto> getProductDtoWithStock(List<ProductDto> productDto){
//        List<ProductDto> dto = new ArrayList<>();
//        for (ProductDto p:dto) {
//            Optional<Stock> stock
//        }
//    }
}
