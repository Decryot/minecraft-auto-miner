import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

public class AutoClicker {
    int minutesInMS;
    int minutes, timePassed = 1;
    Robot robot;

    JLabel timePass;
    Settings settings;

    public AutoClicker(Settings settings, JLabel seconds) {
        minutesInMS = 1000;
        minutes = settings.CLICK_TIME_IN_MINUTES;
        timePass = seconds;
        this.settings = settings;

        try {
            robot = new Robot();
            click();
        }catch (Exception e) {}
    }

    void click() {
        try {
        Thread.sleep(5000);
        }catch (Exception e) {}

        try {
            for (int i = 0; i < minutes*60; i++) {
                timePass.setText(timePassed + "");
                robot.mousePress(16);
                if (settings.AUTO_WALK) {
                    robot.keyPress(KeyEvent.VK_W);
                }
                robot.delay(minutesInMS);
                timePassed++;
            }
            robot.mouseRelease(16);
            robot.keyRelease(KeyEvent.VK_W);
            robot.delay(1);
            timePass.setText("0:00");
            settings.PRESSED = false;
        }catch (Exception e) {}
    }
}