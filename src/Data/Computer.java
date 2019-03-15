package Data;

//컴퓨터 클래스. 상속 : Computer_high, Computer_mid, Computer_low
public abstract class Computer implements Player {
	protected int count = 0; //해당 컴퓨터가 가지고 있는 카드 갯수
	protected Card[] my_deck = null; //해당 컴퓨터의 카드 덱
	private String name = ""; //해당 컴퓨터의 이름
	
	private boolean victory = false; //해당 컴퓨터의 승리 여부
	
	//생성자
	public Computer(int idx) {
		name = "컴퓨터 "+idx;
		my_deck = new Card[20];
	}
	
	@Override
	public void saveCard(Card c) throws ArrayIndexOutOfBoundsException {
		// TODO Auto-generated method stub
		my_deck[count++] = c;
	}
	@Override
	public void putCard(Card c) {
		// TODO Auto-generated method stub
		int idx = searchCard(c);
		
		for (int i = idx; i < count-1; i++)
			my_deck[i] = my_deck[i+1];
		my_deck[--count] = null;
	}
	//해당 카드의 인덱스를 찾아서 반환
	private int searchCard(Card c) {
		for (int i = 0; i < count; i++) {
			if (my_deck[i].getSuit().equals(c.getSuit()) && my_deck[i].getCount() == c.getCount())
				return i;
		}
		return -1;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public Card[] getDeck() {
		// TODO Auto-generated method stub
		return my_deck;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}
	//승리(0장)하면 호출
	public void victory() {
		victory = true;
	}
	//승리했는지의 여부
	public boolean isVictory() { return victory; }

	//컴퓨터의 낼 카드 고르기
	public abstract Card start(String next_suit, int next_count, int stack);
}
