package default_frame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.Pos_BookManagement;
import menu.Pos_HomePanel;
import menu.Pos_InventoryList;
import menu.Pos_MemberManagement;
import menu.Pos_OrderList;
import menu.Pos_Payment;
import menu.Pos_SaleStatus;

public class PosFrame {
	
	static int screenWidth = 1440;
	static int screenHight = 1024;
	
	private JFrame frame;
	private static JPanel[] buttons = new JPanel[7];
	private static JPanel cardPanel;
	private CardLayout cardLayout;
	
	
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
        
        buttons[0] = Addbutton("홈", "images/icon/menuHome.png", 30, 200, "homePanel");
        buttons[1] = Addbutton("회원관리", "images/icon/menuMember.png", 30, 260, "memberPanel");
        buttons[2] = Addbutton("매출현황", "images/icon/menuMember.png", 30, 320, "salesPanel");
        buttons[3] = Addbutton("발주내역", "images/icon/menuMember.png", 30, 380, "orderPanel");
        buttons[4] = Addbutton("입고확인", "images/icon/menuMember.png", 30, 440, "arrivalPanel");
        buttons[5] = Addbutton("도서관리", "images/icon/menuMember.png", 30, 500, "bookPanel");
        buttons[6] = Addbutton("계산하기", "images/icon/menuMember.png", 30, 560, "paymentPanel");
        
        for (JPanel button : buttons) {
        	backgroundPanel.add(button);
        }
        backgroundPanel.setLayout(null);
        backgroundPanel.add(logoLabel);
        
        // 다양한 색상의 카드패널 생성
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(300, 125, 1100, 850); // 위치와 크기 설정

        // 각 패널을 각페널에 띄우고 싶은 화면 구조를 만들어서 넣어주면 된다.
        cardPanel.add(new Pos_HomePanel().createHomePanel(), "homePanel");
        cardPanel.add(new Pos_MemberManagement().creatMemberManagement(), "memberPanel");
        cardPanel.add(new Pos_SaleStatus().creatSaleStatus(), "salesPanel");
        cardPanel.add(new Pos_OrderList().creatOrderList(), "orderPanel");
        cardPanel.add(new Pos_InventoryList().creatInventoryList(), "arrivalPanel");
        cardPanel.add(new Pos_BookManagement().createBookManagement(), "bookPanel");
        cardPanel.add(new Pos_Payment().creatPayment(), "paymentPanel");
        
        backgroundPanel.add(cardPanel);

        // JFrame 설정
       //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(backgroundPanel);
        frame.setSize(screenWidth, screenHight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
	}
	
	
	private static JPanel Addbutton(String name, String path, int x, int y, String panelName) {
		JPanel button = new JPanel() {
        	@Override
        	protected void paintComponent(Graphics g) {
        		Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();

                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, 40, 40));

                super.paintComponent(g);
                
                g2.dispose();
        	}
        };
        
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
        button.setOpaque(false);
       
        
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
            public void mouseClicked(MouseEvent e) {
            	buttonChangeColor(button);
            	 CardLayout cl = (CardLayout) (cardPanel.getLayout());
                 cl.show(cardPanel, panelName);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            	buttonChangeColor(button);
           	 CardLayout cl = (CardLayout) (cardPanel.getLayout());
                cl.show(cardPanel, panelName);
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
	
	// 색상으로 패널을 생성하는 헬퍼 메소드
    private JPanel createPanel(Color color) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        return panel;
    }
	
	public JFrame getFrame() {
		return frame;
	}
	
	public JPanel getButton(int index) {
		return buttons[index]; 
	}
	
	
	

}