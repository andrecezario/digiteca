package aplicacao.bibliotecario.eventos;

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

public class MenuItemAltSenhaListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		//Janela
		JFrame alterarFrame = new JFrame();
		alterarFrame.setTitle("Sistema DigiTeca - Alterar Senha");
		alterarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		alterarFrame.setLocation(200, 200);
		
		//Cont�iner
		Container ct = alterarFrame.getContentPane();
		ct.setLayout(new BorderLayout());
		ct.setBackground(Color.white);

		//Painel
		JPanel painel = new JPanel();
		painel.setLayout(new FlowLayout());
		painel.setBackground(Color.white);

		//R�tulo e campo de texto
		JLabel login = new JLabel("Login:");
		final JTextField campoLogin = new JTextField(10);
		painel.add(login);
		painel.add(campoLogin);
		
		JLabel senhaAntiga = new JLabel("Senha antiga:");
		final JTextField campoSenhaAntiga = new JTextField(10);
		painel.add(senhaAntiga);
		painel.add(campoSenhaAntiga);
		
		JLabel novaSenha = new JLabel("Nova senha:");
		final JTextField campoNovaSenha = new JTextField(10);
		painel.add(novaSenha);
		painel.add(campoNovaSenha);

		//Icone do rotulo incial (Alterar Senha)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotAltSenha.png");
		JLabel rotulo = new JLabel(iconeRotulo);
		rotulo.setBackground(Color.white);

		//Bot�es de alterar ou cancelar
		JPanel painelBotoes = new JPanel();
		Icon icone1 = new ImageIcon("src/aplicacao/icones/iconeEntrar.png");
		Icon icone2 = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
		JButton botaoAlterarSenha= new JButton("Alterar", icone1);
		JButton botaoCancelar = new JButton("Cancelar", icone2);
		painelBotoes.add(botaoCancelar);
		painelBotoes.add(botaoAlterarSenha);
		painelBotoes.setBackground(Color.white);
		
		ct.add(rotulo, BorderLayout.NORTH);
		ct.add(painel, BorderLayout.CENTER);
		ct.add(painelBotoes, BorderLayout.SOUTH);
		
		alterarFrame.pack();
		alterarFrame.setResizable(false);
		alterarFrame.setVisible(true);
		
		//Tratamento de eventos
		BotaoAlterarSenha ouvinteAlterarSenha = new BotaoAlterarSenha(campoLogin, campoSenhaAntiga, campoNovaSenha, alterarFrame);
		botaoAlterarSenha.addActionListener(ouvinteAlterarSenha);
		
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(alterarFrame);
		botaoCancelar.addActionListener(ouvinteCancelar);
		
	}
	private class BotaoAlterarSenha implements ActionListener {
		FacadeAdministrador fachadaAdm = new FacadeAdministrador();
		JTextField senhaAntiga;
		JTextField login;
		JTextField novaSenha;
		JFrame janela;
		
		public BotaoAlterarSenha(JTextField login, JTextField senhaAntiga, JTextField novaSenha, JFrame janela) {
			this.login = login;
			this.senhaAntiga = senhaAntiga;
			this.novaSenha = novaSenha;
			this.janela = janela;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			fachadaAdm.atualizarSenhaAcesso(login.getText(), senhaAntiga.getText(), novaSenha.getText());
			janela.dispose();
		}
	}

}
