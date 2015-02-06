import javax.swing.*;

public class MechDriver extends JFrame {

    public MechDriver() {

        new Game(); //Creates the mech and starts listening for the game to start.
        add(new Board());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setTitle("Mech Shooter V1.0");
        setResizable(true);
        setVisible(true);
//        new Bug(100, 120, 1, 40, 1, 20, 20, 20, 20);
//        new Bug(180, 200, 1, 40, 1, 20, 20, 20, 20);
//        new Bug(70, 150, 1, 40, 1, 20, 20, 20, 20);
//        new Bug(200, 50, 1, 40, 1, 20, 20, 20, 20);
//        new Bug(300, 80, 1, 40, 1, 20, 20, 20, 20);
    }

    public static void main(String[] args) {
        new MechDriver();
    }
}