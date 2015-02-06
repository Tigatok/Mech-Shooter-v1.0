import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tyler on 1/30/2015.
 */
public class Bug {

    public static int bugID = 0;
    public static ArrayList<Bug> bugArrayList = new ArrayList<Bug>();
    public int health;
    public int speed;
    public int damage;
    public int width, height;
    public double value;
    public Image image;
    public int x, y, dir;
    public boolean visible;
    public Bug(int x, int y, int dir, int health, int speed, int damage, double value, int width, int height) {

        this.x = x;
        this.y = y;
        this.dir = dir;
        this.health = health;
        this.speed = speed;
        this.damage = damage;
        this.value = value;

        this.visible = true;
        bugArrayList.add(this);
        bugID++;

        ImageIcon ii = new ImageIcon(this.getClass().getResource("images/bug1.jpg"));
        this.width = ii.getIconWidth();
        this.height = ii.getIconHeight();
        image = ii.getImage();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getWidth() {

        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void move() {
        healthCheck();
        //if there is not a collision, move.
        //else, move the mech back to the start;
        //This is where we will have our death method or such.
        if (!collision()) {
            Random random = new Random();
            int direction = random.nextInt(4);
            if (direction == 0) {
                if (this.y < 400) {
                    this.y++;
                }
            } else if (direction == 1) {
                if (this.x < 400) {
                    this.x++;
                }
            } else if (direction == 2) {
                if (this.y > 0) {
                    this.y--;
                }
            } else if (direction == 3) {
                if (this.x > 0) {
                    this.x--;
                }
            }
        } else {
            Mech.mechHashMap.get(0).x = 40;
            Mech.mechHashMap.get(0).y = 40;
            //Mech kill
            Mech.mechHashMap.get(0).kill();
        }
    }

    public void healthCheck() {
        if (this.health <= 0) {
            this.visible = false;
            this.image = null;
            this.x = 0;
            this.y = 0;
        }
    }

    public boolean collision() {
        System.out.println(Bug.bugArrayList.get(0).getBounds());
        System.out.println(this.getBounds());
        return Mech.mechHashMap.get(0).getBounds().intersects(this.getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    public static  boolean areBugsDead(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bugArrayList.size() == 0;
    }
}
