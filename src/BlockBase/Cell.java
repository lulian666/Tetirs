package BlockBase;

public class Cell
{
	//每个俄罗斯方块都由四个这样的小cell组成，每个cell都有自己的的移动方法
	//一个俄罗斯方块的移动，就是由四个cell各自的移动组成的
	//记录小方块的行列坐标
	private int row;
	private int column;
	
	public Cell(int row , int column)
	{
		this.row = row;
		this.column = column;
	}
	
	//小方块左右下移的方法
	public void moveLeft()
	{
		column--;
	}
	
	public void moveRight()
	{
		column++;
	}
	
	public void moveDown()
	{
		row++;
	}
	
	public int getRow()
	{
		return row;
	}

	public void setRow(int row)
	{
		this.row = row;
	}

	public int getColumn()
	{
		return column;
	}

	public void setColumn(int column)
	{
		this.column = column;
	}
	
}
