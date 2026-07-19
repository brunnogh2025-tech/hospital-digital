package hospital.digital.web.dto.medico.request;

import hospital.digital.entity.medico.EspecialidadeMedico;

public record RequestMedicoQueryDTO(
        String nome,
        EspecialidadeMedico especialidade

) {
}
