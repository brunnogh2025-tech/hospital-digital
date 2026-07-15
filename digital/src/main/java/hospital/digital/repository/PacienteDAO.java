package hospital.digital.repository;

import hospital.digital.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteDAO extends JpaRepository<Paciente,Long> {
    public Paciente findByNome(String nome);
    public Paciente findByTelefone(String tel);
    public Paciente findByEmail(String email);
}
