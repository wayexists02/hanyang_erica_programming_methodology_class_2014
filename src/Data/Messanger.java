package Data;

//Data ��Ű���� �ִ� �ν��Ͻ� ��ȯ�ϴ� Ŭ����
public class Messanger {
	//-----------------------------------------------//
	//�� Ŭ������ �ν��Ͻ��� �ϳ��ۿ� ������ �� ����
	private static Messanger inst = null;
	public static Messanger getInstance() {
		if (inst == null) 
			inst = new Messanger();
		return inst;
	}
	//-------------------------------------------------//
	
	//ī�� �ν��Ͻ� ��ȯ
	public Card getCardInstance(String suit, int count) { return new Card(suit, count); }
	//��ǻ�� �ν��Ͻ� ��ȯ
	public Player getComputerInstance(int difficulty, int order) {
		if (difficulty == Player.LOW)
			return new Computer_low(order);
		else if (difficulty == Player.MIDDLE)
			return new Computer_mid(order);
		else if (difficulty == Player.HIGH)
			return new Computer_high(order);
		else
			return null;
	}
	//���� �ν��Ͻ� ��ȯ
	public Player getUserInstance(String name) { return new User(name); }
	//�� �ν��Ͻ� ��ȯ
	public CardDeck getCardDeckInstance() { return new CardDeck(); }
}
