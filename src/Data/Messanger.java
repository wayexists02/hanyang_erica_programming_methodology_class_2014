package Data;

//Data 패키지에 있는 인스턴스 반환하는 클래스
public class Messanger {
	//-----------------------------------------------//
	//이 클래스의 인스턴스는 하나밖에 생성할 수 없다
	private static Messanger inst = null;
	public static Messanger getInstance() {
		if (inst == null) 
			inst = new Messanger();
		return inst;
	}
	//-------------------------------------------------//
	
	//카드 인스턴스 반환
	public Card getCardInstance(String suit, int count) { return new Card(suit, count); }
	//컴퓨터 인스턴스 반환
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
	//유저 인스턴스 반환
	public Player getUserInstance(String name) { return new User(name); }
	//덱 인스턴스 반환
	public CardDeck getCardDeckInstance() { return new CardDeck(); }
}
