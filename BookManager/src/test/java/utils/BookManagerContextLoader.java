package utils;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bookmanager.app.BookManagerApp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookManagerApp.class)
public abstract class BookManagerContextLoader {

}
