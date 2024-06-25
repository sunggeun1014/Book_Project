package menu.salestatus;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class SalesChart {

    public static void addChartToPanel(JPanel panel, CategoryDataset dataset) {
        // 데이터셋 생성
       // CategoryDataset dataset = createDataset();

        // 차트 생성
        JFreeChart chart = ChartFactory.createBarChart(
                "", // 차트 제목
                "",   // X 축 레이블
                "",   // Y 축 레이블
                dataset,      // 데이터셋
                PlotOrientation.VERTICAL, // 차트 방향 (수직)
                true,         // 레전드 표시
                false,         // 툴팁 표시
                false         // URL 생성
        );
        // 차트의 배경색을 흰색으로 설정
        chart.setBackgroundPaint(Color.WHITE);
        
        // Plot 설정
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE); // Plot 배경색 설정
        plot.setOutlineVisible(false); // Plot 테두리 제거
        plot.setRangeGridlinePaint(new Color(238, 238, 238));
        plot.setRangeGridlineStroke(new BasicStroke(1.0f)); // 실선 설정
        
        Font font = new Font("맑은 고딕", Font.BOLD, 10); // 한글을 지원하는 폰트로 설정
        chart.getTitle().setFont(font);
        chart.getLegend().setItemFont(font);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickLabelFont(font); // X 축 레이블 폰트 설정
        domainAxis.setLabelFont(font);
        
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setTickLabelFont(font); // Y 축 레이블 폰트 설정
        rangeAxis.setLabelFont(font);

        
        // Renderer 설정 (막대  설정)
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(22,40,80)); // "수입" 시리즈의 막대 색상을 파란색으로 설정
        renderer.setBarPainter(new StandardBarPainter()); // 명암 효과 제거
        renderer.setShadowVisible(false); // 그림자 제거
        
        // 차트 패널 생성 및 JPanel에 추가
        ChartPanel chartPanel = new ChartPanel(chart);
        
        panel.removeAll(); // 기존의 컴포넌트를 모두 제거합니다.
        panel.add(chartPanel); // 새로운 차트 패널을 추가합니다.
        panel.revalidate(); // 패널을 다시 그리도록 요청합니다.
        panel.repaint(); // 패널을 다시 그립니다.
        chartPanel.setBorder(null);
      
    }

    public static CategoryDataset createDataset(java.util.Date startDate, java.util.Date endDate) {
        // 데이터셋 생성
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        int startMonth = startDate.getMonth();
        int endMonth = endDate.getMonth();
        
        // 여기에 데이터 추가 (예: 월별 수지)
        for(int i = startMonth; i <= endMonth; i++) {
        	String month = i+1 + "월";
        	// 매출 DB연동
        	dataset.addValue(10000, "매출", month);
        }

        return dataset;
    }
    
    
}
