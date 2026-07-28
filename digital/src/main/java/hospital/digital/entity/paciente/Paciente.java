package hospital.digital.entity.paciente;


import com.fasterxml.jackson.annotation.JsonFormat;
import hospital.digital.entity.consulta.Consulta;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "paciente")
@Getter
@Setter
@Builder
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Consulta> consultas;

    private String nome;

    private LocalDate data_nasc;

    private String email;

    private String senha;

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

    public byte calcularIdade(){
        return (byte) Period.between(data_nasc, LocalDate.now()).getYears();
    }
}
