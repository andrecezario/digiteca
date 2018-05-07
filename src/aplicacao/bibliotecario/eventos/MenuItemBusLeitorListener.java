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

public class MenuItemBusLeitorListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		//Janela
		JFrame buscarFrame = new JFrame();
		buscarFrame.setTitle("Sistema DigiTeca - Buscar Leitor");
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

		//Rotulo e campo de texto
		JLabel verificacao = new JLabel("Digite o CPF do leitor que deseja buscar:");
		final JTextField campoVerificacao = new JTextField(10);
		painel.add(verificacao);
		painel.add(campoVerificacao);

		//Icone do r�tulo incial (Buscar leitor)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotBuscLeitor.png");
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
		BotaoBuscarLeitorListener ouvinteBuscarFuncionario = new BotaoBuscarLeitorListener(campoVerificacao,buscarFrame);
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
				JOptionPane.showMessageDialog(null,"Leitor nao encontrado!", "ERRO", JOptionPane.ERROR_MESSAGE);
			}
			
			else {	
				//Fechando a janela anterior para mostrar o resultado
				janela.dispose();
				
				//Abrindo a nova janela com o resultado da busca
				JFrame resultadoFrame = new JFrame();
				resultadoFrame.setTitle("Sistema DigiTeca - Dados do Leitor");
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
				JLabel valorNome = new JLabel(leitor.getNome());
				painel.add(nome);
				painel.add(valorNome);
				
				JLabel datNasc = new JLabel("Data de Nascimento:");
				JLabel valorDatNasc = new JLabel(leitor.getDataNascimento());
				painel.add(datNasc);
				painel.add(valorDatNasc);
				
				JLabel endereco = new JLabel("Endereco:");
				JLabel valorEndereco = new JLabel(leitor.getEndereco());
				painel.add(endereco);
				painel.add(valorEndereco);
				
				JLabel email = new JLabel("E-mail:");
				JLabel valorEmail = new JLabel(leitor.getEmail());
				painel.add(email);
				painel.add(valorEmail);
				
				JLabel telefone = new JLabel("Telefone:");
				JLabel valorTelefone= new JLabel(leitor.getTelefone());
				painel.add(telefone);
				painel.add(valorTelefone);
				
				Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotConfLeitor.png");
				JLabel rotulo = new JLabel(iconeRotulo);
				rotulo.setBackground(Color.white);
				
				JPanel painelBotoes = new JPanel();
				Icon iconeFechar = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
				JButton botaoFechar = new JButton("Fechar", iconeFechar);
				painelBotoes.add(botaoFechar);
				
				ct.add(rotulo,BorderLayout.NORTH);
				ct.add(painel,BorderLayout.CENTER);
				ct.add(painelBotoes,BorderLayout.SOUTH);
				
				resultadoFrame.pack();
				resultadoFrame.setResizable(false);
				resultadoFrame.setVisible(true);
				
				//Tratando o evento do bot�o fechar
				BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(resultadoFrame);
				botaoFechar.addActionListener(ouvinteCancelar);
			}
		}
	
	}

}
