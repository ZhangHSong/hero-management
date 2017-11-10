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
 * Ӣ����Ϣ����hero���ݿ����
 * 
 * @author HaiSong.Zhang
 *
 */
public class BasicBean {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String heroname = null;// Ӣ������
	// private int Id = 0; // ���
	// private static int num = 0;// ��̬������Id��ֵ

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

	/**
	 * ���ʵ�ֲ���
	 * 
	 * @param str
	 * @return
	 */
	// �ж�������Ƿ�Ϊ����
	public boolean isString(String str) {
		String regex = "[\u4e00-\u9fa5] ";
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

	// ���Ӣ����Ϣ
	public void insertMessage(String heroname, String appellation, String physical_attack, String magic_attack,
			String defense, String difficulty, String camp, String sort) {
		try {
			if (getStringNull(heroname) && isString(heroname)) {
				JOptionPane.showMessageDialog(null, "����������Ӣ������", "����", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getStringNull(appellation) && isString(appellation)) {
				JOptionPane.showMessageDialog(null, "����������Ӣ�۳�ν", "����", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getStringNull(physical_attack) && isInt(physical_attack)) {
				JOptionPane.showMessageDialog(null, "����������Ӣ��������", "����", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getStringNull(magic_attack) && isInt(magic_attack)) {
				JOptionPane.showMessageDialog(null, "����������Ӣ�۷�������", "����", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getStringNull(defense) && isInt(defense)) {
				JOptionPane.showMessageDialog(null, "����������Ӣ�۷�������", "����", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getStringNull(difficulty) && isInt(difficulty)) {
				JOptionPane.showMessageDialog(null, "����������Ӣ�������Ѷ�", "����", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				int physical = Integer.valueOf(physical_attack);
				int magic = Integer.valueOf(magic_attack);
				int Idefense = Integer.valueOf(defense);
				int Idifficulty = Integer.valueOf(difficulty);

				String sql = "INSERT INTO hero( heroname,  appellation,  physical_attack, magic_attack,"
						+ "	defense, difficulty, camp,  sort)  VALUES(?,?,?,?,?,?,?,?)";

				getConnection();
				// num++;// ��ż�һ /
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
				JOptionPane.showMessageDialog(null, "�ɹ����һ���¼�¼��");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "�������ָ�ʽ����", "����", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "���ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
		} finally {
			closePstmt();
			closeConn();
		}
	}

	// ����Ӣ�����ƣ�ɾ��Ӣ�ۼ�¼
	public void deleteMessage(String heroname) {
		// ����һ��bug��sql�������ģ���������ĵģ�Сbugȴ�����ң�
		String sql = "DELETE FROM hero WHERE heroname = ?";
		try {
			getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, heroname);
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "�ɹ�ɾ��һ����¼��");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
		} finally {
			closePstmt();
			closeConn();
		}
	}

	// �޸�Ӣ����Ϣ
	public void modifyMessage(String heroname, String appellation, String physical_attack, String magic_attack,
			String defense, String difficulty, String camp, String sort) {
		try {
			if (getStringNull(appellation) && isString(appellation)) {
				JOptionPane.showMessageDialog(null, "����������Ӣ�۳�ν", "����", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getStringNull(physical_attack) && isInt(physical_attack)) {
				JOptionPane.showMessageDialog(null, "����������Ӣ��������", "����", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getStringNull(magic_attack) && isInt(magic_attack)) {
				JOptionPane.showMessageDialog(null, "����������Ӣ�۷�������", "����", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getStringNull(defense) && isInt(defense)) {
				JOptionPane.showMessageDialog(null, "����������Ӣ�۷�������", "����", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getStringNull(difficulty) && isInt(difficulty)) {
				JOptionPane.showMessageDialog(null, "����������Ӣ�������Ѷ�", "����", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				int physical = Integer.valueOf(physical_attack);
				int magic = Integer.valueOf(magic_attack);
				int Idefense = Integer.valueOf(defense);
				int Idifficulty = Integer.valueOf(difficulty);
				// �������д���ˣ�û��where���ɸѡ�����ȫ��������
				String sql = "UPDATE hero SET  appellation = ?, physical_attack = ?, "
						+ "magic_attack = ?, defense = ?, difficulty = ?, camp = ?, sort = ? WHERE heroname = ? ";
				getConnection();
				// num++;// ��ż�һ /
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

	// ����Ӣ����������Ӣ����Ϣ
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

	// ��������Ӣ����Ϣ
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

	// ��������Ӣ������
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
