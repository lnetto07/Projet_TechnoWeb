/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplejdbc;

/**
 *
 * @author Netto Léa
 */
public class Main {
    
     public static void main(String[] args) throws Exception {
        DAO myDAO = new DAO(DataSourceFactory.getDataSource());
          //int idProd=980001;
        int num=9;
        int qtt=10;
        OrderEntity o=myDAO.selectCommande(num);
        int qt=o.getQty();
        FCompany fCompany=FCompany.Postissimo;
        myDAO.modifCommande(num, qtt, fCompany);
//        int qtTest=o.getQty();
//        System.out.println(o);

     }
     }

