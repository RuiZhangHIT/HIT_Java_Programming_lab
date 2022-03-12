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
				System.out.println("�����������ʲô������ ����w/a/s/d�ƶ�   ����Help��ȡ������Ϣ ����Bag�鿴���� ����Player�鿴�������� ����wearlist�鿴װ����  ����Save�浵 ����Load�����ϴ���Ϸ ����Exit�˳�");
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
						System.out.println("��ͼ��������");
						printArray(maps);
						battle(Maru);
					} else
						System.out.println("��ѽ��ײǽ�ˣ�������������");
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
						System.out.println("��ͼ��������");
						printArray(maps);
						battle(Maru);
						
					} else
						System.out.println("��ѽ��ײǽ�ˣ�������������");
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
						System.out.println("��ͼ��������");
						printArray(maps);
						battle(Maru);
					} else
						System.out.println("��ѽ��ײǽ�ˣ�������������");
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
						System.out.println("��ͼ��������");
						printArray(maps);
						battle(Maru);
					} else
						System.out.println("��ѽ��ײǽ�ˣ�������������");
				}
				case "Bag" -> {
					boolean judge = Maru.listBag();
					if(judge) {
						System.out.println("��ѡ���һ������������useѡ��ʹ������Ʒ������wearѡ����װ��������throw������Ʒ�����������˳�");
						Scanner Bagjudge = new Scanner(System.in);
						String word=Bagjudge.nextLine();
						if ( word.equals("use") ) {
							System.out.println("����������ʹ�õ�����Ʒ���ƣ�");
							Scanner use = new Scanner(System.in);
							String word1=use.nextLine();
							Item searchResult = Maru.searchItem(word1);
							if(searchResult==null) {
								System.out.println("����ʹ�õ�����Ʒ�����ڱ�����");
							}else {
								Maru.useConsumable(searchResult);
							}
						}
						else if ( word.equals("throw")) {
							System.out.println("����������Ҫ������Ʒ������");
							Scanner throwItem = new Scanner(System.in);
							String word2 = throwItem.nextLine();
							Item searchResult2 = Maru.searchItem(word2);
							if(searchResult2==null) {
								System.out.println("���붪������Ʒ�����ڱ�����");
							}else {
								Maru.thowout(searchResult2);
								System.out.println("�����ɹ�");
							}
						}
						else if ( word.equals("wear")) {
							System.out.println("����������Ҫ������Ʒ������");
							Scanner wearItem = new Scanner(System.in);
							String word3 = wearItem.nextLine();
							Item searchResult3 = Maru.searchItem(word3);
							if(searchResult3==null) {
								System.out.println("���봩�ϵ���Ʒ�����ڱ�����");
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
						System.out.println("�Ƿ�ѡ��װ�����е�װ��ж�ص�����������Yes������һ�������������˳���");
						Scanner unwearjudge = new Scanner(System.in);
						String word=unwearjudge.nextLine();
						if(word.equals("Yes")) {
							System.out.println("����������ж�ص�װ��������");
							Scanner getunwear = new Scanner(System.in);
							String getUnwear=getunwear.nextLine();
							Item weaR=Maru.wearlist.get(getUnwear);
							if(weaR!=null) {
								Maru.armEquipment(false, weaR);
								System.out.println("ж�����");
							}else {
								System.out.println("����Ҫж�ص�װ��������װ������");
							}
						}
					}
				}
				case "Help" -> {
					System.out.println("����ͼ��Ϣ��");
					System.out.println("0: �߹���λ�ã���ʼ��0��ʾ��ʼλ��");
					System.out.println("1: ��ǰ������λ��");
					System.out.println("2: ����ͨ�е�·");
					System.out.println("3: ǽ");
					System.out.println("4: ����ݵ�");
					System.out.println("9: ��");
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
						System.out.println("����δ�浵");
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
				System.out.println("��Ϸ����");
			else
				System.out.println("��ϲ�����������");
		}

		public static void battle(Player Maru) {
			int[] cc = Maru.returnlocal();
			String loc=cc[0]+" "+cc[1];
			Monster ThisMonster = Manage.findMonster(loc);
			if (ThisMonster != null) {
				System.out.println("Ұ����"+ThisMonster+"������,������������");
				int[] moninfo=ThisMonster.getinfo();
				System.out.println("HP:"+moninfo[0]);
				System.out.println("Attack:"+moninfo[1]);
				System.out.println("Defence:"+moninfo[2]);
				System.out.println("�����ȶ�"+ThisMonster+"�����˹���");
				Maru.attack(ThisMonster);
				if(Maru.isDead()) {
					System.out.println("ʧ���ˣ�You are Dead");
				}else {
					System.out.println("��սʤ�ˣ������ս��Ʒ");
					Maru.gainExperence(ThisMonster);
					Maru.pickUp(ThisMonster);
					Manage.deleteMonster(loc);
					System.out.println("�������������");
					Scanner p = new Scanner(System.in);
					String conti=p.nextLine();
				}

			}else if(maps[x][y]==4) {
				System.out.println("�þݵ�Ĺ����ѱ�����");
			}
		}
		
		public static void printArray(int[][] maps) {
			System.out.println();
			System.out.println();
			System.out.println("��ͼ����");
			for (int[] map : maps) {
				for (int i : map) {
					System.out.print(i);
				}
				System.out.println();
			}
		}
		
}
