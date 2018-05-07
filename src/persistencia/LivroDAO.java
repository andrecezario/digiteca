package persistencia;

//Terminado, falta testar
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import logica.Categoria;
import logica.Estante;
import logica.Livro;

public class LivroDAO {

	private Banco banco = new Banco();
	private ArrayList<Livro> listaLivros = new ArrayList<Livro>();
	private CategoriaDAO cDAO = new CategoriaDAO();
	private EstanteDAO eDAO = new EstanteDAO();

	public boolean cadastrarLivro(Livro l) {
		String sql;
		try {
			banco.abreConexao();
			sql = "Select * from livro where titulo='"+l.getTitulo()+"'";
			ResultSet rs = banco.query(sql);
			if (rs.next()) {
				Categoria c = cDAO.buscarCategoriaId(rs
						.getString("id_categoria"));
				l.setTitulo(rs.getString("titulo"));
				l.setCategoria(c);
				l.setTipo(rs.getString("tipo"));
				l.setAutor(rs.getString("autor"));
				l.setNumeroEdicao(rs.getInt("edicao"));
				l.setNumeroPaginas(rs.getInt("num_paginas"));
				l.setStatus("Disponivel");
				sql = "Insert into livro (titulo, id_categoria, tipo, isbn, autor, edicao, num_paginas, status) "
						+ "values('"
						+ l.getTitulo()
						+ "','"
						+ l.getCategoria().getIdCategoria()
						+ "','"
						+ l.getTipo()
						+ "','"
						+ l.getIsbn()
						+ "','"
						+ l.getAutor()
						+ "','"
						+ l.getNumeroEdicao()
						+ "','"
						+ l.getNumeroPaginas() + "','" + l.getStatus() + "')";
				banco.update(sql);
				JOptionPane.showMessageDialog(null, "Livro " + l.getTitulo()
						+ " cadastrado com sucesso!", "Livro",
						JOptionPane.DEFAULT_OPTION);
				return true;

			} else if(!rs.next()){
				l.setStatus("Permanente");
				sql = "Insert into livro (titulo, id_categoria, tipo, isbn, autor, edicao, num_paginas, status) "
						+ "values('"
						+ l.getTitulo()
						+ "','"
						+ l.getCategoria().getIdCategoria()
						+ "','"
						+ l.getTipo()
						+ "','"
						+ l.getIsbn()
						+ "','"
						+ l.getAutor()
						+ "','"
						+ l.getNumeroEdicao()
						+ "','"
						+ l.getNumeroPaginas() + "','" + l.getStatus() + "')";
				banco.update(sql);
				JOptionPane.showMessageDialog(null, "Livro " + l.getTitulo()
						+ " cadastrado com sucesso!", "Livro",
						JOptionPane.DEFAULT_OPTION);
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null,
						"O livro não foi cadastrado!", "Livro",
						JOptionPane.DEFAULT_OPTION);
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} finally {
			banco.fechaConexao();
		}
	}

	public boolean removerLivro(String isbn) {
		try {
			banco.abreConexao();
			String sql = "Delete from livro where isbn='" + isbn + "'";
			banco.update(sql);
			JOptionPane.showMessageDialog(null, "Livro " + isbn
					+ " removido com sucesso!", "Livro",
					JOptionPane.DEFAULT_OPTION);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} finally {
			banco.fechaConexao();
		}
	}

	public ArrayList<Livro> buscarLivro(String tipoBusca, String nomeItemBusca) {
		Livro l = new Livro();
		try {
			banco.abreConexao();
			if (tipoBusca.equals("Titulo")) {
				listaLivros = this.buscarLivroTitulo(nomeItemBusca);
			} else if (tipoBusca.equals("Status")) {
				listaLivros = this.buscarLivroStatus(nomeItemBusca);
			} else if (tipoBusca.equals("Autor")) {
				listaLivros = this.buscarLivroAutor(nomeItemBusca);
			} else if (tipoBusca.equals("Id")) {
				listaLivros = this.buscarLivroIdEstante(nomeItemBusca);
			} else {
				JOptionPane.showMessageDialog(null, "Livro nao encontrado",
						"ERRO", JOptionPane.ERROR_MESSAGE);
				return null;
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

	// Sem showMessage
	public Livro buscarLivroIsbn(String isbn) {
		Livro l = new Livro();
		try {
			banco.abreConexao();
			String sql = "Select * from livro where ISBN ='" + isbn + "'";
			ResultSet rs = banco.query(sql);
			if (rs.next()) {
				Categoria c = cDAO.buscarCategoriaId(rs
						.getString("id_categoria"));
				Estante e = eDAO.buscarEstante(rs.getString("id_estante"));
				l.setTitulo(rs.getString("titulo"));
				l.setCategoria(c);
				l.setTipo(rs.getString("tipo"));
				l.setIsbn(rs.getString("isbn"));
				l.setAutor(rs.getString("autor"));
				l.setNumeroEdicao(rs.getInt("edicao"));
				l.setNumeroPaginas(rs.getInt("num_paginas"));
				l.setStatus(rs.getString("status"));
				l.setEstante(e);
				System.out.println(l);
			} else {
				JOptionPane.showMessageDialog(null,
						"Nao foi possivel encontrar o livro!", "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
			return null;
		} finally {
			banco.fechaConexao();
		}
		return l;
	}

	// Sem showMessage
	private ArrayList<Livro> buscarLivroStatus(String status) {
		try {
			banco.abreConexao();
			String sql = "Select * from livro where status='" + status + "'";
			ResultSet rs = banco.query(sql);
			while (rs.next()) {
				Livro l = new Livro();
				Categoria c = cDAO.buscarCategoriaId(rs
						.getString("id_categoria"));
				Estante e = eDAO.buscarEstante(rs.getString("id_estante"));
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

	// Sem showMessage
	private ArrayList<Livro> buscarLivroTitulo(String titulo) {
		try {
			banco.abreConexao();
			String sql = "Select * from livro where titulo='" + titulo + "'";
			ResultSet rs = banco.query(sql);
			while (rs.next()) {
				Livro l = new Livro();
				Categoria c = cDAO.buscarCategoriaId(rs
						.getString("id_categoria"));
				Estante e = eDAO.buscarEstante(rs.getString("id_estante"));
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

	// Sem showMessage
	private ArrayList<Livro> buscarLivroAutor(String autor) {
		try {
			banco.abreConexao();
			String sql = "Select * from livro where autor='" + autor + "'";
			ResultSet rs = banco.query(sql);
			while (rs.next()) {
				Livro l = new Livro();
				Categoria c = cDAO.buscarCategoriaId(rs
						.getString("id_categoria"));
				Estante e = eDAO.buscarEstante(rs.getString("id_estante"));
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

	// Sem showMessage
	private ArrayList<Livro> buscarLivroIdEstante(String idEstante) {
		Livro l = new Livro();
		try {
			banco.abreConexao();
			String sql = "Select * from livro where id_estante='" + idEstante
					+ "'";
			ResultSet rs = banco.query(sql);
			while (rs.next()) {
				Categoria c = cDAO.buscarCategoriaId(rs
						.getString("id_categoria"));
				Estante e = eDAO.buscarEstante(rs.getString("id_estante"));
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
