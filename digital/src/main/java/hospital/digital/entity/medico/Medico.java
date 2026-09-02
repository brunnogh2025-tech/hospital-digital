package hospital.digital.entity.medico;

import hospital.digital.entity.Calculador_idade;
import hospital.digital.entity.Roles;
import hospital.digital.entity.UserDetailsWithId;
import hospital.digital.entity.consulta.Consulta;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "medico")
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Medico implements Calculador_idade, UserDetailsWithId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    @Getter
    private List<Consulta> consultas;

    @Getter
    private String nome;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Roles roles;

    @Column(unique = true)
    @Getter
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Getter
    private EspecialidadeMedico especialidade;

    @Getter
    private LocalDate data_nasc;

    @Column(unique = true)
    @Getter
    private String cpf;

    @Getter
    private String crm;

    private String senha;

    public Medico(String nome, String email, Roles roles, String telefone, EspecialidadeMedico especialidade, LocalDate data_nasc, String cpf, String crm, String senha) {
        this.nome = nome;
        this.email = email;
        this.roles = roles;
        this.telefone = telefone;
        this.especialidade = especialidade;
        this.data_nasc = data_nasc;
        this.cpf = cpf;
        this.crm = crm;
        this.senha = senha;
    }

    public Medico() {
    }

    @Override
    public int calcularIdade(){
        return Period.between(this.getData_nasc(),LocalDate.now()).getYears();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(roles);
    }

    @Override
    public String getPassword() {
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
