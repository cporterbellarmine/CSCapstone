import java.util.Arrays;
import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;

import java.util.ArrayList;

public class MazeGenerator{

    private int counter;
    private int currentStartHeight;
    private int currentStartWidth;
    private int currentEndHeight;
    private int currentEndWidth;
    private int height;
    private int width;

    public static void main(String args[]){
        MazeGenerator mazeGenerator = new MazeGenerator();
        //int[][] completedBoard = mazeGenerator.generateMazeBlock(10,10);
        //mazeGenerator.printMaze(completedBoard);

        int[][] mazeBlock = mazeGenerator.generateMazeBlock(20,20);
        int[][] constructedMaze = mazeGenerator.generatePathways(mazeBlock, mazeGenerator.getHeight(), mazeGenerator.getWidth());
        mazeGenerator.printMaze(constructedMaze);
    }

    public MazeGenerator(){
        
    }//end MazeGenerator

    public void printMaze(int[][] completedBoard){
        int arrayWidth = completedBoard[0].length;
        for(int i = 0; i < arrayWidth; i++){
            System.out.println(Arrays.toString(completedBoard[i]));
        }
    }

    public int[][] generateMazeBlock(int width, int height){
        setWidth(width);
        setHeight(height);
        int[][] board = new int[height][width];
        counter = 1;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                board[i][j] = counter + 3;
                counter++;
            }
        }
        return board;
    }//end generatePathways

    public int[][] generateInsidePathways(int[][] mazeBlock, String startDirection, int width, int height, int startHeight, int startWidth, boolean begin){
        System.out.println("generate inside pathways start...");

        Random rand = new Random();
        int randomDirectionDeterminer = rand.nextInt(3);
        String nextDirection = "null";
        boolean deadEnd = false;
        int newHeight = 0;
        int newWidth = 0;
        String newStartDirection = "null";
        boolean complete;

        try{
            //System.out.println("Random Direction Determiner " + randomDirectionDeterminer);
            //System.out.println("startDirection " + startDirection);
            if(startDirection.equals("north")){
                switch(randomDirectionDeterminer){
                    case(0):
                        nextDirection = "north";
                        break;
                    case(1):
                        nextDirection = "east";
                        break;
                    case(2):
                        nextDirection = "west";
                        break;
                }
            }else if(startDirection.equals("east")){
                switch(randomDirectionDeterminer){
                    case(0):
                        nextDirection = "north";
                        break;
                    case(1):
                        nextDirection = "east";
                        break;
                    case(2):
                        nextDirection = "south";
                        break;
                }
            }else if(startDirection.equals("south")){
                switch(randomDirectionDeterminer){
                    case(0):
                        nextDirection = "east";
                        break;
                    case(1):
                        nextDirection = "south";
                        break;
                    case(2):
                        nextDirection = "west";
                        break;
                }
            }else if(startDirection.equals("west")){
                switch(randomDirectionDeterminer){
                    case(0):
                        nextDirection = "north";
                        break;
                    case(1):
                        nextDirection = "south";
                        break;
                    case(2):
                        nextDirection = "west";
                        break;
                }
            }

            if(begin){
                if(startDirection.equals("north")){
                    if(startHeight+1 >= 0 && startHeight+1 <= height-1){
                        if(mazeBlock[startHeight+1][startWidth]!=1 && mazeBlock[startHeight+1][startWidth]!=0){
                            mazeBlock[startHeight+1][startWidth]=0;
                            newHeight = startHeight+1;
                            //System.out.println("Next direction: " + nextDirection);
                            switch(nextDirection){
                                case("north"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight+1][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("east"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startHeight+1>= 0 && startHeight+1 <= height-1 && startHeight-1 >= 0 && startHeight-1 <= height-1){
                                        mazeBlock[startHeight-1][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth+1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("west"):
                                    if(startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1 && startHeight-1 >= 0 && startHeight-1 <= height-1){
                                        mazeBlock[startHeight-1][startWidth-1] = 1;
                                        mazeBlock[startHeight+1][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                            }
                            generateInsidePathways(mazeBlock, nextDirection, width, height, newHeight, startWidth, false);
                        }else{
                            deadEnd = true;
                            System.out.println("Dead end.");
                        }
                    }else{
                        System.out.println("Index Error!");
                    }
                }else if(startDirection.equals("south")){
                    if(startHeight+1 >= 0 && startHeight-1 <= height-1){
                        if(mazeBlock[startHeight-1][startWidth]!=1&& mazeBlock[startHeight-1][startWidth]!=0){
                            mazeBlock[startHeight-1][startWidth]=0;
                            newHeight = startHeight-1;
                            //System.out.println("Next direction: " + nextDirection);
                            switch(nextDirection){
                                case("south"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight+1][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("east"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startHeight+1>= 0 && startHeight+1 <= height-1 && startHeight-1 >= 0 && startHeight-1 <= height-1){
                                        mazeBlock[startHeight-1][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth+1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("west"):
                                    if(startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1 && startHeight-1 >= 0 && startHeight-1 <= height-1){
                                        mazeBlock[startHeight-1][startWidth-1] = 1;
                                        mazeBlock[startHeight+1][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                            }
                            generateInsidePathways(mazeBlock, nextDirection, width, height, newHeight, startWidth, false);
                        }else{
                            deadEnd = true;
                            System.out.println("Dead end.");
                        }
                    }else{
                        System.out.println("Index Error!");
                    }
                }else if(nextDirection.equals("east")){
                    if(startWidth-1 >= 0 && startWidth <= width-1){
                        if(mazeBlock[startHeight][startWidth-1]!=1 && mazeBlock[startHeight][startWidth-1]!=0){
                            mazeBlock[startHeight][startWidth-1]=0;
                            newWidth = startWidth-1;
                            //System.out.println("Next direction: " + nextDirection);
                            switch(nextDirection){
                                case("north"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight+1][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("south"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight+1][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("west"):
                                    if(startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1 && startHeight-1 >= 0 && startHeight-1 <= height-1){
                                        mazeBlock[startHeight-1][startWidth-1] = 1;
                                        mazeBlock[startHeight+1][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                            }
                            generateInsidePathways(mazeBlock, nextDirection, width, height, startHeight, newWidth, false);
                        }else{
                            deadEnd = true;
                            System.out.println("Dead end.");
                        }
                    }else{
                        System.out.println("Index Error!");
                    }
                }else{
                    if(startWidth+1 >= 0 && startWidth+1 <= width-1){
                        if(mazeBlock[startHeight][startWidth+1]!=1 && mazeBlock[startHeight][startWidth+1]!=0){
                            mazeBlock[startHeight][startWidth+1]=0;
                            newWidth = startWidth+1;
                            //System.out.println("Next direction: " + nextDirection);
                            switch(nextDirection){
                                case("north"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight+1][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("east"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startHeight+1>= 0 && startHeight+1 <= height-1 && startHeight-1 >= 0 && startHeight-1 <= height-1){
                                        mazeBlock[startHeight-1][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth+1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("south"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight+1][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                            }
                            generateInsidePathways(mazeBlock, nextDirection, width, height, startHeight, newWidth, false);
                        }else{
                            deadEnd = true;
                            System.out.println("Dead end.");
                        }
                    }else{
                        System.out.println("Index Error!");
                    }
                }  
            }else{
                if(nextDirection.equals("north")){
                    if(startHeight+1 >= 0 && startHeight+1 <= height-1){
                        if(mazeBlock[startHeight+1][startWidth]!=1 && mazeBlock[startHeight+1][startWidth]!=0){
                            newStartDirection = "north";
                            mazeBlock[startHeight+1][startWidth]=0;
                            newHeight = startHeight+1;
                            //System.out.println("Next Direction" + nextDirection);
                            switch(nextDirection){
                                case("north"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight+1][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("east"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startHeight+1>= 0 && startHeight+1 <= height-1 && startHeight-1 >= 0 && startHeight-1 <= height-1){
                                        mazeBlock[startHeight-1][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth+1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("west"):
                                    if(startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1 && startHeight-1 >= 0 && startHeight-1 <= height-1){
                                        mazeBlock[startHeight-1][startWidth-1] = 1;
                                        mazeBlock[startHeight+1][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                            }
                            generateInsidePathways(mazeBlock, newStartDirection, width, height, newHeight, startWidth, false);
                        }else{
                            deadEnd = true;
                            System.out.println("Dead end.");
                        }
                    }else{
                        System.out.println("Index Error!");
                    }
                }else if(nextDirection.equals("south")){
                    if(startHeight-1 >= 0 && startHeight-1 <= height-1){
                        if(mazeBlock[startHeight-1][startWidth]!=1 && mazeBlock[startHeight-1][startWidth]!=0){
                            newStartDirection = "south";
                            mazeBlock[startHeight-1][startWidth]=0;
                            newHeight = startHeight -1;
                            switch(nextDirection){
                                case("south"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight+1][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("east"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startHeight+1>= 0 && startHeight+1 <= height-1 && startHeight-1 >= 0 && startHeight-1 <= height-1){
                                        mazeBlock[startHeight-1][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth+1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("west"):
                                    if(startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1 && startHeight-1 >= 0 && startHeight-1 <= height-1){
                                        mazeBlock[startHeight-1][startWidth-1] = 1;
                                        mazeBlock[startHeight+1][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                            }
                            generateInsidePathways(mazeBlock, newStartDirection, width, height, newHeight, startWidth, false);
                        }else{
                            deadEnd = true;
                            System.out.println("Dead end.");
                        }
                    }else{
                        System.out.println("Index Error!");
                    }
                }else if(nextDirection.equals("east")){
                    if(startWidth+1 >= 0 && startWidth+1 <= width-1){
                        if(mazeBlock[startHeight][startWidth+1]!=1 && mazeBlock[startHeight][startWidth+1]!=0){
                            newStartDirection = "east";
                            mazeBlock[startHeight][startWidth+1]=0;
                            newWidth = startWidth+1;
                            switch(nextDirection){
                                case("north"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight+1][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("south"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight+1][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("west"):
                                    if(startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1 && startHeight-1 >= 0 && startHeight-1 <= height-1){
                                        mazeBlock[startHeight-1][startWidth-1] = 1;
                                        mazeBlock[startHeight+1][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                            }
                            generateInsidePathways(mazeBlock, newStartDirection, width, height, startHeight, newWidth, false);
                        }else{
                            deadEnd = true;
                            System.out.println("Dead end.");
                        }
                    }else{
                        System.out.println("Index Error!");
                    }
                }else{
                    if(startWidth-1 >= 0 && startWidth-1 <= width-1){
                        if(mazeBlock[startHeight][startWidth-1]!=1 && mazeBlock[startHeight][startWidth-1]!=0){
                            newStartDirection = "west";
                            mazeBlock[startHeight][startWidth-1]=0;
                            newWidth = startWidth-1;
                            switch(nextDirection){
                                case("north"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight+1][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("east"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startHeight+1>= 0 && startHeight+1 <= height-1 && startHeight-1 >= 0 && startHeight-1 <= height-1){
                                        mazeBlock[startHeight-1][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth+1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("south"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight+1][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                            }
                            generateInsidePathways(mazeBlock, newStartDirection, width, height, startHeight, newWidth, false);
                        }else{
                            deadEnd = true;
                            System.out.println("Dead end.");
                        }
                    }else{
                        System.out.println("Index Error!");
                    }//end else
                }//end else  
            }//end else

            if(deadEnd){
                Queue<Integer> queue = new LinkedList<Integer>();
                for(int i = 0; i < height-1; i++){
                    for(int j = 0; j < width-1; j++){
                        if(mazeBlock[i][j]!=1 && mazeBlock[i][j]!=0 && (mazeBlock[i][j+1]==0  || mazeBlock[i][j+1]==1|| mazeBlock[i][j-1]==0 || mazeBlock[i][j-1]==1 || mazeBlock[i+1][j] == 0 || mazeBlock[i+1][j] == 1 || mazeBlock[i-1][j]==0 || mazeBlock[i-1][j]==1)){
                            queue.add(mazeBlock[i][j]);
                        }//end if
                    }//end for
                }//end for

                int queueSize = queue.size();

                //System.out.println(queue);
                
                if(queueSize == 0){
                    complete = true;
                    return mazeBlock;
                }else{
                    int startNode = queue.remove();
                    System.out.println(startNode);
                    for(int i = 0; i < height-1; i++){
                        for(int j = 0; j < width-1; j++){
                            if(mazeBlock[i][j] == startNode){
                                newHeight = i;
                                newWidth = j;
                            }//end if
                        }//end for
                    }//end for
                }//end else
                //System.out.println(newHeight);
                //System.out.println(newWidth);
                //System.out.println(startDirection);
                mazeBlock[newHeight][newWidth] = 0;

                
                for(int i = 0; i < height; i++){
                    for(int j = 0; j < width; j++){
                        if(i+1 >= 0 && i+1 <= height-1 && i-1 >= 0 && i-1 <= height-1 && j+1 >= 0 && j+1 <= width-1 && j-1 >= 0 && j-1<= width-1){
                            if(mazeBlock[i+1][j] == 1 && mazeBlock[i-1][j] == 1 && mazeBlock[i][j-1] == 1 && mazeBlock[i][j+1] == 1){
                                if(i+2 >= 0 && i+2 <= height-1){
                                    if(mazeBlock[i+2][j]==0){
                                        mazeBlock[i+1][j]=0;
                                    }
                                }else if(i-2 >= 0 && i-2 <= height-1){
                                    if(mazeBlock[i-2][j]==0){
                                        mazeBlock[i-1][j]=0;
                                    }
                                }else if(j-2 >= 0 && j-2 <= width-1){
                                    if(mazeBlock[i][j-2]==0){
                                        mazeBlock[i][j-1]=0;
                                    }
                                }else if(j+2 >= 0 && j+2 <= width-1){
                                    if(mazeBlock[i][j+2]==0){
                                        mazeBlock[i][j+1] = 0;
                                    }//end if
                                }//end else
                            }//end if
                        }//end if
                        generateInsidePathways(mazeBlock, nextDirection, width, height, newHeight, newWidth, true);
                    }//end if
                    }
                }

                for (int i = 0; i <= height-1; i++){
                    for (int j = 0; j <= width-1; j++){
                        if(j-1>= 0 && j-1 <= width-1 && i-1 >= 0 && i-1 <= height-1){
                            if(mazeBlock[i][j] == 0 && mazeBlock[i][j-1] == 0 && mazeBlock[i-1][j] == 0 && mazeBlock[i-1][j-1] == 0){
                                mazeBlock [i][j] = 1;
                            }
                        }
                    }
                }
        }catch(StackOverflowError t){
            System.out.println("Caught "+t);
        }

        return mazeBlock;

    }//end generateInsidePathways

    public int[][] generatePathways(int[][] mazeBlock, int width, int height){
        ArrayList<Integer> visitedList = new ArrayList<Integer>();
        Random rand = new Random();

        for(int j = 0; j < width; j++){
            for(int k = 0; k < height; k++){
                if(j == 0 || k == 0 || j == width-1 || k == height-1){
                    visitedList.add(mazeBlock[j][k]);
                    mazeBlock[j][k] = 1;
                }else{
                    continue;
                }
            }
        }

        String startDirection;
            
        int randomStartDeterminer = rand.nextInt(4);

        switch(randomStartDeterminer){
            case 0:
                startDirection = "north";
                break;
            case 1:
                startDirection = "east";
                break;
            case 2:
                startDirection = "south";
                break;
            case 3:
                startDirection = "west";
                break;
            default:
                startDirection = null;
        }

        int randomStartDirectionNS = rand.nextInt(width);
        int randomStartDirectionEW = rand.nextInt(height);
        int randomStartNode;
        int nodeNumber;

        switch(startDirection){
            case "north":
                //System.out.println("north");
                randomStartNode = randomStartDirectionNS;
                //System.out.println(randomStartDirectionNS);
                setCurrentStartHeight(0);
                setCurrentStartWidth(randomStartNode);
                nodeNumber = mazeBlock[this.currentStartHeight][this.currentStartWidth];
                visitedList.add(nodeNumber);
                mazeBlock[this.currentStartHeight][this.currentStartWidth] = 0;
                break;
            case "south":
                //System.out.println("south");
                randomStartNode = randomStartDirectionNS;
                //System.out.println(randomStartDirectionNS);
                setCurrentStartHeight(height-1);
                setCurrentStartWidth(randomStartNode);
                nodeNumber = mazeBlock[this.currentStartHeight][this.currentStartWidth];
                visitedList.add(nodeNumber);
                mazeBlock[this.currentStartHeight][this.currentStartWidth] = 0;
                break;
            case "east":
                //System.out.println("east");
                randomStartNode = randomStartDirectionEW;
                //System.out.println(randomStartDirectionEW);
                setCurrentStartHeight(randomStartNode);
                setCurrentStartWidth(width-1);
                nodeNumber = mazeBlock[this.currentStartHeight][this.currentStartWidth];
                //System.out.println(nodeNumber);
                visitedList.add(nodeNumber);
                mazeBlock[this.currentStartHeight][this.currentStartWidth] = 0;
                break;
            case "west":
                //System.out.println("west");
                randomStartNode = randomStartDirectionEW;
                //System.out.println(randomStartDirectionEW);
                setCurrentStartHeight(randomStartNode);
                setCurrentStartWidth(0);
                nodeNumber = mazeBlock[this.currentStartHeight][this.currentStartWidth];
                //System.out.println(nodeNumber);
                visitedList.add(nodeNumber);
                mazeBlock[this.currentStartHeight][this.currentStartWidth] = 0;
                break;
            default:
                randomStartNode = 0;
        }

        String endDirection;
        //printMaze(mazeBlock);

        int randomEndDeterminer = rand.nextInt(4);
        //System.out.println(randomEndDeterminer);

        switch(randomEndDeterminer){
            case 0:
                endDirection = "north";
                break;
            case 1:
                endDirection = "east";
                break;
            case 2:
                endDirection = "south";
                break;
            case 3:
                endDirection = "west";                                                                                                                                                                                                                                                                                                                                                                    
                break;
            default:
                endDirection = null;
        }

        int randomEndDirectionNS = rand.nextInt(width);
        int randomEndDirectionEW = rand.nextInt(height);
        int randomEndNode;

        switch(endDirection){
            case "north":
                //System.out.println("north");
                randomEndNode = randomEndDirectionNS;
                //System.out.println(randomStartDirectionNS);
                setCurrentEndHeight(0);
                setCurrentEndWidth(randomEndNode);
                nodeNumber = mazeBlock[this.currentEndHeight][this.currentEndWidth];
                visitedList.add(nodeNumber);
                mazeBlock[this.currentEndHeight][this.currentEndWidth] = 0;
                break;
            case "south":
                //System.out.println("south");
                randomEndNode = randomEndDirectionNS;
                //System.out.println(randomStartDirectionNS);
                setCurrentEndHeight(height-1);
                setCurrentEndWidth(randomEndNode);
                nodeNumber = mazeBlock[this.currentEndHeight][this.currentEndWidth];
                visitedList.add(nodeNumber);
                mazeBlock[this.currentEndHeight][this.currentEndWidth] = 0;
                break;
            case "east":
                //System.out.println("east");
                randomEndNode = randomEndDirectionEW;
                //System.out.println(randomStartDirectionEW);
                setCurrentEndHeight(randomEndNode);
                setCurrentEndWidth(width-1);
                nodeNumber = mazeBlock[this.currentEndHeight][this.currentEndWidth];
                visitedList.add(nodeNumber);
                mazeBlock[this.currentEndHeight][this.currentEndWidth] = 0;
                break;
            case "west":
                //System.out.println("west");
                randomEndNode = randomEndDirectionEW;
                //System.out.println(randomStartDirectionEW);
                setCurrentEndHeight(randomEndNode);
                setCurrentEndWidth(0);
                nodeNumber = mazeBlock[this.currentEndHeight][this.currentEndWidth];
                visitedList.add(nodeNumber);
                mazeBlock[this.currentEndHeight][this.currentEndWidth] = 0;
                break;
            default:
                randomEndNode = 0;
        }

        if(startDirection.equals("north")){
            mazeBlock = generateInsidePathways(mazeBlock, startDirection, width, height, this.currentStartHeight, this.currentStartWidth, true);
        }else if(startDirection.equals("east")){
            mazeBlock = generateInsidePathways(mazeBlock, startDirection, width, height, this.currentStartHeight, this.currentStartWidth, true);
        }else if(startDirection.equals("south")){
            mazeBlock = generateInsidePathways(mazeBlock, startDirection, width, height, this.currentStartHeight, this.currentStartWidth, true);
        }else if(startDirection.equals("west")){
            mazeBlock = generateInsidePathways(mazeBlock, startDirection, width, height, this.currentStartHeight, this.currentStartWidth, true);
        }

        switch(startDirection){
            case "north":
                mazeBlock[this.currentStartHeight][this.currentStartWidth] = 0;
                break;
            case "south":
                mazeBlock[this.currentStartHeight][this.currentStartWidth] = 0;
                break;
            case "east":
                mazeBlock[this.currentStartHeight][this.currentStartWidth] = 0;
                break;
            case "west":
                mazeBlock[this.currentStartHeight][this.currentStartWidth] = 0;
                break;
            default:
                randomStartNode = 0;
        }

        switch(endDirection){
            case "north":
                mazeBlock[this.currentEndHeight][this.currentEndWidth] = 0;
                mazeBlock[this.currentEndHeight + 1][this.currentEndWidth] = 0;
                break;
            case "south":
                mazeBlock[this.currentEndHeight][this.currentEndWidth] = 0;
                mazeBlock[this.currentEndHeight-1][this.currentEndWidth] = 0;
                break;
            case "east":
                mazeBlock[this.currentEndHeight][this.currentEndWidth] = 0;
                mazeBlock[this.currentEndHeight][this.currentEndWidth-1] = 0;
                break;
            case "west":
                mazeBlock[this.currentEndHeight][this.currentEndWidth] = 0;
                mazeBlock[this.currentEndHeight][this.currentEndWidth+1] = 0;
                break;
            default:
                randomEndNode = 0;
        }
        return mazeBlock;
    }

    public void setCurrentStartHeight(int currentStartHeight){
        this.currentStartHeight = currentStartHeight;
    }

    public int getCurrentStartHeight(){
        return currentStartHeight;
    }

    public void setCurrentStartWidth(int currentStartWidth){
        this.currentStartWidth = currentStartWidth;
    }

    public int getCurrentStartWidth(){
        return currentStartWidth;
    }

    public void setCurrentEndHeight(int currentEndHeight){
        this.currentEndHeight = currentEndHeight;
    }

    public int getCurrentEndHeight(){
        return currentEndHeight;
    }

    public void setCurrentEndWidth(int currentEndWidth){
        this.currentEndWidth = currentEndWidth;
    }

    public int getCurrentEndWidth(){
        return currentEndWidth;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public int getHeight(){
        return height;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public int getWidth(){
        return width;
    }
}