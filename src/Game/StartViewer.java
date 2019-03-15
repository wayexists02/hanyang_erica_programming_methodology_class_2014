package Game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

//게임 시작 뷰어, 메인 메소드
public class StartViewer extends JPanel implements ActionListener {

	private JFrame frm = null; //메인 프레임
	
	private JButton btn_start = null; //스타트 버튼
	private JButton btn_exit = null; //종료버튼
	
	//생성자
	public StartViewer() {
		frm = new JFrame("원카드");
		frm.setBounds(120, 120, 649, 387);
		frm.setResizable(false);
		
		setLayout(null);
		
		btn_start = new JButton("시작"); //시작 버튼
		btn_start.setBounds(290, 260, 75, 30);
		btn_start.addActionListener(this);
		btn_exit = new JButton("종료"); // 종료 버튼
		btn_exit.setBounds(290, 300, 75, 30);
		btn_exit.addActionListener(this);

		add(btn_start); add(btn_exit);
		
		frm.add(this);
		frm.setVisible(true);
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	//배경 그리기
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
			thread.start(); // 컨트롤러 Thread 스타트.
		}
		else if (e.getSource() == btn_exit) {
			System.exit(0);
		}
	}
	
	//메인 메소드
	public static void main(String[] args) {
		new StartViewer();
	}
}
