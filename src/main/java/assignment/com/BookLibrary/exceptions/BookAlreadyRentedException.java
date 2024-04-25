package assignment.com.BookLibrary.exceptions;

public class BookAlreadyRentedException extends RuntimeException {

    String resourceName;
    String fieldName;
    long fieldValue;

    public BookAlreadyRentedException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s  is not available currently with %s : %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
