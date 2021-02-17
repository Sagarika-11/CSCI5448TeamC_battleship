package edu.colorado.teamc;

// TODO: do subtypes of Ship (Sagarika)
// TODO: implement logic to hit pieces/ sink ship

public abstract class Ship {
    private String name;
    private int length;
    private boolean sunk;

    public Ship(String n, int l){
        name = n;
        length = l;
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

    public  void show()     {     // dunno why this is here maybe it is just an example method
        System.out.println("IF you can't see this then something is severely wrong!!");
    }
}