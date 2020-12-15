import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

public class ManagerCheckCustomerFrame extends JFrame {
    private ManagerSelectOperationFrame fatherFrame;
    private JTextField customerIDField;
    private JTextArea resultField;
    private JButton searchButton,showallButton;

    public ManagerCheckCustomerFrame(ManagerSelectOperationFrame fatherFrame) {
        this.fatherFrame = fatherFrame;

        this.setTitle("check customer information");
        this.setLayout(new BorderLayout(5, 5));
        this.setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel enterLabel = new JLabel("Enter the id of customer:", SwingConstants.RIGHT);
        customerIDField = new JTextField(20);
        searchButton = new JButton("Search");
        this.searchButton.addActionListener(new searchListener());
        showallButton = new JButton("show all customers");
        this.showallButton.addActionListener(new showallListener());
        topPanel.add(enterLabel);
        topPanel.add(customerIDField);
        topPanel.add(searchButton);
        topPanel.add(showallButton);

        this.resultField = new JTextArea();
        this.resultField.setEditable(false);
        JScrollPane jsp = new JScrollPane(resultField);
        jsp.setBounds(13, 10, 500, 340);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.add("North", topPanel);
        this.add("Center", jsp);
        this.setSize(800, 400);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fatherFrame.setEnabled(true);
                ManagerCheckCustomerFrame.this.dispose();
            }
        });
        this.setVisible(true);
    }

    class searchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ManagerCheckCustomerFrame.this.resultField.setText("");
            String filename=ManagerCheckCustomerFrame.this.customerIDField.getText();
            File file = new File(GetData.createFilePath("info_"+filename+".txt"));
            if (!file.exists()){
                JOptionPane tem=new JOptionPane();
                tem.showMessageDialog(null,"no such customer!");
                return;
            }
            ArrayList<String> resultlist =GetData.readlines(GetData.createFilePath("info_"+filename+".txt"),false);
            for(String ins:resultlist){
                ManagerCheckCustomerFrame.this.resultField.append(ins+"\n");
            }
        }
    }
    class showallListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ManagerCheckCustomerFrame.this.resultField.setText("");
            File file = new File(GetData.createFilePath("info.txt"));
            if (!file.exists()){
                JOptionPane tem=new JOptionPane();
                tem.showMessageDialog(null,"no info.txt");
                return;
            }
            ArrayList<String[]> resultlist =GetData.read(GetData.createFilePath("info.txt"),false);
            String headline="userID\tname\tpassword\tsavings\tcurrency\tcheckings\tcurrency\tloan\tcurrency";
            ManagerCheckCustomerFrame.this.resultField.append(headline+"\n\n");
            for(String[] ins:resultlist){
                for(String s:ins){
                    ManagerCheckCustomerFrame.this.resultField.append(s+"\t");
                }
                ManagerCheckCustomerFrame.this.resultField.append("\n");
            }
        }
    }
}
