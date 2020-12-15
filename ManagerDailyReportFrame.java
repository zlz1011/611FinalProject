import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

public class ManagerDailyReportFrame extends JFrame {
    private ManagerSelectOperationFrame fatherFrame;
    private JButton todayButton,anydayButton;
    private JTextField dateField;
    private JTextArea resultField;
    ManagerDailyReportFrame(ManagerSelectOperationFrame fatherFrame){
        this.fatherFrame = fatherFrame;

        this.setTitle("check daily report");
        this.setLayout(new BorderLayout(5, 5));
        this.setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new GridLayout(2,2));
        JLabel todayLabel = new JLabel("Today is: "+GetDate.currentDate(), SwingConstants.RIGHT);
        todayButton = new JButton("Today's report");
        dateField = new JTextField(20);
        anydayButton = new JButton("report of yyyy-MM-dd");
        this.todayButton.addActionListener(new todayListener());
        this.anydayButton.addActionListener(new anydayListener());
        topPanel.add(todayLabel);
        topPanel.add(todayButton);
        topPanel.add(dateField);
        topPanel.add(anydayButton);

        this.resultField = new JTextArea();
        this.resultField.setEditable(false);
        JScrollPane jsp = new JScrollPane(resultField);
        jsp.setSize( 500, 340);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.setSize(800, 500);
        this.add("North", topPanel);
        this.add("Center", jsp);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fatherFrame.setEnabled(true);
                ManagerDailyReportFrame.this.dispose();
            }
        });
        this.setVisible(true);
    }
    class todayListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ManagerDailyReportFrame.this.resultField.setText("");
            File file = new File(GetData.createFilePath(GetDate.currentDate()+"_report.txt"));
            if (!file.exists()){
                JOptionPane tem=new JOptionPane();
                tem.showMessageDialog(null,"no today's report");
                return;
            }
            ArrayList<String> resultlist =GetData.readlines(GetData.createFilePath(GetDate.currentDate()+"_report.txt"),false);
            for(String ins:resultlist){
                ManagerDailyReportFrame.this.resultField.append(ins+"\n");
            }
        }
    }
    class anydayListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ManagerDailyReportFrame.this.resultField.setText("");
            String targetDate=ManagerDailyReportFrame.this.dateField.getText();
            File file = new File(GetData.createFilePath(targetDate+"_report.txt"));
            if (!file.exists()){
                JOptionPane tem=new JOptionPane();
                tem.showMessageDialog(null,"no target date's report");
                return;
            }
            ArrayList<String> resultlist =GetData.readlines(GetData.createFilePath(targetDate+"_report.txt"),false);
            for(String ins:resultlist){
                ManagerDailyReportFrame.this.resultField.append(ins+"\n");
            }
        }
    }
}
