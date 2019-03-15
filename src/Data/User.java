package Data;

//사람 유저의 클래스
public class User implements Player {
	private int count = 0; //해당 플레이어가 가지고 있는 카드 갯수
	private String name = ""; //해당 플레이어의 이름 반환
	private Card[] my_deck = null; //해당 플레이어의 카드 덱 반환
	
	private boolean victory = false; //해당 플레이어의 승리 여부
	
	//생성자
	public User(String name) {
		this.name = name;
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
		
		for (int i = idx; i < count-1; i++) {
			my_deck[i] = my_deck[i+1];
		}
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
	//이겼으면 호출
	public void victory() {
		victory = true;
	}
	//이겼는지 확인
	public boolean isVictory() {
		return victory;
	}
}
