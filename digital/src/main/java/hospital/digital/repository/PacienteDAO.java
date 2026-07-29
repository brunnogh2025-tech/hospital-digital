package hospital.digital.repository;

import hospital.digital.entity.paciente.Paciente;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PacienteDAO extends JpaRepository<Paciente,Long> {
    public Paciente findByNome(String nome);

    public Paciente findByTelefone(String tel);

    public Paciente findByEmail(String email);

    @Query("SELECT p FROM paciente LEFT JOIN FETCH p.consulta " +
            "WHERE p.data_nasc >= :dataMax AND p.data_nasc <= :dataMin " +
            "ORDER BY p.nome")
    public List<Paciente> findByIdade(@Param("dataMax")LocalDate dataMax, @Param("dataMin")LocalDate dataMin);

    @Query(value = "SELECT DISTINCT p FROM paciente JOIN FETCH p.consultas ORDER BY p.nome")
    public Page<Paciente> findAllByPage(@NonNull Pageable pageable);

}
