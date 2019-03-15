package Game;

import Data.*;

import javax.swing.*;

import java.awt.event.*;

//Card�� ��ư���� �����ֱ����� Ŭ���� ����
public class CardViewer extends JButton implements MouseListener {
	
	private Card card = null; //�ش� ��ư�� ������ �ִ� ī��
	
	//������
	public CardViewer(Card c) {
		card = c;
		setSize(Card.WIDTH, Card.HEIGHT);
		setIcon(card.getIcon());
		addMouseListener(this);
	}
	//������ �����ε�
	public CardViewer() {
		setSize(Card.WIDTH, Card.HEIGHT);
		setIcon(Card.getBackIcon());
	}
	
	//�ش� ��ư�� ������ �ִ� ī�� ��ȯ
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