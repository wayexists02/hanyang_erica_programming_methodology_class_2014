package Game;

import Data.*;

import java.awt.*;

public class CardThread{
	private int x_floor = 312; // 바닥에 깔린 버튼의 위치
	private int y_floor = 355; // 바닥에 깔린 버튼의 위치
	
	//생성자
	public CardThread(int turn, Card c) {
		GameViewer viewer = GameViewer.getInstance();
		Graphics g = viewer.getGraphics();
		
		//해당 플레이어가 카드를 냈을때의 애니메이션
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
