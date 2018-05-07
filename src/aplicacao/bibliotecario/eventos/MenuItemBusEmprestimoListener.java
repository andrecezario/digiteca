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

import logica.Emprestimo;
import aplicacao.FacadeBibliotecario;
import aplicacao.eventos_sistema.BotaoCancelarListener;

public class MenuItemBusEmprestimoListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		//Janela
		JFrame removerFrame = new JFrame();
		removerFrame.setTitle("Sistema DigiTeca - Buscar Emprestimo");
		removerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		removerFrame.setLocation(200, 200);
		
		//Container
		Container ct = removerFrame.getContentPane();
		ct.setLayout(new BorderLayout());
		ct.setBackground(Color.white);

		//Painel
		JPanel painel = new JPanel();
		painel.setLayout(new FlowLayout());
		painel.setBackground(Color.white);

		//Rotulo e campo de texto
		JLabel verificacao = new JLabel("Digite o CPF do Leitor para a busca:");
		JTextField campoVerificacao = new JTextField(10);
		painel.add(verificacao);
		painel.add(campoVerificacao);

		//Icone no rotulo incial (Buscar Emprestimo)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotBusEmprestimo.png");
		JLabel rotulo = new JLabel(iconeRotulo);
		rotulo.setBackground(Color.white);

		//Bot�es de buscar ou cancelar
		JPanel painelBotoes = new JPanel();
		Icon icone1 = new ImageIcon("src/aplicacao/icones/iconeEntrar.png");
		Icon icone2 = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
		JButton botaoRemover = new JButton("Buscar", icone1);
		JButton botaoCancelar = new JButton("Cancelar", icone2);
		painelBotoes.add(botaoCancelar);
		painelBotoes.add(botaoRemover);
		painelBotoes.setBackground(Color.white);
		
		//Adicionando componentes ao container
		ct.add(rotulo, BorderLayout.NORTH);
		ct.add(painel, BorderLayout.CENTER);
		ct.add(painelBotoes, BorderLayout.SOUTH);

		//Tratando o evento do botao buscar
		BotaoBuscarEmprestimoListener ouvinteBuscar = new BotaoBuscarEmprestimoListener(campoVerificacao,removerFrame);
		botaoRemover.addActionListener(ouvinteBuscar);
		
		//Tratando o evento do botao cancelar
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(removerFrame);
		botaoCancelar.addActionListener(ouvinteCancelar);
		
		//Mostrando a janela
		removerFrame.pack();
		removerFrame.setResizable(false);
		removerFrame.setVisible(true);
	}
	
	//Usando uma classe interna para tratar o evento do "botao buscar" para remover uma estante no sistema
	private class BotaoBuscarEmprestimoListener implements ActionListener{
		private FacadeBibliotecario fachadaBiblio = new FacadeBibliotecario();
		private JTextField campoTexto;
		private JFrame janela;
		
		public BotaoBuscarEmprestimoListener(JTextField campoTexto, JFrame janela) {
			this.campoTexto = campoTexto;
			this.janela = janela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
//			int idEmprestimo = Integer.valueOf(campoTexto.getText());
			Emprestimo emprestimo = fachadaBiblio.buscarEmprestimo(campoTexto.getText());
			
			if(emprestimo == null) { //Quer dizer que nao existe um emprestimo com esse numero de ID no sistema
				JOptionPane.showMessageDialog(null,"Emprestimo nao encontrado!", "ERRO", JOptionPane.ERROR_MESSAGE);
			}
			else {
				//Fechando a janela anterior para mostrar o resultado
				janela.dispose();
				
				//Abrindo a nova janela com o resultado da busca
				JFrame resultadoFrame = new JFrame();
				resultadoFrame.setTitle("Sistema DigiTeca - Dados da Emprestimo");
				resultadoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				resultadoFrame.setLocation(200, 200);
				
				Container ct = resultadoFrame.getContentPane();
				ct.setLayout(new BorderLayout());
				ct.setBackground(Color.white);
	
				JPanel painel = new JPanel();
				painel.setLayout(new GridLayout(6, 2));
				painel.setBackground(Color.white);
	
				JLabel id = new JLabel("ID do emprestimo:");
				JLabel valorId = new JLabel(Integer.toString(emprestimo.getIdEmprestimo()));
				painel.add(id);
				painel.add(valorId);
				
				JLabel  cpfLeitor= new JLabel("CPF do Leitor:");
				JLabel valorCpfLeitor = new JLabel(emprestimo.getLeitor().getCpf());
				painel.add(cpfLeitor);
				painel.add(valorCpfLeitor);
				
				JLabel isbn = new JLabel("ISBN do Livro:");
				JLabel valorIsbn = new JLabel(emprestimo.getLivro().getIsbn());
				painel.add(isbn);
				painel.add(valorIsbn);
				
				JLabel  cpfBiblio= new JLabel("CPF do Bibliotecario:");
				JLabel valorCpfBiblio = new JLabel(emprestimo.getBibliotecario().getCpf());
				painel.add(cpfBiblio);
				painel.add(valorCpfBiblio);
				
				Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotConfEmprestimo.png");
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
				
				//Tratando o evento do botao fechar
				BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(resultadoFrame);
				botaoFechar.addActionListener(ouvinteCancelar);
			}
			
		}
	}

}
