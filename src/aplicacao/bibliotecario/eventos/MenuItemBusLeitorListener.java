package aplicacao.bibliotecario.eventos;

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

import logica.Leitor;
import aplicacao.FacadeBibliotecario;
import aplicacao.eventos_sistema.BotaoCancelarListener;

public class MenuItemBusLeitorListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		//Janela
		JFrame buscarFrame = new JFrame();
		buscarFrame.setTitle("Sistema DigiTeca - Buscar Leitor");
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
		// Mascaras
		MaskFormatter mascaraCpf = null;

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setPlaceholderCharacter('_');
		} catch (ParseException excp) {
			JOptionPane.showMessageDialog(null, excp.getMessage());
			System.exit(-1);
		}

		// Seta as máscaras nos objetos JFormattedTextField
		JFormattedTextField campoCpf = new JFormattedTextField(mascaraCpf);

		JLabel verificacao = new JLabel("Digite o CPF do leitor que deseja buscar:");
		painel.add(verificacao);
		painel.add(campoCpf);

		//Icone do rotulo incial (Buscar leitor)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotBuscLeitor.png");
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
		BotaoBuscarLeitorListener ouvinteBuscarFuncionario = new BotaoBuscarLeitorListener(campoCpf,buscarFrame);
		botaoBuscar.addActionListener(ouvinteBuscarFuncionario);
		
		//Tratando o evento do botao cancelar
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(buscarFrame);
		botaoCancelar.addActionListener(ouvinteCancelar);
		
		//Mostrando a janela
		buscarFrame.pack();
		buscarFrame.setResizable(false);
		buscarFrame.setVisible(true);
	}
	
	//Usando uma classe interna para tratar o evento do "botao buscar" para buscar um bibliotecario no sistema
	private class BotaoBuscarLeitorListener implements ActionListener {
		private FacadeBibliotecario fachada = new FacadeBibliotecario();
		private JTextField campoTexto;
		private JFrame janela;
		
		public BotaoBuscarLeitorListener(JTextField campoTexto, JFrame janela) {
			this.campoTexto = campoTexto;
			this.janela = janela;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			Leitor leitor = fachada.buscarLeitor(campoTexto.getText());

			if(leitor == null || leitor.getCpf().equals(null)) { //Quer dizer que nao existe um leitor com esse numero de CPF no sistema
				JOptionPane.showMessageDialog(null,"Leitor não encontrado!", "ERRO", JOptionPane.ERROR_MESSAGE);
			}
			
			else {	
				//Fechando a janela anterior para mostrar o resultado
				janela.dispose();
				
				//Abrindo a nova janela com o resultado da busca
				JFrame resultadoFrame = new JFrame();
				resultadoFrame.setTitle("Sistema DigiTeca - Dados do Leitor");
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
				painel.setBorder(BorderFactory.createTitledBorder("Dados do Leitor"));
				
				JLabel cpf = new JLabel("CPF:");
				JLabel valorCpf = new JLabel(leitor.getCpf());
				cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 0;
			    painel.add(cpf,cst);
			    cst.gridx = 1;
			    cst.gridy = 0;
				painel.add(valorCpf,cst);
				
				JLabel nome = new JLabel("Nome:");
				JLabel valorNome = new JLabel(leitor.getNome());
				cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 1;
			    painel.add(nome,cst);
			    cst.gridx = 1;
			    cst.gridy = 1;
				painel.add(valorNome,cst);
				
				JLabel datNasc = new JLabel("Data de Nascimento:");
				JLabel valorDatNasc = new JLabel(leitor.getDataNascimento());
				cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 2;
			    painel.add(datNasc,cst);
			    cst.gridx = 1;
			    cst.gridy = 2;
				painel.add(valorDatNasc,cst);
				
				JLabel endereco = new JLabel("Endereco:");
				JLabel valorEndereco = new JLabel(leitor.getEndereco());
				cst.fill = GridBagConstraints.HORIZONTAL;
				cst.gridx = 0;
				cst.gridy = 3;
				painel.add(endereco, cst);
				cst.gridx = 1;
				cst.gridy = 3;
				painel.add(valorEndereco, cst);
				
				JLabel telefone = new JLabel("Telefone:");
				JLabel valorTelefone= new JLabel(leitor.getTelefone());
				cst.fill = GridBagConstraints.HORIZONTAL;
				cst.gridx = 0;
				cst.gridy = 4;
				painel.add(telefone, cst);
				cst.gridx = 1;
				cst.gridy = 4;
				painel.add(valorTelefone, cst);
				
				JLabel email = new JLabel("E-mail:");
				JLabel valorEmail = new JLabel(leitor.getEmail());
				cst.fill = GridBagConstraints.HORIZONTAL;
				cst.gridx = 0;
				cst.gridy = 5;
				painel.add(email, cst);
				cst.gridx = 1;
				cst.gridy = 5;
				painel.add(valorEmail, cst);
				
				Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotConfLeitor.png");
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
