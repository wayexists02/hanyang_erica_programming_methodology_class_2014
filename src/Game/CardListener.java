package Game;

import java.awt.event.*;
import Data.*;

//CardViewer ��ư�� �׼� ������
public class CardListener implements ActionListener {
	private CardViewer btn = null;
	private Controller controller = null;
	
	//������
	public CardListener(CardViewer btn) {
		this.btn = btn;
		controller = Controller.getInstance();
	}
	
	//���������� �����Ѵ�.
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Card card = btn.getCard();
		String suit = controller.getNextSuit();
		int count = controller.getNextCount();
		int stack = controller.getStack();
		
		if (stack > 0 ) {
			if (count == 0)
				if (card.getCount() != 0)
					return;
			if (!(card.getSuit().equals(suit) && (card.getCount() == Card.ACE || card.getCount() == 2)) && card.getCount() != count && !card.getSuit().equals(Card.JOKER_COLOR) && !card.getSuit().equals(Card.JOKER_BLACK))
				return;
			
		}
		else {
			if (suit.equals(Card.JOKER_COLOR) || suit.equals(Card.JOKER_BLACK)) {
				
			}
			else {
				if (!card.getSuit().equals(suit) && card.getCount() != count && !card.getSuit().equals(Card.JOKER_COLOR) && !card.getSuit().equals(Card.JOKER_BLACK))
					return;
			}
		}
		
		controller.putCard(card);
	}
}
