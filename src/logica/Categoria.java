package logica;

public class Categoria {

	private String idCategoria;
	private String descricao;

	public Categoria(){}
	public Categoria(String idCategoria, String descricao) {
		this.idCategoria = idCategoria;
		this.descricao = descricao;
	}

	public String getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Categoria - " + "Id : " + idCategoria + " | " + descricao + "\n";
	}

}
