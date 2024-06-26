package menu;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicScrollBarUI;

import frame_utility.Utility;
import menu.pos_member.dto.MembersDTO;
import menu.pos_member.query.MembersQuery;

public class Pos_MemberManagement {
	private JPanel contentArea = new RoundPanelTool(40, Color.WHITE);
	private Utility u = new Utility();
	
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
		
		List<MembersDTO> list = new MembersQuery().getMemberList(word);
		Utility u = new Utility();
		
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
	
			
			info[0].setBounds(50, 4, 140, 40);
			info[1].setBounds(220, 4, 220, 40);
			info[2].setBounds(440, 4, 230, 40);
			info[3].setBounds(670, 4, 220, 40);
			info[4].setBounds(910, 11, 100, 30);	
				
			info[4].setHorizontalAlignment(JLabel.CENTER);
			info[4].setBackground(new Color(166, 231, 216));
			
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
	
	
	
}
