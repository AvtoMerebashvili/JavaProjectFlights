package Threads;

import BookApp.App;
import BookApp.BookStatus;
import Fligths.Flight;
import User.User;

import java.util.concurrent.Callable;

public class BookThread implements Callable {
    final String bookedWell = "\nCongratulations! Flight is booked!\n";
    final String bookeDoesnotExist = "\nFailed! Flight does not exist!\n";

    private User user;
    private Flight flight;
    public BookThread(User user, Flight flight){
        this.user = user;
        this.flight = flight;
    }

    @Override
    public User call() throws Exception {
        App applicaition = new App();
        BookStatus status = applicaition.bookTicket(this.user,this.flight);

        switch (status){
            case okay:
                System.out.print(this.bookedWell);
                break;
            case failed:
                System.out.print(this.bookeDoesnotExist);
                break;
        }
        return this.user;
    }
}
