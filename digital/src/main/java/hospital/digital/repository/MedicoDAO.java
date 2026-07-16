package hospital.digital.repository;

import hospital.digital.entity.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoDAO extends JpaRepository<Medico,Long> {
    public Medico findByNome(String nome);
    public Medico findByTelefone(String tel);
    public Medico findByEmail(String email);
}
