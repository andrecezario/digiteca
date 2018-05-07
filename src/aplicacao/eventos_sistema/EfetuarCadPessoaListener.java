package aplicacao.eventos_sistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import aplicacao.FacadeAdministrador;
import aplicacao.FacadeBibliotecario;

/*
 * Essa classe eh usada para facilitar o tratamento do evento de cadastro de leitores e bibliotecarios, bem com a
 * atualizacao de dados do administrador, leitores e bibliotecarios
 */
public class EfetuarCadPessoaListener implements ActionListener{
	private FacadeAdministrador fachadaAdm = new FacadeAdministrador();
	private FacadeBibliotecario fachadaBibliotecario = new FacadeBibliotecario();
	private String tipo;
	private JTextField cpf;
	private JTextField nome;
	private JTextField datNasc;
	private JTextField endereco;
	private JTextField email;
	private JTextField telefone;
	private JTextField login;
	private JTextField senha;
	private JFrame janela;
	
	//Construtor para Leitores
	public EfetuarCadPessoaListener(JTextField cpf, JTextField nome, JTextField endereco, JTextField telefone,JTextField email, JTextField datNasc, JFrame janela) { 
		this.cpf = cpf;
		this.nome = nome;
		this.datNasc = datNasc;
		this.endereco = endereco;
		this.email = email;
		this.telefone = telefone;
		this.janela = janela;
	}
	//Construtor para Funcionarios
	public EfetuarCadPessoaListener(JTextField cpf,JTextField nome,JTextField endereco,JTextField telefone,JTextField email,JTextField datNasc, JTextField login, JTextField senha,JFrame janela) {
		this.cpf = cpf;
		this.nome = nome;
		this.datNasc = datNasc;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.janela = janela;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Eh necessario sobreescrever este evento para cadastrar ou atualizar os dados da pessoa em questao (bibliotecario, administrador, leitor)
		JOptionPane.showMessageDialog(null, "Efetua cadastro ou atualizcao de leitores ou bibliotecarios e atualizacao de dados do administrador!");
		janela.dispose();
	}
	public FacadeAdministrador getFachadaAdm() {
		return fachadaAdm;
	}

	public FacadeBibliotecario getFachadaBiblio() {
		return fachadaBibliotecario;
	}
	public FacadeBibliotecario getFachadaBibliotecario() {
		return fachadaBibliotecario;
	}
	public String getTipo() {
		return tipo;
	}
	public JTextField getCpf() {
		return cpf;
	}
	public JTextField getNome() {
		return nome;
	}
	public JTextField getDatNasc() {
		return datNasc;
	}
	public JTextField getEndereco() {
		return endereco;
	}
	public JTextField getEmail() {
		return email;
	}
	public JTextField getTelefone() {
		return telefone;
	}
	public JTextField getLogin() {
		return login;
	}
	public JTextField getSenha() {
		return senha;
	}
	public JFrame getJanela() {
		return janela;
	}
}	


	