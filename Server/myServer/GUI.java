import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI implements ActionListener {
    private String id, name, year;
    private JTextField nameTF, idTF, yearTF;

    private boolean STATUS = false;

    public GUI() {
        initGUI();
    }

    private void initGUI() {
        var frame = new JFrame();
        var panel = new JPanel();

        frame.setSize(640, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        // Name
        var name = new JLabel("Name");
        name.setBounds(20, 40, 80, 25);
        panel.add(name);

        nameTF = new JTextField();
        nameTF.setBounds(80, 40, 160, 25);
        panel.add(nameTF);

        // ID
        var id = new JLabel("ID");
        id.setBounds(20, 70, 80, 25);
        panel.add(id);

        idTF = new JTextField();
        idTF.setBounds(80, 70, 160, 25);
        panel.add(idTF);

        // Year
        var year = new JLabel("Year");
        year.setBounds(20, 100, 80, 25);
        panel.add(year);

        yearTF = new JTextField();
        yearTF.setBounds(80, 100, 160, 25);
        panel.add(yearTF);

        // Button
        var enterBtn = new JButton("Enter");
        enterBtn.setBounds(115, 140, 70, 25);
        enterBtn.addActionListener(this);
        panel.add(enterBtn);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        name = nameTF.getText();
        id = idTF.getText();
        year = yearTF.getText();

        STATUS = true;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }

    public String getYear() {
        return year;
    }

    public boolean getStatus() {
        return STATUS;
    }

}
