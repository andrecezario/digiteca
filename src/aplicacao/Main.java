package aplicacao;


public class Main {

	public static void main(String[] args) {
		
		FacadeAdministrador fAdm = new FacadeAdministrador();
		
		//Funcionando
//		fAdm.cadastrarBibliotecario("133.111.134-00", "Jose Cavalcante", "Vila João 23/Palmeira dos Índios", "82 9999-2045", "jose@hotmail.com", "02/10/1996", "bibli-04", "4321");

		//Funcionado
//		System.out.println(fAdm.buscarFuncionario("111.400.424-95", "Bibliotecario"));
		
		//Funcionando
//		fAdm.atualizarDados("Administrador", "113.144.567-00", "Paulo Roberto", "09/09/1989","São Cristóvão/Palmeira dos Índios" , "82 9909-0098", "pauloroberto@gmail.com");
	
		//Funcionando
//		fAdm.atualizarDados("Bibliotecario", "122.111.134-00", "Carlos Cavalcante Silva", "00/00/0000", "Vila João 23/Palmeira dos Índios", "82 9900-2045", "carloscavalcante@hotmail.com");
	
		//Nao está funcionando para cadastrar cópias
//		fAdm.cadastrarLivro("Olá mundo", "Literário", "Biologia", "111-11-111-0000-1", "Monteiro Cardoso", 1, 90);
	
		//Funcionando
//		fAdm.cadastrarLivro("Exagerado", "Literario", "Romance", "213-22-121-9891-2", "Cazuza", 1, 130);
		
		//Funcionando
//		fAdm.atualizarSenhaAcesso("admin", "admin", "12434");
	
		//Funcionando
//		fAdm.atualizarSenhaAcesso("bibli-00", "22222", "11111");
		
		//Funcionando, mas não está obedecendo ao padrão de código
//		fAdm.cadastrarEstante("E-000","Literário" );
		
		//Funcionado
//		fAdm.removerEstante("E");
	
		
//		fAdm.buscarEstante("E-000");
		
//		fAdm.efetuarLogin("a", "12434");
		
//		System.out.println(fAdm.buscarLivroIsbn("111-11-111-0000-1"));
		
		
		FacadeBibliotecario fBibli = new FacadeBibliotecario();
		
//		fBibli.atualizarDadosLeitor("000.000.000-11", "Lucas Vieira", "São Paulo", "82 9999-1111", "lucassilva@hotmail.com");
		
//		fBibli.cadastrarLeitor("000.000.000-11", "Lucas","Vila Maria" , "00 1234-0000", "lucas@hotmail.com", "12/09/1980");
	
		//Remover
//		fBibli.removerLeitor(" ");
		
//		fBibli.buscarLeitor("000.000.000-00");
		
//		fAdm.buscarLivroIsbn("111-11-111-0000-1");
		
//		System.out.println(fBibli.buscarLivro("Id", "E-001"));
		
//		fBibli.buscarLivro("Status", "Permanente");
		
		
//		fBibli.buscar(fBibli.buscarLivro("Status", "Permanente"));
		
//		fAdm.adicionarLivroEstante("222-22-222-2222-2", "E-002");
		
//		fAdm.cadastrarEstante("E-002", "Didático");
		
//		fAdm.removerLivroEstante("E-002","222-22-222-2222-2");
		
//		fAdm.limparEstante("E-000");
		
//		fBibli.realizarEmprestimo("111.111.111-99", "213-22-121-9891-2", "444.449.444-44");
		
		fBibli.buscarEmprestimo("333.333.333-33");
	}
}
