/*
 * Maisam Alatrach 20091817
 * Oudai Fayek 20091861
 * Latrobe University
 * 29/05/2020
 */
package com.main;

import java.io.Serializable;
/*
 * 
 * Car constructor
 * This constructor has been made to be copy of the
 * meta data with the GVG data
 * 
 */

public class VehicleReportData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String BodyStyleDesc;
	private int CO2Emissions;
	private int CO2EmissionsExtraUrban;
	private int CO2EmissionsUrban;
	private String CertificationLevelDesc;
	private String DrivingWheelsDesc;
	private int ElectricRangeKm;
	private Double EnergyConsumptionWhkm;
	private String EngineConfigurationDesc;
	private String EngineDisplacementDesc;
	private int ForwardGearsDesc;
	private Double FuelConsumption;
	private Double FuelConsumptionExtraUrban;
	private Double FuelConsumptionUrban;
	private String FuelTypeDesc;
	private String InductionDesc;
	private String IsCurrentModelDesc;
	private String Make;
	private String MarketingModel;
	private String ModelReleaseVersion;
	private int ModelReleaseYear;
	private Double NonCurrentOnDate;
	private Double PublishedDate;
	private String SeatingCapacityDesc;
	private String SideDoorsDesc;
	private String StationaryNoiseDataDesc;
	private String TestSpeedDesc;
	private String TransmissionTypeDesc;
	private String Variant;
	private String VehicleClassDesc;
	private int VehicleDisplayId;
	
	public VehicleReportData() {
		super();
		BodyStyleDesc = "...";
		CO2Emissions = -1;
		CO2EmissionsExtraUrban = -1;
		CO2EmissionsUrban = -1;
		CertificationLevelDesc = "...";
		DrivingWheelsDesc = "...";
		ElectricRangeKm = -1;
		EnergyConsumptionWhkm = -1.0;
		EngineConfigurationDesc = "...";
		EngineDisplacementDesc = "...";
		ForwardGearsDesc = -1;
		FuelConsumption = -1.0;
		FuelConsumptionExtraUrban = -1.0;
		FuelConsumptionUrban =-1.0;
		FuelTypeDesc = "...";
		InductionDesc = "...";
		IsCurrentModelDesc = "...";
		Make = "...";
		MarketingModel = "...";
		ModelReleaseVersion = "...";
		ModelReleaseYear = -1;
		NonCurrentOnDate = -1.0;
		PublishedDate = -1.0;
		SeatingCapacityDesc = "...";
		SideDoorsDesc = "...";
		StationaryNoiseDataDesc = "...";
		TestSpeedDesc = "...";
		TransmissionTypeDesc = "...";
		Variant = "...";
		VehicleClassDesc = "...";
		VehicleDisplayId = -1;
	}



	public String getBodyStyleDesc() {
		return BodyStyleDesc;
	}

	public void setBodyStyleDesc(String bodyStyleDesc) {
		BodyStyleDesc = bodyStyleDesc;
	}

	public int getCO2Emissions() {
		return CO2Emissions;
	}

	public void setCO2Emissions(int cO2Emissions) {
		CO2Emissions = cO2Emissions;
	}

	public int getCO2EmissionsExtraUrban() {
		return CO2EmissionsExtraUrban;
	}

	public void setCO2EmissionsExtraUrban(int cO2EmissionsExtraUrban) {
		CO2EmissionsExtraUrban = cO2EmissionsExtraUrban;
	}

	public int getCO2EmissionsUrban() {
		return CO2EmissionsUrban;
	}

	public void setCO2EmissionsUrban(int cO2EmissionsUrban) {
		CO2EmissionsUrban = cO2EmissionsUrban;
	}

	public String getCertificationLevelDesc() {
		return CertificationLevelDesc;
	}

	public void setCertificationLevelDesc(String certificationLevelDesc) {
		CertificationLevelDesc = certificationLevelDesc;
	}

	public String getDrivingWheelsDesc() {
		return DrivingWheelsDesc;
	}

	public void setDrivingWheelsDesc(String drivingWheelsDesc) {
		DrivingWheelsDesc = drivingWheelsDesc;
	}

	public int getElectricRangeKm() {
		return ElectricRangeKm;
	}

	public void setElectricRangeKm(int electricRangeKm) {
		ElectricRangeKm = electricRangeKm;
	}

	public Double getEnergyConsumptionWhkm() {
		return EnergyConsumptionWhkm;
	}

	public void setEnergyConsumptionWhkm(Double energyConsumptionWhkm) {
		EnergyConsumptionWhkm = energyConsumptionWhkm;
	}

	public String getEngineConfigurationDesc() {
		return EngineConfigurationDesc;
	}

	public void setEngineConfigurationDesc(String engineConfigurationDesc) {
		EngineConfigurationDesc = engineConfigurationDesc;
	}

	public String getEngineDisplacementDesc() {
		return EngineDisplacementDesc;
	}

	public void setEngineDisplacementDesc(String engineDisplacementDesc) {
		EngineDisplacementDesc = engineDisplacementDesc;
	}

	public int getForwardGearsDesc() {
		return ForwardGearsDesc;
	}

	public void setForwardGearsDesc(int forwardGearsDesc) {
		ForwardGearsDesc = forwardGearsDesc;
	}

	public Double getFuelConsumption() {
		return FuelConsumption;
	}

	public void setFuelConsumption(Double fuelConsumption) {
		FuelConsumption = fuelConsumption;
	}

	public Double getFuelConsumptionExtraUrban() {
		return FuelConsumptionExtraUrban;
	}

	public void setFuelConsumptionExtraUrban(Double fuelConsumptionExtraUrban) {
		FuelConsumptionExtraUrban = fuelConsumptionExtraUrban;
	}

	public Double getFuelConsumptionUrban() {
		return FuelConsumptionUrban;
	}

	public void setFuelConsumptionUrban(Double fuelConsumptionUrban) {
		FuelConsumptionUrban = fuelConsumptionUrban;
	}

	public String getFuelTypeDesc() {
		return FuelTypeDesc;
	}

	public void setFuelTypeDesc(String fuelTypeDesc) {
		FuelTypeDesc = fuelTypeDesc;
	}

	public String getInductionDesc() {
		return InductionDesc;
	}

	public void setInductionDesc(String inductionDesc) {
		InductionDesc = inductionDesc;
	}

	public String getIsCurrentModelDesc() {
		return IsCurrentModelDesc;
	}

	public void setIsCurrentModelDesc(String isCurrentModelDesc) {
		IsCurrentModelDesc = isCurrentModelDesc;
	}

	public String getMake() {
		return Make;
	}

	public void setMake(String make) {
		Make = make;
	}

	public String getMarketingModel() {
		return MarketingModel;
	}

	public void setMarketingModel(String marketingModel) {
		MarketingModel = marketingModel;
	}

	public String getModelReleaseVersion() {
		return ModelReleaseVersion;
	}

	public void setModelReleaseVersion(String modelReleaseVersion) {
		ModelReleaseVersion = modelReleaseVersion;
	}

	public int getModelReleaseYear() {
		return ModelReleaseYear;
	}

	public void setModelReleaseYear(int modelReleaseYear) {
		ModelReleaseYear = modelReleaseYear;
	}

	public Double getNonCurrentOnDate() {
		return NonCurrentOnDate;
	}

	public void setNonCurrentOnDate(Double nonCurrentOnDate) {
		NonCurrentOnDate = nonCurrentOnDate;
	}

	public Double getPublishedDate() {
		return PublishedDate;
	}

	public void setPublishedDate(Double publishedDate) {
		PublishedDate = publishedDate;
	}

	public String getSeatingCapacityDesc() {
		return SeatingCapacityDesc;
	}

	public void setSeatingCapacityDesc(String seatingCapacityDesc) {
		SeatingCapacityDesc = seatingCapacityDesc;
	}

	public String getSideDoorsDesc() {
		return SideDoorsDesc;
	}

	public void setSideDoorsDesc(String sideDoorsDesc) {
		SideDoorsDesc = sideDoorsDesc;
	}

	public String getStationaryNoiseDataDesc() {
		return StationaryNoiseDataDesc;
	}

	public void setStationaryNoiseDataDesc(String stationaryNoiseDataDesc) {
		StationaryNoiseDataDesc = stationaryNoiseDataDesc;
	}

	public String getTestSpeedDesc() {
		return TestSpeedDesc;
	}

	public void setTestSpeedDesc(String testSpeedDesc) {
		TestSpeedDesc = testSpeedDesc;
	}

	public String getTransmissionTypeDesc() {
		return TransmissionTypeDesc;
	}

	public void setTransmissionTypeDesc(String transmissionTypeDesc) {
		TransmissionTypeDesc = transmissionTypeDesc;
	}

	public String getVariant() {
		return Variant;
	}

	public void setVariant(String variant) {
		Variant = variant;
	}

	public String getVehicleClassDesc() {
		return VehicleClassDesc;
	}

	public void setVehicleClassDesc(String vehicleClassDesc) {
		VehicleClassDesc = vehicleClassDesc;
	}

	public int getVehicleDisplayId() {
		return VehicleDisplayId;
	}

	public void setVehicleDisplayId(int vehicleDisplayId) {
		VehicleDisplayId = vehicleDisplayId;
	}

	@Override
	public String toString() {
		return String.format(
				"Car [BodyStyleDesc= %s, CO2Emissions= %s, CO2EmissionsExtraUrban= %s, CO2EmissionsUrban= %s, CertificationLevelDesc= %s, DrivingWheelsDesc= %s, ElectricRangeKm= %s, EnergyConsumptionWhkm= %s, EngineConfigurationDesc= %s, EngineDisplacementDesc= %s, ForwardGearsDesc= %s, FuelConsumption= %s, FuelConsumptionExtraUrban= %s, FuelConsumptionUrban= %s, FuelTypeDesc=%s, InductionDesc= %s, IsCurrentModelDesc= %s, Make= %s, MarketingModel= %s, ModelReleaseVersion= %s, ModelReleaseYear= %s, NonCurrentOnDate= %s, PublishedDate= %s, SeatingCapacityDesc= %s, SideDoorsDesc= %s, StationaryNoiseDataDesc= %s, TestSpeedDesc= %s, TransmissionTypeDesc= %s, Variant= %s, VehicleClassDesc= %s, VehicleDisplayId= %s]\n",
				BodyStyleDesc, CO2Emissions, CO2EmissionsExtraUrban, CO2EmissionsUrban, CertificationLevelDesc,
				DrivingWheelsDesc, ElectricRangeKm, EnergyConsumptionWhkm, EngineConfigurationDesc,
				EngineDisplacementDesc, ForwardGearsDesc, FuelConsumption, FuelConsumptionExtraUrban,
				FuelConsumptionUrban, FuelTypeDesc, InductionDesc, IsCurrentModelDesc, Make, MarketingModel,
				ModelReleaseVersion, ModelReleaseYear, NonCurrentOnDate, PublishedDate, SeatingCapacityDesc,
				SideDoorsDesc, StationaryNoiseDataDesc, TestSpeedDesc, TransmissionTypeDesc, Variant, VehicleClassDesc,
				VehicleDisplayId);
	}
	
}
