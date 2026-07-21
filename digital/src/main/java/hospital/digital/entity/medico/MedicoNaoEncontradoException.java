package hospital.digital.entity.medico;

import hospital.digital.web.exception.EntidadeNaoEncontradaException;

public class MedicoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public MedicoNaoEncontradoException(Long id) {
        super("O médico com o id " + id + " não foi encontrado.");
    }
}
