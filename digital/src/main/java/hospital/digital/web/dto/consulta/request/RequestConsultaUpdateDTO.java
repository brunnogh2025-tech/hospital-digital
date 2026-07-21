package hospital.digital.web.dto.consulta.request;

import hospital.digital.entity.medico.Medico;
import hospital.digital.entity.paciente.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RequestConsultaUpdateDTO(
        Paciente paciente,
        LocalDateTime data,
        Medico medico
) {
}
