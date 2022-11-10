import java.util.*;

public class RoundRobin {
    public Scanner sc=new Scanner(System.in);
    public int WT[],BT[],RT[],AT[],TAT[],N,quantum;
    public float avg_tat=0,avg_wt=0;
    public String ID[];
    public void getdata()
    {
        System.out.println("Enter the number of processes : ");
        N=sc.nextInt();
        BT=new int[N];
        WT=new int[N];
        RT=new int[N];
        // AT=new int[N];
        ID=new String[N];
        TAT=new int[N];
        for(int i=0;i<N;i++)
        {
            System.out.println("Enter the id for process "+(i+1)+":");
            ID[i]=sc.next();
            System.out.println("Enter the burst time for process "+(i+1));
            BT[i]=sc.nextInt();  
            // System.out.println("Enter the Arrival time for process "+(i+1));
            // AT[i]=sc.nextInt();
            RT[i]=BT[i];
            WT[i]=0;          
        }
        System.out.println("Enter the number of processes : ");
        quantum=sc.nextInt();

    }
    void calculate()
    {
        int total=0;
        while(true)
        {
            boolean done=true;

            for(int i=0;i<N;i++)
            {
                if(RT[i]>0)
                {
                    done=false;
                    if(RT[i]>quantum)
                    {
                        total+=quantum;
                        RT[i]-=quantum;
                    }
                    else{
                        total+=RT[i];
                        WT[i]=total-BT[i];
                        RT[i]=0;
                    }
                }
            }
            if(done==true)
                break;
        }
        for(int i=0;i<N;i++)
        {
            TAT[i]=WT[i]+BT[i];
            avg_tat=avg_tat+TAT[i];
            avg_wt=avg_wt+WT[i];
        }
        
            
        

    }
    void Display()
    {
        System.out.println("\tPROCESS ID \tBURST TIME\tWAITING TIME\tTURNAROUNDTIME");
        for(int i=0;i<N;i++)
        {
            System.out.println("\t"+ID[i]+"\t\t\t"+BT[i]+"\t\t"+WT[i]+"\t\t"+TAT[i]);

        }
        System.out.println("Average waiting time is : "+avg_wt/N);
        System.out.println("Average turn around time : "+avg_tat/N);
    }
    public static void main(String[] args) throws Exception {
        
        RoundRobin obj=new RoundRobin();
        obj.getdata();
        obj.calculate();
        obj.Display();

    }

};
