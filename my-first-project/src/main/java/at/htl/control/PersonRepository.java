package at.htl.control;

import at.htl.entity.Person;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PersonRepository {

    @Inject
    EntityManager em;

    @Inject
    Logger logger;

    @Transactional
    public void save(Person person) {
        em.persist(person);
    }

    @Transactional
    public Person update(Person person) {
        return em.merge(person);
    }

    public Person findById(long id) {
        return em.find(Person.class, id);
    }

    public List<Person> findAll() {
        TypedQuery<Person> query = em.createQuery("select p from Person p", Person.class);
        return query.getResultList();
    }


}
