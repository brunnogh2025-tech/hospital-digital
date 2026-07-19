package hospital.digital.web.dto.medico.response;

import hospital.digital.entity.medico.EspecialidadeMedico;

public record ResponseMedicoQuery(
        String nome,
        EspecialidadeMedico especialidade
) {
}
