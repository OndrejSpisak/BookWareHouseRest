
package sk.upjs.ics.bookwarehouse;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sk.upjs.ics.bookwarehouse.storage.BookDao;
import sk.upjs.ics.bookwarehouse.storage.TeacherDao;

@RestController
public class BookWareHouseController {
    
    private BookDao bookDao = DaoFactory.INSTANCE.getBookDao();
    private TeacherDao teacherDao = DaoFactory.INSTANCE.getTeacherDao();
    
    @RequestMapping("/books")
    public List<Book> getAllBooks(){
        return bookDao.getAll();
    }
     @RequestMapping("/books/{id}")
     public Book getBookById(@PathVariable int id){
    return bookDao.findById(id);
}
     @RequestMapping(value = "/books" , method = RequestMethod.POST)
     public void addBook (@RequestBody Book book){
         bookDao.save(book);
     }
     @RequestMapping(value = "/books/{id}" , method = RequestMethod.DELETE)
     public void deleteBook (@PathVariable long id){
         bookDao.deleteById(id);
     }
     
     @RequestMapping("/teachers")
     public List<Teacher> getAllTeachers(){
         return teacherDao.getAll();
     }
     
     @RequestMapping("/teachers/id/{id}")
     public Teacher getTeacherByID(@PathVariable Long id){
         return teacherDao.findById(id);
     }
     
     // https://stackoverflow.com/questions/16332092/spring-mvc-pathvariable-with-dot-is-getting-truncated
     @RequestMapping("/teachers/email/{email:.+}")
     public Teacher getTeacherByEmail(@PathVariable String email){
         return teacherDao.findByEmail(email);
     }
     @RequestMapping(value = "/teachers/" , method = RequestMethod.POST)
     public void addTeacher (@RequestBody Teacher teacher){
         teacherDao.save(teacher);
     }
     
     @RequestMapping(value = "/teachers/{id}" , method = RequestMethod.DELETE)
     public void deleteTeacher (@PathVariable long id){
         teacherDao.deleteById(id);
     }
}
