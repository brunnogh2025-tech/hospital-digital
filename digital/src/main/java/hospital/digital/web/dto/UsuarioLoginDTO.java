package hospital.digital.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginDTO(
        @Email(message = "E-mail inválido")
        @NotBlank(message = ("E-mail é obrigatório"))
        String email,
        @NotBlank(message = "Senha é obrigatória.")
        String senha
) {
}
