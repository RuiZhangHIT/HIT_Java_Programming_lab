package textgame;

public class Monster extends Character {
	
	private int giftExperience;
	private Item giftItem;
	
	public Monster(int healthpoint, int attack, int defence, int[] location,int giftExperience,Item giftItem,String name) {
		super(healthpoint, attack, defence, location,name);
		this.giftExperience=giftExperience;
		this.giftItem=giftItem;
	}
	
	public int getgiftExperience() {
		return this.giftExperience;
	}
	
	public Item getgiftItem() {
		return this.giftItem;
	}

}
