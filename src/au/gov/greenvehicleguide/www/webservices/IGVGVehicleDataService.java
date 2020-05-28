/**
 * IGVGVehicleDataService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package au.gov.greenvehicleguide.www.webservices;

public interface IGVGVehicleDataService extends java.rmi.Remote {
    public au.gov.greenvehicleguide.www.webservices.GetAllManufacturersVehicleReportDataResponseGetAllManufacturersVehicleReportDataResult getAllManufacturersVehicleReportData(java.lang.String login, java.lang.String password, java.lang.String dateTimeFrom) throws java.rmi.RemoteException;
    public au.gov.greenvehicleguide.www.webservices.GetAllManufacturersVehicleReportDataSchemaResponseGetAllManufacturersVehicleReportDataSchemaResult getAllManufacturersVehicleReportDataSchema(java.lang.String login, java.lang.String password) throws java.rmi.RemoteException;
}
