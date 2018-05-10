
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

import aplicacao.FacadeBibliotecario;
import aplicacao.eventos_sistema.BotaoCancelarListener;

public class MenuItemDevolucaoListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		//Janela
		JFrame devolucaoFrame = new JFrame();
		devolucaoFrame.setTitle("Sistema DigiTeca - Registrar Devolução");
		devolucaoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		devolucaoFrame.setLocation(400,200);
		
		//Container
		Container ct = devolucaoFrame.getContentPane();
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
		
		//Paineis
		JPanel painelTexto = new JPanel();
		JPanel painelBotoes = new JPanel();
		painelTexto.setLayout(new GridBagLayout());
		GridBagConstraints cst = new GridBagConstraints();
		painelTexto.setBackground(Color.white);
		painelTexto.setBorder(BorderFactory.createTitledBorder("Informações para devolução"));
		painelBotoes.setLayout(new FlowLayout());
		painelBotoes.setBackground(Color.white);
		
		//Rotulos e campos
		// Mascaras
		MaskFormatter mascaraCpf = null;
		MaskFormatter mascaraData = null;

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraData = new MaskFormatter("##/##/####");
			mascaraCpf.setPlaceholderCharacter('_');
			mascaraData.setPlaceholderCharacter('_');
		} catch (ParseException excp) {
			JOptionPane.showMessageDialog(null, excp.getMessage());
			System.exit(-1);
		}

		// Seta as máscaras nos objetos JFormattedTextField
		JFormattedTextField campoCpf = new JFormattedTextField(mascaraCpf);
		JFormattedTextField campoDataDevolucao = new JFormattedTextField(mascaraData);
		
		JLabel cpf = new JLabel("CPF do Leitor: ");
		cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 0;
	    painelTexto.add(cpf,cst);
	    cst.gridx = 1;
	    cst.gridy = 0;
		painelTexto.add(campoCpf,cst);
		
		JLabel dataDevolucao = new JLabel("Data de Devolução: ");
		cst.fill = GridBagConstraints.HORIZONTAL;
	    cst.gridx = 0;
	    cst.gridy = 1;
	    painelTexto.add(dataDevolucao,cst);
	    cst.gridx = 1;
	    cst.gridy = 1;
		painelTexto.add(campoDataDevolucao,cst);
		
		//Icone do rotulo inicial (Registrar devolucao)
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotDevolucao.png");
		JLabel rotImagem = new JLabel(iconeRotulo);
		
		//Botoes
		Icon icone1 = new ImageIcon("src/aplicacao/icones/iconeEntrar.png");
		Icon icone2 = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
		JButton botaoConfirmar= new JButton("Confirmar", icone1);
		JButton botaoCancelar = new JButton("Cancelar", icone2);
		painelBotoes.add(botaoCancelar);
		painelBotoes.add(botaoConfirmar);
		
		//Adicionando os componentes ao container
		ct.add(rotImagem, BorderLayout.NORTH);
		ct.add(panelWest, BorderLayout.WEST);
		ct.add(panelEast, BorderLayout.EAST);
		ct.add(painelTexto, BorderLayout.CENTER);
		ct.add(painelBotoes, BorderLayout.SOUTH);

		//Mostrando a janela
		devolucaoFrame.pack();
		devolucaoFrame.setResizable(false);
		devolucaoFrame.setVisible(true);
		
		//Tratamento de eventos
		BotaoRegistrarDevolucaoListener ouvinteRegistrarDevolucao = new BotaoRegistrarDevolucaoListener(campoCpf, campoDataDevolucao, devolucaoFrame);
		botaoConfirmar.addActionListener(ouvinteRegistrarDevolucao);
		
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(devolucaoFrame);
		botaoCancelar.addActionListener(ouvinteCancelar);
				
				
	}
	
	private class BotaoRegistrarDevolucaoListener implements ActionListener {
		FacadeBibliotecario fachadaBiblio = new FacadeBibliotecario();
		JTextField cpf;
		JTextField dataDevolucao;
		JFrame janela;
		
		public BotaoRegistrarDevolucaoListener(JTextField cpf, JTextField dataDevolucao, JFrame janela) {
			this.cpf = cpf;
			this.dataDevolucao = dataDevolucao;
			this.janela = janela;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			fachadaBiblio.registrarDevolucao(cpf.getText(), dataDevolucao.getText());
			janela.dispose();
		}
	
	
	}
}
