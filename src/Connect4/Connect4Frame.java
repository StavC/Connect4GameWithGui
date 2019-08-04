
package Connect4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Connect4Frame extends JFrame implements ActionListener {


    private static JPanel boardJpanel;
    private static JButton[] rowButtons;
    private static Disks[][] panelsMatrix;
    private static Color whosTurn;
    private static boolean gameWon;
    private static int redWonCounter;
    private static int blueWonCounter;
    private static JLabel counterWinningLabel;
    private static JButton clearWinningCounter;
    private final JPanel gameJpanel;
    private final JPanel buttonJPanel;
    private final JPanel infoJpanel;
    private final JPanel nextTurnJPanel;
    private final JPanel newGameButtonJPanel;
    private final JPanel infoAndButtonsJpanel;
    private final JPanel winningCounterJpanel;
    private final JButton newGameButton;
    private final JLabel nextTurnLabel;

    public Connect4Frame() {
        super("Connect 4 Game");
        gameJpanel = new JPanel();
        gameJpanel.setLayout(new BorderLayout());
        winningCounterJpanel=new JPanel(new BorderLayout());
        boardJpanel = new JPanel();
        boardJpanel.setLayout(new GridLayout(5, 7));
        newGame();

        infoAndButtonsJpanel = new JPanel();
        infoAndButtonsJpanel.setLayout(new BorderLayout());
        rowButtons = new JButton[7];
        buttonJPanel = new JPanel();
        infoJpanel = new JPanel();
        clearWinningCounter=new JButton("Clear Winning Records");
        clearWinningCounter.addActionListener(this);
        counterWinningLabel=new JLabel("Red Won: "+redWonCounter+"\n Blue Won: "+ blueWonCounter);
        winningCounterJpanel.add(counterWinningLabel,BorderLayout.WEST);
        winningCounterJpanel.add(clearWinningCounter,BorderLayout.EAST);

        //  col buttons
        buttonJPanel.setLayout(new GridLayout(1, rowButtons.length));
        for (int i = 0; i < rowButtons.length; i++) {
            rowButtons[i] = new JButton("Col " + (i + 1));
            rowButtons[i].addActionListener(this);
            buttonJPanel.add(rowButtons[i]);
        }
        infoAndButtonsJpanel.add(buttonJPanel, BorderLayout.NORTH);
        ///

        //making the game look nicer but requires more code :(
        nextTurnJPanel = new JPanel();
        newGameButtonJPanel = new JPanel();
        infoJpanel.setLayout(new GridLayout(1, 2));
        nextTurnJPanel.setLayout(new BorderLayout());
        newGameButtonJPanel.setLayout(new BorderLayout());
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this);
        nextTurnLabel = new JLabel("Good Luck!  Red You are First!");
        nextTurnJPanel.add(nextTurnLabel, BorderLayout.WEST);
        newGameButtonJPanel.add(newGameButton, BorderLayout.EAST);
        infoJpanel.add(nextTurnJPanel);
        infoJpanel.add(newGameButtonJPanel);
        infoAndButtonsJpanel.add(infoJpanel, BorderLayout.SOUTH);


        // adding the parts together
        gameJpanel.add(infoAndButtonsJpanel, BorderLayout.SOUTH);
        gameJpanel.add(boardJpanel, BorderLayout.CENTER);
        add(gameJpanel,BorderLayout.CENTER);
        add(winningCounterJpanel,BorderLayout.NORTH);

    }

    public static void newGame() {// starting a new game and filling the matrix with white disks

        panelsMatrix = new Disks[5][7];
        gameWon = false;
        redWonCounter=0;
        blueWonCounter=0;


        for (int i = 0; i < panelsMatrix.length; i++) {
            for (int j = 0; j < panelsMatrix[i].length; j++) {

                panelsMatrix[i][j] = new Disks();
                panelsMatrix[i][j].setDiskColor(Color.WHITE);
                boardJpanel.add(panelsMatrix[i][j]);

            }
        }


        whosTurn = Color.red; //red is starting
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == rowButtons[0]) {
            makeMove(0);
        }
        if (e.getSource() == rowButtons[1]) {
            makeMove(1);
        }
        if (e.getSource() == rowButtons[2]) {
            makeMove(2);
        }
        if (e.getSource() == rowButtons[3]) {
            makeMove(3);
        }
        if (e.getSource() == rowButtons[4]) {
            makeMove(4);
        }
        if (e.getSource() == rowButtons[5]) {
            makeMove(5);
        }
        if (e.getSource() == rowButtons[6]) {
            makeMove(6);
        }
        if(e.getSource()==clearWinningCounter){ //Extra Winning Counter
            redWonCounter=0;
            blueWonCounter=0;
            counterWinningLabel.setText("Red Won: "+redWonCounter+"\n Blue Won: "+ blueWonCounter);

        }

        if (e.getSource() == newGameButton) {//new game
            for (int i = 0; i < panelsMatrix.length; i++) {
                for (int j = 0; j < panelsMatrix[i].length; j++) {

                    panelsMatrix[i][j].setDiskColor(Color.WHITE);
                    boardJpanel.add(panelsMatrix[i][j]);

                }
            }
            for (int k = 0; k < 7; k++) {
                rowButtons[k].setEnabled(true);
            }
            whosTurn = Color.red;
            nextTurnLabel.setText("Good Luck!  Red You are First!");
            gameWon = false;

        }
    }

    private void makeMove(int col) { //making a move and putting a disk in the col if possible

        for (int i = 4; i >= 0; i--) {

            if (i == 0) {
                rowButtons[col].setEnabled(false);
            }

            if (panelsMatrix[i][col].getDiskColor() == Color.WHITE) {//if there is open space put a new disk
                panelsMatrix[i][col].setDiskColor(whosTurn);
                if (checkIfWin(whosTurn)) {//checking if someone won after the move
                    gameWon = true;
                    if (whosTurn == Color.red) {
                        JOptionPane.showMessageDialog(this, "the winner is red!");
                        nextTurnLabel.setText("Red Won! Click NewGame to start a new game");
                        redWonCounter++;
                    } else {
                        JOptionPane.showMessageDialog(this, "the winner is blue!");
                        nextTurnLabel.setText("Blue Won! Click NewGame to start a new game");
                        blueWonCounter++;
                    }
                    counterWinningLabel.setText("Red Won: "+redWonCounter+"\n Blue Won: "+ blueWonCounter);
                    for (int k = 0; k < 7; k++) {
                        rowButtons[k].setEnabled(false);
                    }
                }
                if (!gameWon) {// showing whos turn is it
                    if (whosTurn == Color.red) {
                        whosTurn = Color.blue;
                        nextTurnLabel.setText("Blue Its Your Turn go win this!");
                    } else {
                        whosTurn = Color.red;
                        nextTurnLabel.setText("Red Its Your Turn you can do it!");
                    }
                }
                break;
            }
        }
    }


    public boolean checkIfWin(Color whosTurn) {// checking if someone won

        // horizontalCheck
        for (int i = 0; i < panelsMatrix.length; i++) {
            for (int j = 0; j < panelsMatrix[i].length - 3; j++) {
                if (panelsMatrix[i][j].getDiskColor() == whosTurn && panelsMatrix[i][j + 1].getDiskColor() == whosTurn && panelsMatrix[i][j + 2].getDiskColor() == whosTurn && panelsMatrix[i][j + 3].getDiskColor() == whosTurn) {
                    return true;
                }
            }
        }

        // verticalCheck
        for (int i = 0; i < panelsMatrix.length - 3; i++) {
            for (int j = 0; j < panelsMatrix[i].length; j++) {
                if (panelsMatrix[i][j].getDiskColor() == whosTurn && panelsMatrix[i + 1][j].getDiskColor() == whosTurn && panelsMatrix[i + 2][j].getDiskColor() == whosTurn && panelsMatrix[i + 3][j].getDiskColor() == whosTurn) {
                    return true;
                }
            }
        }

        // ascendingDiagonalCheck
        for (int i = 3; i < panelsMatrix.length; i++) {
            for (int j = 0; j < panelsMatrix[i].length - 3; j++) {
                if (panelsMatrix[i][j].getDiskColor() == whosTurn && panelsMatrix[i - 1][j + 1].getDiskColor() == whosTurn && panelsMatrix[i - 2][j + 2].getDiskColor() == whosTurn && panelsMatrix[i - 3][j + 3].getDiskColor() == whosTurn)
                    return true;
            }
        }

        // descendingDiagonalCheck
        for (int i = 3; i < panelsMatrix.length; i++) {
            for (int j = 3; j < panelsMatrix[i].length; j++) {
                if (panelsMatrix[i][j].getDiskColor() == whosTurn && panelsMatrix[i - 1][j - 1].getDiskColor() == whosTurn && panelsMatrix[i - 2][j - 2].getDiskColor() == whosTurn && panelsMatrix[i - 3][j - 3].getDiskColor() == whosTurn)
                    return true;
            }
        }

        return false;
    }

}










