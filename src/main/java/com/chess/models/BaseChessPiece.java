package main.java.com.chess.models;

public abstract class BaseChessPiece implements ChessPiece{
    protected String position;

    public BaseChessPiece(String position) {
        this.position=position;
    }

    @Override
    public String getPosition(){
        return position;
    }
}
