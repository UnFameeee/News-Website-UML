package com.example.newswebsite.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "article")
public class Article {
    @Id
    private String id;

    private String userId;

    private String image;

    private String title;

    private String content;

    //Chưa duyệt, Đã duyệt, Không được duyệt, Không hoạt động
    private String status;

    private String categoryId;

    private String publishedDate;

    private String updatedDate;

    private String censorId;
}
