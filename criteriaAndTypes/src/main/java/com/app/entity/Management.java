package com.app.entity;

import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.PropertyProjection;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import com.mysql.cj.x.protobuf.MysqlxCrud.Projection;

public class Management {

	Scanner sc=new Scanner(System.in);
	private static SessionFactory sf;
	static {
		Configuration cfg=new Configuration().configure();
		StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder();
		builder.applySettings(cfg.getProperties());
		ServiceRegistry sr=builder.build();
		sf=cfg.buildSessionFactory(sr);
	}
	
	//query like: select * from student;
	@SuppressWarnings("unchecked")
	public void CriteriaEx() {
		Session session=sf.openSession();
		Criteria cr=session.createCriteria(Student.class);
		cr.list().forEach(System.out::println);
	}
	
	//query like: select * from student where id=?;
	@SuppressWarnings("unchecked")
	public void RetrictionEx() {
		Session session=sf.openSession();
		Criteria cr=session.createCriteria(Student.class);
		System.out.println("Enter name ");
		String value=sc.next();
		cr.add(Restrictions.eq("name",value ));
		cr.list().forEach(System.out::println);
	}
	
	//query like: select name from student ;
	@SuppressWarnings("unchecked")
	public void ProjectionPs() {
		Session session=sf.openSession();
		Criteria cr=session.createCriteria(Student.class);
        cr.setProjection(Projections.rowCount());
        cr.list().forEach(System.out::println);
	}
	public static void main(String[] args) {
		Management m=new Management();
		//m.CriteriaEx();
		//m.RetrictionEx();
		m.ProjectionPs();
	}

}
