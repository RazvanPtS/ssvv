import domain.Tema;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.TemaXMLRepository;
import validation.TemaValidator;
import validation.ValidationException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class TestAddAssignment {

    private static TemaXMLRepository temaXMLRepository;

    @BeforeClass
    public static void setUp(){
        TemaValidator temaValidator = new TemaValidator();
        temaXMLRepository = new TemaXMLRepository(temaValidator, "teme.xml");
    }

    @Test
    public void test_addAssignment_success(){
        Tema tema = new Tema("456", "Tema descrioere", 8, 6);
        try{
            temaXMLRepository.save(tema);
        }catch (ValidationException exc){
            exc.printStackTrace();
            fail();
        }finally {
            temaXMLRepository.delete("456");
        }
    }

    @Test
    public void test_addAssignment_invalid(){
        Tema tema = new Tema("456","Tema descriere", 7, 8);
        try{
            Tema ret = temaXMLRepository.save(tema);
            assertNull(ret);
        }catch (Exception exc){
            fail();
        }finally {
            temaXMLRepository.delete("456");
        }
    }

}
