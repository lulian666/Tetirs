package BlockBase;

public class Block//所有俄罗斯方块的父类，等于规范一下每个block的写法
{
	//一个俄罗斯方块由4个小方块构成
	public Cell[] cells = new Cell[4];
	
	public Cell[] getlCells()
	{
		return null;
	}
	
	public Cell[] getrCells()
	{
		return null;
	}

	public Cell[] getbCells()
	{
		return null;
	}
	public Cell[] gettCells()
	{
		return null;
	}
	//移动的方法由每个小方块各自的移动形成
	public void moveLeft()
	{
		for(int i = 0; i < cells.length; i++)
		{
			cells[i].moveLeft();
		}
	}
	
	public void moveRight()
	{
		for(int i = 0; i < cells.length; i++)
		{
			cells[i].moveRight();
		}
	}
	
	public void moveDown()
	{
		for(int i = 0; i < cells.length; i++)
		{
			cells[i].moveDown();
		}
	}

	public int getCenx()
	{
		return 0;
	}
	public void setCenx(int cenx)
	{
	}
	public int getCeny()
	{
		return 0;
	}
	public void setCeny(int ceny)
	{
	}
	
	public String toString()
	{
		return null;
	}
}
