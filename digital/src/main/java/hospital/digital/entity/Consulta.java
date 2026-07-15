package hospital.digital.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "consulta")
@Getter
@Setter
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    public Consulta(LocalDate data) {
        this.data = data;
    }

    public Consulta() {
    }


}
