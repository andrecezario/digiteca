package aplicacao.administrador.eventos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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

public class MenuItemRemEstanteListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		//Janela
		JFrame removerFrame = new JFrame();
		removerFrame.setTitle("Sistema DigiTeca - Remover Estante");
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

		//Ratulo e campo de texto
		JLabel verificacao = new JLabel("Digite o ID da estante para remoção:");
		JTextField campoVerificacao = new JTextField(10);
		painel.add(verificacao);
		painel.add(campoVerificacao);

		//Icone no rotulo incial (Remover estante)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotRemEstante.png");
		JLabel rotulo = new JLabel(iconeRotulo);
		rotulo.setBackground(Color.white);

		//Botoes de remover ou cancelar
		JPanel painelBotoes = new JPanel();
		Icon icone1 = new ImageIcon("src/aplicacao/icones/iconeEntrar.png");
		Icon icone2 = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
		JButton botaoRemover = new JButton("Remover", icone1);
		JButton botaoCancelar = new JButton("Cancelar", icone2);
		painelBotoes.add(botaoCancelar);
		painelBotoes.add(botaoRemover);
		painelBotoes.setBackground(Color.white);

		ct.add(rotulo, BorderLayout.NORTH);
		ct.add(painel, BorderLayout.CENTER);
		ct.add(painelBotoes, BorderLayout.SOUTH);

		//Tratando o evento do botao remover
		BotaoRemoverEstanteListener ouvinteRemover = new BotaoRemoverEstanteListener(campoVerificacao,removerFrame);
		botaoRemover.addActionListener(ouvinteRemover);
		
		//Tratando o evento do botao cancelar
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(removerFrame);
		botaoCancelar.addActionListener(ouvinteCancelar);
		
		//Mostrando a janela
		removerFrame.pack();
		removerFrame.setResizable(false);
		removerFrame.setVisible(true);
	}
	
	//Usando uma classe interna para tratar o evento do "botao remover" para remover uma estante no sistema
	private class BotaoRemoverEstanteListener implements ActionListener{
		private FacadeAdministrador fachadaAdm = new FacadeAdministrador();
		private JTextField campoTexto;
		private JFrame janela;
		
		public BotaoRemoverEstanteListener(JTextField campoTexto, JFrame janela) {
			this.campoTexto = campoTexto;
			this.janela = janela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			fachadaAdm.removerEstante(campoTexto.getText());
			janela.dispose();
		}
	}

}
