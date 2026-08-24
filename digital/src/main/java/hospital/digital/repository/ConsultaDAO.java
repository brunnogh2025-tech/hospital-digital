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

    //projection
    @Query(value = """
        SELECT new hospital.digital.web.dto.consulta.response.ResponseConsultaQueryDTO(
            c.paciente.nome,
            c.data,
            c.medico.nome
        )
        FROM Consulta c
        """)
    public Page<Consulta> getConsultaPageable(Pageable pageable);

}
