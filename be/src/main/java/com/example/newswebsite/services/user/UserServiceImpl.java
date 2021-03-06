package com.example.newswebsite.services.user;

import com.example.newswebsite.utils.EncryptPassSingleton;
import com.example.newswebsite.utils.LoopObjectSingleton;
import com.example.newswebsite.utils.ModelMapperSingleton;
import com.example.newswebsite.dtos.UserDto;
import com.example.newswebsite.entities.User;
import com.example.newswebsite.entities.embedded.AccountEmbedded;
import com.example.newswebsite.exceptions.ConflictedOldValueException;
import com.example.newswebsite.exceptions.DuplicatedValueException;
import com.example.newswebsite.exceptions.NonexistentValueException;
import com.example.newswebsite.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser() throws Exception {
        try {
            return userRepository.findAllUser();
        }catch (Exception ex){
            throw new Exception("System error, detail: " + ex);
        }
    }

    @Override
    public User getOneUser(String id) throws NonexistentValueException {
        Optional<User> user = userRepository.findUserById(id);
        if(user.isEmpty()){
            throw new NonexistentValueException("User doesn't exist !!!");
        }
        return user.get();
    }

    @Override
    public User updateUser(UserDto userDto) throws NonexistentValueException, ConflictedOldValueException, DuplicatedValueException {
        User user = ModelMapperSingleton.getInstance().modelMapper().map(userDto, User.class);
        AccountEmbedded acc = user.getAccount();
        Optional<User> oldUser = userRepository.findUserById(user.getId());
        if(oldUser.isEmpty()){
            throw new NonexistentValueException("Please login to continue your work !!!");
        }
        AccountEmbedded oldAcc = oldUser.get().getAccount();

        LoopObjectSingleton.getInstance().mergingContent(AccountEmbedded.class, acc, oldAcc);
        LoopObjectSingleton.getInstance().mergingContent(User.class, user, oldUser.get());

        //check conflict username and email
        if(userRepository.findUserByUsernameButNotTheSameId(user.getId(), acc.getUsername()).isPresent()){
            throw new DuplicatedValueException("Username existed !!!");
        }else if(userRepository.findUserByEmailButNotTheSameId(user.getId(), user.getEmail()).isPresent()){
            throw new DuplicatedValueException("Email existed !!!");
        }

        //check if pass was hashed if it's not hash it
        if(!EncryptPassSingleton.getInstance().checkHashed(acc.getPassword())){
            if(EncryptPassSingleton.getInstance().compare(acc.getPassword(), oldUser.get().getAccount().getPassword())){
                throw new ConflictedOldValueException("This password was used in the pass !!!");
            }
            acc.setPassword(EncryptPassSingleton.getInstance().encrypt(acc.getPassword()));
        }
        user.setAccount(acc);
        return userRepository.save(user);
    }
    //Th??m b??i b??o ??u th??ch v??o list b??i b??o ??a th??ch c???a user
    //B1: Ki???m tra user c?? t???n t???i hay k
    //B2: L???y list b??i b??o ??a th??ch c???a user ????
    //B3: Ki???m tra b??i b??o ???????c ch???n th??m v??o ???? c?? trong list y??u th??ch ch??a , c?? r th?? k th??m n???a ch??a c?? th?? th??m
    @Override
    public User addFavoriteArticle(Map<String, String> data) throws NonexistentValueException {
        Optional<User> user = userRepository.findUserById(data.get("userId"));
        if(user.isEmpty()){
            throw new NonexistentValueException("User doesn't exist !!!");
        }
        else {
            List<String> listFavoriteArticleNew = user.get().getFavoriteArticle();
            int checkExist = -1;
            for(int i=0;i<listFavoriteArticleNew.size();i++){
                if(Objects.equals(listFavoriteArticleNew.get(i), (data.get("articleId")))){
                    checkExist = i;
                }
            }
            if(checkExist == -1) {
                listFavoriteArticleNew.add(data.get("articleId"));
                user.get().setFavoriteArticle(listFavoriteArticleNew);
            }
            else{
                throw new NonexistentValueException("Article existed !!!"); // custom l???i sau
            }
            return userRepository.save(user.get());
        }
    }
    // t????ng t??? th??m v??o nh??ng thay v?? th??m th?? remove
    @Override
    public User removeFavoriteArticle(Map<String, String> data) throws NonexistentValueException {
        Optional<User> user = userRepository.findUserById(data.get("userId"));
        if(user.isEmpty()){
            throw new NonexistentValueException("User doesn't exist !!!");
        }
        else {
            List<String> listFavoriteArticleNew = user.get().getFavoriteArticle();
            int checkExist = -1;
            for(int i=0;i<listFavoriteArticleNew.size();i++){
                if((Objects.equals(listFavoriteArticleNew.get(i), data.get("articleId")))){
                    checkExist = i;
                }
            }
            if(checkExist !=-1){
                listFavoriteArticleNew.remove(checkExist);
                user.get().setFavoriteArticle(listFavoriteArticleNew);
            }
            else {
                throw new NonexistentValueException("Article doesn't exist in favoriteList !!!");
            }
            return userRepository.save(user.get());
        }
    }

    @Override
    public Boolean isLiked(String userId, String articleId) throws Exception {
        try{
            Optional<User> user = userRepository.findUserById(userId);
            if(user.isEmpty()){
                throw new NonexistentValueException("User doesn't exist !!!");
            }
            else {
                List<String> listFavoriteArticleNew = user.get().getFavoriteArticle();
                for (int i = 0; i < listFavoriteArticleNew.size(); i++) {
                    if (Objects.equals(listFavoriteArticleNew.get(i), (articleId))) {
                        return true;
                    }
                }
                return false;
            }
        }
        catch (Exception ex){
            throw new Exception("System error, detail: " + ex);
        }
    }
}
