package Data;

//난이도 '상' 컴퓨터 클래스
public class Computer_high extends Computer {
	public Computer_high(int i) {
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
				int cnt_spade = 0, cnt_club = 0, cnt_heart = 0, cnt_diamond = 0;
				for (int i = 0; i < count; i++) {
					if (my_deck[i].getSuit().equals(Card.SPADE))
						cnt_spade++;
					else if (my_deck[i].getSuit().equals(Card.CLUB))
						cnt_club++;
					else if (my_deck[i].getSuit().equals(Card.HEART))
						cnt_heart++;
					else if (my_deck[i].getSuit().equals(Card.DIAMOND))
						cnt_diamond++;
					else {  }
				}
				
				int SUIT = (cnt_spade > cnt_club? (cnt_spade > cnt_heart ? (cnt_spade > cnt_diamond ? cnt_spade : cnt_diamond) : (cnt_heart > cnt_diamond ? cnt_heart : cnt_diamond)) : (cnt_club > cnt_heart ? (cnt_club > cnt_diamond ? cnt_club : cnt_diamond) : (cnt_heart > cnt_diamond ? cnt_heart : cnt_diamond)));
				
				if (SUIT == cnt_spade) {
					for (int i = 0; i < count; i++) {
						if (my_deck[i].getSuit().equals(Card.SPADE) && my_deck[i].getCount() != 7) {
							return my_deck[i];

						}
					}
					for (int i = 0; i < count; i++) {
						if (my_deck[i].getSuit().equals(Card.SPADE)) {
							return my_deck[i];

						}
					}
				}
				else if (SUIT == cnt_club) {
					for (int i = 0; i < count; i++) {
						if (my_deck[i].getSuit().equals(Card.CLUB) && my_deck[i].getCount() != 7) {
							return my_deck[i];

						}
					}
					for (int i = 0; i < count; i++) {
						if (my_deck[i].getSuit().equals(Card.CLUB)) {
							return my_deck[i];

						}
					}
				}
				else if (SUIT == cnt_heart) {
					for (int i = 0; i < count; i++) {
						if (my_deck[i].getSuit().equals(Card.HEART) && my_deck[i].getCount() != 7) {
							return my_deck[i];

						}
					}
					for (int i = 0; i < count; i++) {
						if (my_deck[i].getSuit().equals(Card.HEART)) {
							return my_deck[i];

						}
					}
				}
				else if (SUIT == cnt_diamond) {
					for (int i = 0; i < count; i++) {
						if (my_deck[i].getSuit().equals(Card.DIAMOND) && my_deck[i].getCount() != 7) {
							return my_deck[i];

						}
					}
					for (int i = 0; i < count; i++) {
						if (my_deck[i].getSuit().equals(Card.DIAMOND)) {
							return my_deck[i];

						}
					}
				}
				else {  }
			}
			
			for (int i = 0; i < count; i++) {
				if (my_deck[i].getSuit().equals(next_suit)) {
					if (my_deck[i].getCount() == Card.KING) {
						return my_deck[i];

					}
					else if (my_deck[i].getCount() == 7) {
						int cnt = 0;
						for (int j = 0; j < count; j++) {
							if (my_deck[j].getSuit().equals(next_suit))
								cnt++;
						}
						if (cnt == 1) {
							return my_deck[i];
						}
						for (int j = 0; j < count; j++) {
							if (my_deck[j].getSuit().equals(next_suit) && my_deck[j].getCount() != 7)
								return my_deck[j];
						}
					}
					else if (my_deck[i].getCount() == Card.ACE || my_deck[i].getCount() == 2) {
						int cnt = 1;
						for (int j = 0; j < count; j++) {
							if (my_deck[j].getCount() == Card.ACE || my_deck[j].getCount() == 2 || my_deck[j].getSuit().equals(Card.JOKER_COLOR) || my_deck[j].getSuit().equals(Card.JOKER_BLACK)) {
								cnt++;
							}
						}
						if (cnt == count) {
							return my_deck[i];

						}
						if (cnt > count*2) {
							double d = Math.random();
							if (d > 0.75) {
								return my_deck[i];

							}
						}
					}
					else {
						return my_deck[i];

					}
				}
			}
			for (int i = 0; i < count; i++) {
				if (my_deck[i].getCount() == next_count) {
					return my_deck[i];

				}
			}
			for (int i = 0; i < count; i++) {
				if (my_deck[i].getSuit().equals(Card.JOKER_COLOR) || my_deck[i].getSuit().equals(Card.JOKER_BLACK)) {
					int cnt = 0;
					for (int j = 0; j < count; j++) {
						if (my_deck[j].getCount() == Card.ACE || my_deck[j].getCount() == 2 || my_deck[j].getSuit().equals(Card.JOKER_BLACK) || my_deck[j].getSuit().equals(Card.JOKER_COLOR)) {
							cnt++;
						}	
					}
					if (cnt == count) {
						return my_deck[i];
					}
					if (cnt > count*2) {
						double d = Math.random();
						if (d > 0.75) {
							return my_deck[i];

						}
					}
				}
			}
			return null;
		}
		
	}
}
