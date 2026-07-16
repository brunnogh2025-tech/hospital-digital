package hospital.digital.repository;

import hospital.digital.entity.paciente.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class PacienteDAOTest {

    PacienteDAO pacienteDAO;
    Paciente paciente;

    @BeforeEach
    void setup() {
        pacienteDAO = mock(PacienteDAO.class);
        paciente = new Paciente("Carlos",
                LocalDate.of(2001, 1, 1),
                "Carlos@Gmail.com",
                "+55 (12) 34566-7890",
                "Febre");
        when(pacienteDAO.findByNome("Carlos")).thenReturn(paciente);
    }
    @Test
    void shouldReturnCarlos(){
        long inicio = System.nanoTime();
        Assertions.assertEquals(pacienteDAO.findByNome("Carlos"),paciente);
        verify(pacienteDAO).findByNome("Carlos");
        long fim = System.nanoTime();
        System.out.println((fim - inicio) / 1_000_000.0 + " ms");
    }



}
