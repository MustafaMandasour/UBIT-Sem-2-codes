public class EBook extends BookInfo {

    private String fileSize;
    private String format;

    public EBook(String BookTitle, String AuthorName, String PublishDate, int PgNum, String fileSize, String format) {
        super(BookTitle, AuthorName, PublishDate, PgNum);
        this.fileSize = fileSize;
        this.format = format;
    }
     @Override
    public void Display() {
        super.Display();
        System.out.println("File Size: " + fileSize);
        System.out.println("Format   : " + format);
    }

    public String getBookText() {
        return "----- EBook Info -----\n" +
               "Title: " + BookTitle + "\n" +
               "Author: " + AuthorName + "\n" +
               "Published: " + PublishDate + "\n" +
               "Pages: " + PgNum + "\n" +
               "File Size: " + fileSize + "\n" +
               "Format: " + format + "\n";
    }

}