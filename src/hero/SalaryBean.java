package hero;

/**
 * 英雄价格管理money数据库操作
 */
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

public class SalaryBean {

	private Connection conn = null;
	private PreparedStatement pstmt;
	private ResultSet rs = null;
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

	// 返回所有英雄信息
	public List<ItemSalary> findAllMessage() {
		List<ItemSalary> list = new ArrayList<ItemSalary>();
		getConnection();
		String sql = "SELECT * FROM money";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ItemSalary item = new ItemSalary();
				item.setId(rs.getInt("Id"));
				item.setHeroname(rs.getString("heroname"));
				item.setPrice(rs.getInt("price"));
				item.setGold(rs.getInt("gold"));
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
		String sql = "SELECT heroname FROM money";
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

	// 修改英雄信息
	public void modifySalary(String heroname, String price1, String gold1) {
		try {
			if (getStringNull(price1) && isInt(price1)) {
				JOptionPane.showMessageDialog(null, "请重新输入英雄点券", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getStringNull(gold1) && isInt(gold1)) {
				JOptionPane.showMessageDialog(null, "请重新输入英雄金币", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				int price = Integer.valueOf(price1);
				int gold = Integer.valueOf(gold1);
				// 更新语句写错了，没加where语句筛选，结果全部更新了
				String sql = "UPDATE money SET  price = ? , gold = ? WHERE heroname = ? ";
				getConnection();
				// num++;// 编号加一 /
				pstmt = conn.prepareStatement(sql);
				// pstmt.setInt(1, num);
				// pstmt.setString(1, heroname);
				pstmt.setInt(1, price);
				pstmt.setInt(2, gold);
				pstmt.setString(3, heroname);
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
}
