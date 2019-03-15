package Game;

import Data.*;

//플레이어 쓰레드 인터페이스, 구현 : ComputerThread, UserThread
public interface ThreadInterface {
	public void stopped();
	public void suspended();
	public void resumed();
	public void start();
	public Player getPlayer();
}
