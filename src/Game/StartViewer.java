package Game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

//���� ���� ���, ���� �޼ҵ�
public class StartViewer extends JPanel implements ActionListener {

	private JFrame frm = null; //���� ������
	
	private JButton btn_start = null; //��ŸƮ ��ư
	private JButton btn_exit = null; //�����ư
	
	//������
	public StartViewer() {
		frm = new JFrame("��ī��");
		frm.setBounds(120, 120, 649, 387);
		frm.setResizable(false);
		
		setLayout(null);
		
		btn_start = new JButton("����"); //���� ��ư
		btn_start.setBounds(290, 260, 75, 30);
		btn_start.addActionListener(this);
		btn_exit = new JButton("����"); // ���� ��ư
		btn_exit.setBounds(290, 300, 75, 30);
		btn_exit.addActionListener(this);

		add(btn_start); add(btn_exit);
		
		frm.add(this);
		frm.setVisible(true);
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	//��� �׸���
	public void paintComponent(Graphics g) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("Image\\OneCard.PNG");
		g.drawImage(img, 0, 0, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btn_start) {
			frm.dispose();
			Runnable controller = Controller.getInstance();
			Thread thread = new Thread(controller);
			thread.start(); // ��Ʈ�ѷ� Thread ��ŸƮ.
		}
		else if (e.getSource() == btn_exit) {
			System.exit(0);
		}
	}
	
	//���� �޼ҵ�
	public static void main(String[] args) {
		new StartViewer();
	}
}
