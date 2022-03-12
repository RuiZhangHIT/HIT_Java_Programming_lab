package textgame;

import java.io.IOException;
import java.util.Scanner;

public class Main {

		public static MonsterManage Manage = new MonsterManage();
		public static HandlerSave save = new HandlerSave();
		public static HandlerLoad load = new HandlerLoad();
		public static int[][] maps2 ={ { 3, 3, 3, 3, 3, 3, 3, 3, 9, 3 },
				 { 3, 3, 2, 2, 2, 2, 2, 2, 2, 3 },
				 { 3, 3, 2, 3, 2, 3, 2, 3, 3, 3 }, 
				 { 3, 4, 2, 3, 3, 3, 2, 3, 2, 3 }, 
				 { 3, 2, 3, 3, 2, 3, 3, 3, 2, 3 },
				 { 3, 2, 2, 2, 2, 3, 3, 2, 2, 3 }, 
				 { 3, 2, 3, 3, 4, 3, 3, 2, 3, 3 }, 
				 { 3, 3, 3, 3, 2, 3, 3, 3, 3, 3 },
				 { 3, 3, 3, 3, 1, 3, 3, 3, 3, 3 }, 
				 { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 } };
		public static int[][] maps = { { 3, 3, 3, 3, 3, 3, 3, 3, 9, 3 },
				 { 3, 3, 2, 2, 2, 2, 2, 2, 2, 3 },
				 { 3, 3, 2, 3, 2, 3, 2, 3, 3, 3 }, 
				 { 3, 4, 2, 3, 3, 3, 2, 3, 2, 3 }, 
				 { 3, 2, 3, 3, 2, 3, 3, 3, 2, 3 },
				 { 3, 2, 2, 2, 2, 3, 3, 2, 2, 3 }, 
				 { 3, 2, 3, 3, 4, 3, 3, 2, 3, 3 }, 
				 { 3, 3, 3, 3, 2, 3, 3, 3, 3, 3 },
				 { 3, 3, 3, 3, 0, 3, 3, 3, 3, 3 }, 
				 { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 } };
		public static int x = 8;
		public static int y = 4;
		public static void main(String[] args) {
			Manage.createMonsterForRoom();
			int[] a = { 8, 4 };
			Player Maru = new Player(100, 12, 5, 1, a, 0, "Maru");
			printArray(maps);
			Scanner sc = new Scanner(System.in);
			boolean exist=false;
			boolean win = false;
			while (maps[x][y] != 9) {
				//boolean exist=false;
				System.out.println("请问您想进行什么操作？ 输入w/a/s/d移动   输入Help获取帮助信息 输入Bag查看背包 输入Player查看个人属性 输入wearlist查看装备栏  输入Save存档 输入Load继续上次游戏 输入Exit退出");
				String str = sc.nextLine();
				switch (str) {
				case "w" -> {
					if (maps[x - 1][y] != 3) {
						maps[x][y] = 0;
						x--;
						Maru.move(-1, 0);
						if(maps[x][y] == 9) {
							win=true;
						}
						maps[x][y] = 1;
						System.out.println("地图更改如下");
						printArray(maps);
						battle(Maru);
					} else
						System.out.println("哎呀，撞墙了，换个方向试试");
				}
				case "a" -> {
					if (maps[x][y - 1] != 3) {
						maps[x][y] = 0;
						y--;
						Maru.move(0, -1);
						if(maps[x][y] == 9) {
							win=true;
						}
						maps[x][y] = 1;
						System.out.println("地图更改如下");
						printArray(maps);
						battle(Maru);
						
					} else
						System.out.println("哎呀，撞墙了，换个方向试试");
				}
				case "s" -> {
					if (maps[x + 1][y] != 3) {
						maps[x][y] = 0;
						x++;
						Maru.move(1, 0);
						if(maps[x][y] == 9) {
							win=true;
						}
						maps[x][y] = 1;
						System.out.println("地图更改如下");
						printArray(maps);
						battle(Maru);
					} else
						System.out.println("哎呀，撞墙了，换个方向试试");
				}
				case "d" -> {
					if (maps[x][y + 1] != 3) {
						maps[x][y] = 0;
						y++;
						Maru.move(0, 1);
						if(maps[x][y] == 9) {
							win=true;
						}
						maps[x][y] = 1;
						System.out.println("地图更改如下");
						printArray(maps);
						battle(Maru);
					} else
						System.out.println("哎呀，撞墙了，换个方向试试");
				}
				case "Bag" -> {
					boolean judge = Maru.listBag();
					if(judge) {
						System.out.println("请选择进一步操作：输入use选择使用消耗品，输入wear选择穿上装备，输入throw丢弃物品，输入其他退出");
						Scanner Bagjudge = new Scanner(System.in);
						String word=Bagjudge.nextLine();
						if ( word.equals("use") ) {
							System.out.println("请输入您想使用的消耗品名称：");
							Scanner use = new Scanner(System.in);
							String word1=use.nextLine();
							Item searchResult = Maru.searchItem(word1);
							if(searchResult==null) {
								System.out.println("您想使用的消耗品并不在背包中");
							}else {
								Maru.useConsumable(searchResult);
							}
						}
						else if ( word.equals("throw")) {
							System.out.println("请输入你想要丢弃物品的名字");
							Scanner throwItem = new Scanner(System.in);
							String word2 = throwItem.nextLine();
							Item searchResult2 = Maru.searchItem(word2);
							if(searchResult2==null) {
								System.out.println("您想丢弃的物品并不在背包中");
							}else {
								Maru.thowout(searchResult2);
								System.out.println("丢弃成功");
							}
						}
						else if ( word.equals("wear")) {
							System.out.println("请输入你想要穿上物品的名字");
							Scanner wearItem = new Scanner(System.in);
							String word3 = wearItem.nextLine();
							Item searchResult3 = Maru.searchItem(word3);
							if(searchResult3==null) {
								System.out.println("您想穿上的物品并不在背包中");
							}else {
								Maru.armEquipment(true, searchResult3);
							}

						}
					}
				}
				case "Player" -> {
					Maru.printrole();
				}
				case "wearlist" -> {
					boolean judge=Maru.listwearlist();
					if(judge) {
						System.out.println("是否选择将装备栏中的装备卸载到背包？输入Yes进行下一步，输入其他退出。");
						Scanner unwearjudge = new Scanner(System.in);
						String word=unwearjudge.nextLine();
						if(word.equals("Yes")) {
							System.out.println("请输入你想卸载的装备的名称");
							Scanner getunwear = new Scanner(System.in);
							String getUnwear=getunwear.nextLine();
							Item weaR=Maru.wearlist.get(getUnwear);
							if(weaR!=null) {
								Maru.armEquipment(false, weaR);
								System.out.println("卸载完成");
							}else {
								System.out.println("您想要卸载的装备并不在装备栏中");
							}
						}
					}
				}
				case "Help" -> {
					System.out.println("【地图信息】");
					System.out.println("0: 走过的位置，初始的0表示初始位置");
					System.out.println("1: 当前所处的位置");
					System.out.println("2: 允许通行的路");
					System.out.println("3: 墙");
					System.out.println("4: 怪物据点");
					System.out.println("9: 门");
				}
				case "Save" -> {
					save.savePlayer(Maru);
				}
				case "Load" -> {
					try {
						Maru = load.loadPlayer();
						maps[x][y]=0;
						x=8;
						y=4;
						for (int i = 0; i < 10; i++) {
							maps[i] = maps2[i].clone();
						}
						printArray(maps);
						Manage.createMonsterForRoom();
					} catch (IOException e) {
						System.out.println("您尚未存档");
					}
				}
				case "Exit" -> {
					exist=true;
				}
				default -> {
				}
				}
				if(win==true) {
					break;
				}
				if(exist==true)
					break;
				if(Maru.isDead()) {
					break;
				}
			}
			
			if(Maru.isDead()||exist)
				System.out.println("游戏结束");
			else
				System.out.println("恭喜您到达出口了");
		}

