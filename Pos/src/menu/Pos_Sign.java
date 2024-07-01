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

import frame_utility.BackgroundTooll;
import frame_utility.ButtonTool;
import frame_utility.Utility;
import menu.pos_member.dto.MembersDTO;
import menu.pos_member.query.MembersQuery;

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
	       logoLabel.setLocation(550, 70);
	       background.add(logoLabel);
	       
	       // User Name, Pass Word 폰트
	       Font defaultFont = new Font("돋음",Font.BOLD, 25);
	       
	       JLabel username = new JLabel("ID");
	       username.setFont(defaultFont);
	       username.setBounds(525,150,180,80);
	       username.setForeground(Color.BLACK);
	       background.add(username);
	       
	       JLabel password = new JLabel("Pass Word");
	       password.setFont(defaultFont);
	       password.setBounds(525,245,180,80);
	       password.setForeground(Color.BLACK);
	       background.add(password);
	       
	       JLabel name = new JLabel("User Name");
	       name.setFont(defaultFont);
	       name.setBounds(525,395,180,80);
	       name.setForeground(Color.BLACK);
	       background.add(name);
	       
	       JLabel adress = new JLabel("Adress");
	       adress.setFont(defaultFont);
	       adress.setBounds(525,485,180,80);
	       adress.setForeground(Color.BLACK);
	       background.add(adress);
	       
	       JLabel detailadress = new JLabel("Detailed address");
	       detailadress.setFont(defaultFont);
	       detailadress.setBounds(525,570,250,80);
	       detailadress.setForeground(Color.BLACK);
	       background.add(detailadress);
	       
	       JLabel phoneNumber = new JLabel("Phone Number");
	       phoneNumber.setFont(defaultFont);
	       phoneNumber.setBounds(525,665,180,80);
	       phoneNumber.setForeground(Color.BLACK);
	       background.add(phoneNumber);
	       
	       Font announcementFont = new Font("돋음",Font.BOLD, 15);
	       
	       JLabel announcement = new JLabel("관리자 승인후 사용가능합니다.");
	       announcement.setFont(announcementFont);
	       announcement.setBounds(615,815,300,80);
	       announcement.setForeground(Color.GRAY);
	       background.add(announcement);
	       
	       MembersDTO dto = new MembersDTO();
	      
	       // ID 입력
	       RoundedTextField userId = new RoundedTextField(30,"amfkas@Bookstore.com",50,50);
	       userId.setBounds(525,220,400,40);	       	       
	       background.add(userId);
	       
	       // password 입력
	       RoundedPasswordField userPassword = new RoundedPasswordField(30,"***********");
	       userPassword.setBounds(525,315,400,40);
	       background.add(userPassword);
	       
	       // password 확인
	       RoundedPasswordField checkPassword = new RoundedPasswordField(30,"비밀번호를 다시 입력해주세요.");
	       checkPassword.setBounds(525,365,400,40);
	       background.add(checkPassword);
	       
	       // userName 입력
	       RoundedTextField userName = new RoundedTextField(30,"이름을 입력해주세요.",50,50);	       
	       userName.setBounds(525,460,400,40);	       
	       background.add(userName);
	       
	       RoundedTextField userAdress = new RoundedTextField(30,"주소를 입력해주세요.",50,50);	       
	       userAdress.setBounds(525,550,400,40);	       
	       background.add(userAdress);
	       
	       RoundedTextField userDetailAdress = new RoundedTextField(30,"상세주소를 입력해주세요.",50,50);	       
	       userDetailAdress.setBounds(525,640,400,40);	       
	       background.add(userDetailAdress);
	       
	       RoundedTextField userPhoneNumber = new RoundedTextField(30,"핸드폰 번호를 입력해주세요.",50,50);	       
	       userPhoneNumber.setBounds(525,730,400,40);	       
	       background.add(userPhoneNumber);
	       
	       // 회원가입 버튼
	       ButtonTool Sign = ButtonTool.createButton("회원가입", new Color(22, 40, 80), Color.WHITE, new Color(79, 163, 252),new Font("돋음", Font.BOLD, 18),50,50,true);
	       Sign.setBounds(525,790,400,40);
	       background.add(Sign);
	       
	       Sign.addMouseListener(new MouseAdapter() {
	    	   @Override
	    	public void mouseReleased(MouseEvent e) {
	    		   Utility u = new Utility();
	    	       dto.setMemberId(userId.getText());
	    	       dto.setPassword(new String(userPassword.getPassword()));
	    	       dto.setName(userName.getText());
	    	       dto.setAddress(userAdress.getText());
	    	       dto.setDetailedAddress(userDetailAdress.getText());
	    	       dto.setDetailedAddress(userDetailAdress.getText());
	    	       dto.setPhoneNumber(userPhoneNumber.getText());

	    		MembersQuery query = new MembersQuery();
	    	    int result = query.createMember(dto);
		    	    if(result == 0) {
		    	    	u.popup("회원가입 실패 다시 시도해주세요.", frame,false);

		    	    }else {
		    	    	u.popup("회원가입이 완료되었습니다.", frame,true);
		    	    }
	    	}
		});

	       frame.add(background);
	       frame.setResizable(false);
	       frame.setContentPane(background);
	       frame.setSize(1440, 1024);
	       frame.setLocationRelativeTo(null); 
	       //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setVisible(true);
	}
}
