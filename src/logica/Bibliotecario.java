package logica;

import java.util.regex.Pattern;

public class Bibliotecario extends Funcionario {

	public Bibliotecario() {
	}

	public Bibliotecario(String cpf, String nome, String endereco,
			String telefone, String email, String dataNascimento,
			String nomeUsuario, String senhaAcesso) {
		super(cpf, nome, endereco, telefone, email, dataNascimento,
				nomeUsuario, senhaAcesso);
	}

	public void setNomeUsuario(String nomeUsuario) throws Exception {
		if (nomeUsuario.startsWith("bibli")
				&& Pattern.matches("\\D\\D\\D\\D\\D-\\d\\d", nomeUsuario)) {
			super.setNomeUsuario(nomeUsuario);
		} else {
			throw new Exception(
					"O nome de usuario padrao para a categoria  LLLLL-NN (L - bibli e N - Numero)");
		}
	}

	@Override
	public String toString() {
		return "\n--------------------------------------------------------------------------------------------------------------\n"
				+ super.toString()
				+ "\n--------------------------------------------------------------------------------------------------------------";
	}

}
