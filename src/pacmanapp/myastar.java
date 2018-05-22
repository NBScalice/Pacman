// Name:  Farma Nimal, with help from friend F. Arman Imal 
//import java.util.PriorityQueue;
import java.util.*;

public class myastar extends astar
{
    public myastar(int r, int c) { super(r,c); }

    // you need to override the search function in superclass astar
  /*  public coord search(int sy, int sx, int ty, int tx)
	{
		coord current = new coord(sy,sx);
		PriorityQueue<coord> Frontier = new PriorityQueue<coord>();
		coord[][] Status = new coord[ROWS][COLS];
		//Frontier.add(current);
		Status[current.y][current.x] = current;
		while (current.x!= tx|| current.y!= ty)
		{
			for (int dir = 0; dir < 6; dir++)
			{
				int ny = current.y + DY[dir];
			    int nx = current.x + DX[current.y%2][dir];
				if (ny >= 0&& ny< ROWS && nx >=0 && nx < COLS)
				{
					if (Status[ny][nx] == null || (Status[ny][nx].compareTo(Frontier.peek()))<=0)
					{
						coord next = makeneighbor(current,ny,nx,ty,tx);
						if (next != null)
						{
							Frontier.add(next);
							Status[next.y][next.x] = next;
						}
					}
				}
			}
			current.interior = true;
			current = Frontier.poll();
		}
		return current;
	}//search 1st */
	public coord search(int sy, int sx, int ty, int tx)
	{
		coord current = new coord(sy,sx);
		PriorityQueue<coord> Frontier = new PriorityQueue<coord>();
		coord[][]Status = new coord[ROWS][COLS];
		Status[current.y][current.x] = current;
		//Frontier.add(current);
		boolean stop = false;
		
		while (!stop)
		{
			
			for (int k=0 ; k< 6; k++)
			{
				int ny = current.y + DY[k];
				int nx = current.x + DX[current.y%2][k];
				if (ny >= 0 && ny < ROWS && nx >=0 && nx < COLS)
				{
					if (Status[ny][nx] == null ||(Status[ny][nx].compareTo(Frontier.peek()))<=0)
					{
						coord neighbor = makeneighbor(current,ny,nx,ty,tx);
						if (neighbor != null)
						{
							Frontier.add(neighbor);
							Status[neighbor.y][neighbor.x] = neighbor;
							if (ny==ty && nx == tx)
							{
								stop = true;
							}
						}
						
					}
				}
			}
				current.interior = true;
				current = Frontier.poll();
		}
		return current;
	}// search 2nd 
	
    
}//myastar class

/*
Where do I start?

Study the coord.java file and the coord class.  These are objects that
we are going to build a search tree. 

Study the astar class.  There's an array int[][] M with ROWS rows and
COLS columns.  The value of each M[i][j] is its terrain type (0=OPEN,
3=WATER, etc).

Study the DX and DY vectors in the Hexagon class.  These tell you how to
calculate the array coordinates of each of your six neighbors.

The search function takes a starting position sy,sx and a target position 
ty,tx.  Your tree should have as root a coord object with y=sy, x=sx
(and null for prev, 0 for knowndist and estcost).  You are to build a 
spanning tree until you find ty,tx.  You need to return a coord object
with y=ty and x=tx, and with prev pointer set so we can follow it back to
sy,sx.

   more help: I wrote this version of search that searches the map 
   randomly in class: just look at, do not copy (it won't work).

        coord current = new coord(sy,sx);
	while (current.y!=ty || current.x!=tx)
	    {
		//pick random direction
		int dir = (int)(Math.random()*6);
		int cy = current.y, cx = current.x;
		int ny = cy + DY[dir];
		int nx = cx + DX[cy%2][dir];
		if (nx>=0 && nx<COLS && ny>=0 && ny<ROWS)
		    {
			coord next = new coord(ny,nx);
			next.prev = current;
			current = next; 
		    }
		// else, loop back and pick another direction
	    }// main while
	return current;

*/
