package com.softserve.tc.diaryclient.dao.impl;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.softserve.tc.diaryclient.dao.LoginDurationDAO;

public class Main {
public static void main(String[] args) {
//    DiaryServiceImpl rec = new DiaryServiceImpl();
//    String[][] mas= rec.getRecDate();
//    for(int i=0;i<30;i++){
//        for(int j=0;j<2;j++){
//            System.out.print(mas[i][j] + " ");
//        }
//        System.out.println(" ");
//    }
    ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
            "/META-INF/appContext.xml");
    LoginDurationDAO logDurDAO = (LoginDurationDAO)context.getBean("loginDurationDAO");   
    
    Map<Date, Double> map = logDurDAO.getGenLoginDuration();
    Map<Date, Double> treeMap = new TreeMap<Date, Double>(map);
    
System.out.println("MAP********"+map);

//  RecordDAOImpl rec = null ;
//  int[][] mas= rec.getRecordDateCount();
//  for(int i=0;i<29;i++){
//      for(int j=0;j<2;j++){
//          System.out.print(mas[i][j] + " ");
//      }
//      System.out.println(" ");
//  }
////  System.out.println(rec.getRecordDateCount());

}
}