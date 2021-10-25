package com.korbiak.mentorship.io.task4;

import com.korbiak.mentorship.io.task4.model.Tutor;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.*;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.List;

import static org.dbunit.Assertion.assertEqualsIgnoreCols;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TutorRepoTest extends DataSourceBasedDBTestCase {

    private static TutorRepo repo;

    @Override
    protected IDataSet getDataSet() throws DataSetException {
        return new FlatXmlDataSetBuilder().build(ClassLoader.getSystemResourceAsStream("io/task4/data.xml"));
    }

    @Override
    protected DatabaseOperation getSetUpOperation() {
        return DatabaseOperation.INSERT;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() {
        return DatabaseOperation.DELETE_ALL;
    }

    @Override
    protected DataSource getDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL("jdbc:postgresql://localhost:5432/mentorship_db");
        dataSource.setUser(System.getenv("user"));
        dataSource.setPassword(System.getenv("password"));
        return dataSource;
    }

    @BeforeAll
    public static void setUpRepo() {
        repo = new TutorRepo(System.getenv("user"),
                System.getenv("password"));
    }

    @Test
    @Order(1)
    void getAllTutorsTest() throws Exception {
        List<Tutor> allTutors = repo.getAllTutors();
        IDataSet actualData = getConnection().createDataSet();
        ITable actualTable = actualData.getTable("tutor");
        Assertions.assertEquals(actualTable.getRowCount(), allTutors.size());
    }

    @Test
    @Order(2)
    void getTutorByIdTest() throws Exception {
        int id = 1;
        Tutor tutorById = repo.getTutorById(id);
        IDataSet actualData = getConnection().createDataSet();
        ITable actualTable = actualData.getTable("tutor");

        Assertions.assertEquals(actualTable.getValue(0, "id"), tutorById.getId());
        Assertions.assertEquals(actualTable.getValue(0, "name"), tutorById.getName());
        Assertions.assertEquals(actualTable.getValue(0, "surname"), tutorById.getSurname());
    }

    @Test
    @Order(3)
    void insertTutorTest() throws Exception {
        Tutor testTutor = new Tutor(7, "name", "surname");
        boolean resultOfInsertion = repo.insertTutor(testTutor);

        IDataSet actualData = getConnection().createDataSet();
        ITable actualTable = actualData.getTable("tutor");

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(ClassLoader.getSystemResourceAsStream("io/task4/data-insert.xml"));
        ITable expectedTable = expectedData.getTable("tutor");

        String[] excludedColumns = {"id"};
        Assertions.assertTrue(resultOfInsertion);
        assertEqualsIgnoreCols(actualTable, expectedTable, excludedColumns);
    }

    @Test
    @Order(4)
    void updateTutorTest() throws Exception {
        Tutor testTutor = new Tutor(7, "name1", "surname1");
        int result = repo.updateTutor(testTutor);

        IDataSet actualData = getConnection().createDataSet();
        ITable actualTable = actualData.getTable("tutor");

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(ClassLoader.getSystemResourceAsStream("io/task4/data-update.xml"));
        ITable expectedTable = expectedData.getTable("tutor");

        String[] excludedColumns = {"id"};
        Assertions.assertEquals(1, result);
        assertEqualsIgnoreCols(actualTable, expectedTable, excludedColumns);
    }

    @Test
    @Order(5)
    void deleteTutorTest() throws Exception {
        int testId = 7;
        boolean result = repo.deleteTutor(testId);

        IDataSet actualData = getConnection().createDataSet();
        ITable actualTable = actualData.getTable("tutor");

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(ClassLoader.getSystemResourceAsStream("io/task4/data.xml"));
        ITable expectedTable = expectedData.getTable("tutor");

        String[] excludedColumns = {"id"};
        Assertions.assertTrue(result);
        assertEqualsIgnoreCols(actualTable, expectedTable, excludedColumns);
    }
}
