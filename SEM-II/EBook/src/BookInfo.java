
public class BookInfo {

    protected String BookTitle;
    protected String AuthorName;
    protected int PgNum;
    protected String PublishDate;    

    public BookInfo(String Title,String AName,String PDate,int PNum){

        this.BookTitle = Title;
        this.AuthorName = AName;
        this.PgNum = PNum;
        this.PublishDate = PDate;   
    }

    public void Display() {

        System.out.println("---------Book Info--------");
        System.out.println("Title :" + BookTitle);
        System.out.println("Author :" + AuthorName);
        System.out.println("Published on :" + PublishDate);
        System.out.println("Is "+ PgNum+" Long");

    }

}

