import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Transaction extends Component implements ActionListener {
    JFrame frame;
    JLabel lblMsg;
    JTextField txtDeposit,txtCheckBalance,txtWithdraw;
    JButton btnDeposit,btnCheckBalance,btnWithdraw,btnOk;
    JDialog dialog;
    ArrayList<UserData> users;

    public Transaction(ArrayList<UserData> users) {
        this.users = users;
        frame = new JFrame();
        Toolkit toolkit = getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setLocation(dimension.width/2 - 380/2,dimension.height/2 - 400/2);
        frame.setSize(380,400);
        frame.setTitle("Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        txtDeposit = new JTextField();
        txtDeposit.setBounds(140,10,100,30);
        frame.add(txtDeposit);

        btnDeposit = new JButton("Deposit");
        btnDeposit.setBounds(130,50,120,30);
        btnDeposit.addActionListener(this);
        frame.add(btnDeposit);

        txtCheckBalance = new JTextField();
        txtCheckBalance.setBounds(140,110,100,30);
        txtCheckBalance.setEnabled(false);
        frame.add(txtCheckBalance);

        btnCheckBalance = new JButton("Check Balance");
        btnCheckBalance.setBounds(130,150,120,30);
        btnCheckBalance.addActionListener(this);
        frame.add(btnCheckBalance);

        txtWithdraw = new JTextField();
        txtWithdraw.setBounds(140,210,100,30);
        frame.add(txtWithdraw);

        btnWithdraw = new JButton("Withdraw");
        btnWithdraw.setBounds(130,250,120,30);
        btnWithdraw.addActionListener(this);
        frame.add(btnWithdraw);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UserData user = users.get(0);
        if(e.getSource() == btnDeposit){
            double balance = Double.parseDouble(txtDeposit.getText().trim());
            user.setBalance(balance);
        } else if (e.getSource() == btnCheckBalance) {
            double balance = user.getBalance();
            if(balance != 0){
                txtCheckBalance.setText(""+balance+"$");
            }
        } else if (e.getSource() == btnWithdraw) {
            double balance = user.getBalance();
            double withdrawAmount = Double.parseDouble(txtWithdraw.getText().trim());


            if(user.getType().equals("regular")){
                if(withdrawAmount < balance-(500.1)){
                    balance = balance - (withdrawAmount + 0.1);
                    balance = Math.round(balance * 100) / 100.0;
                    user.setBalance(balance);
                    txtCheckBalance.setText(""+balance+"$");
                }else{
                    if(dialog!=null){
                        dialog.dispose();
                    }
                    showDialog("Balance is low!!!");
                }
            }else {
                if(withdrawAmount <= balance){
                    balance = balance - withdrawAmount;
                    user.setBalance(balance);
                    txtCheckBalance.setText(""+balance+"$");
                }else{
                    if(dialog!=null){
                        dialog.dispose();
                    }
                    showDialog("Balance is low!!!");
                }
            }
            user.setBalance(balance);
        } else if (e.getSource()==btnOk) {
            if(dialog!=null){
                dialog.dispose();
            }
        }
    }

    public void showDialog(String msg) {
        dialog = new JDialog(frame,"Warning");
        Toolkit toolkit = getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        dialog.setLocation(dimension.width/2 - 200/2,dimension.height/2 - 100/2);
        dialog.setSize(200,100);
        dialog.setLayout(null);
        dialog.setResizable(false);

        lblMsg = new JLabel(msg);
        lblMsg.setBounds(35,10,150,20);
        dialog.add(lblMsg);

        btnOk = new JButton("Ok");
        btnOk.setBounds(70,30,50,20);
        btnOk.addActionListener(this);
        dialog.add(btnOk);

        dialog.setVisible(true);
    }
}
