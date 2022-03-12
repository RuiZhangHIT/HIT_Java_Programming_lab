package textgame;

import java.util.*;

public class Player extends Character {// ����

	private int experience;
	private int level;
	private static int[] exp = { 50, 100, 150, 200, 250 };
	private static int[] hp = { 100, 120, 150, 200, 250, 320 };
	HashMap<Item, Integer> Bag = new HashMap<>();// ����<��Ʒ������>
	HashMap<String, Item> wearlist = new HashMap<>();// װ����<��Ʒ���֣���Ʒ>

	public Player(int healthpoint, int attack, int defence, int level, int[] location, int experience, String name) {
		super(healthpoint, attack, defence, location, name);
		this.level = level;
		this.experience = experience;
		Bag.put(new Consumables("Bread"), 1);
		Bag.put(new Weapons("Sword"), 1);
	}

	public void createBag(HashMap<Item, Integer> bag) {
		this.Bag = bag;
	}

	public void createWearlist(HashMap<String, Item> Wearlist) {
		this.wearlist = Wearlist;
	}

	public void printrole() {
		int[] role = super.getinfo();
		System.out.println("HP:" + role[0]);
		System.out.println("Attack:" + role[1]);
		System.out.println("Defence:" + role[2]);
		System.out.print("Level:" + this.level);
		System.out.println(" Exp:" + this.experience + "/" + exp[this.level - 1]);
	}

	public void gainExperence(Monster b) {// �������Ӻ���
		experience += b.getgiftExperience();
		int[] infor=this.getinfo();
		if (experience > exp[this.level - 1]) {
			if (level != 6) {
				level++;
				infor[0]=hp[level-1];
				System.out.println("Level Up!");
				this.refreshinfo(infor);
				experience = experience - exp[level - 2];
			}
			if(level==6) {
				experience =0;
			}
		}
	}

	public void useConsumable(Item key) {// ʹ������Ʒ
		if(!key.getsort().equals("Consumables")) {
			System.out.println("����Ʒ��������Ʒ");
			return;
		}
		if (this.getHP() == hp[level - 1]) {
			System.out.println("����ֵ����������Ҫʹ�ûظ���Ʒ");
			return;
		}

		Integer p = Bag.get(key);
		if (p != null) {
			p = p - 1;
			Bag.put(key, p);
			if (p == 0) {
				Bag.remove(key);
			}
			int[] h = this.getinfo();
			h[0] += key.getbuff();
			if (h[0] > hp[level - 1]) {
				h[0] = hp[level - 1];
			}
			super.refreshinfo(h);
			System.out.println("��ʹ��");
		}

	}

	public void armEquipment(boolean install, Item item) {// װװ��

		int i;
			if (item.getsort().equals("Armor")) {
				i = 2;
			} else if (item.getsort().equals("Weapon")) {
				i = 1;
			} else {
				System.out.println("Cannot wear");
				return;
			}
		boolean flag = false;
		for (String key : wearlist.keySet()) {
			if (wearlist.get(key).getsort().equals(item.getsort())) {
				flag = true;
				break;
			}
		}
		int[] h = this.getinfo();
		if (install) {// �ж��ǰ�װ������ж��װ��
			Item searchwear = wearlist.get(item);
			if (!flag) {
				Integer p = Bag.get(item);
				if (p != null) {
					p = p - 1;
					Bag.put(item, p);
					if (p == 0) {
						Bag.remove(item);
					}
					h[i] += item.getbuff();
					super.refreshinfo(h);
					wearlist.put(item.getname(), item);
					System.out.println("װ����װ���");
				}
			} else {
				System.out.println("װ�������Ѿ��и�װ�����޷�������װ");
			}
		} else {// ��װ����ж�ص�����
			wearlist.remove(item.getname());
			h[i] -= item.getbuff();
			Integer p = Bag.get(item);
			if (p != null) {
				p = p + 1;
				Bag.put(item, p);

			} else {
				Bag.put(item, 1);
			}
			super.refreshinfo(h);
		}
	}

	public void thowout(Item item) {
		Integer p = Bag.get(item);
		if (p != null) {
			p = p - 1;
			Bag.put(item, p);
			if (p == 0) {
				Bag.remove(item);
			}
		}
	}

	public void pickUp(Monster b) {// ����������Ʒ����
		Item gift = b.getgiftItem();
		Integer num = null;
		for(Item item:Bag.keySet()) {
			if(item.getname().equals(gift.getname())) {
				num=Bag.get(item);
				gift=item;
			}
		}
		if (num == null) {
			Bag.put(gift, 1);
		} else {
			Bag.put(gift, num + 1);
		}
	}

	public Item searchItem(String name) {// ������Ʒ����
		for (Item key : Bag.keySet()) {
			if (key.getname().equals(name)) {
				return key;
			}
		}
		return null;
	}

	public boolean listBag() {
		if (Bag.size() == 0) {
			System.out.println("����Ϊ��");
			return false;
		} else {
			for (Item key : Bag.keySet()) {
				String rr = key.getsort();
				String sort;
				if (rr.equals("Armor"))
					sort = "Defence";
				else if (rr.equals("Weapon"))
					sort = "Attack";
				else
					sort = "HP";
				System.out.println("Item :" + key.getname() + " Num:" + Bag.get(key) + " " + sort + "+" + key.getbuff()
						+ " ���ͣ�" + rr);
			}
			return true;
		}

	}

	public boolean listwearlist() {// չʾװ����
		if (wearlist.size() == 0) {
			System.out.println("װ����Ϊ��");
			return false;
		} else {
			for (String key : wearlist.keySet()) {
				Item wear = wearlist.get(key);
				String rr = wear.getsort();
				String sort;
				if (rr.equals("Armor"))
					sort = "Defence";
				else if (rr.equals("Weapon"))
					sort = "Attack";
				else
					sort = "HP";
				System.out.println(rr + ":" + key + " " + sort + "+" + wear.getbuff());
			}
			return true;
		}
	}

	public String[] file() {
		String[] playerData = new String[3];
		int[] base = this.getinfo();// getinfo��character�ĺ���������hp������������
		playerData[0] = base[0] + " " + base[1] + " " + base[2] + " " + this.level + " " + this.returnlocal()[0] + " "
				+ this.returnlocal()[1] + " " + this.experience + " " + this.getname();
		int Bagsize = Bag.size();
		playerData[1] = Bagsize + " ";
		if (Bagsize != 0) {
			for (Item item : Bag.keySet()) {
				playerData[1] += item.getsort() + " " + item.getbuff() + " " + item.getname() + " " + Bag.get(item)
						+ " ";
			}
		}
		int wearlistsize = wearlist.size();
		playerData[2] = wearlistsize + " ";
		if (wearlistsize != 0) {
			for (String key : wearlist.keySet()) {
				Item item = wearlist.get(key);
				playerData[2] += key + " " + item.getsort() + " " + item.getbuff() + " " + item.getname() + " ";
			}
		}
		return playerData;
	}
}
