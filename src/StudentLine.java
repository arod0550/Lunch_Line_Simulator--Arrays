/**
 * This class represents the student line where the students with their money will stand in. Data Structure is an Array.
 */
public class StudentLine {
    //Data fields
    private Student[] students;
    private int studentCount;
    //the capacity of the array in this case is 20 students.
    final int CAPACITY=20;

    //Constructors
    /**
     * Empty constructor that initializes the array of students and the number of students.
     */
    public StudentLine(){
        this.students = new Student[CAPACITY];
        this.studentCount = 0;
    }

    //Getters and Setters
    /**
     * Gets the reference to the Student at the given index
     * @param index
     *      an int that gives position of student
     * @return
     *      returning an object of Student
     *
     */
    public Student getStudent(int index){
        try{}
        catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("That is an invalid index.");
        }
        return students[index];
    }

    /**
     * sets an object of student.
     * @param students
     */
    public void setStudents(Student[] students) {
        this.students = students;
    }

    /**
     * sets the counter of students in the line
     * @param studentCount
     */
    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    //Methods

    /**
     * Returns the total number of Students in the list.
     * @return
     *      int representing number of students.
     */
    public int numStudents(){
        return studentCount;
    }

    /**
     * prints the list of students with their respective attributes.
     * @return
     *      A string representing a list of the students in  the line.
     */
    @Override
    public String toString() {
        String print = "";
            for(int i=0; i< numStudents();i++) {
                if(students[i] != null) {
                    print += (i+1) + ". " + (students[i].toString()) + "\n";
                }
            }
        return print;
    }

    /**
     * Removes the given student and moves all students behind this student forward by one index
     * @param index
     *        an int representing the position of the student.
     * @return
     *         an object of student representing the student removed.
     */
    public Student removeStudent(int index) throws EmptyLineException{
        try{
            if (index < 0 && index >20) {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("the index is invalid");}

        //throw EmptyLineException
        if(numStudents()==0){
            throw new EmptyLineException("There are no students to serve lunch to. Mystery Meat Martha is sad.");
        }

        Student temp =(students[index]);
        students[index]= null; //Remove student

        for (int i = index+1; i < CAPACITY-1; i++){ //start from the next index of the one removed
            if(students[i] != null){//check there's a student at the next index
                    students[i-1]=students[i];//let the previous index being null be the student
            }
             else{
                 students[i-1] = null;//there's no student next to it so just make it null
            }
        }
        studentCount--;
        return temp; //Return student that was removed
    }

    /**
     * Adds a student at the given index, moving all other students behind the current student back one index.
     * @param index
     *      an int representing the position of the student.
     * @param student
     *          object of student representing student inputted.
     * @return
     *      an object of student which represents the student added.
     */
    public Student addStudent(int index,Student student) throws DeanException{
        try{
            if (index < 0 && index >20) {
                throw new IllegalArgumentException();
            }
        }
        catch (IllegalArgumentException ex){
            System.out.println("Index is too high or too low  and would create a hole in the array.");
        }
        //throw Dean Exception
        if (numStudents() == CAPACITY) {
            throw new DeanException("You tried to add a student to a full lunch line."+
                        "Dean Mean has picked up the student and given them a healthy dose of detention."+
                        "Therefore, they will not be added to the lunch line.");
        }

        //if space is occupied move line to the right by one space then place student at index
        if (students[index] != null){
            for(int i = CAPACITY-1; i >= 1; i--){
                students[i] = students[i-1];
            }
            students[index] = student;
        }//if space is empty place student at index without any changes
        else{
            students[index] = student;
        }
        //increase count of students as you add students to the list
        studentCount++;
        return student;
    }

    /**
     * If the indices are valid, the two students are swapped.
     * @param index1
     *      int which represents the position desired to be swapped.
     * @param index2
     *      int which represents the position being swapped for.
     */
    public void swapStudents(int index1, int index2){
        try{
            if((index1 < 0 && index1 > 20) || (index2 < 0 && index2 > 20) ){
                throw new IndexOutOfBoundsException();
            }
        }
        catch(IndexOutOfBoundsException ex){
            System.out.println("one or both indexes are invalid.");

        }
        Student temp;
        temp = students[index1];
        students[index1] = students[index2];
        students[index2] = temp;
    }

    /**
     * Creates a deep copy of this StudentLine object.
     * If the copy is modified, this object should remain unmodified.
     * All the students inside should be deep copied as well.
     * @return
     *      a deep copy of this StudentLine object.
     */
    public StudentLine clone(){
        StudentLine studentLine = new StudentLine();
        studentLine.setStudentCount(studentCount); //initializing the data field
        Student[] studentsClone = new Student[CAPACITY]; //creating an array for the clone
        for(int i = 0; i < CAPACITY; i++){
            try {
                if(students[i] != null)
                     studentsClone[i] = students[i].clone(); //cloning every element from original array
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        studentLine.setStudents(studentsClone); //setting new cloned array to the student line
        return studentLine;
    }

    /**
     *Checks if this student line is equal to another object
     * @param o
     *      Of type object which represents whats being compared to.
     * @return
     *      a boolean expression
     */
    public boolean equals(Object o){
        if (this.equals(o))
            return true;
        else
            return false;
    }
}
