package ars.cs.miu.edu.services;


import ars.cs.miu.edu.models.Person;
import ars.cs.miu.edu.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl  implements AirlinesService<Person> {
    @Autowired
    private PersonRepository personsRepository;

    @Override
    public List<Person> findAll() {
        return personsRepository.findAll();
    }

    @Override
    public Person findOne(Long i) {
        return personsRepository.findById(i).orElse(null);
    }

    @Override
    public Person update(Person t) {
        return  personsRepository.save(t);
    }

    @Override
    public void delete(Long i) {

        personsRepository.deleteById(i);
    }

    @Override
    public Person add(Person t) {
        return  personsRepository.save(t);
    }
}
