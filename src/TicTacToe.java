import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    JButton reset;
    JButton newGame;
    JLabel scoreLabel;
    JTextField player1NameField;
    JTextField player2NameField;
    String player1Name = "Player 1";
    String player2Name = "Player 2";
    int player1Score = 0;
    int player2Score = 0;
    int moves = 0;
    boolean player1_turn;
    boolean vsComputer = false;

    TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(240, 240, 240));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            buttons[i].setBackground(new Color(200, 200, 200));
            buttons[i].setForeground(Color.BLACK);
        }

        reset = new JButton("Reset");
        reset.setFocusable(false);
        reset.addActionListener(this);

        newGame = new JButton("New Game");
        newGame.setFocusable(false);
        newGame.addActionListener(this);

        player1NameField = new JTextField(player1Name, 10);
        player2NameField = new JTextField(player2Name, 10);

        scoreLabel = new JLabel("Player 1: " + player1Score + "  Player 2: " + player2Score);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel, BorderLayout.CENTER);
        title_panel.add(reset, BorderLayout.EAST);
        title_panel.add(newGame, BorderLayout.WEST);
        frame.add(scoreLabel, BorderLayout.SOUTH);
        frame.add(player1NameField, BorderLayout.WEST);
        frame.add(player2NameField, BorderLayout.EAST);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reset) {
            resetGame();
        } else if (e.getSource() == newGame) {
            startNewGame();
        } else {
            for (int i = 0; i < 9; i++) {
                if (e.getSource() == buttons[i]) {
                    if (player1_turn) {
                        if (buttons[i].getText().equals("")) {
                            buttons[i].setForeground(new Color(255, 0, 0));
                            buttons[i].setText("X");
                            player1_turn = false;
                            textfield.setText(player2Name + "'s turn");
                            moves++;
                            check();
                            if (vsComputer && moves < 9) {
                                computerMove();
                            }
                        }
                    } else {
                        if (!vsComputer) {
                            if (buttons[i].getText().equals("")) {
                                buttons[i].setForeground(new Color(0, 0, 255));
                                buttons[i].setText("O");
                                player1_turn = true;
                                textfield.setText(player1Name + "'s turn");
                                moves++;
                                check();
                            }
                        }
                    }
                }
            }
        }
    }

    public void firstTurn() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (vsComputer) {
            player1_turn = random.nextBoolean();
            if (player1_turn) {
                textfield.setText(player1Name + "'s turn (X)");
            } else {
                textfield.setText(player2Name + "'s turn (O)");
                computerMove();
            }
        } else {
            player1_turn = true;
            textfield.setText(player1Name + "'s turn (X)");
        }
    }

    public void computerMove() {
        int index;
        do {
            index = random.nextInt(9);
        } while (!buttons[index].getText().equals(""));
        buttons[index].setForeground(new Color(0, 0, 255));
        buttons[index].setText("O");
        player1_turn = true;
        textfield.setText(player1Name + "'s turn (X)");
        moves++;
        check();
    }

    public void check() {
    	if(
				(buttons[0].getText().equals("X")) &&
				(buttons[1].getText().equals("X")) &&
				(buttons[2].getText().equals("X"))
				) {
			       player1Wins(0,1,2);}
		if(
				(buttons[3].getText().equals("X")) &&
				(buttons[4].getText().equals("X")) &&
				(buttons[5].getText().equals("X"))
				) {
			       player1Wins(3,4,5);}
		if(
				(buttons[6].getText().equals("X")) &&
				(buttons[7].getText().equals("X")) &&
				(buttons[8].getText().equals("X"))
				) {
			       player1Wins(6,7,8);}
		if(
				(buttons[0].getText().equals("X")) &&
				(buttons[3].getText().equals("X")) &&
				(buttons[6].getText().equals("X"))
				) {
			       player1Wins(0,3,6);}
		if(
				(buttons[1].getText().equals("X")) &&
				(buttons[4].getText().equals("X")) &&
				(buttons[7].getText().equals("X"))
				) {
			       player1Wins(1,4,7);}
		if(
				(buttons[2].getText().equals("X")) &&
				(buttons[5].getText().equals("X")) &&
				(buttons[8].getText().equals("X"))
				) {
			       player1Wins(2,5,8);}
		if(
				(buttons[0].getText().equals("X")) &&
				(buttons[4].getText().equals("X")) &&
				(buttons[8].getText().equals("X"))
				) {
			       player1Wins(0,4,8);}
		if(
				(buttons[2].getText().equals("X")) &&
				(buttons[4].getText().equals("X")) &&
				(buttons[6].getText().equals("X"))
				) {
			       player1Wins(2,4,6);}
		if(
				(buttons[0].getText().equals("O")) &&
				(buttons[1].getText().equals("O")) &&
				(buttons[2].getText().equals("O"))
				) {
			       player2Wins(0,1,2);}
		if(
				(buttons[3].getText().equals("O")) &&
				(buttons[4].getText().equals("O")) &&
				(buttons[5].getText().equals("O"))
				) {
			       player2Wins(3,4,5);}
		if(
				(buttons[6].getText().equals("O")) &&
				(buttons[7].getText().equals("O")) &&
				(buttons[8].getText().equals("O"))
				) {
			       player2Wins(6,7,8);}
		if(
				(buttons[0].getText().equals("O")) &&
				(buttons[3].getText().equals("O")) &&
				(buttons[6].getText().equals("O"))
				) {
			       player2Wins(0,3,6);}
		if(
				(buttons[1].getText().equals("O")) &&
				(buttons[4].getText().equals("O")) &&
				(buttons[7].getText().equals("O"))
				) {
			       player2Wins(1,4,7);}
		if(
				(buttons[2].getText().equals("O")) &&
				(buttons[5].getText().equals("O")) &&
				(buttons[8].getText().equals("O"))
				) {
			       player2Wins(2,5,8);}
		if(
				(buttons[0].getText().equals("O")) &&
				(buttons[4].getText().equals("O")) &&
				(buttons[8].getText().equals("O"))
				) {
			       player2Wins(0,4,8);}
		if(
				(buttons[2].getText().equals("O")) &&
				(buttons[4].getText().equals("O")) &&
				(buttons[6].getText().equals("O"))
				) {
			       player2Wins(2,4,6);}
            if (moves == 9) {
                drawMatch();
            }
        }
  

    public void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setBackground(new Color(200, 200, 200));
        }
        moves = 0;
        firstTurn();
    }

    public void startNewGame() {
        resetGame();

        player1Name = JOptionPane.showInputDialog(frame, "Enter Player 1's name:", "Player 1");
        player2Name = JOptionPane.showInputDialog(frame, "Enter Player 2's name:", "Player 2");

        player1Score = 0;
        player2Score = 0;
        updateScoreLabel();

        int choice = JOptionPane.showOptionDialog(frame,
                "Choose game mode:",
                "Game Mode",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Player vs Player", "Player vs Computer"},
                "Player vs Player");

        if (choice == JOptionPane.YES_OPTION) {
            vsComputer = false;
            textfield.setText(player1Name + "'s turn (X)");
        } else {
            vsComputer = true;
            firstTurn();
        }
    }

public void player1Wins(int a, int b, int c) {
        
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        
        for (int i=0;i<9;i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText(player1Name + " wins");
        player1Score++;
        updateScoreLabel();
    }
    
    public void player2Wins(int a, int b, int c) {
        
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        
        for (int i=0;i<9;i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText(player2Name + " wins");
        player2Score++;
        updateScoreLabel();
    }
    
    public void drawMatch() {
        textfield.setText("Match Draw!");
        disableButtons();
    }

    private void updateScoreLabel() {
        scoreLabel.setText(player1Name + ": " + player1Score + "  " + player2Name + ": " + player2Score);
    }

    private void disableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }
//
//    private void player1Wins() {
//        player1Score++;
//        updateScoreLabel();
//        JOptionPane.showMessageDialog(frame, player1Name + " wins!");
//        disableButtons();
//    }
//
//    private void player2Wins() {
//        player2Score++;
//        updateScoreLabel();
//        JOptionPane.showMessageDialog(frame, player2Name + " wins!");
//        disableButtons();
//    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
