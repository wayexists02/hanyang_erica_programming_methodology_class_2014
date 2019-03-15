package Data;

//��� ������ Ŭ����
public class User implements Player {
	private int count = 0; //�ش� �÷��̾ ������ �ִ� ī�� ����
	private String name = ""; //�ش� �÷��̾��� �̸� ��ȯ
	private Card[] my_deck = null; //�ش� �÷��̾��� ī�� �� ��ȯ
	
	private boolean victory = false; //�ش� �÷��̾��� �¸� ����
	
	//������
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
	//�ش� ī���� �ε����� ã�Ƽ� ��ȯ
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
	//�̰����� ȣ��
	public void victory() {
		victory = true;
	}
	//�̰���� Ȯ��
	public boolean isVictory() {
		return victory;
	}
}
