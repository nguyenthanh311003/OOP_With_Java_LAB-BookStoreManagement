package object;

public class Publisher {

    private String publisherId;
    private String publisherName;
    private String phoneNumber;

    public Publisher() {

    }

    public Publisher(String publisherId, String publisherName, String phoneNumber) {
        this.publisherId = publisherId;
        this.publisherName = publisherName;
        this.phoneNumber = phoneNumber;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String id) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setName(String name) {
        this.publisherName = publisherName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        //          PublisherID | Publisher Name | Phone Number
        return String.format("%-6s      | %-30s| %-12s\n", publisherId, publisherName, phoneNumber);
    }

}
