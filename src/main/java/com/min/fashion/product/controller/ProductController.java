package com.min.fashion.product.controller;

import com.min.fashion.product.dto.ProductDTO;
import com.min.fashion.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new ProductDTO());
        return "products/add-product";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductDTO product, @RequestParam("image") MultipartFile imageFile, Model model) {
        // 이미지 파일 처리 로직
        try {
            if (!imageFile.isEmpty()) {
                String imageUrl = storeImage(imageFile);
                product.setImageUrl(imageUrl); // 이미지 URL을 ProductDTO 객체에 설정
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        productService.addProduct(product);
        return "redirect:/products";
    }

    private String storeImage(MultipartFile imageFile) throws IOException {
        if (imageFile.isEmpty()) {
            return null; // 또는 적절한 예외 처리
        }

        // 이미지 저장 디렉토리 경로 설정 (예시)
        String uploadDir = "src/main/resources/static/images";

        // 파일 이름 생성 (예: 현재 시간을 기반으로 한 이름)
        String fileName = System.currentTimeMillis() + "-" + imageFile.getOriginalFilename();

        // 파일 저장 경로
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 파일 저장
        try (InputStream inputStream = imageFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString(); // 경로 반환
        } catch (IOException ioe) {
            throw new IOException("이미지 저장에 실패했습니다: " + fileName, ioe);
        }
    }
}

