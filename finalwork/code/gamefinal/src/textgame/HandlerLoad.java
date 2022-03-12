package textgame;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class HandlerLoad {
	
	private Player p;

	public Player loadPlayer() throws IOException {//对已存档玩家信息读档
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							new FileInputStream("save.txt")));//定义输入流
			
			String[] playerdata0 = in.readLine().split(" ");
			int[] location = {8,4};
			p = new Player(Integer.parseInt(playerdata0[0]),Integer.parseInt(playerdata0[1]),Integer.parseInt(playerdata0[2]),
					Integer.parseInt(playerdata0[3]),location,Integer.parseInt(playerdata0[6]),playerdata0[7]);//对玩家角色基本信息初始化
			
			String[] playerdata1 = in.readLine().split(" ");
			int Bagsize = Integer.parseInt(playerdata1[0]);
			if(Bagsize!=0) {//背包非空时，将背包初始化
				HashMap<Item,Integer> HM1 =new HashMap<Item,Integer>();
				for(int i=0;i<Bagsize;i++) {
					Item item = new Item(playerdata1[4*i+1],Integer.parseInt(playerdata1[4*i+2]),playerdata1[4*i+3]);
					HM1.put(item, Integer.parseInt(playerdata1[4*i+4]));
				}
				p.createBag(HM1);
			}
			
			String[] playerdata2 = in.readLine().split(" ");
			int wearlistsize = Integer.parseInt(playerdata2[0]);
			if(wearlistsize!=0) {//装备栏非空时，将装备栏初始化
				HashMap<String,Item> HM2 =new HashMap<String,Item>();
				for(int j=0;j<wearlistsize;j++) {
					Item item = new Item(playerdata2[4*j+2],Integer.parseInt(playerdata2[4*j+3]),playerdata2[4*j+4]);
					HM2.put(playerdata2[4*j+1], item);
				}
				p.createWearlist(HM2);
			}
			
			in.close();
			System.out.println("读取成功");
			System.out.println("角色加载成功");
			System.out.println("角色在初始位置重新出击");
			System.out.println("怪物巢穴已生成生物");
			
			return p;
	}
	
}
