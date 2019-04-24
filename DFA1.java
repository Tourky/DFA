package dfa1;
import java.util.Scanner;
public class DFA1 
{   // set total number of states in the DFA
    static final int Total_states=2;
    // set the  number of final states
    static final int final_states=1; 
    // set how many character in the language
    static final int alphabet_char=2; 
    // (Error) zero flag indicates that the input doesn't belong to DFA
    static final int Error=0; 
    /*Not_reached) one flag indicates that the machine didn't reache 
    the final state with the given string*/
    static final int NoT_reached=1;
    /*(reached) two flag indicates that the machine reached 
    successfully the final state and the string is acccepted*/
    static final int reached=2;
    /*for more example and explanation about enum see the follow link 
    https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
    */
    enum state{q0,q1};// indicate the state machine in DFA
    enum input{a,b}; // indicate the input for the transition table
    static int Accepted_states[]=  {state.q1.ordinal()};// set the accepted states to q1
    static int current_state= state.q0.ordinal();// set the current state to qo
   /*for more explanation about enum see the follow link 
    https://docs.oracle.com/javase/7/docs/api/java/lang/Enum.html
    */
    static char alphabet[]={'a','b'}; // set the characters that produces the language 
    /* according to the above parameter the Transition table 
    will contains two state with two input character */
    static int [][]Transition_Table= new int[2][2];
    // set the values for the transition table according the given DFA
    public static void build_Transition_Table()
    {
        Transition_Table[state.q0.ordinal()][input.a.ordinal()]=state.q1.ordinal();
        Transition_Table[state.q0.ordinal()][input.b.ordinal()]=state.q0.ordinal();
        Transition_Table[state.q1.ordinal()][input.a.ordinal()]=state.q1.ordinal();
        Transition_Table[state.q1.ordinal()][input.b.ordinal()]=state.q0.ordinal();
    }
    /*DFA method receive one character per a time to check whether 
    the intersect of current state and input meet an accept state*/
    public static int DFA( char current_char)
    {
        
        int i , j;
        for(j=0;j<alphabet_char;j++)
            if(current_char==alphabet[j])//get the position of the input
                break;
        if (j==alphabet_char)
            return Error;// return Error flag if any character other than a or b
        for(i=0;i<final_states;i++)// check it meet an accept state
            if ((current_state=Transition_Table[current_state][j])==Accepted_states[i])
                return reached;
        return NoT_reached;
    }
    public static void main(String[] args) 
    {
        Scanner input= new Scanner(System.in);
        char current_char; int result=0;
        build_Transition_Table();
        System.out.print("Enter the string of a's and b's:");
        String reading = input.next();
        for(int i=0;i<reading.length();i++)
        {
            current_char=reading.charAt(i);
            if((result=DFA(current_char))==Error)
            {
                 System.out.println("unknow char "+ current_char); 
                break;
            }
        }
        switch(result)
            {
                case NoT_reached:System.out.println("Not accepted "); 
                break;
                case reached:System.out.println(" Accepted ");
                break;
                default: System.err.println("Error");
            }           
    }
}
