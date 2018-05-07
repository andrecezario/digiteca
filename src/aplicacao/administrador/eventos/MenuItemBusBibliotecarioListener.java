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
import javax.swing.border.Border;

import logica.Funcionario;
import aplicacao.FacadeAdministrador;
import aplicacao.eventos_sistema.BotaoCancelarListener;

public class MenuItemBusBibliotecarioListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		//Janela
		JFrame buscarFrame = new JFrame();
		buscarFrame.setTitle("Sistema DigiTeca - Buscar Bibliotecario");
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
		JLabel verificacao = new JLabel("Digite o CPF do bibliotecario que deseja buscar:");
		final JTextField campoVerificacao = new JTextField(10);
		painel.add(verificacao);
		painel.add(campoVerificacao);

		//Icone do r�tulo incial (Buscar bibliotecario)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotBusBibliotecario.png");
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
		BotaoBuscarBibliotecarioListener ouvinteBuscarFuncionario = new BotaoBuscarBibliotecarioListener(campoVerificacao,buscarFrame);
		botaoBuscar.addActionListener(ouvinteBuscarFuncionario);
		
		//Tratando o evento do botao cancelar
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(buscarFrame);
		botaoCancelar.addActionListener(ouvinteCancelar);
		
		//Mostrando a janela
		buscarFrame.pack();
		buscarFrame.setResizable(false);
		buscarFrame.setVisible(true);
	}
	
	//Usando uma classe interna (aninhada) para tratar o evento do "botao buscar" para buscar um bibliotecario no sistema
	private class BotaoBuscarBibliotecarioListener implements ActionListener {
		private FacadeAdministrador fachada = new FacadeAdministrador();
		private JTextField campoTexto;
		private JFrame janela;
		
		public BotaoBuscarBibliotecarioListener(JTextField campoTexto, JFrame janela) {
			this.campoTexto = campoTexto;
			this.janela = janela;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			Funcionario b = fachada.buscarFuncionario(campoTexto.getText(),"Bibliotecario");
			
			if(b.getCpf() == null || b.getNomeUsuario().equals("admin")) { //Quer dizer que nao existe um bibliotecario com esse numero de CPF no sistema
				JOptionPane.showMessageDialog(null,"Bibliotecario nao encontrado!", "ERRO", JOptionPane.ERROR_MESSAGE);
			}
			else if(b.getNomeUsuario().startsWith("bibli")) {
				//Fechando a janela anterior para mostrar o resultado
				janela.dispose();
				
				//Abrindo a nova janela com o resultado da busca
				JFrame resultadoFrame = new JFrame();
				resultadoFrame.setTitle("Sistema DigiTeca - Dados do Bibliotecario");
				resultadoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				resultadoFrame.setLocation(400, 200);
				
				Container ct = resultadoFrame.getContentPane();
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
	
				JPanel painel = new JPanel();
				painel.setLayout(new GridBagLayout());
				GridBagConstraints cst = new GridBagConstraints();
				painel.setBackground(Color.white);
				painel.setBorder(BorderFactory.createTitledBorder("Dados do Bibliotecário"));
	
				JLabel cpf = new JLabel("CPF:");
				JLabel valorCpf = new JLabel(b.getCpf());
				cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 0;
			    painel.add(cpf,cst);
			    cst.gridx = 1;
			    cst.gridy = 0;
				painel.add(valorCpf,cst);
				
				JLabel nome = new JLabel("Nome:");
				JLabel valorNome = new JLabel(b.getNome());
				cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 1;
			    painel.add(nome,cst);
			    cst.gridx = 1;
			    cst.gridy = 1;
				painel.add(valorNome,cst);
				
				JLabel datNasc = new JLabel("Data de Nascimento:");
				JLabel valorDatNasc = new JLabel(b.getDataNascimento());
				cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 2;
			    painel.add(datNasc,cst);
			    cst.gridx = 1;
			    cst.gridy = 2;
				painel.add(valorDatNasc,cst);
				
				JLabel endereco = new JLabel("Endereco:");
				JLabel valorEndereco = new JLabel(b.getEndereco());
				cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 3;
			    painel.add(endereco,cst);
			    cst.gridx = 1;
			    cst.gridy = 3;
				painel.add(valorEndereco,cst);
				
				JLabel email = new JLabel("E-mail:");
				JLabel valorEmail = new JLabel(b.getEmail());
				cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 5;
			    painel.add(email,cst);
			    cst.gridx = 1;
			    cst.gridy = 5;
				painel.add(valorEmail,cst);
				
				JLabel telefone = new JLabel("Telefone:");
				JLabel valorTelefone= new JLabel(b.getTelefone());
				cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 4;
			    painel.add(telefone,cst);
			    cst.gridx = 1;
			    cst.gridy = 4;
				painel.add(valorTelefone,cst);
				
				Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotConfBibliotecario.png");
				JLabel rotulo = new JLabel(iconeRotulo);
				rotulo.setBackground(Color.white);
				
				JPanel painelBotoes = new JPanel();
				Icon iconeFechar = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
				JButton botaoFechar = new JButton("Fechar", iconeFechar);
				painelBotoes.add(botaoFechar);
				painelBotoes.setBackground(Color.white);
				
				ct.add(rotulo,BorderLayout.NORTH);
				ct.add(painel,BorderLayout.CENTER);
				ct.add(panelWest, BorderLayout.WEST);
				ct.add(panelEast, BorderLayout.EAST);
				ct.add(painelBotoes,BorderLayout.SOUTH);
				
				resultadoFrame.pack();
				resultadoFrame.setResizable(false);
				resultadoFrame.setVisible(true);
				
				//Tratando o evento do botao fechar
				BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(resultadoFrame);
				botaoFechar.addActionListener(ouvinteCancelar);
			}
		}
	
	}

}
