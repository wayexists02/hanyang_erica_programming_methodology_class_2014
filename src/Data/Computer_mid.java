package Data;

//난이도 '중' 컴퓨터 클래스
public class Computer_mid extends Computer {	
	public Computer_mid(int i) {
		super(i);
	}

	@Override
	public Card start(String next_suit, int next_count, int stack) {
		// TODO Auto-generated method stub
		if (stack > 0) {
			if (next_suit.equals(Card.JOKER_COLOR) || next_suit.equals(Card.JOKER_BLACK)) {
				for (int i = 0; i < count; i++) {
					if (my_deck[i].getCount() == 0) {
						return my_deck[i];

					}
				}
			}
			else {
				for (int i = 0; i < count; i++) {
					if (my_deck[i].getSuit().equals(next_suit) && (my_deck[i].getCount() == Card.ACE || my_deck[i].getCount() == 2)) {
						return my_deck[i];

					}
				}
				for (int i = 0; i < count; i++) {
					if (my_deck[i].getCount() == next_count) {
						return my_deck[i];

					}
				}
				for (int i = 0; i < count; i++) {
					if (my_deck[i].getSuit().equals(Card.JOKER_COLOR) || my_deck[i].getSuit().equals(Card.JOKER_BLACK)) {
						return my_deck[i];

					}
				}
			}
			return null;
		}
		else {
			if (next_count == 0) {
				int random = (int)(Math.random()*count);
				return my_deck[random];
			}
			
			for (int i = 0; i < count; i++) {
				if (my_deck[i].getSuit().equals(next_suit) && my_deck[i].getCount() != 7) {
					return my_deck[i];

				}
			}
			for (int i = 0; i < count; i++) {
				if (my_deck[i].getSuit().equals(next_suit)) {
					return my_deck[i];

				}
			}
			for (int i = 0; i < count; i++) {
				if (my_deck[i].getCount() == next_count) {
					return my_deck[i];

				}
			}
			for (int i = 0; i < count; i++) {
				if (my_deck[i].getSuit().equals(Card.JOKER_COLOR) || my_deck[i].getSuit().equals(Card.JOKER_BLACK)) {
					return my_deck[i];

				}
			}
			return null;
		}
	}
}
