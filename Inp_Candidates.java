import java.io.*;
import java.util.*;
class Inp_Candidates
{
    Scanner sc=new Scanner(System.in);
    void Private()throws IOException
    {
        System.out.println("Type \"done\" When All The Candidates Have Been Registered");
        String str="";
        boolean flag=true;
        int n,c=0;
        ArrayList<String> ob=new ArrayList<String>(); //ArrayList to allow user to enter n number of candidates without knowing n
        while(flag)
        {
            c++;
            System.out.print("Enter Candidate Name : ");
            str=Integer.toString(c)+") " +sc.nextLine().trim();
            if((str.substring(3)).equalsIgnoreCase("\"done\"")) //voting is complete
            flag=false;
            if((str.substring(3)).equalsIgnoreCase("\"end\"")){ //user wishes to terminate election
                System.out.println("The Election Has Been Terminated");
                System.exit(0);
            }
            if(flag==true)
            ob.add(str);
        }
        n=ob.size();
        if(n==0){
            System.out.println("No candidates entered\nend");
            System.exit(0);
            
        }
        String arr[] =new String[n]; //Fixed array to store Candidates
        System.out.println("\nThe Candidates Are :");
        for(int i=0;i<n;i++)
        {
            arr[i]=ob.get(i); //Converting ArrayList to fixed array
            System.out.println(arr[i]);
        }
        System.out.println("\nVote By Typing The Candidate's Name or Serial No.");
        //calling functions to enable private elections
        Vote_in_and_calc ob1=new Vote_in_and_calc();
        double votes[]=ob1.count(n,arr);
        int win[]=ob1.votecalc(n,votes);
        ob1.display(win,votes,arr,n);
    }
    void Political()throws IOException
    {
        String str="",str2="",str3="";
        int n;
        System.out.println("Type \"done\" When All The Candidates Have Been Registered");
        ArrayList<String> candidate=new ArrayList<String>();
        ArrayList<Integer> age=new ArrayList<Integer>();
        ArrayList<String> party=new ArrayList<String>(); //ArrayLists to allow user to enter n number of candidates without knowing n
        while(true)
        {
            System.out.print("Enter Candidate Name : ");
            str=sc.nextLine().trim();
            if(str.equalsIgnoreCase("\"done\""))//voting is complete
            break;
            if(str.equalsIgnoreCase("\"end\"")){ //user wishes to terminate election
                System.out.println("The Election Has Been Terminated");
                System.exit(0);
            }
            System.out.print("Enter Political Party Name : ");
            str2=sc.nextLine();
            if(str2.equalsIgnoreCase("\"end\"")){ //user wishes to terminate election
                System.out.println("The Election Has Been Terminated");
                System.exit(0);
            }
            if(str2.equalsIgnoreCase("\"done\"")){ //voting is complete
                break;
            }
            System.out.print("Enter Candidate Age : ");
            str3=sc.nextLine();
            System.out.println("");
            if(str3.equalsIgnoreCase("\"done\"")) //voting is complete
            break;
            if(str3.equalsIgnoreCase("\"end\"")){ //user wishes to terminate election
                System.out.println("The Election Has Been Terminated");
                System.exit(0);
            }
            candidate.add(str);
            party.add(str2);
            age.add(Integer.parseInt(str3));
        }
        n=candidate.size();
        for(int i=0;i<n;i++)
        {
            if((age.get(i))<25) //checking for age since members of parliament have to be over 25 years of age.
            {
                System.out.println(candidate.get(i)+" Is Under The Age of 25 And Hence Cannot Stand For Election.");
                candidate.remove(i);
                age.remove(i);
                party.remove(i);
                n--;
                i--;
            }
        }
        if(n==0){
            System.out.println("No candidates entered\nend");
            System.exit(0);
        }
        for(int i=0;i<n;i++)
        {
            str=Integer.toString(i+1)+") "+candidate.get(i);
            candidate.set(i,str);
        }
        String candid[] =new String[n];
        int aged[]=new int[n];
        String Party[]=new String[n]; //Fixed arrays to store Candidates and details
        System.out.println("\nThe Candidates Are :");
        for(int i=0;i<n;i++)
        {
            candid[i]=candidate.get(i);
            aged[i]=age.get(i);
            Party[i]=party.get(i);
            System.out.println(candid[i]+"\nAge :- "+aged[i]+"\nParty :- "+Party[i]+"\n");
            //Converting ArrayList to fixed array
        }
        System.out.println("Vote By Typing The Candidate's Name or Serial No.");
        //calling functions to enable political elections
        
        Vote_in_and_calc ob1=new Vote_in_and_calc();
        double votes[]=ob1.count(n,candid);
        int win[]=ob1.votecalc(n,votes);
        ob1.display(win,votes,candid,n,Party,aged);
    }
}