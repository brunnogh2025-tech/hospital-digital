package hospital.digital.repository;

import hospital.digital.entity.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaDAO extends JpaRepository<Consulta,Long> {

}
