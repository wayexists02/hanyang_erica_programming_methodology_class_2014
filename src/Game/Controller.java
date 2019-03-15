package Game;

import java.awt.event.*;

import javax.swing.*;

import Data.*;

//���� �߾� ��Ʈ�ѷ�
public class Controller implements Runnable {
	private boolean suspend = false; //Controller ������ ���� ����
	private boolean stop = false; //Controller ������ ���� ����
	
	private ThreadInterface[] threads = null; //�÷��̾� ������
	private Messanger messanger = null; //Data Model �ν��Ͻ� �ҷ����� �ν��Ͻ�
	private Printer out = null; //����ϴ� Ŭ���� �ν��Ͻ�
	
	private Reader in = null; //�Է¹޴� Ŭ���� �ν��Ͻ�
	private Card card_floor = null; //�ٴڿ� ��� ī�� �ν��Ͻ�
	private CardDeck deck = null; //ī�� �� �ν��Ͻ�
	private Player[] player = null; //�÷��̾� �迭 �ν��Ͻ�
	private GameViewer viewer = null; //��� �ν��Ͻ�
	
	private String next_suit = ""; //�ش� ���� �÷��̾ �� �� �ִ� ī�幫��
	private int next_count = 0; //�ش� ���� �÷��̾ �� �� �ִ� ī���ȣ
	private int difficulty = 0; //���̵�
	private int stack = 0; //����ī������� ���̴� ī�� ��ο� ����
	private int turn = 0; //�ش� ��
	private boolean q = false; //Queen ī�� ȿ�� ����
	
//-------------------------------------------------------------------------//
	// �� ��ü�� �� �ϳ��� ������ �� �ִ�.
	private static Controller inst = null;
	public static Controller getInstance() {
		if (inst == null)
			inst = new Controller();
		return inst;
	}
	private Controller() {  }
//--------------------------------------------------------------------------//
	public void run() {
		boolean goOn = false; //�÷��̾ �¸������� ���� ������� ����
		
		threads = new ThreadInterface[4];
		
		messanger = Messanger.getInstance();
		out = new Printer();
		in = new Reader(this);
		
		//���̵� �޴µ��� ���� ������ ����.
		in.readDifficulty();
		while (true) {
			if (!suspend) {
				break;
			}
			else {
				Thread.yield();
			}
		}
		//�� ����
		deck = messanger.getCardDeckInstance();
		
		//�÷��̾� ����
		player = new Player[4];
		player[0] = messanger.getUserInstance("�÷��̾�");
		for (int i = 1; i < player.length; i++) {
			player[i] = messanger.getComputerInstance(difficulty, i);
		}
		
		//ó���� ��ο��� ī�� ���� ����
		int number = in.readNumber();
		for (int i = 0; i < threads.length; i++) {
			for (int j = 0; j < number; j++) {
				player[i].saveCard(deck.newCard());
			}
		}
		
		//�� ����
		viewer = GameViewer.getInstance();
		viewer.updatePanel(player, turn);
		viewer.repaint();
		viewer.setNameLabel(player);
		viewer.setBorderLabel(turn);
		
		setFloorCard(deck.newCard());
		
		// �� �÷��̾�, ��ǻ�͵� ������ ����
		threads[0] = new UserThread();
		for (int i = 1; i < player.length; i++) {
			threads[i] = new ComputerThread(player[i], this);
		}
		
		for (int i = 0; i < player.length; i++) {
			threads[i].suspended();
			
			threads[i].start();
		}

		//���� ���� ����
		while (!stop) {
			if (!suspend) {
				int cnt = 0;
				for (int i = 0; i < player.length; i++) {
					if (player[i].isVictory())
						cnt++;
				}
				if (cnt == 3) {
					out.showMessage(player[turn].getName()+" ���� �й��ϼ̽��ϴ�!");
					break;
				}
				else if (player[0].isVictory()) {
					if (!goOn) {
						out.showMessage(player[0].getName()+" ���� �¸��ϼ̽��ϴ�!");
						if (!in.readGoOn()) {
							break;
						}
						goOn = true;
					}
				}
				
				threads[turn].resumed();
				suspend();
			}
			else {
				Thread.yield();
			}
		}
		
		//��� �����带 ������.
		for (int i = 0; i < player.length; i++) {
			threads[i].stopped();
		}
		viewer.dispose();
		new EndViewer(player);
	}
	public synchronized void suspend() { suspend = true; } //Controller ������ �Ͻ�����
	public synchronized void resume() { suspend = false; } //Controller ������ ���
	public synchronized void stop() {  stop = true;  } //Controller ������ ����
	
