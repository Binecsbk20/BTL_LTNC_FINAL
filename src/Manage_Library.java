/*import java.util.*;
import java.io.*;

public class Manage_Library {
    /*class Book{
        private int bookId;
        private String bookName;
        private String writerName;
        private boolean borrowed;
    
        public Book() {
            bookId = -1;
            bookName = "";
            writerName = "";
            borrowed = false;
        }
        public Book(int id, String bN, String wN, boolean isBr){
            bookId = id;
            bookName = bN;
            writerName = wN;
            borrowed = isBr;
        }
    
        public void setID(int id) {
            bookId = id;
        }
        public void setBookname(String bN) {
            bookName = bN;
        }
        public void setWritername(String wN) {
            writerName = wN;
        }
        public void setBorrowed(boolean T) {
            borrowed = T;
        }
    
        public int getID(){
            return bookId;
        }
        public String getName(){
            return bookName;
        }
        public String getwriterName(){
            return writerName;
        }
        public boolean isBorrowed() {
            return borrowed;
        }
    
    }*/
    
    /*class User_Book_Lent{
    
        private int bookID; // ID của sách mượn
        private String userName; // Tên của người mượn
        private String Date; // Ngày mượn 
    
        public User_Book_Lent(){
            bookID = -1;
            userName = "";
            Date = "";
        }
        public User_Book_Lent(int id_u, String name_u, String date){
            this.bookID = id_u;
            this.userName = name_u;
            this.Date = date;
        }
    
        public void setId(int id){
            this.bookID = id;
        }
        public void setUser(String user){
            this.userName = user;
        }
        public void setDate(String date){
            this.Date = date;
        } 
    
        public int getID(){
            return bookID;
        }
        public String getName_user(){
            return userName;
        }
        public String getDate(){
            return Date;
        }
    }*/
    
    /*class Admin{
        private String name;
        private String pass;    
        public Admin() {
            this.name = "";
            this.pass = "";
        }
        public Admin(String name, String pass) {
            this.name = name;
            this.pass = pass;
        }
        public void setname(String name){
            this.name = name;
        }
        public void setpass(String pass){
            this.pass = pass;
        }
        public String getname(){
            return this.name;
        }
        public String getpass(){
            return this.pass;
        }
    }*/
    
    /*class Library{
        private Vector<Book> books;
        private Vector<User_Book_Lent> list_ULents;
        private int quantity;
        final private int capacity = 1000;
    
        public Library(){
            books = new Vector<Book>();
            list_ULents = new Vector<User_Book_Lent>();
            quantity = 0;
        }
    
        public Library(String name, String pass, int quantity){
            this.quantity = quantity;
        }
    
        void setquan(int quantity){
            this.quantity = quantity;
        }
    
    
        public int getCap() {
            return capacity;
        }
    
        public int getQuan() {
            return quantity;
        }
    
    
        public void add_UserBookLent(User_Book_Lent userbooklent){
            list_ULents.add(userbooklent);
        }
    
        public Vector<User_Book_Lent> getlist_ULents(){
            return list_ULents;
        }
    
        public void add_Book(Book bookNeedAdd){
            books.add(bookNeedAdd);
            quantity++;
        }
    
        public boolean readAdmins() {
            return false;
        }
    
        public boolean readBooks(){
            try {
                FileInputStream booksF = new FileInputStream("books.txt");
                // FileInputStream usersF = new FileInputStream("users.txt");
                // FileInputStream adminsF = new FileInputStream("admins.txt");
                Scanner booksScanner = new Scanner(booksF);
                while(booksScanner.hasNextLine()) {
                    Book bookCur = new Book();
                    String line = booksScanner.nextLine();
                    String[] tokens = line.split("@");
                    for (int i = 0; i <= 3; i++) {
                        if (i == 0) bookCur.setID(Integer.parseInt(tokens[i]));
                        if (i == 1) bookCur.setBookname(tokens[i]);
                        if (i == 2) bookCur.setWritername(tokens[i]);
                        if (i == 3) {
                                bookCur.setBorrowed(Integer.parseInt(tokens[i]) == 0 ? false : true);
                        }
                    }
                    add_Book(bookCur);
                }
                booksF.close();
                booksScanner.close();
                System.out.println("Read files successfully");
                return true;
    
            } catch (IOException e) {
                System.out.println("Files not found");
                e.printStackTrace(); 
                return false;
            }
    
        }
    
        public void delete_Book(Book bookNeedDelete){
            int length = books.size();
            for(int indexOfBook = 0; indexOfBook < length; indexOfBook++){
                if(books.get(indexOfBook) == bookNeedDelete){
                    books.remove(indexOfBook);
                    quantity--;
                    return;
                }
            }
        }
    
        public Vector<Book> search_Book(String bookNeedSearch){
            Vector<Book> stringOfBook = new Vector<Book>();
            bookNeedSearch = bookNeedSearch.toLowerCase();
            bookNeedSearch = bookNeedSearch.replaceAll("\\s+","");
            int length = books.size();
            for(int indexOfBook = 0; indexOfBook < length; indexOfBook++){
                Book nameOfBook = books.get(indexOfBook);
                if(nameOfBook.getName().contains(bookNeedSearch) 
                || nameOfBook.getwriterName().contains(bookNeedSearch)){
                    stringOfBook.add(books.get(indexOfBook));
                }
            }
            return stringOfBook;
        }
    
        public boolean saveBooks(){
            try {
                FileOutputStream booksF = new FileOutputStream("books.txt");
                booksF.write(("").getBytes());
                // FileInputStream usersF = new FileInputStream("users.txt");
                // FileInputStream adminsF = new FileInputStream("admins.txt");
                for (int i = 0; i < quantity; i++) {
                    String line = books.get(i).getID() + "@" 
                                + books.get(i).getName() + "@" 
                                + books.get(i).getwriterName() + "@"
                                + (books.get(i).isBorrowed() ? "1" : "0");
                    booksF.write(line.getBytes());
                    booksF.write("\n".getBytes());
                }
                
                System.out.println("Saved file successfully!");
                return true;
            } catch (IOException e) {
                System.out.println("File not found!");
                e.printStackTrace(); 
                return false;
            }
    
        }
        
    }

}*/