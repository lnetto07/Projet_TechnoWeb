package simplejdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    //Methode DAO Client
    public List<OrderEntity> commandesExistantes(String clientName) throws SQLException {
        List<OrderEntity> commande = new LinkedList<>();
        // Une requête SQL paramétrée
        String sql = "SELECT * FROM PURCHASE_ORDER WHERE CUSTOMER_ID=(SELECT CUSTOMER_ID FROM CUSTOMER WHERE NAME=?) ";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, clientName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // On récupère les champs nécessaires de l'enregistrement courant
                    int num = rs.getInt("ORDER_NUM");
                    int idCustom = rs.getInt("CUSTOMER_ID");
                    int idProd = rs.getInt("PRODUCT_ID");
                    int qtt = rs.getInt("QUANTITY");
                    float shipCost = rs.getFloat("SHIPPING_COST");
                    String shipDate = rs.getString("SHIPPING_DATE");
                    String saleDate = rs.getString("SALES_DATE");
                    String freight = rs.getString("FREIGHT_COMPANY");
                    // On crée l'objet entité
                    OrderEntity o = new OrderEntity(num, idCustom, idProd, qtt, shipCost, shipDate, saleDate, freight);
                    // On l'ajoute à la liste des résultats
                    commande.add(o);
                }
            }
        }
        return commande;
    }

    public OrderEntity ajoutCommande(OrderEntity order) throws SQLException {
        String sql = "INSERT INTO PURCHASE_ORDER VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getOrderId());
            stmt.setInt(2, order.getCustomerId());
            stmt.setInt(3, order.getProductId());
            stmt.setInt(4, order.getQty());
            stmt.setFloat(5, order.getShipCost());
            stmt.setString(6, order.getSalesDate());
            stmt.setString(7, order.getShipDate());
            stmt.setString(8, order.getFCompany());
            stmt.executeUpdate();
        }
        OrderEntity o = new OrderEntity(order.getOrderId(), order.getCustomerId(), order.getProductId(), order.getQty(), order.getShipCost(), order.getSalesDate(), order.getShipDate(), order.getFCompany());

        return o;
    }

    public OrderEntity selectCommande(int num) throws SQLException {
        OrderEntity o = null;
        String sql = "SELECT * FROM PURCHASE_ORDER WHERE ORDER_NUM=? ";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, num);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int idCustom = rs.getInt("CUSTOMER_ID");
                int idProd = rs.getInt("PRODUCT_ID");
                int qtt = rs.getInt("QUANTITY");
                float shipCost = rs.getFloat("SHIPPING_COST");
                String shipDate = rs.getString("SHIPPING_DATE");
                String saleDate = rs.getString("SALES_DATE");
                String freight = rs.getString("FREIGHT_COMPANY");
                // On crée l'objet entité
                o = new OrderEntity(num, idCustom, idProd, qtt, shipCost, shipDate, saleDate, freight);
            }
            return o;
        }
    }

    public OrderEntity modifCommande(int num, int qtt, FCompany fCompany) throws SQLException {
        OrderEntity order = selectCommande(num);
        String sql = "UPDATE PURCHASE_ORDER SET (?, ?, ?, ?, ?, ?, ?, ?) WHERE ORDER_NUM=?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getOrderId());
            stmt.setInt(2, order.getCustomerId());
            stmt.setInt(3, order.getProductId());
            stmt.setInt(4, qtt);
            stmt.setFloat(5, order.getShipCost());
            stmt.setString(6, order.getSalesDate());
            stmt.setString(7, order.getShipDate());
            stmt.setString(8, fCompany.toString());
            stmt.setInt(9, num);
            stmt.executeUpdate();
        }
        return order;
    }

    public String supprimerCommande(int num) throws SQLException {
        String sql = "DELETE FROM PURCHASE_ORDER WHERE ORDER_NUM=?";

        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, num);
            stmt.executeUpdate();
            String s = "La commande a été supprimée";
            return s;
        }
    }

    public String selectDescriptionProd(int id) throws SQLException {
        String sql = "SELECT DESCRIPTION FROM PRODUCT WHERE PRODUCT_ID=? ";
        String descript = "";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                descript = rs.getString("DESCRIPTION");
            }
            return descript;
        }
    }
    // Méthodes DAO Administrateur

    public ProductEntity selectProductById(int id) throws SQLException {
        String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_ID=? ";
        ProductEntity p = null;
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int productId = rs.getInt("PRODUCT_ID");
                float prix = rs.getFloat("PURCHASE_COST");
                String descrip = rs.getString("DESCRIPTION");
                // On crée l'objet entité
                p = new ProductEntity(productId, descrip, prix);
            }
            return p;
        }
    }

    public float prixProduit(int id) throws SQLException {
        String sql = "SELECT PURCHASE_COST FROM PRODUCT WHERE PRODUCT_ID=?";
        float prix = 0;
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                prix = rs.getFloat("PURCHASE_COST");

            }
            return prix;
        }
    }

    // fin et début au format AAAA-MM-JJ
    public float CAProduit(int idProd, String debut, String fin) throws SQLException {
        // Requete pour récupérer le prix d'une commande 
        float CA = 0;
        String sql = "SELECT ORDER_NUM FROM PURCHASE_ORDER WHERE PRODUCT_ID=? AND SALES_DATE BETWEEN ? AND ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setFloat(1, idProd);
            stmt.setString(2, debut);
            stmt.setString(3, fin);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idOrder = rs.getInt("ORDER_NUM");
                OrderEntity order = selectCommande(idOrder);
                CA = CA + order.calculPrixTot(idOrder);

            }
            return CA;
        }

    }

}
