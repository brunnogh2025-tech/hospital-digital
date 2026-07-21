package hospital.digital.web.dto.consulta.request;

import hospital.digital.entity.medico.Medico;
import hospital.digital.entity.paciente.Paciente;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RequestAgendamentoConsultaDTO(
        Paciente paciente,
        @NotNull(message = "Data é obrigatória.")
        LocalDateTime dataAgendada,
        Medico medico
) {
}
