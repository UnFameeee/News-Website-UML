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
import java.util.Map;

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
    public ResponseEntity<List<Article>> getAllArticle(@RequestParam(value = "cate", required = false) String cate) throws Exception{
        if(cate == null)
            return new ResponseEntity<>(articleService.getAllArticle(), HttpStatus.OK);
        else
            return new ResponseEntity<>(articleService.getAllArticleByCateId(cate), HttpStatus.OK);
    }

//    @GetMapping("?{cate}")
//    public ResponseEntity<List<Article>> getAllArticleByCateId(@RequestParam String cate, @PathVariable String cate) throws Exception{
//        return new ResponseEntity<>(articleService.getAllArticle(), HttpStatus.OK);
//    }

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
    public ResponseEntity<Article> createArticle(@Valid @RequestBody ArticleDto articleDto) throws DuplicatedValueException{
        return new ResponseEntity<>(articleService.createArticle(articleDto), HttpStatus.OK);
    }

    /***
     * @author: idtruoc, Unfame
     * @return: The article has updated status "checked"
     * @throws NonexistentValueException : Return Exception if the request article doesn't exist
     */
    @PutMapping("/accept/")
    public ResponseEntity<Article> changeStatusChecked(@Valid @RequestBody Map<String, String> data) throws NonexistentValueException{
        return new ResponseEntity<>(articleService.changeStatusArticleChecked(data), HttpStatus.OK);
    }

    /***
     * @author: idtruoc, Unfame
     * @return: The article has updated status "not checked"
     * @throws NonexistentValueException : Return Exception if the request article doesn't exist
     */
    @PutMapping("/reject/")
    public ResponseEntity<Article> changeStatusNotChecked(@Valid @RequestBody Map<String, String> data) throws NonexistentValueException{
        return new ResponseEntity<>(articleService.changeStatusArticleNotChecked(data), HttpStatus.OK);
    }



    /***
     * @author: idtruoc
     * @return: Get All article checked by admin
     * @throws  Exception : Return Exception if something wrong
     */
    @GetMapping("/checked/")
    public ResponseEntity<List<Article>> getAllArticleChecked(@Valid @RequestBody  ArticleDto articleDto) throws Exception{
        return new ResponseEntity<>(articleService.getArticlesChecked(articleDto), HttpStatus.OK);

    }

    /***
     * @author: idtruoc
     * @return: get all article with status : "waiting" by UserId
     * @throws  Exception : Return Exception if something wrong
     */
    @GetMapping("/userId/waiting/{userId}")
    public ResponseEntity<List<Article>> getAllArticleWaitingByUserId(@Valid @PathVariable String userId) throws Exception{
        return new ResponseEntity<>(articleService.getArticlesWaitingByUserId(userId), HttpStatus.OK);
    }  /***
     * @author: idtruoc
     * @return: get all article with status : "Checked" by UserId
     * @throws  Exception : Return Exception if something wrong
     */
    @GetMapping("/userId/checked/{userId}")
    public ResponseEntity<List<Article>> getAllArticleCheckedByUserId(@Valid @PathVariable String userId) throws Exception{
        return new ResponseEntity<>(articleService.getArticlesCheckedByUserId(userId), HttpStatus.OK);
    }
    /***
     * @author: idtruoc
     * @return: Search artircle with title
     * @throws  Exception : Return Exception if something wrong
     */
    @GetMapping("/search/{title}")
    public ResponseEntity<List<Article>> searchArticleByTitle(@Valid @PathVariable String title) throws Exception{
        return new ResponseEntity<>(articleService.searchArticlesByTitle(title), HttpStatus.OK);
    }

}
