/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplejdbc;

import java.util.List;

/**
 *
 * @author Netto Léa
 */
public class Main {
    
     public static void main(String[] args) throws Exception {
        DAO myDAO = new DAO(DataSourceFactory.getDataSource());
        int taille=myDAO.maxCommande();
        System.out.println(taille);
       

     }
     }

