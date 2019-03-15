package Game;

import Data.*;

import javax.swing.*;

import java.awt.event.*;

//Card를 버튼으로 보여주기위한 클래스 뷰어용
public class CardViewer extends JButton implements MouseListener {
	
	private Card card = null; //해당 버튼이 가지고 있는 카드
	
	//생성자
	public CardViewer(Card c) {
		card = c;
		setSize(Card.WIDTH, Card.HEIGHT);
		setIcon(card.getIcon());
		addMouseListener(this);
	}
	//생성자 오버로딩
	public CardViewer() {
		setSize(Card.WIDTH, Card.HEIGHT);
		setIcon(Card.getBackIcon());
	}
	
	//해당 버튼이 가지고 있는 카드 반환
	public Card getCard() { return card; }
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		setLocation(getX(), getY()-40);
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		setLocation(getX(), getY()+40);
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}