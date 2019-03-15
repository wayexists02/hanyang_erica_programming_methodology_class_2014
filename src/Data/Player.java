package Data;

//플레이어 인터페이스. 구현 : Computer, User
public interface Player {
	public static final int LOW = 1, MIDDLE = 2, HIGH = 3;
	
	public void saveCard(Card c) throws ArrayIndexOutOfBoundsException; //카드를 먹는다
	public void putCard(Card c); //카드를 낸다
	public String getName(); //해당 플레이어의 이름을 반환한다.
	public Card[] getDeck(); //해당 플레이어의 덱을 반환한다.
	public int getCount(); //해당 플레이어가 가지고 있는 카드 갯수를 반환한다.
	public void victory(); //승리했으면 호출한다.
	public boolean isVictory(); //승리했는지 궁금하면 호출한다.
}
