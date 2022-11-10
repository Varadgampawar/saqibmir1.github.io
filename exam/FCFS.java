import java.util.*;
public class FCFS{
    public int n,BT[],WT[],TAT[];
    public String ID[];
    public float avg_waiting_time,avg_tat;
    Scanner sc=new Scanner(System.in);
    public void getdata(){
        System.out.println("Enter the number of processes : ");
        n=sc.nextInt();
        BT=new int[n];
        ID=new String[n];
        for(int i=0;i<n;i++)
        {
            System.out.println("Enter the id for process "+(i+1)+":");
            ID[i]=sc.next();

            System.out.println("Enter the burst time for process "+(i+1));
            BT[i]=sc.nextInt();
        }
    }
    public void Allocation()
    {
        WT=new int[n];
        TAT=new int[n];
        WT[0]=0;
        avg_waiting_time=0;
        TAT[0]=BT[0];
        avg_tat=BT[0];
        for(int i=1;i<n;i++)
        {
            WT[i]=WT[i-1]+BT[i-1];
            TAT[i]=TAT[i-1]+BT[i];
            avg_waiting_time=avg_waiting_time+WT[i];
            avg_tat=avg_tat+TAT[i];
        }
    }
    public void Display()
    {
        System.out.println("\tPROCESS ID \tBURST TIME\tWAITING TIME\tTURNAROUNDTIME");
        for(int i=0;i<n;i++)
        {
            System.out.println("\t"+ID[i]+"\t\t\t"+BT[i]+"\t\t"+WT[i]+"\t\t"+TAT[i]);

        }
        System.out.println("Average waiting time is : "+avg_waiting_time/n);
        System.out.println("Average turn around time : "+avg_tat/n);
    }
    public static void main(String[] args)
    {
        FCFS obj = new FCFS();
        obj.getdata();
        obj.Allocation();
        obj.Display();
    }

};