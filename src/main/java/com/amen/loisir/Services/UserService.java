package com.amen.loisir.Services;


import com.amen.loisir.Entities.User;
import com.amen.loisir.Exception.BadRequestHttpException;
import com.amen.loisir.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.amen.loisir.DTO.UserRequest;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;


    private  User buildUser(UserRequest o) {
        return User.builder()
                .id(o.getIduser())
                .lname(o.getLname())
                .fname(o.getFname())
                .email(o.getEmail())
                .password(passwordEncoder.encode(o.getPassword())) // Utilisation de PasswordEncoder
                .adresse(o.getAdresse())
                .username(o.getUsername())
                .roles(o.getRoles())
                .build();
    }


    @Autowired
    UserRepository userRepository;


    public User add(UserRequest o) {


            var user = buildUser(o);
            return userRepository.save(user);

    }

    public void deleteUser(Long userId) throws BadRequestHttpException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
        } else {
            throw new BadRequestHttpException("Utilisateur non trouvé");
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }
}
