import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Pong2 extends JComponent implements ActionListener, MouseMotionListener, KeyListener
{
	private int ballX = (int) (Math.random() * 770);
	private int ballY = (int) (Math.random() * 200 + 110);
	private double ballYSpeed = ;
	private double ballXSpeed = Math.random() * 3 + 3;
	private double ballSpeedRate = 1.035;
	private int paddleX = 325;
	private int paddleY = 510;
	private int paddle2X = 325;
	private int paddle2Y = 75;
	private int paddleSpeed = 15;
	private boolean brpaddleX = false;
	private boolean blpaddleX = false;
	private boolean brpaddle2X = false;
	private boolean blpaddle2X = false;
	private boolean isGameOver = false;


	public static void main(String[] args)
	{
		JFrame window = new JFrame("Pong2 Game");
		Pong2 game = new Pong2();
		window.add(game);
		window.pack();
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		Timer t = new Timer(10, game);
		t.start();

		window.addMouseMotionListener(game);
		window.addKeyListener(game);
	}

	public Dimension getPreferredSize()
	{
		return new Dimension(800, 600);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		g.setColor(new Color(178, 223, 224)); //background
		g.fillRect(0, 0, 800, 600);

		g.setColor(new Color(110, 65, 13)); //Paddle
		g.fillRect(paddleX, paddleY, 150, 15);

		g.setColor(new Color(110, 65, 13)); //player 2 paddle
		g.fillRect(paddle2X, paddle2Y, 150, 15);

		g.setColor(new Color(155, 93, 169)); //Ball
		g.fillOval(ballX, ballY, 30, 30);

		if (isGameOver)
		{
			g.setColor(new Color( (int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255) ));
			g.setFont(new Font("sansserif", Font.BOLD, 42));
			
			if (ballY >= 570) 
				g.drawString("TOP PLAYER WINS!", 200, 300);
			else
				g.drawString("BOTTOM PLAYER WINS!", 155, 300);
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		ballX += ballXSpeed;
		ballY += ballYSpeed;

		if (ballX + 30 >= paddleX && ballX <= paddleX + 150 && ballY + 30 >= paddleY && ballY <= paddleY + 15) {
			ballYSpeed += .5;
			ballYSpeed *= -1;
			ballXSpeed = collide();
			ballY = 480;
			ballYSpeed *= ballSpeedRate;
		}
		if (ballX + 30 >= paddle2X && ballX <= paddle2X + 150 && ballY + 30 >= paddle2Y && ballY <= paddle2Y + 15) {
			ballYSpeed += .5;
			ballYSpeed *= -1;
			ballXSpeed = collide();
			ballY = 90;
			ballYSpeed *= ballSpeedRate;
		}
		if (ballY <= 0)
			ballYSpeed *= -1;
		if (ballX >= 770)
			ballXSpeed *= -1;
		if (ballX <= 0)
			ballXSpeed *= -1;
		
		if (blpaddleX)
			paddleX -= paddleSpeed;
		if (brpaddleX)
			paddleX += paddleSpeed;
		if (blpaddle2X)
			paddle2X -= paddleSpeed;
		if (brpaddle2X)
			paddle2X += paddleSpeed;
			
		
		if(ballY <= 0 || ballY >= 570) {
			isGameOver = true;
			ballXSpeed = 0;
			ballYSpeed = 0;
		}
		
		repaint();
	}

	private double collide() {
		if( (int) (Math.random() * 2) == 1)
		{
			return Math.random() * 3 + 5;
		}
		else
		{
			return Math.random() * -3 - 5;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
	}

	@Override
	public void mouseMoved(MouseEvent e){
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A)
			blpaddle2X = true;
		if (e.getKeyCode() == KeyEvent.VK_D)
			brpaddle2X = true;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			blpaddleX = true;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			brpaddleX = true;
		
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A)
			blpaddle2X = false;
		if (e.getKeyCode() == KeyEvent.VK_D)
			brpaddle2X = false;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			blpaddleX = false;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			brpaddleX = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}