
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
import javax.swing.JPanel;
import javax.swing.JTextField;

import aplicacao.FacadeBibliotecario;
import aplicacao.eventos_sistema.BotaoCancelarListener;

public class MenuItemDevolucaoListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		//Janela
		JFrame devolucaoFrame = new JFrame();
		devolucaoFrame.setTitle("Sistema DigiTeca - Registrar Devolucao");
		devolucaoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		devolucaoFrame.setLocation(200,200);
		
		//Container
		Container ct = devolucaoFrame.getContentPane();
		ct.setLayout(new BorderLayout());
		ct.setBackground(Color.white);
		
		//Paineis
		JPanel painelTexto = new JPanel();
		JPanel painelBotoes = new JPanel();
		painelTexto.setLayout(new GridLayout(2,2));
		painelTexto.setBackground(Color.white);
		painelBotoes.setLayout(new FlowLayout());
		painelBotoes.setBackground(Color.white);
		
		//Rotulos e campos
		
		JLabel cpf = new JLabel("CPF do Leitor: ");
		JTextField campoCpf = new JTextField(10);
		painelTexto.add(cpf);
		painelTexto.add(campoCpf);
		
		JLabel dataDevolucao = new JLabel("Data de Devolucao: ");
		JTextField campoDataDevolucao = new JTextField(10);
		painelTexto.add(dataDevolucao);
		painelTexto.add(campoDataDevolucao);
		
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
