package manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import object.Publisher;
import object.Book;

public class Management {

    private Scanner sc = new Scanner(System.in);
    private List<Publisher> pList;
    private List<Book> bList;

    public Management() {
        pList = new ArrayList<>();
        bList = new ArrayList<>();
    }

    public void addPublisher() throws Exception {
        String publisherId, publisherName, phoneNumber;
        publisherDisplay();
        publisherId = Validation.getPublisherID("Enter Publisher ID with form (Pxxxxx): ", pList, 1);

        publisherName = Validation.getPublisherName("Enter Publisher Name: ");

        phoneNumber = Validation.getPublisherPhoneNumber("Enter Publisher phone number: ");

        pList.add(new Publisher(publisherId, publisherName, phoneNumber));
        System.out.println("Add Publisher Success <3");
        publisherDisplay();
        this.saveToFile();
        pList.removeAll(pList);
    }

    public void deletePublisher() throws Exception {
        String publisherId;
        if (pList.isEmpty()) {
            System.out.println("Have no any Publisher in list!");
        } else {

            publisherDisplay();
            publisherId = Validation.getPublisherID("Enter Publisher ID with form (Pxxxxx): ", pList, 2);
            for (Publisher o : pList) {
                if (o.getPublisherId().equals(publisherId)) {
                    pList.remove(o);
                    System.out.println("Delete publisher success <3");
                    break;
                }
            }
            this.saveToFile();
            pList.removeAll(pList);
        }

    }

    public void loadFromFile() throws FileNotFoundException, IOException {
        File f = new File("publisher.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            String[] info = line.split("[|]");
            String publisherId = info[0].trim();
            String publisherName = info[1].trim();
            String phoneNumber = info[2].trim();

            pList.add(new Publisher(publisherId, publisherName, phoneNumber));
        }
        fr.close();
        br.close();
    }

    public void loadBookFromFile() throws FileNotFoundException, IOException {
        File f = new File("book.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            String[] info = line.split("[|]");
            String bookId = info[0].trim();
            String bookName = info[1].trim();
            double price = Double.parseDouble(info[2].trim());
            int quantity = Integer.parseInt(info[3].trim());
            String bookOfPublisherId = info[4].trim();
            String status = info[5].trim();

            bList.add(new Book(bookId, bookName, price, quantity, bookOfPublisherId, status));
        }
        fr.close();
        br.close();
    }

    public void saveToFile() {
        try {
            File f = new File("publisher.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Publisher x : pList) {
                bw.write(x.toString());
            }
            System.out.println("The Publisher list has been saved to file.");
            bw.close();
            fw.close();
        } catch (Exception exception) {
            System.out.println("Save to file failed!");
        }
    }

    public void saveBookToFile() {
        try {
            File f = new File("book.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Book x : bList) {
                bw.write(x.toString());
            }
            System.out.println("The Book list has been saved to file.");
            bw.close();
            fw.close();
        } catch (Exception exception) {
            System.out.println("Save to file failed!");
        }

    }

