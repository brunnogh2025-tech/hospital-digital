package hospital.digital.web.dto.consulta.request;

import java.time.LocalDate;

public record RequestAgendamentoConsultaDTO(
        String paciente,
        LocalDate dataAgendada,
        String medico
) {
}
