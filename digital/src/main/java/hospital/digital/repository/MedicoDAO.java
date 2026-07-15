package hospital.digital.repository;

import hospital.digital.entity.Medico;
import hospital.digital.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoDAO extends JpaRepository<Medico,Long> {
    public Medico findByNome(String nome);
    public Medico findByTelefone(String tel);
    public Medico findByEmail(String email);
}
