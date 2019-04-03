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
//        int num=9;
//        int qtt=10;
//        OrderEntity o=myDAO.selectCommande(num);
        String descript="Identity Server";
        String deb="2019-01-01";
        String fin="2019-03-31";
        float CA= myDAO.CAProduit(descript, deb, fin);
        float testCA=1095*8+650*2;
//        int qt=o.getQty();
//        FCompany fCompany=FCompany.Postissimo;
//        int qtTest =myDAO.modifCommande(num, qtt, fCompany);
//          o.setQtt(10);
//        //int qtTest=o.getQty();
        System.out.println(CA);
       System.out.println(testCA);
//        boolean test=false;
//        if (CA==testCA){
//            test=true;
//        }
//        System.out.println(test);

     }
     }

