import java.util.ArrayList;
class Book{
    private String title ; 
    private String author;
    private int isbn; 

    public Book(String title , String author , int isbn){
        this.title= title;
        this.author = author;
        this.isbn = isbn;

    }
    public Book(){}
    
    public void setTitle(String title){
        this.title=title;
    }
    public String getTitle(){
        return title;
    }
    public void setAuthor(String author){
        this.author= author;
    }
    public String getAuthor(){
        return author;
    }
    public void setIsbn(int isbn){
        this.isbn= isbn;
    }
    public int getIsbn(){
        return isbn;
    }

    public void display(){
        System.out.println("Tittle :"+title);
        System.out.println("Author :"+author);
        System.out.println("Isbn :"+isbn);
    }


}
class BookCollection{
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book b ){
        books.add(b); 
        System.out.println("Book Added : "+b.getTitle());
    }
    public void removeBook(Book b){
        if(books.remove(b)){
            System.out.println("book removed :"+b.getTitle());
        }
        else{
            System.out.println(" book not found ");
        }
    }
    public void displayBooks(){
        for(Book b : books){
            b.display();
            // System.out.println(b.getTitle()+","+b.getAuthor()+","+b.getIsbn());
        }
    }

}
class TestMain {
    public static void main(String args[]) {
        Book b1 = new Book("Java Basics", "John Doe", 12345);
        Book b2 = new Book("OOP in Java", "Jane Smith", 67890);

        BookCollection collection = new BookCollection();
        collection.addBook(b1);
        collection.addBook(b2);

        System.out.println("\nAll Books:");
        collection.displayBooks();

        collection.removeBook(b1);

        System.out.println("\nAfter removal:");
        collection.displayBooks();
    }
}
