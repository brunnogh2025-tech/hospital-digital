package hospital.digital.web.dto.consulta.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ResponseConsultaQueryDTO(
        String paciente,
        LocalDateTime agendamento,
        String medico

) {
}
