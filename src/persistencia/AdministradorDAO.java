//Funcionando, falta testar
package persistencia;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import logica.Administrador;

public class AdministradorDAO {

	private Banco banco = new Banco();

	public void atualizarDados(Administrador a) {
		try {
			banco.abreConexao();
			String sql = "Update funcionario set cpf='"+a.getCpf()+"',nome='"+a.getNome() + "',endereco='"+a.getEndereco()+ "',telefone='"+a.getTelefone()+"',email='"+a.getEmail()+"',data_nasc='"+ a.getDataNascimento() + "'where nome_usuario='admin'";
			banco.update(sql);
			JOptionPane.showMessageDialog(null, "Administrador atualizado com sucesso!\n\n" + a, "Administrador", JOptionPane.DEFAULT_OPTION);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		} finally {
			banco.fechaConexao();
		}
	}

	//Sem showMessage
	public Administrador buscarAdministrador(String cpf) {
		Administrador a = new Administrador();
		try {
			banco.abreConexao();
			String sql = "Select * from funcionario where cpf='" + cpf + "'";
			ResultSet rs = banco.query(sql);
			if (rs.next()) {
				a.setCpf(rs.getString("cpf"));
				a.setNome(rs.getString("nome"));
				a.setEndereco(rs.getString("endereco"));
				a.setTelefone(rs.getString("telefone"));
				a.setEmail(rs.getString("email"));
				a.setDataNascimento(rs.getString("data_nasc"));
				a.setNomeUsuario(rs.getString("nome_usuario"));
				a.setSenhaAcesso(rs.getString("senha_acesso"));
				a.setTipo(rs.getString("tipo"));
				System.out.println(a);
			}else{
				JOptionPane.showMessageDialog(null, "Este cpf não existe no sistema!", "ERRO", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return null;
		} finally {
			banco.fechaConexao();
		}
		return a;
	}
	
	public Administrador buscarAdministradorLogin(String nomeUsuario) {
		Administrador a = new Administrador();
		try {
			banco.abreConexao();
			String sql = "Select * from funcionario where nome_usuario='" + nomeUsuario + "'";
			ResultSet rs = banco.query(sql);
			while (rs.next()) {
				a.setCpf(rs.getString("cpf"));
				a.setNome(rs.getString("nome"));
				a.setEndereco(rs.getString("endereco"));
				a.setTelefone(rs.getString("telefone"));
				a.setEmail(rs.getString("email"));
				a.setDataNascimento(rs.getString("data_nasc"));
				a.setNomeUsuario(rs.getString("nome_usuario"));
				a.setSenhaAcesso(rs.getString("senha_acesso"));
				a.setTipo(rs.getString("tipo"));
				System.out.println(a);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return null;
		} finally {
			banco.fechaConexao();
		}
		return a;
	}

}
