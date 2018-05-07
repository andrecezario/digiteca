package persistencia;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import logica.Categoria;

public class CategoriaDAO {
	
	private Banco banco = new Banco();
	
	public Categoria buscarCategoria(String descricao){
		Categoria c = new Categoria();
		try {
			banco.abreConexao();
			String sql = "Select * from categoria where descricao='"+ descricao + "'";
			ResultSet rs = banco.query(sql);
			while(rs.next()){
				c.setIdCategoria(rs.getString("id"));
				c.setDescricao(rs.getString("descricao"));
				//System.out.println(c);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return c;
	}

	public Categoria buscarCategoriaId(String id){
		Categoria c = new Categoria();
		try {
			banco.abreConexao();
			String sql = "Select * from categoria where id='"+ id + "'";
			ResultSet rs = banco.query(sql);
			while(rs.next()){
				c.setIdCategoria(rs.getString("id"));
				c.setDescricao(rs.getString("descricao"));
				//System.out.println(c);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return c;
	}
	
}
