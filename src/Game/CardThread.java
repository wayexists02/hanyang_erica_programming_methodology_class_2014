package Game;

import Data.*;

import java.awt.*;

public class CardThread{
	private int x_floor = 312; // �ٴڿ� �� ��ư�� ��ġ
	private int y_floor = 355; // �ٴڿ� �� ��ư�� ��ġ
	
	//������
	public CardThread(int turn, Card c) {
		GameViewer viewer = GameViewer.getInstance();
		Graphics g = viewer.getGraphics();
		
		//�ش� �÷��̾ ī�带 �������� �ִϸ��̼�
		switch (turn) {
		case 0 :
			for (int i = 570-Card.HEIGHT; i > y_floor; i -= 10) {
				g.drawImage(c.getIcon().getImage(), x_floor, i, Card.WIDTH, Card.HEIGHT, viewer);
				viewer.repaint();
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.setColor(Color.green);
				g.fillRect(x_floor, i, Card.WIDTH, Card.HEIGHT);
			}
			break;
		case 1 :
			for (int i = 606-Card.WIDTH; i > x_floor; i -= 10) {
				g.drawImage(c.getIcon().getImage(), i, y_floor, Card.WIDTH, Card.HEIGHT, viewer);
				viewer.repaint();
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.setColor(Color.green);
				g.fillRect(i, y_floor, Card.WIDTH, Card.HEIGHT);
			}
			break;
		case 2 :
			for (int i = 100; i < y_floor; i += 10) {
				g.drawImage(c.getIcon().getImage(), x_floor, i, Card.WIDTH, Card.HEIGHT, viewer);
				viewer.repaint();
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.setColor(Color.green);
				g.fillRect(x_floor, i, Card.WIDTH, Card.HEIGHT);
			}
			break;
		case 3 :
			for (int i = 100; i < x_floor; i += 10) {
				g.drawImage(c.getIcon().getImage(), i, y_floor, Card.WIDTH, Card.HEIGHT, viewer);
				viewer.repaint();
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.setColor(Color.green);
				g.fillRect(i, y_floor, Card.WIDTH, Card.HEIGHT);
			}
			break;
		}
		viewer.repaint();
	}
}
