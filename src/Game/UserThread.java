package Game;

import Data.*;

//������ ������
public class UserThread extends Thread implements ThreadInterface {
	private boolean suspended = false; //UserThread �Ͻ����� ����
	private boolean stopped = false; //UserThread ���� ����
	
	private User user = null; //UserThread �� ������ �ִ� �������� �ν��Ͻ�
	
	//������
	public UserThread() {

	}
	
	public void run() {
		while (!stopped) {
			if (!suspended) {
				//�÷��̾��� ��.
			}
			else {
				Thread.yield();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void suspended() {
		suspended = true;
	}
	public void resumed() {
		suspended = false;
	}
	public void stopped() {
		stopped = true;
	}

	@Override
	public Player getPlayer() {
		// TODO Auto-generated method stub
		return user;
	}
}
