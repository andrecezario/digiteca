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

import logica.Livro;
import aplicacao.FacadeAdministrador;
import aplicacao.eventos_sistema.BotaoCancelarListener;

public class MenuItemBusLivroListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		//Janela
		JFrame buscarFrame = new JFrame();
		buscarFrame.setTitle("Sistema DigiTeca - Buscar Livro");
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
		JLabel verificacao = new JLabel("Digite o ISBN do livro que deseja buscar:");
		final JTextField campoVerificacao = new JTextField(10);
		painel.add(verificacao);
		painel.add(campoVerificacao);

		//Icone do rotulo incial (Buscar livro)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotBusLivro.png");
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

		//Tratando o evento do botaa buscar
		BotaoBuscarLivroListener ouvinteBuscarLivro = new BotaoBuscarLivroListener(campoVerificacao,buscarFrame);
		botaoBuscar.addActionListener(ouvinteBuscarLivro);
		
		//Tratando o evento do botao cancelar
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(buscarFrame);
		botaoCancelar.addActionListener(ouvinteCancelar);
		
		//Mostrando a janela
		buscarFrame.pack();
		buscarFrame.setResizable(false);
		buscarFrame.setVisible(true);
	}
	
	//Usando uma classe interna (aninhada) para tratar o evento do "botao buscar" para buscar um livro no sistema
	private class BotaoBuscarLivroListener implements ActionListener {
		private FacadeAdministrador fachada = new FacadeAdministrador();
		private JTextField campoTexto;
		private JFrame janela;
		
		public BotaoBuscarLivroListener(JTextField campoTexto, JFrame janela) {
			this.campoTexto = campoTexto;
			this.janela = janela;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			
			Livro livro = fachada.buscarLivroIsbn(campoTexto.getText());
			
			if(livro == null || livro.getIsbn().equals(null)) { //Quer dizer que nao existe um bibliotecario com esse numero de CPF no sistema
				JOptionPane.showMessageDialog(null,"Livro nao encontrado!", "ERRO", JOptionPane.ERROR_MESSAGE);
			}
			else {
				//Fechando a janela anterior para mostrar o resultado
				janela.dispose();
				
				//Abrindo a nova janela com o resultado da busca
				JFrame resultadoFrame = new JFrame();
				resultadoFrame.setTitle("Sistema DigiTeca - Dados do Livro");
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
				painel.setBorder(BorderFactory.createTitledBorder("Dados do Livro"));
	
				JLabel titulo = new JLabel("Titulo:");
				JLabel valorTitulo = new JLabel(livro.getTitulo());
				cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 0;
			    painel.add(titulo,cst);
			    cst.gridx = 1;
			    cst.gridy = 0;
				painel.add(valorTitulo,cst);
				
				JLabel isbn = new JLabel("ISBN:");
				JLabel valorIsbn = new JLabel(livro.getIsbn());
			    cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 1;
			    painel.add(isbn,cst);
			    cst.gridx = 1;
			    cst.gridy = 1;
				painel.add(valorIsbn,cst);
				
				JLabel tipo = new JLabel("Tipo:");
				JLabel valorTipo = new JLabel(livro.getTipo());
				cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 2;
			    painel.add(tipo,cst);
			    cst.gridx = 1;
			    cst.gridy = 2;
				painel.add(valorTipo,cst);
				
				JLabel categoria = new JLabel("Categoria:");
				cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 3;
			    painel.add(categoria,cst);
				
				try {
					JLabel valorCategoria = new JLabel(livro.getCategoria().getDescricao());
					cst.fill = GridBagConstraints.HORIZONTAL;
					cst.gridx = 1;
					cst.gridy = 3;
					painel.add(valorCategoria,cst);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
				JLabel autor = new JLabel("Autor:");
				JLabel valorAutor = new JLabel(livro.getAutor());
				cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 4;
			    painel.add(autor,cst);
			    cst.gridx = 1;
			    cst.gridy = 4;
				painel.add(valorAutor,cst);
				
				JLabel numPaginas = new JLabel("Numero de Paginas: ");
				JLabel valorNumPaginas = new JLabel(Integer.toString(livro.getNumeroPaginas())); //Converte para o n� de paginas int para String
				cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 5;
			    painel.add(numPaginas,cst);
			    cst.gridx = 1;
			    cst.gridy = 5;
				painel.add(valorNumPaginas,cst);
				
				JLabel numEdicao = new JLabel("Numero de Edicao:");
				JLabel valorNumEdicao = new JLabel(Integer.toString(livro.getNumeroEdicao()));
				cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 6;
			    painel.add(numEdicao,cst);
			    cst.gridx = 1;
			    cst.gridy = 6;
				painel.add(valorNumEdicao,cst);
				
				Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotConfLivro.png");
				JLabel rotulo = new JLabel(iconeRotulo);
				rotulo.setBackground(Color.white);
				
				JPanel painelBotoes = new JPanel();
				painelBotoes.setBackground(Color.white);
				Icon iconeFechar = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
				JButton botaoFechar = new JButton("Fechar", iconeFechar);
				painelBotoes.add(botaoFechar);
				
				ct.add(rotulo,BorderLayout.NORTH);
				ct.add(painel,BorderLayout.CENTER);
				ct.add(panelWest, BorderLayout.WEST);
				ct.add(panelEast, BorderLayout.EAST);
				ct.add(painelBotoes,BorderLayout.SOUTH);
				
				resultadoFrame.pack();
				resultadoFrame.setResizable(false);
				resultadoFrame.setVisible(true);
				
				//Tratando o evento do botao fechar
				BotaoCancelarListener ouvinteFechar = new BotaoCancelarListener(resultadoFrame);
				botaoFechar.addActionListener(ouvinteFechar);
				
				//Fechando a janela anterior para mostrar o resultado
				janela.dispose();
			}
		}
	}
}
