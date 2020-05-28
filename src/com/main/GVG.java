package com.main;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.axis.message.MessageElement;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import au.gov.greenvehicleguide.www.webservices.GetAllManufacturersVehicleReportDataResponseGetAllManufacturersVehicleReportDataResult;
import au.gov.greenvehicleguide.www.webservices.IGVGVehicleDataServiceProxy;
import com.main.VehicleReportData;

public class GVG {
	/*
	 * This Class is to bring data from GVG data web site
	 * The data will be received as XML file
	 * this class responsible for converting XML to car constructor
	 * Than update the database
	 * This class is keep writing log file for each step
	 * so the admin can track the updating on his screen
	 */

	private ArrayList <Log> loadingLogs = new ArrayList <Log>();
	private String logFile = "log.dat";

	public ArrayList <VehicleReportData> updateGVG(String login, String password, String dateTimeFrom, String logDir) throws Exception {
		// TODO Auto-generated method stub
		dateTimeFrom = dataFormater(dateTimeFrom);

		IGVGVehicleDataServiceProxy locator = new IGVGVehicleDataServiceProxy();
		ArrayList<VehicleReportData> cars = new ArrayList<VehicleReportData>();

		loadingLogs.add(new Log("Connecting to GVG..."));
		writeLog( logDir+logFile , loadingLogs);

		MessageElement[] messageElementsDta;

		//Connect GVG data web site with user name and password 
		GetAllManufacturersVehicleReportDataResponseGetAllManufacturersVehicleReportDataResult getAllManufacturersVehicleReportData13mtemp = locator.getAllManufacturersVehicleReportData(login, password,dateTimeFrom);
		messageElementsDta = getAllManufacturersVehicleReportData13mtemp.get_any();

		//Converting XML to the constructor
		cars = new ArrayList<VehicleReportData>();
		cars = getFullVehicleReportDatasFromXml(messageElementsDta,"VehicleReportData", logDir);

		loadingLogs.add(new Log("Successfully connected to GVG"));
		writeLog( logDir+logFile , loadingLogs);

		return cars;
	}

