package Game;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import Data.*;

//���� ���
public class GameViewer extends JPanel {
	
	private SouthPanel south = null; //�÷��̾��� ī�尡 �������� �г�
	private WestPanel west = null; //�÷��̾��� ī�尡 �������� �г�
	private EastPanel east = null; //�÷��̾��� ī�尡 �������� �г�
	private NorthPanel north = null; //�÷��̾��� ī�尡 �������� �г�
	private JFrame frm = null; //���� ������
	private JButton card_floor = null; //�ٴڿ� ��� ī�带 �����ֱ� ���� ��ư
	
	private JLabel[] nameLabel = null; //�̸���  ǥ����.
	
	private JButton deck = null; //���� ��Ÿ���� ���� ��ư
	private ActionListener deckListener = null; //���� �׼� ������ �ν��Ͻ�
	
	//-------------------------------------------------------------------------------//
	//�� Ŭ������ �� �ϳ��� �ν��Ͻ��� ������ �� �ִ�.
	private static GameViewer inst = null; 
	public static GameViewer getInstance() {
		if (inst == null)
			inst = new GameViewer();
		return inst;
	}
	//--------------------------------------------------------------------------------//
	private GameViewer() {
		frm = new JFrame("��ī��");
		frm.setBounds(0, 0, 706, 760);
		frm.setResizable(false); // ����ڰ� ���Ƿ� ũ�⸦ �������� ���ϰ� �Ѵ�.
		
		setBackground(Color.green); // ���� ����
		setLayout(null);
		
		//�÷��̾���� �г� �����.
		north = new NorthPanel();
		west = new WestPanel();
		east = new EastPanel();
		south = new SouthPanel();
		
		deckListener = new DeckListener();
		
		// �� �����
		deck = new JButton(Card.getBackIcon());
		deck.setBounds(312, 255, Card.WIDTH, Card.HEIGHT);
		add(deck);
		deck.addActionListener(deckListener);
		
		// �ٴ� ī�� �����
		card_floor = new JButton();
		card_floor.setBounds(312, 355, Card.WIDTH, Card.HEIGHT);
		add(card_floor);
		
		add(north); add(west); add(east); add(south);
		
		//�̸� �� �����
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
	
	//�̸��� �޾ƿͼ� �̸� �󺧿� �߰�
	public void setNameLabel(Player[] player) {
		for (int i = 0; i < nameLabel.length; i++) {
			nameLabel[i].setText(player[i].getName());
		}
	}
	//�ش� ���� �÷��̾��� �̸��� �׵θ� ĥ�ϱ�
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
	//�ٴڿ� ��� ī�� ����
	public void setFloorCard(Card c) {
		card_floor.setIcon(c.getIcon());
		repaint();
	}
	//�÷��̾� �гο� ��Ÿ�������� ī�� ������Ʈ
	public void updatePanel(Player[] player, int turn) {
		//south.updatePanel(player[0]);
		south.updatePanelOfNoActionListener(player[0]);
		east.updatePanel(player[1]);
		north.updatePanel(player[2]);
		west.updatePanel(player[3]);
		
		//��ǻ���� ���ϋ� ��ο� ���� ���ϰ� �ϱ� ���� ���� ����ư�� �����ϰ� ���� �����.
		remove(deck);
		deck = new JButton(Card.getBackIcon());
		deck.setBounds(312, 255, Card.WIDTH, Card.HEIGHT);
		add(deck);
		//�÷��̾��� ���϶� ��ο��Ҽ��ִ� ī�� �� ��ư�� ���� �����.
		if (turn == 0) {
			deck.addActionListener(deckListener);
			south.updatePanel(player[0]);
		}
		repaint();
	}
	//������ ���� �޼ҵ�
	public void dispose() {
		frm.dispose();
	}
	
	public void repaint() {
		//�гε��� �ϼ����� �ʾ��� ���, ȣ����� �ʴ´�.
		if (south == null || north == null || east == null || west == null)
			return;
		
		Graphics g = getGraphics();
		g.drawImage(Card.getBackIcon().getImage(), deck.getX(), deck.getY(), this);
		
		//�гε鵵 repaint �Ѵ�.
		south.repaint();
		north.repaint();
		east.repaint();
		west.repaint();
		super.repaint();
	}
	
	class NorthPanel extends JPanel {
		
		private CardViewer[] cards = null; // ī�� ��ư��
		
		//������
		public NorthPanel() {
			setBounds(100, 0, 500, 100);
			setBackground(new Color(0, 200, 0));
			setLayout(null);
			
			cards = new CardViewer[20];
		}
		
		//ī�� ������ �޾ƿͼ� ��ư���� ����� �����ش�.
		public void updatePanel(Player player) {
			//20���� �Ѿ��� ��쿡�� ȣ������ʴ´�.
			if (player.getCount() > 20)
				return;
			//���� ��ư���� ��� �����ϰ� �ٽ� �����.
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
		
		private CardViewer[] cards = null; //ī�� ��ư��
		//������
		public WestPanel() {
			setBounds(0, 100, 100, 500);
			setBackground(new Color(0, 200, 0));
			setLayout(null);
			
			cards = new CardViewer[20];
		}
		//ī�� ������ �޾ƿͼ� ī�� ��ư���� �����ش�.
		public void updatePanel(Player player) {
			//20���� �Ѿ��� ���� ȣ����� �ʴ´�.
			if (player.getCount() > 20)
				return;
			//���� ī�带 ��� �����ϰ� �ٽ� �����.
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
		
		private CardViewer[] cards = null; //ī�� ��ư��
		//������
		public EastPanel() {
			setBounds(600, 100, 100, 500);
			setBackground(new Color(0, 200, 0));
			setLayout(null);
			
			cards = new CardViewer[20];
		}
		//ī�� ������ �޾ƿͼ� ī�� ��ư���� �����ش�.
		public void updatePanel(Player player) {
			//20���� �Ѿ��� ��쿡�� ȣ����� �ʴ´�.
			if (player.getCount() > 20)
				return;
			//������ �ִ� ī�� ��ư���� ��� �����Ѵ�.
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
	//�÷��̾��� �г�
	class SouthPanel extends JPanel {
		
		private CardViewer[] cards = null; //ī�� ��ư��
		private CardListener[] listener = null; //ī�� ��ư���� �׼� �����ʵ�
		private int[] xPos = null; // ���� ���� ī���� ��ġ
		private int yPos = 40; // ���� ���� ī���� ��ġ
		//������
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
		
		//ī�� ������ �޾ƿͼ� �гο� �����ش�.
		public void updatePanel(Player player) {
			//ī�尡 20���� �Ѿ������� ȣ����� �ʴ´�.
			if (player.getCount() > 20)
				return;
			//���� ī�� ��ư���� ��� �����Ѵ�.
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
		//���� ���� �ƴҶ� �׼� �����ʸ� ���� ����
		public void updatePanelOfNoActionListener(Player player) {
			//20���� �Ѿ������� ȣ����� �ʴ´�.
			if (player.getCount() > 20)
				return;
			//���� ī���ư���� ��� �����Ѵ�.
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
