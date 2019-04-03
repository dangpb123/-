package words;

import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.util.Map; 
import java.util.TreeMap; 
import java.util.Map.Entry; 
import javax.swing.JFrame; 
 
/** 
 * @author Zeng Jia, Wang Jing 
 * @data 2019.4.2 
 */ 

 public class Histogram extends JFrame{ 
 	Map<String, Integer> map = new TreeMap<String, Integer>(); 
 	int temp; 
 	public Histogram(final Map<String, Integer> maps,int j) 
 	{ 
 		setTitle("绘制柱形图"); 
 		setBounds(200,200,600,640); 
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
 		 
 		for (Entry<String, Integer> entry : maps.entrySet())  
        	{  
        		  
         		map.put(entry.getKey(), entry.getValue()); 
           
          }  
 	} 
 	public void paint(Graphics g) 
 	{ 
 		int Width = getWidth(); 
 		int Height = getHeight(); 
 		int leftMargin = 50;//柱形图左边界 
 		int topMargin = 50;//柱形图上边界 
 		Graphics2D g2 = (Graphics2D) g; 
 		int ruler = Height-topMargin; 
 		int rulerStep = ruler/20;//将当前的高度平分为20个单位 
 		g2.setColor(Color.WHITE);//绘制白色背景 
 		g2.fillRect(0, 0, Width, Height);//绘制矩形图 
 		g2.setColor(Color.BLACK); 
 		for(int i=0;i<rulerStep;i++){ 
 		g2.drawString((30000-1500*i)+"个", 8, topMargin+rulerStep*i);//绘制Y轴上的数据 
 		} 
 		g2.setColor(Color.YELLOW); 
 		int m=0; 
 		for (Entry<String, Integer> entry : map.entrySet())  
        	{  
 			int value =entry.getValue(); 
 			int step = (m+1)*40;//设置每隔柱形图的水平间隔为40 
 			g2.fillRoundRect(leftMargin+step*2,Height-value/50-5, 40, value, 40, 10);//绘制每个柱状条 
 			g2.drawString(entry.getKey(), leftMargin+step*2, Height-value/50-5);	//标识每个柱状条		 
             m++; 
          }  
 		  
 	}  
 } 
