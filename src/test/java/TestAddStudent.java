
import domain.Student;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import static org.junit.Assert.*;

public class TestAddStudent {

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
            assertNull(resp);
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
            assertNull(resp); // should return object?
        }catch (AssertionError err){
            err.printStackTrace();
            fail();
        }
    }

}
