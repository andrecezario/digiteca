package aplicacao.administrador;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Enumeration;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.MenuElement;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicMenuBarUI;

import aplicacao.administrador.eventos.MenuItemAddLivroEstanteListener;
import aplicacao.administrador.eventos.MenuItemAltDadosListener;
import aplicacao.administrador.eventos.MenuItemAltSenhaListener;
import aplicacao.administrador.eventos.MenuItemAtuaBibliotecarioListener;
import aplicacao.administrador.eventos.MenuItemBusBibliotecarioListener;
import aplicacao.administrador.eventos.MenuItemBusEstanteListener;
import aplicacao.administrador.eventos.MenuItemBusLivroListener;
import aplicacao.administrador.eventos.MenuItemCadBibliotecarioListener;
import aplicacao.administrador.eventos.MenuItemCadLivroListener;
import aplicacao.administrador.eventos.MenuItemLimpEstanteListener;
import aplicacao.administrador.eventos.MenuItemLivrosEstanteListener;
import aplicacao.administrador.eventos.MenuItemRegEstanteListener;
import aplicacao.administrador.eventos.MenuItemRemBibliotecarioListener;
import aplicacao.administrador.eventos.MenuItemRemEstanteListener;
import aplicacao.administrador.eventos.MenuItemRemLivroEstanteListener;
import aplicacao.administrador.eventos.MenuItemRemLivroListener;
import aplicacao.eventos_sistema.MenuItemSairListener;

public class AdministradorFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdministradorFrame() {
		super("Sistema DigiTeca - Administrador");
		Font font = new Font("Arial", Font.PLAIN, 14);
		setDefaultFont(font);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\logo.png"));

