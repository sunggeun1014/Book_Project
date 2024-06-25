package menu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import frame_utility.ButtonTool;
import frame_utility.RoundPanelTool;
import frame_utility.TableTool;


public class Pos_Payment {
	public JPanel creatPayment () {
		JPanel paymentPanel = new JPanel();
		paymentPanel.setBackground(new Color(246,247,251));
		paymentPanel.setLayout(null);
  
		
		ButtonTool allCancle = ButtonTool.createButton("전체취소", new Color(22, 40, 80), Color.WHITE, new Color(79, 163, 252) ,new Font("돋음", Font.BOLD, 25),15,15,true);
		allCancle.setBounds(0,560,180,90);
		paymentPanel.add(allCancle);
		
		ButtonTool selectCancle = ButtonTool.createButton("선택취소", new Color(22, 40, 80), Color.WHITE, new Color(79, 163, 252) ,new Font("돋음", Font.BOLD, 25),15,15,true);
		selectCancle.setBounds(190,560,180,90);
		paymentPanel.add(selectCancle);
		
		ButtonTool plus = ButtonTool.createButton("+", new Color(22, 40, 80), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25),15,15,true);		
		plus.setBounds(380,560,70,90);
		paymentPanel.add(plus);
		
		ButtonTool minus = ButtonTool.createButton("-", new Color(22, 40, 80), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25),15,15,true);
		minus.setBounds(460,560,70,90);
		paymentPanel.add(minus);
		
		ButtonTool qty = ButtonTool.createButton("수량", new Color(22, 40, 80), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25),15,15,true);
		qty.setBounds(540,560,190,90);
		paymentPanel.add(qty);
		
		ButtonTool cash = ButtonTool.createButton("현금", new Color(106, 118, 147), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25),15,15,true);
		cash.setBounds(0,660,175,90);
		paymentPanel.add(cash);
		
		ButtonTool split = ButtonTool.createButton("분할계산", new Color(106, 118, 147), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25),15,15,true);
		split.setBounds(185,660,175,90);
		paymentPanel.add(split);
		
		ButtonTool card = ButtonTool.createButton("신용카드", new Color(106, 118, 147), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25),15,15,true);
		card.setBounds(370,660,175,90);
		paymentPanel.add(card);
		
		ButtonTool discount = ButtonTool.createButton("할인혜택", new Color(106, 118, 147), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25),15,15,true);
		discount.setBounds(555,660,175,90);
		paymentPanel.add(discount);
		
		ButtonTool calculator = ButtonTool.createButton("계산기", new Color(106, 118, 147), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25),15,15,true);
		calculator.setBounds(0,760,175,90);
		paymentPanel.add(calculator);
		
		ButtonTool receipt = ButtonTool.createButton("영수증", new Color(106, 118, 147), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25),15,15,true);
		receipt.setBounds(185,760,175,90);
		paymentPanel.add(receipt);
		
		ButtonTool cashreceipt = ButtonTool.createButton("현금영수증", new Color(106, 118, 147), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25),15,15,true);
		cashreceipt.setBounds(370,760,175,90);
		paymentPanel.add(cashreceipt);
		
		ButtonTool refund = ButtonTool.createButton("환불", new Color(106, 118, 147), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25),15,15,true);
		refund.setBounds(555,760,175,90);
		paymentPanel.add(refund);
		
		JPanel page1 = new RoundPanelTool(15, Color.WHITE);
		page1 = new TableTool().createTable();		
		page1.setLayout(null);
	    page1.setBackground(new Color(246,247,251));
	    page1.setBounds(0,0,730,550);
	    
	    paymentPanel.add(page1);
	    
	    
   	    
	    JPanel page2 = new RoundPanelTool(15, Color.WHITE);
	    page2.setLayout(null);
	    page2.setBackground(new Color(246,247,251));
	    page2.setBounds(745,0,355,850);
	    paymentPanel.add(page2);
	    
	    
	    ButtonTool print = ButtonTool.createButton("출력", new Color(106, 118, 147), Color.WHITE, new Color(79, 163, 252), new Font("돋음", Font.BOLD, 25),15,15,true);
	    print.setBounds(90,740,175,90);
	    page2.add(print);
	    
		
	    return paymentPanel;
		
		
		
	}

}
