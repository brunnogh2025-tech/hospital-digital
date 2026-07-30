package hospital.digital.web.dto.paciente.request;

import java.time.LocalDate;

public record RequestPacienteQueryDTO(
        String nome,
        int idade,
        LocalDate dataNasc
) {
}
