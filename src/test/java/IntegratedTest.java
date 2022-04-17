import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import static org.junit.Assert.*;

public class IntegratedTest {

    private static NotaXMLRepository notaXMLRepository;
    private static StudentXMLRepository studentXMLRepository;
    private static TemaXMLRepository temaXMLRepository;

    @BeforeClass
    public static void initTests(){
        NotaValidator notaValidator = new NotaValidator();
        TemaValidator temaValidator = new TemaValidator();
        StudentValidator studentValidator = new StudentValidator();
        studentXMLRepository = new StudentXMLRepository(studentValidator, "studenti.xml");
        temaXMLRepository = new TemaXMLRepository(temaValidator, "teme.xml");
        notaXMLRepository = new NotaXMLRepository(notaValidator, "note.xml");
    }

    @Test
    public void addStudent_success_test(){
        try{
            assertNull(studentXMLRepository.findOne("123"));
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
    public void test_addAssignment_success(){
        Tema tema = new Tema("456", "Tema descrioere", 8, 6);
        try{
            assertNull(temaXMLRepository.findOne("456"));
            temaXMLRepository.save(tema);
        }catch (ValidationException exc){
            exc.printStackTrace();
            fail();
        }finally {
            temaXMLRepository.delete("456");
        }
    }

    @Test
    public void test_addGrade_success(){
        Nota nota = null;
        try {
            assertNull(notaXMLRepository.findOne(new Pair<>("123", "456")));
            nota = notaXMLRepository.save(
                    new Nota(new Pair<String, String>("123", "456"), 9, 7, "gg"));
            assertNotNull(nota);
            assertNotNull(notaXMLRepository.findOne(new Pair<>("123", "456")));
        }catch (AssertionError er){
            fail();
        }finally {
            notaXMLRepository.delete(nota.getID());
        }
    }

    @Test
    public void test_addFlow_success(){
        Student student = null;
        Tema tema = null;
        Nota nota = null;
        try {
            student = studentXMLRepository.save(new Student("123", "Student", 933));
            tema = temaXMLRepository.save(new Tema("456", "Tema descriere", 8, 6));
            nota = notaXMLRepository.save(
                    new Nota(new Pair<String, String>(student.getID(), tema.getID()), 9, 7, "gg"));
            assertNotNull(student);
            assertNotNull(tema);
            assertNotNull(nota);
            assertNotNull(notaXMLRepository.findOne(new Pair<>(student.getID(), tema.getID())));
        }catch (AssertionError er){
            fail();
        }finally {
            notaXMLRepository.delete(nota.getID());
            temaXMLRepository.delete(tema.getID());
            studentXMLRepository.delete(student.getID());
        }
    }

}
