package JumpOrRunLevelbuilder.Levels;

import JumpOrRunLevelbuilder.Main.*;
import JumpOrRunLevelbuilder.Utils.*;

import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;

public class LevelHandler {

    Game game;
    BufferedImage[] levelSprite;
    private int[][][] levelData;
    private int levelNr = 3;
    private int selectedValue;
    private int layer = 0;

    public LevelHandler(Game game){
        importSprites();
        getLevel(1);
        this.game = game;
    }

    public void importSprites(){
        BufferedImage img = Load.GetImage("outside_sprites.png");
        levelSprite = new BufferedImage[48];
        for(int j = 0; j < 4; j++){
            for(int i = 0; i < 12; i++){
                int index = 12*j + i;
                levelSprite[index] = img.getSubimage(i*32, j*32, 32, 32);
            }
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < levelData[0].length; i++){
            for(int j = 0; j < levelData[0][0].length; j++){
                g.drawImage(levelSprite[levelData[0][i][j]], (int)(j * Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)(i * Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE), null);
            }
        }
        String entity = "";
        for(int i = 0; i < levelData[0].length; i++){
            for(int j = 0; j < levelData[0][0].length; j++){
                entity = "";
                switch (levelData[1][i][j]) {
                    case 1:
                        entity = "Player";
                        break;
                    case 2:
                        entity = "Crab";
                        break;
                    case 3:
                        entity = "Mimic";
                        break;
                
                    default:
                        break;
                }
                g.drawString(entity, (int)(j * Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)((i + 1) * Constants.GAME.TILE_SIZE * Config.Game.SCALE));
            }
        }
        String interact = "";
        for(int i = 0; i < levelData[0].length; i++){
            for(int j = 0; j < levelData[0][0].length; j++){
                interact = "";
                switch (levelData[2][i][j]) {
                    case 1:
                        interact = "Spawn";
                        break;
                    case 2:
                        interact = "Goal";
                        break;
                    case 3:
                        interact = "Trap";
                        break;
                
                    default:
                        break;
                }
                g.drawString(interact, (int)(j * Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)((i + 1) * Constants.GAME.TILE_SIZE * Config.Game.SCALE));
            }
        }
        for(int i = 0; i < 24; i++){
            for(int j = 0; j < 2; j++){
                int index = i + 24*j;
                g.drawImage(levelSprite[index], (int)((i+ 1) * Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)((j + Game.heightInTiles + 1) * Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)(Constants.GAME.TILE_SIZE * Config.Game.SCALE), null);
            }
        }
        g.drawString("Spawn", (int)(10 * Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)((Game.heightInTiles + 4) * Constants.GAME.TILE_SIZE * Config.Game.SCALE));
        g.drawString("Goal", (int)(11 * Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)((Game.heightInTiles + 4) * Constants.GAME.TILE_SIZE * Config.Game.SCALE));
        g.drawString("Trap", (int)(12 * Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)((Game.heightInTiles + 4) * Constants.GAME.TILE_SIZE * Config.Game.SCALE));
        g.drawString("Crab", (int)(13 * Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)((Game.heightInTiles + 4) * Constants.GAME.TILE_SIZE * Config.Game.SCALE));
        g.drawString("Mimic", (int)(14 * Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)((Game.heightInTiles + 4) * Constants.GAME.TILE_SIZE * Config.Game.SCALE));
        g.drawString("Clear", (int)(15 * Constants.GAME.TILE_SIZE * Config.Game.SCALE), (int)((Game.heightInTiles + 4) * Constants.GAME.TILE_SIZE * Config.Game.SCALE));
    }

    public void update(){
    }

    public void getLevel(int nr){
        /* int[][][] data = null;
        try {
            data = Load.getLevel(nr);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return;
        }
        levelData = data; */
        levelData = new int[3][Game.heightInTiles][Game.widthInTiles];
        for(int j = 0; j < levelData[0].length; j++){
            for(int k = 0; k < levelData[0][0].length; k++){
                levelData[0][j][k] = 11;
            }
        }
        for(int j = 0; j < levelData[0].length; j++){
            for(int k = 0; k < levelData[0][0].length; k++){
                levelData[1][j][k] = 0;
            }
        }
        for(int j = 0; j < levelData[0].length; j++){
            for(int k = 0; k < levelData[0][0].length; k++){
                levelData[2][j][k] = 0;
            }
        }
    }

    public void changeTile(int x, int y){
        levelData[layer][y][x] = selectedValue;
        if(layer == 1 && selectedValue == 0)levelData[2][y][x] = selectedValue;
    }

    public void select(int x, int y){
        selectedValue = x + y * 24;
        layer = 0;
        System.out.println("Selected: " + selectedValue + " of " + x + " | " + y);
    }

    public void selectEntity(int x){
        selectedValue = x;
        if(selectedValue == 4) selectedValue = 0;
        layer = 1;
        System.out.println(selectedValue);
    }
    public void selectInteract(int x){
        selectedValue = x;
        layer = 2;
        System.out.println(selectedValue);
    }

    public void export(){
        String output = new String(Game.heightInTiles + " " + Game.widthInTiles + "\n \n");
        for(int j = 0; j < 3; j++){
            for(int i = 0; i < Game.heightInTiles; i++){
                for (int k = 0; k < Game.widthInTiles; k++) {
                    output += levelData[j][i][k] + " ";
                }
                output += "\n";
            }
            output += "\n";
        }
        try {
            FileWriter myWriter = new FileWriter("JumpOrRunLevelbuilder/Levels/levelX.leveldata");
            myWriter.write(output);
            myWriter.close();
            System.out.println("Successfully exported to a file.");
          } catch (IOException e) {
            e.printStackTrace();
          }
    }
}
