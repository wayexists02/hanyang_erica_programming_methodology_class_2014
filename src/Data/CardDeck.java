package Data;

//ī�� ���� �� Ŭ����
public class CardDeck {
	private int card_count;
	private Card[] deck = new Card[4 * Card.SIZE_OF_ONE_SUIT + 2];
	private Card[] redeck = new Card[4 * Card.SIZE_OF_ONE_SUIT + 2];
	
	// �� ���� �޼ҵ�
	private void createSuit(String which_suit) {
		for (int i = 1; i <= Card.SIZE_OF_ONE_SUIT; i++) {
			deck[card_count] = new Card(which_suit, i);
			card_count++;
		}
	}
	
	// ������ (�� ����)
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
	
	// ������ ������ ī�� ��ȯ
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
	
	// ���� �ִ� ���� ī�� ���� üũ
	public int getCount() { 
		return card_count; 
	}
	
	// ī�带 ����ִ� ���� ����
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
	
	// ���� ��� ���, �ٸ� ������ ��ü
	public void shuffle() {
		deck = redeck;
		for (int i = 0; i < 54; i++)
			if (deck[i] != null)
				card_count++;
		redeck = new Card[4 * Card.SIZE_OF_ONE_SUIT + 2];
	}
}