import java.util.*;
public class Priority {
    public int N,BT[],TAT[],Priority[],WT[];
    public String ID[];
    float avg_tat=0,avg_wt=0;
    Scanner sc = new Scanner(System.in);
    public void getdata()
    {
        System.out.println("Enter the number of processes : ");
        N=sc.nextInt();
        BT=new int[N];
        ID=new String[N];
        Priority=new int[N];
        for(int i=0;i<N;i++)
        {
            System.out.println("Enter the id for process "+(i+1)+":");
            ID[i]=sc.next();

            System.out.println("Enter the burst time for process "+(i+1));
            BT[i]=sc.nextInt();
            System.out.println("Enter the priority for process "+(i+1)+":");
            Priority[i]=sc.nextInt();
            
        }
    }
    public void sort()
    {

        for(int i=0;i<N-1;i++)
        {
            int temp;
            String temp1;
            for(int j=0;j<N-1;j++)
            {
                if(Priority[j]>Priority[j+1])
                {
                    temp=Priority[j];
                    Priority[j]=Priority[j+1];
                    Priority[j+1]=temp;
                    temp1=ID[j];
                    ID[j]=ID[j+1];
                    ID[j+1]=temp1;
                }
            }
        }
    }
    public void calculate()
    {
        WT=new int[N];
        TAT=new int[N];
        WT[0]=0;
        TAT[0]=BT[0];
        avg_tat=TAT[0];
        for(int i=1;i<N;i++)
        {
            WT[i]=WT[i-1]+BT[i-1];
            TAT[i]=WT[i]+BT[i];
            avg_tat=avg_tat+TAT[i];
            avg_wt=avg_wt+WT[i];
        }

    }
    public void Display()
    {
        System.out.println("\tPROCESS ID \tBURST TIME\tWAITING TIME\tTURNAROUNDTIME");
        for(int i=0;i<N;i++)
        {
            System.out.println("\t"+ID[i]+"\t\t\t"+BT[i]+"\t\t"+WT[i]+"\t\t"+TAT[i]);

        }
        System.out.println("Average waiting time is : "+avg_wt/N);
        System.out.println("Average turn around time : "+avg_tat/N);
    }
    public static void main(String[] args){
        Priority obj=new Priority();
        obj.getdata();
        obj.sort();
        obj.calculate();
        obj.Display();
    }


}
