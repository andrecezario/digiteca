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

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logica.Funcionario;
import aplicacao.FacadeAdministrador;
import aplicacao.eventos_sistema.BotaoCancelarListener;
import aplicacao.eventos_sistema.EfetuarCadPessoaListener;

public class MenuItemAltDadosListener implements ActionListener {
	FacadeAdministrador fachadaAdm = new FacadeAdministrador();

	@Override
	public void actionPerformed(ActionEvent e) {
		// Janela
		JFrame atualizarFrame = new JFrame();
		atualizarFrame.setTitle("Sistema DigiTeca - Atualizar dados");
		atualizarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		atualizarFrame.setLocation(400, 200);

		// Container
		Container ct = atualizarFrame.getContentPane();
		ct.setLayout(new BorderLayout());
		ct.setBackground(Color.white);

		// Painel
		JPanel painel = new JPanel();
		painel.setLayout(new GridBagLayout());
		GridBagConstraints cst = new GridBagConstraints();
		painel.setBorder(BorderFactory.createTitledBorder("Novos dados"));
		painel.setBackground(Color.white);

		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.white);

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

		// O adm
		Funcionario adm = fachadaAdm.buscarAdministradorLogin("admin");

		// Os campos com os seus dados
		JLabel cpf = new JLabel("CPF:");
		JTextField campoCpf = new JTextField(adm.getCpf());
	    cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 0;
	    painel.add(cpf,cst);
	    cst.gridx = 1;
	    cst.gridy = 0;
		painel.add(campoCpf,cst);
		
		JLabel nome = new JLabel("Nome:");
		JTextField campoNome = new JTextField(adm.getNome());
	    cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 1;
	    painel.add(nome,cst);
	    cst.gridx = 1;
	    cst.gridy = 1;
		painel.add(campoNome,cst);

		JLabel datNasc = new JLabel("Data de Nascimento: ");
		JTextField campoDatNasc = new JTextField(adm.getDataNascimento());
	    cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 2;
	    painel.add(datNasc,cst);
	    cst.gridx = 1;
	    cst.gridy = 2;
		painel.add(campoDatNasc,cst);
		
		JLabel endereco = new JLabel("Endereco:");
		JTextField campoEndereco = new JTextField(adm.getEndereco());
	    cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 3;
	    painel.add(endereco,cst);
	    cst.gridx = 1;
	    cst.gridy = 3;
		painel.add(campoEndereco,cst);

		JLabel telefone = new JLabel("Telefone:");
		JTextField campoTelefone = new JTextField(adm.getTelefone());
	    cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 4;
	    painel.add(telefone,cst);
	    cst.gridx = 1;
	    cst.gridy = 4;
		painel.add(campoTelefone,cst);

		JLabel email = new JLabel("E-mail:");
		JTextField campoEmail = new JTextField(adm.getEmail());
	    cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 5;
	    painel.add(email,cst);
	    cst.gridx = 1;
	    cst.gridy = 5;
		painel.add(campoEmail,cst);

		JLabel login = new JLabel("Login:");
		JLabel campoLogin = new JLabel(adm.getNomeUsuario());
	    cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 6;
	    painel.add(login,cst);
	    cst.gridx = 1;
	    cst.gridy = 6;
		painel.add(campoLogin,cst);

		JLabel senha = new JLabel("Senha:");
		JLabel campoSenha = new JLabel(adm.getSenhaAcesso());
		cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 7;
	    painel.add(senha,cst);
	    cst.gridx = 1;
	    cst.gridy = 7;
		painel.add(campoSenha,cst);

		// Icone do rotulo incial (Alterar dados)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotAltDados.png");
		JLabel rotulo = new JLabel(iconeRotulo);
		rotulo.setBackground(Color.white);

		// Botoes
		Icon icone1 = new ImageIcon("src/aplicacao/icones/iconeEntrar.png");
		Icon icone2 = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
		JButton botaoAtualizar = new JButton("Atualizar", icone1);
		JButton botaoCancelar = new JButton("Cancelar", icone2);
		painelBotoes.add(botaoCancelar);
		painelBotoes.add(botaoAtualizar);

		// Adicionando componentes ao container
		ct.add(rotulo, BorderLayout.NORTH);
		ct.add(painel, BorderLayout.CENTER);
		ct.add(panelWest, BorderLayout.WEST);
		ct.add(panelEast, BorderLayout.EAST);
		ct.add(painelBotoes, BorderLayout.SOUTH);

		// Mostrando a janela
		atualizarFrame.pack();
		atualizarFrame.setResizable(false);
		atualizarFrame.setVisible(true);

		// Tratando o evento do botao atualizar
		BotaoAtualizarAdmListener ouvinteAtualizarAdm = new BotaoAtualizarAdmListener(campoCpf, campoNome,
				campoEndereco, campoTelefone, campoEmail, campoDatNasc, atualizarFrame);
		botaoAtualizar.addActionListener(ouvinteAtualizarAdm);

		// Tratando o evento do botao cancelar
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(atualizarFrame);
		botaoCancelar.addActionListener(ouvinteCancelar);

	}

	private class BotaoAtualizarAdmListener extends EfetuarCadPessoaListener {
		public BotaoAtualizarAdmListener(JTextField cpf, JTextField nome, JTextField endereco, JTextField telefone,
				JTextField email, JTextField datNasc, JFrame janela) {
			super(cpf, nome, endereco, telefone, email, datNasc, janela);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.getFachadaAdm().atualizarDados("Administrador", this.getCpf().getText(), this.getNome().getText(),
					this.getEndereco().getText(), this.getTelefone().getText(), this.getEmail().getText(),
					this.getDatNasc().getText());
			this.getJanela().dispose();
		}
	}

}
