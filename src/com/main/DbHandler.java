/*
 * Maisam Alatrach 20091817
 * Oudai Fayek 20091861
 * Latrobe University
 * 29/05/2020
 */
package com.main;

import java.util.ArrayList;
import com.main.VehicleReportData;
import java.sql.*;

public class DbHandler{
	/*
	This Class is handling all the connections with database
	All the web site queries are involved in this class
	*/

	//Server settings
	private String useUnicode = "true";
	private String useJDBCCompliantTimezoneShift = "true";
	private String useLegacyDatetimeCode = "false";
	private String serverTimezone = "UTC";
	private String lineSettings = "?useUnicode="+useUnicode+"&useJDBCCompliantTimezoneShift="+useJDBCCompliantTimezoneShift+"&useLegacyDatetimeCode="+useLegacyDatetimeCode+"&serverTimezone="+serverTimezone;
	
	//User name and password
	private String dbURL;
	private String dbUserName;
	private String dbPassword;
	
	//Tables name
	private String carsTable = "cars";
	private String logTable = "log";
	private String userTable = "user";
	
	//Class constructor
	public DbHandler(String dbHost, String dbUserName, String dbPassword) {
		this.dbUserName = dbUserName;
		this.dbPassword = dbPassword;
		dbURL = "jdbc:mysql://" + dbHost + lineSettings;
	}

	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;

