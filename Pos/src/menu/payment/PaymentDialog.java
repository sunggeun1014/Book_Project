package menu.payment;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import frame_utility.ButtonTool;
import frame_utility.Utility;

public class PaymentDialog {
    public JDialog createCashDialog(String price) {
        JDialog cashPaymentScreen = new JDialog();
        cashPaymentScreen.setLayout(null); // null layout 설정

        // 타이틀 바 제거
        cashPaymentScreen.setUndecorated(false);

        // 배경색 설정
        cashPaymentScreen.getContentPane().setBackground(Color.WHITE);

        Font font = new Font("맑은 고딕", Font.BOLD, 15);
        Font font2 = new Font("맑은 고딕", Font.BOLD, 20);

        cashPaymentScreen.setResizable(false);
        cashPaymentScreen.setSize(300, 350); // 다이얼로그 크기 조정

        // 화면 중앙에 위치하도록 계산
        int screenWidth = cashPaymentScreen.getToolkit().getScreenSize().width;
        int screenHeight = cashPaymentScreen.getToolkit().getScreenSize().height;
        int dialogWidth = cashPaymentScreen.getWidth();
        int dialogHeight = cashPaymentScreen.getHeight();
        cashPaymentScreen.setLocation((screenWidth - dialogWidth) / 2, (screenHeight - dialogHeight) / 2);

        JLabel totalAmountLabel = new JLabel("총 금액을 확인해주세요");
        totalAmountLabel.setFont(font);
        totalAmountLabel.setForeground(new Color(22, 40, 80));
        totalAmountLabel.setBounds(60, 30, 200, 30); // 가운데 정렬 수정
        cashPaymentScreen.add(totalAmountLabel);

        JLabel underlineLabel = new JLabel("_______________________________");
        underlineLabel.setBounds(30, 50, 240, 30); // 가운데 정렬 수정
        cashPaymentScreen.add(underlineLabel);

        JLabel priceLabel = new JLabel(price + "원");
        priceLabel.setFont(font2);
        priceLabel.setForeground(Color.RED);
        priceLabel.setBounds(100, 75, 100, 30); // 가운데 정렬 수정
        cashPaymentScreen.add(priceLabel);

        JLabel cashPaymentLabel = new JLabel("현금 결제");
        cashPaymentLabel.setFont(font);
        cashPaymentLabel.setForeground(new Color(22, 40, 80));
        cashPaymentLabel.setBounds(105, 105, 100, 30); // 가운데 정렬 수정
        cashPaymentScreen.add(cashPaymentLabel);

        Utility u = new Utility();

        // JTextField를 확장하지 않고 입력 전 문구를 처리하기 위해 MouseAdapter 사용
        JTextField receivedAmountField = u.getRoundShapeTextField(25, 25);
        receivedAmountField.setBounds(70, 140, 150, 30); // 가운데 정렬 수정
        receivedAmountField.setText("받은 돈을 입력해주세요");
        receivedAmountField.setForeground(Color.GRAY); // 기본 문구는 회색으로 표시
        receivedAmountField.setHorizontalAlignment(JTextField.CENTER); // 텍스트 가운데 정렬 설정
        
     
        receivedAmountField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭 시 기본 문구 삭제
                if ("받은 돈을 입력해주세요".equals(receivedAmountField.getText())) {
                    receivedAmountField.setText("");
                    receivedAmountField.setForeground(Color.BLACK); // 입력 중 텍스트는 검은색으로 변경
                }
            }
        });
        cashPaymentScreen.add(receivedAmountField);

        JLabel changemoneyMent = new JLabel("거스름 돈 금액을 확인해 주세요");
        changemoneyMent.setForeground(new Color(22, 40, 80));
        changemoneyMent.setFont(font);
        changemoneyMent.setBounds(40, 175, 250, 30); // 가운데 정렬 수정
        cashPaymentScreen.add(changemoneyMent);

        ButtonTool button = ButtonTool.createButton("결제완료", new Color(22, 40, 80), Color.WHITE, new Color(79, 163, 252), font, 25, 25, true);
        button.setBounds(75, 250, 150, 30);
        cashPaymentScreen.add(button);

        
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    int pricetext = Integer.parseInt(price);
                    int recieveMoney = Integer.parseInt(receivedAmountField.getText());
                    int change = recieveMoney - pricetext;

                    JLabel pay = new JLabel(change + "원");
                    pay.setFont(font2);
                    pay.setForeground(Color.RED);
                    pay.setBounds(105, 210, 250, 30);
                    cashPaymentScreen.add(pay);
                    cashPaymentScreen.revalidate();
                    cashPaymentScreen.repaint();
                    
                    Utility u = new Utility();
                    JDialog end = u.popup("결제 완료", cashPaymentScreen, true);
                    
                } catch (NumberFormatException ex) {
                    System.err.println("입력된 값이 올바른 형식이 아닙니다.");
                    // 예외 처리 - 입력된 값이 숫자 형식이 아닐 경우
                }
            }
        });

        cashPaymentScreen.setVisible(true);

        return cashPaymentScreen;
    }
    
    
    public JDialog createCardDialog(String price, boolean option) {
        JDialog cardPaymentScreen = new JDialog();
        cardPaymentScreen.setLayout(null); // null layout 설정

        // 타이틀 바 제거
        cardPaymentScreen.setUndecorated(false);

        // 배경색 설정
        cardPaymentScreen.getContentPane().setBackground(Color.WHITE);

        Font font = new Font("맑은 고딕", Font.BOLD, 15);
        Font font2 = new Font("맑은 고딕", Font.BOLD, 20);

        cardPaymentScreen.setResizable(false);
        cardPaymentScreen.setSize(300, 350); // 다이얼로그 크기 조정

        // 화면 중앙에 위치하도록 계산
        int screenWidth = cardPaymentScreen.getToolkit().getScreenSize().width;
        int screenHeight = cardPaymentScreen.getToolkit().getScreenSize().height;
        int dialogWidth = cardPaymentScreen.getWidth();
        int dialogHeight = cardPaymentScreen.getHeight();
        cardPaymentScreen.setLocation((screenWidth - dialogWidth) / 2, (screenHeight - dialogHeight) / 2);

        JLabel totalAmountLabel = new JLabel("총 금액을 확인해주세요");
        totalAmountLabel.setFont(font);
        totalAmountLabel.setForeground(new Color(22, 40, 80));
        totalAmountLabel.setBounds(60, 30, 200, 30); // 가운데 정렬 수정
        cardPaymentScreen.add(totalAmountLabel);

        JLabel underlineLabel = new JLabel("_______________________________");
        underlineLabel.setBounds(30, 50, 240, 30); // 가운데 정렬 수정
        cardPaymentScreen.add(underlineLabel);

        JLabel priceLabel = new JLabel(price + "원");
        priceLabel.setFont(font2);
        priceLabel.setForeground(Color.RED);
        priceLabel.setBounds(100, 75, 100, 30); // 가운데 정렬 수정
        cardPaymentScreen.add(priceLabel);

        JLabel cashPaymentLabel = new JLabel("카드 결제");
        cashPaymentLabel.setFont(font);
        cashPaymentLabel.setForeground(new Color(22, 40, 80));
        cashPaymentLabel.setBounds(105, 105, 100, 30); // 가운데 정렬 수정
        cardPaymentScreen.add(cashPaymentLabel);

        // 이미지 추가
        ImageIcon icon = new ImageIcon("images/icon/카드.png"); // 이미지 경로 설정
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(40, 140, 200, 60); // 이미지 위치와 크기 설정
        cardPaymentScreen.add(imageLabel);

        JLabel changemoneyMent = new JLabel("금액을 확인해 주세요");
        changemoneyMent.setForeground(new Color(22, 40, 80));
        changemoneyMent.setFont(font);
        changemoneyMent.setBounds(70, 210, 250, 30); // 가운데 정렬 수정
        cardPaymentScreen.add(changemoneyMent);

        ButtonTool button = ButtonTool.createButton("결제완료", new Color(22, 40, 80), Color.WHITE, new Color(79, 163, 252), font, 25, 25, true);
        button.setBounds(70, 250, 150, 30);
        cardPaymentScreen.add(button);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
            	
            	if(option) {
            		cardPaymentScreen.dispose();
                }
            	
            	Utility u = new Utility();
                JDialog end = u.popup("결제 완료", cardPaymentScreen, true);
            }
        });

        cardPaymentScreen.setVisible(true);

        return cardPaymentScreen;
    }
    
    public JDialog createSplitDialog(String price) {
        JDialog cashPaymentScreen = new JDialog();
        cashPaymentScreen.setLayout(null); // null layout 설정

        // 타이틀 바 제거
        cashPaymentScreen.setUndecorated(false);

        // 배경색 설정
        cashPaymentScreen.getContentPane().setBackground(Color.WHITE);

        Font font = new Font("맑은 고딕", Font.BOLD, 15);
        Font font2 = new Font("맑은 고딕", Font.BOLD, 20);

        cashPaymentScreen.setResizable(false);
        cashPaymentScreen.setSize(300, 350); // 다이얼로그 크기 조정

        // 화면 중앙에 위치하도록 계산
        int screenWidth = cashPaymentScreen.getToolkit().getScreenSize().width;
        int screenHeight = cashPaymentScreen.getToolkit().getScreenSize().height;
        int dialogWidth = cashPaymentScreen.getWidth();
        int dialogHeight = cashPaymentScreen.getHeight();
        cashPaymentScreen.setLocation((screenWidth - dialogWidth) / 2, (screenHeight - dialogHeight) / 2);

        JLabel totalAmountLabel = new JLabel("총 금액을 확인해주세요");
        totalAmountLabel.setFont(font);
        totalAmountLabel.setForeground(new Color(22, 40, 80));
        totalAmountLabel.setBounds(60, 30, 200, 30); // 가운데 정렬 수정
        cashPaymentScreen.add(totalAmountLabel);

        JLabel underlineLabel = new JLabel("_______________________________");
        underlineLabel.setBounds(30, 50, 240, 30); // 가운데 정렬 수정
        cashPaymentScreen.add(underlineLabel);

        JLabel priceLabel = new JLabel(price + "원");
        priceLabel.setFont(font2);
        priceLabel.setForeground(Color.RED);
        priceLabel.setBounds(100, 75, 100, 30); // 가운데 정렬 수정
        cashPaymentScreen.add(priceLabel);

        JLabel cashPaymentLabel = new JLabel("현금 결제");
        cashPaymentLabel.setFont(font);
        cashPaymentLabel.setForeground(new Color(22, 40, 80));
        cashPaymentLabel.setBounds(105, 105, 100, 30); // 가운데 정렬 수정
        cashPaymentScreen.add(cashPaymentLabel);

        Utility u = new Utility();

        // JTextField를 확장하지 않고 입력 전 문구를 처리하기 위해 MouseAdapter 사용
        JTextField receivedAmountField = u.getRoundShapeTextField(25, 25);
        receivedAmountField.setBounds(70, 140, 150, 30); // 가운데 정렬 수정
        receivedAmountField.setText("받은 돈을 입력해주세요");
        receivedAmountField.setForeground(Color.GRAY); // 기본 문구는 회색으로 표시
        receivedAmountField.setHorizontalAlignment(JTextField.CENTER); // 텍스트 가운데 정렬 설정
        receivedAmountField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 마우스 클릭 시 기본 문구 삭제
                if ("받은 돈을 입력해주세요".equals(receivedAmountField.getText())) {
                    receivedAmountField.setText("");
                    receivedAmountField.setForeground(Color.BLACK); // 입력 중 텍스트는 검은색으로 변경
                }
            }
        });
        cashPaymentScreen.add(receivedAmountField);

        JLabel changemoneyMent = new JLabel("남은 잔액을 확인해주세요");
        changemoneyMent.setForeground(new Color(22, 40, 80));
        changemoneyMent.setFont(font);
        changemoneyMent.setBounds(50, 175, 250, 30); // 가운데 정렬 수정
        cashPaymentScreen.add(changemoneyMent);

        ButtonTool button = ButtonTool.createButton("결제완료", new Color(22, 40, 80), Color.WHITE, new Color(79, 163, 252), font, 25, 25, true);
        button.setBounds(75, 250, 150, 30);
        cashPaymentScreen.add(button);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    int pricetext = Integer.parseInt(price);
                    int recieveMoney = Integer.parseInt(receivedAmountField.getText());
                    int change = pricetext - recieveMoney;

                    JLabel pay = new JLabel(change + "원");
                    pay.setFont(font2);
                    pay.setForeground(Color.RED);
                    pay.setBounds(105, 210, 250, 30);
                    cashPaymentScreen.add(pay);
                    cashPaymentScreen.revalidate();
                    cashPaymentScreen.repaint();
                    
                    
            		JDialog cardScreen = new PaymentDialog().createCardDialog(String.valueOf(change),true);
            		cashPaymentScreen.dispose();
                   
                } catch (NumberFormatException ex) {
                }
                
        		

            }
        });

        cashPaymentScreen.setVisible(true);

        return cashPaymentScreen;
    }
}
