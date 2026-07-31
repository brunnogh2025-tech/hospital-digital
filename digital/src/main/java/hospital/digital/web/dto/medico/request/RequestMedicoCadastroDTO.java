package hospital.digital.web.dto.medico.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import hospital.digital.entity.medico.EspecialidadeMedico;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record RequestMedicoCadastroDTO (
    @NotBlank(message = "Nome é obrigatório")
    String nome,

    @Email
    @NotBlank(message = "E-mail é obrigatório")
    String email,

    @NotBlank(message = "Telefone é obrigatório")
    String telefone,

    @Pattern(regexp = "\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve conter apenas números")
    String cpf,

    @NotBlank(message = "Data de nascimento é obrigatória")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Past(message = "Data de nascimento deve estar no passado")
    LocalDate data_nasc,

    @NotBlank(message = "Especialidade é obrigatória")
    EspecialidadeMedico especialidade,

    @NotBlank(message = "CRM é obrigatório")
    @Pattern(
            regexp = "^\\d{4,6}/[A-Z]{2}$",
            message = "CRM deve estar no formato NNNNN/UF (ex: 123456/SP)"
    )
    String crm,

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
            message = "Senha deve ter no mínimo 8 caracteres, incluindo letra maiúscula, minúscula, número e caractere especial"
    )
    @NotBlank(message = "Senha é obrigatória")
    String senha
){

}
