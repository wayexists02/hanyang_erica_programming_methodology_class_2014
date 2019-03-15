package Game;

import javax.swing.*;

import Data.Player;

import java.awt.*;
import java.awt.event.*;

//������ ������ �������� ���
public class EndViewer extends JPanel {

	//������
	public EndViewer(Player[] player) {
		JFrame frm = new JFrame("���");
		frm.setBounds(120, 120, 649, 400);
		
		setLayout(null);
		
		//�÷��̾���� ī�� ������ �����ִ� ���� ���� �г� ����
		JPanel[] panels = new JPanel[player.length];
		for (int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
			panels[i].setBounds(270, 70 + 55*i, 100, 35);
			add(panels[i]);
		}
		
		//�÷��̾���� ī�������� �����ִ� ��
		JLabel[] score = new JLabel[player.length];
		for (int i = 0; i < score.length; i++) {
			score[i] = new JLabel(player[i].getName()+" : "+player[i].getCount()+"��");
			panels[i].add(score[i]);
		}
		
		JButton btn = new JButton("����");
		btn.addActionListener(new ActionListener() {
			//�͸� Ŭ���� �׼� ������
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
	
	//��� �׸���
	public void paintComponent(Graphics g) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("Image\\OneCard.PNG");
		g.drawImage(img, 0, 0, this);
	}
}
