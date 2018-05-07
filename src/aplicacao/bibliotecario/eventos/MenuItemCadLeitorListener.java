package aplicacao.bibliotecario.eventos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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

import aplicacao.eventos_sistema.BotaoCancelarListener;
import aplicacao.eventos_sistema.EfetuarCadPessoaListener;


public class MenuItemCadLeitorListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		//Janela
		JFrame cadastroFrame = new JFrame();
		cadastroFrame.setTitle("Sistema DigiTeca - Cadastrar Leitor");
		cadastroFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cadastroFrame.setLocation(200,200);

		//Container
		Container ct = cadastroFrame.getContentPane();
		ct.setLayout(new BorderLayout());
		ct.setBackground(Color.white);
		
		//Painel de Texto (R�tulos e campos)
		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(6, 2));
		painel.setBackground(Color.white);

		JLabel cpf = new JLabel("CPF:");
		JTextField campoCpf = new JTextField(30);
		painel.add(cpf);
		painel.add(campoCpf);

		JLabel nome = new JLabel("Nome:");
		JTextField campoNome = new JTextField(30);
		painel.add(nome);
		painel.add(campoNome);

		JLabel dataNasc = new JLabel("Data de Nascimento:");
		JTextField campoDataNasc = new JTextField(20);
		painel.add(dataNasc);
		painel.add(campoDataNasc);

		JLabel endereco = new JLabel("Endereco:");
		JTextField campoEndereco = new JTextField(30);
		painel.add(endereco);
		painel.add(campoEndereco);

		JLabel email = new JLabel("E-mail:");
		JTextField campoEmail = new JTextField(30);
		painel.add(email);
		painel.add(campoEmail);

		JLabel telefone = new JLabel("Telefone:");
		JTextField campoTelefone = new JTextField(30);
		painel.add(telefone);
		painel.add(campoTelefone);

		//Icone do rotulo do inicial (Cadastrar Leitor)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotCadLeitor.png");
		JLabel rotulo = new JLabel(iconeRotulo);
		rotulo.setBackground(Color.white);

		//Painel de Bot�es
		JPanel painelBotoes = new JPanel();
		Icon icone1 = new ImageIcon("src/aplicacao/icones/iconeEntrar.png");
		Icon icone2 = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
		JButton botaoCadastrar = new JButton("Cadastrar", icone1);
		JButton botaoCancelar = new JButton("Cancelar", icone2);
		painelBotoes.add(botaoCancelar);
		painelBotoes.add(botaoCadastrar);
		painelBotoes.setBackground(Color.white);

		//Adcionando componentes ao container
		ct.add(rotulo, BorderLayout.NORTH);
		ct.add(painel, BorderLayout.CENTER);
		ct.add(painelBotoes, BorderLayout.SOUTH);
		
		//Mostrando a janela
		cadastroFrame.pack();
		cadastroFrame.setResizable(false);
		cadastroFrame.setVisible(true);
		
		//Tratando o evento do bot�o cadastrar
		BotaoCadastrarLeitorListener ouvinteCadastrar = new BotaoCadastrarLeitorListener(campoCpf,campoNome, campoEndereco,campoTelefone, campoEmail,campoDataNasc, cadastroFrame);
		botaoCadastrar.addActionListener(ouvinteCadastrar);
						
		//Tratando o evento do bot�o cancelar
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(cadastroFrame);
		botaoCancelar.addActionListener(ouvinteCancelar);
				
		cadastroFrame.pack();
		cadastroFrame.setResizable(false);
		cadastroFrame.setVisible(true);
				
		}	
			
		//Usando uma classe interna (aninhada) que herda de uma classe que efetua o cadastro de uma 
		private class BotaoCadastrarLeitorListener extends EfetuarCadPessoaListener {
			public BotaoCadastrarLeitorListener(JTextField cpf,JTextField nome, JTextField endereco,JTextField telefone,
					JTextField email, JTextField datNasc,JFrame janela) {
				super(cpf, nome, endereco, telefone, email, datNasc,janela);
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				//Metodo sobreescrito
				this.getFachadaBiblio().cadastrarLeitor(this.getCpf().getText(), this.getNome().getText(), 
						this.getEndereco().getText(), this.getTelefone().getText(), this.getEmail().getText(), this.getDatNasc().getText());
				this.getJanela().dispose();
			}	
	}			

}
