package logica;

import java.util.regex.Pattern;

public class Estante {
	
	private String idEstante;
	private Categoria categoria;
	
	public Estante(){}
	public Estante(String idEstante, Categoria categoria) {
		this.idEstante  = idEstante;
		this.categoria = categoria;
	}
	
	public String getIdEstante() {
		return idEstante;
	}

	public void setIdEstante(String idEstante) throws Exception{
		if(Pattern.matches("\\D-\\d\\d\\d", idEstante)){	
			this.idEstante = idEstante;
		}else{
			throw new Exception("Formato ID inválido. (Obs L-NNN, onde L : Letra e N : Número)");
		}
	}

	public Categoria getCategoria() throws Exception{
		if(categoria==null){
			throw new Exception("Estante não associada a uma categoria!");
		}else{
			return categoria;}
	}

	public void setCategoria(Categoria categoria) throws Exception{
		if(categoria==null){
			throw new Exception("Você está passando uma categoria nula!");
		}else{
			this.categoria = categoria;
		}
	}

	@Override
	public String toString() {
		return "Estante - " + "Id : " + idEstante + " | Categoria : " + categoria.getIdCategoria()
				+ "\n";
	}
	
	

}
