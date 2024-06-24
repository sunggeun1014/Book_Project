package menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

	class RoundedTextField extends JTextField {
	    private int arcWidth;
	    private int arcHeight;
	    private String placeholder;
	    private Color borderColor;
	    private boolean hasFocus = false;
	  
	    
	
	    public RoundedTextField(int columns, String placeholder, int arcWidth, int arcHeight) {
	        super(columns);       
	        this.placeholder = placeholder;
	        this.arcWidth = arcWidth;
	        this.arcHeight = arcHeight;
	        setOpaque(false);
	        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); 
	        
	        addFocusListener(new FocusAdapter() {
	        	
	        	@Override
	        	public void focusGained(FocusEvent e) {
		        	hasFocus = true;
		        	if(getText().equals(placeholder)) {
		        		setText("");
		        		setForeground(Color.BLACK);
		        	}
		        	borderColor = Color.GRAY;
		        	repaint();
	        	}
	        	
	        	 @Override
	        	 public void focusLost(FocusEvent e) {
	                 hasFocus = false;
	                 if (new String(getText()).isEmpty()) {
	                     setText(placeholder);
	                     setForeground(Color.GRAY);
	                    
	                 }
	                 borderColor = new Color(157,163,178); 
	                 repaint();
	             }
			});
	        setText(placeholder);
	        setForeground(Color.GRAY);
	        
	    }
	    
	    
	
	    @Override
	    public void paintComponent(Graphics g) {
	        Graphics2D g2 = (Graphics2D) g.create();
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	
	        int width = getWidth();
	        int height = getHeight();
	
	        // 배경 색상 설정
	        g2.setColor(getBackground());
	        g2.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, arcWidth, arcHeight));
	
	        // 텍스트 필드의 테두리 설정
	        g2.setColor(new Color(157,163,178));
	        g2.draw(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, arcWidth, arcHeight));
	
	        g2.dispose();
	
	        super.paintComponent(g);
	    }
	
	    @Override
	    protected void paintBorder(Graphics g) {
	    }
	}
	
	class RoundedPasswordField extends JPasswordField {
	    private int arcWidth = 50;
	    private int arcHeight = 50;
	    private String placeholder;
	    private Color borderColor = Color.GRAY;
	    private boolean hasFocus = false;
	    
	    // 입력가능한 텍스트 개수 = columns , 예시 텍스트 내용 = plaveholder
	    public RoundedPasswordField(int columns, String placeholder) {
	        super(columns);
	        
	        this.placeholder = placeholder;
	        setOpaque(false);
	        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); 
	        
	        addFocusListener(new FocusAdapter() {
	            @Override
	            public void focusGained(FocusEvent e) {
	                hasFocus = true;
	                if (new String(getPassword()).equals(placeholder)) {
	                    setText("");
	                    setForeground(Color.BLACK);
	                    setEchoChar('•');
	                }
	                borderColor = Color.GRAY;
	                repaint();
	            }
	
	            @Override
	            public void focusLost(FocusEvent e) {
	                hasFocus = false;
	                if (new String(getPassword()).isEmpty()) {
	                    setText(placeholder);
	                    setForeground(Color.GRAY);
	                    setEchoChar((char) 0); 
	                }
	                borderColor = new Color(157,163,178); 
	                repaint();
	            }
	        });
	
	        // 초기 상태에서 입력 양식 설정
	        setText(placeholder);
	        setForeground(Color.GRAY);
	        setEchoChar((char) 0); 
	    }
	
	    @Override
	    public void paintComponent(Graphics g) {
	        Graphics2D g2 = (Graphics2D) g.create();
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	
	        int width = getWidth();
	        int height = getHeight();
	
	        // 배경 색상 설정
	        g2.setColor(getBackground());
	        g2.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, arcWidth, arcHeight));
	
	        // 텍스트 필드의 테두리 설정
	        g2.setColor(new Color(157,163,178));
	        g2.draw(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, arcWidth, arcHeight));
	
	        g2.dispose();
	
	        super.paintComponent(g);
	    }
	
	    @Override
	    public void paintBorder(Graphics g) {
	    }
	}

