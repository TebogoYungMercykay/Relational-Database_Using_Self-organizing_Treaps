public class DatabaseException extends Exception {
    private String message;

    private DatabaseException(String msg) {
        message = msg;
    }

    public static DatabaseException invalidNumberOfColums() {
        return new DatabaseException("Invalid number of columns");
    }

    public static DatabaseException invalidColumnName(String invalidCol) {
        return new DatabaseException(invalidCol + " is not a valid column name in database");
    }

    public static DatabaseException databaseFull() {
        return new DatabaseException("Database is full");
    }

    public static <T> DatabaseException duplicateInsert(T data) {
        if (data == null)
            return new DatabaseException("Duplicate insert of: null");
        return new DatabaseException("Duplicate insert of:" + data.toString());
    }

    @Override
    public String toString() {
        return message;
    }
}
