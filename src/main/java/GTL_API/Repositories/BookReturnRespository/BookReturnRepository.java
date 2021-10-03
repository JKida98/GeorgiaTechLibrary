package GTL_API.Repositories.BookReturnRespository;

import GTL_API.Exceptions.CreationException;
import GTL_API.Exceptions.UnknownException;
import GTL_API.Models.Entities.BookReturnEntity;
import GTL_API.Models.ReturnModels.BookReturnReturn;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

@Component
public class BookReturnRepository implements IBookReturnRepositoryCustom {

    private IBookReturnRepository iBookReturnRepository;

    private ModelMapper modelMapper;

    @Autowired
    public void setIBookReturnRepository(IBookReturnRepository iBookReturnRepository) {
        this.iBookReturnRepository = iBookReturnRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookReturnReturn createBookReturn(BookReturnEntity bookReturnEntity) {
        try{
            BookReturnEntity saved = iBookReturnRepository.save(bookReturnEntity);
            return modelMapper.map(saved, BookReturnReturn.class);
        }catch (Exception e){
            throw new CreationException("There was an error while creating a book returning record");
        }
    }
    @Override
    public BookReturnReturn findReturningBook(int id) {
        try{
            Optional<BookReturnEntity> found = iBookReturnRepository.findByIdIsAndStatusIsFalse(id);
            if(found.isPresent()){
                return modelMapper.map(found.get(), BookReturnReturn.class);
            }else{
                return null;
            }
        }catch (Exception e){
            throw new UnknownException(String.format(
                    "There was an error while finding a book returning record with ID: %d", id));
        }
    }

    @Override
    public boolean returnBookAndChangeStatus(int id) {
        try{
            BookReturnEntity updated = addPaymentAndReturnDate(id);
            if(updated.getStatus() && updated.getReturnedDate() != null){
                return true;
            }
            return true;
        }catch (Exception e){
            throw new UnknownException("There was an exception while calling stored procedure of returning book and" +
                    "changing status." + e.getMessage());
        }

    }

    private BookReturnEntity addPaymentAndReturnDate(int id) {
        BookReturnEntity bookReturnEntity = iBookReturnRepository.findByIdIs(id);
        bookReturnEntity.setStatus(true);
        Date estimatedReturnDate = bookReturnEntity.getEstimatedReturnDate();
        Calendar c = Calendar.getInstance();
        Date returnedDate = new Date(c.getTime().getTime());
        bookReturnEntity.setReturnedDate(returnedDate);
        double payment = calculatePayment(estimatedReturnDate, returnedDate);
        bookReturnEntity.setPayment(payment);
        return iBookReturnRepository.save(bookReturnEntity);
    }


    private double calculatePayment(Date estimated, Date returned){
        long diff = returned.getTime() - estimated.getTime();
        long diffDays = diff/(24*60*60*1000);
        double payment = 0D;
        if(diffDays >= 1){
            payment = (double)diffDays * 0.75;
        }
        return payment;
    }
}
