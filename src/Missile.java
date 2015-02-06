import javax.swing.*;
import java.awt.*;

public class Missile {
    private final int MISSILE_SPEED = 2; // change to non constant?
    boolean visible;
    private int x, y,  width, height;
    private double dir;
    private Image image;
    private String missile = "images/missile.png";

    public Missile(int x, int y, double dir, int width, int height) {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(missile));
        image = ii.getImage();
        visible = true;
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.height = height;
        this.width = width;
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

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void move() {
        System.out.println(":" + x);
        if (!visible) {
        } else {
            if (!collision()) {
                if (!(x >= GameAPI.BOARD_WIDTH) && !(x <= GameAPI.BOARD_WIDTH_LEFT)) {
                    if (dir == 0) { //RIGHT
                        x += MISSILE_SPEED;
                    } else if (dir == 1) { //LEFT
                        x -= MISSILE_SPEED;
                    } else if ( dir == 2){ //TOP RIGHT
                        x += MISSILE_SPEED;
                        y += MISSILE_SPEED;
                    } else if ( dir == 3){ //BOT RIGHT
                        x+= MISSILE_SPEED;
                        y-= MISSILE_SPEED;
                    } else if (dir == 4){ // TOP LEFT
                        x -= MISSILE_SPEED;
                        y -= MISSILE_SPEED;
                    } else if (dir == 5){ //BOT LEFT
                        x -= MISSILE_SPEED;
                        y += MISSILE_SPEED;
                    }
                } else {
                    x = MISSILE_SPEED;
                    visible = false;
                    image = null;
                    x = y = 0;
                    dir = 0;
                    missile = null;
                }
            } else {
                System.out.println("Pew");
                this.destroy();
            }
        }

    }

    //Destroy basically means hit the bug
    private void destroy(Bug bug) {

        System.out.println("Bug arrayList: " + Bug.bugArrayList.size());
        System.out.println(Bug.bugArrayList.size());

        bug.setHealth(bug.getHealth() - 20);
        if(bug.getHealth() <= 0) {
            Bug.bugArrayList.remove(bug);
            this.image = null;
            this.visible = false;
            Mech.mechHashMap.get(0).setScore(Mech.mechHashMap.get(0).getScore()+20);
        }
    }

    private void destroy(){
        this.image = null;
        this.visible = false;
    }

    public boolean collision() {
        for(Bug bug : Bug.bugArrayList){
            if(this.getBounds().intersects(bug.getBounds())){
                this.destroy(bug);
                return true;
            }
        }
        return false;
    }


    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

}