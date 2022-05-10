public class User_Book_Lent{
    
    private int id; // ID của sách mượn
    private String name; // Tên của người mượn
    private String Date; // Ngày mượn 

    public User_Book_Lent(){
    }
    public User_Book_Lent(int id_u, String name_u, String date){
        this.id = id_u;
        this.name = name_u;
        this.Date = date;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setUser(String user){
        this.name = user;
    }
    public void setDate(String date){
        this.Date = date;
    } 

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getDate(){
        return Date;
    }
}