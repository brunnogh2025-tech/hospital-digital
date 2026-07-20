package hospital.digital.web.dto.consulta.request;

import java.time.LocalDate;

public record RequestQueryConsultaDTO(
        String paciente,
        LocalDate agendamento,
        String medico
) {
}
