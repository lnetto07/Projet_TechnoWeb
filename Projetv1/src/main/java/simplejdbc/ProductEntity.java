/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplejdbc;

/**
 *
 * @author Eva
 */
public class ProductEntity {

    private int productId;
    private String description;
    private float price;

    public ProductEntity(int productId, String description, float price) {
        this.productId = productId;
        this.description = description;
        this.price = price;
    }

    /**
     * Get the value of customerId
     *
     * @return the value of customerId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getName() {
        return description;
    }

    /**
     * Get the value of price
     *
     * @return the value of price
     */
    public float getPrice() {
        return price;
    }

}
