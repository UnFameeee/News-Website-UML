package com.example.newswebsite.services.article;

import com.example.newswebsite.dtos.ArticleDto;
import com.example.newswebsite.entities.Article;
import com.example.newswebsite.exceptions.DuplicatedValueException;
import com.example.newswebsite.exceptions.NonexistentValueException;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    List<Article> getAllArticle() throws Exception;
    List<Article> getAllArticleByCateId(String cate) throws Exception;
    Article getOneArticle(String Id) throws NonexistentValueException;
    Article createArticle(ArticleDto articleDto) throws DuplicatedValueException;

    Article changeStatusArticleChecked(Map<String, String> data) throws NonexistentValueException;
    Article changeStatusArticleNotChecked(Map<String, String> data) throws NonexistentValueException;

    List<Article> getArticlesWaiting() throws Exception;
    List<Article> getArticlesByCatRelated(String cate) throws Exception;
    List<Article> getArticlesWaitingByUserId(String userId) throws Exception;

    List<Article> getArticlesCheckedByUserId(String userId) throws Exception;
    List<Article> getArticlesNotCheckedByUserId(String userId) throws Exception;
    List<Article> getArticlesChecked(ArticleDto articleDto) throws Exception;
    List<Article> searchArticlesByTitle(String title) throws Exception;

    //noi bat
    List<Article> getArticlesTrending() throws Exception;
}
