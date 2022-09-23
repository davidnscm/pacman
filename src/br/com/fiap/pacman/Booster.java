package br.com.fiap.pacman;

public class Booster extends Item {
    private int shift;

    public Booster(int x, int y, int shift) {
        super(x, y, 100);

        this.shift = shift;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

}