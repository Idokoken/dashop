package ndgroups.DAShop.controller;

import ndgroups.DAShop.service.Interface.ICategoryService;
import ndgroups.DAShop.service.Interface.IProductService;
import ndgroups.DAShop.dto.ProductDto;
import ndgroups.DAShop.exception.ResourceNotFoundException;
import ndgroups.DAShop.model.Product;
import ndgroups.DAShop.request.AddProductRequest;
import ndgroups.DAShop.request.UpdateProductRequest;
import ndgroups.DAShop.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {

    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse> addProducts(@RequestBody AddProductRequest product) {
        try {
            Product newProduct =  productService.addProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("product added", newProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOneProducts(@PathVariable Integer id) {
        try {
            Product product = productService.getProductById(id);
            ProductDto productDto  = productService.convertToDto(product);
            return ResponseEntity.ok(new ApiResponse("success", productDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse>updateProduct(@PathVariable Integer id,
                                                    @RequestBody UpdateProductRequest product){
        try {
            Product updatedProduct  = productService.updateProduct(product, id);
            ProductDto productDto  = productService.convertToDto(updatedProduct);
            return ResponseEntity.ok(new ApiResponse("update successful", productDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok(new ApiResponse("product deleted", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto>convertedProduct =  productService.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("success",convertedProduct));
    }

    @GetMapping("/product/by-category/{category}")
    public ResponseEntity<ApiResponse> getProductsByCategory(@PathVariable String category) {
        try {
            List<Product> products = productService.getProductsByCategory(category);
            if (products.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("No Product found", null));
            }
            List<ProductDto>convertedProduct =  productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("success", convertedProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/product/by-brand")
    public ResponseEntity<ApiResponse> getProductsByBrand(@RequestParam String brand) {

        try {
            List<Product> products = productService.getProductsByBrand(brand);
            if (products.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("No Product found", null));
            }
            List<ProductDto>convertedProduct =  productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("success", convertedProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/product/by-category-and-brand")
    public ResponseEntity<ApiResponse> getProductsByCategoryAndBrand(@RequestParam String category,
                                                                     @RequestParam  String brand) {
        try {
            List<Product> products = productService.getProductsByCategoryAndBrand(category, brand);
            if (products.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("No Product found", null));
            }
            List<ProductDto>convertedProduct =  productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("success", convertedProduct));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/product/by-name/{name}")
    public ResponseEntity<ApiResponse> getProductsByName(@PathVariable String name) {
        try {
            List<Product> products = productService.getProductsByName(name);
            if (products.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("No Product found", null));
            }
            List<ProductDto>convertedProduct =  productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("success", convertedProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/product/by-brand-and-name")
    public ResponseEntity<ApiResponse> getProductsByBrandAndName(@RequestParam String brand,
                                                                 @RequestParam  String name) {
        try {
            List<Product> products = productService.getProductsByBrandAndName(brand, name);
            if (products.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("No Product found", null));
            }
            List<ProductDto>convertedProduct =  productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("success", convertedProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/product/by-count")
    public ResponseEntity<ApiResponse> CountProductByBrandAndName(@RequestParam String brand,
                                                                  @RequestParam  String name) {
        try {
            Long productCount = productService.CountProductsByBrandAndName(brand, name);
            return ResponseEntity.ok(new ApiResponse("success", productCount));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }





}
