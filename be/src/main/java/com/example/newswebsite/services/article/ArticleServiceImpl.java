package com.example.newswebsite.services.article;

import com.example.newswebsite.dtos.ArticleDto;
import com.example.newswebsite.entities.Article;
import com.example.newswebsite.entities.Category;
import com.example.newswebsite.exceptions.DuplicatedValueException;
import com.example.newswebsite.exceptions.NonexistentValueException;
import com.example.newswebsite.repositories.ArticleRepository;
import com.example.newswebsite.repositories.CategoryRepository;
import com.example.newswebsite.utils.GetDateFormatedSingleton;
import com.example.newswebsite.utils.ModelMapperSingleton;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService{
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository, CategoryRepository categoryRepository){
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Article> getAllArticle() throws Exception {
        try{
            return articleRepository.findAllArticle();
        }catch(Exception ex){
            throw new Exception("System error, detail: " + ex);
        }
    }

    @Override
    public List<Article> getAllArticleByCateId(String cate) throws Exception {
        try{
            Optional<Category> category = categoryRepository.findCategoryByCategoryNameAndIsActive(cate, true);
            return articleRepository.findArticlesByCategory(category.get().getId());
        }catch(Exception ex){
            throw new Exception("System error, detail: " + ex);
        }
    }

    @Override
    public Article getOneArticle(String Id) throws NonexistentValueException {
        Optional<Article> article = articleRepository.findArticleById(Id);
        if(article.isEmpty()){
            throw new NonexistentValueException("Article doesn't exist !!!");
        }
        return article.get();
    }

    @Override
    public Article createArticle(ArticleDto articleDto) throws DuplicatedValueException {
        Article article = ModelMapperSingleton.getInstance().modelMapper().map(articleDto, Article.class);
        if(articleRepository.findArticleByTitle(article.getTitle()).isPresent()){
            throw new DuplicatedValueException("This title has been used before !!!");
        }
        article.setPublishedDate(GetDateFormatedSingleton.getInstance().getDate("EEE, dd/MM/yyyy"));
        article.setUpdatedDate(GetDateFormatedSingleton.getInstance().getDate("EEE, dd/MM/yyyy"));
        articleRepository.save(article);
        return article;
    }

    @Override
    public Article changeStatusArticleChecked(ArticleDto articleDto) throws NonexistentValueException {
        Optional<Article> article = articleRepository.findArticleById(articleDto.getId());
        if(article.isEmpty()){
            throw new NonexistentValueException("Article doesn't exist !!!");
        }
        else {
            article.get().setStatus("Đã duyệt");
//            article.get().setCensorId(articleDto.getCensorId());
            articleRepository.save(article.get());
        }
        return article.get();
    }

    @Override
    public Article changeStatusArticleNotChecked(ArticleDto articleDto) throws NonexistentValueException {
        Optional<Article> article = articleRepository.findArticleById(articleDto.getId());
        if(article.isEmpty()){
            throw new NonexistentValueException("Article doesn't exist !!!");
        }
        else {
            article.get().setStatus("Not checked");
            article.get().setCensorId(articleDto.getCensorId());
            articleRepository.save(article.get());

        }
        return article.get();
    }

    @Override
    public List<Article> getArticlesWaiting() throws Exception {

        try{
            return articleRepository.findArticlesByStatus("Chờ duyệt");
        }catch(Exception ex){
            throw new Exception("System error, detail: " + ex);
        }
    }
    // lấy những bài viết admin đã duyệt của chính admin đó
    @Override
    public List<Article> getArticlesChecked(ArticleDto articleDto) throws Exception {

        try{
            return articleRepository.findArticlesByStatusAndCensorId("checked",articleDto.getCensorId());
        }catch(Exception ex){
            throw new Exception("System error, detail: " + ex);
        }
    }

    @Override
    public List<Article> searchArticlesByTitle(String title) throws Exception {
        try{
            return articleRepository.searchArticleByTitle(title);
        }catch(Exception ex){
            throw new Exception("System error, detail: " + ex);
        }
    }

    @Override
    public List<Article> getArticlesByCategory(String categoryName) throws Exception {
        try{
            return articleRepository.findArticlesByCategory(categoryName);
        }catch(Exception ex){
            throw new Exception("System error, detail: " + ex);
        }
    }
}
