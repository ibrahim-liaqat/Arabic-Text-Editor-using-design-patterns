//package PL;
//
//import java.awt.FlowLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPasswordField;
//import javax.swing.JTextField;
//
//public class login {
//	   private JFrame frame;
//	    private JLabel userLabel;
//	    private JLabel passwordLabel;
//	    private JTextField userTextField;
//	    private JTextField passwordField;
//	    private JButton login;
//	    private JButton signup;
//	    login(){
//	    	frame=new JFrame();
//	    	userLabel=new JLabel("username");
//	    	passwordLabel=new JLabel("password");
//	    	userTextField=new JTextField(10);
//	    	passwordField=new JTextField(10);
//	    	login=new JButton("login");
//	    	signup=new JButton("Create new account");
//	    	frame.setLayout(new FlowLayout());
//	    	frame.setSize(900, 900);
//	    	frame.add(userLabel);
//	    	frame.add(userTextField);
//	    	frame.add(passwordLabel);
//	    	frame.add(passwordField);
//	    	frame.add(login);
//	    	frame.add(signup);
//	    	frame.setVisible(true);
//	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    	login.addActionListener(new ActionListener() {
//
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					// TODO Auto-generated method stub
//					if(userTextField.getText().equals("admin") && passwordField.getText().equals("123")) {
//				
//						new home();
//						frame.dispose();
//					}
//					else {
//				        JOptionPane.showMessageDialog(frame, "Try Again", "Invalid", JOptionPane.INFORMATION_MESSAGE);
//
//					}
//				}
//	    		
//	    	});
//	    	signup.addActionListener(new ActionListener() {
//
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					// TODO Auto-generated method stub
//					new signin();
//				}
//	    		
//	    	});
//	    }
//	   
//}
