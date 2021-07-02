package com.example.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Lambda {
    public static void main(String[] args){
        String input = "samsung,'OEM Samsung Washing Machine Pulsator Washplate Cap Shipped With WA48J7700AW, WA48J7700AW/A2, WA48J7700AW/AA',20916,samsung,'OEM Samsung Chrome Washing Machine Washplate Pulsator Cap Shipped With WA52M7750AV, WA52M7750AV/A4, WA52M7750AW, WA52M7750AW/A4',91995,samsung,'SAMSUNG Washing Machine Spring Hanger, DC61-01257M', 22970,samsung,'Samsung DC97-17022B Assy Detergent',32959,samsung,'Samsung DC66-00470A DAMPER \n" +
                "SHOCK',29981,samsung,'DC64-00519D Samsung Washing Machine Door Lock Washer Dryer Dishwashe -MP#GH4498 349Y49HBRG9109150',52000,samsung,'Samsung DC97-16991A Assembly Filter',13000 \n";

        String[] reg = input.split("samsung");
        ArrayList<product> store = new ArrayList<product>();
        int l=1;
        for(String s: reg){
            if(l==1){
                l++;
                continue;
            }
            String[] temp = s.split("',");
            String id = temp[0];
            String price = temp[1];
            store.add(new product("samsung", id, price));
        }
//        for(int i=0; i<store.size(); i++){
//            System.out.println(store.get(i).id);
//            System.out.println("---------");
//        }
        PriorityQueue<product> pq = new PriorityQueue<product>(new customComparator());
        for (product p: store) {
            pq.add(p);
        }
        while (pq.size()!=0){
            product ans = pq.remove();
            System.out.println(ans.brand+",'"+ans.id+"',"+ans.price);
        }

    }
}
class customComparator implements Comparator<product>{

    @Override
    public int compare(product o1, product o2) {
        return o1.price- o2.price;
    }
}
class product{
    String brand;
    String id;
    int price;

    public product(String brand, String id, String price) {
        this.brand = brand;
        id = id.trim();
        id = id.replaceAll(",'","");
        id = id.replaceAll(", '","");
        id = id.replaceAll("\n"," ");
        this.id = id;
        price = price.trim();
        price = price.replaceAll(",", "");
        this.price = Integer.valueOf(price);
    }
}
