package Game;

import java.awt.event.*;

//����� �� ��ư�� �׼� ������
public class DeckListener implements ActionListener {
	private Controller controller = null;
	
	//������
	public DeckListener() {
		controller = Controller.getInstance();
	}

	public void actionPerformed(ActionEvent e) {
		controller.putCard(null);
	}
}
