import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Registration extends Component implements ActionListener {
    JFrame frame;
    JLabel lblName,lblEmail,lblPassword,lblDOB,lblType,lblMsg;
    JTextField txtName,txtEmail,txtDOB;
    JPasswordField txtPassword;
    JRadioButton rbPremium,rbRegular;
    ButtonGroup group;
    JButton btnRegister,btnOk;
    JDialog dialog;
    ArrayList<UserData> users;

    Registration(){
        frame = new JFrame();
        Toolkit toolkit = getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setLocation(dimension.width/2 - 380/2,dimension.height/2 - 400/2);
        frame.setSize(380,400);
        frame.setTitle("Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        lblName = new JLabel();
        lblName.setText("Name:");
        lblName.setBounds(10,10,100,30);
        frame.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(130,10,200,30);
        frame.add(txtName);

        lblEmail = new JLabel();
        lblEmail.setText("Email:");
        lblEmail.setBounds(10,50,100,30);
        frame.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(130,50,200,30);
        frame.add(txtEmail);

        lblPassword = new JLabel();
        lblPassword.setText("Password:");
        lblPassword.setBounds(10,90,100,30);
        frame.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(130,90,200,30);
        frame.add(txtPassword);

        lblDOB = new JLabel();
        lblDOB.setText("Date of birth:");
        lblDOB.setBounds(10,130,100,30);
        frame.add(lblDOB);

        txtDOB = new JTextField();
        txtDOB.setBounds(130,130,200,30);
        frame.add(txtDOB);

        lblType = new JLabel();
        lblType.setText("Type");
        lblType.setBounds(10,170,100,30);
        frame.add(lblType);

        rbRegular = new JRadioButton("Regular Customer");
        rbRegular.setBounds(130,170,300,20);
        frame.add(rbRegular);

        rbPremium = new JRadioButton("Premium Customer");
        rbPremium.setBounds(130,200,300,20);
        frame.add(rbPremium);

        group = new ButtonGroup();
        group.add(rbRegular);
        group.add(rbPremium);

        btnRegister = new JButton();
        btnRegister.setText("Register");
        btnRegister.setBounds(130,240,100,30);
        btnRegister.addActionListener(this);
        frame.add(btnRegister);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnRegister){
            String name = txtName.getText().trim();
            String email = txtEmail.getText().trim();
            String password = txtPassword.getText().trim();
            String DOB = txtDOB.getText().trim();
            String type = null;
            if(rbRegular.isSelected()){
                type = "regular";
            }else if(rbPremium.isSelected()){
                type = "premium";
            }
            if(name.equals("") || email.equals("") || password.equals("") || DOB.equals("")){
                if(dialog!=null){
                    dialog.dispose();
                }
                showDialog("Field can't be empty!!");
            } else{
                UserData user = new UserData();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setDOB(DOB);
                if(type != null){
                    user.setType(type);
                }
                users = new ArrayList<>();
                users.add(user);
                new Transaction(users);
                frame.dispose();
            }


        } else if(e.getSource() == btnOk){
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
