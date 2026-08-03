package hospital.digital.repository;

import hospital.digital.entity.paciente.Paciente;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PacienteDAO extends JpaRepository<Paciente,Long>{
    public Paciente findByNome(String nome);

    public Paciente findByTelefone(String tel);

    public Optional<UserDetails> findByEmail(String email);

    @Query("SELECT DISTINCT p FROM paciente LEFT JOIN FETCH p.consulta " +
            "WHERE :dataMax IS NULL OR p.data_nasc >= :dataMax " +
            "AND :dataMin IS NULL OR p.data_nasc <= :dataMin " +
            "AND :nome IS NULL OR p.nome LIKE CONCAT('%' , :nome , '%') " +
            "ORDER BY p.nome")
    public Page<Paciente> getAllByPageFilter(@Param("dataMax")LocalDate dataMax, @Param("dataMin")LocalDate dataMin,
                                      @Param("nome")String nome,@NonNull Pageable pageable);

    @Query(value = "SELECT DISTINCT p FROM paciente JOIN FETCH p.consultas ORDER BY p.nome")
    public Page<Paciente> findAllByPage(@NonNull Pageable pageable);

}
