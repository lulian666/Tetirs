package Blocks;

import BlockBase.Block;
import BlockBase.Cell;

public class Block1 extends Block//后面的几个Block都是一样的意思
{
	//下面三个数组分别代表判断是否触碰边界（左、右、下）时需要一一进行判断的点
	//如：lCells就是判断是否触碰做边界的时候需要进行判断的cell，这里有两个
	public Cell[] lCells = new Cell[2];
	public Cell[] rCells = new Cell[2];
	public Cell[] bCells = new Cell[3];
	public Cell[] tCells = new Cell[3];
	//每个方块的中心点，用作旋转中心，和标识方块当前位置
	private int cenx;
	private int ceny;
	
	public Block1(){
		cenx = 1;
		ceny = 4;
		cells[0] = new Cell(cenx - 1,ceny);
		cells[1] = new Cell(cenx,ceny - 1);
		cells[2] = new Cell(cenx,ceny);
		cells[3] = new Cell(cenx,ceny + 1);
		
		lCells[0] = new Cell(cenx - 1,ceny);
		lCells[1] = new Cell(cenx,ceny - 1);
		
		rCells[0] = new Cell(cenx - 1,ceny);
		rCells[1] = new Cell(cenx,ceny + 1);
		
		bCells[0] = new Cell(cenx,ceny - 1);
		bCells[1] = new Cell(cenx,ceny);
		bCells[2] = new Cell(cenx,ceny + 1);
		
		tCells[0] = new Cell(cenx - 1,ceny);
		tCells[1] = new Cell(cenx,ceny - 1);
		tCells[2] = new Cell(cenx,ceny + 1);
	}
	public Block1(int cenx, int ceny)
	{
		this.cenx = cenx;
		this.ceny = ceny;
		cells[0] = new Cell(cenx - 1,ceny);
		cells[1] = new Cell(cenx,ceny - 1);
		cells[2] = new Cell(cenx,ceny);
		cells[3] = new Cell(cenx,ceny + 1);
		
		lCells[0] = new Cell(cenx - 1,ceny);
		lCells[1] = new Cell(cenx,ceny - 1);
		
		rCells[0] = new Cell(cenx - 1,ceny);
		rCells[1] = new Cell(cenx,ceny + 1);
		
		bCells[0] = new Cell(cenx,ceny - 1);
		bCells[1] = new Cell(cenx,ceny);
		bCells[2] = new Cell(cenx,ceny + 1);
		
		tCells[0] = new Cell(cenx - 1,ceny);
		tCells[1] = new Cell(cenx,ceny - 1);
		tCells[2] = new Cell(cenx,ceny + 1);
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
	
	public void moveLeft()//左移方法，一个block的移动，是分别去调用每个组成它的cell的左移方法的
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
		ceny--;//中心点的位置也要跟着变
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
		return "Block1";
	}
}
