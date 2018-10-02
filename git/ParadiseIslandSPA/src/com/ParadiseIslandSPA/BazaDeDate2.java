package com.ParadiseIslandSPA;

import java.sql.*;


class BazaDeDate2{
public static void main(String args[]){
	System.out.println("Da");
try{
Class.forName("com.mysql.cj.jdbc.Driver"); // aici am facut o modificare pentru ca era deprecated; adaugat cj

Connection con = DriverManager.getConnection("jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2240093","sql2240093","cC6!xF7*");

Statement stmt = con.createStatement();

ResultSet rs = stmt.executeQuery("select * from clienti");

while(rs.next())
System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  " + rs.getString(5)+"  "+rs.getString(6));

con.close();

}catch(Exception e){ System.out.println(e);}

}
}