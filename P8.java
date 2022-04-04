class Toy{
    private int serialNumber;
    private boolean isDefected;

    public Toy(int serialNumber, boolean isDefected){
        this.serialNumber = serialNumber;
        this.isDefected = isDefected;
    }

    public boolean isDefected(){
        return isDefected;
    }

    public int getSerial(){
        return serialNumber;
    }
}
public class P8 {
    public static void main(String[] args) {
        Toy t1 = new Toy(1, false);
        Toy t2 = new Toy(2, false);
        Toy t3 = new Toy(3, false);
        Toy t4 = new Toy(4, false);
        Toy t5 = new Toy(5, true);
        Toy t6 = new Toy(6, true);

        Toy[] toys = {t1,t2,t3,t4,t5,t6};

        search(toys,0,toys.length-1);
    }
    public static void search(Toy[] toys, int start, int end){
        int mid = start + (end-start)/2;
        if((end-start)==0 || (end-start)==1){
            if(toys[start].isDefected()){
                System.out.println(toys[start].getSerial());
            }
            else{
                System.out.println(toys[end].getSerial());
            }
        }
        else if(toys[mid].isDefected()){
            search(toys,start,mid);
        }
        else if(!toys[mid].isDefected()){
            search(toys,mid,end);
        }
    }
}
