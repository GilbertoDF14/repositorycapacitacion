package org.criteria.producto;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.modelo.producto.Producto;

/*
 * CRITERIA  Consultas ya predefinidas
 */

public class CRITERIAProducto {
	
	public static void caseCriteriaProducto() {
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			Criteria cr = session.createCriteria(Producto.class);
			
			//obtener productos con el precio mayor a 20.0
			//cr.add(Restrictions.gt("precio", 20.0));
			//obtener productos con el precio menor a 20.0
			//cr.add(Restrictions.lt("precio", 20.0));
			//filtrado de datos
			//.add(Restrictions.like("nombre", "L%"));
			//obtener  datos por rango 
			//cr.add(Restrictions.between("precio", 20.0, 30.0));
			//ordenamiento ascendente y descendente 
			//cr.addOrder(Order.asc("id"));
			cr.addOrder(Order.desc("id"));
			
			@SuppressWarnings("unchecked")
			List<Producto> productos = (List<Producto>) cr.list();
			
			for(Producto p: productos) {
				System.out.println(p.getId()+","+p.getNombre()+","+p.getPrecio());
			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void caseCriteriaUniqueResultProducto() {
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			Criteria cr = session.createCriteria(Producto.class);
			
			//obtener numero de filas
			//cr.setProjection(Projections.rowCount());
			//opreaciones: promedio, min, max
			//cr.setProjection(Projections.avg("precio"));
			//cr.setProjection(Projections.min("precio"));
			//cr.setProjection(Projections.max("precio"));
			cr.setProjection(Projections.sum("precio"));
			
			Object resultado= cr.uniqueResult();
			System.out.println("Resultado= "+resultado);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		//caseCriteriaProducto();
		caseCriteriaUniqueResultProducto();
	}
	
}
