import java.util.*;
public class FirstFit {
    public int ProcessNo,BlockNo,Process[],Block[],Allocated[];
    public Scanner sc=new Scanner(System.in);
    public void getdata()
    {
        System.out.print("Enter the number of processes : ");
        ProcessNo=sc.nextInt();
        System.out.print("Enter the number of Blocks : ");
        BlockNo=sc.nextInt();
        Process=new int[ProcessNo];
        Block=new int[BlockNo];
        for(int i=0;i<ProcessNo;i++)
        {
            System.out.print("Enter the siz e of process "+(i+1)+" : ");
            Process[i]=sc.nextInt();

        }
        for(int i=0;i<BlockNo;i++)
        {
            System.out.print("Enter the size of Block "+(i+1)+" : ");
            Block[i]=sc.nextInt();
        }
    }
    public void Allocate()
    {
        Allocated=new int[ProcessNo];
        for(int i=0;i<ProcessNo;i++)
        {
            Allocated[i]=-1;
        }
        for(int i=0;i<ProcessNo;i++)
        {
            for(int j=0;j<BlockNo;j++)
            {
                if(Block[j]>=Process[i])
                {
                    Allocated[i]=j;
                    Block[j]-=Process[i];
                    break;
                }
            }
        }
    }
    public void Display()
    {
        System.out.println("Process No\t"+"Process Size\t"+"Block allocated");
        for(int i=0;i<ProcessNo;i++)
        {
            System.out.print("P"+(i+1)+"\t\t"+Process[i]+"\t\t");
            if(Allocated[i]!=-1)
            {
                System.out.println((Allocated[i]+1));
            }
            else
            {
                System.out.print("NA");
            }
        }
    }
    public static void main(String[] args)
    {
        FirstFit obj=new FirstFit();
        obj.getdata();
        obj.Allocate();
        obj.Display();
    }

}
