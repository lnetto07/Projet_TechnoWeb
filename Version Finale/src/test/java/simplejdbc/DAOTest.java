package simplejdbc;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
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
     * @throws java.sql.SQLException
     */
    @Test
    public void testSelectNameByEmail() throws SQLException{
        String email="www.smallbill.example.com";
        String name= myDAO.selectNomByEmail(email);
        assertEquals(name,"Small Bill Company");
    }
    
    @Test
    public void testSelectCommande() throws SQLException{
        int num=10398001;
        OrderEntity o=myDAO.selectCommande(num);
        assertEquals(10,o.getQty());
    }
    
    @Test
    public void testSelectProdById() throws SQLException{
        int id=980001;
        ProductEntity prod= myDAO.selectProductById(id);
        assertEquals(prod.getName(),"Identity Server");
    }
    
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
    public void testSelectNomById() throws SQLException{
        int id=3;
        String name= myDAO.selectNomById(id);
        assertEquals(name,"Small Bill Company");
    }
    
    @Test
    public void testSelectIdClient() throws SQLException{
        String name="Small Bill Company";
        int id= myDAO.selectIdClient(name);
        assertEquals(id,3);
    }
    
    @Test
    public void testListeProduit() throws DAOException, SQLException {
        List<String> produits = myDAO.listeProduit();
        assertEquals(30, produits.size());
    }
    
    @Test
    public void testListeClient() throws DAOException, SQLException {
        List<String> clients = myDAO.listeClient();
        assertEquals(13, clients.size());
    }
    
    @Test
    public void testListeEtat() throws DAOException, SQLException {
        List<String> etats = myDAO.listeState();
        assertEquals(6, etats.size());
    }
    
    @Test
    public void testCommandeExistante() throws DAOException, SQLException {
        String clientName = "Small Bill Company";
        List<OrderEntity> commandes = myDAO.commandesExistantes(clientName);
        assertEquals(1, commandes.size());
    }
    
//    @Test
//    public void testNumNewCommande() throws SQLException{
//        int max=myDAO.numNewCommande();
//        assertEquals(max,30298005);
//    }   

    @Test
    public void testAjoutCommande() throws DAOException, SQLException {
        OrderEntity order = new OrderEntity(7, 3, 980001, 4, 650, "2019-02-03", "2019-01-20", FCompany.JetLag.toString());
        myDAO.ajoutCommande(order);
        String clientName = "Small Bill Company";
        List<OrderEntity> commandes = myDAO.commandesExistantes(clientName);
        assertEquals(2, commandes.size());
    }
    
    @Test
    public void testModifCommande() throws SQLException {
        int num=10398002;
        int qtt=10;
        FCompany fCompany=FCompany.Postissimo;
        myDAO.modifCommande(num, qtt, fCompany);
        OrderEntity o=myDAO.selectCommande(num);
        assertEquals(10,o.getQty());
    }
    
    @Test
    public void testSupprCommande() throws SQLException {
        int num =7;
        myDAO.supprimerCommande(num);
        String clientName = "Small Bill Company";
        List<OrderEntity> commandes = myDAO.commandesExistantes(clientName);
        assertEquals(1, commandes.size());    
    }
    
    @Test
    public void testPrixProduit() throws SQLException{
        int id=980030;
        float prix= myDAO.prixProduit(id);
        assertEquals(prix,59.95,1);
    }
    
//  Fonctionne dans le main mais le test ne passe pas  
    @Test
    public void testCAProduit() throws SQLException{
        String descript="10Gb Ram";
        String deb="2011-04-12";
        String fin="2012-10-25";
        float CA= myDAO.CAProduit(descript, deb, fin);
        float testCA=(float) 874.5;
        assertEquals(testCA,CA,0);        
    }
    
    @Test
    public void testCAProduits() throws SQLException{
        String deb="2011-04-12";
        String fin="2012-10-25";
        HashMap CA= myDAO.CAProduits( deb, fin);
        int taille=CA.size();
        assertEquals(taille,30);
    }
    
    
    
    @Test
    public void testCAClient() throws SQLException{
        String name = "Small Bill Company";;
        String deb="2011-04-12";
        String fin="2012-10-25";
        float CA= myDAO.CAClient(name, deb, fin);
        float testCA=(float) 874.5;
        assertEquals(testCA,CA,0);
    }
    
    @Test
    public void testCAClients() throws SQLException{
        String deb="2011-04-12";
        String fin="2012-10-25";
        HashMap CA= myDAO.CAClients( deb, fin);
        int taille=CA.size();
        assertEquals(taille,13);
    }
    
    @Test
    public void testCAZone() throws SQLException{
        String state = "GA";
        String deb ="2011-04-12";
        String fin ="2012-10-25";
        float CAZ = myDAO.CAZone(state,deb,fin);
        float testCA=(float) 874.5;
        assertEquals(testCA, CAZ, 0.1);
    }
    
    @Test
    public void testCAZones() throws SQLException{
        String deb="2011-04-12";
        String fin="2012-10-25";
        HashMap CA= myDAO.CAZones( deb, fin);
        int taille=CA.size();
        assertEquals(taille,6);
    }
}