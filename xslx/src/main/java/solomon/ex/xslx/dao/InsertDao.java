package solomon.ex.xslx.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertDao {
	ConnectDao conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs =null;
	
	public void excute(String filename, Integer no) throws Exception {
		//String query = "INSERT INTO show_attribute_om (agentid, date, site_nm, source_type, title, content, polarity, pos_kw, pos_cnt, neg_kw, neg_cnt, attribute, filter_kw, url) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String query1 = "LOAD DATA LOCAL INFILE  '"+filename
				+ "'  INTO TABLE  test1.dmb_acc_main "
				+ "FIELDS  TERMINATED BY ';' "
				+ "LINES TERMINATED BY '\r\n' "
				+ "IGNORE 1 lines;";
		
		String query2 = "LOAD DATA LOCAL INFILE  '"+filename
				+ "' INTO TABLE  test1.dmb_acc_sub "
				+ "FIELDS  TERMINATED BY ';' "
				+ "LINES TERMINATED BY '\r\n'"
				+ "IGNORE 1 lines;";
		
		String query3 = "LOAD DATA LOCAL INFILE  '"+filename
				+ "' INTO TABLE  test1.dmb_acc_stdlink "
				+ "FIELDS  TERMINATED BY ';' "
				+ "LINES TERMINATED BY '\r\n' "
				+ "IGNORE 1 lines;";
		
		String query4 = "LOAD DATA LOCAL INFILE  '"+filename
				+ "' INTO TABLE  test1.dmb_acc_link "
				+ "FIELDS  TERMINATED BY ';' "
				+ "LINES TERMINATED BY '\r\n'"
				+ "IGNORE 1 lines;";
		
		try {
			
			if(no == 1) {
				System.out.println(filename + " insert");
				System.out.println(query1);
				conn  = new ConnectDao();
				pstmt = conn.getConn().prepareStatement(query1);
				rs =pstmt.executeQuery();
				
				System.out.println(filename +" insert Complete!!");
			}
			else if(no ==2) {
				System.out.println(filename +"파일 insert");
				System.out.println(query2);
				conn  = new ConnectDao();
				pstmt = conn.getConn().prepareStatement(query2);
				rs =pstmt.executeQuery();
				System.out.println(filename +" insert Complete!!");
			}
			else if(no == 3) {
				System.out.println(filename +" insert");
				System.out.println(query3);
				conn  = new ConnectDao();
				pstmt = conn.getConn().prepareStatement(query3);
				rs =pstmt.executeQuery();
				System.out.println(filename +" insert Complete!!");
			}
			else if(no == 4) {
				System.out.println(filename +" insert");
				System.out.println(query4);
				conn  = new ConnectDao();
				pstmt = conn.getConn().prepareStatement(query4);
				rs =pstmt.executeQuery();
				System.out.println(filename +" insert Complete!!");
			}

	

			System.out.println("insert를 완료했습니다.");
			   
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
			if(conn  != null) try { conn.getConn().close();  } catch (SQLException e) {}
		} // DB 연결에 사용한 객체와 Query수행을 위해 사용한 객체를 닫는다.
	 }
}
