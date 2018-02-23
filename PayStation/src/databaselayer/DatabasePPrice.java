package databaselayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.sql.SQLException;

import modellayer.*;

public class DatabasePPrice implements DbPPrice {
	
	public PPrice getPriceByZoneId(int zoneId) throws DatabaseLayerException {
		PPrice foundPrice = null;
		
		Connection con = DBConnection.getInstance().getDBcon();

		String baseSelect = "select top 1 price, pZone_id from PPrice ";
		baseSelect += "where pZone_id = " + zoneId + " and starttime < GETDATE() ";
		baseSelect += "order by starttime desc";
		System.out.println(baseSelect);
	
		ResultSet rs = null;
		int price, pZoneId;
		PZone pZone; 
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			// Todo: Get PPrice object
			rs = stmt.executeQuery(baseSelect);
			System.out.println(rs);
			rs.next();
			price = rs.getInt("price");
			pZoneId = rs.getInt("pZone_id");
			pZone = new PZone(pZoneId);
			foundPrice = new PPrice(price,pZone);
			stmt.close();
			
		} catch (SQLException ex) {
			foundPrice = null;
			DatabaseLayerException dle = new DatabaseLayerException("Error retrieving data");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} catch (NullPointerException ex) {
			foundPrice = null;
			DatabaseLayerException dle = new DatabaseLayerException("Null pointer exception - possibly Connection object");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} catch (Exception ex) {
			foundPrice = null;
			DatabaseLayerException dle = new DatabaseLayerException("Data not retrieved! Technical error");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} finally {
			DBConnection.closeConnection();
		}
				
		return foundPrice;
	}
	public int insertPriceByZoneId(int zoneId, int price) throws DatabaseLayerException {
		int insertedKey = 0;
		
		Connection con = DBConnection.getInstance().getDBcon();
		
		String baseInsert = "insert into PPrice (price, starttime, pZone_id) values ";
		baseInsert += "(" + price + ", GETDATE(), " + zoneId + ")";
		System.out.println(baseInsert);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			stmt.executeUpdate(baseInsert, Statement.RETURN_GENERATED_KEYS);
	
			ResultSet rs = stmt.getGeneratedKeys();
		    if (rs.next()) {
		    	insertedKey = rs.getInt(1);
		    }
		    stmt.close();
			
		} catch (SQLException ex) {
			insertedKey = -1;
			DatabaseLayerException dle = new DatabaseLayerException("Error inserting data");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} catch (NullPointerException ex) {
			insertedKey = -2;
			DatabaseLayerException dle = new DatabaseLayerException("Null pointer exception - possibly Connection object");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} catch (Exception ex) {
			insertedKey = -3;
			DatabaseLayerException dle = new DatabaseLayerException("Data not inserted! Technical error");
			dle.setStackTrace(ex.getStackTrace());
			throw dle;
		} finally {
			DBConnection.closeConnection();
		}
		
		System.out.println("Inserted key: " + insertedKey);
		
		return insertedKey;
	}

}
