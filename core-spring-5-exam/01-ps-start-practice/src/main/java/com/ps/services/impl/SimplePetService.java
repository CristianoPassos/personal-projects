package com.ps.services.impl;

import com.ps.base.PetType;
import com.ps.ents.Pet;
import com.ps.ents.User;
import com.ps.repos.PetRepo;
import com.ps.services.PetService;

/**
 * Created by iuliana.cosmina on 2/23/16.
 */
public class SimplePetService extends SimpleAbstractService<Pet> implements PetService {

    private PetRepo repo;

    @Override
    public Pet createPet(User user, String name, int age, PetType petType, String rfid) {
        Pet pet = new Pet();
        pet.setOwner(user);
        pet.setName(name);
        pet.setAge(age);
        pet.setRfid(rfid);
        user.addPet(pet);
        repo.save(pet);
        return pet;
    }

    public PetRepo getRepo() {
        return repo;
    }

    //                setters & getters
    public void setRepo(PetRepo petRepo) {
        this.repo = petRepo;
    }
}
