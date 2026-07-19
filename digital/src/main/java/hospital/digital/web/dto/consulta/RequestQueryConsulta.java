package hospital.digital.web.dto.consulta;

import java.time.LocalDate;

public record RequestQueryConsulta(
        String paciente,
        LocalDate agendamento,
        String medico

) {
}
