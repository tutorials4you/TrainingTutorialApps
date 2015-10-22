package com.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.point.Formatter;
import com.test.ConProvider;

public class ResultRecordDao {
	static Connection connection = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	public static int insertResultRecord(ResultRecordDo resultRecordDo){
		int status =0;
		try {
			connection = ConProvider.getConnection();
			String query ="insert into ResultRecord (rid,userId,userName,userRole,status,marks,cid,noOfAttempt,resdate) values (Res_Id.NEXTVAL,?,?,?,?,?,?,?,?)";
			ps = connection.prepareStatement(query);
			ps.setString(1,resultRecordDo.getUserId() );
			ps.setString(2, resultRecordDo.getUserName());
			ps.setString(3, resultRecordDo.getUserRole());
			ps.setString(4, resultRecordDo.getStatus());
			ps.setInt(5, resultRecordDo.getMarks());
			ps.setString(6, resultRecordDo.getCourseId());
			ps.setInt(7, resultRecordDo.getNoOfAttempts());
			ps.setDate(8, Formatter.getCurrentDate());
			status = ps.executeUpdate();
		}
		catch (Exception e) {
			System.out.println(e);
			ConProvider.cleanUp((Statement)ps, (Connection)connection);
		}
	finally {
		ConProvider.cleanUp((Statement)ps, (Connection)connection);
	}
		return status;
	}
	
	
	public static int  checkNoOfAttemts(String userId,String courseId){
		int status =0;
		try {
			connection = ConProvider.getConnection();
			String query ="Select * from ResultRecord where USERID = ? and CID = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1,userId );
			
			ps.setString(2,courseId);
			rs = ps.executeQuery();
			if(rs.next())
				status=rs.getInt("NOOFATTEMPT");
		}
		catch (Exception e) {
			System.out.println(e);
			ConProvider.cleanUp((Statement)ps, (Connection)connection);
		}
	finally {
		ConProvider.cleanUp((Statement)ps, (Connection)connection);
	}
		return status;
	}


	public static void updateResultRecords(ResultRecordDo resultRecordDo) {
		
		 try {
	            try {
	                connection = ConProvider.getConnection();
	                String sqlQuery = "Update ResultRecord set STATUS =? ,MARKS = ? ,NOOFATTEMPT = ?, RESDATE =?  where USERID = ? and CID = ?";
	               ps = connection.prepareStatement(sqlQuery);
	                System.out.println("Sql Query " + sqlQuery);
	                ps.setString(1,resultRecordDo.getStatus());
	                ps.setInt(2,resultRecordDo.getMarks());
	                ps.setInt(3,resultRecordDo.getNoOfAttempts());
	                ps.setDate(4, Formatter.getCurrentDate());
	                ps.setString(5,resultRecordDo.getUserId());
	                ps.setString(6,resultRecordDo.getCourseId());
	                ps.executeUpdate();

	            }
	            catch (SQLException e) {
	                e.printStackTrace();
	                ConProvider.cleanUp(ps, connection);
	            }
	        }
	        finally {
	            ConProvider.cleanUp(ps, connection);
	        }
		
	}
	
	public static List<ResultRecordDo>  statusOfResult(){
				List<ResultRecordDo> resultStatus = new ArrayList<ResultRecordDo>();
				try {
			connection = ConProvider.getConnection();
			String query ="Select * from ResultRecord ";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next())
			{
				ResultRecordDo resultRecordDo = new ResultRecordDo();
				resultRecordDo.setUserId(rs.getString("userId"));
				resultRecordDo.setUserName(rs.getString("userName"));
				resultRecordDo.setStatus(rs.getString("status"));
				resultRecordDo.setMarks(rs.getInt("marks"));
				resultRecordDo.setNoOfAttempts(rs.getInt("noOfAttempt"));
				resultRecordDo.setCourseId(rs.getString("CID"));

				resultStatus.add(resultRecordDo);
				}
			
				
				}
		catch (Exception e) {
			System.out.println(e);
			ConProvider.cleanUp((Statement)ps, (Connection)connection);
		}
	finally {
		ConProvider.cleanUp((Statement)ps, (Connection)connection);
	}
		return resultStatus;
	}
	
}
