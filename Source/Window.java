import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Window extends JFrame implements ActionListener {

    JPanel background = new JPanel();
    Timer timer = new Timer(10, this);

    Settings settings = new Settings();

    int WIDTH = 500, HEIGHT = 300;

    public Window() {

        setFocusable(true);
        timer.start();

        setSize(WIDTH,HEIGHT);
        setTitle("Auto Miner");
        setResizable(false);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupContent();
    }

    void setupContent() {
        background.setLayout(null);
        background.setBounds(0,0,WIDTH,HEIGHT);
        background.setBackground(new Color(10,10,10));

        JTextField minutesInput = new JTextField();

        JPanel autoWalkerBool = new JPanel();
        JPanel enabledWalking = new JPanel();

        autoWalkerBool.setBounds(135,115,20,20);
        autoWalkerBool.setBackground(new Color(20,20,20));

        enabledWalking.setBounds(20/2-10/2,20/2-10/2,10,10);
        enabledWalking.setBackground(Color.WHITE);

        autoWalkerBool.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                settings.AUTO_WALK = !settings.AUTO_WALK;
                if (settings.AUTO_WALK) {
                    autoWalkerBool.add(enabledWalking);
                }else {
                    autoWalkerBool.remove(enabledWalking);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                autoWalkerBool.setBackground(new Color(15,15,15));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                autoWalkerBool.setBackground(new Color(20,20,20));
            }
        });

        minutesInput.setBounds(135,65,70,20);
        minutesInput.setBackground(Color.BLACK);
        minutesInput.setForeground(Color.WHITE);
        minutesInput.setCaretColor(Color.WHITE);
        minutesInput.setFont(new Font("Monospaced", Font.PLAIN, 14));
        minutesInput.setBorder(null);

        JLabel executeButton = new JLabel("EXECUTE", null, JLabel.HORIZONTAL);
        JLabel timeLabel = new JLabel("TIME MINING:");
        JLabel timePassed = new JLabel("0:00");

        JLabel timeDisplay = new JLabel("[MINUTES]");

        JLabel autoWalker = new JLabel("AUTO WALKING:");

        autoWalker.setForeground(Color.WHITE);
        autoWalker.setFont(new Font("Monospaced", Font.PLAIN, 15));
        autoWalker.setBounds(15,100,200,50);

        timePassed.setForeground(Color.WHITE);
        timePassed.setFont(new Font("Monospaced", Font.PLAIN, 15));
        timePassed.setBounds(15,170,200,50);

        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("Monospaced", Font.PLAIN, 15));
        timeLabel.setBounds(15,50,200,50);

        timeDisplay.setForeground(Color.WHITE);
        timeDisplay.setFont(new Font("Monospaced", Font.PLAIN, 10));
        timeDisplay.setBounds(15,65,200,50);

        int WIDTH_DIFFERENCE = 30;

        int EXECUTE_BUTTON_WIDTH = WIDTH-WIDTH_DIFFERENCE;
        int EXECUTE_BUTTON_HEIGHT = 50;
        int OFFSET_Y = EXECUTE_BUTTON_HEIGHT+WIDTH_DIFFERENCE+WIDTH_DIFFERENCE/4;

        executeButton.setBounds(WIDTH/2-EXECUTE_BUTTON_WIDTH/2, HEIGHT-OFFSET_Y, EXECUTE_BUTTON_WIDTH, EXECUTE_BUTTON_HEIGHT);
        executeButton.setBackground(new Color(20,20,20));
        executeButton.setOpaque(true);

        executeButton.setForeground(Color.WHITE);
        executeButton.setFont(new Font("Monospaced", Font.BOLD, 25));

        executeButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if (!minutesInput.getText().matches("") && !settings.PRESSED) {
                    try {
                        settings.PRESSED = true;
                        AutoClicker clicker;
                        int minutes = Integer.parseInt(minutesInput.getText());
                        settings.CLICK_TIME_IN_MINUTES = minutes;
                        clicker = new AutoClicker(settings, timePassed);
                    }catch (Exception e1) {}
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                executeButton.setBackground(new Color(15,15,15));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                executeButton.setBackground(new Color(20,20,20));
            }
        });

        background.add(minutesInput);
        background.add(timePassed);
        background.add(autoWalkerBool);

        background.add(autoWalker);
        background.add(timeDisplay);
        background.add(timeLabel);
        background.add(executeButton);

        add(background);
        setVisible(true);
    }

    @Override 
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}