package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Pet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class PetRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void persist(Pet pet){
        entityManager.persist(pet); // pet object is persisted and has id
    }

    public Pet find(Long id){
        return entityManager.find(Pet.class, id);
    }


    public Pet merge(Pet pet){
        Pet managedPet = entityManager.merge(pet);
        return managedPet;
    }

    public void delete(Long id){
        Pet petToDelete = entityManager.find(Pet.class, id);
        entityManager.remove(petToDelete);
    }

    public List<Pet> findAllPets(){
        return entityManager.createQuery("from Pet").getResultList();
    }

    public List<Pet> findPetByOwnerId(Long ownerId){
        return entityManager.createQuery("SELECT p FROM Pet p WHERE p.customer.id = :ownerId", Pet.class)
                .setParameter("ownerId", ownerId)
                .getResultList();
    }

}
