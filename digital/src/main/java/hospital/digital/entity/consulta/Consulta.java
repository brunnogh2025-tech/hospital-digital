package hospital.digital.entity.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
import hospital.digital.entity.medico.Medico;
import hospital.digital.entity.paciente.Paciente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "consulta")
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime data;

    public Consulta(LocalDateTime data) {
        this.data = data;
    }

    public Consulta() {
    }


}
