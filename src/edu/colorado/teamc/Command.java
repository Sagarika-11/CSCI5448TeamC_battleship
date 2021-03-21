package edu.colorado.teamc;

//Currently: to implement move_fleet

public interface Command {

    public Grid execute(Direction direction);
    public Grid undo();
}
