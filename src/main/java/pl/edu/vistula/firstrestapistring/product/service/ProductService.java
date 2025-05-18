package pl.edu.vistula.firstrestapistring.product.service;


import org.springframework.stereotype.Service;

import pl.edu.vistula.firstrestapistring.product.api.request.ProductRequest;
import pl.edu.vistula.firstrestapistring.product.api.request.UpdateProductRequest;
import pl.edu.vistula.firstrestapistring.product.api.response.ProductResponse;
import pl.edu.vistula.firstrestapistring.product.domain.Product;
import pl.edu.vistula.firstrestapistring.product.respository.ProductRespository;
import pl.edu.vistula.firstrestapistring.product.support.ProductExceptionSupplier;
import pl.edu.vistula.firstrestapistring.product.support.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRespository productRespository;
    private final ProductMapper productMapper;

    public ProductService(ProductRespository productRespository, ProductMapper productMapper){
        this.productRespository = productRespository;
        this.productMapper = productMapper;
    }
    public ProductResponse create(ProductRequest productRequest){
        Product product = productRespository.save(productMapper.toProduct(productRequest));
        return productMapper.toProductResponse(product);
    }

    public ProductResponse find(Long id){
        Product product = productRespository.findById(id).orElseThrow(ProductExceptionSupplier.productNotFound(id));
        return productMapper.toProductResponse(product);
    }

    public ProductResponse update(Long id, UpdateProductRequest updateProductRequest){
        Product product = productRespository.findById(id).orElseThrow(ProductExceptionSupplier.productNotFound(id));
        productRespository.save(productMapper.toProduct(product, updateProductRequest));
        return productMapper.toProductResponse(product);
    }

    public List<ProductResponse> findAll(){
        return productRespository.findAll().stream().map(productMapper::toProductResponse).collect(Collectors.toList());
    }

    public void delete (Long id){
        Product product = productRespository.findById(id).orElseThrow(ProductExceptionSupplier.productNotFound(id));
        productRespository.deleteById(product.getId());
    }


}
