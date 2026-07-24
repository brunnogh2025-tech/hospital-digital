package hospital.digital.repository;

import hospital.digital.entity.paciente.Paciente;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteDAO extends JpaRepository<Paciente,Long> {
    public Paciente findByNome(String nome);

    public Paciente findByTelefone(String tel);

    public Paciente findByEmail(String email);

    @Query(value = "SELECT DISTINCT p FROM paciente JOIN FETCH p.consultas ORDER BY p.nome")
    public Page<Paciente> findAllByPage(@NonNull Pageable pageable);

}
