package Finalmock;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.Scanner;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Execute;
public class JDBCmock {
public static void main(String[] args) throws Exception {
	Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/jspider","root","root");
//	PreparedStatement p=c.prepareStatement("create database jspider");
//	PreparedStatement p=c.prepareStatement("create table details(id int,name varchar(50),image longblob,ph_number long)");
	Scanner sc=new Scanner(System.in);
	boolean flag=true;
	while(flag) {
		System.out.println("enter valid option");
		System.out.println("1.Add");
		System.out.println("2.Fetch");
		System.out.println("3.Remove");
		System.out.println("4.Exit");
		int opt=sc.nextInt();
	switch (opt) {
	case 1:{
		PreparedStatement p=c.prepareStatement("insert into details value(?,?,?,?)");
		System.out.println("enter id");
		p.setInt(1, sc.nextInt());
		System.out.println("enter name");
		p.setString(2, sc.next());
		System.out.println("image path");
		Scanner sc2=new Scanner(System.in);
		FileInputStream f=new FileInputStream(sc2.nextLine());
		p.setBinaryStream(3, f,f.available());
		System.out.println("enter Number");
		p.setLong(4, sc2.nextLong());
		int e=p.executeUpdate();
		System.out.println(e);
	}
	break;
	case 2:{
		PreparedStatement p=c.prepareStatement("select * from details where id=?");
		System.out.println("enter id");
		p.setString(1, sc.next());
		ResultSet e=p.executeQuery();
		while(e.next())
		{
			System.out.println("id :"+e.getInt(1));
			System.out.println("name :"+e.getString(2));
			Scanner sc1=new Scanner(System.in);
			System.out.println("enter the image");
			Blob b=e.getBlob(3);
			FileOutputStream f=new FileOutputStream(sc1.nextLine());
			f.write(b.getBytes(1, (int)b.length()));
			System.out.println("ph no :"+e.getLong(4));
			}
	}
		break;
			case 3:{
				PreparedStatement p=c.prepareStatement("delete from details where id=?");
				System.out.println("enter id");
				p.setString(1, sc.next());
				int e=p.executeUpdate();
				System.out.println(e);
				System.out.println("Removed");
			}break;
	
	case 4:{
		flag=false;
		System.out.println("Ok Prends see u again........!");
	}break;
	default:{
		System.out.println("Invalid Option");
		}break;
	
	
}
c.close();
	}	
}
}
