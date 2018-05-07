package logica;

public class Leitor extends Pessoa {
	
	public Leitor() {
		super();
	}

	public Leitor(String cpf, String nome, String endereco, String telefone,
			String email, String dataNascimento) {
		super(cpf, nome, endereco, telefone, email, dataNascimento);
	}

	@Override
	public String toString() {
		return super.toString() + "\n";
	}

	
	
}
