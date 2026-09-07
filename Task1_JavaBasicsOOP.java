
import java.util.ArrayList;
import java.util.List;

/* ---------- ABSTRACTION: abstract class ---------- */
abstract class Shape {
    protected String name;

    public Shape(String name) {
        this.name = name;
    }

    public abstract double area(); // must be implemented by subclasses

    public void display() {
        System.out.printf("%-10s -> Area = %.2f%n", name, area());
    }
}

/* ---------- ABSTRACTION: interface ---------- */
interface Describable {
    String describe();

    default void printDescription() {
        System.out.println("Description: " + describe());
    }
}

/* ---------- INHERITANCE + OVERRIDING ---------- */
class Circle extends Shape implements Describable {
    private double radius;

    public Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public String describe() {
        return "A circle with radius " + radius;
    }
}

class Rectangle extends Shape implements Describable {
    private double length, breadth;

    public Rectangle(double length, double breadth) {
        super("Rectangle");
        this.length = length;
        this.breadth = breadth;
    }

    @Override
    public double area() {
        return length * breadth;
    }

    @Override
    public String describe() {
        return "A rectangle " + length + " x " + breadth;
    }
}

/* ---------- INHERITANCE + RUNTIME POLYMORPHISM ---------- */
class Animal {
    protected String animalName;

    public Animal(String animalName) {
        this.animalName = animalName;
    }

    public void sound() {
        System.out.println(animalName + " makes a generic sound.");
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void sound() {
        System.out.println(animalName + " says: Woof!");
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void sound() {
        System.out.println(animalName + " says: Meow!");
    }
}

/* ---------- COMPILE-TIME POLYMORPHISM: overloading ---------- */
class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public double add(double a, double b) {
        return a + b;
    }
}

/* ---------- ENCAPSULATION + static & final ---------- */
class BankAccount {
    private static final double INTEREST_RATE = 4.5; // shared by all objects
    private final String accountNumber;               // set once, never changes
    private String holderName;
    private double balance;

    public BankAccount(String accountNumber, String holderName) {
        this(accountNumber, holderName, 0.0); // constructor chaining
    }

    public BankAccount(String accountNumber, String holderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance for " + holderName);
            return;
        }
        balance -= amount;
    }

    public static double getInterestRate() {
        return INTEREST_RATE;
    }

    public void printStatement() {
        System.out.printf("A/C: %s | Holder: %-10s | Balance: %.2f%n",
                accountNumber, holderName, balance);
    }
}

/* ---------- MAIN: demonstrates everything ---------- */
public class Task1_JavaBasicsOOP {
    public static void main(String[] args) {

        System.out.println("========== INHERITANCE + RUNTIME POLYMORPHISM ==========");
        List<Animal> animals = new ArrayList<>();
        animals.add(new Dog("Tommy"));
        animals.add(new Cat("Whiskers"));
        animals.add(new Animal("Generic Animal"));
        for (Animal a : animals) {
            a.sound();
        }

        System.out.println("\n========== ABSTRACTION (abstract class + interface) ==========");
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(5));
        shapes.add(new Rectangle(4, 6));
        for (Shape s : shapes) {
            s.display();
            if (s instanceof Describable) {
                Describable d = (Describable) s;
                d.printDescription();
            }
        }

        System.out.println("\n========== COMPILE-TIME POLYMORPHISM (overloading) ==========");
        Calculator calc = new Calculator();
        System.out.println("add(2, 3)     = " + calc.add(2, 3));
        System.out.println("add(2, 3, 4)  = " + calc.add(2, 3, 4));
        System.out.println("add(2.5, 3.5) = " + calc.add(2.5, 3.5));

        System.out.println("\n========== ENCAPSULATION + static & final ==========");
        BankAccount acc1 = new BankAccount("AC1001", "Ajeet");
        BankAccount acc2 = new BankAccount("AC1002", "Riya", 5000);

        acc1.deposit(2000);
        acc1.withdraw(500);
        acc2.deposit(1500);

        acc1.printStatement();
        acc2.printStatement();
        System.out.println("Bank interest rate (static, shared): " + BankAccount.getInterestRate() + "%");
    }
}
