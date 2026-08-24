package hospital.digital.entity.medico;

import hospital.digital.entity.Calculador_idade;
import hospital.digital.entity.UserDetailsWithId;
import hospital.digital.entity.consulta.Consulta;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Medico implements Calculador_idade, UserDetailsWithId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Consulta> consultas;

    private String nome;

    @Column(unique = true)
    private String email;

    private List<String> roles;

    @Column(unique = true)
    private String telefone;

    private EspecialidadeMedico especialidade;

    private LocalDate data_nasc;

    @Column(unique = true)
    private String cpf;

    private String crm;

    private String senha;

    public Medico(String nome, String email, List<String> roles, String telefone, EspecialidadeMedico especialidade, LocalDate data_nasc, String cpf, String crm, String senha) {
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
        return roles.stream().map(
                SimpleGrantedAuthority::new
        ).toList();
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