		criarMenu();
	}
	private void criarMenu() {
		// Menu Usuario
		JMenu menuUsuario = new JMenu("Usuário");
		Icon iconeUsuario = new ImageIcon("src/aplicacao/icones/iconeAdm.png");
		menuUsuario.setIcon(iconeUsuario);
	
		Icon iconeSenha = new ImageIcon("src/aplicacao/icones/iconAtuaSenha.png");
		Icon iconeAlterar = new ImageIcon("src/aplicacao/icones/iconeAtuaDados.png");
		Icon iconeSair = new ImageIcon("src/aplicacao/icones/iconeSairSistema2.png");
		
		JMenuItem menuItemSenha = new JMenuItem("Alterar Senha", iconeSenha);
		menuUsuario.add(menuItemSenha);
		
		JMenuItem menuItemAlteraDados = new JMenuItem("Alterar Dados", iconeAlterar);
		menuUsuario.add(menuItemAlteraDados);
		
		JMenuItem menuItemSair = new JMenuItem("Sair",iconeSair);
		menuUsuario.add(menuItemSair);

		// Menu Funcionário
		JMenu menuFuncionario = new JMenu("Bibliotecário");
		menuFuncionario.setBackground(Color.white);
		Icon iconeFuncionario = new ImageIcon("src/aplicacao/icones/iconeFuncionario.png");
		menuFuncionario.setIcon(iconeFuncionario);

		Icon icone1 = new ImageIcon("src/aplicacao/icones/iconeCadastrarFuncionario.png");
		Icon icone2 = new ImageIcon("src/aplicacao/icones/iconeAtualizarFuncionario.png");
		Icon icone3 = new ImageIcon("src/aplicacao/icones/iconeBuscarFuncionario.png");
		Icon icone4 = new ImageIcon("src/aplicacao/icones/iconeRemoverFuncionario.png");

		JMenuItem menuItemCadastrarFuncionario = new JMenuItem("Cadastrar", icone1);
		menuFuncionario.add(menuItemCadastrarFuncionario);

		JMenuItem menuItemAtualizarFuncionario = new JMenuItem("Atualizar", icone2);
		menuFuncionario.add(menuItemAtualizarFuncionario);

		JMenuItem menuItemBuscarFuncionario = new JMenuItem("Buscar", icone3);
		menuFuncionario.add(menuItemBuscarFuncionario);

		JMenuItem menuItemRemoverFuncionario = new JMenuItem("Remover", icone4);
		menuFuncionario.add(menuItemRemoverFuncionario);

		// Menu Livro
		JMenu menuLivro = new JMenu("Livro");
		Icon iconeLivro = new ImageIcon("src/aplicacao/icones/iconeLivro.png");
		menuLivro.setIcon(iconeLivro);

		Icon icone5 = new ImageIcon("src/aplicacao/icones/iconeCadastrarLivro.png");
		Icon icone6 = new ImageIcon("src/aplicacao/icones/iconeBuscarLivro.png");
		Icon icone7 = new ImageIcon("src/aplicacao/icones/iconeRemoverLivro.png");

		JMenuItem menuItemCadastrarLivro = new JMenuItem("Cadastrar", icone5);
		menuLivro.add(menuItemCadastrarLivro);
		
		JMenuItem menuItemBuscarLivro = new JMenuItem("Buscar", icone6);
		menuLivro.add(menuItemBuscarLivro);
		
		JMenuItem menuItemRemoverLivro = new JMenuItem("Remover", icone7);
		menuLivro.add(menuItemRemoverLivro);
		
		//Menu Estante
		JMenu menuEstante = new JMenu("Estante");
		Icon iconeEstante = new ImageIcon("src/aplicacao/icones/iconeEstante.png");
		menuEstante.setIcon(iconeEstante);
		
		Icon icone8 = new ImageIcon("src/aplicacao/icones/iconeAddEstante.png");
		Icon icone9 = new ImageIcon("src/aplicacao/icones/iconeAddLivro.png");
		Icon icone10 = new ImageIcon("src/aplicacao/icones/iconeApagarLivro.png");
		Icon icone11 = new ImageIcon("src/aplicacao/icones/iconeLimpar.png");
		Icon icone12 = new ImageIcon("src/aplicacao/icones/iconRemEstante.png");
		Icon icone13 = new ImageIcon("src/aplicacao/icones/iconeListarLivros.png");
		Icon icone14 = new ImageIcon("src/aplicacao/icones/iconPesqLivros.png");
		
		JMenuItem menuItemRegistrarEstante = new JMenuItem("Registrar", icone8);
		menuEstante.add(menuItemRegistrarEstante);
		
		JMenuItem menuItemAddLivroEstante = new JMenuItem("Adicionar Livro", icone9);
		menuEstante.add(menuItemAddLivroEstante);
		
		JMenuItem menuItemLivrosEstante = new JMenuItem("Listar Livros", icone13);
		menuEstante.add(menuItemLivrosEstante);
		
		JMenuItem menuItemRemLivroEstante = new JMenuItem("Remover Livro", icone10);
		menuEstante.add(menuItemRemLivroEstante);
		
		JMenuItem menuItemBuscarEstante = new JMenuItem("Buscar", icone14);
		menuEstante.add(menuItemBuscarEstante);
		
		JMenuItem menuItemLimparEstante = new JMenuItem("Limpar", icone11);
		menuEstante.add(menuItemLimparEstante);
		
		JMenuItem menuItemRemoverEstante = new JMenuItem("Remover", icone12);
		menuEstante.add(menuItemRemoverEstante);
		
		//Tratando eventos
		// Menu Usuario
		MenuItemAltSenhaListener ouvinteMenuItemAltSenha = new MenuItemAltSenhaListener();
		menuItemSenha.addActionListener(ouvinteMenuItemAltSenha);
		
		MenuItemAltDadosListener ouvinteMenuItemAltDados = new MenuItemAltDadosListener();
		menuItemAlteraDados.addActionListener(ouvinteMenuItemAltDados);
		
		MenuItemSairListener ouvinteMenuItemSair= new MenuItemSairListener();
		menuItemSair.addActionListener(ouvinteMenuItemSair);
		
		//Menu Bibliotecario
		MenuItemCadBibliotecarioListener ouvinteMenuItemCadBiblio= new MenuItemCadBibliotecarioListener();
		menuItemCadastrarFuncionario.addActionListener(ouvinteMenuItemCadBiblio);
		
		MenuItemAtuaBibliotecarioListener ouvinteMenuItemAtuaBiblio= new MenuItemAtuaBibliotecarioListener();
		menuItemAtualizarFuncionario.addActionListener(ouvinteMenuItemAtuaBiblio);
		
		MenuItemBusBibliotecarioListener ouvinteMenuItemBusBiblio= new MenuItemBusBibliotecarioListener();
		menuItemBuscarFuncionario.addActionListener(ouvinteMenuItemBusBiblio);
		
		MenuItemRemBibliotecarioListener ouvinteMenuItemRemBiblio= new MenuItemRemBibliotecarioListener();
		menuItemRemoverFuncionario.addActionListener(ouvinteMenuItemRemBiblio);
		
		//Menu Livro
		MenuItemCadLivroListener ouvinteMenuItemCadLivro = new MenuItemCadLivroListener();
		menuItemCadastrarLivro.addActionListener(ouvinteMenuItemCadLivro);
		
		MenuItemBusLivroListener ouvinteMenuItemBusLivro = new MenuItemBusLivroListener();
		menuItemBuscarLivro.addActionListener(ouvinteMenuItemBusLivro);
		
		MenuItemRemLivroListener ouvinteMenuItemRemLivro = new MenuItemRemLivroListener();
		menuItemRemoverLivro.addActionListener(ouvinteMenuItemRemLivro);
		
		//Menu Estante
		MenuItemRegEstanteListener ouvinteMenuItemRegistrarEstante= new MenuItemRegEstanteListener();
		menuItemRegistrarEstante.addActionListener(ouvinteMenuItemRegistrarEstante);

		MenuItemLivrosEstanteListener ouvinteMenuItemListarLivros = new MenuItemLivrosEstanteListener();
		menuItemLivrosEstante.addActionListener(ouvinteMenuItemListarLivros);

		MenuItemRemEstanteListener ouvinteMenuItemRemEstante = new MenuItemRemEstanteListener();
		menuItemRemoverEstante.addActionListener(ouvinteMenuItemRemEstante);
		
		MenuItemBusEstanteListener ouvinteMenuItemBusEstante = new MenuItemBusEstanteListener();
		menuItemBuscarEstante.addActionListener(ouvinteMenuItemBusEstante);
		
		MenuItemLimpEstanteListener ouvinteMenuItemLimpEstante = new MenuItemLimpEstanteListener();
		menuItemLimparEstante.addActionListener(ouvinteMenuItemLimpEstante);
		
		MenuItemAddLivroEstanteListener ouvinteMenuItemAddLivroEstante = new MenuItemAddLivroEstanteListener();
		menuItemAddLivroEstante.addActionListener(ouvinteMenuItemAddLivroEstante);
		
		MenuItemRemLivroEstanteListener ouvinteMenuItemRemLivroEstante = new MenuItemRemLivroEstanteListener();
		menuItemRemLivroEstante.addActionListener(ouvinteMenuItemRemLivroEstante);
		
		// Barra
		JMenuBar barra = new JMenuBar();
		setJMenuBar(barra);
		barra.add(menuUsuario);
		barra.add(menuFuncionario);
		barra.add(menuLivro);
		barra.add(menuEstante);
		customizeMenuBar(barra);
	}
	
	private void customizeMenuBar(JMenuBar menuBar) {

	    menuBar.setUI(new BasicMenuBarUI() {

	        @Override
	        public void paint(Graphics g, JComponent c) {
	        	Color c2 = new Color(79,78,71);   
	            g.setColor(c2);
	            g.fillRect(0, 0, c.getWidth(), c.getHeight());
	        }

	    });

	    MenuElement[] menus = menuBar.getSubElements();

	    for (MenuElement menuElement : menus) {

	        JMenu menu = (JMenu) menuElement.getComponent();
	        changeComponentColors(menu);
	        menu.setOpaque(true);

	        MenuElement[] menuElements = menu.getSubElements();

	        for (MenuElement popupMenuElement : menuElements) {

	            JPopupMenu popupMenu = (JPopupMenu) popupMenuElement.getComponent();
	            popupMenu.setBorder(null);

	            MenuElement[] menuItens = popupMenuElement.getSubElements();

	            for (MenuElement menuItemElement : menuItens) {

	                JMenuItem menuItem = (JMenuItem) menuItemElement.getComponent();
	                changeComponentColors(menuItem);
	                menuItem.setOpaque(true);

	            }
	        }
	    }
	}

	private void changeComponentColors(Component comp) {
		Color c = new Color(79,78,71);   
	    comp.setBackground(c);
	    comp.setForeground(Color.white);
	}
	
	public void setDefaultFont(Font defaultFont){

		   FontUIResource font = new FontUIResource(defaultFont);

		   Enumeration<?> uiManagerKeys = UIManager.getDefaults().keys();
		   while(uiManagerKeys.hasMoreElements()){
		        Object key   = uiManagerKeys.nextElement(),
		               value = UIManager.get(key);

		        if(null != value && value instanceof FontUIResource)
		            UIManager.put(key, font);
		   }
		}
}
