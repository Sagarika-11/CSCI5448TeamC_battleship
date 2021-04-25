package edu.colorado.teamc;

/**
 * Used in MoveFleet implementation
 */
public interface Command {

    public Grid execute(Direction direction);
    public Grid undo();
}
