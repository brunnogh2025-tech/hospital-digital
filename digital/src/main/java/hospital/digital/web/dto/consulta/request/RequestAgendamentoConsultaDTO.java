package hospital.digital.web.dto.consulta.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RequestAgendamentoConsultaDTO(
        Long paciente_id,
        @NotNull(message = "Data é obrigatória.")
        LocalDateTime dataAgendada,
        Long medico_id
) {
}
