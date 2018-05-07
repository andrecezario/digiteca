package aplicacao.bibliotecario.eventos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logica.Leitor;
import aplicacao.FacadeBibliotecario;
import aplicacao.eventos_sistema.BotaoCancelarListener;
import aplicacao.eventos_sistema.EfetuarCadPessoaListener;

public class MenuItemAtuaLeitorListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame buscarFrame = new JFrame();
		buscarFrame.setTitle("Sistema DigiTeca - Atualizar Leitor");
		buscarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buscarFrame.setLocation(200, 200);
		
		//Cont�iner
		Container ct = buscarFrame.getContentPane();
		ct.setLayout(new BorderLayout());
		ct.setBackground(Color.white);

		//Painel
		JPanel painel = new JPanel();
		painel.setLayout(new FlowLayout());
		painel.setBackground(Color.white);

		//R�tulo e campo de texto
		JLabel verificacao = new JLabel("Digite o CPF do leitor que deseja atualizar:");
		final JTextField campoVerificacao = new JTextField(10);
		painel.add(verificacao);
		painel.add(campoVerificacao);

		//Icone no rotulo incial (Atualizar leitor)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotAtuaLeitor.png");
		JLabel rotulo = new JLabel(iconeRotulo);
		rotulo.setBackground(Color.white);

		//Bot�es de buscar ou cancelar
		JPanel painelBotoes = new JPanel();
		Icon icone1 = new ImageIcon("src/aplicacao/icones/iconeEntrar.png");
		Icon icone2 = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
		JButton botaoBuscar = new JButton("Buscar", icone1);
		JButton botaoCancelar = new JButton("Cancelar", icone2);
		painelBotoes.add(botaoCancelar);
		painelBotoes.add(botaoBuscar);
		painelBotoes.setBackground(Color.white);

		ct.add(rotulo, BorderLayout.NORTH);
		ct.add(painel, BorderLayout.CENTER);
		ct.add(painelBotoes, BorderLayout.SOUTH);

		//Tratando o evento do botao buscar
		BotaoEditarLeitorListener ouvinteEditarBibliotecario = new BotaoEditarLeitorListener(campoVerificacao,buscarFrame);
		botaoBuscar.addActionListener(ouvinteEditarBibliotecario);
		
		//Tratando o evento do botao cancelar
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(buscarFrame);
		botaoCancelar.addActionListener(ouvinteCancelar);
		
		//Mostrando a janela
		buscarFrame.pack();
		buscarFrame.setResizable(false);
		buscarFrame.setVisible(true);
	}
	
	//Usando uma classe interna para tratar o evento do "botao buscar" para buscar um  leitor para atualizacao no sistema
	private class BotaoEditarLeitorListener implements ActionListener {
		private FacadeBibliotecario fachada = new FacadeBibliotecario();
		private JTextField campoTexto;
		private JFrame janela;
		
		public BotaoEditarLeitorListener(JTextField campoTexto, JFrame janela) {
			this.campoTexto = campoTexto;
			this.janela = janela;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			Leitor leitor = fachada.buscarLeitor(campoTexto.getText()); //Busca e retorna o leitor caso exista
			
			if(leitor == null) { //Quer dizer que nao existe um leitor com esse numero de CPF no sistema
				JOptionPane.showMessageDialog(null,"Leitor nao encontrado! Nao eh possivel atualiza-lo!", "ERRO", JOptionPane.ERROR_MESSAGE); 
			}
			else {	
				//Fechando a janela anterior para mostrar a tela de atualizacao
				janela.dispose();
				
				//Abrindo a nova janela
				JFrame resultadoFrame = new JFrame();
				resultadoFrame.setTitle("Sistema DigiTeca - Atualizar dados do leitor");
				resultadoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				resultadoFrame.setLocation(200, 200);
				
				Container ct = resultadoFrame.getContentPane();
				ct.setLayout(new BorderLayout());
				ct.setBackground(Color.white);
	
				JPanel painel = new JPanel();
				painel.setLayout(new GridLayout(6, 2));
				painel.setBackground(Color.white);
	
				JLabel cpf = new JLabel("CPF:");
				JLabel valorCpf = new JLabel(leitor.getCpf());
				painel.add(cpf);
				painel.add(valorCpf);
				
				JLabel nome = new JLabel("Nome:");
				JTextField campoNome = new JTextField(leitor.getNome());
				painel.add(nome);
				painel.add(campoNome);
				
				JLabel endereco = new JLabel("Endereco:");
				JTextField campoEndereco = new JTextField(leitor.getEndereco());
				painel.add(endereco);
				painel.add(campoEndereco);
				
				JLabel telefone = new JLabel("Telefone:");
				JTextField campoTelefone= new JTextField(leitor.getTelefone());
				painel.add(telefone);
				painel.add(campoTelefone);
				
				JLabel email = new JLabel("E-mail:");
				JTextField campoEmail = new JTextField(leitor.getEmail());
				painel.add(email);
				painel.add(campoEmail);
				
				JLabel datNasc = new JLabel("Data de Nascimento:");
				JLabel valorDatNasc = new JLabel(leitor.getDataNascimento());
				painel.add(datNasc);
				painel.add(valorDatNasc);
				
				Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotAtuaLeitor.png");
				JLabel rotulo = new JLabel(iconeRotulo);
				rotulo.setBackground(Color.white);
				
				JPanel painelBotoes = new JPanel();
				Icon icone1 = new ImageIcon("src/aplicacao/icones/iconeEntrar.png");
				Icon icone2 = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
				JButton botaoAtualizar= new JButton("Atualizar", icone1);
				JButton botaoCancelar = new JButton("Cancelar", icone2);
				painelBotoes.add(botaoCancelar);
				painelBotoes.add(botaoAtualizar);
				painelBotoes.setBackground(Color.white);
				
				ct.add(rotulo,BorderLayout.NORTH);
				ct.add(painel,BorderLayout.CENTER);
				ct.add(painelBotoes,BorderLayout.SOUTH);
				
				//Tratando o evento do botao atualizar
				JTextField campoCpf = new JTextField(leitor.getCpf());
				JTextField campoDatNasc = new JTextField (leitor.getDataNascimento());
				BotaoAtualizarLeitorListener ouvinteAtualizarBibliotecario = new BotaoAtualizarLeitorListener(campoCpf,campoNome, campoEndereco,campoTelefone, campoEmail,campoDatNasc,resultadoFrame);
				botaoAtualizar.addActionListener(ouvinteAtualizarBibliotecario);
				
				//Tratando o evento do botao cancelar
				BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(resultadoFrame);
				botaoCancelar.addActionListener(ouvinteCancelar);
				
				resultadoFrame.pack();
				resultadoFrame.setResizable(false);
				resultadoFrame.setVisible(true);
			}
		
		}
	
	}
	
	//Usando uma classe interna (aninhada) para tratar o evento do "botao atualizar" para atualizar um leitor no sistema
	private class BotaoAtualizarLeitorListener extends EfetuarCadPessoaListener {
	
		public BotaoAtualizarLeitorListener(JTextField cpf, JTextField nome, JTextField endereco,JTextField telefone,
				JTextField email, JTextField datNasc, JFrame janela) {
			super(cpf, nome, endereco,telefone, email, datNasc, janela);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.getFachadaBiblio().atualizarDadosLeitor(this.getCpf().getText(), this.getNome().getText(), 
					this.getEndereco().getText(),this.getTelefone().getText(), this.getEmail().getText());
			this.getJanela().dispose();
		}
		
	}

}

