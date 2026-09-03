package hospital.digital.entity.paciente;


import hospital.digital.entity.Calculador_idade;
import hospital.digital.entity.Roles;
import hospital.digital.entity.consulta.Consulta;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "paciente")
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Paciente implements Calculador_idade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Consulta> consultas;

    private String nome;

    private LocalDate data_nasc;

    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    private Roles roles;

    private String cpf;

    private String telefone;

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


}
