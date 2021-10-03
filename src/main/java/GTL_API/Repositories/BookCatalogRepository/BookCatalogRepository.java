package GTL_API.Repositories.BookCatalogRepository;

import GTL_API.Exceptions.NotFoundException;
import GTL_API.Exceptions.UnknownException;
import GTL_API.Handlers.Patcher.PatcherHandler;
import GTL_API.Models.Entities.BookCatalogEntity;
import GTL_API.Models.ReturnModels.BookCatalogReturn;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class BookCatalogRepository implements IBookCatalogRepositoryCustom {

    private IBookCatalogRepository iBookCatalogRepository;

    private ModelMapper modelMapper;

    private PatcherHandler patcherHandler;

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setIBookCatalogRepository(IBookCatalogRepository iBookCatalogRepository) {
        this.iBookCatalogRepository = iBookCatalogRepository;
    }

    @Override
    public List<BookCatalogReturn> getBooksCatalog() {
        try{
            Optional<List<BookCatalogEntity>> found = iBookCatalogRepository.findAllByDeletedIsFalse();
            if(found.isPresent()){
                Type listType = new TypeToken<List<BookCatalogReturn>>() {
                }.getType();
                return modelMapper.map(found.get(), listType);
            }else{
                throw new NotFoundException("Book catalog records was not found");
            }
        } catch (Exception e){
            throw new UnknownException("There was unknown error while listing books catalog");
        }
    }

    @Override
    public BookCatalogReturn getBookCatalog(int id) {
        try{
            Optional<BookCatalogEntity> found = iBookCatalogRepository.findByIdIs(id);
            if(found.isPresent()){
                return modelMapper.map(found.get(), BookCatalogReturn.class);
            }else{
                throw new NotFoundException(String.format("Book catalog with ID: %d, was not found", id));
            }
        }catch (Exception e){
            throw new UnknownException(String.format("There was an error while finding a book catalog with ID: %d", id));
        }
    }

    @Override
    public BookCatalogReturn addBookCatalog(BookCatalogEntity bookCatalogEntity) {
        try{
            bookCatalogEntity.setDeleted(false);
            BookCatalogEntity created = iBookCatalogRepository.save(bookCatalogEntity);
            return modelMapper.map(created, BookCatalogReturn.class);
        }catch (Exception e){
            throw new UnknownException(String.format("There was an error while adding book catalog for book" +
                    "with ISBN: %s", bookCatalogEntity.getIsbn()));
        }
    }

    @Override
    public BookCatalogReturn updateBookCatalog(BookCatalogEntity bookCatalogEntity) {
        try{
            Optional<BookCatalogEntity> foundOptional = iBookCatalogRepository.findByIdIs(bookCatalogEntity.getId());
            if(foundOptional.isPresent()){
                BookCatalogEntity found = foundOptional.get();
                patcherHandler.fillNotNullFields(found, bookCatalogEntity);
                BookCatalogEntity updated = iBookCatalogRepository.save(found);
                return modelMapper.map(updated, BookCatalogReturn.class);
            }else{
                throw new NotFoundException(String.format("Book catalog with ID: %d was not found", bookCatalogEntity.getId()));
            }
        }catch (Exception e){
            throw new UnknownException(String.format("There was an error while updating book catalog for book" +
                    "with ISBN: %s", bookCatalogEntity.getIsbn()));
        }
    }

    @Override
    public boolean removeBookCatalog(int id) {
        try{
            Optional<BookCatalogEntity> foundOptional = iBookCatalogRepository.findByIdIs(id);
            if(foundOptional.isPresent()){
                BookCatalogEntity toDelete = foundOptional.get();
                toDelete.setDeleted(true);
                BookCatalogEntity deleted = iBookCatalogRepository.save(toDelete);
                return deleted.getDeleted();
            }else{
                throw new NotFoundException("The book catalog was not found");
            }
        }catch (Exception e){
            throw new UnknownException(String.format("There was an error while deleting book catalog for book" +
                    "with ID: %d", id));
        }
    }


}
