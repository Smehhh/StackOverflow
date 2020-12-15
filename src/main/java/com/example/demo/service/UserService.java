package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.persistence.UserDAO;
import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserDAO userDAO;
    @Autowired
     BCryptPasswordEncoder bCryptPasswordEncoder;
    @PostConstruct
    public void init(){
        userDAO.findByUserName("user").ifPresent(user ->{
                user.setPassword(bCryptPasswordEncoder.encode("password"));
        userDAO.save(user);});
        System.out.println(userDAO.findByUserName("user").get().getPassword().length());
      /*  if (!userDAO.findByUserName("user").isPresent()){
            userDAO.save(User.builder().username("user").password(encoder.encode("password")).credentialsNonExpired(true)
                    .accountNonLocked(true).authorities(ImmutableList.of(Role.USER)).enabled(true).accountNonExpired(true).build());
        }*/
    }
    @Override
    public UserDetails loadUserByUsername(@NotNull String s) throws UsernameNotFoundException {
        return userDAO.findByUserName(s).orElseThrow(()->new UsernameNotFoundException("user " + s + " not found!"));
    }
}
