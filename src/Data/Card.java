package Data;

import javax.swing.*;

//카드 클래스
public class Card {
	public static final String SPADE = "SPADE";
	public static final String CLUB = "CLUB";
	public static final String HEART = "HEART";
	public static final String DIAMOND = "DIAMOND";
	public static final String JOKER_COLOR = "JOKER_COLOR";
	public static final String JOKER_BLACK = "JOKER_BLACK";
	
	public static final int ACE = 1;
	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;
	
	public static final int WIDTH = 75;
	public static final int HEIGHT = 90;
	public static final int SIZE_OF_ONE_SUIT = 13; //무늬당 카드 갯수
	
	private String suit = ""; //해당 카드의 무늬
	private int count = 0; //해당 카드의 번호
	private ImageIcon img = null; //해당 카드에 해당하는 이미지아이콘
	
	//생성자
	public Card(String suit, int cnt) {
		this.suit = suit;
		count = cnt;
		setImage();
	}
	
	//카드 뒷면 이미지 반환
	public static ImageIcon getBackIcon() { return new ImageIcon("Image\\Back.PNG"); }
	
	public String getSuit() { return suit; } //해당 카드의 무늬 반환
	public int getCount() { return count; } //해당 카드의 번호 반환
	public ImageIcon getIcon() { return img; } //해당 카드의 이미지아이콘 반환
	

