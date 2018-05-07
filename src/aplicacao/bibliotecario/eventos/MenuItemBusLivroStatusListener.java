package aplicacao.bibliotecario.eventos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import aplicacao.eventos_sistema.BotaoCancelarListener;

public class MenuItemBusLivroStatusListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		//Janela
		JFrame buscarFrame = new JFrame();
		buscarFrame.setTitle("Sistema DigiTeca - Pesquisar Livro por Status");
		buscarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buscarFrame.setLocation(200,200);
		
		//Container
		Container ct = buscarFrame.getContentPane();
		ct.setLayout(new BorderLayout());
		ct.setBackground(Color.white);
		
		//Paineis
		JPanel painelTexto = new JPanel();
		JPanel painelBotoes = new JPanel();
		painelTexto.setLayout(new FlowLayout());
		painelTexto.setBackground(Color.white);
		painelBotoes.setLayout(new FlowLayout());
		painelBotoes.setBackground(Color.white);
		
		//Rotulos e campos
		JLabel status = new JLabel("Digite status do livro que deseja pesquisar (Pendente/Disponivel/Ocupado/Permanente): ");
		JTextField campoStatus = new JTextField(15);
		painelTexto.add(status);
		painelTexto.add(campoStatus);
		
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotPesqLivroStatus.png");
		JLabel rotImagem = new JLabel(iconeRotulo);
		
		//Botoes
		Icon icone1 = new ImageIcon("src/aplicacao/icones/iconeEntrar.png");
		Icon icone2 = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
		JButton botaoBuscar = new JButton("Buscar", icone1);
		JButton botaoCancelar = new JButton("Cancelar", icone2);
		painelBotoes.add(botaoCancelar);
		painelBotoes.add(botaoBuscar);
		painelBotoes.setBackground(Color.white);
		
		//Adicionando os componentes ao container
		ct.add(rotImagem, BorderLayout.NORTH);
		ct.add(painelTexto, BorderLayout.CENTER);
		ct.add(painelBotoes, BorderLayout.SOUTH);

		//Mostrando a janela
		buscarFrame.pack();
		buscarFrame.setResizable(false);
		buscarFrame.setVisible(true);

		//Tratando o evento do bot�o buscar
		BuscarLivrosListener ouvinteBuscarLivro = new BuscarLivrosListener("Status", campoStatus,buscarFrame);
		botaoBuscar.addActionListener(ouvinteBuscarLivro);
		
		//Tratando o evento do bot�o cancelar
		BotaoCancelarListener ouvinteCancelar = new BotaoCancelarListener(buscarFrame);
		botaoCancelar.addActionListener(ouvinteCancelar);
	}
}
