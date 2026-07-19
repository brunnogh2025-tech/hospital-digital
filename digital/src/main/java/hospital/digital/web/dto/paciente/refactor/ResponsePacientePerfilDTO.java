package hospital.digital.web.dto.paciente.refactor;

import hospital.digital.entity.consulta.Consulta;

import java.time.LocalDate;
import java.util.List;

public record ResponsePacientePerfilDTO(
        List<Consulta> consultaList,
        String nome,
        LocalDate dataNasc,
        String email,
        String cpf,
        String telefone,
        String sintomas
) {
}
