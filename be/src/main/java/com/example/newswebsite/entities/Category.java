package com.example.newswebsite.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "category")
public class Category {
    @Id
    private String id;

    @Indexed(unique = true)
    private String categoryName;

    private Boolean isActive;
}