	public String getNextSuit() { return next_suit; } //�ش� ���� �÷��̾ �� �� �ִ� ī�幫�� ��ȯ
	public int getNextCount() { return next_count; } //�ش� ���� �÷��̾ �� �� �ִ� ī���ȣ ��ȯ
	public int getStack() { return stack; } //����ī�带 ������ ���̴� ��ο��ϴ� ī�� �� ��ȯ
	public synchronized void setNextSuit(String suit) { next_suit = suit; } //�ش� ���� �÷��̾ �� �� �ִ� ī�幫�� ����
	public synchronized void setNextCount(int i) { next_count = i; } //�ش� ���� �÷��̾ �� �� �ִ� ī���ȣ ����
	
	//�ϳѱ��
	public synchronized void nextTurn(int i) {
		threads[turn].suspended();
		victory();
		
		addTurn(i);
		
		resume();
		viewer.setBorderLabel(turn);
		viewer.updatePanel(player, turn);
	}
	//���� ����.
	private void addTurn(int i) {
		if (q) {
			for (int j = 0; j < i; j++) {
				turn -= 1;
				if (turn < 0)
					turn += 4;
				if (player[turn].isVictory())
					addTurn(1);
			}
		}
		else {
			for (int j = 0; j < i; j++) {
				turn += 1;
				if (turn > 3)
					turn -= 4;
				if (player[turn].isVictory())
					addTurn(1);
			}
		}
	}
	//�ٴڿ� ��� ī�� ����
	public void setFloorCard(Card c) {
		deck.putCard(card_floor);
		card_floor = c;
		next_suit = card_floor.getSuit();
		next_count = card_floor.getCount();
		viewer.setFloorCard(card_floor);
	}
	//�ش� ���� �÷��̾��� ī�尡 0���̸� �¸� ó��
	public void victory() {
		if (player[turn].getCount() == 0)
			player[turn].victory();
	}
	//�÷��̾� �Ǵ� ��ǻ�Ͱ� �� ī�� ó�� (null �Է½� ��ο�)
	public synchronized void putCard(Card c) {
		if (c != null) {
			new CardThread(turn, c);
			setFloorCard(c);
		}
		
		if (c == null) {
			try {
				if (stack == 0) {
					if (deck.getCount() < 1)
						deck.shuffle();
					player[turn].saveCard(deck.newCard());		
				}
				else {
					for (int i = 0; i < stack; i++) {
						if (deck.getCount() < 1)
							deck.shuffle();
						player[turn].saveCard(deck.newCard());
					}
				}
				new DrawThread(turn);
			}
			catch (ArrayIndexOutOfBoundsException e) {
				out.showMessage(player[turn].getName()+"���� �й��߽��ϴ�!");
				stop();
				resume();
				threads[turn].suspended();
				return;
			}
			stack = 0;
			nextTurn(1);
			return;
		}
		else if (c.getCount() == Card.ACE) {
			stack += 3;
		}
		else if (c.getCount() == 2) {
			stack += 2;
		}
		else if (c.getCount() == 7) {
			if (turn == 0) {
				int option = in.readSeven();
				
				switch (option) {
				case 0 :
					next_suit = Card.SPADE;
					break;
				case 1 :
					next_suit = Card.CLUB;
					break;
				case 2 :
					next_suit = Card.HEART;
					break;
				case 3 :
					next_suit = Card.DIAMOND;
					break;
				}
			}
			else {
				if (difficulty == Player.LOW) {
					caseSevenLow();
				}
				else if (difficulty == Player.MIDDLE) {
					caseSevenMid();
				}
				else if (difficulty == Player.HIGH) {
					caseSevenHigh();
				}
				else {  }
			}
			out.showMessage(next_suit+"�� ������ϴ�!");
		}
		else if (c.getCount() == Card.JACK) {
			player[turn].putCard(c);
			nextTurn(2);
			return;
		}
		else if (c.getCount() == Card.QUEEN) {
			if (q)
				q = false;
			else
				q = true;
		}
		else if (c.getCount() == Card.KING) {
			player[turn].putCard(c);
			viewer.setFloorCard(card_floor);
			viewer.updatePanel(player, turn);
			if (!player[turn].isVictory())
				return;
		}
		else if (c.getSuit().equals(Card.JOKER_COLOR)) {
			stack += 10;
		}
		else if (c.getSuit().equals(Card.JOKER_BLACK)) {
			stack += 5;
		}
		else {
			
		}
		player[turn].putCard(c);
		nextTurn(1);
	}
	

