package com.example.newswebsite.dtos;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class CategoryDto {
    private String id;
    private String categoryName;
}
