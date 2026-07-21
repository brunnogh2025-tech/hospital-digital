package hospital.digital.entity.consulta;

import hospital.digital.web.exception.EntidadeNaoEncontradaException;

public class ConsultaNaoEncontradaException extends EntidadeNaoEncontradaException {
    public ConsultaNaoEncontradaException(Long id) {
        super("A consulta com o id " + id + " não foi encontrada.");
    }
}
