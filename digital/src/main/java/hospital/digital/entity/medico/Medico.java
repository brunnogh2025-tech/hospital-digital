package hospital.digital.entity.medico;

import hospital.digital.entity.consulta.Consulta;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "medico")
@Getter
@Setter
@Builder
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Consulta> consultas;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String telefone;

    @NotBlank
    private EspecialidadeMedico especialidade;

    public Medico(String nome, LocalDate data_nasc, String email, String telefone, EspecialidadeMedico especialidade) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.especialidade = especialidade;
    }

    public Medico() {
    }
}
