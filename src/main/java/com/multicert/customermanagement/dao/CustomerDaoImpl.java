package com.multicert.customermanagement.dao;

import com.multicert.customermanagement.model.Customer;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long save(Customer customer) {
        sessionFactory.getCurrentSession().saveOrUpdate(customer);
        return customer.getId();
    }

    @Override
    public Customer get(long id) {
        Customer customer = sessionFactory.getCurrentSession().get(Customer.class, id);
        Hibernate.initialize(customer.getAddresses());
        return customer;
    }

    @Override
    public Customer getByNif(int nif) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT c FROM Customer c JOIN FETCH c.addresses WHERE c.nif=:nif", Customer.class);
        query.setParameter("nif", nif);

        Optional<Customer> customer = query.getResultStream().findFirst();
        return customer.get();
    }

    @Override
    public Set<Customer> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return new HashSet<>(session
                .createQuery("SELECT c FROM Customer c JOIN FETCH c.addresses", Customer.class)
                .getResultList());
    }

    @Override
    public Set<Customer> getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT c FROM Customer c JOIN FETCH c.addresses WHERE c.name like :name", Customer.class);
        query.setParameter("name", "%"+name+"%");
        return new HashSet<Customer>(query.getResultList());
    }

    @Override
    public Set<Customer> getByCity(String city) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT c FROM Customer c JOIN FETCH c.addresses child WHERE child.city=:city", Customer.class);
        query.setParameter("city", city);
        return new HashSet<Customer>(query.getResultList());
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.byId(Customer.class).load(id);
        session.delete(customer);
        // this makes the pending delete to be done.
        session.flush();;
    }
}
