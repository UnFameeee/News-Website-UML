package com.example.newswebsite.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "category")
public class Category {
    private String id;
    private String categoryName;
}
