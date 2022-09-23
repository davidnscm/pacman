package br.com.fiap.pacman;

public class GameObject {

    private int dirX;
    private int dirY;
    private int screenSize;
    private boolean visible = true;
    private int direction;

    public GameObject(int dirX, int dirY, int screenSize) {
        this.dirX = dirX;
        this.dirY = dirY;
        this.screenSize = screenSize;
    }

    public GameObject(int dirX, int dirY) {
        this.dirX = dirX;
        this.dirY = dirY;

    }

    public int getX() {
        return dirX;
    }

    public void setX(int dirX) {
        if (dirX < 0) {
            System.out.println("Posição inválida");
            this.dirX = 0;
        } else
            this.dirX = dirX;
    }

    public int getY() {
        return dirY;
    }

    public void setY(int dirY) {
        if (dirY < 0) {
            System.out.println("Posição inválida");
            this.dirY = 0;
        } else
            this.dirY = dirY;

    }

    public boolean collision(GameObject obj) {
        if (getX() >= obj.getX() && getX() <= obj.getX() + 50 && getY() >= obj.getY() && getY() <= obj.getY() + 50) {
            return true;
        }
        return false;
    }

    public boolean move() {

        int x = getX(), y = getY();

        if (direction == 0) {
            y -= 10;
        }
        if (direction == 180) {
            y += 10;
        }
        if (direction == 90) {
            x += 10;
        }
        if (direction == 270) {
            x -= 10;
        }

        if (x < getScreenSize() && x > 0) {
            if (y < getScreenSize() && y > 0) {
                setX(x);
                setY(y);
                return true;
            }
        }

        return false;

    }

    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        if (direction < 0) {
            System.out.println("Direção inválida");
        }

        this.direction = direction;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisivel(boolean visible) {
        this.visible = visible;
    }

}
