package Game;

import javax.swing.*;

//�޼��� ����� ���� Ŭ����
public class Printer {
	public Printer() {  }
	
	//�Ϲ� �޼��� ���
	public void showMessage(String str) {
		JOptionPane.showMessageDialog(null, str);
	}
	//���� �޼��� ���
	public void showErrorMessage(String str) {
		JOptionPane.showMessageDialog(null, str, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
