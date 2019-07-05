package Blocks;

import BlockBase.Block;
import BlockBase.Cell;

public class Block4 extends Block
{
	public Cell[] lCells = new Cell[3];
	public Cell[] rCells = new Cell[3];
	public Cell[] bCells = new Cell[2];
    public Cell[] tCells = new Cell[2];
	
	private int cenx;
	private int ceny;
	
	public Block4()
	{
		cenx = 1;
		ceny = 4;
		cells[0] = new Cell(cenx - 1,ceny);
		cells[1] = new Cell(cenx,ceny - 1);
		cells[2] = new Cell(cenx,ceny);
		cells[3] = new Cell(cenx + 1,ceny);

		lCells[0] = new Cell(cenx - 1,ceny);
		lCells[1] = new Cell(cenx,ceny - 1);
		lCells[2] = new Cell(cenx + 1,ceny);
		
		rCells[0] = new Cell(cenx - 1,ceny);
		rCells[1] = new Cell(cenx,ceny);
		rCells[2] = new Cell(cenx + 1,ceny);
		
		tCells[0] = new Cell(cenx - 1,ceny);
		tCells[1] = new Cell(cenx,ceny - 1);
		
		bCells[0] = new Cell(cenx,ceny - 1);
		bCells[1] = new Cell(cenx + 1,ceny);
	}
	
	public Block4(int cenx, int ceny)
	{
		this.cenx = cenx;
		this.ceny = ceny;
		cells[0] = new Cell(cenx - 1,ceny);
		cells[0] = new Cell(cenx - 1,ceny);
		cells[1] = new Cell(cenx,ceny - 1);
		cells[2] = new Cell(cenx,ceny);
		cells[3] = new Cell(cenx + 1,ceny);

		lCells[0] = new Cell(cenx - 1,ceny);
		lCells[1] = new Cell(cenx,ceny - 1);
		lCells[2] = new Cell(cenx + 1,ceny);
		
		rCells[0] = new Cell(cenx - 1,ceny);
		rCells[1] = new Cell(cenx,ceny);
		rCells[2] = new Cell(cenx + 1,ceny);
		
		tCells[0] = new Cell(cenx - 1,ceny);
		tCells[1] = new Cell(cenx,ceny - 1);
		
		bCells[0] = new Cell(cenx,ceny - 1);
		bCells[1] = new Cell(cenx + 1,ceny);
	}
	
	public int getCenx()
	{
		return cenx;
	}
	public void setCenx(int cenx)
	{
		this.cenx = cenx;
	}
	public int getCeny()
	{
		return ceny;
	}
	public void setCeny(int ceny)
	{
		this.ceny = ceny;
	}
	
	public Cell[] getlCells()
	{
		return lCells;
	}

	public Cell[] getrCells()
	{
		return rCells;
	}

	public Cell[] getbCells()
	{
		return bCells;
	}

	public Cell[] gettCells()
	{
		return tCells;
	}
	
	public void moveLeft()
	{
		super.moveLeft();
		for (int i = 0; i < bCells.length; i++)
		{
			bCells[i].moveLeft();
		}
		for (int i = 0; i < tCells.length; i++)
		{
			tCells[i].moveLeft();
		}
		for (int i = 0; i < rCells.length; i++)
		{
			rCells[i].moveLeft();
		}
		for (int i = 0; i < lCells.length; i++)
		{
			lCells[i].moveLeft();
		}
		ceny--;
	}
	public void moveRight()
	{
		super.moveRight();
		for (int i = 0; i < bCells.length; i++)
		{
			bCells[i].moveRight();
		}
		for (int i = 0; i < tCells.length; i++)
		{
			tCells[i].moveRight();
		}
		for (int i = 0; i < rCells.length; i++)
		{
			rCells[i].moveRight();
		}
		for (int i = 0; i < lCells.length; i++)
		{
			lCells[i].moveRight();
		}
		ceny++;
	}
	public void moveDown()
	{
		super.moveDown();
		for (int i = 0; i < bCells.length; i++)
		{
			bCells[i].moveDown();
		}
		for (int i = 0; i < tCells.length; i++)
		{
			tCells[i].moveDown();
		}
		for (int i = 0; i < rCells.length; i++)
		{
			rCells[i].moveDown();
		}
		for (int i = 0; i < lCells.length; i++)
		{
			lCells[i].moveDown();
		}
		cenx++;
	}
	
	public String toString()
	{
		return "Block4";
	}
}
