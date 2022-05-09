package com.example.newswebsite.services.user;

import com.example.newswebsite.dtos.UserDto;
import com.example.newswebsite.entities.User;
import com.example.newswebsite.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAllUser();
    }

//    @Override
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }

//    @Override
//    public Optional<Users> findUserById(Long id) {
//        return userRepository.findById(id);
//    }

//    @Override
//    public Optional<User> findUserByEmail(String email) {
//        return Optional.ofNullable(userRepository.findPasswordByEmail(email));
//    }

}
