package Data.SQL_Impl;

import data.help_classes.*;

/**
 *
 * @author Emil PC
 */
public interface DataMapperCustomerInterface {
    
    
    public void createCustomer(Customer customer);
    
    public void createRequest(Request request, Customer customer);
    
    public void createCarport(Carport carport);
    
    public void createRoof(Roof roof);
    
    public void createShed(Shed shed);
 
    
    public int readCarportId(Carport carport);
    
    public int readCustomerId(Customer customer);
    
    public int readShedId(Shed shed);
    
    public int readRoofId(Roof roof);
    
}
