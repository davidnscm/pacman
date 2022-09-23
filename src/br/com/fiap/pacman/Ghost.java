package br.com.fiap.pacman;

public class Ghost extends GameObject {

    public Ghost(int dirX, int dirY, int screenSize) {
        super(dirX, dirY, screenSize);
    }

    public void movement() {
        int dirRandom = (int) (Math.random() * 3) + 0;
        switch (dirRandom) {
            case 0:
                setDirection(0);
                break;
            case 1:
                setDirection(90);
                break;
            case 2:
                setDirection(180);
                break;
            case 3:
                setDirection(270);
                break;
        }
        move();
    }
}
