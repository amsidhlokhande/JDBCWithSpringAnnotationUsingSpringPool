/**
 * 
 */
package com.amsidh.mvc.dtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author amsidhlokhande
 *
 */
@Component
public class VechileDaoImpl {

	@Autowired
	private DataSource dataSource;
	
	public Vechile getVechileByVechileId(Integer vechileId)
	{
		
		Vechile vechile=null;
		Connection con=null;
		try
		{
			con=dataSource.getConnection();
			PreparedStatement pst=con.prepareStatement("SELECT * FROM VECHILE WHERE VECHILEID=?");
			pst.setInt(1, vechileId);
			ResultSet rst=pst.executeQuery();
			while(rst.next())
			{
				vechile=new Vechile(vechileId,rst.getString("VECHILETYPE"));
			}

			rst.close();
			pst.close();
			
		}catch(Exception ex)
		{
			throw new RuntimeException();
		}finally
		{
			try
			{
				con.close();
			}catch(SQLException sqlex)
			{
				
			}
		}
		
		return vechile;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
