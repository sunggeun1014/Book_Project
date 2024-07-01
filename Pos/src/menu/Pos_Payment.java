package menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;
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

        ButtonTool qty = ButtonTool.createButton("적립", new Color(22, 40, 80), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25), 15, 15, true);
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
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(null);
        tableScrollPane.setBounds(15, 15, 700, 520);
        tableScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(5, 0));
        tableScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.GRAY;
                this.trackColor = Color.LIGHT_GRAY;
            }
        });
        
        JScrollBar tableverticalScrollBar = tableScrollPane.getVerticalScrollBar();
        tableverticalScrollBar.setUnitIncrement(16);
        tableverticalScrollBar.setBlockIncrement(100);

        page1.add(tableScrollPane);
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
        
        JPanel listboard = new JPanel();
        listboard.setLayout(null);
        listboard.setBackground(Color.WHITE);

        JScrollPane listScrollPane = new JScrollPane(listboard);
        listScrollPane.setBorder(null);
        listScrollPane.setBounds(10, 120, 335, 640);
        listScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(5, 0));
        listScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.GRAY;
                this.trackColor = Color.LIGHT_GRAY;
            }
        });
        
        JScrollBar verticalScrollBar = listScrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(16);
        verticalScrollBar.setBlockIncrement(100);

        page2.add(listScrollPane);

        // 클릭된 행을 추적할 리스트와 각 행의 패널, JLabel 및 JLabel 맵을 저장
        List<Integer> clickedRows = new ArrayList<>();
        Map<Integer, JLabel> rowToQtyLabelMap = new HashMap<>();
        Map<Integer, JLabel> rowToPriceLabelMap = new HashMap<>();
        Map<JPanel, Integer> panelToRowMap = new HashMap<>();
        AtomicInteger totalQty = new AtomicInteger(0);
        AtomicInteger totalAmount = new AtomicInteger(0);

        JLabel quantityTotalLabel = new JLabel("0개");
        quantityTotalLabel.setFont(font2);
        quantityTotalLabel.setBounds(180, 770, 50, 30);

        JLabel amountTotalLabel = new JLabel("0원");
        amountTotalLabel.setFont(font2);
        amountTotalLabel.setForeground(Color.RED);
        amountTotalLabel.setBounds(250, 770, 100, 30);

        JLabel totalLabel = new JLabel("합계");
        totalLabel.setFont(font2);
        totalLabel.setBounds(30, 770, 100, 30);

        page2.add(totalLabel);
        page2.add(quantityTotalLabel);
        page2.add(amountTotalLabel);

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                
                int row = table.getSelectedRow();
                
                if (row == -1) {
                    return;
                }

                // 선택된 행에서 데이터 가져오기
                String itemName = table.getValueAt(row, 1).toString();
                String priceStr = table.getValueAt(row, 4).toString();
                
                int price = Integer.parseInt(priceStr.replaceAll("[^\\d.]", ""));

                if (!clickedRows.contains(row)) {
                    clickedRows.add(row);
                    
                    JPanel productList = new JPanel();
                    productList.setLayout(null);
                    productList.setBackground(Color.WHITE);
                    productList.setBounds(0, 40 * (clickedRows.size() - 1), 325, 40);

                    JLabel productName = new JLabel(itemName);
                    productName.setForeground(new Color(22, 40, 80));
                    productName.setBounds(20, 0, 100, 40);
                    productList.add(productName);

                    JLabel productQTY = new JLabel("1");
                    productQTY.setForeground(new Color(22, 40, 80));
                    productQTY.setBounds(190, 0, 20, 40);
                    productList.add(productQTY);
                    rowToQtyLabelMap.put(row, productQTY);

                    JLabel productPrice = new JLabel(priceStr);
                    productPrice.setForeground(new Color(22, 40, 80));
                    productPrice.setBounds(270, 0, 60, 40);
                    productList.add(productPrice);
                    rowToPriceLabelMap.put(row, productPrice);
                    panelToRowMap.put(productList, row);
           
                    listboard.add(productList);
                    listboard.revalidate();
                    listboard.repaint();

                    // 선택 시 배경색 변경
                    productList.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                        	if (e.isControlDown()) { // Ctrl 키가 눌려진 경우 다중 선택 가능
                                if (productList.getBackground() == Color.LIGHT_GRAY) {
                                    productList.setBackground(Color.WHITE); // 이미 선택된 경우 다시 원래색으로 변경
                                } else {
                                    productList.setBackground(Color.LIGHT_GRAY); // 선택되지 않은 경우 회색으로 변경
                                }
                            } else { // Ctrl 키가 눌리지 않은 경우 단일 선택만 가능
                                // 모든 리스트의 배경색을 원래색으로 변경
                                for (Component comp : listboard.getComponents()) {
                                    if (comp instanceof JPanel) {
                                        comp.setBackground(Color.WHITE);
                                    }
                                }
                                // 선택된 리스트만 회색으로 변경
                                productList.setBackground(Color.LIGHT_GRAY);
                            }
                        }
                    });
                } else {
                    JLabel productQTY = rowToQtyLabelMap.get(row);
                    int currentQty = Integer.parseInt(productQTY.getText());
                    productQTY.setText(String.valueOf(currentQty + 1));

                    JLabel productPrice = rowToPriceLabelMap.get(row);
                    int currentPrice = Integer.parseInt(productPrice.getText().replaceAll("[^\\d.]", ""));
                    int newPrice = currentPrice + price;
                    productPrice.setText(newPrice + "원");

                    productQTY.revalidate();
                    productQTY.repaint();
                    productPrice.revalidate();
                    productPrice.repaint();
                                          
                }

                // 총 수량 및 총 금액 업데이트
                totalQty.incrementAndGet();
                totalAmount.addAndGet(price);

                quantityTotalLabel.setText(totalQty + "개");
                amountTotalLabel.setText(totalAmount + "원");                

                // listboard 크기를 패널의 총 높이에 맞게 조정
                listboard.setPreferredSize(new Dimension(325, 40 * clickedRows.size()));
                listboard.revalidate();
                listboard.repaint();

                page2.revalidate();
                page2.repaint();
                                   
            }            
            
        });

        allCancle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                listboard.removeAll(); // page2의 모든 컴포넌트 제거
                clickedRows.clear(); // 클릭된 행 리스트 초기화
                rowToQtyLabelMap.clear(); // 라벨 맵 초기화
                rowToPriceLabelMap.clear(); // 가격 라벨 맵 초기화 
                panelToRowMap.clear();
                totalQty.set(0);
                totalAmount.set(0);
                quantityTotalLabel.setText("0개");
                amountTotalLabel.setText("0원");
                listboard.revalidate();
                listboard.repaint();
                page2.revalidate();
                page2.repaint();
            }
        });

        selectCancle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                List<Integer> rowsToRemove = new ArrayList<>();

                // 회색으로 선택된 패널 찾기
                for (Component comp : listboard.getComponents()) {
                    if (comp instanceof JPanel && comp.getBackground().equals(Color.LIGHT_GRAY)) {
                        JPanel selectedPanel = (JPanel) comp;
                        int row = panelToRowMap.get(selectedPanel);
                        rowsToRemove.add(row);
                    }
                }

                // 선택된 패널들을 삭제 처리
                for (int row : rowsToRemove) {
                    clickedRows.remove((Integer) row);
                    JLabel productQTY = rowToQtyLabelMap.get(row);
                    JLabel productPrice = rowToPriceLabelMap.get(row);
                    int qty = Integer.parseInt(productQTY.getText());
                    int price = Integer.parseInt(productPrice.getText().replaceAll("[^\\d.]", ""));
                    totalQty.addAndGet(-qty);
                    totalAmount.addAndGet(-price);
                    quantityTotalLabel.setText(totalQty + "개");
                    amountTotalLabel.setText(totalAmount + "원");

                    // 해당 row에 해당하는 패널 제거
                    for (Component comp : listboard.getComponents()) {
                        if (comp instanceof JPanel) {
                            JPanel panel = (JPanel) comp;
                            int panelRow = panelToRowMap.get(panel);
                            if (panelRow == row) {
                                listboard.remove(panel);
                                rowToQtyLabelMap.remove(row);
                                rowToPriceLabelMap.remove(row);
                                panelToRowMap.remove(panel);
                                break;
                            }
                        }
                    }
                }

                // 남은 패널을 앞으로 당기기
                int index = 0;
                for (Component comp : listboard.getComponents()) {
                    if (comp instanceof JPanel) {
                        comp.setBounds(0, 40 * index, 325, 40);
                        index++;
                    }
                }

                listboard.revalidate();
                listboard.repaint();
                page2.revalidate();
                page2.repaint();
            }
        });

        
        cash.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseReleased(MouseEvent e) {
        		JDialog cashScreen = new PaymentDialog().createCashDialog(String.valueOf(totalAmount));
        		System.out.println(String.valueOf(amountTotalLabel.getText()));
        	}
        });
        
        card.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseReleased(MouseEvent e) {
        		JDialog cardScreen = new PaymentDialog().createCardDialog(String.valueOf(totalAmount),true);
        		
        	}
        });
        
        split.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseReleased(MouseEvent e) {
        		JDialog cardScreen = new PaymentDialog().createSplitDialog(String.valueOf(totalAmount));
        		
        	}
        });
        
        plus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                for (Component comp : listboard.getComponents()) {
                    // listboard 컴포넌트들 중에서 JPanel이고 배경색이 LIGHT_GRAY인 패널을 찾습니다.
                    if (comp instanceof JPanel && comp.getBackground().equals(Color.LIGHT_GRAY)) {
                        JPanel selectedPanel = (JPanel) comp;
                        // 선택된 패널에 대응하는 행 번호를 가져옵니다.
                        int row = panelToRowMap.get(selectedPanel);
                        // 해당 패널의 수량 라벨을 가져옵니다.
                        JLabel productQTY = rowToQtyLabelMap.get(row);

                        // 현재 수량을 가져와 1을 더하여 라벨에 설정합니다.
                        int currentQty = Integer.parseInt(productQTY.getText());
                        productQTY.setText(String.valueOf(currentQty + 1));

                        // 전체 수량을 증가시킵니다 (수량을 증가시킬 때만 증가).
                        totalQty.incrementAndGet();

                        // 해당 제품의 가격 라벨을 가져옵니다.
                        JLabel productPriceLabel = rowToPriceLabelMap.get(row);

                        // 개수가 1일 때의 가격을 가져옵니다.
                        int unitPrice = Integer.parseInt(table.getValueAt(row, 4).toString().replaceAll("[^\\d.]", ""));
                        int newPrice = unitPrice * (currentQty + 1);

                        // 가격 라벨을 업데이트합니다.
                        productPriceLabel.setText(newPrice + "원");

                        // 총 금액을 업데이트합니다 (수량 증가시에만 고정된 개수의 가격을 추가).
                        totalAmount.set(totalAmount.get() + unitPrice);

                        // 총 수량과 총 금액을 나타내는 라벨을 업데이트합니다.
                        quantityTotalLabel.setText(totalQty + "개");
                        amountTotalLabel.setText(totalAmount.get() + "원");

                        // 수량과 금액 라벨을 다시 그리기합니다.
                        productQTY.revalidate();
                        productQTY.repaint();
                        productPriceLabel.revalidate();
                        productPriceLabel.repaint();
                    }
                }

                // 페이지 전체를 다시 그려서 총 수량과 총 금액을 업데이트합니다.
                page2.revalidate();
                page2.repaint();
            }
        });
        
        minus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                for (Component comp : listboard.getComponents()) {
                    // listboard 컴포넌트들 중에서 JPanel이고 배경색이 LIGHT_GRAY인 패널을 찾습니다.
                    if (comp instanceof JPanel && comp.getBackground().equals(Color.LIGHT_GRAY)) {
                        JPanel selectedPanel = (JPanel) comp;
                        // 선택된 패널에 대응하는 행 번호를 가져옵니다.
                        int row = panelToRowMap.get(selectedPanel);
                        // 해당 패널의 수량 라벨을 가져옵니다.
                        JLabel productQTY = rowToQtyLabelMap.get(row);

                        // 현재 수량을 가져와 1을 빼고 라벨에 설정합니다.
                        int currentQty = Integer.parseInt(productQTY.getText());
                        if (currentQty > 1) { // 수량이 1보다 큰 경우에만 감소시킵니다.
                            productQTY.setText(String.valueOf(currentQty - 1));

                            // 전체 수량을 감소시킵니다 (수량 감소시에만 고정된 개수의 가격을 차감).
                            totalQty.decrementAndGet();

                            // 해당 제품의 가격 라벨을 가져옵니다.
                            JLabel productPriceLabel = rowToPriceLabelMap.get(row);

                            // 개수가 1일 때의 가격을 가져옵니다.
                            int unitPrice = Integer.parseInt(table.getValueAt(row, 4).toString().replaceAll("[^\\d.]", ""));
                            int newPrice = unitPrice * (currentQty - 1);

                            // 가격 라벨을 업데이트합니다.
                            productPriceLabel.setText(newPrice + "원");

                            // 총 금액을 업데이트합니다 (수량 감소시에만 고정된 개수의 가격을 차감).
                            totalAmount.set(totalAmount.get() - unitPrice);

                            // 총 수량과 총 금액을 나타내는 라벨을 업데이트합니다.
                            quantityTotalLabel.setText(totalQty + "개");
                            amountTotalLabel.setText(totalAmount.get() + "원");

                            // 수량과 금액 라벨을 다시 그리기합니다.
                            productQTY.revalidate();
                            productQTY.repaint();
                            productPriceLabel.revalidate();
                            productPriceLabel.repaint();
                        }
                    }
                }

                // 페이지 전체를 다시 그려서 총 수량과 총 금액을 업데이트합니다.
                page2.revalidate();
                page2.repaint();
            }
        });


        
        // 점선 구분선 추가
        JPanel dottedLine1 = new JPanel();
        dottedLine1 = dottedLine(page2, 30, 115, 295, 2);
        
        JPanel dottedLine2 = new JPanel();
        dottedLine2 = dottedLine(page2, 30, 760, 295, 2);
        
        JPanel dottedLine3 = new JPanel();
        dottedLine3 = dottedLine(page2, 30, 810, 295, 2);

        paymentPanel.add(page2);

        return paymentPanel;
    }
    
    public JPanel dottedLine(JPanel panel, int x, int y, int wide, int height) {
    	// 점선 구분선 추가
        JPanel dottedLinepanel = new JPanel() {
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
        dottedLinepanel.setBounds(x, y, wide, height); // 점선 위치와 크기 설정
        panel.add(dottedLinepanel);
        
        return dottedLinepanel;
    }
}
