package br.com.desafio.service;

import br.com.desafio.dto.UserDto;
import br.com.desafio.model.UserEntity;
import br.com.desafio.model.enums.UserEnum;
import br.com.desafio.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private UserEntity user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        user = new UserEntity();
        user.setId(1L);
        user.setFullName("João Silva");
        user.setEmail("joao@email.com");
        user.setPhone("+55 11 99999-9999");
        user.setBirthDate(LocalDate.of(1990, 1, 1));
        user.setUserType(UserEnum.ADMIN);

        userDto = new UserDto(
                1L, "João Silva", "joao@email.com", "+55 11 99999-9999",
                LocalDate.of(1990, 1, 1), UserEnum.ADMIN
        );
    }

    @Test
    void testGetAllUsers() {
        List<UserEntity> users = List.of(user);
        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<UserEntity> result = userService.getAll();

        assertEquals(1, result.size());
        assertEquals("João Silva", result.get(0).getFullName());
    }

    @Test
    void testCreateUser_Success() {
        Mockito.when(userRepository.existsByEmail(userDto.email())).thenReturn(false);
        Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(user);

        UserEntity createdUser = userService.create(userDto);

        assertNotNull(createdUser);
        assertEquals("João Silva", createdUser.getFullName());
    }

    @Test
    void testCreateUser_EmailAlreadyExists() {
        Mockito.when(userRepository.existsByEmail(userDto.email())).thenReturn(true);

        assertThrows(DataIntegrityViolationException.class, () -> userService.create(userDto));
    }

    @Test
    void testGetUserById_Success() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserEntity foundUser = userService.getById(1L);

        assertNotNull(foundUser);
        assertEquals(1L, foundUser.getId());
    }

    @Test
    void testGetUserById_NotFound() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.getById(1L));
    }

    @Test
    void testUpdateUser_Success() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(user);

        UserEntity updatedUser = userService.update(1L, userDto);

        assertNotNull(updatedUser);
        assertEquals("João Silva", updatedUser.getFullName());
    }

    @Test
    void testUpdateUser_EmailAlreadyExists() {
        UserEntity anotherUser = new UserEntity();
        anotherUser.setId(2L);
        anotherUser.setEmail("joao@email.com");

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.existsByEmail(userDto.email())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> userService.update(2L, userDto));
    }

    @Test
    void testDeleteUser_Success() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.doNothing().when(userRepository).delete(user);

        assertDoesNotThrow(() -> userService.delete(1L));
    }

    @Test
    void testDeleteUser_NotFound() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.delete(1L));
    }
}

