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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class TestAddGrade {

    private static NotaXMLRepository notaXMLRepository;

    @BeforeClass
    public static void initTests(){
        NotaValidator notaValidator = new NotaValidator();
        notaXMLRepository = new NotaXMLRepository(notaValidator, "note.xml");
    }

    @Test
    public void test_addGrade_success(){
        Nota nota = null;
        try {
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
}
