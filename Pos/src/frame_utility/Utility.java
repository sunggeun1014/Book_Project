package frame_utility;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Utility {

	public JPanel getRoundShape(int num1, int num2) {
		JPanel jpanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				int width = getWidth();
				int height = getHeight();
				
				g2.setColor(getBackground());
				g2.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, num1, num2));
				
				super.paintComponent(g);
				
				g2.dispose();
			}
		};
		jpanel.setOpaque(false);
		jpanel.setLayout(null);
		
		return jpanel;
	}
	
	public JLabel getRoundShapeLabel(int num1, int num2) {
		JLabel jLabel = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				int width = getWidth();
				int height = getHeight();
				
				g2.setColor(getBackground());
				g2.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, num1, num2));
				
				super.paintComponent(g);
				
				g2.dispose();
			}
		};
		jLabel.setOpaque(false);
		jLabel.setLayout(null);
		
		return jLabel;
	}
	
	public JTextField getRoundShapeTextField(int num1, int num2, Color color) {
		JTextField textField = new JTextField() {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				int width = getWidth();
				int height = getHeight();
				
				g2.setColor(color);
				g2.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, num1, num2));
				
				super.paintComponent(g);
				
				g2.dispose();
			}
		};
		textField.setOpaque(false);
		textField.setLayout(null);
		
		return textField;
	}
	
	public boolean isPhoneNumber(String phoneNumber) {
		return Pattern.matches("010-\\d{4}-\\d{4}", phoneNumber);
	}
	
	public String priceFormat(int price) {
		DecimalFormat df = new DecimalFormat("#,###");
		
		return df.format(price);
	}
	
}