    public void printFile() {
        try {
            FileReader fr = new FileReader("publisher.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void publisherDisplay() throws IOException {
        System.out.println("----------------------------------------------------------");
        System.out.println("PublisherID | Publisher Name                | Phone Number");
        pList.sort(Comparator.comparing(Publisher::getPublisherName));
        for (Publisher x : pList) {

            System.out.println(x);
        }
    }

    public void bookDisplay() {
        System.out.println("");
        for (int i = 0; i < bList.size() - 1; i++) {
            for (int j = i + 1; j < bList.size(); j++) {
                if (bList.get(i).getQuantity() < bList.get(j).getQuantity()) {

                    Collections.swap(bList, i, j);

                } else if (bList.get(i).getQuantity() == bList.get(j).getQuantity()) {
                    if (bList.get(i).getPrice() > bList.get(j).getPrice()) {
                        Collections.swap(bList, i, j);
                    }
                }
            }
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("BookID | Book Name                      | Price   | Quantity   | Subtotal                       | Publisher                      | Status");
        for (Book x : bList) {
            System.out.printf("%-6s | %-30s | %-7.1f | %-10d | %-30f | %-30s | %-15s\n", x.getBookId(), x.getBookName(), x.getPrice(), x.getQuantity(), x.getPrice() * x.getQuantity(), Validation.pName(x.getBookOfPublisherId(), pList, bList), x.getStatus());
        }
    }

    public void addBook() throws Exception {
        String bookID, bookName, bookOfPublisherId, status;
        double price;
        int quantity;
        bookDisplay();
        bookID = Validation.getBookID("Enter Book ID with form (Bxxxxx): ", bList, 1);
        bookName = Validation.getBookName("Enter Book Name: ", 1);
//        bookOfPublisherId = Validation.getPublisherID("Enter Publisher ID: ", pList, 3);
        bookOfPublisherId = Validation.getPublisherID("Enter Publisher ID: ", pList, 3);
        status = Validation.getBookStatus("Status of this Book: Available or Not Available (press a for Available || press n for Not Available): ", 1);
        price = Validation.getBookPrice("Price of this Book: ");
        quantity = Validation.getBookQuantity("Quantity of this Book: ");
        bList.add(new Book(bookID, bookName, price, quantity, bookOfPublisherId, status));
        System.out.println("Add book success <3");
        bookDisplay();
        this.saveBookToFile();
        bList.removeAll(bList);
    }

    public void deleteBook() {
        String bookID;
        if (bList.isEmpty()) {
            System.out.println("Have no any Book in list!");
        } else {
            bookDisplay();
            bookID = Validation.getBookID("Enter Book ID: ", bList, 3);
            for (Book o : bList) {
                if (o.getBookId().equals(bookID)) {
                    bList.remove(o);
                    System.out.println("Delete Book Success <3");
                    break;
                }
            }
            this.saveBookToFile();
            bList.removeAll(bList);
        }

    }

    public void searchBook() throws Exception {
        String bookNameSearch;
        String bookPublisherID;
        if (bList.isEmpty()) {
            System.out.println("Have no any Book in list!");
        } else {
            for (Book o : bList) {
                System.out.print("Enter Book Name: ");
                bookNameSearch = sc.nextLine();
                bookPublisherID = Validation.getPublisherID("Enter Publisher ID with form (Pxxxxx): ", pList, 2);
                if(o.getBookName().contains(bookNameSearch) && o.getBookOfPublisherId().contains(bookPublisherID)) {
                    System.out.println(o);
                } else {
                    System.out.println("Don't have any following the format which is giving!");
                }
                break;
            }

        }
    }

    public void update() throws Exception {
        String bookID;
        String bookName = null;
        String price = null;
        String quantity = null;
        String publisherID = null;
        String status = null;
        bookDisplay();
        bookID = Validation.getBookID("Enter Book ID to be update: ", bList, 3);
        if (bList.isEmpty()) {
            System.err.println("Don't have any Book to update!");
        } else {
            for (Book o : bList) {
                if (o.getBookId().equals(bookID)) {
                    bookName = Validation.getBookName("Enter new Book Name: ", 2);
                    if (bookName.equals("")) {
                        o.setBookName(o.getBookName());
                    } else {
                        o.setBookName(bookName);
                    }

                    do {
                        System.out.print("Enter new Book Price: ");
                        price = sc.nextLine();
                        if (price.equals("")) {
                            o.setPrice(o.getPrice());
                            break;
                        } else if (price.matches("^([0-9]){1,15}$") || price.matches("^([0.00-9.99]){1,15}$")) {
                            o.setPrice(Double.parseDouble(price));
                            break;
                        } else {
                            System.err.println("Book price must be a real number and Book price must be greater than 0!");
                        }
                    } while (true);

                    do {
                        System.out.print("Enter new quantity: ");
                        quantity = sc.nextLine();
                        if (quantity.equals("")) {
                            o.setQuantity(o.getQuantity());
                            break;
                        } else if (price.matches("^([0-9]){1,15}$")) {
                            o.setQuantity(Integer.parseInt(quantity));
                            break;
                        } else {
                            System.err.println("A quantity must be an integer number and greater than 0");
                        }
                    } while (true);

                    publisherID = Validation.getPublisherID("Enter new Publisher ID: ", pList, 4);
                    if (publisherID.equals("")) {
                        o.setBookOfPublisherId(o.getBookOfPublisherId());
                    } else {
                        o.setBookOfPublisherId(publisherID);
                    }

                    status = Validation.getBookStatus("Enter new Book Status: ", 2);
                    if (status.equals("")) {
                        o.setStatus(o.getStatus());
                    } else {
                        o.setStatus(status);
                    }

                }
            }
            System.out.println("Update Book success <3");
            bookDisplay();
            this.saveBookToFile();
            bList.removeAll(bList);
        }
    }

}
