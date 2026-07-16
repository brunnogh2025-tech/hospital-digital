package hospital.digital.entity.paciente;

import hospital.digital.web.exception.EntityNotFoundException;
import hospital.digital.web.exception.MensagemNaoEncontrado;

public class PacienteNaoEncontradoException extends EntityNotFoundException {
    private PacienteNaoEncontradoException(String msg) {
        super(msg);
    }

    public static PacienteNaoEncontradoException porId(Long id){
        return new PacienteNaoEncontradoException(MensagemNaoEncontrado.porId(id,"paciente"));
    }

    public static PacienteNaoEncontradoException porNome(String nome){
        return new PacienteNaoEncontradoException(MensagemNaoEncontrado.porNome(nome,"paciente"));
    }

    public static PacienteNaoEncontradoException porEmail(String email){
        return new PacienteNaoEncontradoException(MensagemNaoEncontrado.porEmail(email,"paciente"));
    }

    public static PacienteNaoEncontradoException porTel(String tel){
        return new PacienteNaoEncontradoException(MensagemNaoEncontrado.porTel(tel,"paciente"));
    }
}
