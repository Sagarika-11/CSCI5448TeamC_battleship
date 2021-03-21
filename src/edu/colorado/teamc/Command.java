package edu.colorado.teamc;

//Currently: to implement move_fleet

public interface Command {

    public void execute(Direction direction);
    public void undo();
}
