package object;

import manager.Validation;
import object.Publisher;

public class Book {

    private String bookId;
    private String bookName;
    private double price;
    private int quantity;
    private String bookOfPublisherId;
    private String status;

    public Book() {
    }

    public Book(String bookId, String bookName, double price, int quantity, String bookOfPublisherId, String status) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
        this.quantity = quantity;
        this.bookOfPublisherId = bookOfPublisherId;
        this.status = status;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBookOfPublisherId() {
        return bookOfPublisherId;
    }

    public void setBookOfPublisherId(String bookOfPublisherId) {
        this.bookOfPublisherId = bookOfPublisherId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%-6s | %-30s | %-7.1f | %-10d | %-30s | %-15s\n", bookId, bookName, price, quantity, bookOfPublisherId, status);
    }

}
