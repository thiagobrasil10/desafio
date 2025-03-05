package br.com.desafio.service;

import br.com.desafio.dto.UserDto;
import br.com.desafio.model.UserEntity;
import br.com.desafio.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAll(){
        List<UserEntity> users = userRepository.findAll();
        return users;
    }

    public UserEntity create(UserDto userDto){
        if (userRepository.existsByEmail(userDto.email())) {
            throw new DataIntegrityViolationException("E-mail já cadastrado: " + userDto.email());
        }
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userDto, user);
        return userRepository.save(user);
    }

    public UserEntity getById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
    }

    public UserEntity update(Long id, UserDto userDto){
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        // Verificar se o e-mail já existe para outro usuário
        if (!existingUser.getEmail().equals(userDto.email()) && userRepository.existsByEmail(userDto.email())) {
            throw new RuntimeException("E-mail já registrado em outro usuário.");
        }
        BeanUtils.copyProperties(userDto, existingUser, "id");
        return userRepository.save(existingUser);
    }

    public void delete(Long id){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        userRepository.delete(user);
    }

}
