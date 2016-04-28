/**
 * Created by TUHIN-SAYAN on 4/28/2016.
 */
import java.util.*;
public class TTT1 {

    int board[]=new int[9];
    int nextBestMove;
    int starts;
    /* comp = 1,,,,,, human = 2; */
    public int score()
    {
        int winner = 0;
        for(int i=0;i<3;i++)
        {
            if(won(board[0+i],board[3+i],board[6+i])){
                winner = board[0+i];
                break;
            }
            if(won(board[0+3*i],board[1+3*i],board[2+3*i])){
                winner = board[0+3*i];
                break;
            }
        }
        if(winner==0 && won(board[0],board[4],board[8]))
            winner = board[0];
        if(winner==0 && won(board[2],board[4],board[6]))
            winner = board[2];

        if(winner==1)
            return 10;
        else if(winner==2)
            return -10;
        else
            return 0;
    }

    boolean won(int a, int b, int c)
    {
        return a != 0 && a == b && b == c;
    }

    int isGameOver()
    {
        int flag=0;
        int s = score ();
        if(s!=0)
            return s;
        for(int i=0;i<9;i++)
        {
            if(board[i]==0)
            {
                flag=1;
                break;
            }
        }
        if(flag==0)
            return 0;
        return 5;
    }

    int minimax(int player,int depth)
    {
        int s = isGameOver();
        if(s==10||s==-10||s==0)
            return s;
        int opp = (player==1)?2:1;
        int bestMove=-1;
        int bestScore = (player==1)?-11:11;
        for(int i=0;i<9;i++)
        {
            if(board[i]==0)
            {
                board[i]=player;
                int score = minimax(opp,depth+1);
                if ((player==1 && score>bestScore)|| (player ==2 && score<bestScore))
                {

                    bestScore=score;
                    bestMove = i;
                }
                board[i]=0;
            }
        }
        if(depth==0)
            nextBestMove = bestMove;
        return bestScore;
    }

    void winner(int igo){
        if(igo==0)
            System.out.println("DRAW");
        else if(igo==10)
            System.out.println("I WIN");
        else
            System.out.println("YOU WIN");
    }

    public void controller () throws Exception
    {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner br = new Scanner(System.in);
        System.out.println("Enter 1 for starting first, 2 for Starting second");
        int choice = br.nextInt();
        starts = choice==1?2:1;
        displayBoard();
        while(true)
        {
            if(choice==1)
            {
                int move = br.nextInt();
                board[move-1] = 2;
            }
            else{
                minimax(1,0);
                if(nextBestMove!=-1)
                    board[nextBestMove] = 1;
            }
            displayBoard();
            int igo = isGameOver();

            if(igo%10==0)
            {
                winner(igo);
                break;
            }
            if(choice==2)
            {
                int move = br.nextInt();
                board[move-1] = 2;
            }
            else{
                minimax(1,0);
                if(nextBestMove!=-1)
                    board[nextBestMove] = 1;
            }
            displayBoard();
            igo = isGameOver();

            if(igo%10==0)
            {
                winner(igo);
                break;
            }

        }
    }

    void displayBoard(){
        String line = "---|---|---\n";
        String strings[] = new String[3];
        for(int i=0; i<3; i++)
            strings[i] = " "+getPlayerChar(board[i*3+0])+" | "+getPlayerChar(board[i*3+1])+" | "+getPlayerChar(board[i*3+2])+" \n";
        System.out.println("\n"+strings[0]+line+strings[1]+line+strings[2]);
    }

    char getPlayerChar(int c){
        if(c==0)
            return ' ';
        else if(starts==c)
            return 'X';
        else
            return 'O';
    }

    public static void main(String... args)throws Exception{
        TTT1 obj = new TTT1();
        obj.controller();
    }

}
