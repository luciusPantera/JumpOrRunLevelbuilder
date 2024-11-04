package JumpOrRunLevelbuilder.Inputs;

import java.awt.*;
import java.awt.event.*;

import JumpOrRunLevelbuilder.Main.*;
import JumpOrRunLevelbuilder.Utils.*;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener{

    private GamePanel panel;
    private Game game;

    public Mouse(GamePanel gamePanel){
        this.panel = gamePanel;
        this.game = gamePanel.getGame();
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        int x = e.getX() /(int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE);
        int y = e.getY() /(int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE);
        if(y < Game.heightInTiles){game.changeTile(x, y);game.setHighlight(x, y);}
        else if(x < 25 && x > 0 && y > Game.heightInTiles && y < Game.heightInTiles + 3){game.selectTile(x, y);game.setHighlight(x, y);}
        else if(x < 16 && x > 12 && y == Game.heightInTiles + 3){game.selectEntity(x);game.setHighlight(x, y);}
        else if(x < 13 && x > 9 && y == Game.heightInTiles + 3){game.selectInteract(x);game.setHighlight(x, y);}
        else if(x == 1 && y == Game.heightInTiles + 4) game.export();
        else if(x == Game.widthInTiles - 2 && y == Game.heightInTiles + 4) game.abort();
    }

    public void mouseMoved(MouseEvent e) {
        int x = e.getX() /(int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE);
        int y = e.getY() /(int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE);
        if(y < Game.heightInTiles)game.setHighlight(x, y);
        else if(x < 25 && x > 0 && y > Game.heightInTiles && y < Game.heightInTiles + 3)game.setHighlight(x, y);
        else if(x < 16 && x > 9 && y == Game.heightInTiles + 3)game.setHighlight(x, y);
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX() /(int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE);
        int y = e.getY() /(int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE);
        if(y < Game.heightInTiles)game.changeTile(x, y);
        else if(x < 25 && x > 0 && y > Game.heightInTiles && y < Game.heightInTiles + 3)game.selectTile(x, y);
        else if(x < 16 && x > 12 && y == Game.heightInTiles + 3)game.selectEntity(x);
        else if(x < 13 && x > 9 && y == Game.heightInTiles + 3)game.selectInteract(x);
        else if(x == 1 && y == Game.heightInTiles + 4) game.export();
        else if(x == Game.widthInTiles - 2 && y == Game.heightInTiles + 4) game.abort();
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    
}
