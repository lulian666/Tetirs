package Interface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import BlockBase.Block;
import BlockBase.Cell;

public class TetrisBoard extends JPanel
{
	//这个类表示游戏最核心的部分
	//画布和方块的大小
	public static final int TABLE_WIDTH = 100;
	public static final int TABLE_HEIGHT = 200;
	private static final int BLOCK_SIZE = 10;
	//方块和背景图
	private BufferedImage table = new BufferedImage(TABLE_WIDTH,TABLE_HEIGHT,BufferedImage.TYPE_INT_RGB);
	private BufferedImage smallBlock = new BufferedImage(BLOCK_SIZE,BLOCK_SIZE,BufferedImage.TYPE_INT_RGB);	
	
	private int[][] board;
	public TetrisBoard(int[][] board)
	{
		this.board = board;
		try
		{
			table = ImageIO.read(new File("image/table.png"));
			smallBlock = ImageIO.read(new File("image/smallBlock.png"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//此方法是用于在界面上展示出方块，whatToDo表示调用者的意图（比如是旋转以后，在界面上展示，还是移动以后）
	public void showTable(int whatToDo, Block block)
	{
		int row1 = block.cells[0].getRow();
		int row4 = block.cells[3].getRow();

		Cell[] cells = block.cells;
		Cell[] lCells = block.getlCells();
		Cell[] rCells = block.getrCells();
		Cell[] tCells = block.gettCells();

		switch(whatToDo){
		case 0://新生成方块，啥都不需要做
			break;
		case 1://左移，左移了以后要把rCells中的点的右边的位置清0（rCells定义见每个block类）
			for (int i = 0; i < rCells.length; i++)
			{
				board[rCells[i].getRow()][rCells[i].getColumn() + 1] = 0;
			}
			break;
		case 2://右移
			
			for (int i = 0; i < lCells.length; i++)
			{
				board[lCells[i].getRow()][lCells[i].getColumn() - 1] = 0;
			}
			break;
		case 3://下移
			if ((!(row1 == 0)) && (row4 <= 19))
			{
				for (int i = 0; i < tCells.length; i++)
				{
					board[tCells[i].getRow() - 1][tCells[i].getColumn()] = 0;
				}
			}
			break;
		case 4://旋转或者初始化block，此处啥都不需要做
			break;
		}
		if (row4 <= 19)//把该清0的位置清0以后，把新block的四个点的位置都在数组里设为1
		{
			for (int i = 0; i < cells.length; i++)
			{
				board[cells[i].getRow()][cells[i].getColumn()] = 1;
			}
		}
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(table, 0, 0,100,200,null);
		for (int i = 0; i < 20; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				if (board[i][j] == 1)
				{
					g.drawImage(smallBlock,j*10,i*10,10,10,null);
				}
//				System.out.print(board[i][j]+" ");
			}
//			System.out.println();
		}
	}
}
