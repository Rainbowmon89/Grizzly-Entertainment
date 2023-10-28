package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import factories.SessionFactoryBuilder;

@Entity(name="Equipment")
@Table(name="equipment")
public class Equipment implements Serializable {
	@Id
	
	@Column(name="EquipmentID")
	int equipmentId;
	@Column(name="EquipmentName")
	String equipmentName;
	@Column(name="Availability")
	String availability;
	@Column(name="RentalCost")
	double rentalCost;
	
	public Equipment()
	{
		equipmentId = -1;
		equipmentName = "Default";
		availability = "Y";
		rentalCost = 0.0;
	}
	
	public Equipment(int equipmentId, String equipmentName, String availability, double rentalCost)
	{
		this.equipmentId = equipmentId;
		this.equipmentName = equipmentName;
		this.availability = availability;
		this.rentalCost = rentalCost;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public double getRentalCost() {
		return rentalCost;
	}

	public void setRentalCost(double rentalCost) {
		this.rentalCost = rentalCost;
	}
	
	
	
	@Override
	public String toString() {
		return "Equipment [equipmentId=" + equipmentId + ", equipmentName=" + equipmentName + ", availability="
				+ availability + ", rentalCost=" + rentalCost + "]";
	}

	public void create()
	{
		try 
		{
			Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			session.save(this);
			transaction.commit();
			session.close();
		}catch(HibernateException e)
		{
			e.printStackTrace();
		}
		
	}
	public void update()
	{
		Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Equipment equipment = (Equipment) session.get(Equipment.class, this.equipmentId);
		//equipment.setEquipmentName(this.equipmentName);
		equipment.setAvailability(this.availability);
		session.update(equipment);
		transaction.commit();
		session.close();
	}
	@SuppressWarnings("unchecked")
	public List<Equipment> readAll()
	{
		List<Equipment> eqList = new ArrayList<>();
		Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		eqList = (List<Equipment>) session.createQuery("FROM Equipment").getResultList();
		transaction.commit();
		session.close();
 		return eqList;
	}
	public void delete()
	{
		Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Equipment equipment = (Equipment) session.get(Equipment.class, this.equipmentId);
	    session.delete(equipment);
	    transaction.commit();
	    session.close();
	}

}
