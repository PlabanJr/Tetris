import java.util.Random;
import java.util.*;
class Tetris
{
    
    public static void main(String[] args)
    {
        Random rand = new Random();
        Shape[] shape = new Shape[4];
        Board board = new Board(20,30);

        initialize_board(board);
        generate_shapes(shape,rand.nextInt(7));
        play(shape,board);

        display_board(board,shape);
    }

    public static void display_board(Board board,Shape[] shape)
    {
        clsScr();
        System.out.println("\t    ~~~~TETRIS~~~\n");
        int idx = 0;
        for(int row=0;row<20;row++)
        {
            for(int col=0;col<30;col++)
            {
                if(idx < 4 && row == shape[idx].coord_x && col == shape[idx].coord_y)
                {
                    System.out.print("*");
                    idx++;
                }
                else
                {
                    System.out.print(board.container[row][col]);
                }
            }
            System.out.println();
        }   
    }

    public static void generate_shapes(Shape[] shape,int shape_no)
    {      
        int initial_x = 1;
        int initial_y = 18;
        switch(shape_no)
        {
            case 0:
                shape_T(shape,initial_x,initial_y);
                break;
            case 1:
                shape_L(shape,initial_x,initial_y);
                break;
            case 2:
                shape_S(shape,initial_x,initial_y + 1);
                break;
            case 3:
                shape_O(shape,initial_x,initial_y);
                break;
            case 4:
                shape_L_mirror(shape,initial_x,initial_y+2);
                break;
            case 5:
                shape_Z(shape,initial_x,initial_y);
                break;
            case 6:
                shape_I(shape,initial_x,initial_y+1);
                break;
        }
    }

    public static void initialize_board(Board board)
    {      
        for(int row = 0;row < 20;row++)
        {
            for(int col = 0;col < 30;col++)
            {
                if(row == 0)
                    board.container[row][col] = '.';
                else if(col == 0 || col == 30-1 || row == 20-1)
                    board.container[row][col] = '0';
                else
                    board.container[row][col] = ' ';
            }
        }
    }

    public static void shape_T(Shape[] shape,int initial_x,int initial_y)
    {
        int idx = 0;    
        for(idx = 0;idx < 3;idx++)
        {
            shape[idx] = new Shape();
            shape[idx].coord_x = initial_x;
            shape[idx].coord_y = initial_y++;
        }
        shape[idx] = new Shape();
        shape[idx].coord_x = initial_x+1;
        shape[idx].coord_y = initial_y - 2;
        shape[0].shape_no = 0;
        shape[0].shape = 'T';
    }

    public static void shape_T_1(Shape[] shape)
    {
        int idx = 0; 
        int x = 0;
        int y = 2;  
        for(idx = 0;idx < 4;idx++)
        {
            shape[idx].coord_x += x;
            shape[idx].coord_y += y;
            if(idx == 0)
            {
                shape[idx].shape_no = 1;
                shape[idx].shape = 'T';
                x++;
                y -= 2;
            }
            
            if(idx == 2)
            {
                y++;
            }
        }

        
    }

    public static void shape_T_2(Shape[] shape)
    {
        int idx = 0,y = 0;    
        shape[idx].shape_no = 2;
        shape[idx].shape = 'T';

        if(shape[1].coord_y == 1)
        {
            y = 1;
        }

        for(idx = 0;idx < 3;idx++)
        {
            shape[idx].coord_x += 1;
            shape[idx].coord_y = shape[idx].coord_y - 1 + y;
        }
        shape[idx].coord_y += y;
    }

    public static void shape_T_3(Shape[] shape)
    {
        int idx = 0; 
        int x = 1;
        int y = 1;  

        for(idx = 0;idx < 4;idx++)
        {
            shape[idx].coord_x -= x;
            shape[idx].coord_y -= y;
            if(idx == 0)
            {
                shape[idx].shape_no = 3;
                shape[idx].shape = 'T';
                y--;
            }
            
            if(idx == 2)
            {
                x--;
                y += 2;
            }
        }
    }

