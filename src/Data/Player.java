package Data;

//�÷��̾� �������̽�. ���� : Computer, User
public interface Player {
	public static final int LOW = 1, MIDDLE = 2, HIGH = 3;
	
	public void saveCard(Card c) throws ArrayIndexOutOfBoundsException; //ī�带 �Դ´�
	public void putCard(Card c); //ī�带 ����
	public String getName(); //�ش� �÷��̾��� �̸��� ��ȯ�Ѵ�.
	public Card[] getDeck(); //�ش� �÷��̾��� ���� ��ȯ�Ѵ�.
	public int getCount(); //�ش� �÷��̾ ������ �ִ� ī�� ������ ��ȯ�Ѵ�.
	public void victory(); //�¸������� ȣ���Ѵ�.
	public boolean isVictory(); //�¸��ߴ��� �ñ��ϸ� ȣ���Ѵ�.
}
