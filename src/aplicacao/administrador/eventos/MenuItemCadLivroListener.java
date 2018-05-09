package aplicacao.administrador.eventos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import aplicacao.FacadeAdministrador;
import aplicacao.eventos_sistema.BotaoCancelarListener;

public class MenuItemCadLivroListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		//Janela
		JFrame cadastroFrame = new JFrame();
		cadastroFrame.setTitle("Sistema DigiTeca - Cadastrar Livro");
		cadastroFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cadastroFrame.setLocation(400, 200);
		
		//Container
		Container ct = cadastroFrame.getContentPane();
		ct.setLayout(new BorderLayout());
		ct.setBackground(Color.white);

		//Painel
		JPanel painel = new JPanel();
		painel.setLayout(new GridBagLayout());
		GridBagConstraints cst = new GridBagConstraints();
		painel.setBackground(Color.white);
		painel.setBorder(BorderFactory.createTitledBorder("Novo Livro"));
		
		// Borda
		int tamanho_borda = 30;
		JPanel panelEast = new JPanel();
		panelEast.setOpaque(true);
		panelEast.setPreferredSize(new Dimension(tamanho_borda, tamanho_borda));
		panelEast.setBackground(Color.WHITE);

		JPanel panelWest = new JPanel();
		panelWest.setOpaque(true);
		panelWest.setPreferredSize(new Dimension(tamanho_borda, tamanho_borda));
		panelWest.setBackground(Color.WHITE);

		// Rotulos e campos
		MaskFormatter mascaraIsbn = null;

		try {
			mascaraIsbn = new MaskFormatter("###-##-###-####-#");
			mascaraIsbn.setPlaceholderCharacter('_');
		} catch (ParseException excp) {
			JOptionPane.showMessageDialog(null, excp.getMessage());
			System.exit(-1);
		}

		// Seta as máscaras nos objetos JFormattedTextField
		JFormattedTextField campoIsbn = new JFormattedTextField(mascaraIsbn);

		JLabel titulo = new JLabel("Título:");
		JTextField campoTitulo = new JTextField(30);
		cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 0;
	    painel.add(titulo,cst);
	    cst.gridx = 1;
	    cst.gridy = 0;
		painel.add(campoTitulo,cst);
		
		JLabel isbn = new JLabel("ISBN:");
		cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 1;
	    painel.add(isbn,cst);
	    cst.gridx = 1;
	    cst.gridy = 1;
		painel.add(campoIsbn,cst);
		
		JLabel tipo = new JLabel("Tipo:");
		JTextField campoTipo = new JTextField(30);
		cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 2;
	    painel.add(tipo,cst);
	    cst.gridx = 1;
	    cst.gridy = 2;
		painel.add(campoTipo,cst);
		
		JLabel categoria = new JLabel("Categoria:");
		JComboBox comboCategoria = new JComboBox();
		comboCategoria.addItem("Literário");
		comboCategoria.addItem("Didático");
		comboCategoria.addItem("Profissionalizante");
		cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 3;
	    painel.add(categoria,cst);
	    cst.gridx = 1;
		cst.gridy = 3;
		painel.add(comboCategoria,cst);
		
		JLabel autor = new JLabel("Autor:");
		JTextField campoAutor = new JTextField(30);
		cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 4;
	    painel.add(autor,cst);
	    cst.gridx = 1;
	    cst.gridy = 4;
		painel.add(campoAutor,cst);
		
		JLabel numPaginas = new JLabel("Número de Páginas: ");
		JTextField campoNumPaginas = new JTextField(30);
		cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 5;
	    painel.add(numPaginas,cst);
	    cst.gridx = 1;
	    cst.gridy = 5;
		painel.add(campoNumPaginas,cst);
		
		JLabel numEdicao = new JLabel("Número de Edição:");
		JTextField campoNumEdicao = new JTextField(30);
		cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 6;
	    painel.add(numEdicao,cst);
	    cst.gridx = 1;
	    cst.gridy = 6;
		painel.add(campoNumEdicao,cst);
		
		//Icone do rotulo incial (Cadastrar livro)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotCadLivro.png");
		JLabel rotulo = new JLabel(iconeRotulo);
		rotulo.setBackground(Color.white);
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.white);
		Icon icone1 = new ImageIcon("src/aplicacao/icones/iconeEntrar.png");
		Icon icone2 = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
		JButton botaoCadastrar = new JButton("Cadastrar", icone1);
		JButton botaoCancelar = new JButton("Cancelar", icone2);
		painelBotoes.add(botaoCancelar);
		painelBotoes.add(botaoCadastrar);
		
		ct.add(rotulo,BorderLayout.NORTH);
		ct.add(painel,BorderLayout.CENTER);
		ct.add(panelWest, BorderLayout.WEST);
		ct.add(panelEast, BorderLayout.EAST);
		ct.add(painelBotoes,BorderLayout.SOUTH);
		
		//Tratando o evento do botao cadastrar
		BotaoCadastrarLivroListener ouvinteCadastrar = new BotaoCadastrarLivroListener(campoTitulo, campoIsbn, campoTipo, comboCategoria,campoAutor,campoNumPaginas,campoNumEdicao,cadastroFrame);
		botaoCadastrar.addActionListener(ouvinteCadastrar);
				
		//Tratando o evento do botao cancelar
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(cadastroFrame);
		botaoCancelar.addActionListener(ouvinteCancelar);
	
		cadastroFrame.pack();
		cadastroFrame.setResizable(false);
		cadastroFrame.setVisible(true);	
	}
	
	//Usando uma classe interna para tratar o evento do "botao cadastrar" para cadastrar um livro no sistema
	private class BotaoCadastrarLivroListener implements ActionListener{
		private FacadeAdministrador fachadaAdm = new FacadeAdministrador();
		private JTextField titulo;
		private JTextField isbn;
		private JTextField tipo;
		private JComboBox categoria;
		private JTextField autor;
		private JTextField numPaginas;
		private JTextField numEdicao;
		private JFrame janela;
		
		public BotaoCadastrarLivroListener(JTextField titulo, JTextField isbn, JTextField tipo, JComboBox categoria, JTextField autor, JTextField numPaginas, JTextField numEdicao,JFrame janela) {
			this.titulo = titulo;
			this.isbn = isbn;
			this.tipo = tipo;
			this.categoria = categoria;
			this.autor = autor;
			this.numPaginas = numPaginas;
			this.numEdicao = numEdicao;
			this.janela = janela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int nPaginas = Integer.valueOf(numPaginas.getText());
			int nEdicao= Integer.valueOf(numEdicao.getText());
			String valorCategoria = (String) categoria.getSelectedItem(); 
			fachadaAdm.cadastrarLivro(titulo.getText(),valorCategoria, tipo.getText(),isbn.getText(),autor.getText(),nEdicao, nPaginas);
			janela.dispose();
		}
	}
}
