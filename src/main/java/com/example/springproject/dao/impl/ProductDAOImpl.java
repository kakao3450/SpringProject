package com.example.springproject.dao.impl;

import com.example.springproject.dao.ProductDao;
import com.example.springproject.entity.Product;
import com.example.springproject.repository.ProductRepository;
import com.example.springproject.repository.QProductRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.springproject.entity.QProduct.product;

@Component
public class ProductDAOImpl implements ProductDao {

    private final ProductRepository productRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final QProductRepository qProductRepository;
    @Autowired
    public ProductDAOImpl(ProductRepository productRepository,JPAQueryFactory jpaQueryFactory, QProductRepository qProductRepository){
        this.productRepository = productRepository;
        this.jpaQueryFactory = jpaQueryFactory;
        this.qProductRepository = qProductRepository;
    }
    @Override
    public Product insertProduct(Product product) {
        Product saveProduct = productRepository.save(product);
        return saveProduct;
    }

    @Override
    public Product selectProduct(Long number) {
        Predicate predicate = product.number.eq(number);
        Optional<Product> selectProduct = qProductRepository.findOne(predicate);
        return selectProduct.isPresent() ? selectProduct.get() : null;
    }

    @Override
    public List<Product> selectProductName(String name) {
        List<Product> selectProduct = productRepository.findByName(name, Sort.by(Sort.Order.asc("price")));
        return selectProduct;
    }

    @Override
    public Product selectProductByNameAndPrice(String name, int price) {
        return null;
    }

    @Override
    public Long countByPrice(int price) {
        return productRepository.countByPrice(price);
    }

    @Override
    public boolean existsByNumber(Long number) {
        return false;
    }

    @Override
    public List<Product> listProductByName(String name) {
        return productRepository.findByNameOrderByPriceAsc(name);
    }

    @Override
    public List<Product> listProductByPrice() {
        return productRepository.findAllByOrderByPriceDesc();
    }

    @Override
    public List<Product> listProductByOrder() {
        return null;
    }

    @Override
    public List<Product> allProduct() {
        return productRepository.findAllBy();
    }

    @Override
    public Product updateProductName(Long number, String name, int price, int stock) throws Exception {
        Optional<Product> selectProduct = productRepository.findById(number);
        Product updateProduct;
        if(selectProduct.isPresent()){
            //update
            Product product = selectProduct.get();
            product.setName(name);
            product.setPrice(price);
            product.setStock(stock);
            product.setUpdatedAt(LocalDateTime.now());
            updateProduct = productRepository.save(product);
        }else{
            throw new Exception();
        }
        return updateProduct;
    }

    @Override
    public Product updateProductStock(Long number, String name, int stock) throws Exception {
        Optional<Product> selectProduct = productRepository.findById(number);
        Product updateProduct;
        if(selectProduct.isPresent()){
            //update
            Product product = selectProduct.get();
            product.setStock(stock-1);
            product.setUpdatedAt(LocalDateTime.now());
            updateProduct = productRepository.save(product);
        }else{
            throw new Exception();
        }
        return updateProduct;
    }

    @Override
    public List<Product> listProductByNumber(Long number) {
        List<Product> selectProduct = productRepository.findByNumber(number);
        return selectProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectProduct = productRepository.findById(number);
        if(selectProduct.isPresent()) {
            Product product = selectProduct.get();
            productRepository.delete(product);
        } else throw new Exception();
    }
}