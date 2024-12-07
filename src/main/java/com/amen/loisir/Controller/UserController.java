package com.amen.loisir.Controller;

import com.amen.loisir.DTO.UserRequest;
import com.amen.loisir.Entities.Role;
import com.amen.loisir.Entities.RoleType;
import com.amen.loisir.Entities.User;
import com.amen.loisir.Exception.BadRequestHttpException;
import com.amen.loisir.Repositories.RoleRepository;
import com.amen.loisir.Repositories.UserRepository;
import com.amen.loisir.Services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/user")

public class UserController {

    private final UserService userService;
    private final UserRepository userRepository ;
    private final RoleRepository roleRepository;
    @GetMapping
    public List<UserRequest> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserRequest::new)
                .collect(Collectors.toList()); }

    @PostMapping("/add")
    public User add(@RequestBody UserRequest user) {
        System.out.println(user);
        return userService.add(user);
    }


    @Transactional
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) throws BadRequestHttpException {
        userService.deleteUser(id);
    }

}
