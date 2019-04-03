package simplejdbc;

import static java.lang.System.console;
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
        OrderEntity order = new OrderEntity(7, 2, 980001, 4, 650, "2019-02-03", "2019-01-20", FCompany.JetLag.toString());
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
        assertEquals(4,o.getQty());
    }
    
//    @Test
//    public void testModifCommande() throws SQLException {
//        int num=9;
//        int qtt=10;
//        OrderEntity o=myDAO.selectCommande(num);
//        FCompany fCompany=FCompany.Postissimo;
//        myDAO.modifCommande(num, qtt, fCompany);
//        assertEquals(10,o.getQty());
//    }
    
    @Test
    public void testSelectDescription() throws SQLException{
        int id=980001;
        String description= myDAO.selectDescriptionProd(id);
        assertEquals(description,"Identity Server");
    }
    
    @Test
    public void testSelectIdProd() throws SQLException{
        String description="Identity Server";
        int id= myDAO.selectIdProd(description);
        assertEquals(id,980001);
    }
    
    @Test
    public void testSelectProdById() throws SQLException{
        int id=980001;
        ProductEntity prod= myDAO.selectProductById(id);
        assertEquals(prod.getName(),"Identity Server");
    }
    
    @Test
    public void testPrixProduit() throws SQLException{
        int id=980001;
        float prix= myDAO.prixProduit(id);
        assertEquals(prix,1095.00,0);
    }
    
    @Test
    public void testCAProduit() throws SQLException{
        String descript="Identity Server";
        String deb="2019-01-01";
        String fin="2019-03-31";
        float CA= myDAO.CAProduit(descript, deb, fin);
        float testCA=1095*8+650*2;
        assertEquals(CA,testCA,0);        
    }
    
    @Test
    public void testCAClient() throws SQLException{
        int id=2;
        String deb="2019-01-01";
        String fin="2019-03-31";
        float CA= myDAO.CAClient(id, deb, fin);
        float testCA=1095*8+650*2;
        boolean test=false;
        if (CA==testCA){
            test=true;
        }
        assertFalse(test);
        
    }
    
}
