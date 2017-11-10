package hero;

/**
 * Ӣ�ۼ۸����money���ݿ����
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

	// �ж��ַ����Ƿ�������
	public boolean getStringNull(String str) {
		if (str.equals("") || str == null)
			return true;
		return false;
	}

	// �ж�������Ƿ�Ϊ����
	public boolean isInt(String str) {
		String regex = "[0-9] ";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(str);
		return matcher.matches();
	}

	// ���ݿ����Ӳ���
	public void getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/user";
			conn = DriverManager.getConnection(url, "root", "");
		} catch (ClassNotFoundException e) {
			System.err.println("����ʧ��:" + e.getMessage());
		} catch (SQLException e) {
			System.err.println("����ʧ��:" + e.getMessage());
		}
	}

	// �رս����
	public void closeRs() {
		try {
			if (null != rs)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// �ر�������
	public void closePstmt() {
		try {
			if (null != pstmt)
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// �ر������ݿ�����
	public void closeConn() {
		try {
			if (null != conn)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ��������Ӣ����Ϣ
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

	// ��������Ӣ������
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

	// �޸�Ӣ����Ϣ
	public void modifySalary(String heroname, String price1, String gold1) {
		try {
			if (getStringNull(price1) && isInt(price1)) {
				JOptionPane.showMessageDialog(null, "����������Ӣ�۵�ȯ", "����", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getStringNull(gold1) && isInt(gold1)) {
				JOptionPane.showMessageDialog(null, "����������Ӣ�۽��", "����", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				int price = Integer.valueOf(price1);
				int gold = Integer.valueOf(gold1);
				// �������д���ˣ�û��where���ɸѡ�����ȫ��������
				String sql = "UPDATE money SET  price = ? , gold = ? WHERE heroname = ? ";
				getConnection();
				// num++;// ��ż�һ /
				pstmt = conn.prepareStatement(sql);
				// pstmt.setInt(1, num);
				// pstmt.setString(1, heroname);
				pstmt.setInt(1, price);
				pstmt.setInt(2, gold);
				pstmt.setString(3, heroname);
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "�ɹ�����һ���¼�¼��");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "�������ָ�ʽ����", "����", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "����ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
		} finally {
			closePstmt();
			closeConn();
		}
	}
}
