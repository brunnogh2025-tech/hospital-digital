package hospital.digital.entity.paciente;


import com.fasterxml.jackson.annotation.JsonFormat;
import hospital.digital.entity.consulta.Consulta;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "paciente")
@Getter
@Setter
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Consulta> consultas;

    @NotBlank
    private String nome;

    @NotBlank
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_nasc;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String telefone;

    @NotBlank
    private String sintomas;

    public Paciente(String nome, LocalDate data_nasc, String email, String telefone, String sintomas) {
        this.nome = nome;
        this.data_nasc = data_nasc;
        this.email = email;
        this.telefone = telefone;
        this.sintomas = sintomas;
    }

    public Paciente() {
    }
}
