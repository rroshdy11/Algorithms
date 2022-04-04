/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Dell
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;

/**
 *
 * @author Dell
 */
class Node{
   public int key;
   public Node above;
   public Node below;
   public Node next;
   public Node prev;
   
   public Node(int key){
       this.key=key;
       this.below=null;
       this.next=null;
   }
   }

 class skiplist {
      private Node head;
      private Node tail;
      private final int neg_inf=Integer.MIN_VALUE;
      private final int pos_inf=Integer.MAX_VALUE;
      
      private int hofskiplist=0; 
      public Random random=new Random();
      public skiplist(){
           head= new Node(neg_inf);
           tail=new Node(pos_inf);
           head.next=tail;
           tail.prev=head;
      }
      public Node search(int key){
         Node current=head;
        boolean found = false;

        while (current.below!=null)
        {
            current=current.below;
            while(current.next!=null&&current.next.key<=key)
            {
                current=current.next;
                
            }
        }
        return current;
}
      public Node insert(int key){
          Node position=search(key);
          Node temp;
          
          int level=-1;
          int number_of_heads=-1;
          
          if(position.key==key){
              return position;
          }
          
          do {
              number_of_heads++;
              level++;
              
              Increaselevel(level);
              
              temp=position;
              
              while(position.above==null){
                  position=position.prev;
              }
              
              position=position.above;
              
              temp=insertAfterAbove(position,temp,key);
          }while(random.nextBoolean()==true);
          return temp;
          
          
      }
      private void Increaselevel(int level)
      {
          if(level >=hofskiplist)
          {
              hofskiplist++;
              addlevel();
          }
      }
      private Node insertAfterAbove(Node position, Node temp,int key)
      {
          Node newNode =new Node (key);
          Node nodeBefore=position.below.below;
          
          setBeforeandAfter(temp,newNode);
           setAboveAndBelow(position,key,newNode,nodeBefore);
           return newNode;
      }
      
      private void setBeforeandAfter(Node temp ,Node newNode)
      {
          newNode.next=temp.next;
          newNode.prev=temp;
          temp.next.prev=newNode;
          temp.next=newNode;
      }
      private void setAboveAndBelow(Node position ,int key,Node newNode,Node nodeBefore)
      {
          if(nodeBefore!=null)
          {
              while (true ){
                  if(nodeBefore.next.key!=key)
                  {
                      nodeBefore=nodeBefore.next;
                  }
                  else
                  {
                      break;
                  }
              }   
              newNode.below=nodeBefore.next;
              nodeBefore.next.above=newNode;
          }
          if(position!=null){
            if(position.next.key==key)
            {
               newNode.above=position.next; 
            }
          }
          
      }
      
      private void addlevel()
      {
          Node newhead=new Node (neg_inf);
          Node newtail=new Node(pos_inf);
          
          newhead.next=newtail;
          newhead.below=head;
          
          newtail.prev=newhead;
          newtail.below=newtail;
          
          head.above=newhead;
          tail.above=newtail;
          
          head =newhead;
          tail=newtail;
      }
      
      public Node delete(int key)
      {
         Node node_to_delete=search(key);
         if(node_to_delete.key!=key)
         {
             return null;
         }
         
         removeRef(node_to_delete);
         while(node_to_delete!=null)
         {
             removeRef(node_to_delete);
             if(node_to_delete.above!=null)
             {
                 node_to_delete=node_to_delete.above;
             }
             else
             {
                 break;
             }
         }
         return node_to_delete;
      }

     private void  removeRef(Node node_to_delete) 
     {
        Node afternodeTodelete=node_to_delete.next;
        Node beforenodeTodelete=node_to_delete.prev;
        
        beforenodeTodelete.next=afternodeTodelete;
        afternodeTodelete.prev=beforenodeTodelete;
     }
     
     public void printskiplis(){
         Node current =head.below;
         Node highestlevel=current;
         int level =hofskiplist-1;
         while(highestlevel!=null)
         {
             
             System.out.print("\nlevel :"+level+"\n");
                 
             while(current!=null)
             {
                 if(current.key!=neg_inf && current.key!=pos_inf)
                 {
                 System.out.print(current.key);
                 if(current.next!=null && current.key!=pos_inf)
                 {
                     System.out.print(",");
                 }
                 }
                 current=current.next; 
             }
             highestlevel=highestlevel.below;
             current=highestlevel;
             level--;
             
         }
         
     }
      public void searchsteps(int key){
         Node current=head.below;
        boolean found = false;
        int numsteps=0;
        while (current.below!=null)
        {
            current=current.below;
            
            while(current.next!=null&&current.next.key<=key)
            {
                current=current.next;
                numsteps++;
                if(current.key==key)
                {
                    found=true;
                    break;
                }
                
            }
        }
         if(found)
           {
                System.out.println("skipList.search("+key+")-->"+numsteps); 
           }
         else
         {
              System.out.println("skipList.search("+key+")-->"+-1);
         }
    }
    public int getHigh()
    {
        return hofskiplist;
    }
    public void printlayer(int num)
    {
        if(num>=hofskiplist)
        {
            System.out.println("There is no such a level ");
        }
        else
        {
          Node current=head;
          Node highestlevel=current;
         int level =(hofskiplist)-num;
         while(level>0)
         {
             highestlevel=highestlevel.below;
             current=highestlevel;
             level--;
         }
         System.out.print("\nlevel :"+num+"\n");
                 
             while(current!=null)
             {
                 if(current.key!=neg_inf && current.key!=pos_inf)
                 {
                 System.out.print(current.key);
                 if(current.next!=null && current.key!=pos_inf)
                 {
                     System.out.print(",");
                 }
                 }
                 current=current.next; 
             }
        }
        System.out.print("\n");
    }
      
}

public class P5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        skiplist skp=new skiplist();
        skp.insert(2);
        skp.insert(10);
        skp.insert(31);
        skp.insert(15);
        skp.insert(16);
        skp.insert(71);
        skp.insert(86);
        skp.insert(89);
        skp.insert(91);
        skp.insert(96);
        
        skp.searchsteps(86);
        skp.searchsteps(140);
        skp.searchsteps(16);
        
        System.out.println("Number of levels in the skiplist -->"+skp.getHigh());
        
        skp.printskiplis();
        System.out.println("\n");
        
        skp.printlayer(1);
        skp.printlayer(0);
    }
    
}
