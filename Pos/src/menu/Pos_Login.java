package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import default_frame.PosFrame;
import frame_utility.BackgroundTooll;
import frame_utility.ButtonTool;

public class Pos_Login {
	public Pos_Login() {
		JFrame frame = new JFrame();  // JFrame 생성
		   frame.setFocusable(true);
	       // 배경 이미지 세팅
	       BackgroundTooll background = new BackgroundTooll("images/menu_Login.png");
	       background.setLayout(null);
	       String logoImagePath = "images/icon/mainLogo.png";
	       		
	       // Book Store 로고
	       BufferedImage img = null;
	        try {
	            img = ImageIO.read(new File(logoImagePath));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        Image logo = img.getScaledInstance(350, 100, Image.SCALE_SMOOTH);
	       
	       ImageIcon logoIcon = new ImageIcon(logo);
	       
	       JLabel logoLabel = new JLabel(logoIcon);

	       logoLabel.setSize(350, 100);
	       logoLabel.setLocation(550, 250);
	       background.add(logoLabel);
	       
	       // User Name, Pass Word 폰트
	       Font userName = new Font("돋음",Font.BOLD, 25);
	       
	       JLabel username = new JLabel("User Name");
	       username.setFont(userName);
	       username.setBounds(525,325,180,80);
	       username.setForeground(Color.BLACK);
	       background.add(username);
	       
	       JLabel password = new JLabel("Pass Word");
	       password.setFont(userName);
	       password.setBounds(525,455,180,80);
	       password.setForeground(Color.BLACK);
	       background.add(password);
	       
	       // ID 입력 textfield
	       RoundedTextField userId = new RoundedTextField(15,"amfkas@Bookstore.com",50,50);
	       userId.setBounds(525,415,400,40);
	       
	       background.add(userId);
	       
	       // password 입력 passwordfield
	       RoundedPasswordField userPassword = new RoundedPasswordField(15,"•••••••••••••");
	       userPassword.setBounds(525,525,400,40);
	       background.add(userPassword);
	       
	       ButtonTool Login = ButtonTool.createButton("로그인", new Color(22, 40, 80),Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 18),50,50,true);
	       Login.setBounds(525,595,400,40);
	       background.add(Login);
	       
	       ButtonTool Sign = ButtonTool.createButton("회원가입", new Color(22, 40, 80), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 18),50,50,true);
	       Sign.setBounds(525,645,400,40);
	       background.add(Sign);
	       
	       Sign.addMouseListener(new MouseAdapter() {
	    	   @Override
		       public void mouseClicked(MouseEvent e) {
	    		   new Pos_Sign();
		       }
		   });

	        // 로그인 버튼 기능 할당
	       Login.addMouseListener(new MouseAdapter() {
	           public void mouseClicked(java.awt.event.MouseEvent e) {
		        	String username = userId.getText().trim();
		            String password = new String(userPassword.getPassword());
	
		            // ex 아디 admin, 비번 12345
		            if (username.equals("admin") && password.equals("12345")) {
		                //JOptionPane.showMessageDialog(null, "로그인 성공!");
		            	PosFrame pos = new PosFrame();
		        		pos.getButton(0).setBackground(new Color(79, 163, 252));
		        		pos.getFrame().setVisible(true);
		                // 여기서 로그인 성공 후 다음 화면으로 전환하는 코드를 추가.
		            } else {
		                JOptionPane.showMessageDialog(null, "로그인 실패. 다시 시도하세요.");
		            }
	
		              
		            userId.setText("");
		            userPassword.setText("");
	           };
			});

	       
	       frame.add(background);
	       frame.setResizable(false);
	       frame.setContentPane(background);
	       frame.setSize(1440, 1024);
	       frame.setLocationRelativeTo(null); 
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setVisible(true);
	}
}
