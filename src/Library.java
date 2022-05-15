import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.*;

public class Library{

    public static Vector<Book> books;
    public static Vector<User_Book_Lent> list_ULents;
    private static int quantity;
    public static Vector<Admin> admins;
    private final int SOCOT_CUA_BOOK = 4;
    private final int SOCOT_CUA_ADMIN = 2;
    private final int SOCOT_CUA_USERS = 3;
    private final int capacity = 1000;

    public Library(){
        admins = new Vector<Admin>();
        books = new Vector<Book>();
        list_ULents = new Vector<User_Book_Lent>();
        quantity = 0;
        readData();
    }


    public boolean readData() {
        try {
            FileInputStream booksF = new FileInputStream("src/books.txt");
            FileInputStream usersF = new FileInputStream("src/users.txt");
            FileInputStream adminsF = new FileInputStream("src/admins.txt");
            Scanner booksScanner = new Scanner(booksF);
            Scanner adminsScanner = new Scanner(adminsF);
            Scanner usersScanner = new Scanner(usersF);
            while (adminsScanner.hasNextLine()) {
                Admin curAdmin = new Admin();
                String line = adminsScanner.nextLine();
                String[] tokens = line.split("@");
                for (int i = 0; i < SOCOT_CUA_ADMIN; i++) {
                    if (i == 0) curAdmin.setName(tokens[i]);
                    if (i == 1) curAdmin.setPass(tokens[i]);
                }
                admins.add(curAdmin);
            }
            while (usersScanner.hasNextLine()) {
                User_Book_Lent curUsers = new User_Book_Lent();
                String line = usersScanner.nextLine();
                String[] tokens = line.split("@");
                for (int i = 0; i < SOCOT_CUA_USERS; i++) {
                    if (i == 0) curUsers.setId(Integer.parseInt(tokens[i]));
                    if (i == 1) curUsers.setUser(tokens[i]);
                    if (i == 2) curUsers.setDate(tokens[i]);
                }
                list_ULents.add(curUsers);
            }

            //
            //

            while(booksScanner.hasNextLine()) {
                Book curBook = new Book();
                String line = booksScanner.nextLine();
                String[] tokens = line.split("@");
                
                for (int i = 0; i < SOCOT_CUA_BOOK; i++) {
                    if (i == 0) curBook.setID(Integer.parseInt(tokens[i]));
                    if (i == 1) curBook.setBookname(tokens[i]);
                    if (i == 2) curBook.setAuthor(tokens[i]);
                    if (i == 3) {
                            curBook.setBorrowed(Integer.parseInt(tokens[i]) == 0 ? false : true);
                    }
                }
                add_Book(curBook);
                quantity = books.size();
            }
            booksF.close();
            usersF.close();
            adminsF.close();
            booksScanner.close();
            usersScanner.close();
            adminsScanner.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace(); 
            return false;
        }

    }

    

    public Library(String name, String pass, int q){
        quantity = q;
    }

