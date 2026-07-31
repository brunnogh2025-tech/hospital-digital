package hospital.digital.web.dto.medico.request;

import hospital.digital.entity.medico.EspecialidadeMedico;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record RequestMedicoQueryDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Especialidade é obrigatória")
        EspecialidadeMedico especialidade

) {
}
