import java.util.Scanner;

class Calculator{


static String stack[]=new String[50]; 
static int top=-1;


static void expStack(String exp){
    String s1="";
  top=-1;
for(int i=0;i<exp.length();i++){
   if(exp.charAt(i)>=48&&exp.charAt(i)<=57){
    s1+=exp.charAt(i);
   }
   else
   {
    stack[++top]=s1;
    stack[++top]=""+exp.charAt(i);
    s1="";
   }
}
stack[++top]=s1;

}

static int calculate(String exp){
    
    int a;
    expStack(exp);
    String temp[]=new String[50];
    int t=-1;
    for(int i=0;i<top+1;i++)
    {
   
        if(stack[i].equals("*"))
        {
            a=Integer.parseInt(temp[t--])*Integer.parseInt(stack[i+1]);
            
            temp[++t]=""+a;
            i++;

        }
        else
        if(stack[i].equals("/"))
        {
            a=Integer.parseInt(temp[t--])/Integer.parseInt(stack[i+1]);
            
            temp[++t]=""+a;
            i++;
            
        }
        else
            temp[++t]=stack[i];
        
    }
    stack=temp;
    
    int result=Integer.parseInt(stack[0]);
    for(int i=1;i<t;i++)
    {
        
        if(stack[i].equals("+"))
        {
           
            result+=Integer.parseInt(stack[i+1]);
            
                
        }
        else
        if(stack[i].equals("-"))
        {
            result-=Integer.parseInt(stack[i+1]);
            }
    }
    
    return result;


}


}
