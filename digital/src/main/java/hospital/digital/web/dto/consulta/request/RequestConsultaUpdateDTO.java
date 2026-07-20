package hospital.digital.web.dto.consulta.request;

import hospital.digital.entity.medico.Medico;
import hospital.digital.entity.paciente.Paciente;

import java.time.LocalDate;

public record RequestConsultaUpdateDTO(
        Paciente paciente,
        LocalDate data,
        Medico medico
) {
}
