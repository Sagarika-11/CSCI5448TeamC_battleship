package edu.colorado.teamc;


public class Direction {

    public enum dir {
        EAST,
        NORTH,
        SOUTH,
        WEST
    }

    private dir direction;

    //constructor
    public Direction(String s){
        switch(s){
            case "N":
                this.direction = dir.NORTH;
                break;
            case "S":
                this.direction = dir.SOUTH;
                break;
            case "E":
                this.direction = dir.EAST;
                break;
            case "W":
                this.direction = dir.WEST;
                break;
            default:
                System.out.println("Invalid direction!");
        }
    }
    //Defined for the undo function
    public void setOpposite(){
        dir prev_d = this.direction;
        switch(prev_d) {
            case NORTH:
                this.direction = dir.SOUTH;
                break;
            case SOUTH:
                this.direction = dir.NORTH;
                break;
            case EAST:
                this.direction = dir.WEST;
                break;
            case WEST:
                this.direction = dir.EAST;
                break;
        }
    }

    public Coordinate moveCoordinate(Coordinate c){
        switch (this.direction){
            case NORTH:
                Coordinate c_new1 =  new Coordinate((c.getRow()-1),c.getCol(), c.getDepth());
                return c_new1;
            case SOUTH:
                Coordinate c_new2 =  new Coordinate((c.getRow()+1),c.getCol(), c.getDepth());
                return c_new2;
            case EAST:
                Coordinate c_new3 =  new Coordinate(c.getRow(),(c.getCol()-1), c.getDepth());
                return c_new3;
            case WEST:
                Coordinate c_new4 =  new Coordinate(c.getRow(),(c.getCol()+1), c.getDepth());
                return c_new4;
            default:
                return c;
        }
    }
}

