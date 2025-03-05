package br.com.desafio.model;

import br.com.desafio.annotation.ValidEnum;
import br.com.desafio.model.enums.UserEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    @NotBlank(message = "Campo 'Nome Completo' obrigatório.")
    private String fullName;

    @Column(unique = true, name = "email")
    @Email(message = "Campo 'E-mail' deverá ser válido.")
    @NotBlank(message = "Campo 'E-mail' obrigatório.")
    private String email;

    @Column(name = "phone")
    @Pattern(regexp = "\\+\\d{1,3} \\d{2} \\d{5}-\\d{4}", message = "Campo 'Telefone' deverá possui o formato '+55 11 99999-9999'.")
    @NotBlank(message = "Campo 'Telefone' obrigatório.")
    private String phone;

    @Column(name = "birth_date")
    @NotNull(message = "Campo 'Data de Nascimento' obrigatório.")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    @NotNull(message = "Campo 'Tipo de Usuário' obrigatório.")
    @ValidEnum(enumClass = UserEnum.class)
    private UserEnum userType;
}