		public static void battle(Player Maru) {
			int[] cc = Maru.returnlocal();
			String loc=cc[0]+" "+cc[1];
			Monster ThisMonster = Manage.findMonster(loc);
			if (ThisMonster != null) {
				System.out.println("野生的"+ThisMonster+"出现了,怪物属性如下");
				int[] moninfo=ThisMonster.getinfo();
				System.out.println("HP:"+moninfo[0]);
				System.out.println("Attack:"+moninfo[1]);
				System.out.println("Defence:"+moninfo[2]);
				System.out.println("你率先对"+ThisMonster+"发起了攻击");
				Maru.attack(ThisMonster);
				if(Maru.isDead()) {
					System.out.println("失败了，You are Dead");
				}else {
					System.out.println("你战胜了，获得了战利品");
					Maru.gainExperence(ThisMonster);
					Maru.pickUp(ThisMonster);
					Manage.deleteMonster(loc);
					System.out.println("输入任意键继续");
					Scanner p = new Scanner(System.in);
					String conti=p.nextLine();
				}

			}else if(maps[x][y]==4) {
				System.out.println("该据点的怪物已被净空");
			}
		}
		
		public static void printArray(int[][] maps) {
			System.out.println();
			System.out.println();
			System.out.println("地图如下");
			for (int[] map : maps) {
				for (int i : map) {
					System.out.print(i);
				}
				System.out.println();
			}
		}
		
}
