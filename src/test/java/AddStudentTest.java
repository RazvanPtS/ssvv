
import domain.Student;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.StudentXMLRepository;
import validation.StudentValidator;

import static org.junit.Assert.*;

public class AddStudentTest {

    private static StudentXMLRepository studentXMLRepository;

    @BeforeClass
    public static void init(){
        StudentValidator studentValidator = new StudentValidator();
        studentXMLRepository = new StudentXMLRepository(studentValidator, "studenti.xml");
    }

    @Test
    public void test_addStudent_success(){
        try{
            Student resp = studentXMLRepository.save(new Student("123", "Student", 933));
            assertNotNull(resp);
        }catch (AssertionError err){
            err.printStackTrace();
            fail();
        }
        finally {
            studentXMLRepository.delete("123");
        }
    }

    @Test
    public void test_addStudent_null(){
        try{
            Student resp = studentXMLRepository.save(new Student("231", "Student 123", 2));
            assertNull(resp);
        }catch (AssertionError err){
            err.printStackTrace();
            fail();
        }
    }

    @Test
    public void test_addStudent_success_TC1(){
        try{
            Student resp = studentXMLRepository.save(new Student("1", "name", 115));
            assertNotNull(resp);
            assertEquals("name", resp.getNume());
            assertEquals(115, resp.getGrupa());
        }catch (AssertionError err){
            err.printStackTrace();
            fail();
        }
        finally {
            studentXMLRepository.delete("1");
        }
    }

    @Test
    public void test_addStudent_fails_TC2(){
        try{
            Student resp = studentXMLRepository.save(new Student(null, "name", 115));
            assertNull(resp);
        }catch (AssertionError err){
            err.printStackTrace();
            fail();
        }
    }

    @Test
    public void test_addStudent_fails_TC3(){
        try{
            Student resp = studentXMLRepository.save(new Student("", "name", 115));
            assertNull(resp);
        }catch (AssertionError err){
            err.printStackTrace();
            fail();
        }
    }

    @Test
    public void test_addStudent_fails_TC4(){
        try{
            Student resp = studentXMLRepository.save(new Student("1", null, 115));
            assertNull(resp);
        }catch (AssertionError err){
            err.printStackTrace();
            fail();
        }
    }

    @Test
    public void test_addStudent_fails_TC5(){
        try{
            Student resp = studentXMLRepository.save(new Student("1", "", 115));
            assertNull(resp);
        }catch (AssertionError err){
            err.printStackTrace();
            fail();
        }
    }

    @Test
    public void test_addStudent_fails_TC6(){
        try{
            Student resp = studentXMLRepository.save(new Student("1", "name", 110));
            assertNull(resp);
        }catch (AssertionError err){
            err.printStackTrace();
            fail();
        }
    }

    @Test
    public void test_addStudent_fails_TC7(){
        try{
            Student resp = studentXMLRepository.save(new Student("1", "name", 938));
            assertNull(resp);
        }catch (AssertionError err){
            err.printStackTrace();
            fail();
        }
    }

}
