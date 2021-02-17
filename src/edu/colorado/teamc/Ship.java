package edu.colorado.teamc;

public class Ship {
    private String name;
    private int length;
    private int piecesHit;
    private boolean sunk;

    public Ship(){
        name = "";
        length = 0;
        piecesHit = 0;
        sunk = false;
    }

    public Ship(String n, int l){
        name = n;
        length = l;
        piecesHit = 0;
        sunk = false;
    }

    public void setName(String n){
        this.name = n;
    }

    public String getName(){
        return name;
    }

    public void setLength(int l){
        this.length = l;
    }

    public int getLength(){
        return length;
    }

    public int getPiecesHit() {
        return piecesHit;
    }

    public void setPiecesHit(int piecesHit) {
        this.piecesHit = piecesHit;
    }

    public  void show()     {     // dunno why this is here maybe it is just an example method
        System.out.println("IF you can't see this then something is severely wrong!!");
    }
}
