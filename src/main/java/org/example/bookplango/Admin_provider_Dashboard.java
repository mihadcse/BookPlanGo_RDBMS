package org.example.bookplango;

public class Admin_provider_Dashboard {
    Integer Service_id,Service_phone;
    String Service_name,Service_type;

    public Admin_provider_Dashboard(Integer service_id, String service_name,Integer service_phone, String service_type) {
        Service_id = service_id;
        Service_phone = service_phone;
        Service_name = service_name;
        Service_type = service_type;
    }

    public Integer getService_id() {
        return Service_id;
    }

    public void setService_id(Integer service_id) {
        Service_id = service_id;
    }

    public Integer getService_phone() {
        return Service_phone;
    }

    public void setService_phone(Integer service_phone) {
        Service_phone = service_phone;
    }

    public String getService_name() {
        return Service_name;
    }

    public void setService_name(String service_name) {
        Service_name = service_name;
    }

    public String getService_type() {
        return Service_type;
    }

    public void setService_type(String service_type) {
        Service_type = service_type;
    }
}
