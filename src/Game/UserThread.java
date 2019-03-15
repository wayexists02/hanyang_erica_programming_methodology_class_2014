package Game;

import Data.*;

//유저의 쓰레드
public class UserThread extends Thread implements ThreadInterface {
	private boolean suspended = false; //UserThread 일시정지 여부
	private boolean stopped = false; //UserThread 종료 여부
	
	private User user = null; //UserThread 가 가지고 있는 유저정보 인스턴스
	
	//생성자
	public UserThread() {

	}
	
	public void run() {
		while (!stopped) {
			if (!suspended) {
				//플레이어의 턴.
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
