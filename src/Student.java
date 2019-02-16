/**
 * This class represents the student being part of the lunch line simulator.
 */
public class Student implements Cloneable{
    //Data Fields
    private String name;
    private double money;

    //Constructors
    /**
     * Empty constructor.
     */
    public Student(){}

    /**
     * Default constructor
     * @param name
     *          String representing name of student.
     * @param money
     *          double representing money of student to buy food.
     *
     * <dt>Postconditions: This object has  been initialized to an Student object with specified name and price). </>
     */
    public Student(String name, double money){
        this.name = name;
        this.money = money;
    }

    //Getters and Setters
    /**
     * Sets name of the student object.
     * @param name
     *          A string representing name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets money amount of the Students Object.
     * @param money
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * provides name of student.
     * @return
     *      returns a String representing name of Student Object.
     *
     */
    public String getName() {
        return name;
    }

    /**
     * provides money amount of student.
     * @return returns a double representing money amount of Student Object.
     */
    public double getMoney() {
        return money;
    }

    //Methods
    /**
     * creates a copy of a student.
     * @return
     *      An object representing a clone of student
     * @throws CloneNotSupportedException
     */
    public Student clone()throws CloneNotSupportedException{
        //return super.clone();
        Student student = new Student();
        student.setMoney(money);
        student.setName(name);
        return student;
    }

    /**
     * provides all factors of an object of student.
     * @return
     *      a string representing student's name and money.
     */
    @Override
    public String toString() {
        return
                "" + name +
                "  $" + money;
    }

    /**
     * Compare this Student to another object for equality.
     * @param obj
     * @return
     *      a value of true if the obj refers to a Student object with the same properties as this Student. Otherwise, returns a value of false.
     */
    public boolean equals(Object obj){
        if(obj instanceof Student)
            return true;
        else
            return false;
    }
}
