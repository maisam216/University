package au.gov.greenvehicleguide.www.webservices;

public class IGVGVehicleDataServiceProxy implements au.gov.greenvehicleguide.www.webservices.IGVGVehicleDataService {
  private String _endpoint = null;
  private au.gov.greenvehicleguide.www.webservices.IGVGVehicleDataService iGVGVehicleDataService = null;
  
  public IGVGVehicleDataServiceProxy() {
    _initIGVGVehicleDataServiceProxy();
  }
  
  public IGVGVehicleDataServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initIGVGVehicleDataServiceProxy();
  }
  
  private void _initIGVGVehicleDataServiceProxy() {
    try {
      iGVGVehicleDataService = (new au.gov.greenvehicleguide.www.webservices.GVGDataServiceLocator()).getBasicHttpBinding_IGVGVehicleDataService();
      if (iGVGVehicleDataService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iGVGVehicleDataService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iGVGVehicleDataService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iGVGVehicleDataService != null)
      ((javax.xml.rpc.Stub)iGVGVehicleDataService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public au.gov.greenvehicleguide.www.webservices.IGVGVehicleDataService getIGVGVehicleDataService() {
    if (iGVGVehicleDataService == null)
      _initIGVGVehicleDataServiceProxy();
    return iGVGVehicleDataService;
  }
  
  public au.gov.greenvehicleguide.www.webservices.GetAllManufacturersVehicleReportDataResponseGetAllManufacturersVehicleReportDataResult getAllManufacturersVehicleReportData(java.lang.String login, java.lang.String password, java.lang.String dateTimeFrom) throws java.rmi.RemoteException{
    if (iGVGVehicleDataService == null)
      _initIGVGVehicleDataServiceProxy();
    return iGVGVehicleDataService.getAllManufacturersVehicleReportData(login, password, dateTimeFrom);
  }
  
  public au.gov.greenvehicleguide.www.webservices.GetAllManufacturersVehicleReportDataSchemaResponseGetAllManufacturersVehicleReportDataSchemaResult getAllManufacturersVehicleReportDataSchema(java.lang.String login, java.lang.String password) throws java.rmi.RemoteException{
    if (iGVGVehicleDataService == null)
      _initIGVGVehicleDataServiceProxy();
    return iGVGVehicleDataService.getAllManufacturersVehicleReportDataSchema(login, password);
  }
  
  
}