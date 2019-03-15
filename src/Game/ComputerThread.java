package Game;

import Data.*;

//��ǻ���� �Ͽ� ���ư��� ������
public class ComputerThread extends Thread implements ThreadInterface {
	private Controller controller = null;
	private boolean stopped = false; //ComputerThread ������ ���� ����
	private boolean suspended = false; //ComputerThread ������ �Ͻ����� ����
	
	private Computer computer = null; //�ش� ComputerThread ������ ��ǻ��
	
	public ComputerThread(Player com, Controller controller) {
		computer = (Computer)com;
		this.controller = controller;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!stopped) {
			try {
				if (!suspended) {
					//��ǻ���� ��
					Thread.sleep(1000);
					
					int next_count = controller.getNextCount();
					int stack = controller.getStack();
					String next_suit = controller.getNextSuit();
					
					controller.putCard(computer.start(next_suit, next_count, stack));
				}
				else {
					Thread.yield();
				}
			}
			catch (InterruptedException e) {  }
		}
	}
	//�ش� ������ �Ͻ�����
	public void suspended() {
		suspended = true;
	}
	//�ش� ������ ���
	public void resumed() {
		suspended = false;
	}
	//�ش� ������ ����
	public void stopped() {
		stopped = true;
	}

	@Override
	public Player getPlayer() {
		// TODO Auto-generated method stub
		return computer;
	}
}
