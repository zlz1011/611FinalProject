/**
 * create a frame which manager can check the list of managers and add or delete a manager, however a manager can not delete himself/herself
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ManagerEditMemberFrame extends JFrame {
    private ManagerSelectOperationFrame fatherFrame;
    private JButton addButton, deleteButton;
    private JTextField addIDField, addPasswordField, deleteIDField;
    private JTextArea resultField;
    private String currentManager;

    public ManagerEditMemberFrame(ManagerSelectOperationFrame fatherFrame, String currentManager) {
        this.fatherFrame = fatherFrame;
        this.currentManager = currentManager;

        this.setTitle("edit managers");
        this.setLayout(new BorderLayout(5, 5));
        this.setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new GridLayout(3, 5));
        JLabel addIDLabel = new JLabel("managerID:", SwingConstants.RIGHT);
        addIDField = new JTextField(20);
        JLabel addPasswordLabel = new JLabel("password:", SwingConstants.RIGHT);
        addPasswordField = new JTextField(20);
        addButton = new JButton("add manager");
        addButton.addActionListener(new addListener());
        topPanel.add(addIDLabel);
        topPanel.add(addIDField);
        topPanel.add(addPasswordLabel);
        topPanel.add(addPasswordField);
        topPanel.add(addButton);
        JLabel deleteIDLabel = new JLabel("managerID:", SwingConstants.RIGHT);
        deleteIDField = new JTextField(20);
        deleteButton = new JButton("delete manager");
        deleteButton.addActionListener(new deleteListener());
        topPanel.add(deleteIDLabel);
        topPanel.add(deleteIDField);
        topPanel.add(new JPanel());
        topPanel.add(new JPanel());
        topPanel.add(deleteButton);
        JLabel infoLabel = new JLabel("Current manager list:", SwingConstants.LEFT);
        topPanel.add(infoLabel);
        topPanel.add(new JPanel());
        topPanel.add(new JPanel());
        topPanel.add(new JPanel());
        topPanel.add(new JPanel());

        this.resultField = new JTextArea();
        this.resultField.setEditable(false);
        JScrollPane jsp = new JScrollPane(resultField);
        jsp.setSize(500, 340);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.setSize(800, 500);
        this.add("North", topPanel);
        this.add("Center", jsp);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fatherFrame.setEnabled(true);
                ManagerEditMemberFrame.this.dispose();
            }
        });
        show(getManagerList());
        this.setVisible(true);
    }

    private ArrayList<String[]> getManagerList() {
        File file = new File(GetData.createFilePath("manager_list.txt"));
        if (!file.exists()) {
            JOptionPane tem = new JOptionPane();
            tem.showMessageDialog(null, "no manager_list.txt");
            return null;
        }
        ArrayList<String[]> resultlist = GetData.read(GetData.createFilePath("manager_list.txt"), false);
        return resultlist;
    }

    private void show(ArrayList<String[]> resultlist) {
        ManagerEditMemberFrame.this.resultField.setText("");
        for (String[] ins : resultlist) {
            for (String s : ins) {
                ManagerEditMemberFrame.this.resultField.append(s + "\t");
            }
            ManagerEditMemberFrame.this.resultField.append("\n");
        }
    }

    class addListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String id, password;
            id = ManagerEditMemberFrame.this.addIDField.getText();
            password = ManagerEditMemberFrame.this.addPasswordField.getText();
            if (id.equals("") || password.equals("")) {
                JOptionPane tem = new JOptionPane();
                tem.showMessageDialog(null, "can not be empty");
                return;
            } else {
                ArrayList<String[]> temlist = getManagerList();
                for (String[] ins : temlist) {
                    if (ins[0].equals(id)) {
                        JOptionPane tem = new JOptionPane();
                        tem.showMessageDialog(null, "managerID already exists!");
                        return;
                    }
                }
                try {
                    File file = new File(GetData.createFilePath("manager_list.txt"));
                    FileWriter fileWriter = new FileWriter(file, true);
                    fileWriter.write(id + " " + password + "\n");
                    fileWriter.close();
                    show(getManagerList());
                } catch (IOException h) {
                    h.printStackTrace();
                }
            }
        }
    }

    class deleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String id;
            id = ManagerEditMemberFrame.this.deleteIDField.getText();
            if (id.equals("")) {
                JOptionPane tem = new JOptionPane();
                tem.showMessageDialog(null, "can not be empty");
                return;
            } else if (id.equals(ManagerEditMemberFrame.this.currentManager)) {
                JOptionPane tem = new JOptionPane();
                tem.showMessageDialog(null, "can not delete current manager!");
                return;
            } else {
                ArrayList<String[]> temlist = getManagerList();
                for (String[] ins : temlist) {
                    if (ins[0].equals(id)) {
                        try {
                            File file = new File(GetData.createFilePath("manager_list.txt"));
                            FileWriter fileWriter = new FileWriter(file);
                            for (String[] abc : temlist) {
                                if (abc[0].equals(id)) continue;
                                fileWriter.write(abc[0] + " " + abc[1] + "\n");
                            }
                            fileWriter.close();
                            show(getManagerList());
                        } catch (IOException h) {
                            h.printStackTrace();
                        }
                        return;
                    }
                }
                JOptionPane tem = new JOptionPane();
                tem.showMessageDialog(null, "no such managerID");
            }
        }
    }
}
