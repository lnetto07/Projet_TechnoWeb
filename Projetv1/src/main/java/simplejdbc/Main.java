/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplejdbc;

import java.util.Date;

/**
 *
 * @author Netto Léa
 */
public class Main {
    
     public static void main(String[] args) throws Exception {
        //DAO myDAO = new DAO(DataSourceFactory.getDataSource());
        Date auj=new java.util.Date( );
        int day=auj.getDay();
        int mois=auj.getMonth();
        int annee=auj.getYear();
        Date d=new java.util.Date(annee,mois,day);
        String date=d.toString();
        System.out.println(day);
        System.out.println(date);
       

     }
     }

