import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public interface Page extends ActionListener {

    void actionPerformed(ActionEvent e);
    JFrame getFrame();
}
