package hero;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

/**
 * 英雄信息管理hero数据库操作
 * 
 * @author HaiSong.Zhang
 *
 */
public class BasicBean {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String heroname = null;// 英雄姓名
	// private int Id = 0; // 编号
	// private static int num = 0;// 静态变量给Id赋值

	private Pattern pattern = null;
	private Matcher matcher = null;

	// 判断字符串是否有内容
	public boolean getStringNull(String str) {
		if (str.equals("") || str == null)
			return true;
		return false;
	}

	// 判断输入的是否为整数
	public boolean isInt(String str) {
		String regex = "[0-9] ";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * 这个实现不了
	 * 
	 * @param str
	 * @return
	 */
	// 判断输入的是否为汉字
	public boolean isString(String str) {
		String regex = "[\u4e00-\u9fa5] ";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(str);
		return matcher.matches();
	}

	// 数据库连接操作
	public void getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/user";
			conn = DriverManager.getConnection(url, "root", "");
		} catch (ClassNotFoundException e) {
			System.err.println("连接失败:" + e.getMessage());
		} catch (SQLException e) {
			System.err.println("连接失败:" + e.getMessage());
		}
	}

	// 关闭结果集
	public void closeRs() {
		try {
			if (null != rs)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 关闭语句对象
	public void closePstmt() {
		try {
			if (null != pstmt)
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 关闭与数据库连接
	public void closeConn() {
		try {
			if (null != conn)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 添加英雄信息
	public void insertMessage(String heroname, String appellation, String physical_attack, String magic_attack,
			String defense, String difficulty, String camp, String sort) {
		try {
			if (getStringNull(heroname) && isString(heroname)) {
				JOptionPane.showMessageDialog(null, "请重新输入英雄姓名", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getStringNull(appellation) && isString(appellation)) {
				JOptionPane.showMessageDialog(null, "请重新输入英雄称谓", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getStringNull(physical_attack) && isInt(physical_attack)) {
				JOptionPane.showMessageDialog(null, "请重新输入英雄物理攻击", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getStringNull(magic_attack) && isInt(magic_attack)) {
				JOptionPane.showMessageDialog(null, "请重新输入英雄法术攻击", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getStringNull(defense) && isInt(defense)) {
				JOptionPane.showMessageDialog(null, "请重新输入英雄防守能力", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getStringNull(difficulty) && isInt(difficulty)) {
				JOptionPane.showMessageDialog(null, "请重新输入英雄上手难度", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				int physical = Integer.valueOf(physical_attack);
				int magic = Integer.valueOf(magic_attack);
				int Idefense = Integer.valueOf(defense);
				int Idifficulty = Integer.valueOf(difficulty);

				String sql = "INSERT INTO hero( heroname,  appellation,  physical_attack, magic_attack,"
						+ "	defense, difficulty, camp,  sort)  VALUES(?,?,?,?,?,?,?,?)";

				getConnection();
				// num++;// 编号加一 /
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, heroname);
				pstmt.setString(2, appellation);
				pstmt.setInt(3, physical);
				pstmt.setInt(4, magic);
				pstmt.setInt(5, Idefense);
				pstmt.setInt(6, Idifficulty);
				pstmt.setString(7, camp);
				pstmt.setString(8, sort);
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "成功添加一条新纪录！");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "输入数字格式有误！", "错误", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "添加失败！", "错误", JOptionPane.ERROR_MESSAGE);
		} finally {
			closePstmt();
			closeConn();
		}
	}

	// 根据英雄名称，删除英雄记录
	public void deleteMessage(String heroname) {
		// 出了一个bug：sql语句里面的？打成了中文的！小bug却很难找！
		String sql = "DELETE FROM hero WHERE heroname = ?";
		try {
			getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, heroname);
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "成功删除一条记录！");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "删除失败！", "错误", JOptionPane.ERROR_MESSAGE);
		} finally {
			closePstmt();
			closeConn();
		}
	}

	// 修改英雄信息
	public void modifyMessage(String heroname, String appellation, String physical_attack, String magic_attack,
			String defense, String difficulty, String camp, String sort) {
		try {
			if (getStringNull(appellation) && isString(appellation)) {
				JOptionPane.showMessageDialog(null, "请重新输入英雄称谓", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getStringNull(physical_attack) && isInt(physical_attack)) {
				JOptionPane.showMessageDialog(null, "请重新输入英雄物理攻击", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getStringNull(magic_attack) && isInt(magic_attack)) {
				JOptionPane.showMessageDialog(null, "请重新输入英雄法术攻击", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getStringNull(defense) && isInt(defense)) {
				JOptionPane.showMessageDialog(null, "请重新输入英雄防守能力", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getStringNull(difficulty) && isInt(difficulty)) {
				JOptionPane.showMessageDialog(null, "请重新输入英雄上手难度", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				int physical = Integer.valueOf(physical_attack);
				int magic = Integer.valueOf(magic_attack);
				int Idefense = Integer.valueOf(defense);
				int Idifficulty = Integer.valueOf(difficulty);
				// 更新语句写错了，没加where语句筛选，结果全部更新了
				String sql = "UPDATE hero SET  appellation = ?, physical_attack = ?, "
						+ "magic_attack = ?, defense = ?, difficulty = ?, camp = ?, sort = ? WHERE heroname = ? ";
				getConnection();
				// num++;// 编号加一 /
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, appellation);
				pstmt.setInt(2, physical);
				pstmt.setInt(3, magic);
				pstmt.setInt(4, Idefense);
				pstmt.setInt(5, Idifficulty);
				pstmt.setString(6, camp);
				pstmt.setString(7, sort);
				pstmt.setString(8, heroname);
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "成功更新一条新纪录！");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "输入数字格式有误！", "错误", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "更新失败！", "错误", JOptionPane.ERROR_MESSAGE);
		} finally {
			closePstmt();
			closeConn();
		}
	}

	// 根据英雄姓名查找英雄信息
	public String[] selectMessage(String heroname) {
		String[] message = new String[9];
		String sql = "SELECT *  FROM hero WHERE heroname = ?";
		getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, heroname);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				message[0] = String.valueOf(rs.getInt("Id"));
				message[1] = rs.getString("heroname");
				message[2] = rs.getString("appellation");
				message[3] = rs.getString("physical_attack");
				message[4] = rs.getString("magic_attack");
				message[5] = rs.getString("defense");
				message[6] = rs.getString("difficulty");
				message[7] = rs.getString("camp");
				message[8] = rs.getString("sort");
			} else
				message = null;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeRs();
			closePstmt();
			closeConn();
		}
		return message;
	}

	// 返回所有英雄信息
	public List<Item> findAllMessage() {
		List<Item> list = new ArrayList<Item>();
		getConnection();
		String sql = "SELECT * FROM hero";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setId(rs.getInt("Id"));
				item.setHeroname(rs.getString("heroname"));
				item.setAppellation(rs.getString("appellation"));
				item.setPhysical_attack(rs.getInt("Physical_attack"));
				item.setMagic_attack(rs.getInt("Magic_attack"));
				item.setDefense(rs.getInt("Defense"));
				item.setDifficulty(rs.getInt("Difficulty"));
				item.setCamp(rs.getString("camp"));
				item.setSort(rs.getString("sort"));
				list.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeRs();
			closePstmt();
			closeConn();
		}
		return list;
	}

	// 返回所有英雄名称
	public List<String> getAllName() {
		List<String> list = new ArrayList<String>();
		getConnection();
		String sql = "SELECT heroname FROM hero";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("heroname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeRs();
			closePstmt();
			closeConn();
		}
		return list;
	}
}