	//Data base connecting method
	public void connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(this.dbURL, this.dbUserName, this.dbPassword);
			this.statement = this.connection.createStatement();
		} catch (Exception e) {e.printStackTrace();}
	}

	//Data base disconnecting method
	public void disconnect() throws SQLException {
		if (this.preparedStatement != null) {
			this.preparedStatement.close();
		}

		if (this.statement != null) {
			this.statement.close();
		}

		if (this.connection != null) {
			this.connection.close();
		}
	}

	//Update local database and returns number of updated rows
	public int addVehicleReportDatas(ArrayList <VehicleReportData> cars) throws Exception {
		
		ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM "+carsTable);
		rs.next();
		int nr1 = rs.getInt(1);

		String command = "INSERT IGNORE INTO "+carsTable+" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";	
		for (VehicleReportData car : cars) {
			preparedStatement = connection.prepareStatement(command);
			preparedStatement.setString(1,car.getBodyStyleDesc());
			preparedStatement.setInt(2,car.getCO2Emissions());
			preparedStatement.setInt(3,car.getCO2EmissionsExtraUrban());
			preparedStatement.setInt(4,car.getCO2EmissionsUrban());
			preparedStatement.setString(5,car.getCertificationLevelDesc());
			preparedStatement.setString(6, car.getDrivingWheelsDesc());
			preparedStatement.setInt(7,car.getElectricRangeKm());
			preparedStatement.setDouble(8,car.getEnergyConsumptionWhkm());
			preparedStatement.setString(9,car.getEngineConfigurationDesc());
			preparedStatement.setString(10,car.getEngineDisplacementDesc());
			preparedStatement.setInt(11,car.getForwardGearsDesc());
			preparedStatement.setDouble(12,car.getFuelConsumption());
			preparedStatement.setDouble(13,car.getFuelConsumptionExtraUrban());
			preparedStatement.setDouble(14,car.getFuelConsumptionUrban());
			preparedStatement.setString(15,car.getFuelTypeDesc());
			preparedStatement.setString(16,car.getInductionDesc());
			preparedStatement.setString(17,car.getIsCurrentModelDesc());
			preparedStatement.setString(18,car.getMake());
			preparedStatement.setString(19,car.getMarketingModel());
			preparedStatement.setString(20,car.getModelReleaseVersion());
			preparedStatement.setInt(21,car.getModelReleaseYear());
			preparedStatement.setDouble(22,car.getNonCurrentOnDate());
			preparedStatement.setDouble(23,car.getPublishedDate());
			preparedStatement.setString(24,car.getSeatingCapacityDesc());
			preparedStatement.setString(25,car.getSideDoorsDesc());
			preparedStatement.setString(26,car.getStationaryNoiseDataDesc());
			preparedStatement.setString(27,car.getTestSpeedDesc());
			preparedStatement.setString(28,car.getTransmissionTypeDesc());
			preparedStatement.setString(29,car.getVariant());
			preparedStatement.setString(30,car.getVehicleClassDesc());
			preparedStatement.setInt(31,car.getVehicleDisplayId());

			preparedStatement.executeUpdate();
		}
		
		rs = statement.executeQuery("SELECT COUNT(*) FROM "+carsTable);
		rs.next();
		int nr2 = rs.getInt(1);
		return(nr2-nr1);
	}


	//Get distinct years from the database
	public ArrayList<String> getAllYears() throws Exception {
		String queryString = "SELECT DISTINCT ModelReleaseYear FROM "+carsTable+" ORDER BY ModelReleaseYear DESC LIMIT 100";
		ResultSet rs = this.statement.executeQuery(queryString);
		ArrayList <String> years = new  ArrayList <String>();

		while(rs.next()) {
			String year = rs.getString(1);
			years.add(year);
		}
		return years;
	}

	//Get distinct makes from the database
	public ArrayList<String> getAllMakes(int year) throws Exception {
		String queryString = "SELECT DISTINCT Make FROM "+carsTable+" WHERE ModelReleaseYear = '" + year + "' ORDER BY Make LIMIT 100";
		this.preparedStatement = this.connection.prepareStatement(queryString);
		ResultSet rs = this.preparedStatement.executeQuery(queryString);
		ArrayList<String> makes = new ArrayList <String> ();

		while(rs.next()) {
			String make = rs.getString(1);
			makes.add(make);
		}
		return makes;
	}

	//Get distinct models from the database
	public ArrayList<String> getAllModels(int year, String make) throws Exception {
		String queryString = "SELECT DISTINCT MarketingModel FROM "+carsTable+" WHERE ModelReleaseYear = '" + year + "' AND  Make = '" + make + "' ORDER BY MarketingModel LIMIT 100";
		this.preparedStatement = this.connection.prepareStatement(queryString);
		ResultSet rs = this.preparedStatement.executeQuery(queryString);
		ArrayList <String> models = new ArrayList <String> ();

		while(rs.next()) {
			String model = rs.getString(1);
			models.add(model);
		}
		return models;
	}

	//Get distinct versions from the database
	public ArrayList<String> getAllModelReleaseVersion(int year, String make, String model) throws Exception {
		String queryString = "SELECT DISTINCT ModelReleaseVersion FROM "+carsTable+" WHERE ModelReleaseYear = '" + year + "' AND  Make = '" + make + "' AND MarketingModel = '" + model + "' ORDER BY ModelReleaseVersion LIMIT 100";
		this.preparedStatement = this.connection.prepareStatement(queryString);
		ResultSet rs = this.preparedStatement.executeQuery(queryString);
		ArrayList <String> versions = new ArrayList <String> ();

		while(rs.next()) {
			String variant = rs.getString(1);
			versions.add(variant);
		}
		return versions;
	}
	
	//Get distinct variants from the database
	public ArrayList<String> getAllVariant(int year, String make, String model, String version) throws Exception {
		String queryString = "SELECT DISTINCT Variant FROM "+carsTable+" WHERE ModelReleaseYear = '" + year + "' AND  Make = '" + make + "' AND MarketingModel = '" + model + "' AND ModelReleaseVersion = '" +version+ "' ORDER BY Variant LIMIT 100";
		this.preparedStatement = this.connection.prepareStatement(queryString);
		ResultSet rs = this.preparedStatement.executeQuery(queryString);
		ArrayList <String> variants = new ArrayList <String> ();

		while(rs.next()) {
			String variant = rs.getString(1);
			variants.add(variant);
		}
		return variants;
	}
	
	//Get distinct fuel types from the database
	public ArrayList<String> getAllFuelTypeDesc(int year, String make, String model, String version , String variant) throws Exception {
		String queryString = "SELECT DISTINCT FuelTypeDesc FROM "+carsTable+" WHERE ModelReleaseYear = '" + year + "' AND  Make = '" + make + "' AND MarketingModel = '" + model + "' AND ModelReleaseVersion = '" +version+ "' AND Variant = '"+variant+"' ORDER BY FuelTypeDesc LIMIT 100";
		this.preparedStatement = this.connection.prepareStatement(queryString);
		ResultSet rs = this.preparedStatement.executeQuery(queryString);
		ArrayList <String> fuels = new ArrayList <String> ();

		while(rs.next()) {
			String fuel = rs.getString(1);
			fuels.add(fuel);
		}
		return fuels;
	}

	//Get specific cars details from the database
	public ArrayList<VehicleReportData> getCarsByDetails(int year, String make, String model,String version, String variant, String fuel) throws Exception {
		String queryString = "SELECT * FROM "+carsTable+" WHERE ModelReleaseYear = '" + year + "' AND  Make = '" + make + "' AND MarketingModel = '" + model + "' AND ModelReleaseVersion = '" + version + "' AND Variant = '"+variant+"' AND FuelTypeDesc = '"+ fuel+"' LIMIT 100";
		this.preparedStatement = this.connection.prepareStatement(queryString);
		ResultSet rs = this.preparedStatement.executeQuery(queryString);
		ArrayList <VehicleReportData> cars = new ArrayList <VehicleReportData> ();

		while(rs.next()) {
			VehicleReportData car = new VehicleReportData();
			car.setBodyStyleDesc(rs.getString(1));
			car.setCO2Emissions(rs.getInt(2));
			car.setCO2EmissionsExtraUrban(rs.getInt(3));
			car.setCO2EmissionsUrban(rs.getInt(4));
			car.setCertificationLevelDesc(rs.getString(5));
			car.setDrivingWheelsDesc(rs.getString(6));
			car.setElectricRangeKm(rs.getInt(7));
			car.setEnergyConsumptionWhkm(rs.getDouble(8));
			car.setEngineConfigurationDesc(rs.getString(9));
			car.setEngineDisplacementDesc(rs.getString(10));
			car.setForwardGearsDesc(rs.getInt(11));
			car.setFuelConsumption(rs.getDouble(12));
			car.setFuelConsumptionExtraUrban(rs.getDouble(13));
			car.setFuelConsumptionUrban(rs.getDouble(14));
			car.setFuelTypeDesc(rs.getString(15));
			car.setInductionDesc(rs.getString(16));
			car.setIsCurrentModelDesc(rs.getString(17));
			car.setMake(rs.getString(18));
			car.setMarketingModel(rs.getString(19));
			car.setModelReleaseVersion(rs.getString(20));
			car.setModelReleaseYear(rs.getInt(21));
			car.setNonCurrentOnDate(rs.getDouble(22));
			car.setPublishedDate(rs.getDouble(23));
			car.setSeatingCapacityDesc(rs.getString(24));
			car.setSideDoorsDesc(rs.getString(25));
			car.setStationaryNoiseDataDesc(rs.getString(26));
			car.setTestSpeedDesc(rs.getString(27));
			car.setTransmissionTypeDesc(rs.getString(28));
			car.setVariant(rs.getString(29));
			car.setVehicleClassDesc(rs.getString(30));
			car.setVehicleDisplayId(rs.getInt(31));
			cars.add(car);
		}
		return cars;
	}
	
	//Get one car details
	public VehicleReportData getCarByDetails(int year, String make, String model,String version, String variant, String fuel) throws Exception {
		String queryString = "SELECT * FROM "+carsTable+" WHERE ModelReleaseYear = '" + year + "' AND  Make = '" + make + "' AND MarketingModel = '" + model + "' AND ModelReleaseVersion = '" + version + "' AND Variant = '"+variant+"' AND FuelTypeDesc = '"+ fuel+"' LIMIT 1";
		this.preparedStatement = this.connection.prepareStatement(queryString);
		ResultSet rs = this.preparedStatement.executeQuery(queryString);
		
		if(rs.next()) {		
			VehicleReportData car =new VehicleReportData();
			car.setBodyStyleDesc(rs.getString(1));
			car.setCO2Emissions(rs.getInt(2));
			car.setCO2EmissionsExtraUrban(rs.getInt(3));
			car.setCO2EmissionsUrban(rs.getInt(4));
			car.setCertificationLevelDesc(rs.getString(5));
			car.setDrivingWheelsDesc(rs.getString(6));
			car.setElectricRangeKm(rs.getInt(7));
			car.setEnergyConsumptionWhkm(rs.getDouble(8));
			car.setEngineConfigurationDesc(rs.getString(9));
			car.setEngineDisplacementDesc(rs.getString(10));
			car.setForwardGearsDesc(rs.getInt(11));
			car.setFuelConsumption(rs.getDouble(12));
			car.setFuelConsumptionExtraUrban(rs.getDouble(13));
			car.setFuelConsumptionUrban(rs.getDouble(14));
			car.setFuelTypeDesc(rs.getString(15));
			car.setInductionDesc(rs.getString(16));
			car.setIsCurrentModelDesc(rs.getString(17));
			car.setMake(rs.getString(18));
			car.setMarketingModel(rs.getString(19));
			car.setModelReleaseVersion(rs.getString(20));
			car.setModelReleaseYear(rs.getInt(21));
			car.setNonCurrentOnDate(rs.getDouble(22));
			car.setPublishedDate(rs.getDouble(23));
			car.setSeatingCapacityDesc(rs.getString(24));
			car.setSideDoorsDesc(rs.getString(25));
			car.setStationaryNoiseDataDesc(rs.getString(26));
			car.setTestSpeedDesc(rs.getString(27));
			car.setTransmissionTypeDesc(rs.getString(28));
			car.setVariant(rs.getString(29));
			car.setVehicleClassDesc(rs.getString(30));
			car.setVehicleDisplayId(rs.getInt(31));
			return car;
		}else {
			return null;
		}
	}
	
	//Login validation for admin
	public int loginValidate (String username, String pass) {
		try {
			String queryString = "select * from `"+userTable+"` WHERE (username = '" + username +"') AND (password = '" + pass + "')";
			this.preparedStatement = this.connection.prepareStatement(queryString);
			ResultSet rs = this.preparedStatement.executeQuery(queryString);
			while (rs.next()) {
				return 1;
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	//Write logs to data base
	public int writeLog (Log log) {

		try {
			String queryString = "INSERT IGNORE INTO `"+logTable+"` (`date`, `rowNr`, `logTxt`, `source`, `isDone`) VALUES (?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, log.getDate());
			preparedStatement.setInt(2, log.getRowNr());
			preparedStatement.setString(3, log.getLogtxt());
			preparedStatement.setString(4, log.getSource());
			preparedStatement.setBoolean(5, log.getIsDone());
			int row = preparedStatement.executeUpdate();
			if (row > 0) {
				return 1;
			}else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	//Read logs from database
	public ArrayList<Log> getLogs() throws Exception {
		String queryString = "SELECT * FROM "+logTable+" LIMIT 50";
		this.preparedStatement = this.connection.prepareStatement(queryString);
		ResultSet rs = this.preparedStatement.executeQuery(queryString);
		ArrayList<Log> logsList = new ArrayList<Log> ();
		while(rs.next()) {		
			Log log =new Log();
			log.setDate(rs.getString(2));
			log.setRowNr(rs.getInt(3));
			log.setLogtxt(rs.getString(4));
			log.setSource(rs.getString(5));
			log.setIsDone(rs.getBoolean(6));
			logsList.add(log);
		}
		return logsList;
	}
	
	//Read last success update from GVG data providers
	public String lastSuccessLogDate () {
		try {
			String queryString = "select * from `"+logTable+"` WHERE `isDone` = 1 AND `source` = 'GVG Site' ORDER BY id DESC LIMIT 1";
			this.preparedStatement = this.connection.prepareStatement(queryString);
			ResultSet rs = this.preparedStatement.executeQuery(queryString);
			while (rs.next()) {
				return rs.getString(2).substring(0,10);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Update admin password
	public int updatePass (String username,String pass) {

		try {
			String queryString = "UPDATE `"+userTable+"` SET `password` = ? WHERE (`username` = ?);";
			preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, pass);
			preparedStatement.setString(2, username);
			int row = preparedStatement.executeUpdate();
			if (row > 0) {
				return 1;
			}else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}