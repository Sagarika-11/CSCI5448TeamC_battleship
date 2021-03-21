package edu.colorado.teamc;

public class MoveFleet implements Command {
    Grid grid;
    Direction prev_direction;

    public MoveFleet(Grid grid){
        this.grid = grid;
    }

    @Override
    public Grid execute(Direction d) {
        this.prev_direction = d;
        boolean done = grid.moveFleet(d);
        if(done){
            System.out.println("moved");
        }
        return grid;
    }

    @Override
    public Grid undo(){
        prev_direction.setOpposite();
        grid.moveFleet(prev_direction);
        return grid;
    }
}
