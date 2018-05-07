package aplicacao.administrador.eventos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logica.Funcionario;
import aplicacao.FacadeAdministrador;
import aplicacao.eventos_sistema.BotaoCancelarListener;
import aplicacao.eventos_sistema.EfetuarCadPessoaListener;

public class MenuItemAtuaBibliotecarioListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		//Janela
		JFrame buscarFrame = new JFrame();
		buscarFrame.setTitle("Sistema DigiTeca - Atualizar Bibliotecario");
		buscarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buscarFrame.setLocation(400, 200);
		
		//Container
		Container ct = buscarFrame.getContentPane();
		ct.setLayout(new BorderLayout());
		ct.setBackground(Color.white);

		//Painel
		JPanel painel = new JPanel();
		painel.setLayout(new FlowLayout());
		painel.setBackground(Color.white);

		//Rotulo e campo de texto
		JLabel verificacao = new JLabel("Digite o CPF do bibliotecario que deseja atualizar:");
		final JTextField campoVerificacao = new JTextField(10);
		painel.add(verificacao);
		painel.add(campoVerificacao);

		//Icone no rotulo incial (Atualizar bibliotecario)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotAtuaBibliotecario.png");
		JLabel rotulo = new JLabel(iconeRotulo);
		rotulo.setBackground(Color.white);

		//Botoes de buscar ou cancelar
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
		BotaoEditarBibliotecarioListener ouvinteEditarBibliotecario = new BotaoEditarBibliotecarioListener(campoVerificacao,buscarFrame);
		botaoBuscar.addActionListener(ouvinteEditarBibliotecario);
		
		//Tratando o evento do botao cancelar
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(buscarFrame);
		botaoCancelar.addActionListener(ouvinteCancelar);
		
		//Mostrando a janela
		buscarFrame.pack();
		buscarFrame.setResizable(false);
		buscarFrame.setVisible(true);
	}
	
	//Usando uma classe interna para tratar o evento do "botao buscar" para buscar um  bibliotecario para atualizacao no sistema
	private class BotaoEditarBibliotecarioListener implements ActionListener {
		private FacadeAdministrador fachada = new FacadeAdministrador();
		private JTextField campoTexto;
		private JFrame janela;
		
		public BotaoEditarBibliotecarioListener(JTextField campoTexto, JFrame janela) {
			this.campoTexto = campoTexto;
			this.janela = janela;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			//Buscando o bibliotecario que se deseja atualizar, atraves do CPF passado
			Funcionario bibliotecario = fachada.buscarFuncionario(campoTexto.getText(), "Bibliotecario");
			
			if(bibliotecario == null ||bibliotecario.getNomeUsuario().equals("admin")) {
				JOptionPane.showMessageDialog(null,"Bibliotecario nao encontrado!", "ERRO", JOptionPane.ERROR_MESSAGE);
			}
			else if(bibliotecario.getNomeUsuario().startsWith("bibli")){
				//Janela
				JFrame resultadoFrame = new JFrame();
				resultadoFrame.setTitle("Sistema DigiTeca - Atualizar dados do bibliotecario");
				resultadoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				resultadoFrame.setLocation(400, 200);
				
				//Container
				Container ct = resultadoFrame.getContentPane();
				ct.setLayout(new BorderLayout());
				ct.setBackground(Color.white);
	
				//Paineis
				JPanel painel = new JPanel();
				painel.setLayout(new GridBagLayout());
				GridBagConstraints cst = new GridBagConstraints();
				painel.setBackground(Color.white);
				painel.setBorder(BorderFactory.createTitledBorder("Novos dados"));
				
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
				
				//Campos e rotulos
				JLabel cpf = new JLabel("CPF:");
				JLabel valorCpf = new JLabel(bibliotecario.getCpf()); //Por ser um JLabel nao aparecera no campo de texto, o que impedira a atualizacao desse campo
				cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 0;
			    painel.add(cpf,cst);
			    cst.gridx = 1;
			    cst.gridy = 0;
				painel.add(valorCpf,cst);
				
				JLabel nome = new JLabel("Nome:");
				JTextField campoNome = new JTextField(bibliotecario.getNome()); // Aparecera o campo de texto com o valor do campo registrado, que eh editavel
				cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 1;
			    painel.add(nome,cst);
			    cst.gridx = 1;
			    cst.gridy = 1;
				painel.add(campoNome,cst);
				
				JLabel datNasc = new JLabel("Data de Nascimento:");
				JLabel valorDatNasc = new JLabel(bibliotecario.getDataNascimento());
				cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 2;
			    painel.add(datNasc,cst);
			    cst.gridx = 1;
			    cst.gridy = 2;
				painel.add(valorDatNasc,cst);
							
				JLabel endereco = new JLabel("Endereco:");
				JTextField campoEndereco = new JTextField(bibliotecario.getEndereco());
			    cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 3;
			    painel.add(endereco,cst);
			    cst.gridx = 1;
			    cst.gridy = 3;
				painel.add(campoEndereco,cst);
				
				JLabel email = new JLabel("E-mail:");
				JTextField campoEmail = new JTextField(bibliotecario.getEmail());
			    cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 5;
			    painel.add(email,cst);
			    cst.gridx = 1;
			    cst.gridy = 5;
				painel.add(campoEmail,cst);
				
				JLabel telefone = new JLabel("Telefone:");
				JTextField campoTelefone= new JTextField(bibliotecario.getTelefone());
			    cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 4;
			    painel.add(telefone,cst);
			    cst.gridx = 1;
			    cst.gridy = 4;
				painel.add(campoTelefone,cst);
				
				Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotAtuaBibliotecario.png");
				JLabel rotulo = new JLabel(iconeRotulo);
				rotulo.setBackground(Color.white);
				
				Icon icone1 = new ImageIcon("src/aplicacao/icones/iconeEntrar.png");
				Icon icone2 = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
				JButton botaoAtualizar= new JButton("Atualizar", icone1);
				JButton botaoCancelar = new JButton("Cancelar", icone2);
				painelBotoes.add(botaoCancelar);
				painelBotoes.add(botaoAtualizar);
				
				ct.add(rotulo,BorderLayout.NORTH);
				ct.add(painel,BorderLayout.CENTER);
				ct.add(panelWest, BorderLayout.WEST);
				ct.add(panelEast, BorderLayout.EAST);
				ct.add(painelBotoes,BorderLayout.SOUTH);
				
				//Tratando o evento do botao atualizar
				JTextField campoCpf = new JTextField(bibliotecario.getCpf());
				JTextField campoDatNasc = new JTextField (bibliotecario.getDataNascimento());
				BotaoAtualizarBibliotecarioListener ouvinteAtualizarBibliotecario = new BotaoAtualizarBibliotecarioListener(campoCpf,campoNome,campoEndereco,campoTelefone,campoEmail,campoDatNasc,resultadoFrame);
				botaoAtualizar.addActionListener(ouvinteAtualizarBibliotecario);
				
				//Tratando o evento do botao cancelar
				BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(resultadoFrame);
				botaoCancelar.addActionListener(ouvinteCancelar);
				
				//Mostrando a janela
				resultadoFrame.pack();
				resultadoFrame.setResizable(false);
				resultadoFrame.setVisible(true);
				
				//Fechando a janela anterior para mostrar a tela de atualizacao
				janela.dispose();
			}
		}
	
	}
	
	//Usando uma classe interna para tratar o evento do "botao atualizar" para atualizar um bibliotecario no sistema
	private class BotaoAtualizarBibliotecarioListener extends EfetuarCadPessoaListener {
	
		public BotaoAtualizarBibliotecarioListener(JTextField cpf,
				JTextField nome, JTextField datNasc, JTextField endereco,
				JTextField email, JTextField telefone, JFrame janela) {
			super(cpf, nome, datNasc, endereco, email, telefone, janela);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.getFachadaAdm().atualizarDados("Bibliotecario",this.getCpf().getText(), this.getNome().getText(), this.getEndereco().getText(), this.getTelefone().getText(),this.getEmail().getText(), this.getDatNasc().getText());
			this.getJanela().dispose();
		}
		
	}

}


