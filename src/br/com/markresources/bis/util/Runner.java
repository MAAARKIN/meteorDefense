package br.com.markresources.bis.util;

/**
 * Classe responsável por efetuar o controle do game, para saber se o game encontrasse pausado ou não.
 * 
 * @author Marquinhos
 *
 */
public class Runner {
	
	private static boolean isGamePlaying;
//    private static boolean isGamePaused;
    
    private static Runner runner = null;
    
    private Runner() {
    	
	}
    
    public static Runner check() {
    	if (runner == null){
    		runner = new Runner();
    	}
    	return runner;
    }

//	public static boolean isGamePaused() {
//		return isGamePaused;
//	}
//
//	public static void setGamePaused(boolean isGamePaused) {
//		Runner.isGamePaused = isGamePaused;
//	}

	public static boolean isGamePlaying() {
		return isGamePlaying;
	}

	public static void setGamePlaying(boolean isGamePlaying) {
		Runner.isGamePlaying = isGamePlaying;
	}
    
}