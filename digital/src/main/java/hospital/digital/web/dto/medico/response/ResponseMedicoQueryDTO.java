package hospital.digital.web.dto.medico.response;

import hospital.digital.entity.medico.EspecialidadeMedico;

import java.time.LocalDate;

public record ResponseMedicoQueryDTO(
        String nome,
        EspecialidadeMedico especialidade,
        LocalDate data_nasc
) {
}
