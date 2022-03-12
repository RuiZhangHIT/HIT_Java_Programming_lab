package textgame;

import java.util.HashMap;
import java.util.Random;

public class MonsterManage {
	
	private static HashMap<String, Monster> MonsterList = new HashMap<>();

	public int[] random() {
		int[] c = new int[2];
		Random r = new Random();
		c[0] = r.nextInt(5) + 1;
		c[1] = r.nextInt(5) + 1;
		return c;
	}

	public void createMonsterForRoom() {
		int[] local1= {3,1};
		int[] local2= {6,4};
		MonsterList.put("3 1", new Monster(92, 15, 5, local1, 60, new Armor("Shield"),"Stats"));
		MonsterList.put("6 4", new Monster(50, 7, 2, local2, 10, new Weapons("Exculibur"),"Saber"));
	}

	public Monster findMonster(String local) {
		return MonsterList.get(local);
	}
	
	public void deleteMonster(String local) {
		MonsterList.remove(local);
	}
	
	public  HashMap<String, Monster> returnmassage(){
		return MonsterList;
	}

}

