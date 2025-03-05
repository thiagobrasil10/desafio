package br.com.desafio.controller;

import br.com.desafio.dto.UserDto;
import br.com.desafio.model.UserEntity;
import br.com.desafio.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserControllerImp implements UserController{

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<List<UserEntity>> getUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @Override
    public ResponseEntity<UserEntity> createUser(@Valid UserDto userDto) {
        UserEntity newUser = userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @Override
    public ResponseEntity<UserEntity> getUser(@Valid Long id) {
        UserEntity user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<UserEntity> updateUser(@Valid Long id, UserDto userDto) {
        UserEntity updatedUser = userService.update(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
