import BookApp.App;
import BookApp.AppModel;
import Fligths.Flight;
import Threads.BookThread;
import User.User;
import Validations.WrongPinExeption;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TesterClass {
    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool();

        try {
        BookThread book1 = new BookThread(
                new User("zinedin","zidane","32564978512"),
                AppModel.getFlights().get(1)
        );

        BookThread book2 = new BookThread(
                new User("rose","mito","36598459856"),
                AppModel.getFlights().get(0)
        );

            Future<User> book1Result = exec.submit(book1);
            Future<User> book2Result = exec.submit(book2);

            App.printUserTickects(book1Result.get());
            App.printUserTickects(book2Result.get());

        }catch (Exception e){
            System.out.print("\nsomething wrong happend in threads");
        }finally {
            exec.shutdown();
        }

    }
}
