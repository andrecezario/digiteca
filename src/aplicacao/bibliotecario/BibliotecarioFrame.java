package aplicacao.bibliotecario;

import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import aplicacao.administrador.eventos.MenuItemAltSenhaListener;
import aplicacao.bibliotecario.eventos.MenuItemAtuaLeitorListener;
import aplicacao.bibliotecario.eventos.MenuItemBusEmprestimoListener;
import aplicacao.bibliotecario.eventos.MenuItemBusLeitorListener;
import aplicacao.bibliotecario.eventos.MenuItemBusLivroAutorListener;
import aplicacao.bibliotecario.eventos.MenuItemBusLivroStatusListener;
import aplicacao.bibliotecario.eventos.MenuItemBusLivroTituloListener;
import aplicacao.bibliotecario.eventos.MenuItemCadLeitorListener;
import aplicacao.bibliotecario.eventos.MenuItemDevolucaoListener;
import aplicacao.bibliotecario.eventos.MenuItemEmprestimoListener;
import aplicacao.bibliotecario.eventos.MenuItemListEmprestimosListener;
import aplicacao.bibliotecario.eventos.MenuItemRemLeitorListener;
import aplicacao.eventos_sistema.MenuItemSairListener;

public class BibliotecarioFrame extends JFrame {
	public BibliotecarioFrame() {
		super("Sistema DigiTeca - Bibliotecario");
		criarMenu();
	}
	private void criarMenu() {
		// Menu Usuario
		JMenu menuUsuario = new JMenu("Usuario");
		Icon iconeUsuario = new ImageIcon("src/aplicacao/icones/iconeUsuario.png");
		menuUsuario.setIcon(iconeUsuario);
		menuUsuario.setFont(new Font("Calibre", Font.BOLD, 18));

		Icon iconeSenha = new ImageIcon("src/aplicacao/icones/iconeSenha.png");
		Icon iconeSair = new ImageIcon("src/aplicacao/icones/iconeSairSistema.png");
		
		JMenuItem menuItemSenha = new JMenuItem("Alterar Senha", iconeSenha);
		menuUsuario.add(menuItemSenha);
		JMenuItem menuItemSair = new JMenuItem("Sair", iconeSair);
		menuUsuario.add(menuItemSair);
		
		// Menu Leitor
		JMenu menuLeitor = new JMenu("Leitor");
		Icon iconeLeitor = new ImageIcon("src/aplicacao/icones/iconeLeitor.png");
		menuLeitor.setIcon(iconeLeitor);
		menuLeitor.setFont(new Font("Calibre", Font.BOLD, 18));
		
		//Instanciando imagens dos icones
		Icon icone1 = new ImageIcon("src/aplicacao/icones/iconCadLeitor.png");
		Icon icone2 = new ImageIcon("src/aplicacao/icones/iconAtuaLeitor.png");
		Icon icone3 = new ImageIcon("src/aplicacao/icones/iconBuscLeitor.png");
		Icon icone4 = new ImageIcon("src/aplicacao/icones/iconeRemLeitor.png");
		Icon icone5 = new ImageIcon("src/aplicacao/icones/iconeBuscarEmprestimo.png");
		Icon icone6 = new ImageIcon("src/aplicacao/icones/iconeListarLivros.png");
		Icon icone7 = new ImageIcon("src/aplicacao/icones/iconeEfetuarEmprestimo.png");
		Icon icone8 = new ImageIcon("src/aplicacao/icones/iconeRegistrarDevolucao.png");
		Icon icone9 = new ImageIcon("src/aplicacao/icones/iconBuscarTitulo.png");
		Icon icone10 = new ImageIcon("src/aplicacao/icones/iconeAutor.png");
		Icon icone11 = new ImageIcon("src/aplicacao/icones/iconeStatus.png");
		
		JMenuItem menuItemCadastrarLeitor = new JMenuItem("Cadastrar", icone1);
		menuLeitor.add(menuItemCadastrarLeitor);
		
		JMenuItem menuItemAtualizarLeitor = new JMenuItem("Atualizar", icone2);
		menuLeitor.add(menuItemAtualizarLeitor);

		JMenuItem menuItemBuscarLeitor = new JMenuItem("Buscar", icone3);
		menuLeitor.add(menuItemBuscarLeitor);

		JMenuItem menuItemRemoverLeitor = new JMenuItem("Remover", icone4);
		menuLeitor.add(menuItemRemoverLeitor);

		// Menu Empr�stimo
		JMenu menuEmprestimo = new JMenu("Emprestimo");
		Icon iconeEmprestimo = new ImageIcon("src/aplicacao/icones/iconeEmprestimo.png");
		menuEmprestimo.setIcon(iconeEmprestimo);
		menuEmprestimo.setFont(new Font("Calibre", Font.BOLD, 18));

		JMenuItem menuItemEmprestimo = new JMenuItem("Efetuar Emprestimo", icone7);
		menuEmprestimo.add(menuItemEmprestimo);
		
		JMenuItem menuItemDevolucao = new JMenuItem("Registrar Devolucao", icone8);
		menuEmprestimo.add(menuItemDevolucao);
		
		JMenuItem menuItemBuscarEmprestimo = new JMenuItem("Buscar Emprestimo", icone5);
		menuEmprestimo.add(menuItemBuscarEmprestimo);
		
		JMenuItem menuItemListarEmprestimos = new JMenuItem("Listar Emprestimos", icone6);
		menuEmprestimo.add(menuItemListarEmprestimos);

		// Menu Pesquisar Livro
		JMenu menuPesquisarLivro = new JMenu("Pesquisar Livro");
		Icon iconeLivro = new ImageIcon("src/aplicacao/icones/iconBuscarLivro.png");
		menuPesquisarLivro.setIcon(iconeLivro);
		menuPesquisarLivro.setFont(new Font("Calibre", Font.BOLD, 18));

		JMenuItem menuItemTitulo = new JMenuItem("Titulo", icone9);
		menuPesquisarLivro.add(menuItemTitulo);

		JMenuItem menuItemAutor = new JMenuItem("Autor", icone10);
		menuPesquisarLivro.add(menuItemAutor);
		
		JMenuItem menuItemStatus = new JMenuItem("Status", icone11);
		menuPesquisarLivro.add(menuItemStatus);

		//Tratamento de eventos
		//Eventos do Menu Usuario
		MenuItemAltSenhaListener ouvinteMenuItemAltSenha = new MenuItemAltSenhaListener();
		menuItemSenha.addActionListener(ouvinteMenuItemAltSenha);
		
		MenuItemSairListener ouvinteMenuItemSair = new MenuItemSairListener();
		menuItemSair.addActionListener(ouvinteMenuItemSair);
		
		//Eventos do Menu Leitor
		MenuItemCadLeitorListener ouvinteMenuItemCadLeitor = new MenuItemCadLeitorListener();
		menuItemCadastrarLeitor.addActionListener(ouvinteMenuItemCadLeitor);
		
		MenuItemAtuaLeitorListener ouvinteMenuItemAtuaLeitor = new MenuItemAtuaLeitorListener();
		menuItemAtualizarLeitor.addActionListener(ouvinteMenuItemAtuaLeitor);
		
		MenuItemBusLeitorListener ouvinteMenuItemBusLeitor = new MenuItemBusLeitorListener();
		menuItemBuscarLeitor.addActionListener(ouvinteMenuItemBusLeitor);
		
		MenuItemRemLeitorListener ouvinteMenuItemRemLeitor = new MenuItemRemLeitorListener();
		menuItemRemoverLeitor.addActionListener(ouvinteMenuItemRemLeitor);
		
		//Eventos Menu Emprestimo
		MenuItemEmprestimoListener ouvinteMenuItemEmprestimo = new MenuItemEmprestimoListener();
		menuItemEmprestimo.addActionListener(ouvinteMenuItemEmprestimo);
		
		MenuItemDevolucaoListener ouvinteMenuItemDevolucao = new MenuItemDevolucaoListener();
		menuItemDevolucao.addActionListener(ouvinteMenuItemDevolucao);
		
		MenuItemBusEmprestimoListener ouvinteMenuItemBusEmprestimo = new MenuItemBusEmprestimoListener();
		menuItemBuscarEmprestimo.addActionListener(ouvinteMenuItemBusEmprestimo);
		
		MenuItemListEmprestimosListener ouvinteMenuItemListEmprestimos = new MenuItemListEmprestimosListener();
		menuItemListarEmprestimos.addActionListener(ouvinteMenuItemListEmprestimos);
		
		//Eventos Menu Pesquisar Livro
		MenuItemBusLivroAutorListener ouvinteMenuItemPesqAutor = new MenuItemBusLivroAutorListener();
		menuItemAutor.addActionListener(ouvinteMenuItemPesqAutor);
		
		MenuItemBusLivroTituloListener ouvinteMenuItemPesqTitulo = new MenuItemBusLivroTituloListener();
		menuItemTitulo.addActionListener(ouvinteMenuItemPesqTitulo);
		
		MenuItemBusLivroStatusListener ouvinteMenuItemPesqStatus = new MenuItemBusLivroStatusListener();
		menuItemStatus.addActionListener(ouvinteMenuItemPesqStatus);
		
		// Barra
		JMenuBar barra = new JMenuBar();
		setJMenuBar(barra);
		barra.add(menuUsuario);
		barra.add(menuLeitor);
		barra.add(menuEmprestimo);
		barra.add(menuPesquisarLivro);

	}
}
