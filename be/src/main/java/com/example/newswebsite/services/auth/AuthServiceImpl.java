package com.example.newswebsite.services.auth;

import com.example.newswebsite.dtos.UserDto;
import com.example.newswebsite.entities.User;
import com.example.newswebsite.entities.embedded.AccountEmbedded;
import com.example.newswebsite.exceptions.*;
import com.example.newswebsite.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User register(UserDto userDto) throws DuplicatedUsernameException, DuplicatedEmailException {
        User user = modelMapper.map(userDto, User.class);
        AccountEmbedded acc =  user.getAccount();
        if(userRepository.findUserByEmail(user.getEmail()).isPresent()){
            throw new DuplicatedEmailException("Email existed!!!");
        }
        else if(userRepository.findUserByUsername(user.getPhone()).isPresent()){
            throw new DuplicatedUsernameException("Username existed!!!");
        }

        String encryptedPass = this.passwordEncoder.encode(acc.getPassword());
        acc.setPassword(encryptedPass);
        user.setAccount(acc);
        userRepository.save(user);
        return user;
    }

    @Override
    public User login(UserDto userDto) throws UserNotExistException, PasswordIncorrectException {
        User user = modelMapper.map(userDto, User.class);
        Optional<User> existedUser = userRepository.findUserByEmail(user.getEmail());
        if(existedUser.isEmpty()){
            throw new UserNotExistException("User does not exist!!!");
        }
        AccountEmbedded acc = existedUser.get().getAccount();
        if(!this.passwordEncoder.matches(userDto.getAccount().getPassword(), acc.getPassword())){
            throw new PasswordIncorrectException("Wrong Password, please try again!!!");
        }else
            return existedUser.get();
    }
}
