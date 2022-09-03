package marketproducts.service;


import lombok.RequiredArgsConstructor;
import marketapi.ApiProductsListView;
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
import java.util.stream.Collectors;

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

//    public Page<Product> findAll(int pageIndex, int pageSize){
//
//        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
//    }

    public ApiProductsListView findAll() {
        List<Product> products = productRepository.findAll();

        ApiProductsListView listView = new ApiProductsListView();
        for (Product p :products) {
            ApiProductsView view = productMapper.mapToView(p);

            listView.addProductToList(view);
        }

        return listView;
    }

    @Transactional
    public ApiProductsListView findProductsByCategory(String title){
        Optional<Category> category = categoryService.findByTitle(title);
        if (!category.isEmpty()) {
            List<Product> products = productRepository.findByCategory_Id(category.get().getId());
            if (!products.isEmpty()) {
                ApiProductsListView view = new ApiProductsListView();
                for (Product p :products) {
                    view.addProductToList(productMapper.mapToView(p));
                }
                return view;
            }
        }
        return new ApiProductsListView();
    }

    @Transactional
    public ApiProductsListView findByManufacturer(String title){
        Optional<Manufacturer> manufacturer = manufacturerService.findByTitle(title);
        return (ApiProductsListView) productRepository.findByManufacturer_Id(manufacturer.get().getId()).stream().
                map(p -> productMapper.mapToView(p)).collect(Collectors.toList());
    }

}















