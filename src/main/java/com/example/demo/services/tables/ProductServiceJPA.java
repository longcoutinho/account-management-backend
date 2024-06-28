package com.example.demo.services.tables;

import com.example.demo.controllers.product.PaymentDTO;
import com.example.demo.controllers.product.ProductDTO;
import com.example.demo.dtos.CardDTO;
import com.example.demo.repositories.tables.CardRepositoryJPA;
import com.example.demo.repositories.tables.ProductCategoryEntity;
import com.example.demo.repositories.tables.ProductFeeEntity;
import com.example.demo.repositories.tables.ProductRepositoryJPA;
import com.example.demo.repositories.tables.entities.CardEntity;
import com.example.demo.repositories.tables.entities.FileEntity;
import com.example.demo.repositories.tables.entities.ProductDetailEntity;
import com.example.demo.repositories.tables.entities.ProductEntity;
import com.example.demo.services.ProductCategoryServiceJPA;
import com.example.demo.services.ProductDetailServiceJPA;
import com.example.demo.services.ProductFeeServiceJPA;
import com.example.demo.utils.enums.ErrorApp;
import com.example.demo.utils.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ProductServiceJPA {
    @Autowired
    ProductRepositoryJPA productRepositoryJPA;

    @Autowired
    ProductDetailServiceJPA productDetailServiceJPA;

    @Autowired
    ProductCategoryServiceJPA productCategoryServiceJPA;

    @Autowired
    ProductFeeServiceJPA productFeeServiceJPA;

    @Autowired
    FileServiceJPA fileServiceJPA;

    public List<ProductResponseDTO> getAll() {
        List<ProductResponseDTO> res = new LinkedList<>();
        List<ProductEntity> productEntityList = productRepositoryJPA.getAll();
        for(ProductEntity product: productEntityList) {
            String url = "";
            if (product.getImageId() !=null) {
                FileEntity file = fileServiceJPA.findById(product.getImageId());
                if (file != null) url = file.getUrl();
            }
            res.add(new ProductResponseDTO(product.getName(), product.getId(), url, product.getTypeId()));
        }
        return res;
    }

    public Object create(ProductDTO request) throws IOException {
        /** CREATE PRODUCT **/
        ProductEntity product = new ProductEntity();
        product.setName(request.getName());
        product.setCreate_date(new Date(System.currentTimeMillis()));
        product.setCreate_user(request.getUsername());
        product.setTypeId(request.getTypeId());
        product = productRepositoryJPA.save(product);

        /** CREATE PRODUCT DETAIL **/
        ProductDetailEntity productDetail = new ProductDetailEntity();
        productDetail.setProductId(product.getId());
        productDetail.setDescription(request.getDescription());
        List<String> fileIdList = new LinkedList<>();

        String fileIdListStr = String.join(
                ", ", fileIdList);
        productDetail.setImageList(fileIdListStr);
        productDetailServiceJPA.save(productDetail);

        /** CREATE PRODUCT CATEGORIES **/
        for(String category: request.getCategory()) {
            ProductCategoryEntity productCategory = new ProductCategoryEntity();
            productCategory.setName(category);
            productCategory.setProductId(product.getId());
            productCategoryServiceJPA.save(productCategory);
        }

        /** CREATE PRODUCT FEE **/
        for(PaymentDTO paymentDTO: request.getPrice()) {
            ProductFeeEntity productFee = new ProductFeeEntity();
            productFee.setPaymentCode(paymentDTO.getPaymentCode());
            productFee.setPrice(paymentDTO.getPrice());
            productFee.setProductId(product.getId());
            productFeeServiceJPA.save(productFee);
        }

        return product;
    }

    public ProductDetailResponseDTO getDetail(Long productId) {
        ProductDetailResponseDTO res = new ProductDetailResponseDTO();
        ProductDetailEntity productDetail = productDetailServiceJPA.findByProductId(productId);
        res.setDescription(productDetail.getDescription());
        if (!productDetail.getImageList().equals("")) {
            List<String> fileIdList = Arrays.asList(productDetail.getImageList().split(", "));
            List<String> imageList = new LinkedList<>();
            for(String fileId: fileIdList) {
                FileEntity fileEntity = fileServiceJPA.findById(Long.valueOf(fileId));
                imageList.add(fileEntity.getUrl());
            }
            res.setImagePathList(imageList);
        }
        List<ProductCategoryEntity> categoryEntityList = productCategoryServiceJPA.findByProductId(productId);
        res.setCategoryList(categoryEntityList);
        List<ProductFeeEntity> feeList = productFeeServiceJPA.findByProductId(productId);
        res.setFeeList(feeList);
        ProductEntity productEntity = productRepositoryJPA.findById(productDetail.getProductId()).get();
        res.setTypeId(productEntity.getTypeId());
        res.setName(productEntity.getName());
        return res;
    }

    public void saveImage(List<MultipartFile> imagesList, Long productId) throws IOException {
        List<String> imageDetailId = new LinkedList<>();
        for(int ind = 0; ind < imagesList.size(); ind++) {
            if (ind == 0) {
                FileEntity fileEntity = fileServiceJPA.saveFile(imagesList.get(ind));
                ProductEntity productEntity = productRepositoryJPA.findById(productId).get();
                productEntity.setImageId(fileEntity.getId());
                productRepositoryJPA.save(productEntity);
            }
            else {
                FileEntity fileEntity = fileServiceJPA.saveFile(imagesList.get(ind));
                imageDetailId.add(fileEntity.getId().toString());
            }
        }
        String myString = String.join(", ", imageDetailId);
        ProductDetailEntity productDetailEntity = productDetailServiceJPA.findByProductId(productId);
        productDetailEntity.setImageList(myString);
        productDetailServiceJPA.save(productDetailEntity);
    }

    public void removeById(Long productId) {
        productRepositoryJPA.deleteById(productId);
    }
}