    public static void shape_O(Shape[] shape,int initial_x,int initial_y)
    {
        int idx = 0;    
        for(idx = 0;idx < 2;idx++)
        {
            shape[idx] = new Shape();
            shape[idx].coord_x = initial_x;
            shape[idx].coord_y = initial_y++;
        }
        initial_x++;
        initial_y -= 2;
        for(idx = 2;idx < 4;idx++)
        {
            shape[idx] = new Shape();
            shape[idx].coord_x = initial_x;
            shape[idx].coord_y = initial_y++;
        }
        shape[0].shape_no = 0;
        shape[0].shape = 'O';
    }
    public static void shape_L(Shape[] shape,int initial_x,int initial_y)
    {
        int idx = 0;    
        for(idx = 0;idx < 3;idx++)
        {
            shape[idx] = new Shape();
            shape[idx].coord_x = initial_x++;
            shape[idx].coord_y = initial_y;
        }
        shape[idx] = new Shape();
        shape[idx].coord_x = initial_x - 1;
        shape[idx].coord_y = initial_y + 1;
        shape[0].shape_no = 0;
        shape[0].shape = 'L';
    }

    public static void shape_L_1(Shape[] shape)
    {
        int idx = 0,y = 0; 
        if(shape[idx].coord_y == 27)
        {
            y = 1;
            shape[idx].coord_y -= y;
        }
        shape[idx].shape_no = 1;
        shape[idx].shape = 'L';   
        for(idx = 1;idx < 3;idx++)
        {
            shape[idx].coord_x -= idx;
            shape[idx].coord_y = shape[idx].coord_y + idx - y;
        }
        shape[idx].coord_x -= 1;
        shape[idx].coord_y = shape[idx].coord_y - 1 - y;
    }

    public static void shape_L_2(Shape[] shape)
    {
        int idx = 0; 
        shape[idx].shape_no = 2;
        shape[idx].shape = 'L';   
        for(idx = 2;idx < 4;idx++)
        {
            shape[idx].coord_x += 1;
            if(idx == 2)
                shape[idx].coord_y -= 1;
            else
                shape[idx].coord_y += 1;
        }
    }

    public static void shape_L_3(Shape[] shape)
    {
        int idx = 0,x = 1,y = 1,z = 0;
        
        if(shape[idx].coord_y == 1)
        {
            z = 1;
        }
        
        shape[idx].shape_no = 3;
        shape[idx].shape = 'L';   
        for(idx = 0;idx < 4;idx++)
        {
            shape[idx].coord_x += x;
            shape[idx].coord_y = shape[idx].coord_y + y + z;

            if(idx == 0)
            {
                x++;
                y = -2;
            }
            else
            {
                x--;
                y++;
            }
            
        }
    }

    public static void shape_L_mirror(Shape[] shape,int initial_x,int initial_y)
    {
        int idx = 0;    
        for(idx = 0;idx < 2;idx++)
        {
            shape[idx] = new Shape();
            shape[idx].coord_x = initial_x++;
            shape[idx].coord_y = initial_y;
        }
        initial_y--;
        for(idx = 2;idx < 4;idx++)
        {
            shape[idx] = new Shape();
            shape[idx].coord_x = initial_x;
            shape[idx].coord_y = initial_y++;
        }
        
    }

    public static void shape_S(Shape[] shape,int initial_x,int initial_y)
    {
        int idx = 0;   

        for(idx = 0;idx < 2;idx++)
        {
            shape[idx] = new Shape();
            shape[idx].coord_x = initial_x;
            shape[idx].coord_y = initial_y++;
        }
        initial_x++;
        initial_y -= 3;
        for(idx = 2;idx < 4;idx++)
        {
            shape[idx] = new Shape();
            shape[idx].coord_x = initial_x;
            shape[idx].coord_y = initial_y++;
        }
        shape[0].shape = 'S';
        shape[0].shape_no = 0;
    }

    public static void shape_S_1(Shape[] shape)
    {
        int idx = 0,x = 0,y = -1; 
        shape[idx].shape = 'S';
        shape[idx].shape_no = 1;   
        for(idx = 0;idx < 4;idx++)
        {
            if(idx == 1)
            {
                x = 1;
                y = -2;
            }
            else if(idx == 3)
            {
                y = 0;
                x = 1;
            }
            else if(idx == 2)
            {
                y = 1;
                x = 0;
            }
            
            shape[idx].coord_x += x;
            shape[idx].coord_y += y;
        }
    }

