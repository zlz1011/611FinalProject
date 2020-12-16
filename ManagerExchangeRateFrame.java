/**
 * create a frame which manager can modify the exchange rate of a currency to USD
 */

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

public class ManagerExchangeRateFrame extends JFrame {
    private ManagerSelectOperationFrame fatherFrame;
    private JTextField moneyField;
    private JTextArea resultField;
    private JButton addButton;
    private JComboBox<String> currencyNameList;

    public ManagerExchangeRateFrame(ManagerSelectOperationFrame fatherFrame){
        this.fatherFrame = fatherFrame;
        this.setTitle("exchange rates");
        this.setLayout(new BorderLayout(5, 5));
        this.setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel moneyLabel = new JLabel(":USD = ", SwingConstants.LEFT);
        JLabel rateLabel = new JLabel(":1", SwingConstants.LEFT);
        moneyField = new JTextField(10);

        addButton = new JButton("set exchange rate");
        String[] currencyName = new String[]{"EUR", "GBP", "CNY", "AUD"};
        currencyNameList = new JComboBox<String>(currencyName);
        addButton.addActionListener(new exchangeListener());
        topPanel.add(currencyNameList);
        topPanel.add(moneyLabel);
        topPanel.add(moneyField);
        topPanel.add(rateLabel);
        topPanel.add(addButton);

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
                ManagerExchangeRateFrame.this.dispose();
            }
        });
        show(getMoneyList());
        this.setVisible(true);
    }
    private ArrayList<String[]> getMoneyList() {
        File file = new File(GetData.createFilePath("exchange_rate.txt"));
        if (!file.exists()) {
            JOptionPane tem = new JOptionPane();
            tem.showMessageDialog(null, "no exchange_rate.txt");
            return null;
        }
        ArrayList<String[]> resultlist = GetData.read(GetData.createFilePath("exchange_rate.txt"), false);
        return resultlist;
    }

    private void show(ArrayList<String[]> resultlist) {
        ManagerExchangeRateFrame.this.resultField.setText("");
        for (String[] ins : resultlist) {
            for (String s : ins) {
                ManagerExchangeRateFrame.this.resultField.append(s + "\t");
            }
            ManagerExchangeRateFrame.this.resultField.append("\n");
        }
    }

    class exchangeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String money, type;
            money = ManagerExchangeRateFrame.this.moneyField.getText();
            if(!money.matches("[0-9]+[.]?[0-9]*")){
                JOptionPane.showMessageDialog(null, "Please input a valid number!");
                return;
            }
            double b1=Double.valueOf(money);
            type = ManagerExchangeRateFrame.this.currencyNameList.getItemAt(ManagerExchangeRateFrame.this.currencyNameList.getSelectedIndex());
            if (money.equals("") || type.equals("")) {
                JOptionPane tem = new JOptionPane();
                tem.showMessageDialog(null, "can not be empty");
                return;
            } else {
                ArrayList<String[]> temlist = getMoneyList();
                try {
                    File file = new File(GetData.createFilePath("exchange_rate.txt"));
                    FileWriter fileWriter = new FileWriter(file);
                    boolean flag=true;
                    for (String[] abc : temlist) {
                        if (abc[0].equals(type)) {
                            if(e.getSource()==addButton){
                                DecimalFormat df = new DecimalFormat("#0.0000");
                                String newmoney=df.format(b1);
                                fileWriter.write(abc[0] + "\t" + newmoney + "\n");
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