import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectFourGUI extends JFrame implements ActionListener {
    static String CIRCLE = "●";
    static int CIRCLE_SIZE = 40;
    static Font font = new Font("Arial", Font.PLAIN, CIRCLE_SIZE);

    static Color COLOR_P1 = Color.BLUE;
    static Color COLOR_P2 = Color.RED;

    private JPanel gridPanel;
    private GameManager gamemanager;
    private JButton[][] fields;

    ConnectFourGUI() {
        gridPanel = new JPanel(new GridLayout(GameManager.ROWS, GameManager.COLUMNS));

        createField();

        setLayout(new BorderLayout());
        setTitle("Connect Four");

        add(gridPanel);
        pack();

    }

    void createField() {
        gamemanager = new GameManager();
        fields = new JButton[GameManager.COLUMNS][GameManager.ROWS];
        for (int i = 0; i < GameManager.ROWS; i++) {
            for (int j = 0; j < GameManager.COLUMNS; j++) {
                fields[j][i] = new JButton();
                fields[j][i].addActionListener(this);
                fields[j][i].setPreferredSize(new Dimension(50, 50));
                fields[j][i].setFont(font);
                fields[j][i].setFocusable(false);
                fields[j][i].setMargin(new Insets(2, 2, 2, 2));
                fields[j][i].setText(i + ", " + j);
                gridPanel.add(fields[j][i]);

            }
        }
    }

    void updateView() {
        int status;
        for (int j = 0; j < GameManager.ROWS; j++) {
            for (int i = 0; i < GameManager.COLUMNS; i++) {
                status = gamemanager.getStatus(i, j);
                switch (status) {
                    case 0:
                        fields[i][j].setText("");
                        break;
                    case 1:
                        fields[i][j].setText(CIRCLE);
                        fields[i][j].setForeground(COLOR_P1);
                        break;
                    case 2:
                        fields[i][j].setText(CIRCLE);
                        fields[i][j].setForeground(COLOR_P2);
                        break;
                }
            }
        }
    }


    public static void main(String[] args) {
        ConnectFourGUI gui = new ConnectFourGUI();
        gui.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        for (int i = 0; i < GameManager.COLUMNS; i++) {
            for (int j = 0; j < GameManager.ROWS; j++) {
                if (source == fields[i][j]) {
                    gamemanager.place(i, j);
                    updateView();
                }
            }
        }
    }
}
