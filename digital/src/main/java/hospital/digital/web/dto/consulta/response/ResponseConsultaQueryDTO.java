package hospital.digital.web.dto.consulta.response;

import java.time.LocalDate;

public record ResponseConsultaQueryDTO(
        String paciente,
        LocalDate agendamento,
        String medico

) {
}
