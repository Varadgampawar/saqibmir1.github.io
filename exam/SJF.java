import java.util.*;
public class SJF {
    public int WT[],AT[],BT[],TAT[],N;
    int temp;
    String temp1;
    public float avg_tat,avg_waiting_time;
    Scanner sc = new Scanner(System.in);
    String ID[]=new String[N];
    public void getdata(){
        System.out.println("Enter the number of processes : ");
        N=sc.nextInt();
        BT=new int[N];
        AT=new int[N];
        ID=new String[N];
        for(int i=0;i<N;i++)
        {
            System.out.println("Enter the id for process "+(i+1)+":");
            ID[i]=sc.next();

            System.out.println("Enter the burst time for process "+(i+1));
            BT[i]=sc.nextInt();
            System.out.println("Enter the Arrival time for process "+(i+1));
            AT[i]=sc.nextInt();
        }
    }
    public void Sort(){
        for(int i=0;i<N-1;i++)
        {
            for(int j=0;j<N-i-1;j++){
                if(BT[j]>BT[j+1]){
                    temp=BT[j];
                    BT[j]=BT[j+1];
                    BT[j+1]=temp;
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
        avg_waiting_time=0;
        TAT[0]=BT[0];
        avg_tat=BT[0];
        for(int i=1;i<N;i++)
        {
            WT[i]=WT[i-1]+BT[i-1];
            TAT[i]=TAT[i-1]+BT[i];
            avg_waiting_time=avg_waiting_time+WT[i];
            avg_tat=avg_tat+TAT[i];
        }

    }
    public void Display()
    {
        System.out.println("\tPROCESS ID \tBURST TIME\tWAITING TIME\tTURNAROUNDTIME\tARRIVALTIME");
        for(int i=0;i<N;i++)
        {
            System.out.println("\t"+ID[i]+"\t\t\t"+BT[i]+"\t\t"+WT[i]+"\t\t"+TAT[i]+"\t\t"+AT[i]);

        }
        System.out.println("Average waiting time is : "+avg_waiting_time/N);
        System.out.println("Average turn around time : "+avg_tat/N);
    }
    public static void main(String[] args){
        SJF obj=new SJF();
        obj.getdata();
        obj.Sort();
        obj.calculate();
        obj.Display();
    }

};
