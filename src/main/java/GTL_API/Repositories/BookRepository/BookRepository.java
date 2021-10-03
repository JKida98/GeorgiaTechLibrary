package GTL_API.Repositories.BookRepository;

import GTL_API.Exceptions.DuplicateException;
import GTL_API.Exceptions.NotFoundException;
import GTL_API.Exceptions.UnknownException;
import GTL_API.Handlers.Patcher.PatcherHandler;
import GTL_API.Models.Entities.BookEntity;
import GTL_API.Models.ReturnModels.BookReturn;
import GTL_API.Models.ReturnModels.PersonReturn;
import GTL_API.Models.UpdateModels.BookUpdate;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class BookRepository implements IBookRepositoryCustom {

    private IBookRepository iBookRepository;

    private ModelMapper modelMapper;

    private PatcherHandler patcherHandler;

    @PersistenceContext
    private EntityManager entityManager;



    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setIBookRepository(IBookRepository iBookRepository) {
        this.iBookRepository = iBookRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookReturn> getAllBooks() {
        Optional<List<BookEntity>> foundList =  iBookRepository.findAllByIsbnIsNotNull();
        if(foundList.isPresent()){
            Type listType = new TypeToken<List<BookReturn>>() {
            }.getType();
            return modelMapper.map(foundList.get(), listType);
        }else{
            throw new NotFoundException("List of books was not found.");
        }
    }

    @Override
    public BookReturn addBook(BookEntity bookEntity) {
        try{
            bookEntity.setAvailableBooksNumber(bookEntity.getCopiesNumber());
            BookEntity added = iBookRepository.save(bookEntity);
            return modelMapper.map(added, BookReturn.class);
        }catch (Exception e){
            throw new UnknownException(String.format("There was an unexpected error while adding book with ISBN: %s.", bookEntity.getIsbn()));
        }
    }


    @Override
    public BookReturn updateBook(BookEntity bookEntity) {
        try {
            Optional<BookEntity> foundOptional = iBookRepository.findByIsbnIs(bookEntity.getIsbn());
            if (foundOptional.isPresent()) {
                BookEntity found = foundOptional.get();
                patcherHandler.fillNotNullFields(found, bookEntity);
                if (checkIfExists(found.getIsbn()) > 1) {
                    throw new DuplicateException(String.format("There is already book with ISBN: %s", found.getIsbn()));
                }
                BookEntity updated = iBookRepository.save(found);
                return modelMapper.map(updated, BookReturn.class);
            } else {
                throw new NotFoundException(String.format("Book with ISBN: %s, was not found.", bookEntity.getIsbn()));
            }
        }catch (NotFoundException notFoundException){
            throw notFoundException;
        }catch (Exception e){
            throw new UnknownException(String.format("There was an unexpected error while updating book with ISBN: %s.", bookEntity.getIsbn()));
        }
    }

    @Override
    public BookReturn findBook(String isbn) {
        try {
            Optional<BookEntity> found = iBookRepository.findByIsbnIs(isbn);
            if (found.isPresent()) {
                BookEntity toReturn = found.get();
                return modelMapper.map(toReturn, BookReturn.class);
            } else {
                throw new NotFoundException(String.format("Book with ISBN %s was not found", isbn));
            }
        }catch (NotFoundException notFoundException){
            throw notFoundException;
        }catch (Exception e){
            throw new UnknownException(String.format("There was unknown exception while finding book with ISBN %s", isbn));
        }
    }

    @Override
    public List<BookReturn> findBooksByTitle(String title) {
        try {
            Optional<List<BookEntity>> found = iBookRepository.findAllByTitleIs(title);
            if (found.isPresent()) {
                List<BookEntity> toReturn = found.get();
                Type listType = new TypeToken<List<BookReturn>>() {
                }.getType();
                return modelMapper.map(toReturn, listType);
            } else {
                throw new NotFoundException(String.format("Books with title %s was not found", title));
            }
        }catch(NotFoundException notFoundException){
            throw notFoundException;
        }catch (Exception e){
            throw new UnknownException(String.format("There was unknown exception while finding books with title %s", title));
        }
    }

    @Override
    public List<BookReturn> findBooksByAuthor(String author) {
        try {
            Optional<List<BookEntity>> found = iBookRepository.findAllByAuthorIs(author);
            if (found.isPresent()) {
                List<BookEntity> toReturn = found.get();
                Type listType = new TypeToken<List<BookReturn>>() {
                }.getType();
                return modelMapper.map(toReturn, listType);
            } else {
                throw new NotFoundException(String.format("Books with author %s was not found", author));
            }
        }catch (NotFoundException notFoundException){
            throw notFoundException;
        }catch (Exception e){
            throw new UnknownException(String.format("There was unknown exception while finding books with author %s", author));
        }
    }

    @Override
    @Transactional
    public boolean borrowingBookDecrease(String isbn) {
        try{
            BookEntity bookEntity = modelMapper.map(findBook(isbn), BookEntity.class);
            bookEntity.setAvailableBooksNumber(bookEntity.getAvailableBooksNumber() - 1);
            updateBook(bookEntity);
            return true;
        }catch (Exception e){
            throw new UnknownException("There was unexpected error while decreasing number of available numbers of books");
        }
    }

    @Override
    @Transactional
    public boolean returningBookIncrease(String isbn) {
        try{
            BookEntity bookEntity = modelMapper.map(findBook(isbn), BookEntity.class);
            bookEntity.setAvailableBooksNumber(bookEntity.getAvailableBooksNumber() + 1);
            updateBook(bookEntity);
            return true;
        }catch (Exception e){
            throw new UnknownException("There was unexpected error while increasing number of available numbers of books");
        }
    }

    @Override
    public List<BookReturn> findSpecificUsersBookToReturn(Integer cardNumber) {
        Type listType = new TypeToken<List<BookReturn>>() {}.getType();
        StoredProcedureQuery findSpecificUsersBookToReturn =
                entityManager.createNamedStoredProcedureQuery("findPersonBooksToReturn").setParameter("@cardNumber", cardNumber);
        return modelMapper.map(findSpecificUsersBookToReturn.getResultList(), listType);
    }

    private int checkIfExists(String isbn){
        return iBookRepository.countAllByIsbnIs(isbn);
    }





}
