public class Lecturer extends Employee{
    private String module;
    public Lecturer(String name, double monthlySalary, String module){
        //super is to do inheritence
        super(name, monthlySalary);
        this.module = module;
    }
    //adding a method for the class
    public String getModule() {
        return module;
    }
}
