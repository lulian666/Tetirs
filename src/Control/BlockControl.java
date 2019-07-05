package Control;

import java.util.Random;

import BlockBase.Block;
import BlockBase.Cell;
import Blocks.Block1;
import Blocks.Block2;
import Blocks.Block3;
import Blocks.Block4;

public class BlockControl
{
	//此类是用于控制方块的移动，包括左右移动，下移，旋转
	//随机生成一个方块的方法
	public Block randomBlock(int[][] board)
	{
		Block block = null;
		Random rand = new Random();
		int seed = rand.nextInt(4) + 1;//随机生成一个方块的序号
//		int seed = 3;//调试用
		switch (seed)//根据序号决定生成哪种方块
		{
		case 1:
			block = new Block1();
			break;
		case 2:
			block = new Block2();
			break;
		case 3:
			block = new Block3();
			break;
		case 4:
			block = new Block4();
			break;
		}	
		Cell[] cells = block.cells;
		//此处加个判断：初始化的方块会不会和board上已有的方块重叠
		boolean gameover = false;
		for (int i = 0; i < cells.length; i++)
		{
			if (board[cells[i].getRow()][cells[i].getColumn()] == 1)
			{
				gameover = true;//如果重叠了就表示游戏结束
			}
		}
		if (gameover)
		{
			//此处调用游戏结束的方法(让Tetris里面去调用吧）
			return null;//返回值是null的时候，调用者就知道游戏结束了
		}
		else
		{
			for (int i = 0; i < cells.length; i++)
			{
				board[cells[i].getRow()][cells[i].getColumn()] = 1;
			}
			return block;
		}
		
	}
	
	//旋转方法
		public Block spin(Block block, int[][] board)
		{
			//如果旋转以后没有重叠到其他方块上，且不会超出边界，才可以旋转
			//取出block的中心点（总是以中心点为旋转中心的，也方便标识方块当前的位置）
			int cenx = block.getCenx();
			int ceny = block.getCeny();
			String clazz = block.toString();//Block中重写了toString方法，用于区分是哪种方块
			Block iblock = null;
			switch (clazz)
			{
			    case "Block1"://如果Block1就旋转为Block2，以下同理
			    	iblock = new Block2(cenx,ceny);
			    	break;
			    case "Block2":
			    	iblock = new Block3(cenx,ceny);
			    	break;
			    case "Block3":
			    	iblock = new Block4(cenx,ceny);
			        break;
			    case "Block4":
			    	iblock = new Block1(cenx,ceny);
			    	break;
			}
			//判断以下是否能进行选准
			boolean isSpinnable = spinnable(block,iblock,board);
			//一轮过下来如果isSpinnable的值都stay true，那就是可以旋转
			if(isSpinnable)
			{
				
				//就把原先block在board中的位置改为0，再把新block位置改为1
				for(int i = 0; i < block.cells.length; i++)
				{
					board[block.cells[i].getRow()][block.cells[i].getColumn()] = 0;
				}
				block = iblock;
			}
			//如果不可以旋转，此方法相当于啥效果也没有
			return block;
		}
		
		public boolean spinnable(Block block, Block iblock, int[][] board)//block是旋转前的，iblock是旋转后的
		{
			//取出iblock的四个点的位置
			Cell[] icells = iblock.cells;
			//设两个变量来看是否可以旋转
			boolean term1 = false;
			boolean term2 = false;
			//对icells数组中的值遍历
			for (int i = 0; i < icells.length; i++)
			{
				//此处对icells里面的几个点进行判断，看是否可以旋转
				int xrow = icells[i].getRow();
				int xcolumn = icells[i].getColumn();
				
				if ((xrow >= 0) && (xrow <= 19) && (xcolumn >= 0) && (xcolumn <= 9) )//第一个条件是没有超出边界
				{
					//能旋转就把isSpinnable设为true
					term1 = true;		
				}
				//第二个条件，旋转后的iblock，它里面的四个cell，与原先block中的cell不重复的地方，都要判断该cell在board上的位置是不是1，是1就不能旋转
				//意思就是旋转了以后不能和已有的方块重叠（但不包括旋转前的block）
				if (!((icells[i].getRow() == block.cells[0].getRow()) && (icells[i].getColumn() == block.cells[0].getColumn())) &&
						!((icells[i].getRow() == block.cells[1].getRow()) && (icells[i].getColumn() == block.cells[1].getColumn())) &&
						!((icells[i].getRow() == block.cells[2].getRow()) && (icells[i].getColumn() == block.cells[2].getColumn())) &&
						!((icells[i].getRow() == block.cells[3].getRow()) && (icells[i].getColumn() == block.cells[3].getColumn())))
				{
					if (board[xrow][xcolumn] == 0)
					{
						term2 = true;
					}
				}
			}
			return (term1 && term2);
		}
}
