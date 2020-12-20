import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    private static Library library;
    public static void main(String[]args) {
        library = new Library();
        while (true){
            System.out.println("1 - Add to Library" +
                    "\n2 - Delete from Library" +
                    "\n3 - Print all publications" +
                    "\n4 - Print all publications with word" +
                    "\n5 - Give publication to User" +
                    "\n6 - Get publication back" +
                    "\n7 - Save" +
                    "\n8 - Load" +
                    "\n0 - Exit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addPublication();
                    break;
                case "2":
                    deletePublication();
                    break;
                case "3":
                    System.out.println(library.getAllPublications());
                    break;
                case "4":
                    printPublicationsWithWord();
                    break;
                case "5":
                    givePublicationToUser();
                    break;
                case "6":
                    getPublicationFromUser();
                    break;
                case "7":
                    library.save();
                    break;
                case "8":
                    library.load();
                    break;
                case "0":
                    return;
            }
        }
    }

    private static void addPublication(){
        System.out.println("1 - Add book" +
                "\n2 - Add magazine");
        String choice2 = scanner.nextLine();

        switch (choice2) {
            case "1":
                addBook();
                break;
            case "2":
                addMagazine();
                break;
            default:
                System.out.println("Incorrect input!!!\n");
                break;
        }
    }

    private static void deletePublication(){
        System.out.println("1 - Delete book" +
                "\n2 - Delete magazine");
        String choice2 = scanner.nextLine();

        System.out.println("Enter name edition for deleting: ");
        String nameOfEdition = scanner.nextLine();
        switch (choice2) {
            case "1":
                library.deleteBook(nameOfEdition);
                break;
            case "2":
                library.deleteMagazine(nameOfEdition);
                break;
            default:
                System.out.println("Incorrect input!!!\n");
                break;
        }
    }

    private static void givePublicationToUser(){
        System.out.println("1 - Give book to user" +
                "\n2 - Give magazine to user\n");
        String choice2 = scanner.nextLine();

        switch (choice2) {
            case "1":
                giveBookToUser();
                break;
            case "2":
                giveMagazineToUser();
                break;
            default:
                System.out.println("Incorrect input!!!\n");
                break;
        }
    }

    private static void getPublicationFromUser(){
        System.out.println("1 - Return book" +
                "\n2 - Return magazine\n");
        String choice2 = scanner.nextLine();

        switch (choice2) {
            case "1":
                returnBookToLibrary();
                break;
            case "2":
                returnMagazineToLibrary();
                break;
            default:
                System.out.println("Incorrect input!!!\n");
                break;
        }
    }

    private static void printPublicationsWithWord(){
        System.out.println("Enter key word:" + System.lineSeparator());
        String keyWord = scanner.nextLine();

        System.out.println(library.getListOfPublicationsWithWord(keyWord));
    }

    private static void giveBookToUser(){
        String bookName;
        System.out.println("Enter book name:" + System.lineSeparator());
        bookName = scanner.nextLine();

        System.out.println("\nEnter Firstname User: ");
        String firstName = scanner.nextLine();
        System.out.println("\nEnter Lastname User: ");
        String lastName = scanner.nextLine();
        if(library.isUserRegistered(firstName,lastName) == -1){
            createNewUser(firstName, lastName);
        }
        library.giveBookToUser(bookName, firstName, lastName);
    }

    private static void giveMagazineToUser(){
        String magazineName;
        System.out.println("Enter magazine name:" + System.lineSeparator());
        magazineName = scanner.nextLine();

        System.out.println("\nEnter Firstname User: ");
        String firstName = scanner.nextLine();
        System.out.println("\nEnter Lastname User: ");
        String lastName = scanner.nextLine();
        if(library.isUserRegistered(firstName,lastName) == -1){
            createNewUser(firstName, lastName);
        }
        library.giveMagazineToUser(magazineName, firstName, lastName);
    }

    private static void returnBookToLibrary(){
        System.out.println("Enter book name:" + System.lineSeparator());
        String bookName = scanner.nextLine();

        System.out.println("\nEnter Firstname User: ");
        String firstName = scanner.nextLine();
        System.out.println("\nEnter Lastname User: ");
        String lastName = scanner.nextLine();

        library.returnBookToRegister(bookName, firstName, lastName);
    }

    private static void returnMagazineToLibrary(){
        System.out.println("Enter book name:" + System.lineSeparator());
        String magazineName = scanner.nextLine();

        System.out.println("\nEnter Firstname User: ");
        String firstName = scanner.nextLine();
        System.out.println("\nEnter Lastname User: ");
        String lastName = scanner.nextLine();

        library.returnMagazineToRegister(magazineName, firstName, lastName);
    }

    private static void addBook(){
        String name, publisher, yearOfPublication;
        int countOfPages, numberOfAuthors = 0;
        List<String> authors;

        //scanner.nextLine();

        System.out.print("\nEnter name: ");
        name = scanner.nextLine();

        System.out.print("\nEnter the publisher: ");
        publisher = scanner.nextLine();

        System.out.print("\nEnter year: ");
        yearOfPublication = scanner.nextLine();

        System.out.print("\nEnter count: ");
        countOfPages = getInteger();

        System.out.println("\nEnter number of Authors");
        numberOfAuthors = getInteger();


        authors = new ArrayList<>();

        while (numberOfAuthors > 0) {
            System.out.println("\nEnter Firstname Author: ");
            String firstName = scanner.nextLine();
            System.out.println("\nEnter Lastname Author: ");
            String lastName = scanner.nextLine();
            if(library.isAuthorRegistered(firstName,lastName) == -1){
                createNewAuthor(firstName, lastName);
            }
            authors.add(firstName+ " " + lastName);
            numberOfAuthors--;
        }

        library.addBookToRegister(new Book(name, authors, countOfPages, publisher, yearOfPublication));
    }

    private static void addMagazine(){
        String name, publisher, type, dateOfPublication;
        int countOfPages,countOfArticles, numberOfAuthors = 0;
        List<String> authors;

        //scanner.nextLine();

        System.out.print("Enter name: ");
        name = scanner.nextLine();

        System.out.print("Enter the publisher: ");
        publisher = scanner.nextLine();

        System.out.print("Enter date: ");
        dateOfPublication = scanner.nextLine();

        System.out.print("Enter type: ");
        type = scanner.nextLine();

        System.out.print("Enter count: ");
        countOfPages = getInteger();

        System.out.print("Enter count of articles: ");
        countOfArticles = getInteger();

        System.out.println("Enter number of Authors");
        numberOfAuthors = getInteger();

        authors = new ArrayList<>();

        while (numberOfAuthors > 0) {
            System.out.println("Enter Firstname Author: ");
            String firstName = scanner.nextLine();
            System.out.println("Enter Lastname Author: ");
            String lastName = scanner.nextLine();
            if(library.isAuthorRegistered(firstName,lastName) == -1){
                createNewAuthor(firstName, lastName);
            }
            authors.add(firstName+ " " + lastName);
            numberOfAuthors--;
        }

        library.addMagazineToRegister(new Magazine(name, authors, countOfPages, publisher, dateOfPublication, countOfArticles, type));
    }

    private static void createNewAuthor(String firstName, String lastName){
        System.out.println("New Author, enter additional info about him");
        System.out.print("Enter birthDay: ");
        String birthDay = scanner.nextLine();
        library.addAuthorToRegister(new Author(firstName, lastName, birthDay));
    }

    private static void createNewUser(String firstName, String lastName){
        System.out.println("New User, enter additional info about him");
        System.out.print("Enter birthDay: ");
        String birthDay = scanner.nextLine();
        library.addUserToRegister(new User(firstName, lastName, birthDay));
    }

    private static int getInteger(){
        String t;
        do {
            t = scanner.nextLine();
        } while (!StringUtils.isNumeric(t) || Integer.parseInt(t) < 1);
        return Integer.parseInt(t);
    }
}
