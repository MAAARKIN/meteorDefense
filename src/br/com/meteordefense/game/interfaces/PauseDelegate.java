package br.com.meteordefense.game.interfaces;

public interface PauseDelegate {
	public void resumeGame();
	public void quitGame();
	public void pauseGameAndShowLayer();
}
