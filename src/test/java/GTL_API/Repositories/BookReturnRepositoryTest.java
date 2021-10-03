package GTL_API.Repositories;

import GTL_API.Exceptions.UnknownException;
import GTL_API.MainApplicationClass;
import GTL_API.Models.Entities.BookReturnEntity;
import GTL_API.Repositories.BookReturnRespository.IBookReturnRepository;
import GTL_API.Repositories.BookReturnRespository.IBookReturnRepositoryCustom;
import GTL_API.TestDataSourceConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MainApplicationClass.class, TestDataSourceConfig.class})
public class BookReturnRepositoryTest {

    @Autowired
    private IBookReturnRepositoryCustom iBookReturnRepositoryCustom;

    @Autowired
    private IBookReturnRepository iBookReturnRepository;




    private int createBookReturnRecord(){
        BookReturnEntity br = new BookReturnEntity();

        Calendar c = Calendar.getInstance();
        Date estimatedDate = new Date(c.getTime().getTime());
        br.setEstimatedReturnDate(estimatedDate);
        br.setStatus(false);
        br.setPayment(0D);

        BookReturnEntity result = iBookReturnRepository.save(br);
        if(result!=null){
            return result.getId();
        }
        else{
            return 0;
        }
    }

    private int createBookReturnRecordLate(){
        BookReturnEntity br = new BookReturnEntity();

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -10);
        Date estimatedDate = new Date(c.getTime().getTime());

        br.setEstimatedReturnDate(estimatedDate);
        br.setStatus(false);
        br.setPayment(0D);

        BookReturnEntity result = iBookReturnRepository.save(br);
        if(result!=null){
            return result.getId();
        }
        else{
            return 0;
        }
    }

    @Test
    public void testReturningBookWithValidData_ShouldReturnTrue(){
        int insertedId = createBookReturnRecord();
        if(insertedId!=0){
            boolean result = iBookReturnRepositoryCustom.returnBookAndChangeStatus(insertedId);
            Assert.assertTrue(result);
        }else{
            Assert.fail();
        }
        iBookReturnRepository.deleteById(insertedId);
    }


    @Test
    public void testReturningBookWithValidData_Payment(){
        int insertedId = createBookReturnRecordLate();
        if(insertedId!=0){
            boolean result = iBookReturnRepositoryCustom.returnBookAndChangeStatus(insertedId);
            String payment=  iBookReturnRepository.findByIdIs(insertedId).getPayment().toString();
            Assert.assertEquals("7.5", payment);
        }else{
            Assert.fail();
        }
        iBookReturnRepository.deleteById(insertedId);
    }




    @Test
    public void testReturningBookWithInvalidData_ShouldThrowException(){
            try{
                boolean result = iBookReturnRepositoryCustom.returnBookAndChangeStatus(-1);
                Assert.fail();
            } catch (UnknownException ex){
                Assert.assertTrue(true);
            }
    }
}
