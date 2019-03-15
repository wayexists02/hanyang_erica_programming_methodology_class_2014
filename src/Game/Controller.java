package Game;

import java.awt.event.*;

import javax.swing.*;

import Data.*;

//메인 중앙 컨트롤러
public class Controller implements Runnable {
	private boolean suspend = false; //Controller 쓰레드 중지 상태
	private boolean stop = false; //Controller 쓰레드 종료 상태
	
	private ThreadInterface[] threads = null; //플레이어 쓰레드
	private Messanger messanger = null; //Data Model 인스턴스 불러오는 인스턴스
	private Printer out = null; //출력하는 클래스 인스턴스
	
	private Reader in = null; //입력받는 클래스 인스턴스
	private Card card_floor = null; //바닥에 까는 카드 인스턴스
	private CardDeck deck = null; //카드 덱 인스턴스
	private Player[] player = null; //플레이어 배열 인스턴스
	private GameViewer viewer = null; //뷰어 인스턴스
	
	private String next_suit = ""; //해당 턴의 플레이어가 낼 수 있는 카드무늬
	private int next_count = 0; //해당 턴의 플레이어가 낼 수 있는 카드번호
	private int difficulty = 0; //난이도
	private int stack = 0; //공격카드냈을때 쌓이는 카드 드로우 개수
	private int turn = 0; //해당 턴
	private boolean q = false; //Queen 카드 효과 유무
	
//-------------------------------------------------------------------------//
	// 이 객체는 단 하나만 생성할 수 있다.
	private static Controller inst = null;
	public static Controller getInstance() {
		if (inst == null)
			inst = new Controller();
		return inst;
	}
	private Controller() {  }
//--------------------------------------------------------------------------//
	public void run() {
		boolean goOn = false; //플레이어가 승리했을때 게임 계속할지 여부
		
		threads = new ThreadInterface[4];
		
		messanger = Messanger.getInstance();
		out = new Printer();
		in = new Reader(this);
		
		//난이도 받는동안 현재 쓰레드 중지.
		in.readDifficulty();
		while (true) {
			if (!suspend) {
				break;
			}
			else {
				Thread.yield();
			}
		}
		//덱 생성
		deck = messanger.getCardDeckInstance();
		
		//플레이어 생성
		player = new Player[4];
		player[0] = messanger.getUserInstance("플레이어");
		for (int i = 1; i < player.length; i++) {
			player[i] = messanger.getComputerInstance(difficulty, i);
		}
		
		//처음에 드로우할 카드 갯수 선택
		int number = in.readNumber();
		for (int i = 0; i < threads.length; i++) {
			for (int j = 0; j < number; j++) {
				player[i].saveCard(deck.newCard());
			}
		}
		
		//판 세팅
		viewer = GameViewer.getInstance();
		viewer.updatePanel(player, turn);
		viewer.repaint();
		viewer.setNameLabel(player);
		viewer.setBorderLabel(turn);
		
		setFloorCard(deck.newCard());
		
		// 각 플레이어, 컴퓨터들 쓰레드 생성
		threads[0] = new UserThread();
		for (int i = 1; i < player.length; i++) {
			threads[i] = new ComputerThread(player[i], this);
		}
		
		for (int i = 0; i < player.length; i++) {
			threads[i].suspended();
			
			threads[i].start();
		}

		//메인 게임 시작
		while (!stop) {
			if (!suspend) {
				int cnt = 0;
				for (int i = 0; i < player.length; i++) {
					if (player[i].isVictory())
						cnt++;
				}
				if (cnt == 3) {
					out.showMessage(player[turn].getName()+" 님이 패배하셨습니다!");
					break;
				}
				else if (player[0].isVictory()) {
					if (!goOn) {
						out.showMessage(player[0].getName()+" 님이 승리하셨습니다!");
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
		
		//모든 쓰레드를 끝낸다.
		for (int i = 0; i < player.length; i++) {
			threads[i].stopped();
		}
		viewer.dispose();
		new EndViewer(player);
	}
	public synchronized void suspend() { suspend = true; } //Controller 쓰레드 일시정지
	public synchronized void resume() { suspend = false; } //Controller 쓰레드 재생
	public synchronized void stop() {  stop = true;  } //Controller 쓰레드 끄기
	
	public String getNextSuit() { return next_suit; } //해당 턴의 플레이어가 낼 수 있는 카드무늬 반환
	public int getNextCount() { return next_count; } //해당 턴의 플레이어가 낼 수 있는 카드번호 반환
	public int getStack() { return stack; } //공격카드를 냈을때 쌓이는 드로우하는 카드 수 반환
	public synchronized void setNextSuit(String suit) { next_suit = suit; } //해당 턴의 플레이어가 낼 수 있는 카드무늬 설정
	public synchronized void setNextCount(int i) { next_count = i; } //해당 턴의 플레이어가 낼 수 있는 카드번호 설정
	
	//턴넘기기
	public synchronized void nextTurn(int i) {
		threads[turn].suspended();
		victory();
		
		addTurn(i);
		
		resume();
		viewer.setBorderLabel(turn);
		viewer.updatePanel(player, turn);
	}
	//턴을 더함.
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
	//바닥에 까는 카드 설정
	public void setFloorCard(Card c) {
		deck.putCard(card_floor);
		card_floor = c;
		next_suit = card_floor.getSuit();
		next_count = card_floor.getCount();
		viewer.setFloorCard(card_floor);
	}
	//해당 턴의 플레이어의 카드가 0장이면 승리 처리
	public void victory() {
		if (player[turn].getCount() == 0)
			player[turn].victory();
	}
	//플레이어 또는 컴퓨터가 낸 카드 처리 (null 입력시 드로우)
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
				out.showMessage(player[turn].getName()+"님이 패배했습니다!");
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
			out.showMessage(next_suit+"를 골랐습니다!");
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
	

	//난이도 '하'이고 7을 냈을때 컴퓨터의 선택
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
	
	//난이도 '중'이고 7을 냈을때 컴퓨터의 선택
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
	
	//난이도 '상'이고 7 냈을때 컴퓨터의 선택
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

		// 4개중 가장 큰값 구하기
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

	//난이도 읽어들이는 인스턴스의 액션 리스너
	class DifficultyListener implements ActionListener {
		private JFrame frm = null;
		
		public DifficultyListener(JFrame frm) {
			this.frm = frm;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("하"))
				difficulty = Player.LOW;
			else if (e.getActionCommand().equals("중"))
				difficulty = Player.MIDDLE;
			else if (e.getActionCommand().equals("상"))
				difficulty = Player.HIGH;
			else {  }
			
			frm.dispose();
			resume();
		}
	}
}
