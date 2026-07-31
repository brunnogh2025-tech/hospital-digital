package hospital.digital.repository;

import hospital.digital.entity.medico.Medico;
import hospital.digital.entity.paciente.Paciente;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MedicoDAO extends JpaRepository<Medico,Long> {
    public Medico findByNome(String nome);

    public Medico findByTelefone(String tel);

    public Medico findByEmail(String email);

    @Query("SELECT DISTINCT m FROM medico LEFT JOIN FETCH p.consulta " +
            "WHERE :dataMax IS NULL OR m.data_nasc >= :dataMax " +
            "AND :dataMin IS NULL OR m.data_nasc <= :dataMin " +
            "AND :nome IS NULL OR m.nome LIKE CONCAT('%' , :nome , '%') " +
            "ORDER BY m.nome")
    public Page<Medico> getAllByPageFilter(@Param("dataMax") LocalDate dataMax, @Param("dataMin")LocalDate dataMin,
                                             @Param("nome")String nome, @NonNull Pageable pageable);

    @Query("SELECT DISTINCT m FROM Medico JOIN FETCH m.consultas ORDER BY medico.nome")
    public Page<Medico> getMedicosByPage(Pageable pageable);
}
