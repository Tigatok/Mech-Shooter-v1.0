import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

    private Timer tick;
    public Mech player;
    public int highlightedString;

    public Board() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        player = Game.player;

        highlightedString = 0;

        tick = new Timer(5, this);
        tick.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        if(Game.gameState == GameState.LOADING){
            if(highlightedString == 0){
                g.setColor(Color.RED);
                g.drawString("Start Game", 100, 80);
                g.setColor(Color.GREEN);
                g.drawString("Continue Game", 100, 100);
                g.drawString("Options", 100, 120);
            } else if (highlightedString == 1){
                g.setColor(Color.GREEN);
                g.drawString("Start Game", 100, 80);
                g.setColor(Color.RED);
                g.drawString("Continue Game", 100, 100);
                g.setColor(Color.GREEN);
                g.drawString("Options", 100, 120);
            }
            else if (highlightedString == 2){
                g.setColor(Color.GREEN);
                g.drawString("Start Game", 100, 80);
                g.setColor(Color.GREEN);
                g.drawString("Continue Game", 100, 100);
                g.setColor(Color.RED);
                g.drawString("Options", 100, 120);
            }
        }
        if(Game.gameState == GameState.PLAYING) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
            ArrayList ms = player.getMissiles();

            g.drawString("Bug Count: " + Bug.bugArrayList.size(), 270, 30);
            g.drawString("Player Score: " + Mech.mechHashMap.get(0).getScore(), 270, 60);
            g.drawString("Player Lives: " + Mech.mechHashMap.get(0).getLives(), 270, 90);
            g.drawString("Weapon Selected: " + Mech.mechHashMap.get(0).weaponSelected, 270, 120);

            for (int i = 0; i < ms.size(); i++) {
                Missile m = (Missile) ms.get(i);
                g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
            for (Bug bug : Bug.bugArrayList) {
                g2d.drawImage(bug.image, bug.x, bug.y, this);
                System.out.println("draw bug");
                System.out.println("Bug Count;" + Bug.bugArrayList.size());
                bug.move();
            }
        }
        else if (Game.gameState == GameState.GAMEOVER){
            g.drawString("GAME OVER!", 100, 100);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        if(Game.running) {
            if (player != null) {
                ArrayList ms = player.getMissiles();

                for (int i = 0; i < ms.size(); i++) {
                    Missile m = (Missile) ms.get(i);
                    if (m.isVisible())
                        m.move();
                    else
                        ms.remove(i);
                }
                player.move();
                repaint();
            }
        } else {
            tick.stop();
        }
    }

    private class  TAdapter extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            if(Game.gameState == GameState.LOADING) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    highlightedString++;
                    if(highlightedString > 2){
                        highlightedString = 0;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if(highlightedString == 0) {
                        Game.gameState = GameState.PLAYING;
                        return;
                    } else if (highlightedString == 1){
                        //do cvs load/read
                    } else if (highlightedString ==2 ){
                        //open option
                    }
                }
            }
            player.keyPressed(e);
        }
    }
}