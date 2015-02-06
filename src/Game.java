import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tyler on 2/3/2015.
 */
public class Game extends Thread{
    public static boolean running;
    public static GameState gameState;
    public static Mech player;
    public static int bugCount;


    public Game(){
        System.out.println("Initializing new Game");
        gameState = GameState.LOADING;
        player = GameAPI.getMech();
        bugCount = 0;
        running = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while(running) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            if(gameState == GameState.LOADING){
////                new MenuScreen();
//            }
            while (gameState == GameState.PLAYING) {
                if (Mech.mechHashMap.get(0).getLives() != 0) {
                    System.out.print("test");
                    gameState = GameState.PLAYING;
                    if (Bug.areBugsDead()) {
                        System.out.println("Bugs are dead");
                        if(Mech.mechHashMap.get(0).getScore() == 0){
                            spawnBug();
                        }
                        for (int i = 0; i < Mech.mechHashMap.get(0).getScore() / 10; i++) {
                            spawnBug();
                        }
                    }
                    Mech.mechHashMap.get(0).updateWeapons();
                } //If mech is alive.
//                else {
//                    gameState = GameState.GAMEOVER;
//                }// Game Over
            }
//            if(gameState == GameState.GAMEOVER){
//
//            }
        }
    }

    public void spawnBug(){
        Random random= new Random();
        int x= random.nextInt(400 - 100) + 100;
        int y = random.nextInt(400 - 100) + 100;
        new Bug(x, y, 1, 80, 1, 80, 20, 20, 20);
    }
}
