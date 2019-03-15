package Game;

import javax.swing.*;

import Game.Controller.DifficultyListener;

import java.awt.*;

//�о� ���̴� Ŭ����
public class Reader {
	private Controller controller = null;
	private Printer out = null; //�޼��� ����ϴ� �ν��Ͻ�
	
	//������
	public Reader(Controller controller) {
		this.controller = controller;
		out = new Printer();
	}
	
	//���̵��� �о� ���̴� �޼ҵ�
	public void readDifficulty() {
		Thread thread = new Thread(new ReadDifficulty());
		thread.start();
		controller.suspend();
	}
	//������ 7�� ������ �ߵ��ϴ� �޼ҵ�
	public int readSeven() {
		return JOptionPane.showOptionDialog(null, "�����ϼ���.", "7", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new ImageIcon[] {new ImageIcon("Image\\Spade 7.png"), new ImageIcon("Image\\Club 7.png"), new ImageIcon("Image\\Heart 7.png"), new ImageIcon("Image\\Diamond 7.png")}, null);
	}
	//ó�� �����Ҷ� ��ο��ϴ� ī�� ����
	public int readNumber() {
		int number = 0;
		while (true) {
			try {
				String str = JOptionPane.showInputDialog("�������� �����Ͻðڽ��ϱ�?");
				number = new Integer(str).intValue();
				
				if (number < 3 || number > 7)
					throw new NumberFormatException();
				
				break;
			}
			catch (NumberFormatException e) {
				out.showErrorMessage("3~7������� �Է� �����մϴ�.");
			}
		}
		return number;
	}
	//�÷��̾ �¸������� ������ �޼��� ���.
	public boolean readGoOn() {
		int option = JOptionPane.showOptionDialog(null, "��ǻ�� �÷��̸� ����ؼ� ���ðڽ��ϱ�?", "�޼���", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, null, null);
		
		switch (option) {
		case JOptionPane.YES_OPTION :
			return true;
		case JOptionPane.NO_OPTION :
			return false;
		}
		return false;
	}
	
	//���̵� ���� ���
	class ReadDifficulty extends JPanel implements Runnable {
		
		private JFrame frm = null;
		
		public ReadDifficulty() {
			frm = new JFrame("���̵� ����");
			//super("���̵� ����");
			frm.setBounds(120, 120, 649, 387);
			
			setLayout(null);
			
			JPanel labelPanel = new JPanel();
			labelPanel.setBackground(Color.white);
			labelPanel.setBounds(190, 50, 280, 50);
			
			Font font = new Font("Font", Font.BOLD, 25);
			JLabel label = new JLabel();
			label.setFont(font);
			label.setText("���̵��� ������ �ּ���.");
			
			//label.setBounds(200, 50, 400, 30);
			
			DifficultyListener listener = controller.new DifficultyListener(frm);
			
			JButton btn_low = new JButton("��"); btn_low.addActionListener(listener);
			btn_low.setBounds(290, 280, 70, 30);
			//btn_low.setBackground(Color.white);
			JButton btn_mid = new JButton("��"); btn_mid.addActionListener(listener);
			btn_mid.setBounds(290, 240, 70, 30);
			//btn_mid.setBackground(Color.white);
			JButton btn_high = new JButton("��"); btn_high.addActionListener(listener);
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