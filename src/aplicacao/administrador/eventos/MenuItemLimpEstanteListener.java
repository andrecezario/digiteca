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

public class MenuItemLimpEstanteListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		// Janela
		JFrame limparFrame = new JFrame();
		limparFrame.setTitle("Sistema DigiTeca - Limpar Estante");
		limparFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		limparFrame.setLocation(400, 200);

		// Container
		Container ct = limparFrame.getContentPane();
		ct.setLayout(new BorderLayout());
		ct.setBackground(Color.white);

		// Painel
		JPanel painel = new JPanel();
		painel.setLayout(new FlowLayout());
		painel.setBackground(Color.white);

		// Rotulo e campo de texto
		JLabel verificacao = new JLabel("Digite o ID da estante (ISTO EXCLUIRA TODOS OS LIVROS DESSA ESTANTE):");
		JTextField campoVerificacao = new JTextField(10);
		painel.add(verificacao);
		painel.add(campoVerificacao);

		// Icone do rotulo incial (Limpar estante)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotLimparEstante.png");
		JLabel rotulo = new JLabel(iconeRotulo);
		rotulo.setBackground(Color.white);

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

		// Botoes de remover ou cancelar
		JPanel painelBotoes = new JPanel();
		Icon icone1 = new ImageIcon("src/aplicacao/icones/iconeEntrar.png");
		Icon icone2 = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
		JButton botaoLimpar = new JButton("Limpar", icone1);
		JButton botaoCancelar = new JButton("Cancelar", icone2);
		painelBotoes.add(botaoCancelar);
		painelBotoes.add(botaoLimpar);
		painelBotoes.setBackground(Color.white);

		ct.add(rotulo, BorderLayout.NORTH);
		ct.add(painel, BorderLayout.CENTER);
		ct.add(painelBotoes, BorderLayout.SOUTH);
		ct.add(panelWest, BorderLayout.WEST);
		ct.add(panelEast, BorderLayout.EAST);

		// Tratando o evento do botoo limpar
		BotaoLimparEstanteListener ouvinteLimpar = new BotaoLimparEstanteListener(campoVerificacao, limparFrame);
		botaoLimpar.addActionListener(ouvinteLimpar);

		// Tratando o evento do botoo cancelar
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(limparFrame);
		botaoCancelar.addActionListener(ouvinteCancelar);

		limparFrame.pack();
		limparFrame.setResizable(false);
		limparFrame.setVisible(true);
	}

	// Usando uma classe interna para tratar o evento do "botao limpar" para limpar
	// a estante
	private class BotaoLimparEstanteListener implements ActionListener {
		private FacadeAdministrador fachadaAdm = new FacadeAdministrador();
		private JTextField campoTexto;
		private JFrame janela;

		public BotaoLimparEstanteListener(JTextField campoTexto, JFrame janela) {
			this.campoTexto = campoTexto;
			this.janela = janela;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			fachadaAdm.limparEstante(campoTexto.getText());
			janela.dispose();
		}
	}

}
