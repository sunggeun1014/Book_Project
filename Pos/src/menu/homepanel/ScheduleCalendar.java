package menu.homepanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicScrollBarUI;

import frame_utility.ButtonTool;
import frame_utility.Utility;

public class ScheduleCalendar {
    private JPanel contentArea = new JPanel();
    private static JDialog dialog;
    private Utility u = new Utility();
    private JPanel panel;

    public ScheduleCalendar() {
        dialog = new JDialog((JFrame) null, "스케쥴 달력", Dialog.ModalityType.MODELESS);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(400, 800));
        panel.setLayout(null);

        JPanel deco = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        deco.add(panel, gbc);

        JPanel scheduleInfo = new JPanel();
        scheduleInfo.setBounds(0, 360, 400, 250);
        scheduleInfo.setBackground(new Color(246, 246, 246));
        scheduleInfo.setLayout(null);

        Font font = new Font("맑은 고딕", Font.BOLD, 20);

        ButtonTool check = ButtonTool.createButton("확인", new Color(22, 40, 80), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 18), 50, 50, true);
        ButtonTool plus = ButtonTool.createButton("＋", new Color(22, 40, 80), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 18), 50, 50, true);
        check.setBounds(50, 700, 300, 50);

        plus.setBounds(310, 640, 50, 50);
        check.setFont(font);
        plus.setFont(font);
        panel.add(check);
        panel.add(plus);

        JPanel searchArea = u.getRoundShape(50, 50);
        searchArea.setSize(250, 50);
        searchArea.setLocation(30, 640);
        searchArea.setBackground(new Color(246, 246, 246));

        searchArea.add(getTextBox());

        panel.add(searchArea);

        contentArea.setLayout(new BoxLayout(contentArea, BoxLayout.Y_AXIS));
        contentArea.setOpaque(false);
        addSchedule();
        addSchedule();
        addSchedule();
        addSchedule();
        addSchedule();
        addSchedule();
        addSchedule();


        JScrollPane scrollPane = new JScrollPane(contentArea);
        scrollPane.setBorder(null);
        scrollPane.setBounds(0, 0, 400, 250);
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

        scheduleInfo.add(scrollPane);
        panel.add(scheduleInfo);

        CalendarApp calendar = new CalendarApp();
        calendar.creatCalendarApp(panel, true, false, true);

        calendar.getCalendarPanel().setBounds(15, 50, 350, 310);
        calendar.getYearMonthField().setBounds(115, 10, 150, 30);
        dialog.setContentPane(deco);
        dialog.getContentPane().setBackground(new Color(128, 128, 128, 128));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(true);
        dialog.setVisible(true);

    }
				
	
	
	public JDialog getFrame() {
		return dialog;
	}
	
	private JTextField getTextBox() {
		JTextField textBox = new JTextField("일정을 추가해 주세요.");
		textBox.setBackground(new Color(246,246,246));
		textBox.setSize(220, 40);
		textBox.setLocation(15, 5);
		textBox.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		textBox.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		textBox.setForeground(Color.GRAY);
		
        textBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textBox.getText().equals("일정을 추가해 주세요.")) {
                    textBox.setText("");
                    textBox.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textBox.getText().isEmpty()) {
                    textBox.setText("일정을 추가해 주세요.");
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

	private void addSchedule() {
		JPanel panel = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		
        		
		panel.setOpaque(false);
		panel.setPreferredSize(new Dimension(400, 60));
	    panel.setMaximumSize(new Dimension(400, 60)); // Ensure fixed height
		panel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.LIGHT_GRAY));
		panel.setLayout(new GridBagLayout());
		Font font = new Font("맑은 고딕", Font.BOLD, 20);
		
		JLabel label = new JLabel(" 오늘의 일정");
		label.setFont(font);
		label.setSize(280, 50);
		label.setLocation(20, 05);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2; // 가로 공간 비율
        gbc.weighty = 2;
		panel.add(label,gbc);
		
		
		JLabel deleteButton = new JLabel();
		deleteButton = u.getRoundShapeLabel(50, 50);
		deleteButton.setText("+");
		deleteButton.setHorizontalAlignment(JLabel.CENTER);
		deleteButton.setFont(font);
		
		deleteButton.setBackground(Color.WHITE);
		deleteButton.setForeground(new Color(22,40,80));
		deleteButton.setSize(50,50);
		deleteButton.setLocation(320,05);
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				JFrame pop = new JFrame();
				Utility u = new Utility();
				u.popup("새창",pop,false);
				pop.setVisible(true);
				pop.setSize(200,200);
			}
		});
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.09; // 가로 공간 비율
        gbc.weighty = 0.09;
		panel.add(deleteButton,gbc);
//		panel.add(deleteButton);
//		panel.add(label);
		contentArea.add(panel);
		contentArea.revalidate();
	    contentArea.repaint();
	}
	
	private void removeMemberArea(String word) {
		contentArea.removeAll();
	    
		addSchedule();

	    contentArea.revalidate();
	    contentArea.repaint();
	}

	
	
}
