package com.assesment.dom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.ConProvider;



public class ReteriveQuestion {
	Connection connection = null;
	private PreparedStatement ps = null;
	ResultSet rs = null;
	Statement statement = null;
	
	
	public List<AssesmentDO> getAllQuestion() {
		ArrayList<AssesmentDO> courses;
		courses = new ArrayList<AssesmentDO>();
		try {
			try {
				this.connection = ConProvider.getConnection();
				this.statement = this.connection.createStatement();
				this.rs = this.statement.executeQuery("select * from Assesments");
				while (this.rs.next()) {
					AssesmentDO assesment = new AssesmentDO();
					//assesment.setQuestionNumber(this.rs.getInt("questionNumber"));
					assesment.setQuestion(this.rs.getString("question"));
					assesment.setOptOne(this.rs.getString("answerOne"));
					assesment.setOptTwo(this.rs.getString("answerTwo"));
					assesment.setOptThree(this.rs.getString("answerThree"));
					assesment.setOptFour(this.rs.getString("answerFour"));
					assesment.setCorrAnswer(this.rs.getString("correctAnswer"));
					courses.add(assesment);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				ConProvider.cleanUp((ResultSet)this.rs, (Statement)this.statement, (Connection)this.connection);
			}
		}
		finally {
			ConProvider.cleanUp((ResultSet)this.rs, (Statement)this.statement, (Connection)this.connection);
		}
		return courses;
	}
	
	
	public List<AssesmentDO> getAllCourseID() {
		ArrayList<AssesmentDO> courses;
		courses = new ArrayList<AssesmentDO>();
		try {
			try {
				this.connection = ConProvider.getConnection();
				this.statement = this.connection.createStatement();
				this.rs = this.statement.executeQuery("select CID from Assesments");
				while (this.rs.next()) {
					AssesmentDO assesment = new AssesmentDO();
					assesment.setCid(this.rs.getString("CID"));
					courses.add(assesment);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				ConProvider.cleanUp((ResultSet)this.rs, (Statement)this.statement, (Connection)this.connection);
			}
		}
		finally {
			ConProvider.cleanUp((ResultSet)this.rs, (Statement)this.statement, (Connection)this.connection);
		}
		return courses;
	}
	
	

}
