package aplicacao.bibliotecario.eventos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import logica.Livro;
import aplicacao.FacadeBibliotecario;
import aplicacao.eventos_sistema.BotaoCancelarListener;

public class BuscarLivrosListener implements ActionListener {
	FacadeBibliotecario fachadaBiblio = new FacadeBibliotecario();
	String tipoBusca;
	JTextField itemBusca;
	JFrame janela;

	public BuscarLivrosListener(String tipoBusca, JTextField itemBusca, JFrame janela) {
		this.janela = janela;
		this.tipoBusca = tipoBusca;
		this.itemBusca = itemBusca;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Janela
		JFrame listaLivrosFrame = new JFrame();
		listaLivrosFrame .setTitle("Sistema DigiTeca - Resultado da Busca");
		listaLivrosFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		listaLivrosFrame.setLocation(400, 0);

		// Container
		Container ct = listaLivrosFrame.getContentPane();
		ct.setLayout(new BorderLayout());
		ct.setBackground(Color.white);

		// Paineis
		JPanel painelTabela = new JPanel();
		JPanel painelBotoes = new JPanel();
		painelTabela.setBackground(Color.white);
		painelBotoes.setLayout(new FlowLayout());
		painelBotoes.setBackground(Color.white);
		
		// Criando os dados da tabela
		ArrayList<Livro> livrosArray = fachadaBiblio.buscarLivro(tipoBusca, itemBusca.getText());
		
		Object[] colunas = new Object[]{"ISBN" , "Título" ,"Tipo","Categoria","Autor","Nº Edição","Nº Páginas","ID Estante","Status"};
		
		Object[][] valores = new Object[livrosArray.size()][9];
		for (int i = 0; i <livrosArray.size(); i++) {
			valores[i][0] = livrosArray.get(i).getIsbn();
			if(livrosArray.get(i).getTitulo() == null) {
				valores[i][1] = "Sem titulo";
			}
			else {
				valores[i][1] = livrosArray.get(i).getTitulo();
			}
			if(livrosArray.get(i).getTipo() == null) {
				valores[i][2] = "Sem titulo";
			}
			else {
				valores[i][2] = livrosArray.get(i).getTipo();
			}
			
			valores[i][3] = livrosArray.get(i).getCategoria().getIdCategoria();
			
			if(livrosArray.get(i).getAutor() == null) {
				valores[i][4] = "Sem autor";
			}
			else {
				valores[i][4] = livrosArray.get(i).getAutor();
			}
			
			if(livrosArray.get(i).getNumeroEdicao() == 0) {
				valores[i][5] = 0;
			}
			else {
				valores[i][5] = livrosArray.get(i).getNumeroEdicao();
			}
		
			if(livrosArray.get(i).getNumeroPaginas() == 0) {
				valores[i][6] = 0;
			}
			else {
				valores[i][6] = livrosArray.get(i).getNumeroPaginas();
			}
		
			if(livrosArray.get(i).getEstante() == null) {
				valores[i][7] = "Sem estante";
			}
			else {
				valores[i][7] = livrosArray.get(i).getEstante().getIdEstante();
			}
			valores[i][8] = livrosArray.get(i).getStatus();
 		}
		
		//Tabela
		JTable tabelaLivros = new JTable(valores,colunas);

		// Mostrar bordas da tabela
		tabelaLivros.setBorder(new LineBorder(Color.black));
		tabelaLivros.setGridColor(Color.black);
		tabelaLivros.setShowGrid(true);
		
		// Adicionar rolagem (scroll) para tabela
		JScrollPane rolagem = new JScrollPane();			
		rolagem.setViewportView(tabelaLivros);
		
		//Botoes
		Icon iconeFechar = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
		JButton botaoFechar = new JButton("Fechar", iconeFechar);
		painelBotoes.add(botaoFechar);
		
		//Rotulo
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotResultadoLivros.png");
		JLabel rotImagem = new JLabel(iconeRotulo);
		
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

		ct.add(rotImagem, BorderLayout.NORTH);
		ct.add(rolagem, BorderLayout.CENTER);
		ct.add(panelWest, BorderLayout.WEST);
		ct.add(panelEast, BorderLayout.EAST);
		ct.add(painelBotoes, BorderLayout.SOUTH);
		
		listaLivrosFrame.pack();
		listaLivrosFrame.setVisible(true);
		listaLivrosFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//Tratando o evento do botao fechar
		BotaoCancelarListener ouvinteFechar = new BotaoCancelarListener(listaLivrosFrame);
		botaoFechar.addActionListener(ouvinteFechar);
		
		//Fechar janela anterior	
		janela.dispose();
	}
}
