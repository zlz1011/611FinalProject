import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

public class ManagerLoginFrame extends JFrame {
    private JTextField managerNameField;
    private JPasswordField passwordField;
    private JButton SignOnButton;
    public ManagerLoginFrame(){
        this.setTitle("Manager Log in");
        this.setLayout(new GridLayout(3,2));
        this.setSize(400,150);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel name = new JLabel("Manager ID: ",SwingConstants.RIGHT);
        this.add(name);
        this.managerNameField=new JTextField(20);
        this.add(this.managerNameField);

        JLabel passwordText = new JLabel("Password: ",SwingConstants.RIGHT);
        this.add(passwordText);
        this.passwordField=new JPasswordField(20);
        this.add(this.passwordField);

        this.add(new JPanel());
        this.SignOnButton=new JButton("Log In");
        this.SignOnButton.setForeground(Color.blue);
        this.add(this.SignOnButton);

        this.SignOnButton.addActionListener(new ManagerLogInListener());
        this.setVisible(true);
    }
    class ManagerLogInListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            File file = new File(GetData.createFilePath("manager_list.txt"));
            if (!file.exists()){
                JOptionPane tem=new JOptionPane();
                tem.showMessageDialog(null,"no manager_list.txt");
                return;
            }
            ArrayList<String[]> manager_list =GetData.read(GetData.createFilePath("manager_list.txt"),true);
            boolean flag=false;
            String managerID=managerNameField.getText();
            String password=new String(passwordField.getPassword());
            for (String[] ins:manager_list){
                if(ins[0].equals(managerID)&&ins[1].equals(password)){
                    flag=true;
                    ManagerSelectOperationFrame msof=new ManagerSelectOperationFrame(ins[0]);
                    break;
                }
            }
            if(!flag) {
                JOptionPane tem=new JOptionPane();
                tem.showMessageDialog(null,"wrong ID or Password!");
                ManagerLoginFrame frame = new ManagerLoginFrame();
            }
            ManagerLoginFrame.this.dispose();
        }
    }
}