    public static void shape_Z(Shape[] shape,int initial_x,int initial_y)
    {
        int idx = 0;    
        for(idx = 0;idx < 2;idx++)
        {
            shape[idx] = new Shape();
            shape[idx].coord_x = initial_x;
            shape[idx].coord_y = initial_y++;
        }
        initial_x++;
        initial_y -= 1;
        for(idx = 2;idx < 4;idx++)
        {
            shape[idx] = new Shape();
            shape[idx].coord_x = initial_x;
            shape[idx].coord_y = initial_y++;
        }
        shape[0].shape = 'Z';
        shape[0].shape_no = 0;
    }

    public static void shape_Z_1(Shape[] shape)
    {
        int idx = 0;    
        for(idx = 0;idx < 4;idx++)
        {
            if(idx == 1 || idx == 3)
                shape[idx].coord_x = shape[idx].coord_x + 1;

            if(idx == 0) 
            {   
                shape[idx].coord_y = shape[idx].coord_y + 1;
                shape[0].shape_no = 1;
            }
            if(idx == 1)    
                shape[idx].coord_y = shape[idx].coord_y - 1;
            if(idx == 3)    
                shape[idx].coord_y = shape[idx].coord_y - 2;
        }
    }

    public static void shape_I(Shape[] shape,int initial_x,int initial_y)
    {
        int idx = 0;    
        for(idx = 0;idx < 4;idx++)
        {
            shape[idx] = new Shape();
            shape[idx].coord_x = initial_x++;
            shape[idx].coord_y = initial_y;
        }
        shape[0].shape = 'I';
        shape[0].shape_no = 0;
    }

    public static void shape_I_1(Shape[] shape)
    {  
        int x = 0, y = -1, z = 0;
        if(shape[0].coord_y == 1)
            z = 1;
        else if(shape[0].coord_y == 28 || shape[0].coord_y == 27)
            z = -2;
        for(int idx = 0;idx < 4;idx++)
        {
            shape[idx].coord_x = shape[idx].coord_x + x;
            shape[idx].coord_y = shape[idx].coord_y + y + z;
            x--;
            y++;
        }
        shape[0].shape = 'I';
        shape[0].shape_no = 1;
    }

    public static void play(Shape[] shape,Board board)
    {
        Random rand = new Random();
        int move_val = -1;
        Scanner input = new Scanner(System.in);
        char feed = '\0';
       
        do
        {
            display_board(board,shape);
            feed = input.next().charAt(0);
            move_val = move(shape,board,feed);
          
            
            if(move_val == 1)
            {
                put_shape_in_board(board,shape);
                generate_shapes(shape,rand.nextInt(7));
                remove_row(board);
            }
        }
        while(feed != 'x');
        
        input.close();
        
    }

    public static void remove_row(Board board)
    {
        int count = 0;

        for(int row = 18;row > 0 ;row--)
        {
            count = 0;
            for(int col = 28; col > 0;col--)
            {
                if(board.container[row][col] != ' ')
                    count++;
            }
            if(count == 28)
            {
                delete_the_row(board, row);
                shift_content(board,row);
            }
            else if(count == 0)
            {
                break;
            }
        }
    }

    public static void shift_content(Board board, int row)
    {
        int count = 0;
        for(row = row - 1;row >= 1;row--)
        {
            count = 0;
            for(int col = 28;col >= 1;col--)
            {
                board.container[row + 1][col] = board.container[row][col];
                if(board.container[row][col] == ' ')
                    count++;
            }
            if(count == 0)
                break;
        }
    }

    public static void delete_the_row(Board board, int row)
    {
        for(int col = 1;col <= 28;col++)
        {
            board.container[row][col] = ' ';
        }
    }

    public static void put_shape_in_board(Board board,Shape[] shape)
    {
        for(int idx = 0;idx < 4;idx++)
        {
            board.container[shape[idx].coord_x][shape[idx].coord_y] = '*';
        }
    }

    public static int move(Shape[] shape,Board board,char feed)
    {
        int move_val = -1;
        switch(feed)
        {
            case 's':
                move_val = check_move_down(shape,board);
                if(move_val != 1)
                    move_down(shape);
                break;
            case 'a':
                move_val = check_move_left(shape,board);
                if(move_val != 1)
                    move_left(shape);
                else
                    move_val = 2;
                break;
            case 'd':
                move_val = check_move_right(shape,board);
                if(move_val != 1)
                    move_right(shape);
                else
                    move_val = 2;
                break;
            case 'w':
                rotate_90_degree(shape);
                break;
            case 'x':
                move_val = 99;
                break;
        }

        return move_val;
    }

