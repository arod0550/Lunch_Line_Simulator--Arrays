import java.util.*;

/**
 * Main class of the project. A middle school lunch line where you can manipulate who gets added and taken off the line among many features.
 */
public class LunchLineSimulator {
    public static void main(String[] args) {
        //initializes the variable containing reality to A
        String currentReality = "A";

        //Student line for Reality A & Reality B
        StudentLine studentLineA = new StudentLine();
        StudentLine studentLineB = new StudentLine();

        //create a scanner
        Scanner input = new Scanner(System.in);

        //Welcoming message
        System.out.println("Welcome to the Middle School where you are the master of the lunch line, and you may subject");
        System.out.println("your angry kids to whatever form of culinary torture best fits your mood. You are in Reality A.");
        System.out.println();

        //initialize section variable to a empty string
        String section;
        mainMenu();

        while (true) {
            section = input.next();

            switch (section.toLowerCase()) {
                case "a":
                    //Section A  (Add student)
                    //User input
                    try {
                        System.out.println("Please enter student name: ");
                        input.nextLine();//flushing
                        String studentName = input.nextLine();

                        System.out.println("Please enter money amount: ");
                        double studentMoney = input.nextDouble();

                        //new student created with name and money provided
                        Student student = new Student(studentName, studentMoney);

                        //student created added to the back of line
                        try {
                            if (currentReality.equals("A")) {
                                studentLineA.addStudent(studentLineA.numStudents(), student);

                                //let user know student has been added
                                System.out.println(studentName + " has been added to the line in reality "
                                        + currentReality + " in position " + (studentLineA.numStudents()) + ". " + studentName + " has $" + studentMoney);
                            } else {
                                studentLineB.addStudent(studentLineB.numStudents(), student);

                                //let user know student has been added
                                System.out.println(studentName + " has been added to the line in reality "
                                        + currentReality + " in position " + (studentLineB.numStudents()) + ". " + studentName + " has $" + studentMoney);
                            }

                        } catch (ArrayIndexOutOfBoundsException ex) {
                            System.out.println("this is an invalid index. Try again");
                        } catch (DeanException ex) {
                            System.out.println(ex.getMessage());
                        }

                        System.out.println();
                        mainMenu();
                    } catch (InputMismatchException ex) {
                        System.out.println("invalid input. Try again.");
                    }
                    break;
                case "b":
                    //Section B (remove student)

                    //user input
                    System.out.println("Please enter student index (1-20): ");
                    int indexRemoved = input.nextInt() - 1;

                    try {
                        if (currentReality.equals("A")) {
                            //student removed by index provided
                            Student studentRemoved = studentLineA.removeStudent(indexRemoved);

                            //let user know student has been removed
                            System.out.println("The bully has stolen " + studentRemoved.getName() + "'s lunch money, and " + studentRemoved.getName() + " has left feeling hungry.");
                            System.out.println("Current Reality: " + currentReality);
                        } else {
                            Student studentRemoved = studentLineB.removeStudent(indexRemoved);

                            //let user know student has been removed
                            System.out.println("The bully has stolen " + studentRemoved.getName() + "'s lunch money, and " + studentRemoved.getName() + " has left feeling hungry.");
                            System.out.println("Current Reality: " + currentReality);
                        }
                    } catch (NullPointerException ex) {
                        System.out.println("This is an Invalid Index. Try Again");
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        System.out.println("This is an Invalid Index. Try Again");
                    } catch (EmptyLineException ex) {
                        System.out.println(ex.getMessage());
                    }
                    System.out.println();
                    mainMenu();
                    break;
                case "c":
                    //Section C (Cut Friend)
                    //user input
                    System.out.println("Please enter student name: ");
                    input.nextLine();//flushing
                    String studentName1 = input.nextLine();

                    System.out.println("Please enter money amount: ");
                    double studentMoney1 = input.nextDouble();

                    System.out.println("Please enter position (1-20): ");
                    int position = input.nextInt() - 1;

                    //create a student with the info provided
                    Student cuttingStudent = new Student(studentName1, studentMoney1);

                    //adding student to the line
                    try {
                        if (currentReality.equals("A")) {
                            studentLineA.addStudent(position, cuttingStudent);
                        } else {
                            studentLineB.addStudent(position, cuttingStudent);
                        }
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        System.out.println("this is an invalid index. Try again");
                    } catch (DeanException ex) {
                        System.out.println(ex.getMessage());
                    }

                    //let user know student has been added
                    System.out.println(studentName1 + " has been added to the line in position " + (position + 1) + ". " + studentName1 + " has $" + studentMoney1);
                    System.out.println("Current Reality: " + currentReality);

                    System.out.println();
                    mainMenu();
                    break;
                case "d":
                    //Section D (Duplicate realities)
                    if (currentReality.equals("A")) {
                        studentLineB = studentLineA.clone();
                        System.out.println("Reality A has been copied into Reality B.");
                    } else {
                        studentLineA = studentLineB.clone();
                        System.out.println("Reality B has been copied into Reality A.");
                    }
                    mainMenu();
                    break;
                case "e":
                    //Section E (Realities equality)
                    if (studentLineA.toString().equals(studentLineB.toString()))
                        System.out.println("The Realities are equal");
                    else
                        System.out.println("The Realities are not equal");
                    mainMenu();
                    break;
                case "o":
                    //Section O (Switch realities)
                    if (currentReality.equals("A")) {
                        currentReality = "B";
                        System.out.println("You are now in Reality B");
                    } else {
                        currentReality = "A";
                        System.out.println("You are now in Reality A");
                    }
                    mainMenu();
                    break;
                case "p":
                    //Section P (Print reality Specified)
                    if (currentReality.equals("A")) {
                        System.out.println("Lunch Line in Reality A:");
                        System.out.println(studentLineA.toString());
                    } else {
                        System.out.println("Lunch Line in Reality B:");
                        System.out.println(studentLineB.toString());
                    }

                    mainMenu();
                    break;
                case "s":
                    //Section S (serve student)
                    //serve lunch to the first person in line
                    try {
                        if (currentReality.equals("A")) {
                            Student studentRemoved = studentLineA.removeStudent(0);
                            System.out.println(studentRemoved.getName() + " has been served today's special: Bouncy Chicken? Nuggets. We hope he/she lives to see another day!");
                        } else {
                            Student studentRemoved = studentLineB.removeStudent(0);
                            System.out.println(studentRemoved.getName() + " has been served today's special: Bouncy Chicken? Nuggets. We hope he/she lives to see another day!");
                        }
                    } catch (NullPointerException ex) {
                        System.out.println("There's no student to serve lunch to. Mystery Meat Martha is sad.");
                    } catch (EmptyLineException ex) {
                        System.out.println(ex.getMessage());
                    }
                    mainMenu();
                    break;
                case "t":
                    //Section T (Swapping students)
                    //user input
                    System.out.println("Please enter student1 index (1-20): ");
                    int index1 = input.nextInt() - 1;
                    System.out.println("Please enter student2 index (1-20): ");
                    int index2 = input.nextInt() - 1;

                    //swap students
                    try {
                        if (currentReality.equals("A")) {
                            studentLineA.swapStudents(index1, index2);
                            //let user know students have been swapped
                            Student studentSwap1 = studentLineA.getStudent(index1);
                            Student studentSwap2 = studentLineA.getStudent(index2);
                            System.out.println(studentSwap1.getName() + " has swapped positions with " + studentSwap2.getName() + " in the line");
                        } else {
                            studentLineB.swapStudents(index1, index2);
                            //let user know students have been swapped
                            Student studentSwapOne = studentLineB.getStudent(index1);
                            Student studentSwapTwo = studentLineB.getStudent(index2);
                            System.out.println(studentSwapOne.getName() + " has swapped positions with " + studentSwapTwo.getName() + " in the line");
                        }
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        System.out.println("Invalid index. Try again.");
                    } catch (NullPointerException ex) {
                        System.out.println("Invalid index. There's no one there at any of the given positions. Try again");
                    }
                    mainMenu();
                    break;
                case "u":
                    //Section U (Updating money)
                    //user input
                    System.out.println("Please enter student index (1-20): ");
                    int position1 = input.nextInt() - 1;

                    System.out.println("Please enter new money amount: ");
                    double newMoney = input.nextDouble();

                    //Check Realities

                    //Reality A
                    if(currentReality.equals("A")) {
                        //Update money
                        try {
                            if (newMoney < 0) {
                                throw new ArrayIndexOutOfBoundsException();
                            }
                            if(studentLineA.numStudents() == 0){
                                throw new EmptyLineException();
                            }
                            //getting the student at that position
                            Student studentMoneyUpdateA = studentLineA.getStudent(position1);

                            //setting his money amount to the new money amount
                            studentMoneyUpdateA.setMoney(newMoney);

                            //let user know that student's money has been updated
                            System.out.println(studentMoneyUpdateA.getName() + " now has $" + studentMoneyUpdateA.getMoney() + ". This was the result of a shady transaction.");

                        } catch (ArrayIndexOutOfBoundsException ex) {
                            System.out.println("Invalid index. Try again.");
                        } catch (NullPointerException ex) {
                            System.out.println("invalid index. Try again.");
                        } catch (EmptyLineException ex){
                            System.out.println("There are no students to serve lunch to. Mystery Meat Martha is sad.");
                        }
                    }
                    //Reality B
                    if(currentReality.equals("B")) {
                        //Update money
                        try {
                            if (newMoney < 0) {
                                throw new ArrayIndexOutOfBoundsException();
                            }
                           if (studentLineB.numStudents() == 0) {
                                throw new EmptyLineException();
                            }
                            //getting the student at that position
                            Student studentMoneyUpdateB = studentLineB.getStudent(position1);
                            //setting his money amount to the new money amount
                            studentMoneyUpdateB.setMoney(newMoney);

                            //let user know that student's money has been updated
                            System.out.println(studentMoneyUpdateB.getName() + " now has $" + studentMoneyUpdateB.getMoney() + ". This was the result of a shady transaction.");

                        } catch (ArrayIndexOutOfBoundsException ex) {
                            System.out.println("Invalid index. Try again.");
                        } catch (NullPointerException ex) {
                            System.out.println("invalid index. Try again.");
                        } catch (EmptyLineException ex) {
                            System.out.println("There are no students to serve lunch to. Mystery Meat Martha is sad.");
                        }
                    }
                    mainMenu();
                    break;
                case "q":
                    // Section Q (Quit)
                    System.out.println("You are now leaving the Middle School Lunch Line Simulator.");
                    System.out.println("We congratulate you on your decision to do something more productive with your time.");
                    System.exit(0);
                    break;
                default:
                    //Display wrong status, No Section was matched
                    System.out.println("Error: invalid status. Try again");
                    mainMenu();
            }
        }
    }

    /**
     * Method containing Menu.
     */
    public static void mainMenu() {
        //Menu
        System.out.println("Menu:");
        System.out.println("    A) Add a student to the line at the end");
        System.out.println("    B) Have the bully remove a student");
        System.out.println("    C) Have a new student cut a friend");
        System.out.println("    D) Duplicate this reality into the other reality");
        System.out.println("    E) Check if the realities are equal");
        System.out.println("    O) Switch to the other reality");
        System.out.println("    P) Print the current reality's lunch line");
        System.out.println("    Q) Quit middle school and move on to real life.");
        System.out.println("    S) Serve a student");
        System.out.println("    T) Have two students trade places");
        System.out.println("    U) Update a student's money amount");
        System.out.println();

        //prompt user to go to section desired
        System.out.println("please select an option: ");
    }
}
