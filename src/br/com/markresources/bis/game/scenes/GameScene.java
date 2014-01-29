package br.com.markresources.bis.game.scenes;

import java.util.ArrayList;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;

import br.com.markresources.bis.R;
import br.com.markresources.bis.game.control.GameButtons;
import br.com.markresources.bis.game.engines.CollisionEngine;
import br.com.markresources.bis.game.engines.MeteorsEngine;
import br.com.markresources.bis.game.engines.ShootEngine;
import br.com.markresources.bis.game.interfaces.PauseDelegate;
import br.com.markresources.bis.game.model.Player;
import br.com.markresources.bis.game.model.Score;
import br.com.markresources.bis.sprite.ScreenBackground;
import br.com.markresources.bis.util.Assets;
import br.com.markresources.bis.util.DeviceSettings;
import br.com.markresources.bis.util.MunitionFactory;
import br.com.markresources.bis.util.Runner;
import br.com.markresources.bis.util.SoundUtil;

public class GameScene extends CCLayer implements PauseDelegate {
	
	private int shootValue = 1;

	// POSSUIRÁ UM BACKGROUND
	private ScreenBackground background;

	// responsável pela criação dos meteoros
	private MeteorsEngine meteorsEngine; //responsável por gerar os meteoros
	private ShootEngine shootEngine; //responável por gerar os tiros
	private CollisionEngine collisionEngine; //reponsável por verificar as colisões

	private CCLayer meteorsLayer; // camada dos metoros
	private CCLayer playerLayer; // camada do player
	private CCLayer shootsLayer; // camada dos tiros
	private CCLayer scoreLayer; //camada do score
	private CCLayer layerTop; //camada do pause
	
	private PauseScreen pauseScreen;
	
	private Score score;

	@SuppressWarnings("rawtypes")
	private ArrayList shootsArray; // array para checagem de colisoes
	@SuppressWarnings("rawtypes")
	private ArrayList playersArray;
	@SuppressWarnings("rawtypes")
	private ArrayList meteorsArray;

	private Player player; // jogador

	private GameScene() {
		// instanciar o background para essa tela.
		this.background = new ScreenBackground(Assets.BACKGROUND);
		this.background.setPosition(CGPoint.ccp(
				DeviceSettings.screenWidth() / 2,
				DeviceSettings.screenHeight() / 2));

		this.addChild(background);

		// inicia o player
		this.player = Player.iniciarPlayer();
		this.player.catchAccelerometer(); //inicia o acelerometro

		// iniciando as camadas
		this.meteorsLayer = CCLayer.node();
		this.playerLayer = CCLayer.node();
		this.shootsLayer = CCLayer.node();
		this.scoreLayer = CCLayer.node();
		this.layerTop = CCLayer.node();

		// inicia os buttons para controle do player
		GameButtons gameButtonsLayer = GameButtons.gameButtons();
		gameButtonsLayer.setDelegate(this);

		// adiciona na tela principal a nova camada.
		this.addChild(this.meteorsLayer);
		this.addChild(this.playerLayer);
		this.addChild(this.shootsLayer);
		this.addChild(this.scoreLayer);
		this.addChild(gameButtonsLayer);
		this.addChild(this.layerTop);

		this.setIsTouchEnabled(true);

		SoundUtil.playSound(R.raw.music, true); //musica de fundo
		
		this.preloadCache(); //colocará em cache os sons, para evitar demora.
		this.addGameObjects();
	}

	/**
	 * Metodo utilizado para criar a scena principal do game.
	 * 
	 * @return
	 */
	public static CCScene createGame() {
		CCScene scene = CCScene.node();
		GameScene camada = new GameScene();
		scene.addChild(camada);
		return scene;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addGameObjects() {
		this.meteorsArray = new ArrayList();
		this.shootsArray = new ArrayList();
		this.playersArray = new ArrayList();
		this.score = new Score(this);
		
		// inicia as engines
		this.meteorsEngine = new MeteorsEngine(meteorsLayer, meteorsArray);
		this.shootEngine = new ShootEngine(shootsLayer, shootsArray);
		this.collisionEngine = new CollisionEngine(this);
		
		this.player.setDelegate(shootEngine); //quem vai implementar o disparo do delegate será a gameScene

		this.playersArray.add(this.player);
		
		this.playerLayer.addChild(player);
		this.scoreLayer.addChild(getScore());
	}
	
	public void removePlayer() {
		this.playersArray.remove(this.player);
	}
	
	public void startFinalScreen() {
	    CCDirector.sharedDirector().replaceScene(new FinalScreen().scene());
	}

	@Override
	public void onEnter() {
		super.onEnter();
		
		// Inicia o status do jogo
		Runner.setGamePlaying(true);
//	    Runner.setGamePaused(false);
		
		// pause
//	    SoundEngine.sharedEngine().setEffectsVolume(1f);
//	    SoundEngine.sharedEngine().setSoundVolume(1f);
	    
	    // efetua o agendamento das verificacoes das colisoes.
		this.schedule("checkHits");
		this.startEngines();
	}

	private void startEngines() {
		this.addChild(this.meteorsEngine);
		this.addChild(this.shootEngine);
	}

	@SuppressWarnings("unchecked")
	public void checkHits(float dt) {
		collisionEngine.checkRadiusHitsOfArray(this.meteorsArray, this.shootsArray, "meteoroHit");
		collisionEngine.checkRadiusHitsOfArray(this.meteorsArray, this.playersArray, "playerHit");
	}
	
	public void shoot() {
		player.shoot(MunitionFactory.instanceOfShoot(shootValue));
	}

	public void moveLeft() {
		player.moveLeft();
	}

	public void moveRight() {
		player.moveRight();
	}
	
	private void preloadCache() {
		SoundUtil.playEffect(R.raw.bang);
		SoundUtil.playEffect(R.raw.shoot);
		SoundUtil.playEffect(R.raw.over);
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	@Override
	public void resumeGame() {
		if (!Runner.check().isGamePlaying()) {
			// Resume game
			this.pauseScreen = null;
			Runner.setGamePlaying(true);
			this.setIsTouchEnabled(true);
		}
	}

	@Override
	public void quitGame() {
		SoundEngine.sharedEngine().setEffectsVolume(0f);
        SoundEngine.sharedEngine().setSoundVolume(0f);
        CCDirector.sharedDirector().replaceScene(new TitleScreen().scene());		
	}

	@Override
	public void pauseGameAndShowLayer() {
		if (Runner.check().isGamePlaying()) {
			Runner.setGamePlaying(false);
		}

		if (!Runner.check().isGamePlaying() && this.pauseScreen == null) {
			this.pauseScreen = new PauseScreen();
			this.layerTop.addChild(this.pauseScreen);
			this.pauseScreen.setDelegate(this);
		}
	}

}