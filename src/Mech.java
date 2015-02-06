import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class Mech {
    public static HashMap<Integer, Mech> mechHashMap = new HashMap<Integer, Mech>();
    public static int count = 0;
    private static int dir;
    private final int CRAFT_SIZE = 20;
    public int x;
    public int y;
    private String mech = "images/mech_still_right.png";
    private int dx;
    private int dy;
    private int width;
    public enum Weapons{MISSILE, SHOTGUN}
    public Weapons weaponSelected;
    public ArrayList<Weapons> mechWeapons = new ArrayList<Weapons>();

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    private int lives;
    private int height;
    private double score;
    private Image image;
    private ArrayList<Missile> missiles;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Mech() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(mech));
        mechHashMap.put(count++, this);
        width = ii.getIconWidth();
        height = ii.getIconHeight();
        weaponSelected = Weapons.MISSILE;
        System.out.println(ii.getIconWidth());
        System.out.println(ii.getIconHeight());
        image = ii.getImage();
        score = 0;
        lives = 3;
        missiles = new ArrayList<Missile>();
        x = 40;
        y = 60;

    }

    public static int getDir() {
        return dir;
    }

    public void setDir(int x) {
        Mech.dir = x;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void move() {
        x += dx;
        y += dy;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public ArrayList<Missile> getMissiles() {
        return missiles;
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            fire();
        }
        if (key == KeyEvent.VK_Q){
            //If the weapon is the last in the list.
            if(mechWeapons.indexOf(this.weaponSelected) == mechWeapons.size()) {
                this.weaponSelected = mechWeapons.get(0);
            }
            this.weaponSelected = mechWeapons.get(mechWeapons.indexOf(this.weaponSelected) + 1);
            System.out.println("weapon selected: "+ this.weaponSelected);
        }
        if (!collision()) {
            if ((!(x >= GameAPI.BOARD_WIDTH) && !(x <= GameAPI.BOARD_WIDTH_LEFT))) {
                if (key == KeyEvent.VK_LEFT) {
                    setDir(1);
                    mech = "images/mech_left.png";
                    ImageIcon ii = new ImageIcon(this.getClass().getResource(mech));
                    image = ii.getImage();
                    dx = -1;
                }
                if (key == KeyEvent.VK_RIGHT) {
                    setDir(0);
                    mech = "images/mech_right.png";
                    ImageIcon ii = new ImageIcon(this.getClass().getResource(mech));
                    image = ii.getImage();
                    dx = 1;
                }
                if (key == KeyEvent.VK_UP) {
                    mech = "images/mech_up.png";
                    ImageIcon ii = new ImageIcon(this.getClass().getResource(mech));
                    image = ii.getImage();

                    dy = -1;
                }
                if (key == KeyEvent.VK_DOWN) {
                    mech = "images/mech_down.png";
                    ImageIcon ii = new ImageIcon(this.getClass().getResource(mech));
                    image = ii.getImage();
                    dy = 1;
                }
            }
        } else   if(collision()) {
            System.out.print(("asd"));
            damage();
        }
    }

    public void damage(){
        if (this.lives <= 0){
            this.x = 20;
            this.y = 20;
            this.score = 0;
            System.out.print("DAMAGE");
            //new GameOver();
        }
        this.x = this.x -50;
        this.y = this.y -50;
        System.out.println("ASDASD");
        setLives(getLives() - 1);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            setDir(1);
            mech = "images/mech_still_left.png";
            ImageIcon ii = new ImageIcon(this.getClass().getResource(mech));
            image = ii.getImage();

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            setDir(0);
            mech = "images/mech_still.png";
            ImageIcon ii = new ImageIcon(this.getClass().getResource(mech));
            image = ii.getImage();

            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            mech = "images/mech_still.png";
            ImageIcon ii = new ImageIcon(this.getClass().getResource(mech));
            image = ii.getImage();

            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            mech = "images/mech_still.png";
            ImageIcon ii = new ImageIcon(this.getClass().getResource(mech));
            image = ii.getImage();
            dy = 0;
        }
    }

    public boolean collision() {
        if (Bug.bugArrayList.size() > 0) {
            System.out.println(Bug.bugArrayList.get(0).getBounds());
            System.out.println(this.getBounds());
            return Bug.bugArrayList.get(0).getBounds().intersects(this.getBounds());
        }
        else return false;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    public void kill(){
        if(!(this.lives <= 0)) {
            this.score = 0;
            this.lives = getLives() - 1;
            this.x = 40;
            this.y = 40;
            System.out.println("kill");
        } else {
            System.out.println("You lose!");
        }
    }

    public void fire() {
        if (weaponSelected == Weapons.MISSILE) {
            if (getDir() == 0)
                missiles.add(new Missile(x + CRAFT_SIZE, y + CRAFT_SIZE / 2, 0, 5, 5));
            else if (getDir() == 1)
                missiles.add(new Missile(x + CRAFT_SIZE, y + CRAFT_SIZE / 2, 1, 5, 5));
        } else if (weaponSelected == Weapons.SHOTGUN){
            if(getDir() == 0){ //RIGHT
                missiles.add(new Missile(x + CRAFT_SIZE, y + CRAFT_SIZE, 0, 5, 5));
                missiles.add(new Missile(x + CRAFT_SIZE, y + CRAFT_SIZE, 2, 5, 5));
                missiles.add(new Missile(x + CRAFT_SIZE, y + CRAFT_SIZE, 3, 5, 5));
            } else if (getDir() == 1){
                missiles.add(new Missile(x + CRAFT_SIZE, y + CRAFT_SIZE, 4, 5, 5));
                missiles.add(new Missile(x + CRAFT_SIZE, y + CRAFT_SIZE, 1, 5, 5));
                missiles.add(new Missile(x + CRAFT_SIZE, y + CRAFT_SIZE, 5, 5, 5));
            }
        }
    }
    public void updateWeapons(){
        if(this.score <= 150){
            this.mechWeapons.add(Weapons.MISSILE);
            this.weaponSelected = Weapons.MISSILE;
        }
        if(this.score <= 500){
            this.mechWeapons.add(Weapons.SHOTGUN);
        }
    }
}
