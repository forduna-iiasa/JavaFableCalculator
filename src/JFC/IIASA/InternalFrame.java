package JFC.IIASA;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
class InternalFrame extends JInternalFrame {

    // pass in the reference in the constructor
    public InternalFrame(final ReferenceExample refEg) {
        setPreferredSize(new Dimension(200, 200));
        setClosable(true);

        JButton addInternalFrameBtn = new JButton("Add Internal Frame");
        addInternalFrameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // use the reference here
                refEg.addInternalFrame();
            }
        });
        JPanel panel = new JPanel();
        panel.add(addInternalFrameBtn);
        getContentPane().add(panel);
        pack();
    }
}