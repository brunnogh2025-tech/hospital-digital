package hospital.digital.web.dto.medico.response;

import hospital.digital.entity.medico.EspecialidadeMedico;

public record ResponseMedicoQueryDTO(
        String nome,
        EspecialidadeMedico especialidade
) {
}
