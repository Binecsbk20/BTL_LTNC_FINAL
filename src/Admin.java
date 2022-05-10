public class Admin{
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
    public void setName(String name){
        this.name = name;
    }
    public void setPass(String pass){
        this.pass = pass;
    }
    public String getName(){
        return this.name;
    }
    public String getPass(){
        return this.pass;
    }
}
