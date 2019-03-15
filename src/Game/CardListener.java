package Game;

import java.awt.event.*;
import Data.*;

//CardViewer 버튼의 액션 리스너
public class CardListener implements ActionListener {
	private CardViewer btn = null;
	private Controller controller = null;
	
	//생성자
	public CardListener(CardViewer btn) {
		this.btn = btn;
		controller = Controller.getInstance();
	}
	
	//낼수없으면 리턴한다.
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
