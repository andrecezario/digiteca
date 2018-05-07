package persistencia;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import logica.Leitor;

public class LeitorDAO {

	private Banco banco = new Banco();

	public boolean cadastrarLeitor(Leitor l) {
		try {
			banco.abreConexao();	
				String sql = "Insert into leitor(cpf, nome, endereco, telefone, email, data_nasc) values ('"
						+ l.getCpf()
						+ "','"
						+ l.getNome()
						+ "','"
						+ l.getEndereco()
						+ "','"
						+ l.getTelefone()
						+ "','"
						+ l.getEmail()
						+ "','"
						+ l.getDataNascimento()
						+ "')";
				banco.update(sql);
				JOptionPane.showMessageDialog(null, "Leitor " + l.getNome()
						+ " cadastrado com sucesso!", "Leitor", JOptionPane.DEFAULT_OPTION);
				return true;
//			}else{
//				System.out.println("Nao e possivel cadastrar esse leitor!");
//				JOptionPane.showMessageDialog(null, "Nao eh possivel cadastrar esse leitor!","EROO", JOptionPane.ERROR_MESSAGE);
//				return false;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return false;
		} finally {
			banco.fechaConexao();
		}
	}

	public void atualizarLeitor(Leitor l) {
		try {
			banco.abreConexao();
			String sql = "Update leitor set nome='"+ l.getNome()+ "', endereco='"+ l.getEndereco() +"', telefone='"+l.getTelefone()+"',email='"+l.getEmail()+"' where cpf='"
					+ l.getCpf() + "'";
			banco.update(sql);
			System.out.println("Leitor atualizado com sucesso! " + l);
			JOptionPane.showMessageDialog(null, "Leitor atualizado com sucesso! " + l, "Leitor", JOptionPane.DEFAULT_OPTION);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		} finally {
			banco.fechaConexao();
		}
	}

	public boolean removerLeitor(String cpf) {
		try {
			banco.abreConexao();
			String sql = "Delete from leitor where cpf='" + cpf + "'";
			banco.update(sql);
			JOptionPane.showMessageDialog(null, "Leitor" + cpf + " removido com sucesso","Leitor", JOptionPane.DEFAULT_OPTION);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return false;
		} finally {
			banco.fechaConexao();
		}
	}

	public Leitor buscarLeitor(String cpf) {
		Leitor l = new Leitor();
		try {
			banco.abreConexao();
			String sql = "Select * from leitor where cpf='" + cpf + "'";
			ResultSet rs = banco.query(sql);
			while (rs.next()) {
				l.setCpf(rs.getString("cpf"));
				l.setNome(rs.getString("nome"));
				l.setEndereco(rs.getString("endereco"));
				l.setTelefone(rs.getString("telefone"));
				l.setEmail(rs.getString("email"));
				l.setDataNascimento(rs.getString("data_nasc"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return l;
	}
}
