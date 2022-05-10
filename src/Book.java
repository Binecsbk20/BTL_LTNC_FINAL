public class Book{
    public int id;
    private String name;
    public String author;
    private boolean borrowed;
    

    public Book() {
    }
    public Book(int id, String bN, String wN, boolean isBr){
        this.id = id;
        name = bN;
        author = wN;
        borrowed = isBr;
    }

    public void setID(int id) {
        this.id = id;
    }
    public void setBookname(String bN) {
        name = bN;
    }
    public void setAuthor(String wN) {
        author = wN;
    }
    public void setBorrowed(boolean T) {
        borrowed = T;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getAuthor(){
        return author;
    }
    public boolean getBorrowed() {
        return borrowed;
    }

}