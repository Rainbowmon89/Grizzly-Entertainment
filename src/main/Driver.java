package main;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import factories.SessionFactoryBuilder;
import models.Equipment;


public class Driver {

	public static void main(String[] args) {
		
		int id;
		String name;
		String avail;
		double rc;
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter equipmnet id:");
		id = scan.nextInt();
		//System.out.println("ID:"+id);
		System.out.println("Enter name:");
		name=scan.next();
		System.out.println("Enter avail");
		avail = scan.next();
		System.out.println("Enter cost:");
		rc  = scan.nextDouble();
		//Equipment equipment = new Equipment();
		Equipment equipment = new Equipment(id,name,avail,rc);
		String string = equipment.toString();
		//System.out.println(string);
		//equipment.create();
		List<Equipment> equip = equipment.readAll();
		for (Equipment eq :equip)
		{
			String info = eq.toString();
			System.out.println(info);
		}
		//equipment.update();
		/*
		
		//equipment.delete();
		//equip = equipment.readAll();
		
		
		
		*/
		//Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();
		//session.close();
	}

}
