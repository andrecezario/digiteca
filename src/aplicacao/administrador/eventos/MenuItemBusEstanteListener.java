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

import logica.Estante;
import aplicacao.FacadeAdministrador;
import aplicacao.eventos_sistema.BotaoCancelarListener;

public class MenuItemBusEstanteListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		//Janela
		JFrame removerFrame = new JFrame();
		removerFrame.setTitle("Sistema DigiTeca - Buscar Estante");
		removerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		removerFrame.setLocation(400, 200);
		
		//Contoiner
		Container ct = removerFrame.getContentPane();
		ct.setLayout(new BorderLayout());
		ct.setBackground(Color.white);

		//Painel
		JPanel painel = new JPanel();
		painel.setLayout(new FlowLayout());
		painel.setBackground(Color.white);

		//Rotulo e campo de texto
		MaskFormatter mascaraId = null;

		try {
			mascaraId = new MaskFormatter("U-###");
			mascaraId.setPlaceholderCharacter('_');
		} catch (ParseException excp) {
			JOptionPane.showMessageDialog(null, excp.getMessage());
			System.exit(-1);
		}

		// Seta as máscaras nos objetos JFormattedTextField
		JFormattedTextField campoVerificacao = new JFormattedTextField(mascaraId);
		
		JLabel verificacao = new JLabel("Digite o ID da estante para a busca:");
		painel.add(verificacao);
		painel.add(campoVerificacao);

		//Icone no rotulo incial (Remover estante)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotBusEstante.png");
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
		
		//Adicionando componentes ao container
		ct.add(rotulo, BorderLayout.NORTH);
		ct.add(painel, BorderLayout.CENTER);
		ct.add(painelBotoes, BorderLayout.SOUTH);

		//Tratando o evento do botao buscar
		BotaoBuscarEstanteListener ouvinteRemover = new BotaoBuscarEstanteListener(campoVerificacao,removerFrame);
		botaoBuscar.addActionListener(ouvinteRemover);
		
		//Tratando o evento do botao cancelar
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(removerFrame);
		botaoCancelar.addActionListener(ouvinteCancelar);
		
		//Mostrando a janela
		removerFrame.pack();
		removerFrame.setResizable(false);
		removerFrame.setVisible(true);
	}
	
	//Usando uma classe interna para tratar o evento do "botao buscar" para remover uma estante no sistema
	private class BotaoBuscarEstanteListener implements ActionListener{
		private FacadeAdministrador fachadaAdm = new FacadeAdministrador();
		private JTextField campoTexto;
		private JFrame janela;
		
		public BotaoBuscarEstanteListener(JTextField campoTexto, JFrame janela) {
			this.campoTexto = campoTexto;
			this.janela = janela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Estante estante = fachadaAdm.buscarEstante(campoTexto.getText());
			if(estante.getIdEstante() == null) { //Quer dizer que nao existe uma estante com esse numero de ID no sistema
				JOptionPane.showMessageDialog(null,"Estante não encontrada!", "ERRO", JOptionPane.ERROR_MESSAGE);
			}
			else {
				//Fechando a janela anterior para mostrar o resultado
				janela.dispose();
				
				//Abrindo a nova janela com o resultado da busca
				JFrame resultadoFrame = new JFrame();
				resultadoFrame.setTitle("Sistema DigiTeca - Dados da Estante");
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
				painel.setBorder(BorderFactory.createTitledBorder("Dados da Estante"));
	
				JLabel id = new JLabel("ID da estante:");
				JLabel valorId = new JLabel(estante.getIdEstante());
				cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 0;
			    painel.add(id,cst);
			    cst.gridx = 1;
			    cst.gridy = 0;
				painel.add(valorId,cst);
				
				JLabel descricao = new JLabel("Descrição:");
				cst.fill = GridBagConstraints.HORIZONTAL;
			    cst.gridx = 0;
			    cst.gridy = 1;
			    painel.add(descricao,cst);		
				
				try {
					JLabel valorDescricao = new JLabel(estante.getCategoria().getDescricao());
					cst.fill = GridBagConstraints.HORIZONTAL;
					cst.gridx = 1;
				    cst.gridy = 1;
					painel.add(valorDescricao,cst);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
				Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotConfEstante.png");
				JLabel rotulo = new JLabel(iconeRotulo);
				rotulo.setBackground(Color.white);
				
				JPanel painelBotoes = new JPanel();
				Icon iconeFechar = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
				JButton botaoFechar = new JButton("Fechar", iconeFechar);
				painelBotoes.setBackground(Color.white);
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
				BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(resultadoFrame);
				botaoFechar.addActionListener(ouvinteCancelar);
			}
			
		}
	}
}