    void setquan(int q){
        quantity = q;
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

    public Vector<Book> getBooks(){
        return books;
    }

    public void add_Book(Book bookNeedAdd){
        books.add(bookNeedAdd);
        quantity++;
    }

    public boolean readAdmins() {
        return false;
    }

    public boolean isAdmins(Admin admin) {
        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getName().equals(admin.getName())) {
                if (admins.get(i).getPass().equals(admin.getPass())) return true;
                return false;
            }
        }
        return false;
    }
    
    public boolean delete_Book(Book bookNeedDelete){
        int length = books.size();
        boolean checkAns = false;
        for(int indexOfBook = length - 1; indexOfBook >= 0; indexOfBook--){
            Book current = books.get(indexOfBook);
            if(current.getId() == bookNeedDelete.getId() &&
                current.getName().equals(bookNeedDelete.getName()) &&
                    current.getAuthor().equals(bookNeedDelete.getAuthor()) &&
                        current.getBorrowed() == bookNeedDelete.getBorrowed()){
                books.remove(indexOfBook);
                quantity--;
                checkAns = true;
            }
        }
        saveData();
        return checkAns;
    }

    public boolean delete_User_Lent(User_Book_Lent userNeedDelete){
        int length = list_ULents.size();
        boolean checkAns = false;
        for(int indexOfuser = length - 1; indexOfuser >= 0; indexOfuser--){
            User_Book_Lent current = list_ULents.get(indexOfuser);
            if(current.getId() == userNeedDelete.getId() &&
                current.getName().equals(userNeedDelete.getName()) &&
                    current.getDate().equals(userNeedDelete.getDate()))
            {
                for(int i=0; i<books.size(); i++){
                    if(books.get(i).getId() == current.getId()){
                        books.get(i).setBorrowed(false);
                    }
                }
                list_ULents.remove(indexOfuser);
                checkAns = true;
            }
        }

        saveData();
        return checkAns;
    }

    /*public Vector<Book> search_Book(String bookNeedSearch){
        Vector<Book> stringOfBook = new Vector<Book>();
        bookNeedSearch = bookNeedSearch.toLowerCase();
        bookNeedSearch = bookNeedSearch.replaceAll("\\s+","");
        int length = books.size();
        for(int indexOfBook = 0; indexOfBook < length; indexOfBook++){
            Book nameOfBook = books.get(indexOfBook);
            if(nameOfBook.getName().contains(bookNeedSearch) 
            || nameOfBook.getAuthor().contains(bookNeedSearch)){
                stringOfBook.add(books.get(indexOfBook));
            }
        }
        return stringOfBook;
    }*/

    public Vector<Book> search_Book_Name(String bookNeedSearch){
        Vector<Book> stringOfBook = new Vector<Book>();
        bookNeedSearch = bookNeedSearch.toLowerCase();
        bookNeedSearch = bookNeedSearch.replaceAll("\\s+","");
        int length = books.size();
        for(int indexOfBook = 0; indexOfBook < length; indexOfBook++){
            Book nameOfBook = books.get(indexOfBook);
            String name = nameOfBook.getName();
            //String nameAuthor = nameOfBook.getAuthor();
            //nameAuthor = nameAuthor.toLowerCase();
            name = name.toLowerCase();
            if(name.contains(bookNeedSearch)){
                stringOfBook.add(nameOfBook);
            }
        }
        return stringOfBook;
    }

    public Vector<Book> search_Book_Author(String bookNeedSearch){
        Vector<Book> stringOfBook = new Vector<Book>();
        bookNeedSearch = bookNeedSearch.toLowerCase();
        bookNeedSearch = bookNeedSearch.replaceAll("\\s+","");
        int length = books.size();
        for(int indexOfBook = 0; indexOfBook < length; indexOfBook++){
            Book nameOfBook = books.get(indexOfBook);
            ////String name = nameOfBook.getName();
            String nameAuthor = nameOfBook.getAuthor();
            nameAuthor = nameAuthor.toLowerCase();
            //name = name.toLowerCase();
            if(nameAuthor.contains(bookNeedSearch)){
                stringOfBook.add(nameOfBook);
            }
        }
        return stringOfBook;
    }

    public Vector<Book> search_Book_UseID(int idNeedSearch){
        Vector<Book> stringOfBook = new Vector<Book>();
        int length = books.size();
        for(int indexOfBook = 0; indexOfBook < length; indexOfBook++){
            Book nameOfBook = books.get(indexOfBook);
            if(nameOfBook.getId() == idNeedSearch){
                stringOfBook.add(books.get(indexOfBook));
            }
        }
        return stringOfBook;
    }

    public boolean changePass (Admin admin, String newpass) {
        if (isAdmins(admin)) {
            for (int i = 0; i < admins.size(); i++) {
                if (admins.get(i).getName().equals(admin.getName())) {
                    admins.get(i).setPass(newpass);
                    return true;
                }
            }
        }
        return false;
    }

    
    public boolean borrowBook(String UserName, int idOfBook){
        SimpleDateFormat formatDate = new SimpleDateFormat(
            "dd/MM/yyyy HH:mm:ss z"); 
        Date date = new Date();
        formatDate.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        
        int length = books.size();
        boolean checkAns = false;
        User_Book_Lent user = new User_Book_Lent(-1, UserName, "");
        for(int indexOfBook = length - 1; indexOfBook >= 0; indexOfBook--){
            Book current = books.get(indexOfBook);
            if(current.getId() == idOfBook && current.getBorrowed() == false){
                current.setBorrowed(true);
                books.set(indexOfBook, current);
                user.setId(current.getId());
                user.setDate(formatDate.format(date));
                list_ULents.add(user);
                checkAns = true;
                return checkAns;
            }
        }
        return checkAns;
    }
    public String getBookNamebyID (int iD) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).id == iD) {
                return books.get(i).getAuthor();
            }
        }
        return "null";
    }
    public boolean saveData(){
        try {
            FileOutputStream booksF = new FileOutputStream("src/books.txt");
            FileOutputStream adminsF = new FileOutputStream("src/admins.txt");
            FileOutputStream usersF = new FileOutputStream("src/users.txt");
            booksF.write(("").getBytes());
            adminsF.write(("").getBytes());
            usersF.write(("").getBytes());
            for (int i = 0; i < books.size(); i++) {
                String line = books.get(i).getId() + "@" 
                            + books.get(i).getName() + "@" 
                            + books.get(i).getAuthor() + "@"
                            + (books.get(i).getBorrowed() ? "1" : "0");
                booksF.write(line.getBytes());
                if (i != books.size() - 1) booksF.write("\n".getBytes());
            }
            for (int i = 0; i < this.admins.size(); i++) {
                String line = admins.get(i).getName() + "@" 
                            + admins.get(i).getPass();
                adminsF.write(line.getBytes());
                // adminsF.write("\n".getBytes());
                if (i != this.admins.size() - 1) adminsF.write("\n".getBytes());
            }
            for (int i = 0; i < this.list_ULents.size(); i++) {
                String line = list_ULents.get(i).getId() + "@" 
                            + list_ULents.get(i).getName() + "@" 
                            + list_ULents.get(i).getDate();
                usersF.write(line.getBytes());
                if (i != this.list_ULents.size() - 1) usersF.write("\n".getBytes());
            }
            booksF.close();
            adminsF.close();
            usersF.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace(); 
            return false;
        }

    }

    
}
