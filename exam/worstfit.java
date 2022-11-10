import java.util.*;
public class worstfit {
    public int ProcessNo,BlockNo,Process[],Block[],Allocated[];
    public Scanner sc=new Scanner(System.in);
    public void getdata()
    {
        System.out.println("Enter the number of processes : ");
        ProcessNo=sc.nextInt();
        System.out.println("Enter the number of Blocks : ");
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
            System.out.print("Enter the siz e of process "+(i+1)+" : ");
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
        for(int j=0;j<ProcessNo;j++)
        {
            int minIndex=-1;
            for(int i=0;i<BlockNo;i++)
            {
                if(Block[i]>=Process[j])
                {
                    if(minIndex==-1)
                    {
                        minIndex=i;
                    }
                
                    else if(Block[minIndex]<Block[i])
                    {
                        minIndex=i;
                    }
                    
                }

            }
            if(minIndex!=-1)
            {
                Allocated[j]=minIndex;
                Block[minIndex]-=Process[j];
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
        worstfit obj=new worstfit();
        obj.getdata();
        obj.Allocate();
        obj.Display();
    }
}
