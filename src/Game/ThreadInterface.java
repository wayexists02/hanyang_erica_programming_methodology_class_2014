package Game;

import Data.*;

//�÷��̾� ������ �������̽�, ���� : ComputerThread, UserThread
public interface ThreadInterface {
	public void stopped();
	public void suspended();
	public void resumed();
	public void start();
	public Player getPlayer();
}