	//Converting XML to the constructor
	private ArrayList <VehicleReportData> getFullVehicleReportDatasFromXml(MessageElement[] mE, String tagName, String logDir) throws Exception {

		int loadingPer100 = 0;
		int maxLength = mE[0].getLength();
		int each10 = maxLength/10;

		loadingLogs.add(new Log("Start bringing data from GVG..."));
		writeLog( logDir+logFile , loadingLogs);

		ArrayList<VehicleReportData> cars = new ArrayList<VehicleReportData>(mE[0].getLength());
		for(int i=0; i < maxLength ; i++) { 

			Node portType = (Node) mE[0].getElementsByTagName(tagName).item(i);         
			NodeList operations = portType.getChildNodes();

			//This function is to read data from XML, this function takes time more than other functions but..
			//it is more accurate, and prevent the local data base from any changes in meta data from the source data provider. 
			//the other function to update database like JSON or CSV is fast methods, but it has their disadvantages too
			VehicleReportData car = new VehicleReportData();
			for(int j = 0 ; j < operations.getLength();j++) {
				NodeList tmp_node = operations.item(j).getChildNodes(); 
				try {
					if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("BodyStyleDesc")) {
						car.setBodyStyleDesc(tmp_node.item(0).getNodeValue());
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("CO2Emissions")) {
						car.setCO2Emissions(Integer.parseInt(tmp_node.item(0).getNodeValue()));
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("CO2EmissionsExtraUrban")) {
						car.setCO2EmissionsExtraUrban(Integer.parseInt(tmp_node.item(0).getNodeValue()));
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("CO2EmissionsUrban")) {
						car.setCO2EmissionsUrban(Integer.parseInt(tmp_node.item(0).getNodeValue()));
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("CertificationLevelDesc")) {
						car.setCertificationLevelDesc(tmp_node.item(0).getNodeValue());
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("DrivingWheelsDesc")) {
						car.setDrivingWheelsDesc(tmp_node.item(0).getNodeValue());
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("ElectricRangeKm")) {
						car.setElectricRangeKm(Integer.parseInt(tmp_node.item(0).getNodeValue()));
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("EnergyConsumptionWhkm")) {
						car.setEnergyConsumptionWhkm(Double.parseDouble(tmp_node.item(0).getNodeValue()));
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("EngineConfigurationDesc")) {
						car.setEngineConfigurationDesc(tmp_node.item(0).getNodeValue());
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("EngineDisplacementDesc")) {
						car.setEngineDisplacementDesc(tmp_node.item(0).getNodeValue());
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("ForwardGearsDesc")) {
						car.setForwardGearsDesc(Integer.parseInt(tmp_node.item(0).getNodeValue()));
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("FuelConsumption")) {
						car.setFuelConsumption(Double.parseDouble(tmp_node.item(0).getNodeValue()));
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("FuelConsumptionExtraUrban")) {
						car.setFuelConsumptionExtraUrban(Double.parseDouble(tmp_node.item(0).getNodeValue()));
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("FuelConsumptionUrban")) {
						car.setFuelConsumptionUrban(Double.parseDouble(tmp_node.item(0).getNodeValue()));
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("FuelTypeDesc")) {
						car.setFuelTypeDesc(tmp_node.item(0).getNodeValue());
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("InductionDesc")) {
						car.setInductionDesc(tmp_node.item(0).getNodeValue());
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("IsCurrentModelDesc")) {
						car.setIsCurrentModelDesc(tmp_node.item(0).getNodeValue());
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("Make")) {
						car.setMake(tmp_node.item(0).getNodeValue());
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("MarketingModel")) {
						car.setMarketingModel(tmp_node.item(0).getNodeValue());
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("ModelReleaseVersion")) {
						car.setModelReleaseVersion(tmp_node.item(0).getNodeValue());
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("ModelReleaseYear")) {
						car.setModelReleaseYear(Integer.parseInt(tmp_node.item(0).getNodeValue()));
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("NonCurrentOnDate")) {
						car.setNonCurrentOnDate(Double.parseDouble(tmp_node.item(0).getNodeValue()));
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("PublishedDate")) {
						car.setPublishedDate(Double.parseDouble(tmp_node.item(0).getNodeValue()));
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("setSeatingCapacityDesc")) {
						car.setSeatingCapacityDesc(tmp_node.item(0).getNodeValue());
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("SideDoorsDesc")) {
						car.setSideDoorsDesc(tmp_node.item(0).getNodeValue());
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("StationaryNoiseDataDesc")) {
						car.setStationaryNoiseDataDesc(tmp_node.item(0).getNodeValue());
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("TestSpeedDesc")) {
						car.setTestSpeedDesc(tmp_node.item(0).getNodeValue());
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("TransmissionTypeDesc")) {
						car.setTransmissionTypeDesc(tmp_node.item(0).getNodeValue());
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("Variant")) {
						car.setVariant(tmp_node.item(0).getNodeValue());
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("VehicleClassDesc")) {
						car.setVehicleClassDesc(tmp_node.item(0).getNodeValue());
					} else if(tmp_node.item(0).getParentNode().getNodeName().equalsIgnoreCase("VehicleDisplayId")) {
						car.setVehicleDisplayId(Integer.parseInt(tmp_node.item(0).getNodeValue()));
					}
				} catch (Exception e) {
				}
			}

			//Loading process
			if(i%each10==0) {
				loadingLogs.add(new Log(loadingPer100 + " percent of proceeds..."));
				writeLog( logDir+logFile , loadingLogs);
				loadingPer100 += 10;
			}
			cars.add(car);
		}

		//If no data received
		if(maxLength==0) {
			loadingPer100 = 100;
			loadingLogs.add(new Log("There are no data to pring."));
			writeLog( logDir+logFile , loadingLogs);
		}else {
			loadingLogs.add(new Log("Loading data has been done successfully."));
			writeLog( logDir+logFile , loadingLogs);
		}
		return cars;
	}

	//Write data to file method
	private static void writeLog(String fileName, ArrayList<Log> logs) {
		String content = "";
		for(Log l : logs) {
			content += (l.getDate()+" -- " + l.getLogtxt() +"<br>");
		}
		try {
			FileWriter myWriter = new FileWriter(fileName);
			myWriter.write(content);
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Setting date formatter
	private static String dataFormater(String stringIn) throws ParseException {
		DateFormat formatIn = new SimpleDateFormat("YYYY-MM-dd");
		Date dateIn = formatIn.parse(stringIn);

		DateFormat formatOut = new SimpleDateFormat("dd/MM/YYYY");
		String stringOut = formatOut.format(dateIn);
		return stringOut;
	}

}

