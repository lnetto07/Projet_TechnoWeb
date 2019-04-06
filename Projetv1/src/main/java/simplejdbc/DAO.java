package simplejdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;
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

    //methode DAO récupérer le nom d'un client à partir de son email
    public String selectNomByEmail(String email) throws SQLException {
        String sql = "SELECT NAME FROM CUSTOMER WHERE EMAIL=? ";
        String name = "";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                name = rs.getString("NAME");
            }
            return name;
        }
    }

    //Methode DAO utile pour modifier une commande:
    //Récupérer une commande en fonction de son numéro
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
    
    //Methode DAO:Récupérer un produit en fonction de son id
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
    
    // Méthode DAO: Récupérer la description d'un produit à partir de son id
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
    
    // Méthode DAO: selectionner l'id d'un produit à partir de sa description
    //Il est plus facilepour l'utilisateur de connaitre le description que l'id mais les tables utilisent l'id
    public int selectIdProd(String description) throws SQLException {
        String sql = "SELECT PRODUCT_ID FROM PRODUCT WHERE DESCRIPTION=?";
        int id = 0;
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, description);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("PRODUCT_ID");

            }
            return id;
        }
    }
    
    // Méthode DAO: Récupérer le no d'un client à partir de son id
    public String selectNomById(int id) throws SQLException {
        String sql = "SELECT NAME FROM CUSTOMER WHERE CUSTOMER_ID=? ";
        String name = "";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                name = rs.getString("NAME");
            }
            return name;
        }
    }

    // Méthode DAO: Récupérer l'id d'un client à partir de son nom
    //Il est plus facile pour l'utilisateur de connaitre le nom que l'id mais les tables utilisent l'id
    public int selectIdClient(String name) throws SQLException {
        String sql = "SELECT CUSTOMER_ID FROM CUSTOMER WHERE NAME=?";
        int id = 0;
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("CUSTOMER_ID");

            }
            return id;
        }
    }
    
    //Méthode DAO: Obtenir la liste des description de produit
    // Utile pour choisir le produit de calcul du CA
    public List<String> listeProduit() throws SQLException {
        List<String> produits = new LinkedList<>();
        String sql = "SELECT DESCRIPTION FROM PRODUCT";
        try (Connection connection = myDataSource.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String description = rs.getString("DESCRIPTION");
                produits.add(description);

            }
            return produits;
        }
    }

    //Méthode DAO: Obtenir la liste des noms des clients
    // Utile pour choisir le client de calcul du CA
    public List<String> listeClient() throws SQLException {
        List<String> clients = new LinkedList<>();
        String sql = "SELECT NAME FROM CUSTOMER";
        try (Connection connection = myDataSource.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String name = rs.getString("NAME");
                clients.add(name);

            }
            return clients;
        }
    }

    //Méthode DAO: Obtenir la liste des états dans lesquels on a des clients
    // Utile pour choisir l'état de calcul du CA
    public List<String> listeState() throws SQLException {
        List<String> states = new LinkedList<>();
        String sql = "SELECT STATE FROM CUSTOMER GROUP BY STATE";
        try (Connection connection = myDataSource.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String etat = rs.getString("STATE");
                states.add(etat);

            }
            return states;
        }
    }
    
    //Méthodes DAO Client
    //Methode DAO Client Obtenir la liste des commandes effctuées par un client à partir de son nom
    public List<OrderEntity> commandesExistantes(String clientName) throws SQLException {
        List<OrderEntity> commande = new LinkedList<>();
        // Une requête SQL paramétrée
        String sql = "SELECT * FROM PURCHASE_ORDER WHERE CUSTOMER_ID=(SELECT CUSTOMER_ID FROM CUSTOMER WHERE NAME=?) ";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, clientName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // On récupère les champs nécessaires à l'enregistrement d'une commande
                    int num = rs.getInt("ORDER_NUM");
                    int idCustom = rs.getInt("CUSTOMER_ID");
                    int idProd = rs.getInt("PRODUCT_ID");
                    int qtt = rs.getInt("QUANTITY");
                    float shipCost = rs.getFloat("SHIPPING_COST");
                    String shipDate = rs.getString("SHIPPING_DATE");
                    String saleDate = rs.getString("SALES_DATE");
                    String freight = rs.getString("FREIGHT_COMPANY");
                    // On crée la commande
                    OrderEntity o = new OrderEntity(num, idCustom, idProd, qtt, shipCost, shipDate, saleDate, freight);
                    // On l'ajoute à la liste des commandes
                    commande.add(o);
                }
            }
        }
        return commande;
    }

    // Méthode DAO Client utile pour ajouter une commande: 
    //Récupérer le max des num de commande pour en générer un lors d'une nouvelle commande
    public int numNewCommande() throws SQLException {
        String sql = "SELECT ORDER_NUM  FROM PURCHASE_ORDER ";
        ArrayList<Integer> orderNum=new ArrayList<Integer>();
        int max = 0;
        try (Connection connection = myDataSource.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int order=rs.getInt("ORDER_NUM");
                orderNum.add(order);
            }
                max = (int)Collections.max(orderNum)+1;
            }
            return max;
        }
    
    //Methode DAO Client: Ajouter une commande
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

    // Méthode DAO Client:Modifier une commande
    //La commandes est choisit par son num et on peut modifier la quantité et la compagnie d'envoi.
    //La date de la commande change aussi pour être fixée à la date du jour
    public OrderEntity modifCommande(int num, int qtt, FCompany fCompany, String date) throws SQLException {
        OrderEntity order = selectCommande(num);
        String sql = "UPDATE PURCHASE_ORDER SET ORDER_NUM=?, CUSTOMER_ID=?, PRODUCT_ID=?, QUANTITY=?, SHIPPING_COST=?, SALES_DATE=?, SHIPPING_DATE=?, FREIGHT_COMPANY=? WHERE ORDER_NUM=?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getOrderId());
            stmt.setInt(2, order.getCustomerId());
            stmt.setInt(3, order.getProductId());
            stmt.setInt(4, qtt);
            stmt.setFloat(5, order.getShipCost());
            stmt.setString(6, date);
            stmt.setString(7, order.getShipDate());
            stmt.setString(8, fCompany.toString());
            stmt.setInt(9, num);
            stmt.executeUpdate();
            order = selectCommande(num);
        }
        return order;
    }

    // Méthode DAO Client: supprimer une commande à partir de son num
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

    
        // Méthodes DAO Administrateur
    
    // Méthode DAO utile pour calculer un CA: récupérer le prix d'un produit à partir de son id  
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

