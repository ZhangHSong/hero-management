package hero;

/**
 * moneyÊý¾Ý¿âJavaBean
 * 
 * @author HaiSong.Zhang
 *
 */
public class ItemSalary {
	private int Id;
	private String heroname;
	private int price;
	private int gold;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getHeroname() {
		return heroname;
	}

	public void setHeroname(String heroname) {
		this.heroname = heroname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

}
