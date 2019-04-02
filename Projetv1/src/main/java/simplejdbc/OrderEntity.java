/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplejdbc;

import java.sql.SQLException;
import java.util.Date;
import javax.sql.DataSource;

/**
 *
 * @author Eva
 */
public class OrderEntity {
    private int orderId;
    private int customerId;
    private int productId;
    private int qty;
    private float shipCost;
    private String salesDate;
    private String shipDate;
    private String fCompany;
    

    public OrderEntity(int orderId, int customerId, int productId, int qty, float shipCost, String salesDate, String shipDate, String fCompany) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.qty = qty;
        this.shipCost = shipCost;
        this.salesDate = salesDate;
        this.shipDate = shipDate;
        this.fCompany = fCompany;
    }

    public int getOrderId() {
        return orderId;
    }
    
    public int getCustomerId() {
        return customerId;
    }
    
    public int getProductId() {
        return productId;
    }

    public int getQty() {
        return qty;
    }

    public float getShipCost() {
        return shipCost;
    }
    
    public String getSalesDate() {
        return salesDate;
    }
    
    public String getShipDate() {
        return shipDate;
    }
    
    public String getFCompany() {
        return fCompany;
    }
    public void setQtt(int newQtt){
        this.qty=newQtt;
    }
    
    public void setFCompany(FCompany newFCompany){
        this.fCompany=newFCompany.toString();
    }
    public float calculPrix(int orderId) throws SQLException{
        DAO myDAO;
        DataSource myDataSource;
        myDataSource = DataSourceFactory.getDataSource();
        myDAO = new DAO(myDataSource);
        OrderEntity order=myDAO.selectCommande(orderId);
        float prixProd=myDAO.prixProduit(order.getProductId());
        int qtt=order.getQty();
        float p=prixProd*qtt;
        return p;
    }
    
    public float calculPrixTot(int orderId) throws SQLException{
        DAO myDAO;
        DataSource myDataSource;
        myDataSource = DataSourceFactory.getDataSource();
        myDAO = new DAO(myDataSource);
        float prix=calculPrix(orderId);
        float fraisPort=myDAO.selectCommande(orderId).getShipCost();
        float p=prix+fraisPort;
        return p;
    }

}
