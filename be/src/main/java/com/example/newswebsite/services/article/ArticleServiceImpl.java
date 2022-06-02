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

import java.util.*;
import java.util.Collections;
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
            List<Article> articles = articleRepository.findArticlesByStatus("Đã duyệt");
        return  articles;
        }catch(Exception ex){
            throw new Exception("System error, detail: " + ex);
        }
    }

    @Override
    public List<Article> getAllArticleByCateId(String cate) throws Exception {
        try{
            Optional<Category> category = categoryRepository.findCategoryByCategoryNameAndIsActive(cate, true);
            return articleRepository.findArticlesByCategoryAndStatus("Đã duyệt", category.get().getId());
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
    public Article changeStatusArticleChecked(Map<String, String> data) throws NonexistentValueException {
        Optional<Article> article = articleRepository.findArticleById(data.get("articleId"));
        if(article.isEmpty()){
            throw new NonexistentValueException("Article doesn't exist !!!");
        }
        else {
            article.get().setStatus("Đã duyệt");
            article.get().setCensorId(data.get("censorId"));
            articleRepository.save(article.get());
        }
        return article.get();
    }

    @Override
    public Article changeStatusArticleNotChecked(Map<String, String> data) throws NonexistentValueException {
        Optional<Article> article = articleRepository.findArticleById(data.get("articleId"));
        if(article.isEmpty()){
            throw new NonexistentValueException("Article doesn't exist !!!");
        }
        else {
            article.get().setStatus("Không được duyệt");
            article.get().setCensorId(data.get("censorId"));
            articleRepository.save(article.get());
        }
        return article.get();
    }

    @Override
    public Article changeStatusArticleDisable(Map<String, String> data) throws NonexistentValueException {
        Optional<Article> article = articleRepository.findArticleById(data.get("articleId"));
        if(article.isEmpty()){
            throw new NonexistentValueException("Article doesn't exist !!!");
        }
        else {
            article.get().setStatus("Không hoạt động");
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

    @Override
    public List<Article> getArticlesByCatRelated(String postId) throws NonexistentValueException {
        //Find article cate with articleId
        Optional<Article> article = articleRepository.findArticleById(postId);
        if(article.isEmpty()){
            throw new NonexistentValueException("Article doesn't exist !!!");
        }
//        Optional<Category> category = categoryRepository.findCategoryByIdAndIsActive(article.get().getCategoryId(), true);

        List<Article> articleList = articleRepository.findArticlesByCategoryAndStatus("Đã duyệt", article.get().getCategoryId());
        if(articleList.isEmpty()){
            throw new NonexistentValueException("Category doesn't exist !!!");
        }
        List<Article> articleListNew = new ArrayList<>();
        Collections.shuffle(articleList);
        for(int i =0;i<6;i++){
            articleListNew.add(articleList.get(i));
        }
        return articleListNew;
    }

    @Override
    public List<Article> getArticlesWaitingByUserId(String userId) throws Exception {
        try{
            return articleRepository.findArticlesByStatusAndUserId("Chờ duyệt", userId);
        }catch(Exception ex){
            throw new Exception("System error, detail: " + ex);
        }
    }

    @Override
    public List<Article> getArticlesCheckedByUserId(String userId) throws Exception {
        try{
            return articleRepository.findArticlesByStatusAndUserId("Đã duyệt", userId);
        }catch(Exception ex){
            throw new Exception("System error, detail: " + ex);
        }
    }

    @Override
    public List<Article> getArticlesNotCheckedByUserId(String userId) throws Exception {
        try{
            return articleRepository.findArticlesByStatusAndUserId("Không được duyệt", userId);
        }catch(Exception ex){
            throw new Exception("System error, detail: " + ex);
        }
    }

    // lấy những bài viết admin đã duyệt của chính admin đó
    @Override
    public List<Article> getArticlesChecked(String censorId) throws Exception {
        try{
            return articleRepository.findArticlesByStatusAndCensorId("Đã duyệt", censorId);
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
    public List<Article> getArticlesTrending() throws Exception {
        try{
            List<Article> articleList = articleRepository.findArticlesByStatus("Đã duyệt");

            List<Article> articleListNew = new ArrayList<>();

            Collections.shuffle(articleList);
            for(int i =0;i<6;i++){
                articleListNew.add(articleList.get(i)) ;
            }
            return  articleListNew;
        }catch(Exception ex){
            throw new Exception("System error, detail: " + ex);
        }
    }
}
