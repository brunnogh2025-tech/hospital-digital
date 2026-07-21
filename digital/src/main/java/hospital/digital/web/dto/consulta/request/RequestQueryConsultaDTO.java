package hospital.digital.web.dto.consulta.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RequestQueryConsultaDTO(
        String paciente,
        LocalDateTime agendamento,
        String medico
) {
}
