
        this.setBounds(700,400,400,300);
		JButton open = new JButton("打开");
		JButton check = new JButton("查询");
		JButton order=new JButton("按字典排序");
		this.setLayout(null);
		open.setBounds(100, 100, 80, 30);
		check.setBounds(200, 100,80, 30);
		order.setBounds(150,150,100,30);
		this.add(open);
		this.add(check);
		this.add(order);
		num=new JLabel("英文文本的单词数量");
	    num.setBounds(120,40,150,40);
		this.add(num);
		/*此处省略的按钮监听器的添加将在后面各个功能介绍中实现中*/
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FileDialog jf = new FileDialog(frame, "打开文件", FileDialog.LOAD);
				jf.setVisible(true);
				String dirName = jf.getDirectory();
				String fileName = jf.getFile();
				File f = new File(dirName, fileName);
				textFile = new StringBuilder();
				String b = null;
				BufferedReader br;
				try {
					br = new BufferedReader(new FileReader(f));
					while ((b = br.readLine()) != null) {
						textFile.append(b);
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

public String[] Dell(StringBuilder b){
	 String s=String.valueOf(b);//转换为字符串
	 String s1=s.replace(',', ' ');//将逗号用空格替换
	 String s2=s1.replace('.', ' ');//将句号用空格替换
	 String s3=s2.replace(';',' ');//将分好用空格替换
	 String textArry[]=s3.split(" ");//按照空格切割字符串，得到存储着文本中所有英文单词的字符数组
	 
	 for(int i=0;i<textArry.length;i++){
	      if( textArry[i].length()==0){//将字符串数组中为空的字符串删除
	    	  textArry[i]=textArry[i+1];
	      }
		 }
	    //统计英文单词的数量
	    String a=String.valueOf(textArry.length);
	    num.setText("文本中英文单词数量为:"+a);//设置标签的值，显示单词数量
	    return textArry;
	    
	}

order.addActionListener(new ActionListener(){
       	@Override
			public void actionPerformed(ActionEvent e) {
       		JFrame f=new JFrame("排序结果");//新窗体的创建
       		f.setBounds(800,400,500,600);
            JTextArea ta=new JTextArea(10,5);//显示多行文本
            ta.setLineWrap(true);
            f.add(ta);
           
				String textArry[]=Dell(textFile);
				for(int i=0;i<textArry.length;i++){ //冒泡排序
					for(int j=0;j<textArry.length-i-1;j++){
						if((textArry[j].compareTo(textArry[j+1]))>0){
							String temp=textArry[j];
							textArry[j]=textArry[j+1];
							textArry[j+1]=temp;
						}
					}
				}
				StringBuilder sb=new StringBuilder();
				for(int i=0;i<textArry.length;i++){	    
				    sb.append(textArry[i]);
				    sb.append(" \n");//每个单词之间添加一个换行
				}
				
				ta.setText(String.valueOf(sb));
			f.setVisible(true);
			}	
		});

public class DBUtil {
public static Connection getConnection() throws Exception{
	Connection conn=null;
	try {
		 Class.forName("com.mysql.jdbc.Driver");
		conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/count","root","root");
	} catch (Exception e) {
		e.printStackTrace();
		//向外抛出异常，谁调用此方法就抛给谁
		throw e;
	}
	return conn;
}
public static void close(java.sql.Connection conn){
	try {
		if (conn!=null) {
			conn.close();
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

public static void main(String[] args) throws Exception {
	//getConnection()方法是一个static修饰的静态方法，
	//方法的调用可以使用类名点
	Connection conn= DBUtil.getConnection();
	System.out.println(conn);
	DBUtil.close(conn);
}
}
public class CountDao {
	public void save(String words,int counts){
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			//1.加载驱动并建立连接
			conn=DBUtil.getConnection();
			String sql="insert into count values(?,?) ";
			//预编译sql语句
			ps=conn.prepareStatement(sql);
			//PreparedStatement类中的set方法中的第1个参数
			//表示sql语句中第几个问号，第2个参数表示给这个问号赋值
			ps.setString(1, words);
			ps.setInt(2, counts);
			//2.执行sql语句
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			//3.关闭连接
			DBUtil.close(conn);
		}
	} 
	
}

check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] textArry=Dell(textFile);
				CountDao cd=new CountDao();
			    int tenpCount=0;//单词频率
			    for(int i=0;i<textArry.length;i++){
			    	for(int j=0;j<textArry.length;j++){
			    		if(textArry[i].equals(textArry[j])){
			    			tenpCount++;
			    		}	
			    	}
			    	cd.save( textArry[i],tenpCount);//将单词和出现的频率保存在数据库中   	
			    }
			}

		});


