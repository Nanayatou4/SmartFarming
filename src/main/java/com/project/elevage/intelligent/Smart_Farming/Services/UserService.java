package com.project.elevage.intelligent.Smart_Farming.Services;

import com.project.elevage.intelligent.Smart_Farming.Entities.Authority;
import com.project.elevage.intelligent.Smart_Farming.Entities.UserEntity;
import com.project.elevage.intelligent.Smart_Farming.Repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserEntityRepository userRepository;

//    public UserEntity assignRole(Long userId, Authority authority) {
//        UserEntity user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//
//        user.setAuthority(authority);
//        return userRepository.save(user);
//    }
}