	//이미지 셋팅
	public void setImage() {
		switch (count) {
		case 0 :
			if (suit.equals(JOKER_COLOR))
				img = new ImageIcon("Image\\Joker.PNG");
			else if (suit.equals(JOKER_BLACK))
				img = new ImageIcon("Image\\JokerBlack.PNG");
		case ACE :
			if (suit.equals(SPADE))
				img = new ImageIcon("Image\\Spade Ace.PNG");
			else if (suit.equals(CLUB))
				img = new ImageIcon("Image\\Club Ace.PNG");
			else if (suit.equals(HEART))
				img = new ImageIcon("Image\\Heart Ace.PNG");
			else if (suit.equals(DIAMOND))
				img = new ImageIcon("Image\\Diamond Ace.PNG");
			else {  }
			break;
		case 2 :
			if (suit.equals(SPADE))
				img = new ImageIcon("Image\\Spade 2.PNG");
			else if (suit.equals(CLUB))
				img = new ImageIcon("Image\\Club 2.PNG");
			else if (suit.equals(HEART))
				img = new ImageIcon("Image\\Heart 2.PNG");
			else if (suit.equals(DIAMOND))
				img = new ImageIcon("Image\\Diamond 2.PNG");
			else {  }
			break;
		case 3 :
			if (suit.equals(SPADE))
				img = new ImageIcon("Image\\Spade 3.PNG");
			else if (suit.equals(CLUB))
				img = new ImageIcon("Image\\Club 3.PNG");
			else if (suit.equals(HEART))
				img = new ImageIcon("Image\\Heart 3.PNG");
			else if (suit.equals(DIAMOND))
				img = new ImageIcon("Image\\Diamond 3.PNG");
			else {  }
			break;
		case 4 :
			if (suit.equals(SPADE))
				img = new ImageIcon("Image\\Spade 4.PNG");
			else if (suit.equals(CLUB))
				img = new ImageIcon("Image\\Club 4.PNG");
			else if (suit.equals(HEART))
				img = new ImageIcon("Image\\Heart 4.PNG");
			else if (suit.equals(DIAMOND))
				img = new ImageIcon("Image\\Diamond 4.PNG");
			else {  }
			break;
		case 5 :
			if (suit.equals(SPADE))
				img = new ImageIcon("Image\\Spade 5.PNG");
			else if (suit.equals(CLUB))
				img = new ImageIcon("Image\\Club 5.PNG");
			else if (suit.equals(HEART))
				img = new ImageIcon("Image\\Heart 5.PNG");
			else if (suit.equals(DIAMOND))
				img = new ImageIcon("Image\\Diamond 5.PNG");
			else {  }
			break;
		case 6 :
			if (suit.equals(SPADE))
				img = new ImageIcon("Image\\Spade 6.PNG");
			else if (suit.equals(CLUB))
				img = new ImageIcon("Image\\Club 6.PNG");
			else if (suit.equals(HEART))
				img = new ImageIcon("Image\\Heart 6.PNG");
			else if (suit.equals(DIAMOND))
				img = new ImageIcon("Image\\Diamond 6.PNG");
			else {  }
			break;
		case 7 :
			if (suit.equals(SPADE))
				img = new ImageIcon("Image\\Spade 7.PNG");
			else if (suit.equals(CLUB))
				img = new ImageIcon("Image\\Club 7.PNG");
			else if (suit.equals(HEART))
				img = new ImageIcon("Image\\Heart 7.PNG");
			else if (suit.equals(DIAMOND))
				img = new ImageIcon("Image\\Diamond 7.PNG");
			else {  }
			break;
		case 8 :
			if (suit.equals(SPADE))
				img = new ImageIcon("Image\\Spade 8.PNG");
			else if (suit.equals(CLUB))
				img = new ImageIcon("Image\\Club 8.PNG");
			else if (suit.equals(HEART))
				img = new ImageIcon("Image\\Heart 8.PNG");
			else if (suit.equals(DIAMOND))
				img = new ImageIcon("Image\\Diamond 8.PNG");
			else {  }
			break;
		case 9 :
			if (suit.equals(SPADE))
				img = new ImageIcon("Image\\Spade 9.PNG");
			else if (suit.equals(CLUB))
				img = new ImageIcon("Image\\Club 9.PNG");
			else if (suit.equals(HEART))
				img = new ImageIcon("Image\\Heart 9.PNG");
			else if (suit.equals(DIAMOND))
				img = new ImageIcon("Image\\Diamond 9.PNG");
			else {  }
			break;
		case 10 :
			if (suit.equals(SPADE))
				img = new ImageIcon("Image\\Spade 10.PNG");
			else if (suit.equals(CLUB))
				img = new ImageIcon("Image\\Club 10.PNG");
			else if (suit.equals(HEART))
				img = new ImageIcon("Image\\Heart 10.PNG");
			else if (suit.equals(DIAMOND))
				img = new ImageIcon("Image\\Diamond 10.PNG");
			else {  }
			break;
		case JACK :
			if (suit.equals(SPADE))
				img = new ImageIcon("Image\\Spade Jack.PNG");
			else if (suit.equals(CLUB))
				img = new ImageIcon("Image\\Club Jack.PNG");
			else if (suit.equals(HEART))
				img = new ImageIcon("Image\\Heart Jack.PNG");
			else if (suit.equals(DIAMOND))
				img = new ImageIcon("Image\\Diamond Jack.PNG");
			else {  }
			break;
		case QUEEN :
			if (suit.equals(SPADE))
				img = new ImageIcon("Image\\Spade Queen.PNG");
			else if (suit.equals(CLUB))
				img = new ImageIcon("Image\\Club Queen.PNG");
			else if (suit.equals(HEART))
				img = new ImageIcon("Image\\Heart Queen.PNG");
			else if (suit.equals(DIAMOND))
				img = new ImageIcon("Image\\Diamond Queen.PNG");
			else {  }
			break;
		case KING :
			if (suit.equals(SPADE))
				img = new ImageIcon("Image\\Spade King.PNG");
			else if (suit.equals(CLUB))
				img = new ImageIcon("Image\\Club King.PNG");
			else if (suit.equals(HEART))
				img = new ImageIcon("Image\\Heart King.PNG");
			else if (suit.equals(DIAMOND))
				img = new ImageIcon("Image\\Diamond King.PNG");
			else {  }
			break;
		default :
			break;
		}
	}
}
