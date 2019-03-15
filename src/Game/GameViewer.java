package Game;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import Data.*;

//메인 뷰어
public class GameViewer extends JPanel {
	
	private SouthPanel south = null; //플레이어의 카드가 보여지는 패널
	private WestPanel west = null; //플레이어의 카드가 보여지는 패널
	private EastPanel east = null; //플레이어의 카드가 보여지는 패널
	private NorthPanel north = null; //플레이어의 카드가 보여지는 패널
	private JFrame frm = null; //메인 프레임
	private JButton card_floor = null; //바닥에 까는 카드를 보여주기 위한 버튼
	
	private JLabel[] nameLabel = null; //이름을  표시함.
	
	private JButton deck = null; //덱을 나타내기 위한 버튼
	private ActionListener deckListener = null; //덱의 액션 리스너 인스턴스
	
	//-------------------------------------------------------------------------------//
	//이 클래스는 단 하나의 인스턴스만 생성할 수 있다.
	private static GameViewer inst = null; 
	public static GameViewer getInstance() {
		if (inst == null)
			inst = new GameViewer();
		return inst;
	}
	//--------------------------------------------------------------------------------//
	private GameViewer() {
		frm = new JFrame("원카드");
		frm.setBounds(0, 0, 706, 760);
		frm.setResizable(false); // 사용자가 임의로 크기를 변경하지 못하게 한다.
		
		setBackground(Color.green); // 배경색 연두
		setLayout(null);
		
		//플레이어들의 패널 만들기.
		north = new NorthPanel();
		west = new WestPanel();
		east = new EastPanel();
		south = new SouthPanel();
		
		deckListener = new DeckListener();
		
		// 덱 만들기
		deck = new JButton(Card.getBackIcon());
		deck.setBounds(312, 255, Card.WIDTH, Card.HEIGHT);
		add(deck);
		deck.addActionListener(deckListener);
		
		// 바닥 카드 만들기
		card_floor = new JButton();
		card_floor.setBounds(312, 355, Card.WIDTH, Card.HEIGHT);
		add(card_floor);
		
		add(north); add(west); add(east); add(south);
		
		//이름 라벨 만들기
		nameLabel = new JLabel[4];
		
		nameLabel[0] = new JLabel("");
		nameLabel[0].setBounds(320, 575, 70, 25);
		
		nameLabel[1] = new JLabel("");
		nameLabel[1].setBounds(530, 337, 70, 25);
		
		nameLabel[2] = new JLabel("");
		nameLabel[2].setBounds(325, 100, 70, 25);
		
		nameLabel[3] = new JLabel("");
		nameLabel[3].setBounds(100, 337, 70, 25);
		
		for (int i = 0; i < nameLabel.length; i++) {
			add(nameLabel[i]);
		}
		
		frm.add(this);
		
		frm.setVisible(true);
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	//이름을 받아와서 이름 라벨에 추가
	public void setNameLabel(Player[] player) {
		for (int i = 0; i < nameLabel.length; i++) {
			nameLabel[i].setText(player[i].getName());
		}
	}
	//해당 턴의 플레이어의 이름에 테두리 칠하기
	public void setBorderLabel(int turn) {
		for (int i = 0; i < nameLabel.length; i++) {
			if (i == turn) {
				Border border = BorderFactory.createEtchedBorder();
				nameLabel[i].setBorder(border);
			}
			else {
				nameLabel[i].setBorder(null);
			}
		}
	}
	//바닥에 까는 카드 셋팅
	public void setFloorCard(Card c) {
		card_floor.setIcon(c.getIcon());
		repaint();
	}
	//플레이어 패널에 나타내어지는 카드 업데이트
	public void updatePanel(Player[] player, int turn) {
		//south.updatePanel(player[0]);
		south.updatePanelOfNoActionListener(player[0]);
		east.updatePanel(player[1]);
		north.updatePanel(player[2]);
		west.updatePanel(player[3]);
		
		//컴퓨터의 턴일떄 드로우 하지 못하게 하기 위해 기존 덱버튼을 제거하고 새로 만든다.
		remove(deck);
		deck = new JButton(Card.getBackIcon());
		deck.setBounds(312, 255, Card.WIDTH, Card.HEIGHT);
		add(deck);
		//플레이어의 턴일때 드로우할수있는 카드 덱 버튼을 새로 만든다.
		if (turn == 0) {
			deck.addActionListener(deckListener);
			south.updatePanel(player[0]);
		}
		repaint();
	}
	//프레임 종료 메소드
	public void dispose() {
		frm.dispose();
	}
	
	public void repaint() {
		//패널들이 완성되지 않았을 경우, 호출되지 않는다.
		if (south == null || north == null || east == null || west == null)
			return;
		
		Graphics g = getGraphics();
		g.drawImage(Card.getBackIcon().getImage(), deck.getX(), deck.getY(), this);
		
		//패널들도 repaint 한다.
		south.repaint();
		north.repaint();
		east.repaint();
		west.repaint();
		super.repaint();
	}
	
	class NorthPanel extends JPanel {
		
		private CardViewer[] cards = null; // 카드 버튼들
		
		//생성자
		public NorthPanel() {
			setBounds(100, 0, 500, 100);
			setBackground(new Color(0, 200, 0));
			setLayout(null);
			
			cards = new CardViewer[20];
		}
		
		//카드 정보를 받아와서 버튼으로 만들어 보여준다.
		public void updatePanel(Player player) {
			//20장이 넘었을 경우에는 호출되지않는다.
			if (player.getCount() > 20)
				return;
			//이전 버튼들을 모두 제거하고 다시 만든다.
			removeAll();
			for (int i = 0; i < player.getCount(); i++) {
				cards[i] = new CardViewer();
				cards[i].setBounds(500-20*i-Card.WIDTH, 0, Card.WIDTH, Card.HEIGHT);
				add(cards[i]);
				if (i == 19)
					break;
			}
			repaint();
		}
	}
	class WestPanel extends JPanel {
		
		private CardViewer[] cards = null; //카드 버튼들
		//생성자
		public WestPanel() {
			setBounds(0, 100, 100, 500);
			setBackground(new Color(0, 200, 0));
			setLayout(null);
			
			cards = new CardViewer[20];
		}
		//카드 정보를 받아와서 카드 버튼으로 보여준다.
		public void updatePanel(Player player) {
			//20장이 넘었을 경우는 호출되지 않는다.
			if (player.getCount() > 20)
				return;
			//이전 카드를 모두 제거하고 다시 만든다.
			removeAll();
			for (int i = 0; i < player.getCount(); i++) {
				cards[i] = new CardViewer();
				cards[i].setBounds(0, 20*i, Card.WIDTH, Card.HEIGHT);
				add(cards[i]);
				if (i == 19)
					break;
			}
			repaint();
		}
		
	}
	class EastPanel extends JPanel {
		
		private CardViewer[] cards = null; //카드 버튼들
		//생성자
		public EastPanel() {
			setBounds(600, 100, 100, 500);
			setBackground(new Color(0, 200, 0));
			setLayout(null);
			
			cards = new CardViewer[20];
		}
		//카드 정보를 받아와서 카드 버튼으로 보여준다.
		public void updatePanel(Player player) {
			//20장이 넘었을 경우에는 호출되지 않는다.
			if (player.getCount() > 20)
				return;
			//이전에 있던 카드 버튼들을 모두 제거한다.
			removeAll();
			for (int i = 0; i < player.getCount(); i++) {
				cards[i] = new CardViewer();
				cards[i].setBounds(0, 500-Card.HEIGHT-20*i, Card.WIDTH, Card.HEIGHT);
				add(cards[i]);
				if (i == 19)
					break;
			}
			repaint();
		}
		
	}
	//플레이어의 패널
	class SouthPanel extends JPanel {
		
		private CardViewer[] cards = null; //카드 버튼들
		private CardListener[] listener = null; //카드 버튼들의 액션 리스너들
		private int[] xPos = null; // 현재 놓일 카드의 위치
		private int yPos = 40; // 현재 놓일 카드의 위치
		//생성자
		public SouthPanel() {
			setBounds(100, 600, 500, 140);
			setBackground(new Color(0, 200, 0));
			setLayout(null);
			
			cards = new CardViewer[20];
			listener = new CardListener[20];
			xPos = new int[20];
			
			for (int i = 0; i < xPos.length; i++) {
				xPos[i] = i*20;
			}
		}
		
		//카드 정보를 받아와서 패널에 보여준다.
		public void updatePanel(Player player) {
			//카드가 20장이 넘었을때는 호출되지 않는다.
			if (player.getCount() > 20)
				return;
			//이전 카드 버튼들을 모두 제거한다.
			removeAll();
			
			Card[] deck = player.getDeck();
			for (int i = 0; i < player.getCount(); i++) {
				cards[i] = new CardViewer(deck[i]);
				listener[i] = new CardListener(cards[i]);
				cards[i].addActionListener(listener[i]);
				cards[i].setBounds(xPos[player.getCount()-i-1], yPos, Card.WIDTH, Card.HEIGHT);
				add(cards[i]);
				if (i == 19)
					break;
			}
			repaint();
		}
		//유저 턴이 아닐때 액션 리스너를 넣지 않음
		public void updatePanelOfNoActionListener(Player player) {
			//20장이 넘었을때는 호출되지 않는다.
			if (player.getCount() > 20)
				return;
			//이전 카드버튼들을 모두 제거한다.
			removeAll();
			
			Card[] deck = player.getDeck();
			for (int i = 0; i < player.getCount(); i++) {
				cards[i] = new CardViewer(deck[i]);
				listener[i] = new CardListener(cards[i]);
				cards[i].setBounds(xPos[player.getCount()-i-1], yPos, Card.WIDTH, Card.HEIGHT);
				add(cards[i]);
				if (i == 19)
					break;
			}
			repaint();
		}
	}
}
