package words;

import java.awt.Font; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException; 
import java.io.FileReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList; 
import java.util.Collections; 
import java.util.Comparator; 
import java.util.List; 
import java.util.Map; 
import java.util.TreeMap; 
import java.util.Map.Entry; 
import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JOptionPane; 
import javax.swing.JPanel; 
import javax.swing.JTextField; 
 
/** 
  * @author Zeng Jia, Wang Jing 
  * @data 2019.4.2 
  */ 

public class Main extends JFrame  
 { 
 	//高频词输出按钮 
 	private static JButton highFrequencyButton;  
 	//统计指定单词个数按钮 
 	private static JButton wordCountButton; 
 	//词频写入文件按钮 
 	private static JButton printFile;  
 	//行数单词数统计按钮 
 	private static JButton lineWordCount;  
 	//版面 
 	private static JLabel input; 
 	//框架 
 	private static JFrame statistics;  
 	//文件名 
 	private static JTextField file2;  
 	private static JLabel file ;

 	private static JTextField word2; 
	private static JLabel word;
	
 	private static JTextField number2; 
	private static JLabel number;
 	 
 	public static FileReader fr; 
 	static BufferedReader br; 
 	//行数 
	static int rowNumber=0;  
 	//单词数 
 	static int wordNumber=0; 
 	//统计行数单词数所用时间 
 	static long time; 
 	
 	public static Map<String, Integer> map = new TreeMap<String, Integer>(); 
 	/**初始化登陆界面*/ 
 	public Main () 
 	{  
 		//设置字体 
    	    Font font =new Font("黑体", Font.PLAIN, 20);  
    	    statistics=new JFrame("英文文本统计分析小软件"); 
 		statistics.setSize(700, 380); 
 		input=new JLabel(); 
 		 
 		file=new JLabel("导入文件名:"); 
 		file.setBounds(20, 40, 150, 50); 
 		
 		word=new JLabel("输入查询词频单词:"); 
 		word.setBounds(20, 100, 150, 50); 

 		number=new JLabel("输入高频词的个数:"); 
 		number.setBounds(20, 140, 150, 100); 
 		 
 		highFrequencyButton=new JButton("输出前N个高频词");     
 		highFrequencyButton.setBounds(450, 160, 200, 35); 
 		highFrequencyButton.setFont(font); 
 		 
 		wordCountButton=new JButton("输出词频和柱状图"); 
 		wordCountButton.setBounds(450, 100, 200, 35); //230, 150, 200, 50
 		wordCountButton.setFont(font); 

 		printFile=new JButton("词频导出到文件"); 
 		printFile.setBounds(100, 250, 200, 50); 
 		printFile.setFont(font); 
 		 
 		lineWordCount=new JButton("各项统计"); 
 		lineWordCount.setBounds(350, 250, 200, 50); 
 		lineWordCount.setFont(font);  
 
 		//加入文本框 
 		file2 = new JTextField(); 
 		file2.setBounds(150, 40, 250, 40); 

 		word2=new JTextField(); 
 		word2.setBounds(150, 100, 250, 40); 

 		number2=new JTextField(); 
 		number2.setBounds(150, 160, 250, 40); 
 		 
 		input.add(file); 
 		input.add(file2); 
 		input.add(word); 
 		input.add(word2); 
 		input.add(number); 
 		input.add(number2); 
 	  
 		input.add(highFrequencyButton); 
 		input.add(wordCountButton); 
 		input.add(printFile); 
 		input.add(lineWordCount); 
 		 
 		statistics.add(input); 
 		statistics.setVisible(true); 
 		statistics.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
 		statistics.setLocation(300,400); 
 	} 
 	
	/**从指定文件读入单词并统计词频*/ 
     static void FileName(final Map<String, Integer> map) throws IOException 
     { 
 		String file=file2.getText();
 		
 		if (file.isEmpty()) 
 		{ 
 			JOptionPane.showConfirmDialog(null, "请输入文件名！","提示",JOptionPane.DEFAULT_OPTION); 
 		} 
 		else  
 		{ 
 			try  
 			{ 
 				fr= new FileReader(file); 
 			}  
 			catch (FileNotFoundException e2)  
 			{ 
 				e2.printStackTrace(); 
 			}	 
 	    	BufferedReader b = new BufferedReader(fr); 
 	            String value= b.readLine(); 
 	            long start = System.currentTimeMillis(); 
 	            rowNumber=0; 
 	            while (value!= null)  
 	            { 
 	            	//处理标点符号 
	            	String[] words = value.split("[^a-zA-Z]");  
 	            	for (int i = 0; i < words.length; i++)  
 	            	{ 
 	            		//将大写字母转换为小写字母 
 	                      String key = words[i].toLowerCase(); 
 	                		if (key.length() > 0)  
 	                		{ 
 	                      		if (!map.containsKey(key))  
 	                      		{ 
 	                      			wordNumber++; 
 	                          		map.put(key, 1); 
 	                          		}  
 	                      		else  
 	                      		{  
 	                      			int k = map.get(key)+1;// 如果不是第一次出现，就把k值++ 
 	                                map.put(key, k); 
	                          		} 
 	                      		} 
 	                  		}  
 	                value = b.readLine(); 
 	                rowNumber++; 
 	            } 
 	            long end=System.currentTimeMillis(); 
 	            time=end-start; 
 		}    
 	} 
 	public static void main(String[] args) 
 	{ 
 	    Main main = new Main(); 
 	    /**打开查看前N个高频词界面*/ 
 		highFrequencyButton.addActionListener(new ActionListener() 
 		{ 
         public void actionPerformed(ActionEvent event) 
         { 
            if (event.getSource()==highFrequencyButton) 
            {   
 			   try  
			   { 
 				FileName(map); 
 			    }  
 			   catch (IOException e)  
 			   { 
 				// TODO Auto-generated catch block 
 				e.printStackTrace(); 
 			   }	 	     
         	   //new HighFrequencyWords(map); 
 			  long start = System.currentTimeMillis(); 
        	   String n=number2.getText(); 
        	   int wordNum=Integer.parseInt(n); 
       	   String print = "";              	 
       	   if (!n.isEmpty()) 
        	   {		  
     		   String str = "in,of,at,on,to,above,over,into,onto,behind,during,from,inside,outside,without," 
      		   		+ "a,an,the,throughout,i,she,he,its,we,you,my,out,them,your,myeself,yourself,herself"; 
       		       String s[] = str.split(",");   
       		         
       		       ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet()); 
       	           //按词频排序 
       		       Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()  
       		       { 
       	                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) 
       	                { 
       	                    return o2.getValue() - o1.getValue();  
       	                } 
       	           }); 
       		       int j=0; 
     		       int i=0; 
                   while (j<wordNum) 
                   { 
            			boolean flag = true ; 
            			String tmpString = list.get(i).getKey(); 
           			for (String string : s)  
            			{	 
            				if(tmpString.equals(string)) 
            				{ 
            					    flag = false; 
            				 }  
            			}	 
            		 
            			if(flag) 
            			{     
           			     print += list.get(i).getKey()+ ": " +list.get(i).getValue()+"    "; 
            			     j++; 
            			      
            			 } 
            			i++; 
               		} 
                   JOptionPane.showConfirmDialog(null,print+"\n"+"所用时间为："+(System.currentTimeMillis() - start)+"ms","结果",JOptionPane.DEFAULT_OPTION); 
        	   } 
			} 
			else 
			{ 
				   	JOptionPane.showConfirmDialog(null, "请输入要查询的信息！","提示",JOptionPane.DEFAULT_OPTION);					 
            } 
         } 
      }); 
 		
 		 /**打开查询指定单词词频界面*/ 
 		wordCountButton.addActionListener(new ActionListener() 
 		{ 
 			public void actionPerformed(ActionEvent event) 
 	         { 
 	            if (event.getSource()==wordCountButton) 
 	            { 
 	            	try  
 	            	{ 
 	            		FileName(map); 
 	 			   	}  
 	            	catch (IOException e)  
 	         	   	{ 
 	            		// TODO Auto-generated catch block 
 	            		e.printStackTrace(); 
 	         	   	} 
 	         	   long start = System.currentTimeMillis(); 
 	         	   String word=word2.getText(); 
 	         	   if (!word.isEmpty()) 
 	         	   {		   
 	         			Map<String,Integer> map1 = new TreeMap<String, Integer>();   
 	 					String[] input= word.split(","); 
 	 				    int i; 
 	 				    String print = ""; 
 	 				    for (i=0; i<input.length; i++)  
 	 				    {  
 	 				       	for (Entry<String, Integer> entry : map.entrySet())  
 	 				       	{  
 	 				       		if (input[i].equals(entry.getKey())) 
 	 				        	{ 
 	 				        		map1.put(entry.getKey(), entry.getValue()); 
 	 				        		print += entry.getKey() + ":" + entry.getValue()+"    ";
 	 				        		break; 
 	 				        	} 
 	 				         }  
 	 				     } 
 	 				    long time=System.currentTimeMillis() - start; 
 	 				   	JOptionPane.showConfirmDialog(null,print+"\n"+"所用时间为："+(System.currentTimeMillis() - start)+"ms","结果",JOptionPane.DEFAULT_OPTION); 
 	             				 Histogram histogram=new Histogram(map1,input.length); 
 	 				   	histogram.setVisible(true); 
 	 					} 
 	 				else 
 	 				{ 
 	 				   	JOptionPane.showConfirmDialog(null, "请输入要查询的信息！","提示",JOptionPane.DEFAULT_OPTION);					 
 	 				}  
 	         	    
 	            } 
 	         } 
      }); 
 		  
 		 /**执行词频写入文件功能*/ 
 		printFile.addActionListener(new ActionListener() 
 		{ 
         public void actionPerformed(ActionEvent event) 
         { 
            if (event.getSource()==printFile) 
           { 
         	   try  
         	   { 
 				FileName(map); 
 			    }  
         	   catch (IOException e)  
         	   { 
				// TODO Auto-generated catch block 
 				e.printStackTrace(); 
 			    }       
         	Result rs = new Result();  
 			try  
 			{ 
				rs.PrintToF(map); 
 			}  
 			catch (IOException e)  
 			{ 
 				// TODO Auto-generated catch block 
 				e.printStackTrace(); 
 			} 
         	JOptionPane.showConfirmDialog(null, "写入文件成功，在result.txt中查看！","提示",JOptionPane.DEFAULT_OPTION);	    
            } 
         } 
      }); 
 		 
 		/**各项统计功能*/ 
 		lineWordCount.addActionListener(new ActionListener() 
		{ 
         public void actionPerformed(ActionEvent event) 
        { 
        	String file=file2.getText();      	 
            if (event.getSource()==lineWordCount) 
            { 
            	InputStreamReader read = null;
				try {
					read = new InputStreamReader(new FileInputStream(file),"utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 								
                BufferedReader in = new BufferedReader(read);//可用于读取指定文件     
                String str=null;//定义一个字符串类型变量str
                String b=null;//定义一个字符串类型变量b
                int i = 0;//定义一个整型变量,用于统计行数
                int c = 0,c1 = 0,c2 = 0,c3 = 0,c4 = 0,c5 = 0,c6 = 0;//定义整型变量，用于统计字符数
                int d = 0;//定义一个整型变量，用于统计字节数
                try {
					while ((str = in.readLine())!= null) {//readLine()方法, 用于读取一行,只要读取内容不为空就一直执行
						i++;//每循环一次就进行一次自增，用于统计文本行数
						c += str.length();//用于统计总字符数
						byte[] bytes=str.getBytes();//求出该行的字节数组
						d += bytes.length;//用于统计总字节数
						for (int j = 0; j < str.length(); j++) {//for循环的条件，当j小于该行长度时就一直循环并自增
							b = Character.toString(str.charAt(j));//返回一个字符串对象
							if (b.matches("[\\u4e00-\\u9fa5]")) {//if语句的条件，判断是否为汉字
					            c1++;//若为汉字则c1自增
					        } else if(b.matches("[A-Z]")){//if语句的条件，判断是否为大写字母
								c2++;//若为大写字母则c2自增
							} else if(b.matches("[a-z]")){//if语句的条件，判断是否为小写字母
								c3++;//若为小写字母则c3自增
							} else if(b.matches("[0-9]")){//if语句的条件，判断是否为数字
								c4++;//若为数字则c4自增
							} else {//否则可判断为其他字符
								c5++;//若为其他字符则c5自增
							}
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                c6 = c2 + c3;//统计总的字母数
                try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//关闭流
                JOptionPane.showConfirmDialog(null,"该文本共有"+i+"行"+"\n"+"该文本共有"+c+"个字符"+"\n"+"其中包含："+c6+"个字母，其中"+c2+"个大写字母，"+c3+"个小写字母"+"\n"+c4+"个数字"+"\n"+c5+"个其他字符"+"\n"+"共用时："+time+"ms"+"\n"+"该文本共有"+d+"个字节","结果",JOptionPane.DEFAULT_OPTION);
            }          	          	        	    
        } 
		});  
 	}  
} 
 
