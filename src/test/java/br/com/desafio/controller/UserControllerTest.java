package br.com.desafio.controller;
import br.com.desafio.dto.UserDto;
import br.com.desafio.model.UserEntity;
import br.com.desafio.model.enums.UserEnum;
import br.com.desafio.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserControllerImp.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

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
    void testGetUsers() throws Exception {
        List<UserEntity> users = List.of(user);
        Mockito.when(userService.getAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].fullName").value("João Silva"));
    }

    @Test
    void testCreateUser() throws Exception {
        Mockito.when(userService.create(Mockito.any(UserDto.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))  // Usando ObjectMapper corretamente
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("joao@email.com"));
    }

    @Test
    void testGetUserById() throws Exception {
        Mockito.when(userService.getById(1L)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("João Silva"));
    }

    @Test
    void testUpdateUser() throws Exception {
        Mockito.when(userService.update(Mockito.eq(1L), Mockito.any(UserDto.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("João Silva"));
    }

    @Test
    void testDeleteUser() throws Exception {
        Mockito.doNothing().when(userService).delete(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/1"))
                .andExpect(status().isNoContent());
    }

}


