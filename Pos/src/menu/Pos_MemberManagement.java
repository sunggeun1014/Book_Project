package menu;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicScrollBarUI;

import frame_utility.RoundPanelTool;
import frame_utility.Utility;
import menu.pos_book_purchase.dto.BookPurchaseDTO;
import menu.pos_book_purchase.query.BookPurchaseQuery;
import menu.pos_member.dto.MemberPurchaseDTO;
import menu.pos_member.dto.MembersDTO;
import menu.pos_member.query.MembersQuery;

public class Pos_MemberManagement {
	private JPanel contentArea = new RoundPanelTool(40, Color.WHITE);
	private Utility u = new Utility();
	private MembersQuery member = new MembersQuery();
	private BookPurchaseQuery purchase = new BookPurchaseQuery();
	private JFrame popup;
	
	public JPanel creatMemberManagement() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(246, 247, 251));
		panel.setLayout(null);
		
		JPanel searchArea = u.getRoundShape(20, 20);
		searchArea.setSize(320, 50);
		searchArea.setLocation(10, 20);
		searchArea.setBackground(Color.WHITE);
		
		searchArea.add(getTextBox());
		searchArea.add(getSearchImg());
		panel.add(searchArea);
		
		contentArea.setLayout(null);
		contentArea.setBackground(new Color(246, 247, 251));
		contentArea.setBounds(10, 120, 1080, 630);
		
		addColName(contentArea);
		addMembers("");
		
		panel.add(contentArea);

		return panel;

	}
	
	private JLabel getSearchImg() {
		ImageIcon icon = new ImageIcon("images/icon/search.png");
        
        JLabel imgLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        imgLabel.setSize(24, 24);
        imgLabel.setLocation(22, 14);
        
        
        return imgLabel;
	}
	
	private JTextField getTextBox() {
		JTextField textBox = new JTextField("회원 이름을 입력해주세요");
		
		textBox.setSize(280, 40);
		textBox.setLocation(60, 5);
		textBox.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		textBox.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		textBox.setForeground(Color.GRAY);
		
        textBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textBox.getText().equals("회원 이름을 입력해주세요")) {
                    textBox.setText("");
                    textBox.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textBox.getText().isEmpty()) {
                    textBox.setText("회원 이름을 입력해주세요");
                }
            }
            
            
        });
        
        textBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	removeMemberArea(textBox.getText());
            }
        });

		
		return textBox;
	}
	
	private void addColName(JPanel panel) {
		JLabel title = new JLabel("회원관리");
		title.setSize(100, 100);
		title.setLocation(40, 10);
		title.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		
		JLabel[] colNames = new JLabel[5];
		colNames[0] = new JLabel("아이디");
		colNames[1] = new JLabel("이름");
		colNames[2] = new JLabel("전화번호");
		colNames[3] = new JLabel("포인트");
		colNames[4] = new JLabel("구매내역 확인");
		
		colNames[0].setBounds(45, 100, 100, 40);
		colNames[1].setBounds(220, 100, 200, 40);
		colNames[2].setBounds(440, 100, 100, 40);
		colNames[3].setBounds(670, 100, 100, 40);
		colNames[4].setBounds(900, 100, 250, 40);
		
		
		for(JLabel colName : colNames) {
			colName.setFont(new Font("맑은 고딕", Font.BOLD, 18));
			colName.setForeground(new Color(22, 40, 80));
			panel.add(colName);
		}
		panel.add(title);
	}
	
	private void addMembers(String word) {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    panel.setSize(1080, 800);
		
		List<MembersDTO> list = member.getMemberList(word);
		
		for(MembersDTO dto : list) {
			JLabel[] info = new JLabel[5];
			
			for (int i = 0; i < info.length - 1; i++) {
				info[i] = new JLabel();
			}
			info[info.length - 1] = u.getRoundShapeLabel(10, 10);
			
			
			JPanel row = new JPanel();
			
			row.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(238, 238, 238)));
			row.setLayout(null);
			row.setPreferredSize(new Dimension(1000, 50));
			row.setOpaque(false);
			
			
			info[0].setText(dto.getMemberId());
			info[1].setText(dto.getName());
			info[2].setText(dto.getPhoneNumber());
			info[3].setText(dto.getPoint().toString() + "P");
			info[4].setText("내역 확인");
			info[4].putClientProperty("hiddenText", dto.getMemberId());
			
			info[0].setBounds(50, 4, 140, 40);
			info[1].setBounds(220, 4, 220, 40);
			info[2].setBounds(440, 4, 230, 40);
			info[3].setBounds(670, 4, 220, 40);
			info[4].setBounds(910, 11, 100, 30);	
				
			info[4].setHorizontalAlignment(JLabel.CENTER);
			info[4].setBackground(new Color(166, 231, 216));
			
			addLabelListener(info[4]);
			for(JLabel label : info) {
				label.setForeground(Color.BLACK);
				label.setFont(new Font("돋움", Font.PLAIN, 14));
				row.add(label);
			}
			panel.add(row);
			
		}
		
		for(int i = list.size(); i < 8; i++) {
			JPanel row = new JPanel();
			
			row.setLayout(null);
			row.setPreferredSize(new Dimension(1000, 50));
			row.setOpaque(false);
			
			panel.add(row);
		}
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBorder(null);
        scrollPane.setBounds(0, 150, 1080, 460);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(5, 0));
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.GRAY;
                this.trackColor = Color.LIGHT_GRAY;
            }
        });
        
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(16);
        verticalScrollBar.setBlockIncrement(100);

        contentArea.add(scrollPane);
	}
	
	private void removeMemberArea(String word) {
	    contentArea.removeAll();
	    
	    addColName(contentArea);
	    addMembers(word);

	    contentArea.revalidate();
	    contentArea.repaint();
	}
	
	private void addLabelListener(JLabel label) {
		label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	label.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
            	popup = setJFrameOption(new JFrame(), 1000, 500);
            	addMemberModifyJPanel(popup, label.getClientProperty("hiddenText").toString());
        		
            	popup.setVisible(true);
            }
        });
	}
	
	private JFrame setJFrameOption(JFrame frame, int width, int height) {
		frame.setUndecorated(false);
		frame.setSize(width, height);
		frame.setLayout(null);
		frame.getContentPane().setBackground(Color.WHITE);
		//frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 30, 30));
    	frame.setLocationRelativeTo(null);
    	
    	return frame;
	}
	
	private JFrame addMemberModifyJPanel(JFrame frame, String id) {
		JLabel[] headers = new JLabel[2];
		List<MemberPurchaseDTO> purchaseList = member.getMemberPurchaseList(id);
		
		headers[0] = new JLabel(id + " 고객님의");
		headers[1] = new JLabel("구매내역 (" + purchaseList.size() + "건)");
		
		headers[0].setBounds(40, 50, 350, 30);
		headers[1].setBounds(40, 80, 350, 40);
		
		headers[0].setFont(new Font("맑은 고딕", Font.BOLD, 15));
		headers[1].setFont(new Font("맑은 고딕", Font.BOLD, 22));
		for(JLabel header : headers) {
			header.setForeground(Color.BLACK);
			frame.add(header);
		}
		
		JTextField[] info = new JTextField[6];
		
		int height = 150;
		for(int i = 0; i < info.length; i++) {
			JPanel panel = u.getRoundShape(14, 14);
			info[i] = new JTextField();
			
			panel.setLayout(null);
			panel.setBounds(40, height, 450, 35);
			panel.setBorder(null);
			
			info[i].setBounds(12, 0, 430, 35);
			info[i].setBorder(null);
			info[i].setOpaque(false);
			panel.add(info[i]);
			
			info[i].setFont(new Font("맑은 고딕", Font.BOLD, 14));
			
			height += 40;
			
			panel.add(info[i]);
			frame.add(panel);
		}
		
		MembersDTO dto = member.getMemberDetail(id);
		
		info[0].setText(dto.getMemberId());
		info[1].setText("비밀번호");
		info[2].setText(dto.getName());
		info[3].setText(dto.getPhoneNumber());
		info[4].setText(dto.getAddress());
		if(dto.getDetailedAddress() == null || dto.getDetailedAddress().equals("")) {
			info[5].setText("상세주소");
			info[5].setForeground(new Color(197, 197, 197));
		} else {
			info[5].setText(dto.getDetailedAddress());
		}
		
		info[0].setEditable(false);
		info[2].setEditable(false);
		info[4].setEditable(false);
		info[1].setForeground(new Color(197, 197, 197));
		
		
		setMbrTextBox(info[1], "비밀번호");
		setMbrTextBox(info[3], "ex) 010-0000-0000");
		setMbrTextBox(info[4], "주소");
		setMbrTextBox(info[5], "상세주소");
		
		JLabel button = u.getRoundShapeLabel(10, 10);
		
		button.setText("회원정보수정");
		button.setHorizontalAlignment(JLabel.CENTER);
		button.setBackground(new Color(166, 231, 216));
		button.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		button.setForeground(new Color(0, 135, 103));
		button.setSize(450, 35);
		button.setLocation(40, 395);
		
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
            	if(memberValidation(info)) {
            		MembersDTO dto = new MembersDTO();
            		
            		dto.setMemberId(info[0].getText()); 
            		dto.setPassword(info[1].getText());
            		dto.setPhoneNumber(info[3].getText());
            		dto.setAddress(info[4].getText());
            		dto.setDetailedAddress(info[5].getText());
            		
            		int result = member.memberModify(dto);
            		
            		if(result != 0) {
            			popup.dispose();
            		} else {
            			
            		}
            	}
            }
        });
		
		frame.add(getPurchaseListPanel(purchase.getPurchaseList(id)));
		frame.add(button);
		
		return frame;
	}
	
	private void setMbrTextBox(JTextField textBox, String text) {
        textBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textBox.getText().equals(text)) {
                    textBox.setText("");
                    textBox.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textBox.getText().isEmpty()) {
                    textBox.setText(text);
                    textBox.setForeground(new Color(197, 197, 197));
                }
            }
            
        });
        
        textBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	removeMemberArea(textBox.getText());
            }
        });
	}
	
	private boolean memberValidation(JTextField[] field) {
		if(field[1].getText() == null || field[1].getText().equals("") || field[1].getText().equals("비밀번호")) {
			System.out.println("비밀번호를 입력해 주세요.");
			field[1].requestFocus();
			return false;
		} else if(field[1].getText().length() < 4 && field[1].getText().length() <= 30) {
			System.out.println("비밀번호의 길이는 최소 5자 최대 30자 입니다.");
			field[1].requestFocus();
			return false;
		} else if(!u.isPhoneNumber(field[3].getText()) || field[3].getText().equals("") || field[3].getText() == null) {
			System.out.println("비밀번호를 다시 입력해 주세요 ex) 010-0000-0000");
			field[3].requestFocus();
			return false;
		} else if(field[4].getText().equals("") || field[3].getText() == null) {
			System.out.println("주소를 입력해 주세요");
			field[4].requestFocus();
			return false;
		}
		
		
		return true;
	}
	
	private JPanel getPurchaseListPanel(List<BookPurchaseDTO> list) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. HH:mm");

		JPanel panel = u.getRoundShape(14, 14);
		
		int height = 20;
		int cnt = 0;
		for(BookPurchaseDTO dto : list) {
			if(cnt++ == 1) break;
			JPanel row = new JPanel();
			
			row.setLayout(null);
			row.setBounds(20, height, 380, 120);
			
			
			JLabel[] info = new JLabel[5];
	        
			ImageIcon icon = new ImageIcon("images/thumbnail/" + dto.getThumbnail());
	        info[0] = new JLabel() {
	            @Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
	            }
	        };
	        
	        info[1] = new JLabel(sdf.format(dto.getPurchaseDate()));
	        info[2] = new JLabel(dto.getBookTitle());
	        info[3] = new JLabel("수량 " + dto.getPurchaseQty());
			info[4] = new JLabel(u.priceFormat(dto.getPurchaseQty() * dto.getPrice()) + "원");

	        
			for(JLabel label : info) {
				row.add(label);
			}
			
			setPurchaseInfo(info);
			
			panel.add(row);
			
			height += 30;
		}
		
		panel.setBounds(510, 150, 430, 280);
		
		return panel; 
	}
	
	private void setPurchaseInfo(JLabel[] info) {
		info[0].setBounds(0, 0, 70, 70);
		
	}
}
