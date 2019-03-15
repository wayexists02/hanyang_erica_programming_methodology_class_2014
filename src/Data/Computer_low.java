package Data;

// 난이도 '하' 컴퓨터 클래스
public class Computer_low extends Computer {
	
	public Computer_low(int i) {
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
					if (my_deck[i].getSuit().equals(Card.JOKER_COLOR) || my_deck[i].getSuit().equals(Card.JOKER_BLACK)) {
						return my_deck[i];

					}
					else if ((my_deck[i].getSuit().equals(next_suit) && (my_deck[i].getCount() == Card.ACE || my_deck[i].getCount() == 2))
							 || my_deck[i].getCount() == next_count) {
						return my_deck[i];

					}
				}
			}
			return null;
		}
		else {
			if (next_count == 0) {
				return my_deck[0];

			}
			for (int i = 0; i < count; i++) {
				if (my_deck[i].getSuit().equals(next_suit) || my_deck[i].getCount() == next_count) {
					return my_deck[i];

				}
			}
			return null;
		}
	}
}
