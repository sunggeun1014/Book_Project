package menu;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicScrollBarUI;

import frame_utility.ButtonTool;
import frame_utility.RoundPanelTool;
import frame_utility.Utility;
import menu.bookmanagement.BookInfoDTO;
import menu.bookmanagement.BookInfoQuery;

public class Pos_BookManagement {
   private Utility u = new Utility();
   private BookInfoQuery bookInv = new BookInfoQuery();
   private static ButtonTool[] buttons;
   private JPanel dataPanel;
   private JDialog popup;
   

   public JPanel createBookManagement() {
      JPanel bookmanagementPanel = new JPanel();
      bookmanagementPanel.setBackground(new Color(246,247,251));
      bookmanagementPanel.setLayout(null);
      
      RoundedTextField search = new RoundedTextField(15, "이름, 바코드, 속성 검색", 15, 15);
      search.setBounds(5, 30, 400, 40);
      search.setForeground(Color.GRAY);
      search.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
      search.setFont(new Font("맑은 고딕", Font.BOLD, 16));
      
      search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               removeMemberArea(search.getText());
            }
        });
      bookmanagementPanel.add(search);
      
      
      buttons = new ButtonTool[3];
      buttons[0] = ButtonTool.createButton("수정", new Color(246,247,251), new Color(197,195,195), new Color(180, 181, 184), new Font("맑은 고딕", Font.BOLD, 18),15,15,true);
      buttons[0].setBounds(420, 30, 40, 40);
      
      buttons[1] = ButtonTool.createButton("등록", new Color(246,247,251), new Color(197,195,195), new Color(180, 181, 184), new Font("맑은 고딕", Font.BOLD, 18),15,15,true);
      buttons[1].setBounds(470, 30, 40, 40);
      
      buttons[2] = ButtonTool.createButton("삭제", new Color(246,247,251), new Color(197,195,195), new Color(180, 181, 184), new Font("맑은 고딕", Font.BOLD, 18),15,15,true);
      buttons[2].setBounds(520, 30, 40, 40);
      
      dataPanel = new RoundPanelTool(15, Color.WHITE);
      dataPanel.setLayout(null);
      dataPanel.setBackground(new Color(246,247,251));
      dataPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
      dataPanel.setBounds(5, 80, 1080, 700);
      
      for(int i = 0; i < buttons.length; i++) {
            int index = i;
            
            buttons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    buttonChangeColor(buttons[index]);
//                    updateDisplay(index, dataPanel);
                }
            });
            bookmanagementPanel.add(buttons[i]);
        }
      
       buttonChangeColor(buttons[0]);
//        updateDisplay(0, dataPanel);  // 첫 화면은 '수정' 띄워주기
      
        addColName(dataPanel);
        bookInfo("");
        
       bookmanagementPanel.add(dataPanel);

       return bookmanagementPanel;
   }
   
   
   private static void buttonChangeColor(ButtonTool button) {
      button.setForeground(Color.BLACK);
      for(ButtonTool btn : buttons) {
         if(btn.hashCode() != button.hashCode()) {
            btn.setBackground(new Color(246,247,251));
            btn.setForeground(new Color(197,195,195));
         }
      }
   }
   
