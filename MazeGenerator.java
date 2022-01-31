import java.util.Arrays;
import java.util.Random;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

import java.util.ArrayList;

public class MazeGenerator{

    int counter;
    int newBeginNode;
    int currentHeight;
    int currentWidth;

    public static void main(String args[]){
        MazeGenerator mazeGenerator = new MazeGenerator();
        int[][] completedBoard = mazeGenerator.generateMazeBlock(10,10);
        String printMaze = mazeGenerator.printMaze(completedBoard);
        System.out.println(printMaze);

        int[][] mazeBlock = mazeGenerator.generateMazeBlock(10,10);
        int[][] constructedMaze = mazeGenerator.generatePathways(mazeBlock, 10, 10);
        printMaze = mazeGenerator.printMaze(constructedMaze);
        System.out.println(printMaze);
    }

    public MazeGenerator(){
        
    }//end MazeGenerator

    public String printMaze(int[][] completedBoard){
        String boardRep = Arrays.deepToString(completedBoard);
        return boardRep;
    }

    public int[][] generateMazeBlock(int width, int height){
        int[][] board = new int[width][height];
        counter = 1;
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
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
        boolean complete = false;

        try{
            System.out.println("Random Direction Determiner " + randomDirectionDeterminer);
            System.out.println("startDirection " + startDirection);
            if(startDirection.equals("north")){
                switch(randomDirectionDeterminer){
                    case(0):
                        //System.out.println("SWITCH");
                        nextDirection = "north";
                        break;
                    case(1):
                        //System.out.println("SWITCH");
                        nextDirection = "east";
                        break;
                    case(2):
                        //System.out.println("SWITCH");
                        nextDirection = "west";
                        break;
                }
            }else if(startDirection.equals("east")){
                switch(randomDirectionDeterminer){
                    case(0):
                        //System.out.println("SWITCH");
                        nextDirection = "north";
                        break;
                    case(1):
                        //System.out.println("SWITCH");
                        nextDirection = "east";
                        break;
                    case(2):
                        //System.out.println("SWITCH");
                        nextDirection = "south";
                        break;
                }
            }else if(startDirection.equals("south")){
                switch(randomDirectionDeterminer){
                    case(0):
                        //System.out.println("SWITCH");
                        nextDirection = "east";
                        break;
                    case(1):
                        //System.out.println("SWITCH");
                        nextDirection = "south";
                        break;
                    case(2):
                        //System.out.println("SWITCH");
                        nextDirection = "west";
                        break;
                }
            }else if(startDirection.equals("west")){
                switch(randomDirectionDeterminer){
                    case(0):
                        //System.out.println("SWITCH");
                        nextDirection = "north";
                        break;
                    case(1):
                        //System.out.println("SWITCH");
                        nextDirection = "south";
                        break;
                    case(2):
                        //System.out.println("SWITCH");
                        nextDirection = "west";
                        break;
                }
            }

            System.out.println("Next Direction " + nextDirection);

            /**switch(nextDirection){
                case("north"):
                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1){
                        System.out.println("WALLS NORTH");
                        mazeBlock[startHeight][startWidth+1] = 1;
                        mazeBlock[startHeight][startWidth-1] = 1;
                        String printMaze = printMaze(mazeBlock);
                        System.out.println(printMaze);
                    }else{
                        System.out.println("Index Error!");
                    }
                case("east"):
                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startHeight+1>= 0 && startHeight+1 <= height-1){
                        mazeBlock[startHeight][startWidth+1] = 1;
                        mazeBlock[startHeight+1][startWidth] = 1;
                    }else{
                        System.out.println("Index Error!");
                    }
                case("south"):
                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1){
                        mazeBlock[startHeight][startWidth+1] = 1;
                        mazeBlock[startHeight][startWidth-1] = 1;
                    }else{
                        System.out.println("Index Error!");
                    }
                case("west"):
                    if(startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1){
                        mazeBlock[startHeight][startWidth-1] = 1;
                        mazeBlock[startHeight+1][startWidth] = 1;
                    }else{
                        System.out.println("Index Error!");
                    }*/
            //}
            if(begin){
                if(startDirection.equals("north")){
                    if(startHeight+1 >= 0 && startHeight+1 <= height-1){
                        if(mazeBlock[startHeight+1][startWidth]!=1 && mazeBlock[startHeight+1][startWidth]!=0){
                            mazeBlock[startHeight+1][startWidth]=0;
                            newHeight = startHeight+1;
                            System.out.println("Next direction: " + nextDirection);
                            switch(nextDirection){
                                case("north"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1){
                                        System.out.println("WALLS NORTH");
                                        mazeBlock[startHeight][startWidth+1] = 1;
                                        mazeBlock[startHeight][startWidth-1] = 1;
                                        String printMaze = printMaze(mazeBlock);
                                        System.out.println(printMaze);
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("east"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startHeight+1>= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("west"):
                                    if(startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight][startWidth-1] = 1;
                                        mazeBlock[startHeight+1][startWidth] = 1;
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
                            System.out.println("Next direction: " + nextDirection);
                            switch(nextDirection){
                                case("south"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1){
                                        mazeBlock[startHeight][startWidth+1] = 1;
                                        mazeBlock[startHeight][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("east"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startHeight+1>= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("west"):
                                    if(startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight][startWidth-1] = 1;
                                        mazeBlock[startHeight+1][startWidth] = 1;
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
                            System.out.println("Next direction: " + nextDirection);
                            switch(nextDirection){
                                case("north"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1){
                                        System.out.println("WALLS NORTH");
                                        mazeBlock[startHeight][startWidth+1] = 1;
                                        mazeBlock[startHeight][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("south"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1){
                                        mazeBlock[startHeight][startWidth+1] = 1;
                                        mazeBlock[startHeight][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("west"):
                                    if(startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight][startWidth-1] = 1;
                                        mazeBlock[startHeight+1][startWidth] = 1;
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
                            System.out.println("Next direction: " + nextDirection);
                            switch(nextDirection){
                                    case("north"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1){
                                        System.out.println("WALLS NORTH");
                                        mazeBlock[startHeight][startWidth+1] = 1;
                                        mazeBlock[startHeight][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("east"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startHeight+1>= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("south"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1){
                                        mazeBlock[startHeight][startWidth+1] = 1;
                                        mazeBlock[startHeight][startWidth-1] = 1;
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
                            System.out.println("Next Direction" + nextDirection);
                            switch(nextDirection){
                                case("north"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1){
                                        mazeBlock[startHeight][startWidth+1] = 1;
                                        mazeBlock[startHeight][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("east"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startHeight+1>= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("west"):
                                    if(startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight][startWidth-1] = 1;
                                        mazeBlock[startHeight+1][startWidth] = 1;
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
                            //System.out.println("Next Direction" + nextDirection);
                            switch(nextDirection){
                                case("south"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1){
                                        mazeBlock[startHeight][startWidth+1] = 1;
                                        mazeBlock[startHeight][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("east"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startHeight+1>= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("west"):
                                    if(startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight][startWidth-1] = 1;
                                        mazeBlock[startHeight+1][startWidth] = 1;
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
                            //System.out.println("Next Direction" + nextDirection);
                            switch(nextDirection){
                                case("north"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1){
                                        System.out.println("WALLS NORTH");
                                        mazeBlock[startHeight][startWidth+1] = 1;
                                        mazeBlock[startHeight][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("south"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1){
                                        mazeBlock[startHeight][startWidth+1] = 1;
                                        mazeBlock[startHeight][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("west"):
                                    if(startWidth-1 >= 0 && startWidth-1 <= width-1 && startHeight+1 >= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight][startWidth-1] = 1;
                                        mazeBlock[startHeight+1][startWidth] = 1;
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
                            //System.out.println("Next Direction" + nextDirection);
                            switch(nextDirection){
                                case("north"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1){
                                        System.out.println("WALLS NORTH");
                                        mazeBlock[startHeight][startWidth+1] = 1;
                                        mazeBlock[startHeight][startWidth-1] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("east"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startHeight+1>= 0 && startHeight+1 <= height-1){
                                        mazeBlock[startHeight][startWidth+1] = 1;
                                        mazeBlock[startHeight+1][startWidth] = 1;
                                    }else{
                                        System.out.println("Index Error!");
                                    }
                                    break;
                                case("south"):
                                    if(startWidth+1 >= 0 && startWidth+1 <= width-1 && startWidth-1 >= 0 && startWidth-1 <= width-1){
                                        mazeBlock[startHeight][startWidth+1] = 1;
                                        mazeBlock[startHeight][startWidth-1] = 1;
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
                        if(mazeBlock[i][j]!=1 && mazeBlock[i][j]!=0){
                            queue.add(mazeBlock[i][j]);
                        }//end if
                    }//end for
                }//end for

                int queueSize = queue.size();

                System.out.println(queue);
                
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
                System.out.println(newHeight);
                System.out.println(newWidth);
                System.out.println(startDirection);
                mazeBlock[newHeight][newWidth] = 0;
                generateInsidePathways(mazeBlock, nextDirection, width, height, newHeight, newWidth, true);
            }//end if
        }catch(StackOverflowError t){
            System.out.println("Caught "+t);
        }

        return mazeBlock;

    }//end generateInsidePathways

    public int[][] generatePathways(int[][] mazeBlock, int width, int height){
        int i = 0;
        ArrayList<Integer> visitedList = new ArrayList<Integer>();
        Random rand = new Random();

        for(int j = 0; j < width; j++){
            for(int k = 0; k < height; k++){
                if(j == 0 || k == 0 || j == width-1 || k == height-1){
                    visitedList.add(mazeBlock[j][k]);
                    mazeBlock[j][k] = 1;
                    i++;
                }
            }
        }

        String startDirection;
            
        int randomStartDeterminer = rand.nextInt(4);
        //System.out.println(randomStartDeterminer);

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
                System.out.println("north");
                randomStartNode = randomStartDirectionNS;
                System.out.println(randomStartDirectionNS);
                nodeNumber = mazeBlock[0][randomStartNode];
                //System.out.println(nodeNumber);
                visitedList.add(nodeNumber);
                mazeBlock[0][randomStartNode] = 0;
                i++;
                break;
            case "south":
                System.out.println("south");
                randomStartNode = randomStartDirectionNS;
                System.out.println(randomStartDirectionNS);
                nodeNumber = mazeBlock[width-1][randomStartNode];
                //System.out.println(nodeNumber);
                visitedList.add(nodeNumber);
                mazeBlock[width-1][randomStartNode] = 0;
                i++;
                break;
            case "east":
                System.out.println("east");
                randomStartNode = randomStartDirectionEW;
                System.out.println(randomStartDirectionEW);
                nodeNumber = mazeBlock[randomStartNode][height-1];
                System.out.println(nodeNumber);
                visitedList.add(nodeNumber);
                mazeBlock[randomStartNode][height-1] = 0;
                i++;
                break;
            case "west":
                System.out.println("west");
                randomStartNode = randomStartDirectionEW;
                System.out.println(randomStartDirectionEW);
                nodeNumber = mazeBlock[randomStartNode][0];
                System.out.println(nodeNumber);
                visitedList.add(nodeNumber);
                mazeBlock[randomStartNode][0] = 0;
                i++;
                break;
            default:
                randomStartNode = 0;
        }

        String endDirection;
        String printMaze = printMaze(mazeBlock);
        System.out.println(printMaze);

        int randomEndDeterminer = rand.nextInt(4);
        System.out.println(randomEndDeterminer);

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
                System.out.println("north");
                randomEndNode = randomEndDirectionNS;
                System.out.println(randomStartDirectionNS);
                nodeNumber = mazeBlock[0][randomEndNode];
                //System.out.println(nodeNumber);
                visitedList.add(nodeNumber);
                mazeBlock[0][randomEndNode] = 0;
                i++;
                break;
            case "south":
                System.out.println("south");
                randomEndNode = randomEndDirectionNS;
                System.out.println(randomStartDirectionNS);
                nodeNumber = mazeBlock[width-1][randomEndNode];
                //System.out.println(nodeNumber);
                visitedList.add(nodeNumber);
                mazeBlock[width-1][randomEndNode] = 0;
                i++;
                break;
            case "east":
                System.out.println("east");
                randomEndNode = randomEndDirectionEW;
                System.out.println(randomStartDirectionEW);
                nodeNumber = mazeBlock[randomEndNode][height-1];
                //System.out.println(nodeNumber);
                visitedList.add(nodeNumber);
                mazeBlock[randomEndNode][height-1] = 0;
                i++;
                break;
            case "west":
                System.out.println("west");
                randomEndNode = randomEndDirectionEW;
                System.out.println(randomStartDirectionEW);
                nodeNumber = mazeBlock[randomEndNode][0];
                //System.out.println(nodeNumber);
                visitedList.add(nodeNumber);
                mazeBlock[randomEndNode][0] = 0;
                i++;
                break;
            default:
                randomEndNode = 0;
        }

        System.out.println(printMaze);

        if(startDirection.equals("north")){
            mazeBlock = generateInsidePathways(mazeBlock, startDirection, width, height, 0, randomStartNode, true);
        }else if(startDirection.equals("east")){
            mazeBlock = generateInsidePathways(mazeBlock, startDirection, width, height, randomStartNode, height-1, true);
        }else if(startDirection.equals("south")){
            mazeBlock = generateInsidePathways(mazeBlock, startDirection, width, height, width-1, randomStartNode, true);
        }else if(startDirection.equals("west")){
            mazeBlock = generateInsidePathways(mazeBlock, startDirection, width, height, randomStartNode, 0, true);
        }





        /*while(i < width * height){
            //System.out.println("i is: " + i);
            int randomNode = rand.nextInt(100) + 4;
            //System.out.println("Random Node is: " + randomNode);
            int randomEdge = rand.nextInt(2);
            //System.out.println("Random Edge is: " + randomEdge);
            
            if(visitedList.contains(randomNode)){
                continue;
            }else{
                for(int j = 0; j < width; j++){
                    for(int k = 0; k < height; k++){
                        if(j == 0 || k == 0 || j == width-1 || k == height-1){
                            visitedList.add(mazeBlock[j][k]);
                            mazeBlock[j][k] = 1;
                            i++;
                        }
                        
                        int north = k + 1;
                        int south = k - 1;
                        int east = j + 1;
                        int west = j - 1;

                        
                        if(mazeBlock[j][k] == randomNode){
                            mazeBlock[j][k] = randomEdge;
                            visitedList.add(randomNode);
                            i++;
                            //System.out.println("Visited List: " + visitedList);
                            //continue;
                        }
                    }
                }
            }
        }*/
        return mazeBlock;
    }
}