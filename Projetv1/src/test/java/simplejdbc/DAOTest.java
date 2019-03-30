package simplejdbc;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DAOTest {

    private DAO myDAO; // L'objet à tester
    private DataSource myDataSource; // La source de données à utiliser

    @Before
    public void setUp() throws SQLException {
        myDataSource = DataSourceFactory.getDataSource();
        myDAO = new DAO(myDataSource);
    }

    /**
     * Test of numberOfCustomers method, of class DAO.
     *
     * @throws simplejdbc.DAOException
     * @throws java.sql.SQLException
     */
    @Test
    public void testCommandeExistante() throws DAOException, SQLException {
        String clientName = "New Enterprises";
        List<OrderEntity> commandes = myDAO.commandesExistantes(clientName);
        assertEquals(3, commandes.size());
    }

    @Test
    public void testAjoutCommande() throws DAOException, SQLException {
        OrderEntity order = new OrderEntity(7, 2, 980001, 4, 650, "2019-02-03", "2019-01-20", "JetLag");
        myDAO.ajoutCommande(order);
        String clientName = "New Enterprises";
        List<OrderEntity> commandes = myDAO.commandesExistantes(clientName);
        assertEquals(4, commandes.size());
    }
    
    @Test
    public void testSupprCommande() throws SQLException {
        int num =7;
        myDAO.supprimerCommande(num);
        String clientName = "New Enterprises";
        List<OrderEntity> commandes = myDAO.commandesExistantes(clientName);
        assertEquals(3, commandes.size());    
    }
    
    @Test
    public void testSelectCommande() throws SQLException{
        int num=9;
        OrderEntity o=myDAO.selectCommande(num);
//       assertEquals(4,o.getQty());
    }
    
//    public void testModifCommande() {
//        int num=9;
//        int qtt=10;
//        String fCompany="Postissima";
//        myDAO.modifCommande(num, qtt, fCompany);
//        assertEquals(10,)
//    }
}
