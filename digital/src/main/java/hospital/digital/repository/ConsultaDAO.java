package hospital.digital.repository;

import hospital.digital.entity.consulta.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//Resolver problema N+1

@Repository
public interface ConsultaDAO extends JpaRepository<Consulta,Long> {
    @Query(value = "SELECT c.paciente.nome AS paciente, c.data AS data, c.medico.nome AS medico FROM Consulta c")
    public Page<Consulta> getConsultaPageable(Pageable pageable);

}
