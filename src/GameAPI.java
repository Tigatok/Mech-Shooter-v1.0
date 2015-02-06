/**
 * Created by Tyler on 2/3/2015.
 */
public class GameAPI {
    public static GameState gameState;
    public static Mech mech;
    public static final int BOARD_WIDTH = 400; // change this value to non hardcoded
    public static final int BOARD_WIDTH_LEFT = 0;
    public static final int BOARD_HEIGHT_TOP = 0;
    public static final int BOARD_HEIGHT_BOTTOM = 400;


    public GameAPI(){

    }

    public static Mech getMech(){
        if(mech == null){
            mech = new Mech();
        }
        return mech;
    }
}
