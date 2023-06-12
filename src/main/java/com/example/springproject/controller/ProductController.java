package com.example.springproject.controller;

import com.example.springproject.dto.ChangeProductDto;
import com.example.springproject.dto.ProductResponseDto;
import com.example.springproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @PutMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductResponseDto> changeProductName(@RequestParam Long number, @RequestParam String name,
                                                                @RequestParam int price, @RequestParam int stock) throws Exception {
        ProductResponseDto productResponseDto = productService.changeProductName(number, name, price, stock);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestParam String name, @RequestParam int price, @RequestParam int stock){
        ProductResponseDto productResponseDto = productService.saveProduct(name, price,stock);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteProduct(Long number) throws Exception {
        productService.deleteProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었음");
    }
    //리스트로 수정
    @GetMapping("/list")
    public ResponseEntity<List<ProductResponseDto>> allProduct(){
        List<ProductResponseDto> productResponseDto = productService.allProduct();
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
    //리스트로 수정
    @GetMapping("/listOrderByPrice")
    public ResponseEntity<List<ProductResponseDto>>listOrderByPrice(){
        List<ProductResponseDto> productResponseDto = productService.listOrderByPrice();
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @GetMapping("/byName")
    public ResponseEntity<List<ProductResponseDto>> listProductByName(String name) {
        List<ProductResponseDto> productResponseDto = productService.listProductByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponseDto>> listProductByNumber(long number) {
        List<ProductResponseDto> productResponseDto = productService.listProductByNumber(number);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

}
