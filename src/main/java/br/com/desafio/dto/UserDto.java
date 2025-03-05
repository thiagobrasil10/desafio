package br.com.desafio.dto;

import br.com.desafio.annotation.ValidEnum;
import br.com.desafio.model.enums.UserEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record UserDto(
        Long id,
        @NotBlank(message = "Campo 'Nome Completo' obrigatório.")
        String fullName,

        @Email(message = "Campo 'E-mail' deverá ser válido.")
        @NotBlank(message = "Campo 'E-mail' obrigatório.")
        String email,

        @Pattern(regexp = "\\+\\d{1,3} \\d{2} \\d{5}-\\d{4}", message = "Campo 'Telefone' deverá possuir o formato '+55 11 99999-9999'.")
        @NotBlank(message = "Campo 'Telefone' obrigatório.")
        String phone,

        @NotNull(message = "Campo 'Data de Nascimento' obrigatório.")
        LocalDate birthDate,

        @NotNull(message = "Campo 'Tipo de Usuário' obrigatório.")
        @ValidEnum(enumClass = UserEnum.class)
        UserEnum userType
    ){}
