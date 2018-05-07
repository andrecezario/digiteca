package aplicacao.bibliotecario.eventos;

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
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import aplicacao.eventos_sistema.BotaoCancelarListener;
import aplicacao.eventos_sistema.EfetuarCadPessoaListener;

public class MenuItemCadLeitorListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// Janela
		JFrame cadastroFrame = new JFrame();
		cadastroFrame.setTitle("Sistema DigiTeca - Cadastrar Leitor");
		cadastroFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cadastroFrame.setLocation(400, 200);

		// Container
		Container ct = cadastroFrame.getContentPane();
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

		// Painel de Texto (Rotulos e campos)
		JPanel painel = new JPanel();
		painel.setLayout(new GridBagLayout());
		GridBagConstraints cst = new GridBagConstraints();
		painel.setBackground(Color.white);
		painel.setBorder(BorderFactory.createTitledBorder("Novo Leitor"));

		// Mascaras
		MaskFormatter mascaraTel = null;
		MaskFormatter mascaraCpf = null;
		MaskFormatter mascaraData = null;

		try {
			mascaraTel = new MaskFormatter("## #####-####");
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraData = new MaskFormatter("##/##/####");
			mascaraTel.setPlaceholderCharacter('_');
			mascaraCpf.setPlaceholderCharacter('_');
			mascaraData.setPlaceholderCharacter('_');
		} catch (ParseException excp) {
			JOptionPane.showMessageDialog(null, excp.getMessage());
			System.exit(-1);
		}

		// Seta as máscaras nos objetos JFormattedTextField
		JFormattedTextField campoTelefone = new JFormattedTextField(mascaraTel);
		JFormattedTextField campoCpf = new JFormattedTextField(mascaraCpf);
		JFormattedTextField campoDataNasc = new JFormattedTextField(mascaraData);

		JLabel cpf = new JLabel("CPF:");
		cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 0;
	    painel.add(cpf,cst);
	    cst.gridx = 1;
	    cst.gridy = 0;
		painel.add(campoCpf,cst);

		JLabel nome = new JLabel("Nome:");
		JTextField campoNome = new JTextField(30);
		cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 1;
	    painel.add(nome,cst);
	    cst.gridx = 1;
	    cst.gridy = 1;
		painel.add(campoNome,cst);

		JLabel dataNasc = new JLabel("Data de Nascimento:");
		cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 2;
	    painel.add(dataNasc,cst);
	    cst.gridx = 1;
	    cst.gridy = 2;
		painel.add(campoDataNasc,cst);

		JLabel endereco = new JLabel("Endereco:");
		JTextField campoEndereco = new JTextField(30);
		cst.fill = GridBagConstraints.HORIZONTAL;
		cst.gridx = 0;
		cst.gridy = 3;
		painel.add(endereco, cst);
		cst.gridx = 1;
		cst.gridy = 3;
		painel.add(campoEndereco, cst);

		JLabel telefone = new JLabel("Telefone:");
		cst.fill = GridBagConstraints.HORIZONTAL;
		cst.gridx = 0;
		cst.gridy = 4;
		painel.add(telefone, cst);
		cst.gridx = 1;
		cst.gridy = 4;
		painel.add(campoTelefone, cst);
				
		JLabel email = new JLabel("E-mail:");
		JTextField campoEmail = new JTextField(30);
		cst.fill = GridBagConstraints.HORIZONTAL;
		cst.gridx = 0;
		cst.gridy = 5;
		painel.add(email, cst);
		cst.gridx = 1;
		cst.gridy = 5;
		painel.add(campoEmail, cst);

		// Icone do rotulo do inicial (Cadastrar Leitor)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotCadLeitor.png");
		JLabel rotulo = new JLabel(iconeRotulo);
		rotulo.setBackground(Color.white);

		// Painel de Botoes
		JPanel painelBotoes = new JPanel();
		Icon icone1 = new ImageIcon("src/aplicacao/icones/iconeEntrar.png");
		Icon icone2 = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
		JButton botaoCadastrar = new JButton("Cadastrar", icone1);
		JButton botaoCancelar = new JButton("Cancelar", icone2);
		painelBotoes.add(botaoCancelar);
		painelBotoes.add(botaoCadastrar);
		painelBotoes.setBackground(Color.white);

		// Adcionando componentes ao container
		ct.add(rotulo, BorderLayout.NORTH);
		ct.add(painel, BorderLayout.CENTER);
		ct.add(panelWest, BorderLayout.WEST);
		ct.add(panelEast, BorderLayout.EAST);
		ct.add(painelBotoes, BorderLayout.SOUTH);

		// Mostrando a janela
		cadastroFrame.pack();
		cadastroFrame.setResizable(false);
		cadastroFrame.setVisible(true);

		// Tratando o evento do botao cadastrar
		BotaoCadastrarLeitorListener ouvinteCadastrar = new BotaoCadastrarLeitorListener(campoCpf, campoNome,
				campoEndereco, campoTelefone, campoEmail, campoDataNasc, cadastroFrame);
		botaoCadastrar.addActionListener(ouvinteCadastrar);

		// Tratando o evento do botao cancelar
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(cadastroFrame);
		botaoCancelar.addActionListener(ouvinteCancelar);

		cadastroFrame.pack();
		cadastroFrame.setResizable(false);
		cadastroFrame.setVisible(true);

	}

	// Usando uma classe interna (aninhada) que herda de uma classe que efetua o
	// cadastro de uma
	private class BotaoCadastrarLeitorListener extends EfetuarCadPessoaListener {
		public BotaoCadastrarLeitorListener(JTextField cpf, JTextField nome, JTextField endereco, JTextField telefone,
				JTextField email, JTextField datNasc, JFrame janela) {
			super(cpf, nome, endereco, telefone, email, datNasc, janela);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// Metodo sobreescrito
			this.getFachadaBiblio().cadastrarLeitor(this.getCpf().getText(), this.getNome().getText(),
					this.getEndereco().getText(), this.getTelefone().getText(), this.getEmail().getText(),
					this.getDatNasc().getText());
			this.getJanela().dispose();
		}
	}

}
