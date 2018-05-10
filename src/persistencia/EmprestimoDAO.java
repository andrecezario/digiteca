package persistencia;

import java.sql.ResultSet;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import logica.Bibliotecario;
import logica.Categoria;
import logica.Emprestimo;
import logica.Leitor;
import logica.Livro;

public class EmprestimoDAO {

	//private ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
	private Banco banco = new Banco();
	private LivroDAO lDAO = new LivroDAO();
	private LeitorDAO leitorDAO = new LeitorDAO();
	private BibliotecarioDAO bDAO = new BibliotecarioDAO();
	int i =0;

	public boolean cadastrarEmprestimo(Emprestimo e) {
		String sql;
		ResultSet rs;
		try {
			banco.abreConexao();
			sql = "Select * from emprestimo where cpf_leitor='"
					+ e.getLeitor().getCpf() + "'";
			rs = banco.query(sql);
			if (!rs.next()) {
				sql = "Select * from livro where isbn='"+e.getLivro().getIsbn()+"'";
				rs = banco.query(sql);
				if(rs.next()){
					Livro l = lDAO.buscarLivroIsbn(rs.getString("isbn"));
					e.setLivro(l);
					if (l.getStatus().equals("Disponivel")) {
						e.getLivro().setStatus("Ocupado");
						sql = "Update livro set status='"+e.getLivro().getStatus()+"' where isbn='"+e.getLivro().getIsbn()+"'";
						banco.update(sql);
						i += e.getIdEmprestimo();

						sql = "Insert into emprestimo (id_emprestimo,data_atual, data_devolucao, isbn_livro, cpf_leitor, cpf_bibliotecario) values "
								+ "('" + i + "','" + e.getDataAtual()
								+ "','"
								+ e.getDataDevolucao()
								+ "','"
								+ e.getLivro().getIsbn()
								+ "','"
								+ e.getLeitor().getCpf()
								+ "','" + e.getBibliotecario().getCpf()+ "')";
						banco.update(sql);
						JOptionPane.showMessageDialog(null,
								"Emprestimo realizado com sucesso!", "Empréstimo",
								JOptionPane.DEFAULT_OPTION);
						return true;
					} else if (l.getStatus().equals("Ocupado")) {
						JOptionPane
								.showMessageDialog(
										null,
										"Empréstimo não realizado porque não existem cópias disponíveis do livro!",
										"ERRO", JOptionPane.ERROR_MESSAGE);
						return false;
					}
					else {
						JOptionPane.showMessageDialog(null,
								"Emprestimo não realizado porque este livro é permanente!", "ERRO",
								JOptionPane.ERROR_MESSAGE);
						return false;
					}
				
				}else{
					JOptionPane.showMessageDialog(null,
							"Não foi possível encontrar o livro!", "ERRO",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} else if (!rs.getString("cpf_leitor").equals(null)) {
				JOptionPane
						.showMessageDialog(
								null,
								"Não foi possível realizar o emprestimo, já existe um empréstimo com o cpf deste leitor no sistema!",
								"ERRO", JOptionPane.ERROR_MESSAGE);
				return false;
			} else {
				JOptionPane.showMessageDialog(null,
						"O livro ainda não foi adicionado à uma estante!",
						"ERRO", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} finally {
			banco.fechaConexao();
		}
	}

	public boolean registrarDevolucao(String cpfLeitor,
			String dataDevolucao) {
		String sql;
		try {
			banco.abreConexao();
			Emprestimo emp = this.buscarEmprestimoLeitor(cpfLeitor);
			if (emp.getLeitor().getCpf().equals(cpfLeitor)) {
				
				emp.getLivro().setStatus("Disponivel");
				banco.abreConexao();
				sql = "Update livro set status='"+emp.getLivro().getStatus()+"' where isbn='"+emp.getLivro().getIsbn()+"'";
				banco.update(sql);
				banco.abreConexao();
				sql = "Delete from emprestimo where cpf_leitor='"+cpfLeitor+"'";
				banco.update(sql);
				JOptionPane.showMessageDialog(null, "Devolução do livro "
						+ emp.getLivro().getTitulo()
						+ " realizada com sucesso!", "Devolução",
						JOptionPane.DEFAULT_OPTION);
				return true;
			/*} else if (!emp.getDataDevolucao().equals(dataDevolucao)) {
				JOptionPane
						.showMessageDialog(
								null,
								"Data de devolução não encontrada no respectivo emprestimo!",
								"ERRO", JOptionPane.ERROR_MESSAGE);
				return false;*/
			} else if (!emp.getLeitor().getCpf().equals(cpfLeitor)) {
				JOptionPane.showMessageDialog(null,
						"Cpf não encontrado no respectivo emprestimo!", "ERRO",
						JOptionPane.ERROR_MESSAGE);
				return false;
			} else {
				JOptionPane.showMessageDialog(null,
						"Empréstimo não encontrado!", "ERRO",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} finally {
			banco.fechaConexao();
		}
	}
	public ArrayList<Emprestimo> listarEmprestimos() {
		
		ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		try {
			banco.abreConexao();
			String sql = "Select * from emprestimo";
			ResultSet rs = banco.query(sql);
			while (rs.next()) {
				Emprestimo emp = new Emprestimo();
				emp.setIdEmprestimo(rs.getInt("id_emprestimo"));
				emp.setDataAtual(rs.getString("data_atual"));
				emp.setDataDevolucao(rs.getString("data_devolucao"));
				emp.setLeitor(leitorDAO.buscarLeitor(rs.getString("cpf_leitor")));
				emp.setBibliotecario(bDAO.buscarBibliotecario(rs.getString("cpf_bibliotecario")));
				emp.setLivro(lDAO.buscarLivroIsbn(rs.getString("isbn_livro")));
				emprestimos.add(emp);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro no empréstimo Dao", "ERRO",
					JOptionPane.ERROR_MESSAGE);
			return null;
		} finally {
			banco.fechaConexao();
		}
		return emprestimos;
	}

	// Sem showMessage
	public Emprestimo buscarEmprestimoLeitor(String cpfLeitor) {
		Emprestimo emp = new Emprestimo();
		try {
			banco.abreConexao();
			String sql = "Select * from emprestimo where cpf_leitor='"
					+ cpfLeitor + "'";
			ResultSet rs = banco.query(sql);
			while (rs.next()) {
				Livro l = lDAO.buscarLivroIsbn(rs.getString("isbn_livro"));
				Leitor le = leitorDAO.buscarLeitor(rs.getString("cpf_leitor"));
				Bibliotecario b = bDAO.buscarBibliotecario(rs.getString("cpf_bibliotecario"));
				emp.setIdEmprestimo(rs.getInt("id_emprestimo"));
				emp.setDataAtual(rs.getString("data_atual"));
				emp.setDataDevolucao(rs.getString("data_devolucao"));
				emp.setLivro(l);
				emp.setLeitor(le);
				emp.setBibliotecario(b);
				System.out.println(emp);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
			return null;
		} finally {
			banco.fechaConexao();
		}
		return emp;
	}

	// Sem showMessage
	public Emprestimo buscarEmprestimo(int idEmprestimo) {
		Emprestimo emp = new Emprestimo();
		try {
			banco.abreConexao();
			String sql = "Select * from emprestimo whrere id_emprestimo='"
					+ idEmprestimo + "'";
			ResultSet rs = banco.query(sql);
			while (rs.next()) {
				emp.setIdEmprestimo(rs.getInt("id_emprestimo"));
				emp.setDataAtual(rs.getString("data_atual"));
				emp.setDataDevolucao(rs.getString("data_devolucao"));
				emp.getLivro().setIsbn(rs.getString("isbn_livro"));
				emp.getLeitor().setCpf(rs.getString("cpf_leitor"));
				emp.getBibliotecario().setCpf(rs.getString("cpf_bibliotecario"));
				System.out.println(emp);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
			return null;
		} finally {
			banco.fechaConexao();
		}
		return emp;
	}

//	private boolean removerEmprestimo(int idEmprestimo) {
//		try {
//			banco.abreConexao();
//			String sql = "Delete from emprestimo where id_emprestimo='"
//					+ idEmprestimo + "'";
//			banco.update(sql);
//			JOptionPane.showMessageDialog(null,
//					"Emprestimo removido com sucesso!", "Emprestimo",
//					JOptionPane.DEFAULT_OPTION);
//			return true;
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
//					JOptionPane.ERROR_MESSAGE);
//			return false;
//		} finally {
//			banco.fechaConexao();
//		}
//	}

	public void atualizarStatusAtraso() {
		// Percorrer todos os empréstimo e comparar a data atual com a data de
		// devolução, se a data se devolução for inferior
		// a data atual, então o status do livro é atualizado para "Pendente" e
		// o status do leitor é atualizado para
		// Com pendências
	}
	
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

}
