import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;

public class ManagerBankMoneyFrame extends JFrame {
    private ManagerSelectOperationFrame fatherFrame;
    private JTextField moneyField;
    private JTextArea resultField;
    private JButton addButton, minusButton;
    private JComboBox<String> currencyNameList;

    public ManagerBankMoneyFrame(ManagerSelectOperationFrame fatherFrame) {
        this.fatherFrame = fatherFrame;
        this.setTitle("all funds");
        this.setLayout(new BorderLayout(5, 5));
        this.setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel moneyLabel = new JLabel("amount of money:", SwingConstants.RIGHT);
        moneyField = new JTextField(20);

        addButton = new JButton("add money");
        minusButton = new JButton("minus money");
        String[] currencyName = new String[]{"USD", "EUR", "GBP", "CNY", "AUD"};
        currencyNameList = new JComboBox<String>(currencyName);
        addButton.addActionListener(new moneyListener());
        minusButton.addActionListener(new moneyListener());
        topPanel.add(moneyLabel);
        topPanel.add(moneyField);
        topPanel.add(currencyNameList);
        topPanel.add(addButton);
        topPanel.add(minusButton);

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
                ManagerBankMoneyFrame.this.dispose();
            }
        });
        show(getMoneyList());
        this.setVisible(true);
    }

    private ArrayList<String[]> getMoneyList() {
        File file = new File(GetData.createFilePath("bank_money.txt"));
        if (!file.exists()) {
            JOptionPane tem = new JOptionPane();
            tem.showMessageDialog(null, "no bank_money.txt");
            return null;
        }
        ArrayList<String[]> resultlist = GetData.read(GetData.createFilePath("bank_money.txt"), false);
        return resultlist;
    }

    private void show(ArrayList<String[]> resultlist) {
        ManagerBankMoneyFrame.this.resultField.setText("");
        for (String[] ins : resultlist) {
            for (String s : ins) {
                ManagerBankMoneyFrame.this.resultField.append(s + "\t");
            }
            ManagerBankMoneyFrame.this.resultField.append("\n");
        }
    }

    class moneyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String money, type;
            money = ManagerBankMoneyFrame.this.moneyField.getText();
            if(!money.matches("[0-9]+[.]?[0-9]*")){
                JOptionPane.showMessageDialog(null, "Please input a valid number!");
                return;
            }
            double b1=Double.valueOf(money);
            type = ManagerBankMoneyFrame.this.currencyNameList.getItemAt(ManagerBankMoneyFrame.this.currencyNameList.getSelectedIndex());
            if (money.equals("") || type.equals("")) {
                JOptionPane tem = new JOptionPane();
                tem.showMessageDialog(null, "can not be empty");
                return;
            } else {
                ArrayList<String[]> temlist = getMoneyList();
                try {
                    File file = new File(GetData.createFilePath("bank_money.txt"));
                    FileWriter fileWriter = new FileWriter(file);
                    boolean flag=true;
                    for (String[] abc : temlist) {
                        if (abc[1].equals(type)) {
                            if(e.getSource()==addButton){
                                b1=b1+Double.valueOf(abc[0]);

                                //use double or int
                                //DecimalFormat df = new DecimalFormat("#.00");
                                //String newmoney=df.format(b1);
                                String newmoney=String.valueOf((int)b1);

                                fileWriter.write(newmoney + "\t" + abc[1] + "\n");
                            }else if(e.getSource()==minusButton){
                                if(b1>Double.valueOf(abc[0])){
                                    JOptionPane.showMessageDialog(null, "can not minus more than have!");
                                    return;
                                }
                                b1=Double.valueOf(abc[0])-b1;

                                //use double or int
                                //DecimalFormat df = new DecimalFormat("#.00");
                                //String newmoney=df.format(b1);
                                String newmoney=String.valueOf((int)b1);

                                fileWriter.write(newmoney + "\t" + abc[1] + "\n");
                            }
                            flag=false;
                        }else{
                            fileWriter.write(abc[0] + "\t" + abc[1] + "\n");
                        }
                    }
                    fileWriter.close();
                    show(getMoneyList());
                    if(flag)JOptionPane.showMessageDialog(null, "No such kind of currency!");
                } catch (IOException h) {
                    h.printStackTrace();
                }
            }
        }
    }
}
