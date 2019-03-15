package Game;

import Data.*;

//컴퓨터의 턴에 돌아가는 쓰래드
public class ComputerThread extends Thread implements ThreadInterface {
	private Controller controller = null;
	private boolean stopped = false; //ComputerThread 쓰레드 종료 여부
	private boolean suspended = false; //ComputerThread 쓰레드 일시정지 여부
	
	private Computer computer = null; //해당 ComputerThread 쓰레드 컴퓨터
	
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
					//컴퓨터의 턴
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
	//해당 쓰레드 일시정지
	public void suspended() {
		suspended = true;
	}
	//해당 쓰레드 재생
	public void resumed() {
		suspended = false;
	}
	//해당 쓰레드 종료
	public void stopped() {
		stopped = true;
	}

	@Override
	public Player getPlayer() {
		// TODO Auto-generated method stub
		return computer;
	}
}
