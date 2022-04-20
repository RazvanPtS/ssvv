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

import static org.junit.Assert.*;

public class IncrementalIntegrationTest {

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
    public void test_addStudent_success(){
        try{
            assertNull(studentXMLRepository.findOne("123"));
            Student resp = studentXMLRepository.save(new Student("789", "Student New", 931));
            assertNotNull(resp);
        }catch (AssertionError err){
            err.printStackTrace();
            fail();
        }
        finally {
            studentXMLRepository.delete("789");
        }
    }

    @Test
    public void test_addAssignment_success(){
        try{
            Student student = studentXMLRepository.save(new Student("789", "Student new", 931));
            Tema tema = temaXMLRepository.save(new Tema("789", "Tema descriere", 10, 7));
            assertNotNull(student);
            assertNotNull(tema);
        }catch (AssertionError er){
            fail();
        }finally {
            temaXMLRepository.delete("789");
            studentXMLRepository.delete("789");
        }
    }

    @Test
    public void test_addGrade_success(){
        try {
            Student student = studentXMLRepository.save(new Student("789", "Student new", 931));
            Tema tema = temaXMLRepository.save(new Tema("789", "Tema descriere", 10, 7));
            Nota nota = notaXMLRepository.save(
                    new Nota(new Pair<String, String>(student.getID(), tema.getID()), 10, 8, "gg"));
            assertNotNull(student);
            assertNotNull(tema);
            assertNotNull(nota);
            assertNotNull(notaXMLRepository.findOne(new Pair<>(student.getID(), tema.getID())));
        }catch (AssertionError er){
            fail();
        }finally {
            notaXMLRepository.delete(new Pair<>("789","789"));
            temaXMLRepository.delete("789");
            studentXMLRepository.delete("789");
        }
    }

}
