package aplicacao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import aplicacao.eventos_sistema.BotaoCancelarListener;
import aplicacao.eventos_sistema.BotaoEntrarListener;

public class BibliotecaGUI {
	 public BibliotecaGUI() {
		JFrame janela = new JFrame("Sistema DigiTeca - Bem-Vindo");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setLocation(350,30);
		janela.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("iconApp.png")));
		Font font = new Font("Arial", Font.PLAIN, 14);
		setDefaultFont(font);
		Container ct1 = new JFrame().getContentPane();
		ct1.setLayout(new BorderLayout());
		Container ctPrincipal = janela.getContentPane();

		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.white);

		Icon iconeEntrar = new ImageIcon("src/aplicacao/icones/iconEntrarSistema.png");
		Icon iconeFechar = new ImageIcon("src/aplicacao/icones/iconeSairSistema.png");

		JButton botaoFechar = new JButton("Sair",iconeFechar);
		painelBotoes.add(botaoFechar);
		
		JButton botaoEntrar = new JButton("Entrar",iconeEntrar);
		painelBotoes.add(botaoEntrar);

		Icon imgLogo = new ImageIcon("src/aplicacao/icones/logoMenor.png");
		JLabel logo = new JLabel(imgLogo);
		JPanel painelImagem = new JPanel();
		painelImagem.add(logo);
		painelImagem.setBackground(Color.white);

		ctPrincipal.add(painelImagem, BorderLayout.CENTER);
		ctPrincipal.add(painelBotoes, BorderLayout.SOUTH);

		janela.pack();
		janela.setVisible(true);
		janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//Tratando eventos
		BotaoEntrarSistemaListener ouvinteEntrar = new BotaoEntrarSistemaListener(janela);
		botaoEntrar.addActionListener(ouvinteEntrar);
		
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(janela);
		botaoFechar.addActionListener(ouvinteCancelar);

	}
	
	private class BotaoEntrarSistemaListener implements ActionListener {
		private JFrame janelaPrincipal;
		public BotaoEntrarSistemaListener(JFrame janelaPrincipal) {
			this.janelaPrincipal = janelaPrincipal;
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame janela = new JFrame("Sistema DigiTeca - Autenticacao");
			janela.setLocation(200, 200);
			janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			Container ct1 = new JFrame().getContentPane();
			ct1.setLayout(new BorderLayout());
			Container ctPrincipal = janela.getContentPane();

			JPanel painelLogin = new JPanel();
			JPanel painelSenha = new JPanel();
			JPanel painelBotoes = new JPanel();
			painelLogin.setBackground(Color.white);
			painelSenha.setBackground(Color.white);
			painelBotoes.setBackground(Color.white);

			Icon iconeLogin = new ImageIcon("src/aplicacao/icones/iconeLogin.png");
			Icon iconeSenha = new ImageIcon("src/aplicacao/icones/iconeSenha.png");

			JLabel login = new JLabel("Login:");
			login.setIcon(iconeLogin);
			JTextField campoLogin = new JTextField(30);
			painelLogin.add(login);
			painelLogin.add(campoLogin);
			painelLogin.setBackground(Color.white);

			JLabel senha = new JLabel("Senha:");
			senha.setIcon(iconeSenha);
			JPasswordField campoSenha = new JPasswordField(30);
			painelSenha.add(senha);
			painelSenha.add(campoSenha);
			painelSenha.setBackground(Color.white);

			Icon icone1 = new ImageIcon("src/aplicacao/icones/iconeEntrar.png");
			Icon icone2 = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
			JButton botaoCancelar = new JButton("Cancelar", icone2);
			JButton botaoEntrar = new JButton("Entrar", icone1);
			painelBotoes.add(botaoCancelar);
			painelBotoes.add(botaoEntrar);
			painelBotoes.setBackground(Color.white);

			Icon img = new ImageIcon("src/aplicacao/icones/logoMenor.png");
			JLabel logo = new JLabel(img);
			JPanel painelImagem = new JPanel();
			painelImagem.add(logo);
			painelImagem.setBackground(Color.white);

			ct1.add(painelImagem, BorderLayout.NORTH);
			ct1.add(painelLogin, BorderLayout.CENTER);
			ct1.add(painelSenha, BorderLayout.SOUTH);

			ctPrincipal.add(ct1, BorderLayout.NORTH);
			ctPrincipal.add(painelBotoes, BorderLayout.CENTER);

			// Tratamento de eventos
			BotaoEntrarListener ouvinteEntrar = new BotaoEntrarListener(campoLogin, campoSenha, janela);
			botaoEntrar.addActionListener(ouvinteEntrar);

			BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(janela);
			botaoCancelar.addActionListener(ouvinteCancelar);

			janela.pack();
			janela.setVisible(true);
			janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
			
			janelaPrincipal.dispose();
		}
	}
	
	public static void main(String[] args) {
		BibliotecaGUI minhaAplicacao = new BibliotecaGUI();
	}
	
	public void setDefaultFont(Font defaultFont) {

		FontUIResource font = new FontUIResource(defaultFont);

		Enumeration<?> uiManagerKeys = UIManager.getDefaults().keys();
		while (uiManagerKeys.hasMoreElements()) {
			Object key = uiManagerKeys.nextElement(), value = UIManager.get(key);

			if (null != value && value instanceof FontUIResource)
				UIManager.put(key, font);
		}
	}
}
