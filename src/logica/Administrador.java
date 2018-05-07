package logica;

import java.util.regex.Pattern;

public class Administrador extends Funcionario {

	public Administrador() {
	}

	public Administrador(String cpf, String nome, String endereco,
			String telefone, String email, String dataNascimento,
			String nomeUsuario, String senhaAcesso) {
		super(cpf, nome, endereco, telefone, email, dataNascimento,
				nomeUsuario, senhaAcesso);
	}

	public void setNomeUsuario(String nomeUsuario) throws Exception {
		if (nomeUsuario.equals("admin")
				&& Pattern.matches("\\D\\D\\D\\D\\D", nomeUsuario)) {
			super.setNomeUsuario(nomeUsuario);
		} else {
			throw new Exception(
					"O nome de usuário padrão para a categoria é LLLLL (L - admin)");
		}
	}

	@Override
	public String toString() {
		return "--------------------------------------------------------------------------------------------------------------\n"
				+ super.toString()
				+ "\n--------------------------------------------------------------------------------------------------------------";
	}

}
