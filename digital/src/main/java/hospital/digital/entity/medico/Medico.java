package hospital.digital.entity.medico;

import hospital.digital.entity.Roles;
import hospital.digital.entity.Usuario;
import hospital.digital.entity.consulta.Consulta;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "medico")
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Medico implements Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    
    private List<Consulta> consultas;

    
    private String nome;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Roles roles;

    @Column(unique = true)
    
    private String telefone;

    @Enumerated(EnumType.STRING)
    
    private EspecialidadeMedico especialidade;

    
    private LocalDate data_nasc;

    @Column(unique = true)
    
    private String cpf;

    
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


}
