public class Employee {
    //private variables
    private String name;
    private double monthlySalary;
    //adding constructor
    public Employee(String name, double monthlySalary){
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    public String getName() {
        return name;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }
    public double getAnnualSalary(){
        return rounded(monthlySalary * 12);
    }
    //function to round
    public double rounded(double x){
        return (double) Math.round(x * 100)/100;
    }
}
