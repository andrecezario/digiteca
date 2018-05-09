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

public class MenuItemRegEstanteListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		// Janela
		JFrame cadastroFrame = new JFrame();
		cadastroFrame.setTitle("Sistema DigiTeca - Registrar Estante");
		cadastroFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cadastroFrame.setLocation(400, 200);

		// Container
		Container ct = cadastroFrame.getContentPane();
		ct.setLayout(new BorderLayout());
		ct.setBackground(Color.white);

		// Painel Texto
		MaskFormatter mascaraId = null;

		try {
			mascaraId = new MaskFormatter("U-###");
			mascaraId.setPlaceholderCharacter('_');
		} catch (ParseException excp) {
			JOptionPane.showMessageDialog(null, excp.getMessage());
			System.exit(-1);
		}

		// Seta as máscaras nos objetos JFormattedTextField
		JFormattedTextField campoIdEstante = new JFormattedTextField(mascaraId);
		
		JPanel painel = new JPanel();
		painel.setLayout(new GridBagLayout());
		GridBagConstraints cst = new GridBagConstraints();
		painel.setBackground(Color.white);
		painel.setBorder(BorderFactory.createTitledBorder("Nova Estante"));

		JLabel idEstante = new JLabel("ID da Estante: ");
		cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 0;
	    painel.add(idEstante,cst);
	    cst.gridx = 1;
	    cst.gridy = 0;
		painel.add(campoIdEstante,cst);

		JLabel categoria = new JLabel("Categoria:");
		JComboBox comboCategoria = new JComboBox();
		comboCategoria.addItem("Literario");
		comboCategoria.addItem("Didatico");
		comboCategoria.addItem("Profissionalizante");
		cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 1;
	    painel.add(categoria,cst);
	    cst.gridx = 1;
	    cst.gridy = 1;
		painel.add(comboCategoria,cst);

		// Icone do rotulo incial (Registrar Estante)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotRegistrarEstante.png");
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

		// Botoes
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.white);

		Icon icone1 = new ImageIcon("src/aplicacao/icones/iconeEntrar.png");
		Icon icone2 = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
		JButton botaoCadastrar = new JButton("Registrar", icone1);
		JButton botaoCancelar = new JButton("Cancelar", icone2);

		painelBotoes.add(botaoCancelar);
		painelBotoes.add(botaoCadastrar);

		ct.add(rotulo, BorderLayout.NORTH);
		ct.add(painel, BorderLayout.CENTER);
		ct.add(panelWest, BorderLayout.WEST);
		ct.add(panelEast, BorderLayout.EAST);
		ct.add(painelBotoes, BorderLayout.SOUTH);

		// Tratando o evento do botao cadastrar
		BotaoCadastrarEstanteListener ouvinteCadastrar = new BotaoCadastrarEstanteListener(campoIdEstante,
				comboCategoria, cadastroFrame);
		botaoCadastrar.addActionListener(ouvinteCadastrar);

		// Tratando o evento do botao cancelar
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(cadastroFrame);
		botaoCancelar.addActionListener(ouvinteCancelar);

		cadastroFrame.pack();
		cadastroFrame.setResizable(false);
		cadastroFrame.setVisible(true);
	}

	// Usando uma classe interna para tratar o evento do "botao cadastrar" para
	// registrar uma estante no sistema
	private class BotaoCadastrarEstanteListener implements ActionListener {
		private FacadeAdministrador fachadaAdm = new FacadeAdministrador();
		private JTextField idEstante;
		private JComboBox categoria;
		private JFrame janela;

		public BotaoCadastrarEstanteListener(JTextField idEstante, JComboBox categoria, JFrame janela) {
			super();
			this.idEstante = idEstante;
			this.categoria = categoria;
			this.janela = janela;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String valorCategoria = (String) categoria.getSelectedItem();
			fachadaAdm.cadastrarEstante(idEstante.getText(), valorCategoria);
			janela.dispose();
		}
	}

}
