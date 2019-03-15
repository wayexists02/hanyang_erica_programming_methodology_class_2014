package Game;

import java.awt.event.*;

//뷰어의 덱 버튼의 액션 리스너
public class DeckListener implements ActionListener {
	private Controller controller = null;
	
	//생성자
	public DeckListener() {
		controller = Controller.getInstance();
	}

	public void actionPerformed(ActionEvent e) {
		controller.putCard(null);
	}
}
