package Data;

//카드 메인 덱 클래스
public class CardDeck {
	private int card_count;
	private Card[] deck = new Card[4 * Card.SIZE_OF_ONE_SUIT + 2];
	private Card[] redeck = new Card[4 * Card.SIZE_OF_ONE_SUIT + 2];
	
	// 덱 생성 메소드
	private void createSuit(String which_suit) {
		for (int i = 1; i <= Card.SIZE_OF_ONE_SUIT; i++) {
			deck[card_count] = new Card(which_suit, i);
			card_count++;
		}
	}
	
	// 생성자 (덱 생성)
	public CardDeck() {
		createSuit(Card.SPADE);
		createSuit(Card.HEART);
		createSuit(Card.CLUB);
		createSuit(Card.DIAMOND);
		deck[card_count] = new Card(Card.JOKER_COLOR, 0);
		card_count++;
		deck[card_count] = new Card(Card.JOKER_BLACK, 0);
		card_count++;
	}
	
	// 덱에서 임의의 카드 반환
	public Card newCard() {
		Card next_card = null;
		if(card_count != 0) {
			int index = (int)(Math.random() * card_count);
			next_card = deck[index];
			for (int i = index + 1; i < card_count; i++)
				deck[i - 1] = deck[i];
			card_count--;
		}
		return next_card;
	}
	
	// 덱에 있는 현재 카드 개수 체크
	public int getCount() { 
		return card_count; 
	}
	
	// 카드를 비어있는 덱에 넣음
	public void putCard(Card c) {
		if (redeck[0] == null) {
			redeck[0] = c;
		}
		else {
			for (int i = 53; i >= 1; i--) {
				redeck[i] = redeck[i - 1];
			}
			redeck[0] = c;
		}
	}
	
	// 덱이 모두 비면, 다른 덱으로 교체
	public void shuffle() {
		deck = redeck;
		for (int i = 0; i < 54; i++)
			if (deck[i] != null)
				card_count++;
		redeck = new Card[4 * Card.SIZE_OF_ONE_SUIT + 2];
	}
}