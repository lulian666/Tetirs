package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.Timer;

import BlockBase.Block;
import Control.BlockControl;
import Control.BoundaryControl;
import Control.GameControl;

public class Tetris//程序界面及入口
{
	//board数组记录桌面哪个位置有方块
	private int[][] board = new int[20][10];
	//block表示当前在移动的方块
	private Block block;
	//游戏界面
	JFrame jf = new JFrame("俄罗斯方块");
	//定时任务，负责定时调用方块的下降方法
	private Timer fallingTimer;
	//边界控制的类
	private BoundaryControl boundaryControl = new BoundaryControl();
	//展现俄罗斯方块的类
	private TetrisBoard terisBoard = new TetrisBoard(board);
	//控制方块运动的类
	private BlockControl blockControl = new BlockControl();
	//控制整体游戏的类
	private GameControl gameControl = new GameControl();
	//控制下落速度的变量
//	public static int speed = 1000;
//	//计算所得分数，每消除一行得100分
//	public static int socre = 0;
	
	public void init() throws IOException
	{
		block = null;
		//定时任务
		ActionListener task = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (block == null)//block是null的话代表新开局
				{
					block = blockControl.randomBlock(board);//初始化一个block
					terisBoard.showTable(4,block);//让block在界面上展示出来
					terisBoard.repaint();
				}
				else if(!boundaryControl.isTouchingBottom(block,board))
				{
					block.moveDown();//如果block还没有触底，就继续调用moveDown()方法下移block
					terisBoard.showTable(3,block);//让block在界面上展示出来
					terisBoard.repaint();
				}
				else
				{
					gameControl.remove(board);//每次触底了就调用remove()消除方法，如果有整行就消除
					boolean gameover = gameControl.isGameOver(board);//每次触底也进行gameover的判断，看是否游戏结束
					if (gameover)
					{
						//此处做gameover的处理
						fallingTimer.stop();
						int result = gameControl.gameover(jf,block,board);//返回值表示用户的选择，是重玩还是不玩了
						if (result == 1)
						{
							fallingTimer.start();
						}
					}
					else
					{
						block = blockControl.randomBlock(board);//如果游戏未结束，就再初始化一个block
						if (block == null)//如果block返回值是null，表示初始化的block会和界面上已有的方块重叠，那么游戏结束
						{
							fallingTimer.stop();
							int result = gameControl.gameover(jf,block,board);
							if (result == 1)
							{
								fallingTimer.start();
							}
						}
						else
						{
							terisBoard.showTable(4,block);
							terisBoard.repaint();
						}
					}
				}
			}
		};
		fallingTimer = new Timer(300,task);
		fallingTimer.start();
		//为jf增加键盘监听器
		jf.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				switch(e.getKeyCode())
				{
				case KeyEvent.VK_LEFT:
					//先判断有没有触及边界再调用左移方法
					if (!boundaryControl.isTouchingLeft(block,board))
					{
						block.moveLeft();
						terisBoard.showTable(1,block);
						terisBoard.repaint();
					}
					break;
				case KeyEvent.VK_RIGHT:
					//先判断有没有触及边界再调用右移方法
					if (!boundaryControl.isTouchingRight(block,board))
					{
						block.moveRight();
						terisBoard.showTable(2,block);
						terisBoard.repaint();
					}
					break;
				case KeyEvent.VK_DOWN:
					//先判断有没有触及边界再调用下移方法，与定时任务中的逻辑差不多
					if (!boundaryControl.isTouchingBottom(block,board))
					{
						block.moveDown();
						terisBoard.showTable(3,block);
						terisBoard.repaint();
					}
					else
					{
						gameControl.remove(board);
						block = blockControl.randomBlock(board);
						if (block == null)
						{
							fallingTimer.stop();
							int result = gameControl.gameover(jf,block,board);
							if (result == 1)
							{
								fallingTimer.start();
							}
						}
						else
						{
							terisBoard.showTable(4,block);
							terisBoard.repaint();
						}
					}
					break;
				case KeyEvent.VK_UP://向上键表示旋转
					block = blockControl.spin(block,board);	//可以旋转的话，返回的block就是旋转后的block，否则就不变
					terisBoard.showTable(4,block);
					terisBoard.repaint();
					break;
				case KeyEvent.VK_SPACE:
					//暂停游戏和恢复
					if(fallingTimer.isRunning())
					{
						fallingTimer.stop();
					}
					else
					{
						fallingTimer.start();
					}
					break;
				}
			}
			@Override
			public void keyReleased(KeyEvent e){}
		});
		
		terisBoard.setPreferredSize(new Dimension(TetrisBoard.TABLE_WIDTH,TetrisBoard.TABLE_HEIGHT));
		jf.add(terisBoard,BorderLayout.CENTER);
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	public static void main(String[] args) throws Exception
	{
		new Tetris().init();
	}
}
