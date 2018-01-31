import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import static java.lang.System.*;

public class TicTacToe extends JFrame {
	private static final long serialVersionUID = 1L;
	private char[][] positions = new char[3][3];
	private int turnNum = 1;

	public TicTacToe() {
		setTitle("Tic Tac Toe");
		getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel gamePanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				g2d.setStroke(new BasicStroke(2));
				g2d.setFont(new Font("SansSerif", Font.PLAIN, 15));
				g2d.drawString("Player 1 = ", 135, 40);
				g2d.setColor(Color.red);
				g2d.drawLine(215, 25, 215 + 15, 25 + 15);
				g2d.drawLine(215, 25 + 15, 215 + 15, 25);
				
				g2d.setColor(Color.black);
				g2d.drawString("Player 2 = ", 275, 40);
				g2d.setColor(Color.blue);
				g2d.drawOval(355, 25, 15, 15);
				
				g2d.setStroke(new BasicStroke(3));
				g2d.setColor(Color.black);
				g2d.drawLine(190, 70, 190, 450); // first vertical line
				g2d.drawLine(320, 70, 320, 450); // second vertical line
				g2d.drawLine(70, 190, 450, 190); // first horizontal line
				g2d.drawLine(70, 320, 450, 320); // second horizontal line
			}
		};
		gamePanel.setBackground(Color.white);
		gamePanel.setPreferredSize(new Dimension(510, 515));
		gamePanel.setLayout(new BorderLayout());
		BoardListener listener = new BoardListener();
		gamePanel.addMouseListener(listener);

		getContentPane().add(gamePanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new TicTacToe();
			}
		});
	}

	public void draw(char[][] pos) {
		Graphics2D g2d = (Graphics2D) getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(4));
		int x1 = (70 + 190) / 2 - 30;
		int y1 = (70 + 190) / 2 - 10;
		if (pos[0][0] == 'x') { // first cell
			g2d.setColor(Color.red);
			g2d.drawLine(x1, y1, x1 + 60, y1 + 60);
			g2d.drawLine(x1, y1 + 60, x1 + 60, y1);
		} else if (pos[0][0] == 'o') {
			g2d.setColor(Color.blue);
			g2d.drawOval(x1, y1, 60, 60);
		}
		int x2 = (190 + 320) / 2 - 30;
		int y2 = (70 + 190) / 2 - 10;
		if (pos[0][1] == 'x') { // second cell
			g2d.setColor(Color.red);
			g2d.drawLine(x2, y2, x2 + 60, y2 + 60);
			g2d.drawLine(x2, y2 + 60, x2 + 60, y2);
		} else if (pos[0][1] == 'o') {
			g2d.setColor(Color.blue);
			g2d.drawOval(x2, y2, 60, 60);
		}
		int x3 = (320 + 450) / 2 - 30;
		int y3 = (70 + 190) / 2 - 10;
		if (pos[0][2] == 'x') { // third cell
			g2d.setColor(Color.red);
			g2d.drawLine(x3, y3, x3 + 60, y3 + 60);
			g2d.drawLine(x3, y3 + 60, x3 + 60, y3);
		} else if (pos[0][2] == 'o') {
			g2d.setColor(Color.blue);
			g2d.drawOval(x3, y3, 60, 60);
		}
		int x4 = (70 + 190) / 2 - 30;
		int y4 = (190 + 320) / 2 - 10;
		if (pos[1][0] == 'x') { // fourth cell
			g2d.setColor(Color.red);
			g2d.drawLine(x4, y4, x4 + 60, y4 + 60);
			g2d.drawLine(x4, y4 + 60, x4 + 60, y4);
		} else if (pos[1][0] == 'o') {
			g2d.setColor(Color.blue);
			g2d.drawOval(x4, y4, 60, 60);
		}
		int x5 = (190 + 320) / 2 - 30;
		int y5 = (190 + 320) / 2 - 10;
		if (pos[1][1] == 'x') { // fifth cell
			g2d.setColor(Color.red);
			g2d.drawLine(x5, y5, x5 + 60, y5 + 60);
			g2d.drawLine(x5, y5 + 60, x5 + 60, y5);
		} else if (pos[1][1] == 'o') {
			g2d.setColor(Color.blue);
			g2d.drawOval(x5, y5, 60, 60);
		}
		int x6 = (320 + 450) / 2 - 30;
		int y6 = (190 + 320) / 2 - 10;
		if (pos[1][2] == 'x') { // sixth cell
			g2d.setColor(Color.red);
			g2d.drawLine(x6, y6, x6 + 60, y6 + 60);
			g2d.drawLine(x6, y6 + 60, x6 + 60, y6);
		} else if (pos[1][2] == 'o') {
			g2d.setColor(Color.blue);
			g2d.drawOval(x6, y6, 60, 60);
		}
		int x7 = (70 + 190) / 2 - 30;
		int y7 = (320 + 450) / 2 - 10;
		if (pos[2][0] == 'x') { // seventh cell
			g2d.setColor(Color.red);
			g2d.drawLine(x7, y7, x7 + 60, y7 + 60);
			g2d.drawLine(x7, y7 + 60, x7 + 60, y7);
		} else if (pos[2][0] == 'o') {
			g2d.setColor(Color.blue);
			g2d.drawOval(x7, y7, 60, 60);
		}
		int x8 = (190 + 320) / 2 - 30;
		int y8 = (320 + 450) / 2 - 10;
		if (pos[2][1] == 'x') { // eighth cell
			g2d.setColor(Color.red);
			g2d.drawLine(x8, y8, x8 + 60, y8 + 60);
			g2d.drawLine(x8, y8 + 60, x8 + 60, y8);
		} else if (pos[2][1] == 'o') {
			g2d.setColor(Color.blue);
			g2d.drawOval(x8, y8, 60, 60);
		}
		int x9 = (320 + 450) / 2 - 30;
		int y9 = (320 + 450) / 2 - 10;
		if (pos[2][2] == 'x') { // ninth cell
			g2d.setColor(Color.red);
			g2d.drawLine(x9, y9, x9 + 60, y9 + 60);
			g2d.drawLine(x9, y9 + 60, x9 + 60, y9);
		} else if (pos[2][2] == 'o') {
			g2d.setColor(Color.blue);
			g2d.drawOval(x9, y9, 60, 60);
		}
		if (isXWin()) {
			out.println("x won");
			g2d.setColor(Color.red);
			g2d.setFont(new Font("SansSerif", Font.PLAIN, 20));
			g2d.drawString("X won. Do you want to play again?", 25, 510);
			g2d.setColor(Color.black);
			g2d.drawString("Yes", 380, 510);
			g2d.drawString("No", 440, 510);
		}
		if (isOWin()) {
			out.println("o won");
			g2d.setColor(Color.blue);
			g2d.setFont(new Font("SansSerif", Font.PLAIN, 20));
			g2d.drawString("O won. Do you want to play again?", 25, 510);
			g2d.setColor(Color.black);
			g2d.drawString("Yes", 380, 510);
			g2d.drawString("No", 440, 510);
		}
		if (isTie()) {
			out.println("tie");
			g2d.setColor(Color.black);
			g2d.setFont(new Font("SansSerif", Font.PLAIN, 20));
			g2d.drawString("Do you want to play again?", 25, 510);
			g2d.drawString("Yes", 380, 510);
			g2d.drawString("No", 440, 510);
		}
	}
	
	public boolean isXWin() {
		boolean cond = false;
		if (positions[0][0] == 'x' && positions[0][1] == 'x' && positions[0][2] == 'x') {
			cond = true;
		} else if (positions[1][0] == 'x' && positions[1][1] == 'x' && positions[1][2] == 'x') {
			cond = true;
		} else if (positions[2][0] == 'x' && positions[2][1] == 'x' && positions[2][2] == 'x') {
			cond = true;
		} else if (positions[0][0] == 'x' && positions[1][0] == 'x' && positions[2][0] == 'x') {
			cond = true;
		} else if (positions[0][1] == 'x' && positions[1][1] == 'x' && positions[2][1] == 'x') {
			cond = true;
		} else if (positions[0][2] == 'x' && positions[1][2] == 'x' && positions[2][2] == 'x') {
			cond = true;
		} else if (positions[0][0] == 'x' && positions[1][1] == 'x' && positions[2][2] == 'x') {
			cond = true;
		} else if (positions[0][2] == 'x' && positions[1][1] == 'x' && positions[2][0] == 'x') {
			cond = true;
		}
		return cond;
	}

	public boolean isOWin() {
		boolean cond = false;
		if (positions[0][0] == 'o' && positions[0][1] == 'o' && positions[0][2] == 'o') {
			cond = true;
		} else if (positions[1][0] == 'o' && positions[1][1] == 'o' && positions[1][2] == 'o') {
			cond = true;
		} else if (positions[2][0] == 'o' && positions[2][1] == 'o' && positions[2][2] == 'o') {
			cond = true;
		} else if (positions[0][0] == 'o' && positions[1][0] == 'o' && positions[2][0] == 'o') {
			cond = true;
		} else if (positions[0][1] == 'o' && positions[1][1] == 'o' && positions[2][1] == 'o') {
			cond = true;
		} else if (positions[0][2] == 'o' && positions[1][2] == 'o' && positions[2][2] == 'o') {
			cond = true;
		} else if (positions[0][0] == 'o' && positions[1][1] == 'o' && positions[2][2] == 'o') {
			cond = true;
		} else if (positions[0][2] == 'o' && positions[1][1] == 'o' && positions[2][0] == 'o') {
			cond = true;
		}
		return cond;
	}
	
	public boolean isTie() {
		if (isXWin() || isOWin()) {
			return false;
		}
		for (int row = 0; row < positions.length; row++) {
			for (int col = 0; col < positions.length; col++) {
				if (positions[row][col] == '\u0000') {
					return false;
				}
			}
		}
		return true;
	}
	
	private class BoardListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			out.println("x: " + e.getX() + ", y: " + e.getY());
			if (!isXWin() && !isOWin() && !isTie()) {
				if (e.getX() >= 70 && e.getX() <= 190 && e.getY() >= 70 && e.getY() <= 190) { // first cell
					if (turnNum % 2 != 0 && positions[0][0] == '\u0000') {
						positions[0][0] = 'x';
						turnNum++;
						draw(positions);
					} else if (turnNum % 2 == 0 && positions[0][0] == '\u0000') {
						positions[0][0] = 'o';
						turnNum++;
						draw(positions);
					}
				} else if (e.getX() >= 190 && e.getX() <= 320 && e.getY() >= 70 && e.getY() <= 190) { // second cell
					if (turnNum % 2 != 0 && positions[0][1] == '\u0000') {
						positions[0][1] = 'x';
						turnNum++;
						draw(positions);
					} else if (turnNum % 2 == 0 && positions[0][1] == '\u0000') {
						positions[0][1] = 'o';
						turnNum++;
						draw(positions);
					}
				} else if (e.getX() >= 320 && e.getX() <= 450 && e.getY() >= 70 && e.getY() <= 190) { // third cell
					if (turnNum % 2 != 0 && positions[0][2] == '\u0000') {
						positions[0][2] = 'x';
						turnNum++;
						draw(positions);
					} else if (turnNum % 2 == 0 && positions[0][2] == '\u0000') {
						positions[0][2] = 'o';
						turnNum++;
						draw(positions);
					}
				} else if (e.getX() >= 70 && e.getX() <= 190 && e.getY() >= 190 && e.getY() <= 320) { // fourth cell
					if (turnNum % 2 != 0 && positions[1][0] == '\u0000') {
						positions[1][0] = 'x';
						turnNum++;
						draw(positions);
					} else if (turnNum % 2 == 0 && positions[1][0] == '\u0000') {
						positions[1][0] = 'o';
						turnNum++;
						draw(positions);
					}
				} else if (e.getX() >= 190 && e.getX() <= 320 && e.getY() >= 190 && e.getY() <= 320) { // fifth cell
					if (turnNum % 2 != 0 && positions[1][1] == '\u0000') {
						positions[1][1] = 'x';
						turnNum++;
						draw(positions);
					} else if (turnNum % 2 == 0 && positions[1][1] == '\u0000') {
						positions[1][1] = 'o';
						turnNum++;
						draw(positions);
					}
				} else if (e.getX() >= 320 && e.getX() <= 450 && e.getY() >= 190 && e.getY() <= 320) { // sixth cell
					if (turnNum % 2 != 0 && positions[1][2] == '\u0000') {
						positions[1][2] = 'x';
						turnNum++;
						draw(positions);
					} else if (turnNum % 2 == 0 && positions[1][2] == '\u0000') {
						positions[1][2] = 'o';
						turnNum++;
						draw(positions);
					}
				} else if (e.getX() >= 70 && e.getX() <= 190 && e.getY() >= 320 && e.getY() <= 450) { // seventh cell
					if (turnNum % 2 != 0 && positions[2][0] == '\u0000') {
						positions[2][0] = 'x';
						turnNum++;
						draw(positions);
					} else if (turnNum % 2 == 0 && positions[2][0] == '\u0000') {
						positions[2][0] = 'o';
						turnNum++;
						draw(positions);
					}
				} else if (e.getX() >= 190 && e.getX() <= 320 && e.getY() >= 320 && e.getY() <= 450) { // eighth cell
					if (turnNum % 2 != 0 && positions[2][1] == '\u0000') {
						positions[2][1] = 'x';
						turnNum++;
						draw(positions);
					} else if (turnNum % 2 == 0 && positions[2][1] == '\u0000') {
						positions[2][1] = 'o';
						turnNum++;
						draw(positions);
					}
				} else if (e.getX() >= 320 && e.getX() <= 450 && e.getY() >= 320 && e.getY() <= 450) { // ninth cell
					if (turnNum % 2 != 0 && positions[2][2] == '\u0000') {
						positions[2][2] = 'x';
						turnNum++;
						draw(positions);
					} else if (turnNum % 2 == 0 && positions[2][2] == '\u0000') {
						positions[2][2] = 'o';
						turnNum++;
						draw(positions);
					}
				}
			} else if (isXWin() || isOWin() || isTie()) {
				if (e.getX() >= 380 && e.getX() <= 420 && e.getY() >= 470 && e.getY() <= 490) {
					repaint();
					positions = new char[3][3];
					turnNum = 1;
				} else if (e.getX() >= 430 && e.getX() <= 470 && e.getY() >= 470 && e.getY() <= 490) {
					System.exit(0);
				}
			}
		}
	}
}