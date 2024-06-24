package menu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

class RoundPanelTool extends JPanel {
	
	
    private Color backgroundColor;
    private int cornerRadius; // 라운딩 처리할 반지름 크기
    private int borderWidth; // 경계선 두께
    
    public RoundPanelTool(int radius, Color bgColor) {
    	super();
    	cornerRadius = radius;
    	backgroundColor = bgColor;
    }

    public RoundPanelTool(LayoutManager layout, int radius) {
        super(layout);
        cornerRadius = radius;
    }

    public RoundPanelTool(LayoutManager layout, int radius, Color bgColor) {
        super(layout);
        cornerRadius = radius;
        backgroundColor = bgColor;
    }

    public RoundPanelTool(int radius) {
        super();
        cornerRadius = radius;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 배경색 설정
        if (backgroundColor != null) {
            graphics.setColor(backgroundColor);
        } else {
            graphics.setColor(getBackground());
        }

        // 둥근 사각형 그리기
        graphics.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, arcs.width, arcs.height));

        // 경계선 설정
        graphics.setStroke(new BasicStroke(borderWidth));
        graphics.setColor(new Color(226,227,230));
        graphics.draw(new RoundRectangle2D.Double(borderWidth / 2, borderWidth / 2,
                width - borderWidth, height - borderWidth, arcs.width, arcs.height));
    }
    

    @Override
    public Insets getInsets() {
    	int top = borderWidth / 2;
        int left = borderWidth / 2;
        int bottom = borderWidth / 2;
        int right = borderWidth / 2;
        return new Insets(top, left, bottom, right);    
    }

}
