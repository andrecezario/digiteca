package logica;

import java.util.regex.Pattern;

public abstract class Pessoa {
	
	private String cpf;
	private String nome;
	private String endereco;
	private String telefone;
	private String email;
	private String dataNascimento;
	
	public Pessoa(){}
	public Pessoa(String cpf, String nome, String endereco, String telefone,
			String email, String dataNascimento) {
		this.cpf = cpf;
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) throws Exception{
		if(Pattern.matches("\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d", cpf)){	
			this.cpf = cpf;
		}else{
			throw new Exception("O cpf nao foi digitado corretamente! (Obs. Formato padrao NNN.NNN.NNN-NN, onde N - Numero) ");
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) throws Exception{
		if(Pattern.matches("\\d\\d \\d\\d\\d\\d-\\d\\d\\d\\d", telefone) || Pattern.matches("\\d\\d \\d\\d\\d\\d\\d-\\d\\d\\d\\d", telefone)){
			this.telefone = telefone;
		}else{
			throw new Exception("Formato de telefone invalido! (Obs. NN NNNN-NNNN ou NN NNNNN-NNNN, onde N : Numero)");
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) throws Exception{
		if(Pattern.matches("\\d\\d/\\d\\d/\\d\\d\\d\\d", dataNascimento)){
			this.dataNascimento = dataNascimento;
		}else{
			throw new Exception("Data de nascimento incorreta! (Obs. Formato padrao: NN/NN/NNNN, onde N: Numero)");
		}
	}

	@Override
	public String toString() {
		return "Cpf : " + cpf + " | Nome: " + nome + " | Data de Nascimento : " + dataNascimento + "\nEndereco : "
				+ endereco + "\nTelefone : " + telefone + "\nEmail: " + email;
	}
	
	

}
