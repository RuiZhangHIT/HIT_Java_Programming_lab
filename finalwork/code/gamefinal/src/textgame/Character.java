package textgame;

public class Character {
	
	private int healthpoint;
	private int attack;
	private int defence;
	private int level;
	private int[] location = new int[2];
	private String name;

	public Character(int healthpoint, int attack, int defence, int[] location2, String name) {// location想用一个int[2]表示，但是还没和卜铎试过
		this.healthpoint = healthpoint;
		this.attack = attack;
		this.defence = defence;
		this.location = location2;
		this.name = name;
	}

	public void print() {// 打印生命值，攻击力，防御
		System.out.print(healthpoint + " " + attack + " " + defence);
	}

	public int[] getinfo() {
		int[] a = { this.healthpoint, this.attack, this.defence };
		return a;
	}

	public int getHP() {
		return this.healthpoint;
	}
	
	public int[] refreshinfo(int[] a) {// 刷新参数，主要是是用来修改怪物的数值的
		this.healthpoint = a[0];
		this.attack = a[1];
		this.defence = a[2];
		return a;
	}
	
	public int[] returnlocal() {
		return this.location;
	}

	public void attack(Character c) {

		int[] b = c.getinfo();
		int harm1 = attack - b[2];
		int harm2 = b[1] - this.defence;
		if (harm1 < 0) {
			harm1 = 0;
		}
		if (harm2 < 0) {
			harm2 = 0;
		}
		b[0] -= harm1;
		while (b[0] > 0 && this.healthpoint > 0) {
			this.healthpoint -= harm2;
			b[0] -= harm1;			
		}
		c.refreshinfo(b);
	}

	public boolean isDead() {
		if (this.healthpoint <= 0) {
			return true;
		} else
			return false;
	}

	public void move(int x, int y) {
		this.location[0] += x;
		this.location[1] += y;
	}
	
	public String getname() {
		return this.name;
	}
	
	public String toString() {
        return this.getname();
	}

}
