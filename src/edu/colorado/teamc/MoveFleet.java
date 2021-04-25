package edu.colorado.teamc;

/**
 * Allows a player to move their entire fleet in a specified direction
 */
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
            System.out.print("Successfully moved fleet\n");
        }
        return grid;
    }

    /**
     * Undo a MoveFleet move
     *
     * @return new grid after undo
     */
    @Override
    public Grid undo(){
        if (prev_direction != null) {
            prev_direction.setOpposite();

            System.out.print("Nothing to undo... turn wasted!\n");
            return grid;
        }
        grid.moveFleet(prev_direction);
        System.out.print("Successful undo of movefleet\n");

        return grid;
    }
}
