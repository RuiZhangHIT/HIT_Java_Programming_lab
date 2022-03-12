package textgame;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class HandlerLoad {
	
	private Player p;

	public Player loadPlayer() throws IOException {//���Ѵ浵�����Ϣ����
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							new FileInputStream("save.txt")));//����������
			
			String[] playerdata0 = in.readLine().split(" ");
			int[] location = {8,4};
			p = new Player(Integer.parseInt(playerdata0[0]),Integer.parseInt(playerdata0[1]),Integer.parseInt(playerdata0[2]),
					Integer.parseInt(playerdata0[3]),location,Integer.parseInt(playerdata0[6]),playerdata0[7]);//����ҽ�ɫ������Ϣ��ʼ��
			
			String[] playerdata1 = in.readLine().split(" ");
			int Bagsize = Integer.parseInt(playerdata1[0]);
			if(Bagsize!=0) {//�����ǿ�ʱ����������ʼ��
				HashMap<Item,Integer> HM1 =new HashMap<Item,Integer>();
				for(int i=0;i<Bagsize;i++) {
					Item item = new Item(playerdata1[4*i+1],Integer.parseInt(playerdata1[4*i+2]),playerdata1[4*i+3]);
					HM1.put(item, Integer.parseInt(playerdata1[4*i+4]));
				}
				p.createBag(HM1);
			}
			
			String[] playerdata2 = in.readLine().split(" ");
			int wearlistsize = Integer.parseInt(playerdata2[0]);
			if(wearlistsize!=0) {//װ�����ǿ�ʱ����װ������ʼ��
				HashMap<String,Item> HM2 =new HashMap<String,Item>();
				for(int j=0;j<wearlistsize;j++) {
					Item item = new Item(playerdata2[4*j+2],Integer.parseInt(playerdata2[4*j+3]),playerdata2[4*j+4]);
					HM2.put(playerdata2[4*j+1], item);
				}
				p.createWearlist(HM2);
			}
			
			in.close();
			System.out.println("��ȡ�ɹ�");
			System.out.println("��ɫ���سɹ�");
			System.out.println("��ɫ�ڳ�ʼλ�����³���");
			System.out.println("���ﳲѨ����������");
			
			return p;
	}
	
}