// Méthode DAO admin: Calcul du chiffre d'affaires avec un produit    
// fin et début au format AAAA-MM-JJ
    public float CAProduit(String description, String debut, String fin) throws SQLException {
        // Requete pour récupérer le prix d'une commande 
        int idProd = selectIdProd(description);
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

    
// Méthode DAO admin: Calcul du chiffre d'affaires avec un Client   
// fin et début au format AAAA-MM-JJ
    public float CAClient(String name, String debut, String fin) throws SQLException {
        float CA = 0;
        int idClient = selectIdClient(name);
        String sql = "SELECT ORDER_NUM FROM PURCHASE_ORDER WHERE CUSTOMER_ID=? AND SALES_DATE BETWEEN ? AND ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setFloat(1, idClient);
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

    
    // Méthode DAO admin: Calcul du chiffre d'affaires avec dans une zone  
    // fin et début au format AAAA-MM-JJ
    public float CAZone(String state, String debut, String fin) throws SQLException {
        float CA = 0;
        String sql = "SELECT ORDER_NUM FROM PURCHASE_ORDER JOIN CUSTOMER USING (CUSTOMER_ID) WHERE CUSTOMER.STATE= ? AND SALES_DATE BETWEEN ? AND ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, state);
            stmt.setString(2, debut);
            stmt.setString(3, fin);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int orderNum = rs.getInt("ORDER_NUM");
                OrderEntity order = selectCommande(orderNum);
                CA = CA + order.calculPrixTot(orderNum);
            }
            return CA;
        }

    }

}
