package Control;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import BlockBase.Block;

public class GameControl
{
	//此类是对游戏整体规则的控制
	
	//整行消除方法
	//触底以后调用此方法，判断界面上是否有可以消除的行
	public void remove(int[][] board)
	{
		//temp数组记录每行有方块的个数
		int[] temp = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		//遍历数组
		for (int i = 0; i < 20; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				if (board[i][j] == 1)
				{
					temp[i]++;//每行遇到1就计数
				}
			}
			if (temp[i] == 10)//某行的计数为10的时候，表示该行全为1，该行上面的所有行都要下移
			{
				for (int x = i; x > 0; x--)//第一行不用管，因为如果第一行有方块的话，直接游戏结束了
				{
					for (int y = 0; y < 10; y++)
					{
						board[x][y] = board[x-1][y];
					}
				}
			}
		}
	}
	
	//判断游戏是否结束的方法，规则：方块触底以后如果第一行有方块，则视为游戏结束
	//另一处判断在初始化方块的时候，如果初始化的时候与画布上已有的方块重叠，游戏结束
	public boolean isGameOver(int[][] board)
	{
		for (int i = 0; i < board[0].length; i++)
		{
			if (board[0][i] == 1)
			{
				return true;
			}
		}
		return false;
	}
	
	//gameover时的处理
	public int gameover(JFrame jf, Block block, int[][] board)
	{
		Object[] option = new Object[]{"不玩了","重新开始"};
		int result = JOptionPane.showOptionDialog(jf, new String("你太菜了，重新玩吧！"), "游戏结束", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, null);
		//重新开始是1，不玩了是0
		switch(result)
		{
		case 0:
			
			break;
		case 1:
			block = null;
			for (int i = 0; i < 20; i++)
			{
				for (int j = 0; j < 10; j++)
				{
					board[i][j] = 0;
				}
			}
			break;
		}
		return result;
	}
}
