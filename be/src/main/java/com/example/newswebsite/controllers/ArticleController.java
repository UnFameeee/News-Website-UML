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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public ResponseEntity<List<Article>> getAllArticle(@RequestParam(value = "cate", required = false) String cate, @RequestParam(value = "search", required = false) String search) throws Exception{
        if(cate == null && search == null)
            return new ResponseEntity<>(articleService.getAllArticle(), HttpStatus.OK);
        else if(cate != null)
            return new ResponseEntity<>(articleService.getAllArticleByCateId(cate), HttpStatus.OK);
        else
            return new ResponseEntity<>(articleService.searchArticlesByTitle(search), HttpStatus.OK);
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
    public ResponseEntity<Article> createArticle(@Valid @RequestBody ArticleDto articleDto) throws DuplicatedValueException{
        return new ResponseEntity<>(articleService.createArticle(articleDto), HttpStatus.OK);
    }

    /***
     * @author: idtruoc, Unfame
     * @return: The article has updated status "Đã duyệt"
     * @throws NonexistentValueException : Return Exception if the request article doesn't exist
     */
    @PutMapping("/accept/")
    public ResponseEntity<Article> changeStatusChecked(@Valid @RequestBody Map<String, String> data) throws NonexistentValueException{
        return new ResponseEntity<>(articleService.changeStatusArticleChecked(data), HttpStatus.OK);
    }

    /***
     * @author: idtruoc, Unfame
     * @return: The article has updated status "Không được duyệt"
     * @throws NonexistentValueException : Return Exception if the request article doesn't exist
     */
    @PutMapping("/reject/")
    public ResponseEntity<Article> changeStatusNotChecked(@Valid @RequestBody Map<String, String> data) throws NonexistentValueException{
        return new ResponseEntity<>(articleService.changeStatusArticleNotChecked(data), HttpStatus.OK);
    }

    /***
     * @author: idtruoc
     * @return: get some article trending
     * @throws  Exception : Return Exception if something wrong
     */
    @GetMapping("/trending")
    public ResponseEntity<List<Article>> getTrendingArticle() throws Exception{
        return new ResponseEntity<>(articleService.getArticlesTrending(), HttpStatus.OK);
    }

    /***
     * @author: idtruoc, Unfame
     * @return: List Article by cate related
     * @throws  Exception : Return Exception if something wrong
     */
    @GetMapping("/category/{postId}")
    public ResponseEntity<List<Article>> getArticleRelatedByCategory(@Valid @PathVariable String postId) throws Exception{
        return new ResponseEntity<>(articleService.getArticlesByCatRelated(postId), HttpStatus.OK);
    }

    /***
     * @author: Unfame
     * @return: get all article with status : "Chờ duyệt" ,"Đã duyệt" by Creator
     * @throws  Exception : Return Exception if something wrong
     */
    @GetMapping("/censor/{censorId}/")
    public ResponseEntity<List<Article>> getAllArticleCensor(@Valid @PathVariable String censorId,@RequestParam(value = "status", required = false) String status) throws Exception{
        //Tất cả các Censor đều có thể xem các bài viết chờ duyệt
        if (Objects.equals(status, "Chờ duyệt"))
            return new ResponseEntity<>(articleService.getArticlesWaiting(), HttpStatus.OK);
        //Các bài viết đã duyệt bởi chính censor đó
        else if (Objects.equals(status, "Đã duyệt"))
            return new ResponseEntity<>(articleService.getArticlesChecked(censorId), HttpStatus.OK);
        return new ResponseEntity<>(articleService.getArticlesWaiting(), HttpStatus.OK);
    }

    /***
     * @author: idtruoc, Unfame
     * @return: get all article with status : "Chờ duyệt" ,"Đã duyệt", "Không được duyệt" by Creator
     * @throws  Exception : Return Exception if something wrong
     */
    @GetMapping("/creator/{userId}/")
    public ResponseEntity<List<Article>> getAllArticleCreator(@Valid @PathVariable String userId,@RequestParam(value = "status", required = false) String status) throws Exception{
       if (Objects.equals(status, "Chờ duyệt"))
           return new ResponseEntity<>(articleService.getArticlesWaitingByUserId(userId), HttpStatus.OK);
       else if (Objects.equals(status, "Đã duyệt"))
           return new ResponseEntity<>(articleService.getArticlesCheckedByUserId(userId), HttpStatus.OK);
       else if (Objects.equals(status,  "Không được duyệt"))
           return new ResponseEntity<>(articleService.getArticlesNotCheckedByUserId(userId), HttpStatus.OK);
        //Vừa đăng nhập vào =>
       return new ResponseEntity<>(articleService.getArticlesCheckedByUserId(userId), HttpStatus.OK);
    }

//    /***
//     * @author: idtruoc
//     * @return: Search artircle with title
//     * @throws  Exception : Return Exception if something wrong
//     */
//    @GetMapping("/search/{title}")
//    public ResponseEntity<List<Article>> searchArticleByTitle(@Valid @PathVariable String title) throws Exception{
//        return new ResponseEntity<>(articleService.searchArticlesByTitle(title), HttpStatus.OK);
//    }
}
