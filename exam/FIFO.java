import java.util.*;

public class FIFO {
    public int capacity,pages[],N, pagefault=0,hit=0;//number of pages memory can hold
    public Scanner sc=new Scanner(System.in);
    public void getdata()
    {
        System.out.println("Enter the capacity : ");
        capacity=sc.nextInt();
        System.out.println("Enter the number of pages to be allocated :  ");
        N=sc.nextInt();
        pages=new int[N];
        for(int i=0;i<N;i++)
        {
            System.out.print("Enter the page id "+(i+1)+" : ");
            pages[i]=sc.nextInt();

        }
    }
    void Replacement()
    {
        // To represent set of current pages. We use
        // an unordered_set so that we quickly check
        // if a page is present in set or not    
        HashSet<Integer> s = new HashSet<>(capacity);
        Queue<Integer> indexes = new LinkedList<>() ;
        for(int i=0;i<N;i++)
        {
            if(s.size()<capacity)
            {

                if(!s.contains(pages[i]))
                {
                    s.add(pages[i]);
                    pagefault++;
                    indexes.add(pages[i]);
                }
                else{
                    hit++;
                }

            }
            else
            {
                if(!s.contains(pages[i]))
                {
                    int val=indexes.peek();
                    indexes.poll();
                    s.remove(val);
                    s.add(pages[i]);
                    indexes.add(pages[i]);
                    pagefault++;
                }
                else
                {
                    hit++;
                }
            }
        }
    }
    public void Display()
    {
        System.out.println("Page fault is : "+pagefault);
        System.out.println("Hit : "+hit);
    }
    public static void main(String[] args)
    {
        FIFO obj=new FIFO();
        obj.getdata();
        obj.Replacement();
        obj.Display();
    }
    
}
