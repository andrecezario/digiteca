package persistencia;
//Finalizada
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Banco {
	
	private Connection conexao;
	private Statement stm;
	
	public void abreConexao(){
		try{
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/DigiTeca", "root", "andre");
			stm = conexao.createStatement();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void fechaConexao(){
		try {
			stm.close();
			conexao.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ResultSet query(String sql){
		try {
			return stm.executeQuery(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void update(String sql){
		try{
			stm.executeUpdate(sql);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
}
