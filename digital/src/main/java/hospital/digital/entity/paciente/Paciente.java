package hospital.digital.entity.paciente;


import hospital.digital.entity.Calculador_idade;
import hospital.digital.entity.Roles;
import hospital.digital.entity.UserDetailsWithId;
import hospital.digital.entity.consulta.Consulta;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "paciente")
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Paciente implements Calculador_idade, UserDetailsWithId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    @Getter
    private List<Consulta> consultas;

    @Getter
    private String nome;

    @Getter
    private LocalDate data_nasc;

    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    private Roles roles;

    @Getter
    private String cpf;

    @Getter
    private String telefone;

    @Getter
    private String sintomas;

    public Paciente(String nome, LocalDate data_nasc, String email, String telefone, String cpf, String sintomas, String senha) {
        this.nome = nome;
        this.data_nasc = data_nasc;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.sintomas = sintomas;
        this.senha = senha;
    }

    public Paciente() {
    }

    @Override
    public int calcularIdade(){
        return Period.between(data_nasc, LocalDate.now()).getYears();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(roles);
    }

    @Override
    public @Nullable String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
