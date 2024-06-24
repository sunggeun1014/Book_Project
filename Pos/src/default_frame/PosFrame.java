package default_frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frame_utility.utility;

public class PosFrame {
	
	static int screenWidth = 1440;
	static int screenHight = 1024;
	
	private JFrame frame;
	private static JPanel[] buttons = new JPanel[7];
	
	public PosFrame() {
		frame = new JFrame("POS");

        String mainImagePath = "images/main_frame.png";
        String logoImagePath = "images/icon/mainLogo.png";

        ImageIcon mainIcon = new ImageIcon(mainImagePath);
        ImageIcon logoIcon = new ImageIcon(new ImageIcon(logoImagePath).getImage().getScaledInstance(180, 70, Image.SCALE_SMOOTH));
        
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(mainIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setSize(180, 80);
        logoLabel.setLocation(30, 10);
        
        logoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	logoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	logoLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            
        });
        
        buttons[0] = Addbutton("홈", "images/icon/menuHome.png", 30, 200);
        buttons[1] = Addbutton("회원관리", "images/icon/menuMember.png", 30, 260);
        buttons[2] = Addbutton("매출현황", "images/icon/menuMember.png", 30, 320);
        buttons[3] = Addbutton("발주내역", "images/icon/menuMember.png", 30, 380);
        buttons[4] = Addbutton("입고확인", "images/icon/menuMember.png", 30, 440);
        buttons[5] = Addbutton("도서관리", "images/icon/menuMember.png", 30, 500);
        buttons[6] = Addbutton("계산하기", "images/icon/menuMember.png", 30, 560);
        
        for (JPanel button : buttons) {
        	backgroundPanel.add(button);
        }
        backgroundPanel.setLayout(null);
        backgroundPanel.add(logoLabel);
        

        // JFrame 설정
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(backgroundPanel);
        frame.setSize(screenWidth, screenHight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
	}
	
	private static JPanel Addbutton(String name, String path, int x, int y) {
		JPanel button = new utility().getRoundShape(40, 40);
        
        button.setLayout(null);
        ImageIcon buttonImg = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        JLabel textLabel = new JLabel(name);
        JLabel imgLabel = new JLabel(buttonImg);
        
        imgLabel.setBounds(20, 7, 30, 25); 
        textLabel.setBounds(70, 9, 140, 25);
        
        textLabel.setFont(new Font("돋움", Font.BOLD, 15));
        textLabel.setForeground(Color.WHITE);
        button.add(imgLabel);
        button.add(textLabel);
        
        button.setBackground(new Color(22, 40, 80));
        button.setSize(220, 40);
        button.setLocation(x, y);
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
            	buttonChangeColor(button);
            }
            
        });
        
		return button;
	}
	
	private static void buttonChangeColor(JPanel button) {
		button.setBackground(new Color(79, 163, 252));
		for(JPanel btn : buttons) {
			if(btn.hashCode() != button.hashCode()) {
				btn.setBackground(new Color(22, 40, 80));
			}
		}
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public JPanel getButton(int index) {
		return buttons[index]; 
	}
	

}
