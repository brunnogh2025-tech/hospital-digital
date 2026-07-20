package hospital.digital.web.dto.paciente.response;

import java.time.LocalDate;

public record ResponsePacienteQueryDTO(
        String nome,
        int idade,
        LocalDate dataNasc,
        String sintomas
) {
}
