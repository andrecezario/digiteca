package aplicacao.administrador.eventos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

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

import aplicacao.FacadeAdministrador;
import aplicacao.eventos_sistema.BotaoCancelarListener;

public class MenuItemRemLivroListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		//Janela
		JFrame removerFrame = new JFrame();
		removerFrame.setTitle("Sistema DigiTeca - Remover Livro");
		removerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		removerFrame.setLocation(400, 200);
		
		//Container
		Container ct = removerFrame.getContentPane();
		ct.setLayout(new BorderLayout());
		ct.setBackground(Color.white);

		//Painel
		JPanel painel = new JPanel();
		painel.setLayout(new FlowLayout());
		painel.setBackground(Color.white);

		//Rotulo e campo de texto
		MaskFormatter mascaraIsbn = null;

		try {
			mascaraIsbn = new MaskFormatter("###-##-###-####-#");
			mascaraIsbn.setPlaceholderCharacter('_');
		} catch (ParseException excp) {
			JOptionPane.showMessageDialog(null, excp.getMessage());
			System.exit(-1);
		}

		// Seta as máscaras nos objetos JFormattedTextField
		JFormattedTextField campoVerificacao= new JFormattedTextField(mascaraIsbn);
		
		JLabel verificacao = new JLabel("Digite o ISBN do livro que deseja remover:");
		painel.add(verificacao);
		painel.add(campoVerificacao);

		//Icone no rotulo incial (Remover livro)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotRemLivro.png");
		JLabel rotulo = new JLabel(iconeRotulo);
		rotulo.setBackground(Color.white);

		//Botoes de remover ou cancelar
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.white);
		
		Icon icone1 = new ImageIcon("src/aplicacao/icones/iconeEntrar.png");
		Icon icone2 = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
		JButton botaoRemover = new JButton("Remover", icone1);
		JButton botaoCancelar = new JButton("Cancelar", icone2);
		painelBotoes.add(botaoCancelar);
		painelBotoes.add(botaoRemover);
		
		ct.add(rotulo, BorderLayout.NORTH);
		ct.add(painel, BorderLayout.CENTER);
		ct.add(painelBotoes, BorderLayout.SOUTH);

		//Tratando o evento do botao remover
		BotaoRemoverLivroListener ouvinteRemover = new BotaoRemoverLivroListener(campoVerificacao,removerFrame);
		botaoRemover.addActionListener(ouvinteRemover);
		
		//Tratando o evento do botao cancelar
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(removerFrame);
		botaoCancelar.addActionListener(ouvinteCancelar);
		
		//Mostrando a janela
		removerFrame.pack();
		removerFrame.setResizable(false);
		removerFrame.setVisible(true);
	}
	
	//Usando uma classe interna para tratar o evento do "botao remover" para remover um livro no sistema
	private class BotaoRemoverLivroListener implements ActionListener{
		private FacadeAdministrador fachadaAdm = new FacadeAdministrador();
		private JTextField campoTexto;
		private JFrame janela;
		
		public BotaoRemoverLivroListener(JTextField campoTexto, JFrame janela) {
			this.campoTexto = campoTexto;
			this.janela = janela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			fachadaAdm.removerLivro(campoTexto.getText());
			janela.dispose();
		}
	}
}
