package hospital.digital.repository;

import hospital.digital.entity.consulta.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaDAO extends JpaRepository<Consulta,Long> {
    @Query(value = "SELECT c.paciente_id.nome AS paciente, c.data AS data, c.medico_id.nome AS medico FROM Paciente c")
    public Page<Consulta> getConsultaPageable(Pageable pageable);

}
