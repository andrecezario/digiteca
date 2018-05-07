package aplicacao.eventos_sistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuItemSairListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}	
}
