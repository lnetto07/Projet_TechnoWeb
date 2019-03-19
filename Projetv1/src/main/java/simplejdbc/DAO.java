package simplejdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class DAO {

    protected final DataSource myDataSource;

    /**
     *
     * @param dataSource la source de données à utiliser
     */
    public DAO(DataSource dataSource) {
        this.myDataSource = dataSource;
    }

    /**
     *
     * @return le nombre d'enregistrements dans la table CUSTOMER
     * @throws DAOException
     */
    public int loginCustomer(String email, int customer_id) throws DAOException {
        int result = -1;

        String sql = "SELECT CUSTOMER_ID FROM CUSTOMER WHERE EMAIL="
                + "? AND CUSTOMER_ID=?";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setInt(2, customer_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // On a trouvé
                    result = rs.getInt("CUSTOMER_ID");

                    // On crée l'objet "entity"
                } // else on n'a pas trouvé, on renverra null
            }
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }

        return result;
    }

    public List<OrderEntity> commandesExistantes(CustomerEntity client) throws SQLException {
        List<OrderEntity> commande = new LinkedList<>();
        // Une requête SQL paramétrée
        String sql = "SELECT * FROM PURCHASE_ORDER WHERE CUSTOMER_ID=? ";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, client.getCustomerId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // On récupère les champs nécessaires de l'enregistrement courant
                    int num = rs.getInt("ORDER_NUM");
                    int idCustom = rs.getInt("CUSTOMER_ID");
                    int idProd = rs.getInt("PRODUCT_ID");
                    int qtt = rs.getInt("QUANTITY");
                    float shipCost = rs.getFloat("SHIPPING_COST");
                    Date shipDate = rs.getDate("SHIPPING_DATE");
                    Date saleDate = rs.getDate("SALES_DATE");
                    String freight = rs.getString("FREIGHT_COMPAGNY");
                    // On crée l'objet entité
                    OrderEntity o = new OrderEntity(num, idCustom, idProd, qtt, shipCost, shipDate, saleDate, freight);
                    // On l'ajoute à la liste des résultats
                    result.add(o);
                }
            }
        }
        return result;
    }

    public OrderEntity ajoutCommande(int num, int idCustom, int idProd, int qtt, float shipCost, Date shipDate, Date saleDate, String freight) throws SQLException {
        String sql = "INSERT INTO PURCHASE_ORDER VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, num);
            stmt.setInt(2, idCustom);
            stmt.setInt(3, idProd);
            stmt.setInt(4, qtt);
            stmt.setFloat(5, shipCost);
            stmt.setDate(6, (java.sql.Date) shipDate);
            stmt.setDate(7, (java.sql.Date) saleDate);
            stmt.setString(8, freight);
            result = stmt.executeUpdate();
        }
        return result;
    }

    public OrderEntity selectCommande(int num) throws SQLException {
        String sql = "SELECT * FROM PURCHASE_ORDER WHERE ORDER_NUM=? ";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, num);
            ResultSet rs = stmt.executeQuery();
            int idCustom = rs.getInt("CUSTOMER_ID");
            int idProd = rs.getInt("PRODUCT_ID");
            int qtt = rs.getInt("QUANTITY");
            float shipCost = rs.getFloat("SHIPPING_COST");
            Date shipDate = rs.getDate("SHIPPING_DATE");
            Date saleDate = rs.getDate("SALES_DATE");
            String freight = rs.getString("FREIGHT_COMPAGNY");
            // On crée l'objet entité
            OrderEntity o = new OrderEntity(num, idCustom, idProd, qtt, shipCost, shipDate, saleDate, freight);
            // On l'ajoute à la liste des résultats
            result.add(o);
            result = stmt.add(o);
        }
        return result;
    }
    
    public OrderEntity modifCommande(OrderEntity order) throws SQLException {
        String sql = "SELECT * FROM PURCHASE_ORDER WHERE ORDER_NUM=? ";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, num);
            ResultSet rs = stmt.executeQuery();
            int idCustom = rs.getInt("CUSTOMER_ID");
            int idProd = rs.getInt("PRODUCT_ID");
            int qtt = rs.getInt("QUANTITY");
            float shipCost = rs.getFloat("SHIPPING_COST");
            Date shipDate = rs.getDate("SHIPPING_DATE");
            Date saleDate = rs.getDate("SALES_DATE");
            String freight = rs.getString("FREIGHT_COMPAGNY");
            // On crée l'objet entité
            OrderEntity o = new OrderEntity(num, idCustom, idProd, qtt, shipCost, shipDate, saleDate, freight);
            // On l'ajoute à la liste des résultats
            result.add(o);
            result = stmt.add(o);
        }
        return result;
    }

}
