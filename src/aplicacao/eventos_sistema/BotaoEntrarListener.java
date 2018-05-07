package aplicacao.eventos_sistema;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import aplicacao.FacadeAdministrador;
import aplicacao.administrador.AdministradorFrame;
import aplicacao.bibliotecario.BibliotecarioFrame;

public class BotaoEntrarListener implements ActionListener {
	private FacadeAdministrador fachadaAdm = new FacadeAdministrador();
	private JTextField login;
	private JPasswordField senha;
	private JFrame janela;

	public BotaoEntrarListener(JTextField login, JPasswordField senha,
			JFrame janela) {
		this.login = login;
		this.senha = senha;
		this.janela = janela;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = new String(senha.getPassword());
		if (fachadaAdm.efetuarLogin(login.getText(), s) == true) {
			if (login.getText().equals("admin")) {
				AdministradorFrame administradorFrame = new AdministradorFrame();
				administradorFrame.setTitle("Sistema DigiTeca - Administrador");
				administradorFrame
						.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				Container ct = administradorFrame.getContentPane();
				ct.setLayout(new BorderLayout());
				ct.setBackground(Color.white);

				Icon img = new ImageIcon("src/aplicacao/icones/logoMaior.png");
				JLabel logo = new JLabel(img);
				
				Icon img2 = new ImageIcon("src/aplicacao/icones/background.jpg");
				JLabel background = new JLabel(img2);

				JPanel painelImagem = new JPanel();
				painelImagem.add(logo);
				painelImagem.setBackground(Color.white);
				
				JPanel painelImagem2 = new JPanel();
				painelImagem2.setPreferredSize(new Dimension(50,50));
				Color c = new Color(79,78,71);  
				painelImagem2.setBackground(c);

				ct.add(painelImagem,BorderLayout.NORTH);
				ct.add(painelImagem2,BorderLayout.SOUTH);

				administradorFrame.setVisible(true);
				administradorFrame.pack();
				administradorFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}

			else {
				BibliotecarioFrame bibliotecarioFrame = new BibliotecarioFrame();
				bibliotecarioFrame.setTitle("Sistema DigiTeca - Bibliotecario");
				bibliotecarioFrame
						.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				bibliotecarioFrame.setLocation(100, 100);

				Container ct = bibliotecarioFrame.getContentPane();
				ct.setLayout(new FlowLayout());
				ct.setBackground(Color.white);

				Icon img = new ImageIcon("src/aplicacao/icones/logoMaior.png");
				JLabel logo = new JLabel(img);

				logo.setBackground(Color.white);

				ct.add(logo);

				bibliotecarioFrame.setVisible(true);
				bibliotecarioFrame.pack();
				bibliotecarioFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
			janela.dispose();
		}
	}
}
