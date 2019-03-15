package Game;

import java.awt.Color;
import java.awt.Graphics;

import Data.Card;

public class DrawThread {
	private int x_floor = 312; // 덱버튼의 위치
	private int y_floor = 255; // 덱버튼의 위치
	
	//생성자
	public DrawThread(int turn) {
		GameViewer viewer = GameViewer.getInstance();
		Graphics g = viewer.getGraphics();
		
		//드로우할때 애니메이션 생성
		switch (turn) {
		case 0 :
			for (int i = y_floor+100+Card.HEIGHT; i < 720 - Card.HEIGHT; i += 10) {
				g.drawImage(Card.getBackIcon().getImage(), x_floor, i, Card.WIDTH, Card.HEIGHT, viewer);
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
			for (int i = x_floor; i < 606-Card.WIDTH; i += 10) {
				g.drawImage(Card.getBackIcon().getImage(), i, y_floor, Card.WIDTH, Card.HEIGHT, viewer);
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
			for (int i = y_floor; i > 100; i -= 10) {
				g.drawImage(Card.getBackIcon().getImage(), x_floor, i, Card.WIDTH, Card.HEIGHT, viewer);
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
			for (int i = x_floor; i > 100; i -= 10) {
				g.drawImage(Card.getBackIcon().getImage(), i, y_floor, Card.WIDTH, Card.HEIGHT, viewer);
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
