
        this.setBounds(700,400,400,300);
		JButton open = new JButton("��");
		JButton check = new JButton("��ѯ");
		JButton order=new JButton("���ֵ�����");
		this.setLayout(null);
		open.setBounds(100, 100, 80, 30);
		check.setBounds(200, 100,80, 30);
		order.setBounds(150,150,100,30);
		this.add(open);
		this.add(check);
		this.add(order);
		num=new JLabel("Ӣ���ı��ĵ�������");
	    num.setBounds(120,40,150,40);
		this.add(num);
		/*�˴�ʡ�Եİ�ť����������ӽ��ں���������ܽ�����ʵ����*/
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FileDialog jf = new FileDialog(frame, "���ļ�", FileDialog.LOAD);
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
	 String s=String.valueOf(b);//ת��Ϊ�ַ���
	 String s1=s.replace(',', ' ');//�������ÿո��滻
	 String s2=s1.replace('.', ' ');//������ÿո��滻
	 String s3=s2.replace(';',' ');//���ֺ��ÿո��滻
	 String textArry[]=s3.split(" ");//���տո��и��ַ������õ��洢���ı�������Ӣ�ĵ��ʵ��ַ�����
	 
	 for(int i=0;i<textArry.length;i++){
	      if( textArry[i].length()==0){//���ַ���������Ϊ�յ��ַ���ɾ��
	    	  textArry[i]=textArry[i+1];
	      }
		 }
	    //ͳ��Ӣ�ĵ��ʵ�����
	    String a=String.valueOf(textArry.length);
	    num.setText("�ı���Ӣ�ĵ�������Ϊ:"+a);//���ñ�ǩ��ֵ����ʾ��������
	    return textArry;
	    
	}

order.addActionListener(new ActionListener(){
       	@Override
			public void actionPerformed(ActionEvent e) {
       		JFrame f=new JFrame("������");//�´���Ĵ���
       		f.setBounds(800,400,500,600);
            JTextArea ta=new JTextArea(10,5);//��ʾ�����ı�
            ta.setLineWrap(true);
            f.add(ta);
           
				String textArry[]=Dell(textFile);
				for(int i=0;i<textArry.length;i++){ //ð������
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
				    sb.append(" \n");//ÿ������֮�����һ������
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
		//�����׳��쳣��˭���ô˷������׸�˭
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
	//getConnection()������һ��static���εľ�̬������
	//�����ĵ��ÿ���ʹ��������
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
			//1.������������������
			conn=DBUtil.getConnection();
			String sql="insert into count values(?,?) ";
			//Ԥ����sql���
			ps=conn.prepareStatement(sql);
			//PreparedStatement���е�set�����еĵ�1������
			//��ʾsql����еڼ����ʺţ���2��������ʾ������ʺŸ�ֵ
			ps.setString(1, words);
			ps.setInt(2, counts);
			//2.ִ��sql���
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			//3.�ر�����
			DBUtil.close(conn);
		}
	} 
	
}

check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] textArry=Dell(textFile);
				CountDao cd=new CountDao();
			    int tenpCount=0;//����Ƶ��
			    for(int i=0;i<textArry.length;i++){
			    	for(int j=0;j<textArry.length;j++){
			    		if(textArry[i].equals(textArry[j])){
			    			tenpCount++;
			    		}	
			    	}
			    	cd.save( textArry[i],tenpCount);//�����ʺͳ��ֵ�Ƶ�ʱ��������ݿ���   	
			    }
			}

		});


