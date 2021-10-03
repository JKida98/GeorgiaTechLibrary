package GTL_API.Repositories.BookBorrowRepository;

import GTL_API.Exceptions.CreationException;
import GTL_API.Exceptions.NotFoundException;
import GTL_API.Exceptions.UnknownException;
import GTL_API.Models.Entities.BookBorrowEntity;
import GTL_API.Models.ReturnModels.BookBorrowReturn;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class BookBorrowRepository implements IBookBorrowRepositoryCustom {

    private IBookBorrowRepository iBookBorrowRepository;

    private ModelMapper modelMapper;

    @Autowired
    public void setIBookBorrowRepository(IBookBorrowRepository iBookBorrowRepository) {
        this.iBookBorrowRepository = iBookBorrowRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookBorrowReturn createBookBorrow(@NotNull BookBorrowEntity bookBorrowEntity, int bookReturnId) {
        try {
            bookBorrowEntity.setBookReturnId(bookReturnId);
            bookBorrowEntity.setBorrowDate(new Date(Calendar.getInstance().getTime().getTime()));
            BookBorrowEntity savedBookBorrow = iBookBorrowRepository.save(bookBorrowEntity);
            return modelMapper.map(savedBookBorrow, BookBorrowReturn.class);
        }catch(NullPointerException nullPointerException){
            throw new NullPointerException("Object is null");
        }catch (DataIntegrityViolationException dataIntegrityViolationException){
            throw new CreationException("Missing fields to add records to the database");
        }catch (Exception e){
            throw new UnknownException("There was unknown error while creating book borrow.");
        }
    }

    @Override
    public List<Integer> findBorrowedBook(int bookCatalogId, String ssn) {
        List<Integer> ids = new ArrayList<>();
        List<BookBorrowEntity> foundLoans = iBookBorrowRepository.findAllByBookCatalogIdIsAndSsnIs(bookCatalogId, ssn);
        if(foundLoans.size() == 0){
            throw new NotFoundException(String.format("No loans with book catalog: %d and SSN: %s was found.", bookCatalogId, ssn));
        }
        for(BookBorrowEntity loan: foundLoans){
            ids.add(loan.getBookReturnId());
        }
        return ids;
    }
}
