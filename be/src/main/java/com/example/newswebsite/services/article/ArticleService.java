package com.example.newswebsite.services.article;

import com.example.newswebsite.dtos.ArticleDto;
import com.example.newswebsite.entities.Article;
import com.example.newswebsite.exceptions.DuplicatedValueException;
import com.example.newswebsite.exceptions.NonexistentValueException;

import java.util.List;

public interface ArticleService {
    List<Article> getAllArticle() throws Exception;
    Article getOneArticle(String Id) throws NonexistentValueException;
    Article creatingArticle(ArticleDto articleDto) throws DuplicatedValueException;
    Article changeStatusArticleChecked(ArticleDto articleDto) throws NonexistentValueException;
    Article changeStatusArticleNotChecked(ArticleDto articleDto) throws NonexistentValueException;
    List<Article> getArticlesWaiting() throws Exception;
    List<Article> getArticlesCheked(ArticleDto articleDto) throws Exception;
    List<Article> searchArticlesByTitle(String title) throws Exception;
}
