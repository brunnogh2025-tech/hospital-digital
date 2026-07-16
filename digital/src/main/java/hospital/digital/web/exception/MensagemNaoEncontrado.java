package hospital.digital.web.exception;

public class MensagemNaoEncontrado {

    public static String porId(Long id, String entidade){
        return "O " + entidade + " com o nome " + id + " não foi encontrado.";
    }

    public static String porNome(String nome, String entidade){
        return "O " + entidade + " com o nome " + nome + " não foi encontrado.";
    }

    public static String porEmail(String email, String entidade){
        return "O " + entidade + " com o e-mail " + email + " não foi encontrado.";
    }

    public static String porTel(String tel, String entidade){
        return "O " + entidade + " com o telefone " + tel + " não foi encontrado.";
    }
}
