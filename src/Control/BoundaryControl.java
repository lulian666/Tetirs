package Control;

import BlockBase.Block;
import BlockBase.Cell;

public class BoundaryControl
{
	//这个类用于判断是否触碰到边界
	public boolean isTouchingLeft(Block block, int[][] board)
	{
		//触左边条件1:只要有一个cell的column值==0
		boolean term1 = false;
		//触左边条件2:只要有一个lCell的左边的那格有方块，即board[row][column-1] == 1
		boolean term2 = false;
		//两个条件只要有一个成立就构成触左边界
		Cell[] cells = block.cells;
		Cell[] lCells = block.getlCells();
		for (int i = 0; i < cells.length; i++)
		{
			if (cells[i].getColumn() == 0)
			{
				term1 = true;
			}
		}
		for (int i = 0; i < lCells.length; i++)
		{
			if ((lCells[i].getColumn() > 0) && (board[lCells[i].getRow()][lCells[i].getColumn() - 1] == 1))
			{
				term2 = true;
			}
		}
		return (term1 || term2);
	}
	
	public boolean isTouchingRight(Block block, int[][] board)
	{
		//触左边条件1:只要有一个cell的column==9
		//触左边条件2:只要有一个cell的右边的那格有方块，即board[row][column+1] == 1
		//两个条件只要有一个成立就构成触左边界
		Cell[] cells = block.cells;
		Cell[] rCells = block.getrCells();
		for (int i = 0; i < cells.length; i++)
		{
			if (cells[i].getColumn() == 9)
			{
				return true;
			}
		}
		for (int i = 0; i < rCells.length; i++)
		{
			if (board[rCells[i].getRow()][rCells[i].getColumn() + 1] == 1)
			{
				return true;
			}
		}
		return false;
	}
	
	//是否触底的判断
	public boolean isTouchingBottom(Block block, int[][] board)
	{
		//触底条件1:只要有一个cell的row==19
		boolean term1 = false;
		//触底条件2:只要有一个bCell的下边那格有方块，即board[row+1][column] == 1
		boolean term2 = false;
		Cell[] cells = block.cells;
		Cell[] bCells = block.getbCells();
		for (int i = 0; i < cells.length; i++)
		{
			if (cells[i].getRow() == 19)
			{
				term1 = true;
			}
		}
		for (int i = 0; i < bCells.length; i++)
		{
			if ((bCells[i].getRow()<19) && (board[bCells[i].getRow() + 1][bCells[i].getColumn()] == 1))
			{
				term2 = true;
			}
		}
		return (term1 || term2);		
	}
}
