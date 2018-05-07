package logica;

public class Emprestimo {

	private int idEmprestimo;
	private String dataAtual;
	private String dataDevolucao;
	private Livro livro;
	private Leitor leitor;
	private Bibliotecario bibliotecario;

	public Emprestimo(){}
	public Emprestimo(String dataAtual, String dataDevolucao,
			Livro livro, Leitor leitor, Bibliotecario bibliotecario) {
		this.dataAtual = dataAtual;
		this.dataDevolucao = dataDevolucao;
		this.livro = livro;
		this.leitor = leitor;
		this.bibliotecario = bibliotecario;
	}

	public int getIdEmprestimo() {
		return idEmprestimo;
	}

	public String getDataAtual() {
		return dataAtual;
	}

	public String getDataDevolucao() {
		return dataDevolucao;
	}

	public Livro getLivro() {
		return livro;
	}

	public Leitor getLeitor() {
		return leitor;
	}

	public Bibliotecario getBibliotecario() {
		return bibliotecario;
	}

	@Override
	public String toString() {
		return "Empréstimo - Id : " + idEmprestimo + "\nData: " + dataAtual
				+ " | Data de devolução : " + dataDevolucao + "\nLivro : "
				+ livro.getTitulo() + " | Leitor : " + leitor.getNome()
				+ "\nBibliotecário responsável: " + bibliotecario.getNome()
				+ "\n";
	}

	public void setIdEmprestimo(int idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}

	public void setDataAtual(String dataAtual) {
		this.dataAtual = dataAtual;
	}

	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public void setLeitor(Leitor leitor) {
		this.leitor = leitor;
	}

	public void setBibliotecario(Bibliotecario bibliotecario) {
		this.bibliotecario = bibliotecario;
	}

}
