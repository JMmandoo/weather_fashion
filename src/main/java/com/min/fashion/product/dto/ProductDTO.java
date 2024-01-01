package com.min.fashion.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {
    private int productId;

    @NotBlank(message = "이름은 비워둘 수 없습니다.")
    private String name;

    @NotBlank(message = "설명은 비워둘 수 없습니다.")
    private String description;

    @NotNull(message = "가격을 작성해주세요.")
    @Min(value = 0, message = "가격은 0보다 작을수 없습니다")
    private double price;
    // 이미지 파일을 위한 필드 추가
    private String imageUrl;
}
