package logica;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import persistencia.Banco;

public abstract class Funcionario extends Pessoa {

	private String nomeUsuario;
	private String senhaAcesso;
	private String tipo;
	private Banco banco = new Banco();

	public Funcionario() {
	}

	public Funcionario(String cpf, String nome, String endereco,
			String telefone, String email, String dataNascimento,
			String nomeUsuario, String senhaAcesso) {
		super(cpf, nome, endereco, telefone, email, dataNascimento);
		this.nomeUsuario = nomeUsuario;
		this.senhaAcesso = senhaAcesso;
	}
	
	public boolean efetuarLogin(String nomeUsuario, String senhaAcesso) {
		try {
			banco.abreConexao();
			String sql = "Select * from funcionario where nome_usuario ='" +nomeUsuario + "' and senha_acesso = '"+senhaAcesso+"'";
			ResultSet rs = banco.query(sql);
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "Usuario autenticado com sucesso!");
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Nao foi possivel efetuar o login, usuario ou senha incorretos!", "ERRO", JOptionPane.ERROR_MESSAGE);
				return false;
				}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return false;
		} finally {
			banco.fechaConexao();
		}
	}
	
	public void atualizarSenhaAcesso(String nomeUsuario, String senhaAcesso,
			String novaSenha) {
		String sql;
		try {
			banco.abreConexao();
			if (nomeUsuario.equals("admin")) {
				sql = "Select senha_acesso from funcionario where senha_acesso='"+senhaAcesso+"'";
				ResultSet rs = banco.query(sql);
				if(rs.next()){
					sql = "Update funcionario set senha_acesso='"
							+ novaSenha + "' where nome_usuario='" + nomeUsuario
							+ "' and senha_acesso='" + senhaAcesso + "'";
					banco.update(sql);
					JOptionPane.showMessageDialog(null, "Senha alterada com sucesso! Sua nova senha eh: "
							+ novaSenha, "Administrador", JOptionPane.DEFAULT_OPTION);
				}else{
					JOptionPane.showMessageDialog(null, "Senha invalida" ,"Administrador - ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
			} else if (nomeUsuario.startsWith("bibli")) {
				sql = "Select senha_acesso from funcionario where senha_acesso='"+senhaAcesso+"'";
				ResultSet rs = banco.query(sql);
				if(rs.next()){
					sql = "Update funcionario set senha_acesso='"
							+ novaSenha + "' where nome_usuario='" + nomeUsuario
							+ "' and senha_acesso='" + senhaAcesso + "'";
					banco.update(sql);
					JOptionPane.showMessageDialog(null, "Senha alterada com sucesso! Sua nova senha eh: "
							+ novaSenha, "Bibliotecario", JOptionPane.DEFAULT_OPTION);
				}else{
					JOptionPane.showMessageDialog(null, "Senha invalida" ,"Bibliotecario - ERRO", JOptionPane.ERROR_MESSAGE);
				}

			}else{
				JOptionPane.showMessageDialog(null, "Funcionario nao encontrado!", "ERRO", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		} finally {
			banco.fechaConexao();
		}
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) throws Exception {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenhaAcesso() {
		return senhaAcesso;
	}

	public void setSenhaAcesso(String senhaAcesso) {
		this.senhaAcesso = senhaAcesso;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nTipo : " + tipo;
	}

}
