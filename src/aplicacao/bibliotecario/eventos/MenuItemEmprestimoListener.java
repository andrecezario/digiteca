
package aplicacao.bibliotecario.eventos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import aplicacao.FacadeBibliotecario;
import aplicacao.eventos_sistema.BotaoCancelarListener;

public class MenuItemEmprestimoListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		//Janela
		JFrame emprestimoFrame = new JFrame();
		emprestimoFrame.setTitle("Sistema DigiTeca - Efetuar Emprestimo");
		emprestimoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		emprestimoFrame.setLocation(200,200);
		
		//Container
		Container ct = emprestimoFrame.getContentPane();
		ct.setLayout(new BorderLayout());
		ct.setBackground(Color.white);
		
		//Paineis
		JPanel painelTexto = new JPanel();
		JPanel painelBotoes = new JPanel();
		painelTexto.setLayout(new GridLayout(3,2));
		painelTexto.setBackground(Color.white);
		painelBotoes.setLayout(new FlowLayout());
		painelBotoes.setBackground(Color.white);
		
		//R�tulos e campos
		JLabel cpfLeitor = new JLabel("CPF do Leitor: ");
		JTextField campoCpfLeitor = new JTextField(10);
		painelTexto.add(cpfLeitor);
		painelTexto.add(campoCpfLeitor);
		
		JLabel isbn = new JLabel("ISBN do Livro: ");
		JTextField campoIsbn = new JTextField(10);
		painelTexto.add(isbn);
		painelTexto.add(campoIsbn);
		
		JLabel cpfBiblio = new JLabel("CPF do Bibliotecario: ");
		JTextField campoCpfBiblio = new JTextField(10);
		painelTexto.add(cpfBiblio);
		painelTexto.add(campoCpfBiblio);
		
		//Icone do rotulo inicial (Efetuar emprestimo)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotEmprestimo.png");
		JLabel rotImagem = new JLabel(iconeRotulo);
		
		//Bot�es
		Icon icone1 = new ImageIcon("src/aplicacao/icones/iconeEntrar.png");
		Icon icone2 = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
		JButton botaoConfirmar= new JButton("Confirmar", icone1);
		JButton botaoCancelar = new JButton("Cancelar", icone2);
		painelBotoes.add(botaoCancelar);
		painelBotoes.add(botaoConfirmar);
		
		//Adicionando os componentes ao container
		ct.add(rotImagem, BorderLayout.NORTH);
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
