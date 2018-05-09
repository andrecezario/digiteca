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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import logica.Emprestimo;
import logica.Livro;
import aplicacao.FacadeBibliotecario;
import aplicacao.eventos_sistema.BotaoCancelarListener;

public class MenuItemListEmprestimosListener implements ActionListener {
	FacadeBibliotecario fachadaBiblio = new FacadeBibliotecario();

	@Override
	public void actionPerformed(ActionEvent e) {
		// Janela
		JFrame listaEmprestimosFrame = new JFrame();
		listaEmprestimosFrame.setTitle("Sistema DigiTeca - Resultado da Busca");
		listaEmprestimosFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		listaEmprestimosFrame.setLocation(350, 0);

		// Container
		Container ct = listaEmprestimosFrame.getContentPane();
		ct.setLayout(new BorderLayout());
		ct.setBackground(Color.white);

		// Paineis
		JPanel painelTabela = new JPanel();
		JPanel painelBotoes = new JPanel();
		painelTabela.setBackground(Color.white);
		painelBotoes.setLayout(new FlowLayout());
		painelBotoes.setBackground(Color.white);
		
		//Encontrando os valores da tabela
		ArrayList<Emprestimo> emprestimosArray = fachadaBiblio.listarEmprestimos();
		
		Object[] colunas = new Object[]{"CPF Leitor" ,"ISBN Livro","CPF Bibliotecário","Data Empréstimo","Data Devolução",};
		
		Object[][] valores = new Object[emprestimosArray.size()][5];
		for (int i = 0; i <emprestimosArray.size(); i++) {
			if(emprestimosArray.get(i).getLeitor().getCpf() == null) {
				valores[i][0] = "Sem leitor";
			}
			else {
				valores[i][0] = emprestimosArray.get(i).getLeitor().getCpf();
			}
			if(emprestimosArray.get(i).getLivro().getIsbn() == null) {
				valores[i][1] = "Sem livro";
			}
			else {
				valores[i][1] = emprestimosArray.get(i).getLivro().getIsbn();
			}
		
			if(emprestimosArray.get(i).getBibliotecario().getCpf() == null) {
				valores[i][2] = "Sem bibliotecario";
			}
			else {
				valores[i][2] = emprestimosArray.get(i).getBibliotecario().getCpf();
			}
		
			if(emprestimosArray.get(i).getDataAtual() == null) {
				valores[i][3] = null;
			}
			else {
				valores[i][3] = emprestimosArray.get(i).getDataAtual();
			}
	
			if(emprestimosArray.get(i).getDataDevolucao() == null) {
				valores[i][4] = null;
			}
			else {
				valores[i][4] = emprestimosArray.get(i).getDataDevolucao();
			}
		}
		
		// Tabela
		JTable tabelaLivros = new JTable(valores, colunas);

		// Mostrar bordas da tabela
		tabelaLivros.setBorder(new LineBorder(Color.black));
		tabelaLivros.setGridColor(Color.black);
		tabelaLivros.setShowGrid(true);

		// Adicionar rolagem (scroll) para tabela
		JScrollPane rolagem = new JScrollPane();
		rolagem.setViewportView(tabelaLivros);
		// Botoes
		Icon iconeFechar = new ImageIcon("src/aplicacao/icones/iconeRemover.png");
		JButton botaoFechar = new JButton("Fechar", iconeFechar);
		painelBotoes.add(botaoFechar);

		//Rotulo
		Icon iconeRotulo = new ImageIcon("src/aplicacao/icones/rotResultadoEmprestimos.png");
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
		
		listaEmprestimosFrame.pack();
		listaEmprestimosFrame.setVisible(true);
		listaEmprestimosFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//Tratando o evento do botao fechar
		BotaoCancelarListener ouvinteFechar = new BotaoCancelarListener(listaEmprestimosFrame);
		botaoFechar.addActionListener(ouvinteFechar);
	}

}
