package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Pet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CustomerRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void persist(Customer customer){
        entityManager.persist(customer); // customer object is persisted and has id
    }

    public Customer find(Long id){
        Customer customer = entityManager.find(Customer.class, id);
        return customer;
    }


    public Customer merge(Customer customer){
        Customer managedCustomer = entityManager.merge(customer);
        return managedCustomer;
    }

    public void delete(Long id){
        Customer customerToDelete = entityManager.find(Customer.class, id);
        entityManager.remove(customerToDelete);
    }

    public List<Customer> findAll(){
        return entityManager.createQuery("from Customer").getResultList();
    }

    public Customer findCustomerByPet(Pet pet){
        return entityManager.createQuery("SELECT c FROM Customer c WHERE :pet member of c.pets", Customer.class)
                .setParameter("pet", pet)
                .getSingleResult();
    }

}
