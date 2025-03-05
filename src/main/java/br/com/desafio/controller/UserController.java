package br.com.desafio.controller;

import br.com.desafio.dto.UserDto;
import br.com.desafio.model.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api")
@Tag(name = "Usuários", description = "Endpoints de gerenciamento de usuários")
public interface UserController {

    @GetMapping("/users")
    @Operation(summary = "Listar usuários", description = "Retorna a lista de todos os usuários cadastrados")
    ResponseEntity<List<UserEntity>> getUsers();

    @PostMapping("/user")
    @Operation(summary = "Criar um usuário", description = "Cria um novo usuário na base de dados")
    ResponseEntity<UserEntity> createUser(@RequestBody UserDto userDto);

    @GetMapping("/user/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário específico pelo seu ID")
    ResponseEntity<UserEntity> getUser(@PathVariable("id") Long id);

    @PutMapping("/user/{id}")
    @Operation(summary = "Atualizar um usuário", description = "Atualiza um usuário do sistema pelo ID e retorna com as novas informações")
    ResponseEntity<UserEntity> updateUser(@PathVariable("id") Long id,
                                          @RequestBody UserDto userDto);

    @DeleteMapping("/user/{id}")
    @Operation(summary = "Excluir um usuário", description = "Remove um usuário do sistema pelo ID")
    ResponseEntity<Void> deleteUser(@PathVariable("id") Long id);

}
