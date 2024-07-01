package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import frame_utility.ButtonTool;
import frame_utility.RoundPanelTool;
import menu.payment.PaymentDialog;
import menu.payment.dto.PaymentDTO;
import menu.payment.query.PaymentQuery;

public class Pos_Payment {
    public JPanel creatPayment() {
        JPanel paymentPanel = new JPanel();
        paymentPanel.setBackground(new Color(246, 247, 251));
        paymentPanel.setLayout(null);

        Font font = new Font("맑은 고딕", Font.BOLD, 25);

        ButtonTool allCancle = ButtonTool.createButton("전체취소", new Color(22, 40, 80), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25), 15, 15, true);
        allCancle.setFont(font);
        allCancle.setBounds(0, 560, 180, 90);
        paymentPanel.add(allCancle);

        ButtonTool selectCancle = ButtonTool.createButton("선택취소", new Color(22, 40, 80), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25), 15, 15, true);
        selectCancle.setFont(font);
        selectCancle.setBounds(190, 560, 180, 90);
        paymentPanel.add(selectCancle);

        ButtonTool plus = ButtonTool.createButton("▲", new Color(22, 40, 80), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25), 15, 15, true);
        plus.setFont(font);
        plus.setBounds(380, 560, 70, 90);
        paymentPanel.add(plus);

        ButtonTool minus = ButtonTool.createButton("▼", new Color(22, 40, 80), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25), 15, 15, true);
        minus.setFont(font);
        minus.setBounds(460, 560, 70, 90);
        paymentPanel.add(minus);

        ButtonTool qty = ButtonTool.createButton("수량", new Color(22, 40, 80), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25), 15, 15, true);
        qty.setFont(font);
        qty.setBounds(540, 560, 190, 90);
        paymentPanel.add(qty);

        ButtonTool cash = ButtonTool.createButton("현금", new Color(106, 118, 147), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25), 15, 15, true);
        cash.setFont(font);
        cash.setBounds(0, 660, 175, 90);
        paymentPanel.add(cash);

        ButtonTool card = ButtonTool.createButton("신용카드", new Color(106, 118, 147), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25), 15, 15, true);
        card.setFont(font);
        card.setBounds(185, 660, 175, 90);
        paymentPanel.add(card);

        ButtonTool split = ButtonTool.createButton("분할계산", new Color(106, 118, 147), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25), 15, 15, true);
        split.setFont(font);
        split.setBounds(370, 660, 175, 90);
        paymentPanel.add(split);

        ButtonTool discount = ButtonTool.createButton("할인혜택", new Color(106, 118, 147), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25), 15, 15, true);
        discount.setFont(font);
        discount.setBounds(555, 660, 175, 90);
        paymentPanel.add(discount);

        ButtonTool calculator = ButtonTool.createButton("환불", new Color(106, 118, 147), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25), 15, 15, true);
        calculator.setFont(font);
        calculator.setBounds(0, 760, 175, 90);
        paymentPanel.add(calculator);

        ButtonTool receipt = ButtonTool.createButton("계산기", new Color(106, 118, 147), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25), 15, 15, true);
        receipt.setFont(font);
        receipt.setBounds(185, 760, 175, 90);
        paymentPanel.add(receipt);

        ButtonTool cashreceipt = ButtonTool.createButton("", new Color(106, 118, 147), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25), 15, 15, true);
        cashreceipt.setFont(font);
        cashreceipt.setBounds(370, 760, 175, 90);
        paymentPanel.add(cashreceipt);

        ButtonTool refund = ButtonTool.createButton("", new Color(106, 118, 147), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25), 15, 15, true);
        refund.setFont(font);
        refund.setBounds(555, 760, 175, 90);
        paymentPanel.add(refund);

        JPanel page1 = new RoundPanelTool(15, Color.WHITE);
        page1.setLayout(null);
        page1.setBackground(new Color(246, 247, 251));
        page1.setBounds(0, 0, 730, 550);

        // 테이블 헤더 및 데이터 정의
        String[] columnNames = {"품목번호", "품목명", "출판사", "작가", "가격"};
        
        PaymentQuery sql = new PaymentQuery();
        List<PaymentDTO> bookInfo = sql.getPurchaseList();
        Object[][] data = new Object[bookInfo.size()][5];
        int index = 0;
        for(PaymentDTO dto : bookInfo) {
        	data[index] = new Object[] {index+1,dto.getBook_title(),dto.getAUTHOR(),dto.getPUBLISHER(),dto.getPrice()+"원"};
        	index++;
        }

        // 테이블 모델 생성
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 셀 편집 비활성화
            }
        };

        // 테이블 생성 및 설정
        JTable table = new JTable(model) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.BLACK); // 구분선 색상
                int rowHeight = getRowHeight();
                int rowCount = getRowCount();
                for (int i = 0; i < rowCount - 1; i++) {
                    g2.drawLine(0, (i + 1) * rowHeight - 1, getWidth(), (i + 1) * rowHeight - 1); // 각 행 아래에 구분선 그리기
                }
            }
        };
        table.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        table.setRowHeight(30);

        // 테이블 내용 가운데 정렬
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setCellRenderer(centerRenderer);
            columnModel.getColumn(i).setResizable(false); // 컬럼 크기 조정 불가능 설정
        }

        // 테이블 헤더 가운데 정렬
        JTableHeader header = table.getTableHeader();
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        header.setFont(new Font("맑은 고딕", Font.BOLD, 22));
        header.setBackground(new Color(22, 40, 80));
        header.setForeground(Color.WHITE); // 글자색 설정
        header.setPreferredSize(new Dimension(700, 40)); // 헤더 높이 설정
        header.setReorderingAllowed(false); // 컬럼 이동 불가능 설정

        // 헤더 구분선 제거
        header.setUI(new BasicTableHeaderUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                // 배경 및 텍스트 그리기
                super.paint(g, c);
            }
        });

        // 테이블의 그리드 색상 제거
        table.setOpaque(true); // 배경색이 나타날 수 있도록 설정
        table.setFillsViewportHeight(true); // 테이블이 스크롤 팬을 채우도록 설정
        table.setBackground(Color.WHITE); // 빈 영역의 배경색을 하얀색으로 설정
        table.setShowGrid(false);

        // 스크롤 팬 추가
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15, 15, 700, 520); // 패널 내부 위치와 크기 설정

        page1.add(scrollPane);
        paymentPanel.add(page1);

        JPanel page2 = new RoundPanelTool(15, Color.WHITE);
        page2.setLayout(null);
        page2.setBackground(new Color(246, 247, 251));
        page2.setBounds(745, 0, 355, 850);

        JLabel storeName = new JLabel("북스토어 노원점");
        storeName.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        storeName.setForeground(new Color(22, 40, 80));
        storeName.setBounds(30, 30, 300, 40);
        page2.add(storeName);
        
        Font font2 = new Font("맑은 고딕", Font.BOLD, 15);

        // 구매목록, 수량, 금액 레이블 추가
        JLabel itemListLabel = new JLabel("구매목록");
        itemListLabel.setFont(font2);
        itemListLabel.setBounds(30, 80, 100, 30);
        page2.add(itemListLabel);

        JLabel quantityLabel = new JLabel("수량");
        quantityLabel.setFont(font2);
        quantityLabel.setBounds(190, 80, 50, 30);
        page2.add(quantityLabel);

        JLabel amountLabel = new JLabel("금액");
        amountLabel.setFont(font2);
        amountLabel.setBounds(290, 80, 100, 30);
        page2.add(amountLabel);
        
        
        table.addMouseListener(new MouseAdapter() {
        	int num = 0;
            @Override
            public void mouseReleased(MouseEvent e) {
                int row = table.getSelectedRow();
                // 데이터 출력
                for(int i = 0; i < num; i++) {               	
                	System.out.println(num++);
                	JPanel productList = new JPanel();
                	productList.setLayout(null);
                	productList.setBackground(Color.BLACK);
                	productList.setBounds(0,120,355,40);
                	page2.add(productList);
                	page2.revalidate();
                	page2.repaint();
                }
                
                System.out.println(row+1);
                
                // 선택된 행에서 데이터 가져오기
                String itemNumber = table.getValueAt(row, 0).toString();
                String itemName = table.getValueAt(row, 1).toString();
                String author = table.getValueAt(row, 2).toString();
                String publisher = table.getValueAt(row, 3).toString();
                String price = table.getValueAt(row, 4).toString();

                    
                
            }
        });
        
        // 점선 구분선 추가
        JPanel dottedLine1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.BLACK);
                float[] dashPattern = {5, 5}; // 점선 패턴 (길이, 간격)
                g2.setStroke(new java.awt.BasicStroke(1, java.awt.BasicStroke.CAP_BUTT, java.awt.BasicStroke.JOIN_MITER, 10, dashPattern, 0));
                g2.drawLine(0, 0, getWidth(), 0);
            }
        };
        dottedLine1.setBounds(30, 115, 295, 2); // 점선 위치와 크기 설정
        page2.add(dottedLine1);
        
        //구매 항목들 상세 설명
        
        
        
        
        
        
     // 점선 구분선 추가
        JPanel dottedLine2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.BLACK);
                float[] dashPattern = {5, 5}; // 점선 패턴 (길이, 간격)
                g2.setStroke(new java.awt.BasicStroke(1, java.awt.BasicStroke.CAP_BUTT, java.awt.BasicStroke.JOIN_MITER, 10, dashPattern, 0));
                g2.drawLine(0, 0, getWidth(), 0);
            }
        };
        dottedLine2.setBounds(30, 760, 295, 2); // 점선 위치와 크기 설정
        page2.add(dottedLine2);
        
        // "합계", "수량", "총합금액" 레이블 추가
        JLabel totalLabel = new JLabel("합계");
        totalLabel.setFont(font2);
        totalLabel.setBounds(30, 770, 100, 30);
        page2.add(totalLabel);

        JLabel quantityTotalLabel = new JLabel("100개");
        quantityTotalLabel.setFont(font2);
        quantityTotalLabel.setBounds(180, 770, 50, 30);
        page2.add(quantityTotalLabel);

        JLabel amountTotalLabel = new JLabel("140000");
        amountTotalLabel.setFont(font2);
        amountTotalLabel.setForeground(Color.RED);
        amountTotalLabel.setBounds(250, 770, 100, 30);
        page2.add(amountTotalLabel);

        // 점선 구분선 추가
        JPanel dottedLine3 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.BLACK);
                float[] dashPattern = {5, 5}; // 점선 패턴 (길이, 간격)
                g2.setStroke(new java.awt.BasicStroke(1, java.awt.BasicStroke.CAP_BUTT, java.awt.BasicStroke.JOIN_MITER, 10, dashPattern, 0));
                g2.drawLine(0, 0, getWidth(), 0);
            }
        };
        dottedLine3.setBounds(30, 810, 295, 2); // 점선 위치와 크기 설정
        page2.add(dottedLine3);
        
        cash.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseReleased(MouseEvent e) {
        		 JDialog cashScreen = new PaymentDialog().createCashDialog(amountTotalLabel.getText());        		 
        	}
		});
        
        card.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseReleased(MouseEvent e) {
        		JDialog cardScreen = new PaymentDialog().createCardDialog(amountTotalLabel.getText(),true);
        		
        	}
		});
        
        split.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseReleased(MouseEvent e) {
        		JDialog cardScreen = new PaymentDialog().createSplitDialog(amountTotalLabel.getText());

        	}
		});
     

        paymentPanel.add(page2);

        return paymentPanel;
    }
}
