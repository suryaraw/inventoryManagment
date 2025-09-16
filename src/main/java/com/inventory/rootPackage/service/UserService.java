package com.inventory.rootPackage.service;

import com.inventory.rootPackage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    public void saveUser(User user) {
//        userRepository.save(user);
//    }
//
//    public boolean updatePassword(String email, String newPassword) {
//        User user = userRepository.findByEmail(email);
//        if (user != null) {
//            user.setPassword(newPassword);
//            userRepository.save(user);
//            return true;
//        }
//        return false;
//    }
}
