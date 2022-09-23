package br.com.fiap.pacman;

public class Player extends GameObject {
    private int life;

    private boolean invincible;

    public Player(int dirX, int dirY, int screenSize) {
        super(dirX, dirY, screenSize);
    }

    public void setLife(int life) {
        if (life < 1) {
            System.out.println("As vidas precisam ser maior do que zero");
        } else
            this.life = life;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public int getLife() {
        return life;
    }

    public void setInvencivel(boolean invincible) {
        this.invincible = invincible;
    }

    public Player(int dirX, int dirY, int screenSize, int life, int direction, boolean invincible) {
        super(dirX, dirY, screenSize);
        this.life = life;
        this.setDirection(direction);
        this.invincible = invincible;
    }

}
