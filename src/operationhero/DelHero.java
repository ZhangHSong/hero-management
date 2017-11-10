package operationhero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import hero.BasicBean;
import hero.HeroFrame;
import hero.Item;

/**
 * 删除英雄信息界面
 * 
 * @author HaiSong.Zhang
 *
 */
public class DelHero extends JPanel implements ListSelectionListener, ActionListener {

	private JPanel panelUp = new JPanel();
	private JPanel panelCenter = new JPanel();
	private JPanel panelDown = new JPanel();

	private JLabel labelTitle = new JLabel();
	private JLabel label = new JLabel();
	private JTextField nameTxt = new JTextField(12);
	private JButton delButton = new JButton();

	// 定义表格
	private JScrollPane jScrollPane1 = null;// 滚动条面板
	private JTable heroTable = null;// 表格组件
	private ListSelectionModel listSelectionModel = null;
	// 表格头部
	private String[] tableColName = { "英雄排名", "英雄姓名", "英雄称谓", "物理攻击", "魔法攻击", "防御能力", "上手难度", "所属阵营", "英雄定位" };
	private String[][] tableColVaule = null;// 表格的值
	private GridBagLayout gridBag = new GridBagLayout();// 布局管理器
	private GridBagConstraints gridBagCon = null;
/**
 * 构造器
 */
	public DelHero() {
		this.setLayout(new BorderLayout());
		this.setSize(750, 500);
		this.setBackground(new Color(240, 240, 240));
		try { // 虽然程序不要求加异常捕捉，但是可以防范，细节
			initPanelUp();// 上部面板布局
			initPanelCenter();// 中部面板布局
			initPanelDown();// 下部面板布局
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  上部面板
	 */
	public void initPanelUp() {

		labelTitle.setText("删除英雄信息");
		labelTitle.setFont(new Font("微软雅黑", Font.BOLD, 24));
		labelTitle.setForeground(Color.RED);
		labelTitle.setSize(30, 80);
		panelUp.add(labelTitle);
		this.add(panelUp, BorderLayout.NORTH);
	}

	/**
	 *  中部面板
	 */
	public void initPanelCenter() {
		BasicBean bb = new BasicBean();

		panelCenter.setLayout(gridBag);
		List<Item> list = bb.findAllMessage();
		tableColVaule = new String[list.size()][9];
		for (int row = 0; row < list.size(); row++) {
			tableColVaule[row][0] = list.get(row).getId() + "";
			tableColVaule[row][1] = list.get(row).getHeroname();
			tableColVaule[row][2] = list.get(row).getAppellation();
			tableColVaule[row][3] = list.get(row).getPhysical_attack() + "";
			tableColVaule[row][4] = list.get(row).getMagic_attack() + "";
			tableColVaule[row][5] = list.get(row).getDefense() + "";
			tableColVaule[row][6] = list.get(row).getDifficulty() + "";
			tableColVaule[row][7] = list.get(row).getCamp();
			tableColVaule[row][8] = list.get(row).getSort();
		}
		// 初始化表格中的信息
		heroTable = new JTable(tableColVaule, tableColName);
		heroTable.setFont(new Font("微软雅黑", Font.BOLD, 16));
		heroTable.setRowHeight(30);
		heroTable.setPreferredScrollableViewportSize(new Dimension(750, 300));
		listSelectionModel = heroTable.getSelectionModel();
		// 设置表格选择方式:选择一行
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSelectionModel.addListSelectionListener(this);
		jScrollPane1 = new JScrollPane(heroTable);
		jScrollPane1.setPreferredSize(new Dimension(750, 300));
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 1;
		gridBagCon.insets = new Insets(0, 0, 0, 0);
		gridBag.setConstraints(jScrollPane1, gridBagCon);
		panelCenter.add(jScrollPane1);
		this.add(panelCenter, BorderLayout.CENTER);
	}

	/**
	 *  下部面板
	 */
	public void initPanelDown() {
		label.setText("姓名");
		label.setFont(new Font("微软雅黑", 0, 12));
		panelDown.add(label);
		panelDown.add(nameTxt);
		delButton.setText("删除");
		delButton.setFont(new Font("微软雅黑", 0, 12));
		delButton.addActionListener(this);
		panelDown.add(delButton);
		this.add(panelDown, BorderLayout.SOUTH);
		delButton.setEnabled(false); // 细节
	}

	/**
	 *  当选择表中内容时，在框中显示选中的英雄名称
	 */
	public void valueChanged(ListSelectionEvent lse) {
		// 取得选中的行号
		int index = heroTable.getSelectedRow();
		// 在文本框中显示内容
		nameTxt.setText(tableColVaule[index][1]);
		// 设置删除是否可操作
		delButton.setEnabled(true);
	}

	/**
	 *  实现按钮的监听事件
	 */
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == delButton) {
			int flag = JOptionPane.showConfirmDialog(this, "确定要删除该英雄吗？", "退出", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (flag == JOptionPane.YES_OPTION) {
				BasicBean bb = new BasicBean();
				bb.deleteMessage(nameTxt.getText());
				DelHero delHero = new DelHero();
				HeroFrame.splitPaneMain.setRightComponent(delHero);
			}
		}
	}

}
