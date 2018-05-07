
package aplicacao.bibliotecario.eventos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import aplicacao.FacadeBibliotecario;
import aplicacao.eventos_sistema.BotaoCancelarListener;

public class MenuItemEmprestimoListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		//Janela
		JFrame emprestimoFrame = new JFrame();
		emprestimoFrame.setTitle("Sistema DigiTeca - Efetuar Emprestimo");
		emprestimoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		emprestimoFrame.setLocation(400,200);
		
		//Container
		Container ct = emprestimoFrame.getContentPane();
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
		
		//Paineis
		JPanel painelTexto = new JPanel();
		painelTexto.setLayout(new GridBagLayout());
		GridBagConstraints cst = new GridBagConstraints();
		painelTexto.setBackground(Color.white);
		painelTexto.setBorder(BorderFactory.createTitledBorder("Novo Empréstimo"));
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout());
		painelBotoes.setBackground(Color.white);
		
		//Rotulos e campos
		MaskFormatter mascaraCpf = null;
		MaskFormatter mascaraIsbn = null;

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraIsbn = new MaskFormatter("###-##-###-####-#");;
			mascaraCpf.setPlaceholderCharacter('_');
			mascaraIsbn.setPlaceholderCharacter('_');
		} catch (ParseException excp) {
			JOptionPane.showMessageDialog(null, excp.getMessage());
			System.exit(-1);
		}

		// Seta as máscaras nos objetos JFormattedTextField
		JFormattedTextField campoCpfLeitor = new JFormattedTextField(mascaraCpf);
		JFormattedTextField campoCpfBiblio = new JFormattedTextField(mascaraCpf);
		JFormattedTextField campoIsbn = new JFormattedTextField(mascaraIsbn);
		
		JLabel cpfLeitor = new JLabel("CPF do Leitor: ");
		cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 0;
	    painelTexto.add(cpfLeitor,cst);
	    cst.gridx = 1;
	    cst.gridy = 0;
		painelTexto.add(campoCpfLeitor,cst);
		
		JLabel isbn = new JLabel("ISBN do Livro: ");
		cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 1;
	    painelTexto.add(isbn,cst);
	    cst.gridx = 1;
	    cst.gridy = 1;
		painelTexto.add(campoIsbn,cst);
		
		JLabel cpfBiblio = new JLabel("CPF do Bibliotecario: ");
		cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 2;
	    painelTexto.add(cpfBiblio,cst);
	    cst.gridx = 1;
	    cst.gridy = 2;
		painelTexto.add(campoCpfBiblio,cst);
		
		//Icone do rotulo inicial (Efetuar emprestimo)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotEmprestimo.png");
		JLabel rotImagem = new JLabel(iconeRotulo);
		
		//Botoes
		Icon icone1 = new ImageIcon("src/aplicacao/icones/iconeEntrar.png");
		Icon icone2 = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
		JButton botaoConfirmar= new JButton("Confirmar", icone1);
		JButton botaoCancelar = new JButton("Cancelar", icone2);
		painelBotoes.add(botaoCancelar);
		painelBotoes.add(botaoConfirmar);
		
		//Adicionando os componentes ao container
		ct.add(rotImagem, BorderLayout.NORTH);
		ct.add(panelWest, BorderLayout.WEST);
		ct.add(panelEast, BorderLayout.EAST);
		ct.add(painelTexto, BorderLayout.CENTER);
		ct.add(painelBotoes, BorderLayout.SOUTH);

		//Mostrando a janela
		emprestimoFrame.pack();
		emprestimoFrame.setResizable(false);
		emprestimoFrame.setVisible(true);
		
		//Tratamento de eventos
		BotaoEfetuarEmprestimoListener ouvinteEfetuarEmprestimo = new BotaoEfetuarEmprestimoListener(campoCpfLeitor,campoIsbn,campoCpfBiblio,emprestimoFrame);
		botaoConfirmar.addActionListener(ouvinteEfetuarEmprestimo);
		
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(emprestimoFrame);
		botaoCancelar.addActionListener(ouvinteCancelar);
	
	}
	
	private class BotaoEfetuarEmprestimoListener implements ActionListener {
			FacadeBibliotecario fachadaBiblio = new FacadeBibliotecario();
			JTextField isbn;
			JTextField cpfLeitor;
			JTextField cpfBiblio;
			JFrame janela;
			public BotaoEfetuarEmprestimoListener(JTextField cpfLeitor, JTextField isbn,JTextField cpfBiblio, JFrame janela) {
				this.isbn = isbn;
				this.cpfLeitor = cpfLeitor;
				this.cpfBiblio = cpfBiblio;
				this.janela = janela;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				fachadaBiblio.realizarEmprestimo(cpfLeitor.getText(),isbn.getText(),cpfBiblio.getText());
				janela.dispose();
			}
			
	}
	
}
