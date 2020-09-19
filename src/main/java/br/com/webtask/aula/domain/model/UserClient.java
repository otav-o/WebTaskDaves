package br.com.webtask.aula.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 80, min = 3)
    @Column(name = "nome", nullable = false, length = 80)
    private String name;

    @NotBlank
    //@CPF(message = "CPF inválido")
    @Column(name = "cpf", nullable = false, length = 50, unique = true)
    private String cpf;

    @NotBlank
    @Email(message = "{format.email}")
    @Column(nullable = false, unique = true, length = 80)
    private String email;

    @NotBlank
    //@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8,}",
    //        message = "A senha deve ser forte.")
    @Column(nullable = false, length = 100)
    private String senha;
    
    private boolean ativo;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks;

}
