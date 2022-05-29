package com.example.newswebsite.controllers;

import com.example.newswebsite.dtos.ArticleDto;
import com.example.newswebsite.entities.Article;
import com.example.newswebsite.exceptions.DuplicatedValueException;
import com.example.newswebsite.exceptions.NonexistentValueException;
import com.example.newswebsite.services.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /***
     * @author: Unfame
     * @return: All article in the database
     * @throws Exception : Return Exception if something wrong
     */
    @GetMapping("/")
    public ResponseEntity<List<Article>> getAllArticle() throws Exception{
        return new ResponseEntity<>(articleService.getAllArticle(), HttpStatus.OK);
    }

    /***
     * @author: Unfame
     * @return: Article required
     * @throws NonexistentValueException : Return Exception if the request article doesn't exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<Article> getOneArticle(@Valid @PathVariable String id) throws NonexistentValueException {
        return new ResponseEntity<>(articleService.getOneArticle(id), HttpStatus.OK);
    }

    /***
     * @author: Unfame
     * @return: The article has just been created
     * @throws DuplicatedValueException : Return Exception if the title of the article is duplicated
     */
    @PostMapping("")
    public ResponseEntity<Article> creatingArticle(@Valid @RequestBody ArticleDto articleDto) throws DuplicatedValueException{
        return new ResponseEntity<>(articleService.creatingArticle(articleDto), HttpStatus.OK);
    }
    /***
     * @author: idtruoc
     * @return: The article has updated status "checked"
     * @throws NonexistentValueException : Return Exception if the request article doesn't exist
     */
    @PutMapping("/checked/")
    public ResponseEntity<Article> changeStatusChecked(@Valid @RequestBody ArticleDto articleDto) throws NonexistentValueException{
        return new ResponseEntity<>(articleService.changeStatusArticleChecked(articleDto), HttpStatus.OK);
    }
    /***
     * @author: idtruoc
     * @return: The article has updated status "not checked"
     * @throws NonexistentValueException : Return Exception if the request article doesn't exist
     */

    @PutMapping("/notchecked/")
    public ResponseEntity<Article> changeStatusNotChecked(@Valid @RequestBody  ArticleDto articleDto) throws NonexistentValueException{
        return new ResponseEntity<>(articleService.changeStatusArticleNotChecked(articleDto), HttpStatus.OK);
    }
    /***
     * @author: idtruoc
     * @return: get all article with status : "waiting"
     * @throws  Exception : Return Exception if something wrong
     */
    @GetMapping("/waiting/")
    public ResponseEntity<List<Article>> getAllArticleWaiting() throws Exception{
       return new ResponseEntity<>(articleService.getArticlesWaiting(), HttpStatus.OK);

    }
    /***
     * @author: idtruoc
     * @return: Get All article checked by admin
     * @throws  Exception : Return Exception if something wrong
     */
    @GetMapping("/checked/")
    public ResponseEntity<List<Article>> getAllArticleChecked(@Valid @RequestBody  ArticleDto articleDto) throws Exception{
        return new ResponseEntity<>(articleService.getArticlesCheked(articleDto), HttpStatus.OK);

    }
    /***
     * @author: idtruoc
     * @return: Search artircle with title
     * @throws  Exception : Return Exception if something wrong
     */
    @GetMapping("/search/{title}")
    public ResponseEntity<List<Article>> searchArticlleByTitle(@Valid @PathVariable String title) throws Exception{
        return new ResponseEntity<>(articleService.searchArticlesByTitle(title), HttpStatus.OK);

    }
}
