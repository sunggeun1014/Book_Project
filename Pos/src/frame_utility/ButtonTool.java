package frame_utility;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Timer;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ButtonTool extends JLabel {
	// 라운딩 정도
    private int arcWidth;
    private int arcHeight;;

    // 라운딩 정도 설정
    public ButtonTool(String text) {
        super(text);
        setOpaque(false); // 배경을 투명하게 설정하여 라운드된 모서리를 그릴 수 있도록 함
    }
    
    

    // 버튼에 들어갈 text, 버튼 배경색, text문구 색, Font (new Font(글꼴, 속성, 크기)생성후 사용
    public static ButtonTool createButton(String buttonName,Color backgroundColor, Color foregroundColor, Color changeColor, Font font,
    		int arcWidth, int arcHeight, boolean check) {
        ButtonTool button = new ButtonTool(buttonName);
        button.arcWidth = arcWidth;
        button.arcHeight = arcHeight;
        button.setOpaque(false);
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        
        button.setFont(font);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                
            }
            //new Color(79, 163, 252);
            @Override
            
            public void mouseReleased(MouseEvent e) {
            	
            	if(check) {
            		Color afterColor = changeColor;
            		button.setBackground(afterColor);
	            	Timer timer = new Timer(100, new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                        // 타이머가 실행될 때의 동작
	                        button.setBackground(backgroundColor);                      
	                    }
	                });
	            	timer.setRepeats(false);
	            	timer.start();
            	}else {
            		Color afterColor = changeColor;
                	button.setBackground(afterColor);
                	button.setForeground(Color.BLACK);
            	}
            }

        });        
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, arcWidth, arcHeight));

        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width += 10;
        size.height += 10;
        return size;
    }
   
    
  
}