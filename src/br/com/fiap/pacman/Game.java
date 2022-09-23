package br.com.fiap.pacman;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Game extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	private Player player = new Player(275, 275, 180);
	private Ghost ghost1 = new Ghost(10, 10, 10);
	private Ghost ghost2 = new Ghost(500, 0, 10);
	private Ghost ghost3 = new Ghost(0, 500, 10);
	private Ghost ghost4 = new Ghost(500, 500, 10);
	private Bomb bomb = new Bomb(100, 100);
	private Booster booster = new Booster(400, 400, (int) (Math.random() * 100) + 45);

	private JLabel imgPlayer = new JLabel(new ImageIcon("src/images/pacman.png"));
	private JLabel imgGhost1 = new JLabel(new ImageIcon("src/images/ghost.png"));
	private JLabel imgGhost2 = new JLabel(new ImageIcon("src/images/ghost.png"));
	private JLabel imgGhost3 = new JLabel(new ImageIcon("src/images/ghost.png"));
	private JLabel imgGhost4 = new JLabel(new ImageIcon("src/images/ghost.png"));
	private JLabel imgBomb = new JLabel(new ImageIcon("src/images/bomb.png"));
	private JLabel imgBooster = new JLabel(new ImageIcon("src/images/booster.png"));

	private final int SCREENSIZE = 600;
	private int speed = 50;

	boolean up;
	boolean right;
	boolean left;
	boolean down;

	public static void main(String[] args) {
		new Game().init();
	}

	private void init() {
		setLayout(null);
		player.setScreenSize(SCREENSIZE);
		player.setLife(15);

		ghost1.setScreenSize(SCREENSIZE);
		ghost2.setScreenSize(SCREENSIZE);
		ghost3.setScreenSize(SCREENSIZE);
		ghost4.setScreenSize(SCREENSIZE);

		add(imgPlayer);
		add(imgGhost1);
		add(imgGhost2);
		add(imgGhost3);
		add(imgGhost4);
		add(imgBomb);
		add(imgBooster);

		render();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(SCREENSIZE + 100, SCREENSIZE + 100);
		setVisible(true);
		addKeyListener(this);

		run();
	}

	private void render() {

		updateLocation(imgPlayer, player);
		updateLocation(imgGhost1, ghost1);
		updateLocation(imgGhost2, ghost2);
		updateLocation(imgGhost3, ghost3);
		updateLocation(imgGhost4, ghost4);
		updateLocation(imgBomb, bomb);
		updateLocation(imgBooster, booster);
		setTitle("Life: " + player.getLife());
		SwingUtilities.updateComponentTreeUI(this);

	}

	private void updateLocation(JLabel label, GameObject object) {
		label.setBounds(object.getX(), object.getY(), 50, 50);
		ImageIcon myImage = (ImageIcon) label.getIcon();
		Image img = myImage.getImage();
		Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(newImg));
		label.setVisible(object.isVisible());
	}

	private void run() {
		while (player.getLife() > 0) {

			if (up == true) {
				player.move();
			}

			if (left == true) {
				player.move();
			}

			if (down == true) {
				player.move();
			}

			if (right == true) {
				player.move();
			}

			if (player.collision(ghost1) || player.collision(ghost2) || player.collision(ghost3)
					|| player.collision(ghost4) || player.collision(bomb)) {
				System.out.println("Você perdeu uma vida");
				hit();
			}

			if (player.collision(booster) && booster.isVisible()) {
				System.out.println("Você agora é Invencível");
				player.setInvincible(true);
				booster.setVisible(false);
			}

			ghost1.movement();
			ghost2.movement();
			ghost3.movement();
			ghost4.movement();

			if (!booster.isVisible()) {
				booster.setShift(booster.getShift() - 1);
				if (booster.getShift() == 0) {
					booster.setVisible(true);
					player.setInvincible(false);
					booster.setShift((int) (Math.random() * 100) + 45);

				}

			}

			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			render();

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == '8' || c == 'w')
			player.setDirection(0);
		if (c == '6' || c == 'd')
			player.setDirection(90);
		if (c == '2' || c == 's')
			player.setDirection(180);
		if (c == '4' || c == 'a')
			player.setDirection(270);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == '8' || c == 'w')
			up = true;
		if (c == '6' || c == 'd')
			right = true;
		if (c == '2' || c == 's')
			down = true;
		if (c == '4' || c == 'a')
			left = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == '8' || c == 'w')
			up = true;
		if (c == '6' || c == 'd')
			right = true;
		if (c == '2' || c == 's')
			down = true;
		if (c == '4' || c == 'a')
			left = true;
	}

	private void resetPosition() {
		player.setX(275);
		player.setY(275);
		ghost1.setX(0);
		ghost1.setY(0);
		ghost2.setX(500);
		ghost2.setY(0);
		ghost3.setX(0);
		ghost3.setY(500);
		ghost4.setX(500);
		ghost4.setY(500);
		bomb.setX(100);
		bomb.setY(100);
		booster.setX(400);
		booster.setY(400);
	}

	private void hit() {
		if (!player.isInvincible()) {
			player.setLife(player.getLife() - 1);
			resetPosition();
		}

	}

}
