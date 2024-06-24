package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import frame_utility.BackgroundTooll;
import frame_utility.ButtonTool;

public class Pos_Sign {
	public Pos_Sign() {
		
		JFrame frame = new JFrame();  // JFrame 생성
		   frame.setFocusable(true);
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
	       logoLabel.setLocation(550, 150);
	       background.add(logoLabel);
	       
	       // User Name, Pass Word 폰트
	       Font defaultFont = new Font("돋음",Font.BOLD, 25);
	       
	       JLabel username = new JLabel("User Name");
	       username.setFont(defaultFont);
	       username.setBounds(525,245,180,80);
	       username.setForeground(Color.BLACK);
	       background.add(username);
	       
	       JLabel password = new JLabel("Pass Word");
	       password.setFont(defaultFont);
	       password.setBounds(525,350,180,80);
	       password.setForeground(Color.BLACK);
	       background.add(password);
	       
	       JLabel affiliation = new JLabel("Affiliation");
	       affiliation.setFont(defaultFont);
	       affiliation.setBounds(525,500,180,80);
	       affiliation.setForeground(Color.BLACK);
	       background.add(affiliation);
	       
	       Font announcementFont = new Font("돋음",Font.BOLD, 15);
	       
	       JLabel announcement = new JLabel("관리자 승인후 사용가능합니다.");
	       announcement.setFont(announcementFont);
	       announcement.setBounds(615,700,300,80);
	       announcement.setForeground(Color.GRAY);
	       background.add(announcement);
	       
	    // ID 입력
	       RoundedTextField userId = new RoundedTextField(15,"amfkas@Bookstore.com",50,50);
	       userId.setBounds(525,315,400,40);
	       
	       background.add(userId);
	       
	       // password 입력
	       RoundedPasswordField userPassword = new RoundedPasswordField(15,"•••••••••••••");
	       userPassword.setBounds(525,415,400,40);
	       background.add(userPassword);
	       
	       // password 확인
	       RoundedPasswordField checkPassword = new RoundedPasswordField(15,"비밀번호를 다시 입력해주세요.");
	       checkPassword.setBounds(525,465,400,40);
	       background.add(checkPassword);
	       
	       // affiliation 입력
	       RoundedTextField userAffiliation = new RoundedTextField(15,"소속을 입력해주세요.",50,50);
	       userAffiliation.setBounds(525,565,400,40);	       
	       background.add(userAffiliation);
	       
	       // 회원가입 버튼
	       ButtonTool Sign = ButtonTool.createButton("회원가입", new Color(22, 40, 80), Color.WHITE, new Font("돋음", Font.BOLD, 18),50,50);
	       Sign.setBounds(525,645,400,40);
	       background.add(Sign);

	       
	       frame.add(background);
	       frame.setResizable(false);
	       frame.setContentPane(background);
	       frame.setSize(1440, 1024);
	       frame.setLocationRelativeTo(null); 
	       //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setVisible(true);
	}
}
