package com.example.springproject.service.imple;

import com.example.springproject.dao.ProductDao;
import com.example.springproject.dto.ProductResponseDto;
import com.example.springproject.service.ProductService;
import com.example.springproject.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDAO;

    @Autowired
    public ProductServiceImpl(ProductDao productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {
        Product product = productDAO.selectProduct(number);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(product.getName());
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> getProductByName(String name) {
        List<Product> products = productDAO.selectProductName(name);
        List<ProductResponseDto> productResponseDto = products.stream().map(ProductResponseDto::new).collect(Collectors.toList());;
        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> listProductByNumber(Long number) {
        List<Product> product = productDAO.listProductByNumber(number);
        List<ProductResponseDto> productResponseDTO = product.stream().map(
                ProductResponseDto::new).collect(Collectors.toList());
        return productResponseDTO;
    }

    @Override
    public List<ProductResponseDto> allProduct() {
        List<Product> products = productDAO.allProduct();
        List<ProductResponseDto> productResponseDtoList = products.stream().map(ProductResponseDto::new).collect(Collectors.toList());;
        return productResponseDtoList;
    }

    @Override
    public ProductResponseDto saveProduct(String name, int price, int stock) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());


        Product saveProduct = productDAO.insertProduct(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(saveProduct.getName());
        productResponseDto.setNumber(saveProduct.getNumber());
        productResponseDto.setPrice(saveProduct.getPrice());
        productResponseDto.setStock(saveProduct.getStock());
        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name, int price, int stock) throws Exception {
        Product changeProduct = productDAO.updateProductName(number, name, price, stock);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(changeProduct.getName());
        productResponseDto.setPrice(changeProduct.getPrice());
        productResponseDto.setStock(changeProduct.getStock());
        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductStock(Long number, String name, int stock) throws Exception {
        Product changeProduct = productDAO.updateProductStock(number, name, stock);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(changeProduct.getNumber());
        productResponseDto.setName(changeProduct.getName());
        productResponseDto.setPrice(changeProduct.getPrice());
        productResponseDto.setStock(changeProduct.getStock());
        return productResponseDto;
    }

    @Override
    public Long countByPrice(Long price) {
        return null;
    }

    @Override
    public ProductResponseDto getProductByNameANdPrice(String name, int price) {
        Product product = productDAO.selectProductByNameAndPrice(name, price);
        return new ProductResponseDto(product);
    }

    @Override
    public boolean existsByNumber(Long number) {
        return productDAO.existsByNumber(number);
    }

    @Override
    public List<ProductResponseDto> listProductByName(String name) {
        List<Product> products = productDAO.listProductByName(name);
        List<ProductResponseDto> productResponseDtoList = products.stream().map(product -> new ProductResponseDto(product)).collect(Collectors.toList());
        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> listProductByOrder() {
        List<Product> products = productDAO.listProductByOrder();
        List<ProductResponseDto> productResponseDtoOrder = products.stream().map(product -> new ProductResponseDto(product)).collect(Collectors.toList());
        return productResponseDtoOrder;
    }

    @Override
    public List<ProductResponseDto> listOrderByPrice() {
        List<Product> products = productDAO.listProductByPrice();
        List<ProductResponseDto> productResponseDtoOrder = products.stream().map(product -> new ProductResponseDto(product)).collect(Collectors.toList());
        return productResponseDtoOrder;
    }

    @Override
    public void deleteProduct(long number) throws Exception {
        productDAO.deleteProduct(number);
    }
}
