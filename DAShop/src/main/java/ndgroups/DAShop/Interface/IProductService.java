package ndgroups.DAShop.Interface;

import ndgroups.DAShop.model.Product;
import ndgroups.DAShop.request.AddProductRequest;
import ndgroups.DAShop.request.UpdateProductRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest product);
    Product getProductById(Integer id);
    void deleteProduct(Integer id);
    Product updateProduct(UpdateProductRequest request, Integer id);
    List<Product>getAllProducts();
    List<Product>getProductsByCategory(String category);
    List<Product>getProductsByBrand(String brand);
    List<Product>getProductsByCategoryAndBrand(String category, String brand);
    List<Product>getProductsByName(String name);
    List<Product>getProductsByBrandAndName(String brand, String name);
    Long CountProductsByBrandAndName(String brand, String name);
}
