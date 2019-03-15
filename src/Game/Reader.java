package Game;

import javax.swing.*;

import Game.Controller.DifficultyListener;

import java.awt.*;

//읽어 들이는 클래스
public class Reader {
	private Controller controller = null;
	private Printer out = null; //메세지 출력하는 인스턴스
	
	//생성자
	public Reader(Controller controller) {
		this.controller = controller;
		out = new Printer();
	}
	
	//난이도를 읽어 들이는 메소드
	public void readDifficulty() {
		Thread thread = new Thread(new ReadDifficulty());
		thread.start();
		controller.suspend();
	}
	//유저가 7을 냈을때 발동하는 메소드
	public int readSeven() {
		return JOptionPane.showOptionDialog(null, "선택하세요.", "7", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new ImageIcon[] {new ImageIcon("Image\\Spade 7.png"), new ImageIcon("Image\\Club 7.png"), new ImageIcon("Image\\Heart 7.png"), new ImageIcon("Image\\Diamond 7.png")}, null);
	}
	//처음 시작할때 드로우하는 카드 갯수
	public int readNumber() {
		int number = 0;
		while (true) {
			try {
				String str = JOptionPane.showInputDialog("몇장으로 시작하시겠습니까?");
				number = new Integer(str).intValue();
				
				if (number < 3 || number > 7)
					throw new NumberFormatException();
				
				break;
			}
			catch (NumberFormatException e) {
				out.showErrorMessage("3~7장까지만 입력 가능합니다.");
			}
		}
		return number;
	}
	//플레이어가 승리했을때 나오는 메세지 출력.
	public boolean readGoOn() {
		int option = JOptionPane.showOptionDialog(null, "컴퓨터 플레이를 계속해서 보시겠습니까?", "메세지", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, null, null);
		
		switch (option) {
		case JOptionPane.YES_OPTION :
			return true;
		case JOptionPane.NO_OPTION :
			return false;
		}
		return false;
	}
	
	//난이도 설정 뷰어
	class ReadDifficulty extends JPanel implements Runnable {
		
		private JFrame frm = null;
		
		public ReadDifficulty() {
			frm = new JFrame("난이도 설정");
			//super("난이도 설정");
			frm.setBounds(120, 120, 649, 387);
			
			setLayout(null);
			
			JPanel labelPanel = new JPanel();
			labelPanel.setBackground(Color.white);
			labelPanel.setBounds(190, 50, 280, 50);
			
			Font font = new Font("Font", Font.BOLD, 25);
			JLabel label = new JLabel();
			label.setFont(font);
			label.setText("난이도를 설정해 주세요.");
			
			//label.setBounds(200, 50, 400, 30);
			
			DifficultyListener listener = controller.new DifficultyListener(frm);
			
			JButton btn_low = new JButton("하"); btn_low.addActionListener(listener);
			btn_low.setBounds(290, 280, 70, 30);
			//btn_low.setBackground(Color.white);
			JButton btn_mid = new JButton("중"); btn_mid.addActionListener(listener);
			btn_mid.setBounds(290, 240, 70, 30);
			//btn_mid.setBackground(Color.white);
			JButton btn_high = new JButton("상"); btn_high.addActionListener(listener);
			btn_high.setBounds(290, 200, 70, 30);
			//btn_high.setBackground(Color.white);
			
			labelPanel.add(label);
			add(labelPanel);
			add(btn_high); add(btn_mid); add(btn_low);
			
			frm.add(this);
			frm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
		public void paintComponent(Graphics g) {
			Toolkit tk = Toolkit.getDefaultToolkit();
			Image img = tk.getImage("Image\\OneCard.PNG");
			g.drawImage(img, 0, 0, this);
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			frm.setVisible(true);
			frm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
	}
}