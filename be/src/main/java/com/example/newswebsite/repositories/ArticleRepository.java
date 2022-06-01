package com.example.newswebsite.repositories;

import com.example.newswebsite.entities.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {
    @Query("{}")
    List<Article> findAllArticle();

    @Query("{_id: '?0'}")
    Optional<Article> findArticleById(String id);

    @Query("{title: '?0'}")
    Optional<Article> findArticleByTitle(String title);

    @Query("{ 'status':  '?0' },{ 'categoryId':  '?0' }")
    List<Article> findArticlesByCategoryAndStatus(String status,String categoryName);

    @Query("{ 'status':  '?0' }")
    List<Article> findArticlesByStatus(String status);


    @Query(" { 'status':  '?0'  }, { 'censorId': '?0'} ")
    List<Article> findArticlesByStatusAndCensorId(String status,String censorId);

    @Query(" { 'status':  '?0'  }, { 'userId': '?0'} ")
    List<Article> findArticlesByStatusAndUserId(String status,String userId);

    @Query(" { $text: {$search: '?0'}} ")
    List<Article> searchArticleByTitle(String search);



}
