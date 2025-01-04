package ndgroups.DAShop.service.impl;

import ndgroups.DAShop.service.Interface.IProductService;
import ndgroups.DAShop.dto.ImageDto;
import ndgroups.DAShop.dto.ProductDto;
import ndgroups.DAShop.exception.ResourceNotFoundException;
import ndgroups.DAShop.model.Category;
import ndgroups.DAShop.model.Image;
import ndgroups.DAShop.model.Product;
import ndgroups.DAShop.repository.CategoryRepository;
import ndgroups.DAShop.repository.ImageRepository;
import ndgroups.DAShop.repository.ProductRepository;
import ndgroups.DAShop.request.AddProductRequest;
import ndgroups.DAShop.request.UpdateProductRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
      @Autowired
      private ProductRepository productRepository;
      @Autowired
      private CategoryRepository categoryRepository;
      @Autowired
      private ImageRepository imageRepository;
      @Autowired
      private ModelMapper modelMapper;
      @Override
      public Product addProduct(AddProductRequest product){
            Category category = Optional.ofNullable(categoryRepository.findByName(product.getCategory().getName()))
                    .orElseGet(() -> {
                          Category newCategory = new Category(product.getCategory().getName());
                          return categoryRepository.save(newCategory);
                    });
            product.setCategory(category);
            return productRepository.save(createProduct(product, category));
      }
      private Product createProduct(AddProductRequest request, Category category){
            return new Product(request.getName(),
                    request.getDescription(),
                    request.getPrice(),
                    request.getStockQuantity(),
                    request.getBrand(),
                    request.getSKU(),
                    category
                    );
      }

      @Override
      public Product getProductById(Integer id){
            return productRepository.findById(id)
                    .orElseThrow(()-> new ResourceNotFoundException("Product not found"));

      }
      @Override
      public Product updateProduct(UpdateProductRequest request, Integer id){
            return productRepository.findById(id)
                    .map(existingProduct -> updateExistingProduct(existingProduct, request))
                    .map(productRepository :: save)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
      }
    private Product updateExistingProduct(Product existingProduct, UpdateProductRequest request){
                existingProduct.setName(request.getName());
                existingProduct.setDescription(request.getDescription());
                existingProduct.setPrice(request.getPrice());
                existingProduct.setStockQuantity(request.getStockQuantity());
                existingProduct.setBrand(request.getBrand());
                existingProduct.setSKU(request.getSKU());

                Category category = categoryRepository.findByName(request.getCategory().getName());
                existingProduct.setCategory(category);
                return existingProduct;

    }
      @Override
      public void deleteProduct(Integer id){
           if(!productRepository.existsById(id)){
                 throw new ResourceNotFoundException("product not found with id: " + id);
           }
           productRepository.deleteById(id);
      }
      @Override
      public List<Product> getAllProducts(){
            return productRepository.findAll();
      }
      @Override
      public List<Product> getProductsByCategory(String category) {
            return productRepository.findByCategoryName(category);
      }
      @Override
      public List<Product> getProductsByBrand(String brand) {
            return productRepository.findByBrand(brand);
      }
      @Override
      public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
            return productRepository.findByCategoryNameAndBrand(category, brand);
      }
      @Override
      public List<Product> getProductsByName(String name) {
            return productRepository.findByName(name);
      }
      @Override
      public List<Product> getProductsByBrandAndName(String brand, String name) {
            return productRepository.findByBrandAndName(brand, name);
      }
      @Override
      public Long CountProductsByBrandAndName(String brand, String name) {
            return productRepository.countProductsByBrandAndName(brand, name);
      }

      @Override
      public List<ProductDto>getConvertedProducts(List<Product> products){
            return products.stream().map(this::convertToDto).toList();
      }

      @Override
      public ProductDto convertToDto(Product product){
          ProductDto productDto = modelMapper.map(product, ProductDto.class);
           List<Image>images = imageRepository.findByProductProductId(product.getProductId());
           List<ImageDto>imageDto = images.stream().map(image -> modelMapper.map(image, ImageDto.class)).toList();
           productDto.setImages(imageDto);
           return productDto;
      }



 }

