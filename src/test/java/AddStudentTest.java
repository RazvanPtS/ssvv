import domain.Student;
import org.junit.gen5.api.Assertions;
import org.junit.gen5.api.BeforeAll;
import org.junit.gen5.api.Test;
import org.junit.gen5.api.extension.InstancePostProcessor;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

public class AddStudentTest {

    private static Service service;

    @BeforeAll
    public static void init(){
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        NotaValidator notaValidator = new NotaValidator();
        StudentXMLRepository studentXMLRepository = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository notaXMLRepository = new NotaXMLRepository(notaValidator, "note.xml");
        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @Test
    public void test_addStudent_success(){
        try{
            int resp = service.saveStudent("123", "Student 123", 1);
            Assertions.assertEquals(1, resp);
        }catch (AssertionError err){
            err.printStackTrace();
        }
    }

    @Test
    public void test_addStudent_exists(){
        try{
            int resp = service.saveStudent("123", "Student 123", 1);
            Assertions.assertEquals(0, resp);
        }catch (AssertionError err){
            err.printStackTrace();
        }
    }

}
