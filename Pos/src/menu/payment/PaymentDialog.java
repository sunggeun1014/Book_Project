package menu.payment;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import frame_utility.ButtonTool;
import frame_utility.Utility;
import menu.Pos_Payment;
import menu.payment.dto.PaymentPurchaseDTO;
import menu.payment.query.PaymentQuery;

public class PaymentDialog {
	private Pos_Payment posPayment;
	private static String memberID = "";

	public PaymentDialog(Pos_Payment posPayment) {
		this.posPayment = posPayment;
	}

	public static String getMemberID() {
		return memberID;
	}

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
        priceLabel.setBounds(100, 75, 150, 30); // 가운데 정렬 수정
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

                    // 결제 처리
                    PaymentQuery query = new PaymentQuery();
                    List<PaymentPurchaseDTO> purchaseDTOList = posPayment.getPurchaseDTOList();
                    int result = query.createPurchase(purchaseDTOList);
                    if (result > 0) {
                        JDialog end = u.popup("결제 완료", cashPaymentScreen, true);
                        posPayment.getPurchaseDTOList().clear(); // 결제 완료 후 리스트 초기화
                    } else {
                        JDialog end = u.popup("결제 실패", cashPaymentScreen, true);
                    }

                } catch (NumberFormatException ex) {
                    System.err.println("입력된 값이 올바른 형식이 아닙니다.");
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
        priceLabel.setBounds(100, 75, 150, 30); // 가운데 정렬 수정
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
            	PaymentQuery query = new PaymentQuery();
                List<PaymentPurchaseDTO> purchaseDTOList = posPayment.getPurchaseDTOList();
                int result = query.createPurchase(purchaseDTOList);
                if (result > 0) {
                    JDialog end = u.popup("결제 완료", cardPaymentScreen, true);
                    posPayment.getPurchaseDTOList().clear(); // 결제 완료 후 리스트 초기화
                } else {
                    JDialog end = u.popup("결제 실패", cardPaymentScreen, true);
                }
            }
        });

        cardPaymentScreen.setVisible(true);

        return cardPaymentScreen;
    }
    
    public JDialog createSplitDialog(String price) {
        JDialog splitPaymentScreen = new JDialog();
        splitPaymentScreen.setLayout(null);

        splitPaymentScreen.setUndecorated(false);
        splitPaymentScreen.getContentPane().setBackground(Color.WHITE);

        Font font = new Font("맑은 고딕", Font.BOLD, 15);
        Font font2 = new Font("맑은 고딕", Font.BOLD, 20);

        splitPaymentScreen.setResizable(false);
        splitPaymentScreen.setSize(300, 350);

        int screenWidth = splitPaymentScreen.getToolkit().getScreenSize().width;
        int screenHeight = splitPaymentScreen.getToolkit().getScreenSize().height;
        int dialogWidth = splitPaymentScreen.getWidth();
        int dialogHeight = splitPaymentScreen.getHeight();
        splitPaymentScreen.setLocation((screenWidth - dialogWidth) / 2, (screenHeight - dialogHeight) / 2);

        JLabel totalAmountLabel = new JLabel("총 금액을 확인해주세요");
        totalAmountLabel.setFont(font);
        totalAmountLabel.setForeground(new Color(22, 40, 80));
        totalAmountLabel.setBounds(60, 30, 200, 30);
        splitPaymentScreen.add(totalAmountLabel);

        JLabel underlineLabel = new JLabel("_______________________________");
        underlineLabel.setBounds(30, 50, 240, 30);
        splitPaymentScreen.add(underlineLabel);

        JLabel priceLabel = new JLabel(price + "원");
        priceLabel.setFont(font2);
        priceLabel.setForeground(Color.RED);
        priceLabel.setBounds(100, 75, 150, 30);
        splitPaymentScreen.add(priceLabel);

        JLabel cashPaymentLabel = new JLabel("현금 결제");
        cashPaymentLabel.setFont(font);
        cashPaymentLabel.setForeground(new Color(22, 40, 80));
        cashPaymentLabel.setBounds(105, 105, 100, 30);
        splitPaymentScreen.add(cashPaymentLabel);

        Utility u = new Utility();

        JTextField receivedAmountField = u.getRoundShapeTextField(25, 25);
        receivedAmountField.setBounds(70, 140, 150, 30);
        receivedAmountField.setText("받은 돈을 입력해주세요");
        receivedAmountField.setForeground(Color.GRAY);
        receivedAmountField.setHorizontalAlignment(JTextField.CENTER);

        receivedAmountField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ("받은 돈을 입력해주세요".equals(receivedAmountField.getText())) {
                    receivedAmountField.setText("");
                    receivedAmountField.setForeground(Color.BLACK);
                }
            }
        });
        splitPaymentScreen.add(receivedAmountField);

        JLabel changemoneyMent = new JLabel("남은 잔액을 확인해주세요");
        changemoneyMent.setForeground(new Color(22, 40, 80));
        changemoneyMent.setFont(font);
        changemoneyMent.setBounds(50, 175, 250, 30);
        splitPaymentScreen.add(changemoneyMent);

        ButtonTool button = ButtonTool.createButton("결제완료", new Color(22, 40, 80), Color.WHITE, new Color(79, 163, 252), font, 25, 25, true);
        button.setBounds(75, 250, 150, 30);
        splitPaymentScreen.add(button);

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
                    splitPaymentScreen.add(pay);
                    splitPaymentScreen.revalidate();
                    splitPaymentScreen.repaint();

                    // Split payment 처리 후 카드 결제 창 호출
                    JDialog cardScreen = new PaymentDialog(posPayment).createCardDialog(String.valueOf(change), true);
                    splitPaymentScreen.dispose();
                } catch (NumberFormatException ex) {
                    System.err.println("입력된 값이 올바른 형식이 아닙니다.");
                }
            }
        });

        splitPaymentScreen.setVisible(true);
        return splitPaymentScreen;
    }
    
    public JDialog createMemberInquiry() {
        JDialog memberInquiryScreen = new JDialog();
        memberInquiryScreen.setLayout(null);

        memberInquiryScreen.setUndecorated(false);
        memberInquiryScreen.getContentPane().setBackground(Color.WHITE);

        Font font = new Font("맑은 고딕", Font.BOLD, 15);
        Font font2 = new Font("맑은 고딕", Font.BOLD, 20);

        memberInquiryScreen.setResizable(false);
        memberInquiryScreen.setSize(250, 200);

        int screenWidth = memberInquiryScreen.getToolkit().getScreenSize().width;
        int screenHeight = memberInquiryScreen.getToolkit().getScreenSize().height;
        int dialogWidth = memberInquiryScreen.getWidth();
        int dialogHeight = memberInquiryScreen.getHeight();
        memberInquiryScreen.setLocation((screenWidth - dialogWidth) / 2, (screenHeight - dialogHeight) / 2);

        JLabel totalAmountLabel = new JLabel("ID를 입력해주세요");
        totalAmountLabel.setFont(font);
        totalAmountLabel.setForeground(new Color(22, 40, 80));
        totalAmountLabel.setBounds(60, 20, 200, 30);
        memberInquiryScreen.add(totalAmountLabel);

        JLabel underlineLabel = new JLabel("_________________________");
        underlineLabel.setBounds(30, 40, 240, 30);
        memberInquiryScreen.add(underlineLabel);

        Utility u = new Utility();
        JTextField userId = u.getRoundShapeTextField(25, 25);
        userId.setBounds(50, 75, 150, 30);
        userId.setText("ID를 입력해 주세요.");
        userId.setForeground(Color.GRAY);
        userId.setHorizontalAlignment(JTextField.CENTER);

        userId.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ("ID를 입력해 주세요.".equals(userId.getText())) {
                    userId.setText("");
                    userId.setForeground(Color.BLACK);
                }
            }
        });
        memberInquiryScreen.add(userId);

        ButtonTool button = ButtonTool.createButton("회원조회", new Color(22, 40, 80), Color.WHITE, new Color(79, 163, 252), font, 25, 25, true);
        button.setBounds(75, 115, 100, 25);
        memberInquiryScreen.add(button);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                memberID = userId.getText();
                memberInquiryScreen.dispose();
                JDialog end = u.popup(memberID + " 회원 확인", memberInquiryScreen, true);
            }
        });

        memberInquiryScreen.setVisible(true);
        return memberInquiryScreen;
    }
   
}
