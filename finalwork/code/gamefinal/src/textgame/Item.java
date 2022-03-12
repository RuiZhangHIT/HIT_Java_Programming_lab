package textgame;

public class Item {
	
	private String sort;
	private int buff;
	private String name;
	
	public Item(String sort,int buff, String name) {
		this.sort = sort;
		this.buff = buff;
		this.name = name;
	}
	
	public String getsort() {
		return this.sort;
	}
	
	public int getbuff() {
		return this.buff;
	}
	
	public String getname() {
		return this.name;
	}

	public String toString() {
        return this.getname();
	}

}
