package at.htl.entity;

import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.postgresql.ds.PGSimpleDataSource;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.transaction.*;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonTest {

    @Inject
    EntityManager em;

    @Inject
    UserTransaction tm;

    static final String DATABASE = "db";
    static final String USERNAME = "app";
    static final String PASSWORD = "app";
    public static final String URL = "jdbc:postgresql://localhost:5432/db";

    /**
     * JDBC-DataSource
     * @return DataSource
     */
    public static DataSource getDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName(DATABASE);
        dataSource.setUser(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    /**
     * Transaktion ... kleinste unteilbare Aktion
     * zB Überweisung in einer Bank
     *    - von Konto A wird abgebucht
     *    - auf Konto B wird aufgebucht
     * Logical Unit of Work (LUW)
     */
    @Test
    @Order(10)
    void createPerson() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Person susi = new Person("susi");
        tm.begin();
        em.persist(susi);
        tm.commit();
        Table personTable = new Table(getDataSource(), "person");
        output(personTable).toConsole();
        assertThat(personTable).hasNumberOfRows(1);
    }

    @Test
    @Order(20)
    @Transactional
    void updatePersonWithoutMerge() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Person susi = new Person("susi");
        em.persist(susi);
        susi.setName("Suzie Quattro");
//        Table personTable = new Table(getDataSource(), "person");
//        output(personTable).toConsole();
//        assertThat(personTable).hasNumberOfRows(1);
        fail("not yet implemented");
    }
}
