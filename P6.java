import java.util.*;
class Employee{
    private String ID;
    private String name;
    private String dept;
    private int index;
    private static int count = 0;

    public Employee(String ID, String name, String dept){
        this.ID = ID;
        this.name = name;
        this.dept = dept;
        index = count;
        count++;
    }

    public String getID(){
        return ID;
    }

    public int getIndex(){
        return index;
    }
}
class Transaction{
    private String SoldProduct;
    private String SoldBy;
    private String SoldTo;

    public Transaction(String SoldProduct, String SoldBy, String SoldTo){
        this.SoldProduct = SoldProduct;
        this.SoldBy = SoldBy;
        this.SoldTo = SoldTo;
    }

    public String getSoldBy(){
        return SoldBy;
    }
}
public class P6 {
    public static void main(String[] args){
        LinkedHashMap<String,Employee> empMap = new LinkedHashMap<>();

        Employee emp1 = new Employee("1","Karim","IT");
        Employee emp2 = new Employee("2","Ziad","HR");
        Employee emp3 = new Employee("3","Reda","PR");

        empMap.put(emp1.getID(),emp1);
        empMap.put(emp2.getID(),emp2);
        empMap.put(emp3.getID(),emp3);
        
        
        ArrayList<Transaction> txn = new ArrayList<>();

        Transaction txn1 = new Transaction("A", "1", "company 1");
        Transaction txn2 = new Transaction("B", "2", "company 2");
        Transaction txn3 = new Transaction("C", "3", "company 3");
        Transaction txn4 = new Transaction("D", "2", "company 4");
        Transaction txn5 = new Transaction("E", "1", "company 5");
        Transaction txn6 = new Transaction("F", "3", "company 6");

        txn.add(txn1);
        txn.add(txn2);
        txn.add(txn3);
        txn.add(txn4);
        txn.add(txn5);
        txn.add(txn6);

        ArrayList<Integer> joins = new ArrayList<>();
        for(int i=0; i<txn.size(); i++){
            if(empMap.containsKey(txn.get(i).getSoldBy())){
                joins.add(empMap.get(txn.get(i).getSoldBy()).getIndex());
            }
        }
        System.out.println(joins);
    }
}
