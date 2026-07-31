package hospital.digital.web.dto.paciente.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import hospital.digital.entity.paciente.Paciente;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RequestPacienteCadastroDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @NotNull(message = "Data de nascimento é obrigatória.")
        @Past(message = "Data de nascimento deve estar no passado")
        LocalDate dataNasc,
        @Email(message = "Email inválido.")
        @NotBlank(message = "E-mail é obrigatório.")
        String email,
        @NotBlank
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
                message = "Senha deve ter no mínimo 8 caracteres, incluindo letra maiúscula, minúscula, número e caractere especial"
        )
        String senha,
        @Pattern(regexp = "\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve conter apenas números")
        String cpf,
        @NotBlank(message = "Telefone é obrigatório.")
        @Pattern(regexp = "\\d{11}")
        String telefone,
        String sintomas
                                         ) {

}
