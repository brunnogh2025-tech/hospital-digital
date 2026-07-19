package hospital.digital.web.dto.paciente.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestPacienteLoginDTO(
        @Email(message = "E-mail inválido")
        @NotBlank(message = ("E-mail é obrigatório"))
        String email,
        @NotBlank(message = "Senha é obrigatória.")
        String senha
){

}
