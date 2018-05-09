package persistencia;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import logica.Categoria;
import logica.Estante;
import logica.Livro;

import persistencia.CategoriaDAO;
import persistencia.EstanteDAO;
import persistencia.LivroDAO;

public class EstanteDAO {

	private Banco banco = new Banco();
	private ArrayList<Livro> livrosEstante = new ArrayList<Livro>();
	private CategoriaDAO cDAO = new CategoriaDAO();
    //public LivroDAO livroDAO = new LivroDAO();

	public boolean adicionarLivroEstante(String isbn, String idEstante) {
		String sql;
		Livro l = new Livro();
		try {
			banco.abreConexao();
			sql = "Select * from livro where isbn='" + isbn + "'";
			ResultSet rs = banco.query(sql);
			if (rs.next()) {
				Categoria c = cDAO.buscarCategoriaId(rs.getString("id_categoria"));
				Estante e = this.buscarEstante(idEstante);
				e.setIdEstante(idEstante);
				l.setTitulo(rs.getString("titulo"));
				l.setCategoria(c);
				l.setTipo(rs.getString("tipo"));
				l.setAutor(rs.getString("autor"));
				l.setNumeroEdicao(rs.getInt("edicao"));
				l.setNumeroPaginas(rs.getInt("num_paginas"));
				l.setEstante(e);
				l.setStatus(rs.getString("status"));
				if (e.getCategoria().getIdCategoria().equals(l.getCategoria().getIdCategoria())) {
					l.setEstante(e);
					banco.abreConexao();
					sql = "Update livro set id_estante='"+l.getEstante().getIdEstante()+"' where isbn='"+isbn+"'";
					banco.update(sql);
					livrosEstante.add(l);
					l.setTitulo(rs.getString("titulo"));
					JOptionPane.showMessageDialog(null, "Livro " + l.getTitulo() + " adicionado com sucesso à estante " + idEstante , "Estante",JOptionPane.DEFAULT_OPTION);
					return true;
				} 
				else {
				JOptionPane.showMessageDialog(null, "Não foi possível adicionar o livro, porque ele não pertence a categoria da estante!", "ERRO", JOptionPane.ERROR_MESSAGE);
				return false;}
			}else{
				System.out.println("nao foi");
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + "NAO FOI", "ERRO", JOptionPane.ERROR_MESSAGE);
			return false;
		} finally {
			banco.fechaConexao();
		}
	}
	
//	
//	
//	public boolean adicionarLivroEstante(String isbn, String idEstante){
//		String sql;
//		Livro l = new Livro();
//		Estante e = this.buscarEstante(idEstante);
//		try {
//			banco.abreConexao();
//			sql = "Select * from livro where isbn='"+isbn+"'"; 
//			ResultSet rs = banco.query(sql);
//			if(rs.next()){
//				if(e.getCategoria().getIdCategoria().equals(l.getCategoria().getIdCategoria())){
//					l.setEstante(e);
//					banco.abreConexao();
//					sql = "Update livro set id_estante='"+l.getCategoria().getIdCategoria()+"' where isbn='"+isbn+"'";
//					banco.update(sql);
//					livrosEstante.add(l);
//					return true;
//				}else{
//					JOptionPane.showMessageDialog(null, "Voce não pode adicionar esse livro nessa estante, porque as categorias sao diferentes!", "ERRO", JOptionPane.ERROR_MESSAGE);
//					return false;
//				}
//			}else{
//				JOptionPane.showMessageDialog(null, "Livro não encontrado!", "ERRO", JOptionPane.ERROR_MESSAGE);
//				return false;
//			}
//			
//		} catch (Exception ee) {
//			JOptionPane.showMessageDialog(null, ee.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
//			return false;
//		}finally{
//			banco.fechaConexao();
//		}
//	}
//
//	// Este método busca o livro de acordo com o isbn passado, depois verifica
//	// se este livro possui o id da estante
//	// que desejo remover, se sim, o id agora passa a ser null e removo este
//	// livro da lista de livros da estante
	public boolean removerLivroEstante(String isbn,String idEstante) {
		try {
			banco.abreConexao();
			LivroDAO lDAO = new LivroDAO();
			Livro l = lDAO.buscarLivroIsbn(isbn);
			Estante e = this.buscarEstante(idEstante);
			if (l.getEstante().getIdEstante().equals(e.getIdEstante())) {
				l.setEstante(null);
				banco.abreConexao();
				String sql = "Update livro set id_estante=null where isbn='"+isbn+"'";
				banco.update(sql);
				livrosEstante.remove(l);
				JOptionPane.showMessageDialog(null,"Livro " + isbn + " removido com sucesso da estante " + idEstante, "Estante", JOptionPane.DEFAULT_OPTION);
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "O livro informado não foi encontrado na estante!", "ERRO", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return false;
		} finally {
			banco.fechaConexao();
		}
	}

	public boolean cadastrarEstante(Estante estante) {
		String sql;
		try {
			banco.abreConexao();
			sql = "Select * from estante where id='"+estante.getIdEstante()+"'";
			ResultSet rs = banco.query(sql);
			if(!rs.next()){
				sql = "Insert into estante (id, id_categoria) values ('"
						+ estante.getIdEstante() + "','"
						+ estante.getCategoria().getIdCategoria() + "')";
				banco.update(sql);
				JOptionPane.showMessageDialog(null,"Estante " + estante.getIdEstante()
						+ " cadastrado com sucesso!", "Estante", JOptionPane.DEFAULT_OPTION);
				return true;
			}else{
				JOptionPane.showMessageDialog(null,"Esse id já existe no sistema!", "ERRO", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return false;
		} finally {
			banco.fechaConexao();
		}
	}

	public boolean removerEstante(String id) {
		String sql;
		Estante e = this.buscarEstante(id);
		try {
			banco.abreConexao();
			e.setIdEstante(id);
			sql = "Select * from estante where id='"+e.getIdEstante()+"'";
			ResultSet rs = banco.query(sql);
			if(rs.next()){
				banco.abreConexao();
				sql = "Delete from estante where id='" + e.getIdEstante() + "'";
				banco.update(sql);
				JOptionPane.showMessageDialog(null, "Estante " + id + " removida com sucesso!", "Estante", JOptionPane.DEFAULT_OPTION);
				return true;
			}else{
				JOptionPane.showMessageDialog(null, "Não existe essa estante no sistema!", "ERRO", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (Exception ee) {
			JOptionPane.showMessageDialog(null, ee.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return false;
		} finally {
			banco.fechaConexao();
		}
	}

	public void limparEstante(String idEstante) {
		try {
			Livro l = new Livro();
			banco.abreConexao();
			String sql = "Select * from livro where id_estante='" + idEstante
					+ "'";
			ResultSet rs = banco.query(sql);
			while (rs.next()) {
				this.removerLivroEstante(rs.getString("isbn"), idEstante);
			}
			JOptionPane.showMessageDialog(null, "A limpeza foi realizada com sucesso!", "Estante", JOptionPane.DEFAULT_OPTION);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		} finally {
			banco.fechaConexao();
		}
	}

	//Sem showMessage
	public Estante buscarEstante(String idEstante) {
		Estante e = new Estante();
		try {
			banco.abreConexao();
			String sql = "Select id, id_categoria from estante where id='" + idEstante + "'";
			ResultSet rs = banco.query(sql);
			while (rs.next()) {
				Categoria c = cDAO.buscarCategoriaId(rs.getString("id_categoria"));
				e.setIdEstante(rs.getString("id"));
				e.setCategoria(c);
				System.out.println(e);
			}
		} catch (Exception ee) {
			JOptionPane.showMessageDialog(null, ee.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return null;
		} finally {
			banco.fechaConexao();
		}
		return e;
	}
	//Sem showMessage
	public ArrayList<Livro> listarLivrosEstante(String id) {
		
		ArrayList<Livro> listaLivros = new ArrayList<Livro>();
		try {
			banco.abreConexao();
			String sql = "Select * from livro where id_estante='" + id
					+ "'";
			ResultSet rs = banco.query(sql);
			while (rs.next()) {
				Categoria c = cDAO.buscarCategoriaId(rs
						.getString("id_categoria"));
				Estante e = this.buscarEstante(rs.getString("id_estante"));
				Livro l = new Livro();
				l.setTitulo(rs.getString("titulo"));
				l.setCategoria(c);
				l.setTipo(rs.getString("tipo"));
				l.setIsbn(rs.getString("isbn"));
				l.setAutor(rs.getString("autor"));
				l.setNumeroEdicao(rs.getInt("edicao"));
				l.setNumeroPaginas(rs.getInt("num_paginas"));
				l.setStatus(rs.getString("status"));
				l.setEstante(e);
				listaLivros.add(l);
				System.out.println(l);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
			return null;
		} finally {
			banco.fechaConexao();
		}
		
		return listaLivros;
	}

}
