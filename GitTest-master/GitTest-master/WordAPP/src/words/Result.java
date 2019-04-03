package words;

import java.io.File; 
 import java.io.FileWriter; 
 import java.io.IOException; 
 import java.util.Collections; 
 import java.util.Comparator; 
 import java.util.LinkedHashMap; 
 import java.util.LinkedList; 
 import java.util.Map; 
 import java.util.Set; 
 import java.util.Map.Entry; 
 
 
 import javax.swing.JOptionPane; 

 /** 
  * @author Zeng Jia, Wang Jing 
  * @data 2019.4.2 
  */ 

 
 /**按字典顺序输出到文件*/ 
 public class Result  
 {	 
	  	Map<String,Integer> Map = new LinkedHashMap<String, Integer>();  
 	 /**按字典顺序排序*/ 
     void Sort(Map<String, Integer> map)  
     {   
        Set<Entry<String,Integer>> m= map.entrySet();    
        LinkedList<Entry<String, Integer>> List = new LinkedList<Entry<String,Integer>>(m); 
      
     	   Collections.sort(List, new Comparator<Entry<String,Integer>>()  
     	   {      
                public int compare(Entry<String, Integer> a,  Entry<String, Integer> b)  
                {   
                    return a.getKey().compareTo(b.getKey());   
               }      
            });   
        for (Entry<String,Integer> entry: List)  
        {   
            Map.put(entry.getKey(), entry.getValue());   
        }   
    }  
     /**写入文件*/ 
 	 void PrintToF(Map<String, Integer> map)throws IOException  
 	 {   
 		    long start = System.currentTimeMillis(); 
 	    	Sort(map); 
 	        File file = new File("result.txt"); 
 	        FileWriter f = new FileWriter(file.getAbsoluteFile()); 
 	        for (Entry<String,Integer> w: Map.entrySet())  
 	        { 
 	        	f.write(w.getKey() + ":" + w.getValue()+"     "); 
 	        } 
 	        f.close(); 
 	        JOptionPane.showConfirmDialog(null,"所用时间："+(System.currentTimeMillis() - start)+"ms","结果",JOptionPane.DEFAULT_OPTION); 
 	 }          
 } 
