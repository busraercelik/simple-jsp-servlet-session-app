package bsr.repo.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bsr.model.Note;
import bsr.repo.NoteRepo;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class NoteRepoImpl implements NoteRepo{

	private String USER_TABLE_USERNAME="username";
	private String USER_TABLE_ID="id";
	
	private String NOTE_TABLE_USERID="userId";
	private String NOTE_TABLE_ID="id";
	private String NOTE_TABLE_TEXT="text";
	
	private String DRIVER = "com.mysql.cj.jdbc.Driver";
	private String MYSQL_URL = "jdbc:mysql://localhost:3306/personel_note_db";
	private String MYSQL_USERNAME = "root";
	private String MYSQL_PASSWORD = "root";
	
	public Note getNote(String name) {
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		
		try {
			Note noteModel = new Note();
			//step1 load the driver class 
			Class.forName(DRIVER);
			//step2 create  the connection object  
			con = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
			//step3 create the statement object 
			stmt = con.prepareStatement("SELECT * FROM user WHERE username=?");
			//step4 execute query  
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			
			Long userId = null;
			while (rs.next()) {
				userId = rs.getLong(USER_TABLE_ID);
			}
			
			stmt2 = con.prepareStatement("select * from note where userId=?");
			stmt2.setLong(1,userId);
			ResultSet rsNote = stmt2.executeQuery();
					
			while (rsNote.next()) {
				String note = rsNote.getString(NOTE_TABLE_TEXT);
				noteModel.setNote(note);
				noteModel.setUserId(userId);
				noteModel.setId(rsNote.getLong(NOTE_TABLE_ID));
				log.info("note: {}",noteModel);
			}
			return noteModel;			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		} 
		finally {
			//step5 close the connection object
			try {
				con.close();
				stmt.close();
				stmt2.close();
			} catch (SQLException e) {
				log.error(e.getMessage(),e);
			}
		}
		return null;
	}

	
	@Override
	public Note updateNote(Note note) {
		Connection con = null;
		PreparedStatement stmt = null;
		Note updatedNote = new Note();
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
			String updateQuery = "UPDATE note SET text=? WHERE userId=?";
			stmt = con.prepareStatement(updateQuery);
			
			stmt.setString(1, note.getNote());
			stmt.setLong(2, note.getUserId());
			stmt.executeUpdate();
			
		} catch (Exception e) {
	
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
				if (stmt != null ) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return updatedNote;
	}

}
