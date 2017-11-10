package hero;
/**
 * heroÊý¾Ý¿âJavaBean
 * @author HaiSong.Zhang
 *
 */
public class Item {
	private int Id;
	private String heroname;
	private String appellation;
	private int physical_attack;
	private int magic_attack;
	private int defense;
	private int difficulty;
	private String camp;
	private String sort;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

	public String getHeroname() {
		return heroname;
	}

	public void setHeroname(String heroname) {
		this.heroname = heroname;
	}

	public String getAppellation() {
		return appellation;
	}

	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}

	public int getPhysical_attack() {
		return physical_attack;
	}

	public void setPhysical_attack(int physical_attack) {
		this.physical_attack = physical_attack;
	}

	public int getMagic_attack() {
		return magic_attack;
	}

	public void setMagic_attack(int magic_attack) {
		this.magic_attack = magic_attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String getCamp() {
		return camp;
	}

	public void setCamp(String camp) {
		this.camp = camp;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
