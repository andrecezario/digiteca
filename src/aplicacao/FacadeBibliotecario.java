package aplicacao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import logica.Administrador;
import logica.Bibliotecario;
import logica.Emprestimo;
import logica.Funcionario;
import logica.Leitor;
import logica.Livro;

import persistencia.BibliotecarioDAO;
import persistencia.EmprestimoDAO;
import persistencia.LeitorDAO;
import persistencia.LivroDAO;

public class FacadeBibliotecario {

	private EmprestimoDAO eDAO = new EmprestimoDAO();
	private LivroDAO lDAO = new LivroDAO();
	private LeitorDAO leitorDAO = new LeitorDAO();
	private BibliotecarioDAO bDAO = new BibliotecarioDAO();
	
	public boolean efetuarLogin(String nomeUsuario, String senhaAcesso){
		try{	
			Funcionario a = new Administrador();
			a.efetuarLogin(nomeUsuario, senhaAcesso);
			return true;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public void atualizarSenhaAcesso(String nomeUsuario, String senhaAcesso, String novaSenha){
		Funcionario a = new Administrador();
		a.atualizarSenhaAcesso(nomeUsuario, senhaAcesso, novaSenha);
	}


	public void realizarEmprestimo(String cpfLeitor, String isbn,
			String cpfBibliotecario) {
		Leitor leitor = leitorDAO.buscarLeitor(cpfLeitor);
		Bibliotecario b = bDAO.buscarBibliotecario(cpfBibliotecario);
		Livro livro = lDAO.buscarLivroIsbn(isbn);
		try {
			//leitor.setCpf(cpfLeitor);
			//b.setCpf(cpfBibliotecario);
			//livro.setIsbn(isbn);
			Date data = new Date();
			Emprestimo e = new Emprestimo(FacadeBibliotecario.data(data),
					FacadeBibliotecario.dataFormatada(data), livro, leitor, b);
			eDAO.cadastrarEmprestimo(e);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void registrarDevolucao(String cpfLeitor,
			String dataDevolucao) {
		Leitor l = leitorDAO.buscarLeitor(cpfLeitor);
		try{
			eDAO.registrarDevolucao(l.getCpf(), dataDevolucao);
		}catch(Exception ee){
			JOptionPane.showMessageDialog(null, ee.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public Emprestimo buscarEmprestimo(String cpfLeitor) {
		Emprestimo emp = new Emprestimo();
		try {
			emp = eDAO.buscarEmprestimoLeitor(cpfLeitor);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
		return emp;
	}

	public Emprestimo buscarEmprestimo(int idEmprestimo) {
		Emprestimo emp = new Emprestimo();
		try {
			emp = eDAO.buscarEmprestimo(idEmprestimo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
		return emp;
	}

	public ArrayList<Emprestimo> listarEmprestimos() {
		ArrayList<Emprestimo> listaEmprestimos = new ArrayList<Emprestimo>();
		try {
			listaEmprestimos = eDAO.listarEmprestimos();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro no facadeBiblio", "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
		return listaEmprestimos;
	}

	public ArrayList<Livro> buscarLivro(String tipoBusca, String nomeItemBusca) {
		ArrayList<Livro> listaLivros = new ArrayList<Livro>();
		try {
			listaLivros = lDAO.buscarLivro(tipoBusca, nomeItemBusca);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
		return listaLivros;
	}

	public ArrayList<Livro> buscar(ArrayList<Livro> i){
		for(Livro l: i){
			System.out.println(l);
		}
		return i;
	}
	
	
	public void cadastrarLeitor(String cpfLeitor, String nome, String endereco,
			String telefone, String email, String dataNascimento) {
		try {
			Leitor l = new Leitor();
			l.setCpf(cpfLeitor);
			l.setNome(nome);
			l.setEndereco(endereco);
			l.setTelefone(telefone);
			l.setEmail(email);
			l.setDataNascimento(dataNascimento);
			leitorDAO.cadastrarLeitor(l);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void atualizarDadosLeitor(String cpf, String nome, String endereco,
			String telefone, String email) {
		try {
			Leitor l = leitorDAO.buscarLeitor(cpf);
			l.setNome(nome);
			l.setEndereco(endereco);
			l.setTelefone(telefone);
			l.setEmail(email);
			leitorDAO.atualizarLeitor(l);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void removerLeitor(String cpfLeitor) {
		leitorDAO.removerLeitor(cpfLeitor);
	}

	public Leitor buscarLeitor(String cpfLeitor) {
		Leitor l = new Leitor();
		try {
			l = leitorDAO.buscarLeitor(cpfLeitor);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
		return l;

	}

	// O metodo abaixo so pode ser manipulado pela propria classe e pelo metodo
	// de cadastrarEmprestimo
	// Este metodo recebe uma data do sistema do etodo cadastraEmprestimo e
	// formata para o padrao de string "dia/mes/ano"
	private static String data(Date data) {
		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		return dataFormatada.format(data);
	}
	
	private static String dataFormatada(Date data){
		data.setDate((data.getDate() + 5));
		String formato = "dd/MM/yyyy";
		SimpleDateFormat dataFormatada = new SimpleDateFormat(formato);
		return dataFormatada.format(data);
	}
}
