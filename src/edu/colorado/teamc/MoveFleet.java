package edu.colorado.teamc;

public class MoveFleet implements Command {
    Grid grid;
    Direction prev_direction;

    public MoveFleet(Grid grid){
        this.grid = grid;
    }

    @Override
    public void execute(Direction d) {
        this.prev_direction = d;
        grid.moveFleet(d);

    }

    @Override
    public void undo(){
        prev_direction.setOpposite();
        grid.moveFleet(prev_direction);
    }
}
