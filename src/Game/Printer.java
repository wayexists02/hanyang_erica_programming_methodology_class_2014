package Game;

import javax.swing.*;

//메세지 출력을 위한 클래스
public class Printer {
	public Printer() {  }
	
	//일반 메세지 출력
	public void showMessage(String str) {
		JOptionPane.showMessageDialog(null, str);
	}
	//에러 메세지 출력
	public void showErrorMessage(String str) {
		JOptionPane.showMessageDialog(null, str, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
