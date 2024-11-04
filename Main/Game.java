package JumpOrRunLevelbuilder.Main;

import java.awt.*;

import JumpOrRunLevelbuilder.Levels.*;
import JumpOrRunLevelbuilder.Utils.*;

public class Game implements Runnable{

    public final static int tileSize = 32;
    public final static float scale = Config.Game.SCALE;
    public final static int scaledTileSize = (int) (tileSize * scale);
    public static final int fpsSet = 120, upsSet = 200;
    public static int widthInTiles;
    public static int heightInTiles;
    public static int GameWidth;
    public static int GameHeight;

    private GamePanel panel;
    private Window window;
    private Thread gameThread;
    private int frames = 0, updates = 0;
    private long lastSecond;
    private LevelHandler level;
    private int x, y;
    private int selX, selY;

    public Game(){
        setGameSize(50, 25);
        level = new LevelHandler(this);
        panel = new GamePanel(this);
        window = new Window(panel);
        panel.requestFocus();

        startLoop();
    }

    public void startLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void restartLoop(){
        gameThread.start();
    }

    public void run(){

        double tpf = 1000000000 / fpsSet;
        double tpu = 1000000000 / upsSet;
        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();

        while(true){

            if(System.nanoTime()-lastFrame >= tpf){
                panel.repaint();
                lastFrame += tpf;
                frames++;
            }

            if(System.nanoTime()-lastUpdate >= tpu){
                update();
                lastUpdate += tpu;
                updates++;
            }
            
            if(System.currentTimeMillis()-lastSecond >= 1000){
                lastSecond = System.currentTimeMillis();
                //System.out.println("Current FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }

    }

    public void update(){
        
    }

    public void render(Graphics g){
        level.render(g);
        g.setColor(Color.RED);
        g.drawRect(x * (int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE), y * (int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE));
        g.drawRect(selX * (int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE), selY * (int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE));
        g.fillRect((Game.widthInTiles - 2) * (int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE), (Game.heightInTiles + 4) * (int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE));
        g.setColor(Color.GREEN);
        g.fillRect(1 * (int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE), (Game.heightInTiles + 4) * (int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE));
    }

    public void setGameSize(int width, int height){
        widthInTiles = width;
        heightInTiles = height;
        GameWidth = widthInTiles * scaledTileSize;
        GameHeight = heightInTiles * scaledTileSize;
    }

    public void setHighlight(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void changeTile(int x, int y){
        level.changeTile(x,y);
    }

    public void selectTile(int x, int y){
        level.select(x - 1, y - heightInTiles - 1);
        selX = x;
        selY = y;
    }

    public void selectEntity(int x){
        level.selectEntity(x - 11);
        selX = x;
        selY = heightInTiles + 3;
    }

    public void selectInteract(int x){
        level.selectInteract(x - 9);
        selX = x;
        selY = heightInTiles + 3;
    }

    public void export(){
        level.export();
    }

    public void abort(){
        level.getLevel(1);
    }

}