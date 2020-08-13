package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainThread extends Thread {

	private Socket socket;
	private String name_ID;


	public MainThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		
	    String driver = "org.mariadb.jdbc.Driver";
	    String url = "jdbc:mariadb://127.0.0.1:3306/hwang";
	    String user = "hwang";
	    String pass = "hwang";
	   
	    Connection con = null;
	    PreparedStatement ps = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
		try {
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String text;

					
            Class.forName(driver);	// Drive에 등록
            System.out.println("Driver 연결 성공");
 
            con = DriverManager.getConnection(url, user, pass);	// URL과 ID PW 객체 생성
            System.out.println("DB 연결 성공");
            
            
			while(true) {

				text = buf.readLine();

				if (text == null) {
					System.out.println(name_ID + "님이 나갔습니다.");
					for (int i = 0; i < ChatServer.forclient.size(); i++) {
						ChatServer.forclient.get(i).println(name_ID + "님이 나갔습니다..");
						ChatServer.forclient.get(i).flush();
					}
					break;
				}

				String[] split = text.split("hwang");
				
				if (split.length == 2 && split[0].equals("ID")) {
					name_ID = split[1];
					System.out.println(name_ID + "님이 입장하셨습니다.");
					
					for (int i = 0; i < ChatServer.forclient.size(); i++) {
						ChatServer.forclient.get(i).println(name_ID + "님이 입장하셨습니다..");
						ChatServer.forclient.get(i).flush();
					}
					
					System.out.println("- 지난 대화  -");
					
					String SQL = "select * from hwang";
					
					stmt = con.createStatement();
					rs = stmt.executeQuery(SQL);
					
					while (rs.next()) {
						
						String id = rs.getString("id");
						String pass1 = rs.getString("pass");
						
						System.out.println(id + " " + pass1 + " ");

					}

					
					continue;
				}

			    String SQL = "insert into hwang(id, pass) values(?, ?)";

			    ps = con.prepareStatement(SQL);
			    
			    ps.setString(1, name_ID);
			    ps.setString(2, text);
			    
			    ps.executeUpdate();
			    
				for (int i = 0; i < ChatServer.forclient.size(); i++) {
				    
					ChatServer.forclient.get(i).println(name_ID + "> " + text);
					ChatServer.forclient.get(i).flush();
				
				}
			}

			ChatServer.forclient.remove(new PrintWriter(socket.getOutputStream()));
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}catch (SQLException e) { 
            System.out.println("[SQL Error : " + e.getMessage() + "]");
        } catch (ClassNotFoundException e1) {
            System.out.println("[JDBC Connector Driver 오류 : " + e1.getMessage() + "]");
        } finally {
        	if (rs != null) {
        		try {
        			rs.close(); 
        		} catch (SQLException e) {
        			e.printStackTrace(); 
        		}
        	}
        	if (stmt != null) {
        		try {
        			stmt.close();
        		} catch (SQLException e) {
        			e.printStackTrace();
				}
        	}
        	if (ps != null) {
        		try {
        			ps.close();
        		} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        	if (con != null) {
        		try {
        			con.close();
        		} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        }

	}
}
