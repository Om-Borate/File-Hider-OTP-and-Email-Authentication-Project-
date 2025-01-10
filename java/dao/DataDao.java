package dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.MYConnection;
import model.Data;

public class DataDao {
	public static List<Data> getAllFiles(String email)throws SQLException{
		Connection connection = MYConnection.getConnection();
		
		PreparedStatement prepared = connection.prepareStatement("SELECT * FROM data where email =?");
		prepared.setString(1,email);
		ResultSet rs =prepared.executeQuery();
		List<Data> file = new ArrayList<>();
		while(rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			String path = rs.getString(3);
			file.add(new Data(id,name,path));
		}
		return file;
	}
	public static  int hidefile(Data file)throws SQLException, IOException {
		Connection connection = MYConnection.getConnection();
		PreparedStatement prepared = connection.prepareStatement("insert into data(name,path,email,bin_data)values(?,?,?,?)");
		prepared.setString(1,file.getFilename());
		prepared.setString(2,file.getPath());
		prepared.setString(3,file.getEmail());
		File f = new File(file.getPath());
		FileReader fr = new FileReader(f);
		prepared.setCharacterStream(4,fr,f.length());
		int ans = prepared.executeUpdate();
		fr.close();
		f.delete();
		return ans;
	}
	public static void unhide(int id)throws SQLException , Exception {
		Connection connection = MYConnection.getConnection();
		PreparedStatement prepared = connection.prepareStatement("select path,bin_data from data where id = ?");
		prepared.setInt(1, id);
		ResultSet rs =prepared.executeQuery();
		rs.next();
		String path= rs.getString("path");
		Clob c = rs.getClob("bin_data");
		Reader r =c.getCharacterStream();
		FileWriter fw = new FileWriter(path);
		int i;
		while((i=r.read()) != -1){
			fw.write((char) i);
		}
		fw.close();
		prepared=connection.prepareStatement("delete from data where id =?");
		prepared.setInt(1,id);
		prepared.executeUpdate();
		System.out.println("Successfully UnHidden");
	}
}
