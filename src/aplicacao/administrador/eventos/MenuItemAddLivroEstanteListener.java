package aplicacao.administrador.eventos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import aplicacao.FacadeAdministrador;
import aplicacao.eventos_sistema.BotaoCancelarListener;

public class MenuItemAddLivroEstanteListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		//Janela
		JFrame adicionarFrame = new JFrame();
		adicionarFrame.setTitle("Sistema DigiTeca - Adicionar Livro a Estante");
		adicionarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adicionarFrame.setLocation(200, 200);
		
		//Container
		Container ct = adicionarFrame.getContentPane();
		ct.setLayout(new BorderLayout());
		ct.setBackground(Color.white);

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

		//Painel Texto
		JPanel painel = new JPanel();
		painel.setLayout(new FlowLayout());
		painel.setBackground(Color.white);
		
		JLabel idEstante= new JLabel("Digite o ID da estante:");
		JTextField campoIdEstante = new JTextField(10);
		painel.add(idEstante);
		painel.add(campoIdEstante);
		
		JLabel isbn = new JLabel("Digite o ISBN do livro (OS LIVROS DEVEM ESTAR PREVIAMENTE CADASTRADOS):");
		JTextField campoIsbn = new JTextField(10);
		painel.add(isbn);
		painel.add(campoIsbn);
		
		//Icone do rotulo incial (Adicionar livro a estante)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotAddLivroEstante.png");
		JLabel rotulo = new JLabel(iconeRotulo);
		rotulo.setBackground(Color.white);
		
		//Botoes
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.white);
		
		Icon icone1 = new ImageIcon("src/aplicacao/icones/iconeEntrar.png");
		Icon icone2 = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
		JButton botaoCadastrar = new JButton("Adicionar", icone1);
		JButton botaoCancelar = new JButton("Cancelar", icone2);
		
		painelBotoes.add(botaoCancelar);
		painelBotoes.add(botaoCadastrar);
		
		ct.add(rotulo,BorderLayout.NORTH);
		ct.add(painel,BorderLayout.CENTER);
		ct.add(panelWest, BorderLayout.WEST);
		ct.add(panelEast, BorderLayout.EAST);
		ct.add(painelBotoes,BorderLayout.SOUTH);
		
		//Tratando o evento do botao cadastrar
		BotaoAddLivroEstanteListener ouvinteCadastrar = new BotaoAddLivroEstanteListener(campoIsbn,campoIdEstante,adicionarFrame);
		botaoCadastrar.addActionListener(ouvinteCadastrar);
				
		//Tratando o evento do botao cancelar
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(adicionarFrame);
		botaoCancelar.addActionListener(ouvinteCancelar);
	
		adicionarFrame.pack();
		adicionarFrame.setResizable(false);
		adicionarFrame.setVisible(true);	
	}
	
	//Usando uma classe interna para tratar o evento do "botao cadastrar" para registrar uma estante no sistema
	private class BotaoAddLivroEstanteListener implements ActionListener{
		private FacadeAdministrador fachadaAdm = new FacadeAdministrador();
		private JTextField idEstante;
		private JTextField isbn;
		private JFrame janela;
		
		public BotaoAddLivroEstanteListener(JTextField isbn,JTextField idEstante, JFrame janela) {
			this.idEstante = idEstante;
			this.isbn = isbn;
			this.janela = janela;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			fachadaAdm.adicionarLivroEstante(isbn.getText(),idEstante.getText());
			janela.dispose();
		}
	}

}
