package JumpOrRunLevelbuilder.Main;

import java.awt.*;
import java.awt.image.*;
import java.io.InputStream;
import java.io.IOException;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.*;
import java.util.*;

import JumpOrRunLevelbuilder.Inputs.*;
import JumpOrRunLevelbuilder.Utils.*;


public class GamePanel extends JPanel{

    private Mouse mouse;
    private Game game;

    public GamePanel(Game game){

        this.game = game;
        
        mouse = new Mouse(this);
        
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        addMouseWheelListener(mouse);

        setPanelSize();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
        //Elements
        //repaint();
    }

    private void setPanelSize(){
        Dimension size = new Dimension(Game.GameWidth, Game.GameHeight + (int)(6 * Constants.GAME.TILE_SIZE * Config.Game.SCALE));
        setPreferredSize(size);
    }

    public Game getGame(){
        return game;
    }
}