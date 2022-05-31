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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    //Thêm bài báo ưu thích vào list bài báo ưa thích của user
    //B1: Kiểm tra user có tồn tại hay k
    //B2: Lấy list bài báo ưa thích của user đó
    //B3: Kiểm tra bài báo được chọn thêm vào đã có trong list yêu thích chưa , có r thì k thêm nữa chưa có thì thêm
    @Override
    public User addFavoriteArtical(Map<String, String> data) throws NonexistentValueException {

        Optional<User> user = userRepository.findUserById(data.get("userId"));
        if(user.isEmpty()){
            throw new NonexistentValueException("User doesn't exist !!!");
        }
        else {
            List<String> listFavoriteArticalnew = new ArrayList<>();
            listFavoriteArticalnew = user.get().getFavoriteArtical();
            int checkExist = -1;
            for(int i=0;i<listFavoriteArticalnew.size();i++){
                if(listFavoriteArticalnew.get(i).equals(data.get("articleId"))){
                    checkExist = i;
                }
            }
            if(checkExist != -1) {
                listFavoriteArticalnew.add(data.get("articleId"));
                user.get().setFavoriteArtical(listFavoriteArticalnew);
            }
            else{
                throw new NonexistentValueException("Article existed !!!"); // custom lại sau
            }

            return user.get();


        }


    }
    // tương tự thêm vào nhưng thay vì thêm thì remove
    @Override
    public User removeFavoriteArtical(Map<String, String> data) throws NonexistentValueException {
        Optional<User> user = userRepository.findUserById(data.get("userId"));
        if(user.isEmpty()){
            throw new NonexistentValueException("User doesn't exist !!!");
        }
        else {
            List<String> listFavoriteArticalnew = new ArrayList<>();
            listFavoriteArticalnew = user.get().getFavoriteArtical();
            listFavoriteArticalnew.add(data.get("articleId"));
            int checkExist = -1;
            for(int i=0;i<listFavoriteArticalnew.size();i++){
                if(listFavoriteArticalnew.get(i).equals(data.get("articleId"))){
                    checkExist = i;
                }
            }
            if(checkExist==-1)
                throw new NonexistentValueException("Article doesn't exist in favoriteList !!!");
            else {
                listFavoriteArticalnew.remove(checkExist);
            }
            user.get().setFavoriteArtical(listFavoriteArticalnew);
            return user.get();


        }

    }
}
