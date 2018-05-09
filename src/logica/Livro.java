package logica;

import java.util.regex.Pattern;

public class Livro {
	
	private String titulo;
	private Categoria categoria;
	private String tipo;
	private String isbn;
	private String autor;
	private int numeroEdicao;
	private int numeroPaginas;
	private String status;
	private Estante estante;
	
	public Livro(){}
	public Livro(String titulo, Categoria categoria, String tipo,
			String isbn, String autor, int numeroEdicao, int numeroPaginas) {
		this.titulo = titulo;
		this.categoria = categoria;
		this.tipo = tipo;
		this.isbn = isbn;
		this.autor = autor;
		this.numeroEdicao = numeroEdicao;
		this.numeroPaginas = numeroPaginas;
		this.estante = new Estante();
	}

	public Estante getEstante(){
		return estante;
	}
	
	public void setEstante(Estante estante){
		this.estante = estante;
	}

	public String getTitulo() {
		return titulo;
	}

	public Categoria getCategoria(){
		return categoria;
	}

	public String getTipo() {
		return tipo;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getAutor() {
		return autor;
	}

	public int getNumeroEdicao() {
		return numeroEdicao;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Livro  | Titulo : " + titulo
				+ "\n" + "Categoria : " + categoria.getDescricao() + "\nTipo : " + tipo + " | ISBN : "
				+ isbn + " | Autor : " + autor + "\nEdicao : " + numeroEdicao
				+ " | Paginas : " + numeroPaginas + "\nStatus : " + status + " | Codigo da estante : " + estante.getIdEstante()
				+ "\n";
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setCategoria(Categoria categoria) throws Exception{
		if(categoria==null){
			throw new Exception("Voce esta passando uma categoria nula!");
		}else{
			this.categoria = categoria;
		}
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setIsbn(String isbn) throws Exception{
		if(Pattern.matches("\\d\\d\\d-\\d\\d-\\d\\d\\d-\\d\\d\\d\\d-\\d", isbn)){
			this.isbn = isbn;
		}else{
			throw new Exception("Formato isbn incorreto! (Obs. Padrao: NNN-NN-NNN-NNNN-N, onde N: Numero)");
		}
	
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public void setNumeroEdicao(int numeroEdicao) {
		this.numeroEdicao = numeroEdicao;
	}
	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