//   private void updateDisplay(int index, JPanel dataPaenl) {
//      
//      switch (index) {
//         case 0:
//            
//      }
//   }
   
   
   private void addColName(JPanel panel) {
      
      JLabel[] colNames = new JLabel[8];
      colNames[0] = new JLabel("썸네일");
      colNames[1] = new JLabel("책 제목");
      colNames[2] = new JLabel("상품코드");
      colNames[3] = new JLabel("작가");
      colNames[4] = new JLabel("출판사");
      colNames[5] = new JLabel("카테고리");
      colNames[6] = new JLabel("위치");
      colNames[7] = new JLabel("판매가");
      
      int[] x = {40, 140, 330, 480, 600, 750, 880, 980};
       int[] widths = {90, 150, 150, 120, 130, 80, 100, 100};

      for (int i = 0; i < colNames.length; i++) {
         colNames[i].setBounds(x[i], 0, widths[i], 50);
         colNames[i].setBackground(new Color(246, 247, 251));
         colNames[i].setFont(new Font("맑은 고딕", Font.BOLD, 17));
         colNames[i].setForeground(new Color(22, 40, 80));
         panel.add(colNames[i]);
      }
   }
   
   private void bookInfo(String word) {
      JPanel panel = new JPanel();
      panel.setBackground(Color.WHITE);
       panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
       panel.setSize(1080, 800);
      
      List<BookInfoDTO> list = bookInv.getBookInv(word);
      
      for(BookInfoDTO dto : list) {
         JLabel[] info = new JLabel[8];
         
         for (int i = 0; i < info.length - 1; i++) {
            info[i] = new JLabel();
         }
         info[info.length - 1] = u.getRoundShapeLabel(0, 0);
         
         JPanel row = new JPanel();
         
         row.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(238, 238, 238)));
         row.setLayout(null);
         row.setPreferredSize(new Dimension(1000, 80));
         row.setOpaque(false);
         
         info[0].setIcon(dto.getThumbnail());
         info[1].setText(dto.getBookTitle());
         info[2].setText(dto.getBookIsbn());
         info[3].setText(dto.getAuthor());
         info[4].setText(dto.getPublisher());
         info[5].setText(dto.getCategory());
         info[6].setText(dto.getBookLocation());
         info[7].setText(dto.getPrice().toString());

         int[] x = {30, 140, 330, 480, 600, 750, 880, 980};
         int[] widths = {90, 150, 150, 120, 130, 130, 100, 100};
         
         addLabelListener(row);

         for (int i = 0; i < info.length; i++) {
            info[i].setBackground(Color.WHITE);
             info[i].setBounds(x[i], 3, widths[i], 73);
             info[i].setForeground(Color.BLACK);
             info[i].setFont(new Font("맑은 고딕", Font.PLAIN, 15));
            row.add(info[i]);
         }
         
         panel.add(row);
         
      }
      
      JScrollPane scrollPane = new JScrollPane(panel);
      scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        scrollPane.setBounds(0, 50, 1080, 650);
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

        dataPanel.add(scrollPane);
   }
   
   private void removeMemberArea(String word) {
      dataPanel.removeAll();
       
       addColName(dataPanel);
       bookInfo(word);

       dataPanel.revalidate();
       dataPanel.repaint();
   }
   
   
   private void addLabelListener(JPanel row) {
      row.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
               row.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
               row.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
               popup = setJFrameOption(new JDialog(), 510, 300);
              addbookModifyJPanel(popup, "");
               popup.setVisible(true);
            }
        });
   }
   
   private JDialog setJFrameOption(JDialog frame, int width, int height) {
      frame.setUndecorated(false);
      frame.setSize(width, height);
      frame.getContentPane().setLayout(null);
      frame.getContentPane().setBackground(Color.WHITE);
       frame.setLocationRelativeTo(null);
       
       return frame;
   }

   private JDialog addbookModifyJPanel(JDialog frame, String invId) {
      String[] headerTitles = {"상품코드", "위치", "판매가"};
      int[] x = {60, 230, 370};

      JLabel[] headers = new JLabel[headerTitles.length];

      for (int i = 0; i < headerTitles.length; i++) {
          headers[i] = new JLabel(headerTitles[i]);
          headers[i].setBounds(x[i], 30, 100, 30);
          headers[i].setFont(new Font("맑은 고딕", Font.BOLD, 18));
          headers[i].setForeground(new Color(22, 40, 80));
          frame.getContentPane().add(headers[i]);
      }
      
      JPanel panel = u.getRoundShape(14, 14);
      
      panel.setLayout(null);
      panel.setBackground(Color.WHITE);
      panel.setBounds(10, 80, 480, 50);
      
      JTextField[] textBox = new JTextField[3];

      int[] xPosition = {20, 170, 320};

      for(int i = 0; i < textBox.length; i++) {
         textBox[i] = new JTextField();
         
         textBox[i].setBounds(xPosition[i], 0, 140, 40);
         textBox[i].setBackground(new Color(222, 222, 222));
         
         panel.add(textBox[i]);
         
         textBox[i].setFont(new Font("맑은 고딕", Font.PLAIN, 14));
         
         panel.add(textBox[i]);
         frame.getContentPane().add(panel);
      }
      
      BookInfoDTO dto = bookInv.getBookInfo(invId);
      
      textBox[0].setText(dto.getBookIsbn());
      textBox[1].setText(dto.getBookLocation());
      
      textBox[0].setEditable(true);
      textBox[1].setEditable(true);

      
      JLabel label = new JLabel("상품을 수정하시겠습니까?");
      label.setBounds(130, 157, 266, 30);
      label.setBackground(Color.LIGHT_GRAY);
      label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      label.setForeground(new Color(22, 40, 80));

      frame.getContentPane().add(label);
      
      buttons = new ButtonTool[2];
      buttons[0] = ButtonTool.createButton("네", new Color(22, 40, 80), Color.WHITE, new Color(180, 181, 184), new Font("맑은 고딕", Font.BOLD, 18),15,15,true);
      buttons[0].setBounds(80, 200, 160, 40);
      
      buttons[1] = ButtonTool.createButton("아니오", new Color(22, 40, 80), Color.WHITE, new Color(180, 181, 184), new Font("맑은 고딕", Font.BOLD, 18),15,15,true);
      buttons[1].setBounds(260, 200, 160, 40);
      
      for(int i = 0; i < buttons.length; i++) {
            int index = i;
            
            buttons[i].addMouseListener(new MouseAdapter() {
               @Override
              public void mouseEntered(MouseEvent e) {
                 buttons[index].setCursor(new Cursor(Cursor.HAND_CURSOR));
              }
               
               @Override
              public void mouseExited(MouseEvent e) {
                 buttons[index].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
              }
               
//               @Override
//               public void mouseReleased(MouseEvent e) {
//                   if(modifyValidation(textBox)) {
//                      BookInfoDTO dto = new BookInfoDTO();
//                      dto.setBookIsbn(textBox[0].getText()); 
//                      dto.setBookLocation(textBox[1].getText());
//                      dto.setPrice(textBox[2].getText());
//                      
//                      int result = bookInv.modifyBook(dto);
//                      if(result != 0) {
//                         u.popup("수정이 완료되었습니다.", popup, true);
//                      } else {
//                         u.popup("입력이 잘못되었습니다.", popup, false);
//                      }
//                   }
//               }
            });
            frame.getContentPane().add(buttons[i]);
        }
      
      
      
      return frame;
      
   }
   
//   private boolean modifyValidation(JTextField[] field) {
//      if(field[0].getText() == null || field[0].getText().equals("") || field[0].getText().length() != 13) {
//         u.popup("상품코드를 바르게 입력해주세요", popup, false);
//      } else if (field[1].getText() == null || field[1].getText().equals("") || field[1].getText().equals()) {
//         u.popup("위치를 바르게 입력해주세요", popup, false);
//      } else if (field[2].getText() == null || field[2].getText().equals
//   }
//         
//   

   }   
   
   

