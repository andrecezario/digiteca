package aplicacao;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import logica.Administrador;
import logica.Bibliotecario;
import logica.Categoria;
import logica.Estante;
import logica.Funcionario;
import logica.Livro;

import persistencia.AdministradorDAO;
import persistencia.BibliotecarioDAO;
import persistencia.CategoriaDAO;
import persistencia.EstanteDAO;
import persistencia.LivroDAO;

public class FacadeAdministrador {

	private AdministradorDAO admDAO = new AdministradorDAO();
	private BibliotecarioDAO bibliDAO = new BibliotecarioDAO();
	private CategoriaDAO catDAO = new CategoriaDAO();
	private EstanteDAO eDAO = new EstanteDAO();
	private LivroDAO livroDAO = new LivroDAO();

	public boolean efetuarLogin(String nomeUsuario, String senhaAcesso) {
		
		try {
			Funcionario a = new Administrador();
			if(a.efetuarLogin(nomeUsuario, senhaAcesso)){
				return true;
			}
			else{
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public void atualizarSenhaAcesso(String nomeUsuario, String senhaAcesso,
			String novaSenha) {
		Funcionario a = new Administrador();
		a.atualizarSenhaAcesso(nomeUsuario, senhaAcesso, novaSenha);
	}

	public void atualizarDados(String tipo, String cpf, String nome,
			 String endereco, String telefone,
			String email, String dataNascimento) {
		try {
			if (tipo.equals("Administrador")) {
				Administrador a = new Administrador();
				a.setCpf(cpf);
				a.setNome(nome);
				a.setDataNascimento(dataNascimento);
				a.setEndereco(endereco);
				a.setTelefone(telefone);
				a.setEmail(email);
				admDAO.atualizarDados(a);
			} else if (tipo.equals("Bibliotecario")) {
				Bibliotecario b = new Bibliotecario();
				b.setCpf(cpf);
				b.setNome(nome);
				b.setEndereco(endereco);
				b.setTelefone(telefone);
				b.setEmail(email);
				bibliDAO.atualizarDados(b);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void cadastrarBibliotecario(String cpf, String nome,
			String endereco, String telefone, String email,
			String dataNascimento, String nomeUsuario, String senhaAcesso) {
		try{	
			Bibliotecario b = new Bibliotecario();
			b.setCpf(cpf);
			b.setNome(nome);
			b.setEndereco(endereco);
			b.setTelefone(telefone);
			b.setEmail(email);
			b.setDataNascimento(dataNascimento);
			b.setNomeUsuario(nomeUsuario);
			b.setSenhaAcesso(senhaAcesso);
			bibliDAO.cadastrarBibliotecario(b);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}


	public void removerBibliotecario(String cpf) {
		try{
			bibliDAO.removerBibliotecario(cpf);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public Funcionario buscarFuncionario(String cpf, String tipo) {
		Funcionario f = null;
		try {
			if (tipo.equals("Administrador")) {
				f = admDAO.buscarAdministrador(cpf);
			} else if (tipo.equals("Bibliotecario")) {
				f = bibliDAO.buscarBibliotecario(cpf);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return f;
	}

	public Administrador buscarAdministradorLogin(String nomeUsuario){
		Administrador a = new Administrador();
		try {
			a = admDAO.buscarAdministradorLogin(nomeUsuario);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
		return a;
	}
	
	public void cadastrarLivro(String titulo, String descricaoCategoria,
			String tipo, String isbn, String autor, int numeroEdicao,
			int numeroPaginas) {
		Livro l = new Livro();
		Categoria c = catDAO.buscarCategoria(descricaoCategoria);
		try{
			l.setTitulo(titulo);
			l.setCategoria(c);
			l.setTipo(tipo);
			l.setIsbn(isbn);
			l.setAutor(autor);
			l.setNumeroEdicao(numeroEdicao);
			l.setNumeroPaginas(numeroPaginas);
			livroDAO.cadastrarLivro(l);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void removerLivro(String isbn) {
		livroDAO.removerLivro(isbn);
	}

	public Livro buscarLivroIsbn(String nomeItemBusca) {
		Livro l = new Livro();
		try {
			l = livroDAO.buscarLivroIsbn(nomeItemBusca);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
		return l;
	}

	public void adicionarLivroEstante(String isbn,String idEstante) {
		Livro l = livroDAO.buscarLivroIsbn(isbn);
		try{
			if(l.getEstante().getIdEstante().equals(null)){
				eDAO.adicionarLivroEstante(isbn, idEstante);
			}else{
				JOptionPane.showMessageDialog(null, "O livro já está associado a uma estante!", "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
			System.out.println("AQUI");
		}
	}

	public void removerLivroEstante(String isbn,String idEstante) {
		Livro l = livroDAO.buscarLivroIsbn(isbn);
		try{
			if(l.getEstante().getIdEstante().equals(null)){
				JOptionPane.showMessageDialog(null, "Voce está passando um livro que não está associado a uma estante", "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}else{
				eDAO.removerLivroEstante(l.getIsbn(), idEstante);
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Não foi possivel remover : " + e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void cadastrarEstante(String idEstante, String descricaoCategoria) {
		Estante e = new Estante();
		Categoria c = catDAO.buscarCategoria(descricaoCategoria);
		try{	
			e.setIdEstante(idEstante);
			e.setCategoria(c);
			eDAO.cadastrarEstante(e);
		}catch(Exception e2){
			JOptionPane.showMessageDialog(null, e2.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void removerEstante(String idEstante) {
		try {
			Estante e = eDAO.buscarEstante(idEstante);
			e.setIdEstante(idEstante);
			if(e.getIdEstante().equals(null)){
				JOptionPane.showMessageDialog(null, "Não existe essa estante!", "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}else{
				eDAO.removerEstante(e.getIdEstante());
			}
		} catch (Exception ee) {
			JOptionPane.showMessageDialog(null, ee.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public Estante buscarEstante(String idEstante) {
		Estante es = new Estante();
		try {
			es = eDAO.buscarEstante(idEstante);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return es;
	}

	public void limparEstante(String idEstante) {
		try{
			eDAO.limparEstante(idEstante);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public ArrayList<Livro> listarLivrosEstante(String idEstante) {
		ArrayList<Livro> listaLivrosEstante = new ArrayList<Livro>();
		try {
			listaLivrosEstante = eDAO.listarLivrosEstante("idEstante");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
		return listaLivrosEstante;
	}
}
