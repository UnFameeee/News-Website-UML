package com.example.newswebsite.services.auth;

import com.example.newswebsite.dtos.UserDto;
import com.example.newswebsite.entities.User;
import com.example.newswebsite.exceptions.DuplicatedEmailException;
import com.example.newswebsite.exceptions.DuplicatedPhoneException;
import com.example.newswebsite.exceptions.DuplicatedUsernameException;
import com.example.newswebsite.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private final ModelMapper modelMapper;

    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User register(UserDto userDto) throws DuplicatedUsernameException, DuplicatedEmailException, DuplicatedPhoneException {
        User user = modelMapper.map(userDto, User.class);
        //encrypt password
        userRepository.save(user);
        return user;
    }

    @Override
    public User login(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        Optional<User> existedUser = userRepository.findUserByEmail(user.getEmail());
        if(!existedUser.isPresent()){
            System.out.println("Khong ton tai");
        }
        return existedUser.get();
    }
}