	//���̵� '��'�̰� 7�� ������ ��ǻ���� ����
	public void caseSevenLow() {
		int random = (int)(Math.random()*4);
		switch (random) {
		case 0 :
			next_suit = Card.SPADE;
			break;
		case 1 :
			next_suit = Card.CLUB;
			break;
		case 2 :
			next_suit = Card.HEART;
			break;
		case 3 :
			next_suit = Card.DIAMOND;
			break;
		}
	}
	
	//���̵� '��'�̰� 7�� ������ ��ǻ���� ����
	public void caseSevenMid() {
		int random = (int)(Math.random()*3);
		switch (random) {
		case 0 :
			if (next_suit.equals(Card.SPADE))
				next_suit = Card.CLUB;
			else
				next_suit = Card.SPADE;
			break;
		case 1 :
			if (next_suit.equals(Card.CLUB))
				next_suit = Card.HEART;
			else
				next_suit = Card.CLUB;
			break;
		case 2 :
			if (next_suit.equals(Card.HEART))
				next_suit = Card.DIAMOND;
			else
				next_suit = Card.HEART;
			break;
		}
	}
	
	//���̵� '��'�̰� 7 ������ ��ǻ���� ����
	public void caseSevenHigh() {
		Card[] my_deck = player[turn].getDeck();
		int cnt_spade = 0;
		int cnt_club = 0;
		int cnt_heart = 0;
		int cnt_diamond = 0;
		for (int i = 0; i < player[turn].getCount(); i++) {
			if (my_deck[i].getSuit().equals(Card.SPADE))
				cnt_spade++;
			else if (my_deck[i].getSuit().equals(Card.CLUB))
				cnt_club++;
			else if (my_deck[i].getSuit().equals(Card.HEART))
				cnt_heart++;
			else if (my_deck[i].getSuit().equals(Card.DIAMOND))
				cnt_diamond++;
			else {  }
		}

		// 4���� ���� ū�� ���ϱ�
		int SUIT = (cnt_spade > cnt_club? (cnt_spade > cnt_heart ? (cnt_spade > cnt_diamond ? cnt_spade : cnt_diamond) : (cnt_heart > cnt_diamond ? cnt_heart : cnt_diamond)) : (cnt_club > cnt_heart ? (cnt_club > cnt_diamond ? cnt_club : cnt_diamond) : (cnt_heart > cnt_diamond ? cnt_heart : cnt_diamond)));
		if (SUIT == cnt_spade) {
			next_suit = Card.SPADE;
		}
		else if (SUIT == cnt_club) {
			next_suit = Card.CLUB;
		}
		else if (SUIT == cnt_heart) {
			next_suit = Card.HEART;
		}
		else if (SUIT == cnt_diamond) {
			next_suit = Card.DIAMOND;
		}
		else {  }
	}

	//���̵� �о���̴� �ν��Ͻ��� �׼� ������
	class DifficultyListener implements ActionListener {
		private JFrame frm = null;
		
		public DifficultyListener(JFrame frm) {
			this.frm = frm;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("��"))
				difficulty = Player.LOW;
			else if (e.getActionCommand().equals("��"))
				difficulty = Player.MIDDLE;
			else if (e.getActionCommand().equals("��"))
				difficulty = Player.HIGH;
			else {  }
			
			frm.dispose();
			resume();
		}
	}
}
