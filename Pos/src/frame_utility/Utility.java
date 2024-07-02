package frame_utility;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Utility {

	public JPanel getRoundShape(int num1, int num2) {
		JPanel jpanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				int width = getWidth();
				int height = getHeight();
				
				g2.setColor(getBackground());
				g2.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, num1, num2));
				
				super.paintComponent(g);
				
				g2.dispose();
			}
		};
		jpanel.setOpaque(false);
		jpanel.setLayout(null);
		
		return jpanel;
	}
	
	public JLabel getRoundShapeLabel(int num1, int num2) {
		JLabel jLabel = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				int width = getWidth();
				int height = getHeight();
				
				g2.setColor(getBackground());
				g2.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, num1, num2));
				
				super.paintComponent(g);
				
				g2.dispose();
			}
		};
		jLabel.setOpaque(false);
		jLabel.setLayout(null);
		
		return jLabel;
	}
	

	public JTextField getRoundShapeTextField(int num1, int num2) {
		JTextField jtextField = new JTextField() {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				int width = getWidth();
				int height = getHeight();
				
				g2.setColor(getBackground());
				g2.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, num1, num2));
				
				  // 텍스트 필드의 테두리 설정
		        g2.setColor(new Color(157,163,178));
		        g2.draw(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, num1, num2));
				
				super.paintComponent(g);
				
				g2.dispose();
			}
			
			@Override
		    protected void paintBorder(Graphics g) {
		    }
		};
		jtextField.setOpaque(false);
		jtextField.setLayout(null);
		
		return jtextField;
	}
	
	public JDialog popup(String text, JDialog frame, boolean option) {
	    // 팝업 프레임 생성
	    JDialog pop_upPage = new JDialog();
	    pop_upPage.setLayout(null);
	    pop_upPage.setUndecorated(false); // 타이틀 바 제거
	    pop_upPage.getContentPane().setBackground(Color.WHITE); // 배경색 변경

	    Font font = new Font("맑은 고딕", Font.BOLD, 15);

	    // 메시지 라벨 생성
	    JLabel message = new JLabel(text, SwingConstants.CENTER);
	    message.setFont(font);
	    message.setOpaque(true);
	    message.setBackground(Color.WHITE); // 메시지 라벨의 배경색 변경
	    message.setForeground(new Color(22,40,80)); // 메시지 텍스트 색상 설정

	    // 텍스트 너비 계산
	    FontMetrics metrics = message.getFontMetrics(font);
	    int textWidth = metrics.stringWidth(text);
	    int textHeight = 70;

	    // 텍스트의 중앙 위치 계산
	    int xText = (300 - textWidth) / 2;
	    int yText = 25; // 고정된 Y 위치

	    message.setBounds(xText, yText, textWidth, textHeight);

	    // 버튼 라벨 생성
	    Utility u = new Utility();
	    JLabel button = u.getRoundShapeLabel(25, 25);
	    button.setHorizontalAlignment(SwingConstants.CENTER);
	    button.setText("확인");
	    button.setFont(font);
	    button.setOpaque(false); // 배경 색 가시성을 위해 Opaque 설정
	    button.setBackground(new Color(22,40,80)); // 버튼 배경색 변경
	    button.setForeground(Color.WHITE); // 버튼 텍스트 색상 설정

	    // 버튼 너비 계산
	    int buttonWidth = 150; // 약간의 패딩 추가
	    int buttonHeight = 30;

	    // 버튼의 중앙 위치 계산
	    int xButton = (300 - buttonWidth) / 2;
	    int yButton = 110; // 고정된 Y 위치

	    button.setBounds(xButton, yButton, 150, buttonHeight);

	    // 버튼에 마우스 리스너를 추가하여 두 프레임을 모두 닫음
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
	            if (option) {
	                pop_upPage.dispose();
	                frame.dispose();
	            } else {
	                pop_upPage.dispose();
	            }
	        }
	    });
	    
	    

	    // 컴포넌트를 팝업 프레임에 추가
	    pop_upPage.setResizable(false);
	    pop_upPage.add(message);
	    pop_upPage.add(button);
	    pop_upPage.setSize(300, 200);
	    pop_upPage.setLocationRelativeTo(null);
	    pop_upPage.setVisible(true);
		return pop_upPage;
	}
	
	public JDialog popup(String text, JFrame frame, boolean option) {
        // 팝업 프레임 생성
        JDialog pop_upPage = new JDialog(frame, "Popup", true); // 프레임을 부모로 하는 모달 다이얼로그
        pop_upPage.setLayout(null);
        pop_upPage.getContentPane().setBackground(Color.WHITE);

        Font font = new Font("맑은 고딕", Font.BOLD, 15);

        // 메시지 라벨 생성
        JLabel message = new JLabel(text, SwingConstants.CENTER);
        message.setFont(font);
        message.setForeground(new Color(22, 40, 80));

        // 텍스트 너비 계산
        FontMetrics metrics = message.getFontMetrics(font);
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();

        // 텍스트의 중앙 위치 계산
        int xText = (300 - textWidth) / 2;
        int yText = 50;

        message.setBounds(xText, yText, textWidth, textHeight);

        // 버튼 라벨 생성
        JLabel button = new JLabel("확인", SwingConstants.CENTER);
        button.setFont(font);
        button.setOpaque(true);
        button.setBackground(new Color(22, 40, 80));
        button.setForeground(Color.WHITE);

        // 버튼 너비 계산
        int buttonWidth = 100;
        int buttonHeight = 30;

        // 버튼의 중앙 위치 계산
        int xButton = (300 - buttonWidth) / 2;
        int yButton = 120;

        button.setBounds(xButton, yButton, buttonWidth, buttonHeight);

        // 버튼에 마우스 리스너 추가
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
                pop_upPage.dispose();
                if (option) {
                    frame.dispose();
                }
            }
        });

        // 컴포넌트를 팝업 프레임에 추가
        pop_upPage.add(message);
        pop_upPage.add(button);
        pop_upPage.setSize(300, 200);
        pop_upPage.setLocationRelativeTo(frame);
        pop_upPage.setVisible(true);

        return pop_upPage;
    }
	
	public JTextField getRoundShapeTextField(int num1, int num2, Color color) {
		JTextField textField = new JTextField() {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				int width = getWidth();
				int height = getHeight();
				
				g2.setColor(color);
				g2.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, num1, num2));
				
				super.paintComponent(g);
				
				g2.dispose();
			}
		};
		textField.setOpaque(false);
		textField.setLayout(null);
		
		return textField;
	}
	
	public boolean isPhoneNumber(String phoneNumber) {
		return Pattern.matches("010-\\d{4}-\\d{4}", phoneNumber);
	}
	
	public String priceFormat(int price) {
		DecimalFormat df = new DecimalFormat("#,###");
		
		return df.format(price);
	}
	
}