    public static void move_down(Shape[] shape)
    {
        for(int idx = 0;idx < 4;idx++)
        {
            shape[idx].coord_x++;
        }
    }

    public static int check_move_down(Shape[] shape,Board board)
    {
        int flag = 0;
        for(int idx = 0;idx < 4;idx++)
        {
            if(board.container[shape[idx].coord_x + 1][shape[idx].coord_y] != ' ')
            {
                flag = 1;
                break;
            }
        }   
        return flag;
    }

    public static void move_left(Shape[] shape)
    {
        for(int idx = 0;idx < 4;idx++)
        {
            shape[idx].coord_y--;
        }
    }

    public static int check_move_left(Shape[] shape,Board board)
    {
        int flag = 0;
        for(int idx = 0;idx < 4;idx++)
        {
            if(board.container[shape[idx].coord_x][shape[idx].coord_y - 1] != ' ')
            {
                flag = 1;
                break;
            }
        }   
        return flag;
    }

    public static void move_right(Shape[] shape)
    {
        for(int idx = 0;idx < 4;idx++)
        {
            shape[idx].coord_y++;
        }
    }

    public static int check_move_right(Shape[] shape,Board board)
    {
        int flag = 0;
        for(int idx = 0;idx < 4;idx++)
        {
            if(board.container[shape[idx].coord_x][shape[idx].coord_y + 1] != ' ')
            {
                flag = 1;
                break;
            }
        }   
        return flag;
    }

    public static void rotate_90_degree(Shape[] shape)
    {
        if(shape[0].shape == 'T')
        {
            if(shape[0].shape_no == 0)
            {
                shape_T_1(shape);
            }
            else if(shape[0].shape_no == 1)
            {
                shape_T_2(shape);
            }
            else if(shape[0].shape_no == 2)
            {
                shape_T_3(shape);
            }
            else if(shape[0].shape_no == 3)
            {
                if(shape[2].coord_y == 28)
                    shape_T(shape,shape[0].coord_x,shape[0].coord_y-1);
                else
                    shape_T(shape,shape[0].coord_x,shape[0].coord_y);
            }
        }
        else if(shape[0].shape == 'L')
        {
            if(shape[0].shape_no == 0)
            {
                shape_L_1(shape);
            }
            else if(shape[0].shape_no == 1)
            {
                shape_L_2(shape);
            }
            else if(shape[0].shape_no == 2)
            {
                shape_L_3(shape);
            }
            else if(shape[0].shape_no == 3)
            {
                shape_L(shape,shape[0].coord_x-1,shape[0].coord_y-1);
            }
        }
        else if(shape[0].shape == 'S')
        {
            if(shape[0].shape_no == 0)
            {
                shape_S_1(shape);
            }
            else if(shape[0].shape_no == 1)
            {
                shape_S(shape,shape[0].coord_x,shape[0].coord_y+1);
            }
        }
        else if(shape[0].shape == 'O')
        {
            if(shape[0].shape_no == 0)
            {
                shape_O(shape,shape[0].coord_x,shape[0].coord_y);
            }
        }
        else if(shape[0].shape == 'Z')
        {
            if(shape[0].shape_no == 0)
            {
                shape_Z_1(shape);
            }
            else if(shape[0].shape_no == 1)
            {
                if(shape[0].coord_y == 28)
                    shape_Z(shape,shape[0].coord_x,shape[0].coord_y-2);
                else
                    shape_Z(shape,shape[0].coord_x,shape[0].coord_y-1);
            }
        }
        else if(shape[0].shape == 'I')
        {
            if(shape[0].shape_no == 0)
            {
                shape_I_1(shape);
            }
            else if(shape[0].shape_no == 1)
            {
                shape_I(shape,shape[0].coord_x,shape[0].coord_y+1);
            }
        }

    }

    public static void clsScr()
    {
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
    }

}

class Board
{
    public char[][] container;
    
    public Board(int row,int col)
    {
        container = new char[row][col];
    } 
}

class Shape
{
    int coord_x,coord_y,shape_no;
    char shape;
}

