import java.util.*;

public class LRU {
  public int capacity, pages[], N, pagefault = 0, hit = 0; //number of pages memory can hold
  public Scanner sc = new Scanner(System.in);

  public void getdata() {
    System.out.println("Enter the capacity : ");
    capacity = sc.nextInt();
    System.out.println("Enter the number of pages to be allocated :  ");
    N = sc.nextInt();
    pages = new int[N];
    for (int i = 0; i < N; i++) {
      System.out.print("Enter the page id " + (i + 1) + " : ");
      pages[i] = sc.nextInt();
    }
  }

  public void Replacement() {
    HashSet<Integer> s = new HashSet<>(capacity);
    HashMap<Integer, Integer> indexes = new HashMap<>();
    for (int i = 0; i < N; i++) {
      if (s.size() < capacity) {
        if (!s.contains(pages[i])) {
          s.add(pages[i]);
          pagefault++;
        } else {
          hit++;
        }
        indexes.put(pages[i], i); //store recently used index of each page
      }
      else{
        if (!s.contains(pages[i]))
        {
            int lru=Integer.MAX_VALUE,val=Integer.MIN_VALUE;
            Iterator<Integer>itr=s.iterator();
            while(itr.hasNext())
            {
                int temp = itr.next();
                if (indexes.get(temp) < lru)
                    {
                        lru = indexes.get(temp);
                        val = temp;
                    }
                    
            }
            s.remove(val);
            indexes.remove(val);
            s.add(pages[i]);
            pagefault++;

        }
        else
        {
            hit++;
        }
        indexes.put(pages[i],i);

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
        LRU obj=new LRU();
        obj.getdata();
        obj.Replacement();
        obj.Display();
    }
}
