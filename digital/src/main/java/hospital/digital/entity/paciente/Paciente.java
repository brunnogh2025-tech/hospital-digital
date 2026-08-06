package hospital.digital.entity.paciente;


import com.fasterxml.jackson.annotation.JsonFormat;
import hospital.digital.entity.Calculador_idade;
import hospital.digital.entity.UserDetailsWithId;
import hospital.digital.entity.consulta.Consulta;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "paciente")
@Getter
@Setter
@Builder
public class Paciente implements Calculador_idade, UserDetailsWithId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Consulta> consultas;

    private String nome;

    private LocalDate data_nasc;

    private String email;

    private String senha;

    private List<String> roles;

    private String cpf;

    private String telefone;

    private String sintomas;

    public Paciente(String nome, LocalDate data_nasc, String email, List<String> roles, String telefone, String cpf, String sintomas, String senha) {
        this.nome = nome;
        this.data_nasc = data_nasc;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.sintomas = sintomas;
        this.senha = senha;
        this.roles = roles;
    }

    public Paciente() {
    }

    @Override
    public int calcularIdade(){
        return Period.between(data_nasc, LocalDate.now()).getYears();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public @Nullable String getPassword() {
        return this.getSenha();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }
}
