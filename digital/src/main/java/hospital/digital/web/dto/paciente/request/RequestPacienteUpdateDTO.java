package hospital.digital.web.dto.paciente.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RequestPacienteUpdateDTO(
        String nome,
        LocalDate dataNasc,
        String email,
        String senha,
        String cpf,
        String sintomas,
        String telefone
) {
}
