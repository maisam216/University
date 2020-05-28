/**
 * GVGDataServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package au.gov.greenvehicleguide.www.webservices;

public class GVGDataServiceLocator extends org.apache.axis.client.Service implements au.gov.greenvehicleguide.www.webservices.GVGDataService {

    public GVGDataServiceLocator() {
    }


    public GVGDataServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public GVGDataServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BasicHttpBinding_IGVGVehicleDataService
    private java.lang.String BasicHttpBinding_IGVGVehicleDataService_address = "https://www.greenvehicleguide.gov.au/VehicleDataService/GVGDataService.svc";

    public java.lang.String getBasicHttpBinding_IGVGVehicleDataServiceAddress() {
        return BasicHttpBinding_IGVGVehicleDataService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BasicHttpBinding_IGVGVehicleDataServiceWSDDServiceName = "BasicHttpBinding_IGVGVehicleDataService";

    public java.lang.String getBasicHttpBinding_IGVGVehicleDataServiceWSDDServiceName() {
        return BasicHttpBinding_IGVGVehicleDataServiceWSDDServiceName;
    }

    public void setBasicHttpBinding_IGVGVehicleDataServiceWSDDServiceName(java.lang.String name) {
        BasicHttpBinding_IGVGVehicleDataServiceWSDDServiceName = name;
    }

    public au.gov.greenvehicleguide.www.webservices.IGVGVehicleDataService getBasicHttpBinding_IGVGVehicleDataService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_IGVGVehicleDataService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_IGVGVehicleDataService(endpoint);
    }

    public au.gov.greenvehicleguide.www.webservices.IGVGVehicleDataService getBasicHttpBinding_IGVGVehicleDataService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            au.gov.greenvehicleguide.www.webservices.BasicHttpBinding_IGVGVehicleDataServiceStub _stub = new au.gov.greenvehicleguide.www.webservices.BasicHttpBinding_IGVGVehicleDataServiceStub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_IGVGVehicleDataServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_IGVGVehicleDataServiceEndpointAddress(java.lang.String address) {
        BasicHttpBinding_IGVGVehicleDataService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (au.gov.greenvehicleguide.www.webservices.IGVGVehicleDataService.class.isAssignableFrom(serviceEndpointInterface)) {
                au.gov.greenvehicleguide.www.webservices.BasicHttpBinding_IGVGVehicleDataServiceStub _stub = new au.gov.greenvehicleguide.www.webservices.BasicHttpBinding_IGVGVehicleDataServiceStub(new java.net.URL(BasicHttpBinding_IGVGVehicleDataService_address), this);
                _stub.setPortName(getBasicHttpBinding_IGVGVehicleDataServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("BasicHttpBinding_IGVGVehicleDataService".equals(inputPortName)) {
            return getBasicHttpBinding_IGVGVehicleDataService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.greenvehicleguide.gov.au/webservices/", "GVGDataService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.greenvehicleguide.gov.au/webservices/", "BasicHttpBinding_IGVGVehicleDataService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BasicHttpBinding_IGVGVehicleDataService".equals(portName)) {
            setBasicHttpBinding_IGVGVehicleDataServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
