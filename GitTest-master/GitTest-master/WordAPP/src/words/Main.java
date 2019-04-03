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
 	//��Ƶ�������ť 
 	private static JButton highFrequencyButton;  
 	//ͳ��ָ�����ʸ�����ť 
 	private static JButton wordCountButton; 
 	//��Ƶд���ļ���ť 
 	private static JButton printFile;  
 	//����������ͳ�ư�ť 
 	private static JButton lineWordCount;  
 	//���� 
 	private static JLabel input; 
 	//��� 
 	private static JFrame statistics;  
 	//�ļ��� 
 	private static JTextField file2;  
 	private static JLabel file ;

 	private static JTextField word2; 
	private static JLabel word;
	
 	private static JTextField number2; 
	private static JLabel number;
 	 
 	public static FileReader fr; 
 	static BufferedReader br; 
 	//���� 
	static int rowNumber=0;  
 	//������ 
 	static int wordNumber=0; 
 	//ͳ����������������ʱ�� 
 	static long time; 
 	
 	public static Map<String, Integer> map = new TreeMap<String, Integer>(); 
 	/**��ʼ����½����*/ 
 	public Main () 
 	{  
 		//�������� 
    	    Font font =new Font("����", Font.PLAIN, 20);  
    	    statistics=new JFrame("Ӣ���ı�ͳ�Ʒ���С���"); 
 		statistics.setSize(700, 380); 
 		input=new JLabel(); 
 		 
 		file=new JLabel("�����ļ���:"); 
 		file.setBounds(20, 40, 150, 50); 
 		
 		word=new JLabel("�����ѯ��Ƶ����:"); 
 		word.setBounds(20, 100, 150, 50); 

 		number=new JLabel("�����Ƶ�ʵĸ���:"); 
 		number.setBounds(20, 140, 150, 100); 
 		 
 		highFrequencyButton=new JButton("���ǰN����Ƶ��");     
 		highFrequencyButton.setBounds(450, 160, 200, 35); 
 		highFrequencyButton.setFont(font); 
 		 
 		wordCountButton=new JButton("�����Ƶ����״ͼ"); 
 		wordCountButton.setBounds(450, 100, 200, 35); //230, 150, 200, 50
 		wordCountButton.setFont(font); 

 		printFile=new JButton("��Ƶ�������ļ�"); 
 		printFile.setBounds(100, 250, 200, 50); 
 		printFile.setFont(font); 
 		 
 		lineWordCount=new JButton("����ͳ��"); 
 		lineWordCount.setBounds(350, 250, 200, 50); 
 		lineWordCount.setFont(font);  
 
 		//�����ı��� 
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
 	
	/**��ָ���ļ����뵥�ʲ�ͳ�ƴ�Ƶ*/ 
     static void FileName(final Map<String, Integer> map) throws IOException 
     { 
 		String file=file2.getText();
 		
 		if (file.isEmpty()) 
 		{ 
 			JOptionPane.showConfirmDialog(null, "�������ļ�����","��ʾ",JOptionPane.DEFAULT_OPTION); 
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
 	            	//��������� 
	            	String[] words = value.split("[^a-zA-Z]");  
 	            	for (int i = 0; i < words.length; i++)  
 	            	{ 
 	            		//����д��ĸת��ΪСд��ĸ 
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
 	                      			int k = map.get(key)+1;// ������ǵ�һ�γ��֣��Ͱ�kֵ++ 
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
 	    /**�򿪲鿴ǰN����Ƶ�ʽ���*/ 
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
       	           //����Ƶ���� 
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
                   JOptionPane.showConfirmDialog(null,print+"\n"+"����ʱ��Ϊ��"+(System.currentTimeMillis() - start)+"ms","���",JOptionPane.DEFAULT_OPTION); 
        	   } 
			} 
			else 
			{ 
				   	JOptionPane.showConfirmDialog(null, "������Ҫ��ѯ����Ϣ��","��ʾ",JOptionPane.DEFAULT_OPTION);					 
            } 
         } 
      }); 
 		
 		 /**�򿪲�ѯָ�����ʴ�Ƶ����*/ 
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
 	 				   	JOptionPane.showConfirmDialog(null,print+"\n"+"����ʱ��Ϊ��"+(System.currentTimeMillis() - start)+"ms","���",JOptionPane.DEFAULT_OPTION); 
 	             				 Histogram histogram=new Histogram(map1,input.length); 
 	 				   	histogram.setVisible(true); 
 	 					} 
 	 				else 
 	 				{ 
 	 				   	JOptionPane.showConfirmDialog(null, "������Ҫ��ѯ����Ϣ��","��ʾ",JOptionPane.DEFAULT_OPTION);					 
 	 				}  
 	         	    
 	            } 
 	         } 
      }); 
 		  
 		 /**ִ�д�Ƶд���ļ�����*/ 
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
         	JOptionPane.showConfirmDialog(null, "д���ļ��ɹ�����result.txt�в鿴��","��ʾ",JOptionPane.DEFAULT_OPTION);	    
            } 
         } 
      }); 
 		 
 		/**����ͳ�ƹ���*/ 
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
                BufferedReader in = new BufferedReader(read);//�����ڶ�ȡָ���ļ�     
                String str=null;//����һ���ַ������ͱ���str
                String b=null;//����һ���ַ������ͱ���b
                int i = 0;//����һ�����ͱ���,����ͳ������
                int c = 0,c1 = 0,c2 = 0,c3 = 0,c4 = 0,c5 = 0,c6 = 0;//�������ͱ���������ͳ���ַ���
                int d = 0;//����һ�����ͱ���������ͳ���ֽ���
                try {
					while ((str = in.readLine())!= null) {//readLine()����, ���ڶ�ȡһ��,ֻҪ��ȡ���ݲ�Ϊ�վ�һֱִ��
						i++;//ÿѭ��һ�ξͽ���һ������������ͳ���ı�����
						c += str.length();//����ͳ�����ַ���
						byte[] bytes=str.getBytes();//������е��ֽ�����
						d += bytes.length;//����ͳ�����ֽ���
						for (int j = 0; j < str.length(); j++) {//forѭ������������jС�ڸ��г���ʱ��һֱѭ��������
							b = Character.toString(str.charAt(j));//����һ���ַ�������
							if (b.matches("[\\u4e00-\\u9fa5]")) {//if�����������ж��Ƿ�Ϊ����
					            c1++;//��Ϊ������c1����
					        } else if(b.matches("[A-Z]")){//if�����������ж��Ƿ�Ϊ��д��ĸ
								c2++;//��Ϊ��д��ĸ��c2����
							} else if(b.matches("[a-z]")){//if�����������ж��Ƿ�ΪСд��ĸ
								c3++;//��ΪСд��ĸ��c3����
							} else if(b.matches("[0-9]")){//if�����������ж��Ƿ�Ϊ����
								c4++;//��Ϊ������c4����
							} else {//������ж�Ϊ�����ַ�
								c5++;//��Ϊ�����ַ���c5����
							}
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                c6 = c2 + c3;//ͳ���ܵ���ĸ��
                try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//�ر���
                JOptionPane.showConfirmDialog(null,"���ı�����"+i+"��"+"\n"+"���ı�����"+c+"���ַ�"+"\n"+"���а�����"+c6+"����ĸ������"+c2+"����д��ĸ��"+c3+"��Сд��ĸ"+"\n"+c4+"������"+"\n"+c5+"�������ַ�"+"\n"+"����ʱ��"+time+"ms"+"\n"+"���ı�����"+d+"���ֽ�","���",JOptionPane.DEFAULT_OPTION);
            }          	          	        	    
        } 
		});  
 	}  
} 
 
