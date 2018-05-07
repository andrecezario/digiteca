//Terminado, falta testar
package persistencia;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import logica.Bibliotecario;

public class BibliotecarioDAO {

	private Banco banco = new Banco();

	public boolean cadastrarBibliotecario(Bibliotecario b) {
		String sql;
		try {
			banco.abreConexao();
					sql = "Select nome_usuario from funcionario where nome_usuario='"
							+ b.getNomeUsuario() + "'";
					ResultSet rs = banco.query(sql);
					 if (!rs.next()){
						b.setTipo("Bibliotecario");
						sql = "insert into funcionario (cpf, nome, endereco, telefone, email, data_nasc, nome_usuario, senha_acesso, tipo) values('"
								+ b.getCpf()
								+ "','"
								+ b.getNome()
								+ "','"
								+ b.getEndereco()
								+ "','"
								+ b.getTelefone()
								+ "','"
								+ b.getEmail()
								+ "','"
								+ b.getDataNascimento()
								+ "','"
								+ b.getNomeUsuario()
								+ "','"
								+ b.getSenhaAcesso() + "', '" + b.getTipo() + "')";
						JOptionPane.showMessageDialog(null,
								"Cadastro do bibliotecario " + b.getNome()
										+ " realizado com sucesso!",
								"Bibliotecario", JOptionPane.DEFAULT_OPTION);
						banco.update(sql);
						return true;
					}else  {
						JOptionPane.showMessageDialog(null,
								"O nome de usuario ja existe no sistema!", "ERRO",
								JOptionPane.ERROR_MESSAGE);
						return false;}
				
			// }else if(!rs.next() && rs1.next()){
			// JOptionPane.showMessageDialog(null,
			// "O cpf já existe no sistema!", "ERRO",
			// JOptionPane.ERROR_MESSAGE);
			// resultado = false;}
			// }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} finally {
			banco.fechaConexao();
		}
	}

	public boolean removerBibliotecario(String cpf) {
		try {
			banco.abreConexao();
			String sql = "Delete from funcionario where cpf='" + cpf + "'";
			banco.update(sql);
			JOptionPane.showMessageDialog(null, "Bibliotecário " + cpf
					+ " removido com sucesso!", "Bibliotecário",
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

	// Sem showMessage
	public Bibliotecario buscarBibliotecario(String cpf) {
		Bibliotecario b = new Bibliotecario();
		try {
			banco.abreConexao();
			String sql = "Select * from funcionario where cpf='" + cpf + "'";
			ResultSet rs = banco.query(sql);
			if(rs.next()) {
				b.setCpf(rs.getString("cpf"));
				b.setNome(rs.getString("nome"));
				b.setEndereco(rs.getString("endereco"));
				b.setTelefone(rs.getString("telefone"));
				b.setEmail(rs.getString("email"));
				b.setDataNascimento(rs.getString("data_nasc"));
				b.setNomeUsuario(rs.getString("nome_usuario"));
				b.setSenhaAcesso(rs.getString("senha_acesso"));
				b.setTipo(rs.getString("tipo"));
				System.out.println(b);
			}else{
				JOptionPane.showMessageDialog(null, "Este cpf nao existe no sistema!", "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			banco.fechaConexao();
		}
		return b;
	}

	public void atualizarDados(Bibliotecario b) {
		String sql;
		try {
			banco.abreConexao();
			sql = "Update funcionario set nome='" + b.getNome()
					+ "',endereco='" + b.getEndereco() + "',telefone='"
					+ b.getTelefone() + "',email='" + b.getEmail()
					+ "' where cpf='" + b.getCpf() + "'";
			banco.update(sql);
			sql = "Select data_nasc, tipo from funcionario where cpf='"
					+ b.getCpf() + "'";
			ResultSet rs = banco.query(sql);
			while (rs.next()) {
				b.setDataNascimento(rs.getString("data_nasc"));
				b.setTipo(rs.getString("tipo"));
			}
			JOptionPane.showMessageDialog(null,
					"Bibliotecario atualizado com sucesso!" + b,
					"Bibliotecario", JOptionPane.DEFAULT_OPTION);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			banco.fechaConexao();
		}
	}

}
