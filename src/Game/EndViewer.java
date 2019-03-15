package Game;

import javax.swing.*;

import Data.Player;

import java.awt.*;
import java.awt.event.*;

//게임이 끝날때 보여지는 뷰어
public class EndViewer extends JPanel {

	//생성자
	public EndViewer(Player[] player) {
		JFrame frm = new JFrame("결과");
		frm.setBounds(120, 120, 649, 400);
		
		setLayout(null);
		
		//플레이어들의 카드 정보를 보여주는 라벨을 위한 패널 생성
		JPanel[] panels = new JPanel[player.length];
		for (int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
			panels[i].setBounds(270, 70 + 55*i, 100, 35);
			add(panels[i]);
		}
		
		//플레이어들의 카드정보를 보여주는 라벨
		JLabel[] score = new JLabel[player.length];
		for (int i = 0; i < score.length; i++) {
			score[i] = new JLabel(player[i].getName()+" : "+player[i].getCount()+"장");
			panels[i].add(score[i]);
		}
		
		JButton btn = new JButton("종료");
		btn.addActionListener(new ActionListener() {
			//익명 클래스 액션 리스너
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btn.setBounds(280, 300, 60, 30);
		add(btn);
		
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
}
