/**
 * GetAllManufacturersVehicleReportDataSchemaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package au.gov.greenvehicleguide.www.webservices;

public class GetAllManufacturersVehicleReportDataSchemaResponse  implements java.io.Serializable {
    private au.gov.greenvehicleguide.www.webservices.GetAllManufacturersVehicleReportDataSchemaResponseGetAllManufacturersVehicleReportDataSchemaResult getAllManufacturersVehicleReportDataSchemaResult;

    public GetAllManufacturersVehicleReportDataSchemaResponse() {
    }

    public GetAllManufacturersVehicleReportDataSchemaResponse(
           au.gov.greenvehicleguide.www.webservices.GetAllManufacturersVehicleReportDataSchemaResponseGetAllManufacturersVehicleReportDataSchemaResult getAllManufacturersVehicleReportDataSchemaResult) {
           this.getAllManufacturersVehicleReportDataSchemaResult = getAllManufacturersVehicleReportDataSchemaResult;
    }


    /**
     * Gets the getAllManufacturersVehicleReportDataSchemaResult value for this GetAllManufacturersVehicleReportDataSchemaResponse.
     * 
     * @return getAllManufacturersVehicleReportDataSchemaResult
     */
    public au.gov.greenvehicleguide.www.webservices.GetAllManufacturersVehicleReportDataSchemaResponseGetAllManufacturersVehicleReportDataSchemaResult getGetAllManufacturersVehicleReportDataSchemaResult() {
        return getAllManufacturersVehicleReportDataSchemaResult;
    }


    /**
     * Sets the getAllManufacturersVehicleReportDataSchemaResult value for this GetAllManufacturersVehicleReportDataSchemaResponse.
     * 
     * @param getAllManufacturersVehicleReportDataSchemaResult
     */
    public void setGetAllManufacturersVehicleReportDataSchemaResult(au.gov.greenvehicleguide.www.webservices.GetAllManufacturersVehicleReportDataSchemaResponseGetAllManufacturersVehicleReportDataSchemaResult getAllManufacturersVehicleReportDataSchemaResult) {
        this.getAllManufacturersVehicleReportDataSchemaResult = getAllManufacturersVehicleReportDataSchemaResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetAllManufacturersVehicleReportDataSchemaResponse)) return false;
        GetAllManufacturersVehicleReportDataSchemaResponse other = (GetAllManufacturersVehicleReportDataSchemaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getAllManufacturersVehicleReportDataSchemaResult==null && other.getGetAllManufacturersVehicleReportDataSchemaResult()==null) || 
             (this.getAllManufacturersVehicleReportDataSchemaResult!=null &&
              this.getAllManufacturersVehicleReportDataSchemaResult.equals(other.getGetAllManufacturersVehicleReportDataSchemaResult())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getGetAllManufacturersVehicleReportDataSchemaResult() != null) {
            _hashCode += getGetAllManufacturersVehicleReportDataSchemaResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetAllManufacturersVehicleReportDataSchemaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.greenvehicleguide.gov.au/webservices/", ">GetAllManufacturersVehicleReportDataSchemaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getAllManufacturersVehicleReportDataSchemaResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.greenvehicleguide.gov.au/webservices/", "GetAllManufacturersVehicleReportDataSchemaResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.greenvehicleguide.gov.au/webservices/", ">>GetAllManufacturersVehicleReportDataSchemaResponse>GetAllManufacturersVehicleReportDataSchemaResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
