package game;

public class Launcher {

	public static final float FPS = 60.0F;
	
	public static void main(String[] args) {
		Game game = new Game("Key Game");

		game.init();

		Thread gameUpdateThread = new Thread(){

			private long lastTime;
			
			public void run(){

				System.out.println("Game Thread starting!");

				final float timerResolution = 1000F;
				lastTime = System.currentTimeMillis();
				
				while(game.canRun()){
					long currentTime = System.currentTimeMillis();
					float delta = (currentTime - lastTime) / timerResolution;
					System.out.println(delta);
					lastTime = currentTime;
					try {
						Thread.sleep((long) (1000 / FPS));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				System.out.println("Game Thread closing!");
				
			}
		};

		gameUpdateThread.run();

	}
}
