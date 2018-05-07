package aplicacao.eventos_sistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class BotaoCancelarListener implements ActionListener{
	JFrame janela;
	
	public BotaoCancelarListener(JFrame janela) {
		this.janela = janela;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		janela.dispose();
		
	}	

}
