package operationsalary;

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

import hero.HeroFrame;
import hero.ItemSalary;
import hero.SalaryBean;

/**
 * 修改英雄价格操作
 * 
 * @author HaiSong.Zhang
 *
 */
public class PriceHero extends JPanel implements ListSelectionListener, ActionListener {
	private JPanel panelUp = new JPanel();
	private JPanel panelCenter = new JPanel();
	private JPanel panelDown = new JPanel();

	private JLabel labelTitle = new JLabel();
	private JLabel label = new JLabel();
	private JLabel label1 = new JLabel();
	private JLabel label11 = new JLabel();
	private JLabel labe2 = new JLabel();
	private JLabel labe22 = new JLabel();
	private JTextField nameTxt = new JTextField(12);
	private JTextField txt1 = new JTextField(12);
	private JTextField txt11 = new JTextField(12);
	private JTextField txt2 = new JTextField(12);
	private JTextField txt22 = new JTextField(12);
	private JButton Button0 = new JButton("查询");
	private JButton Button1 = new JButton("确定");
	private JButton Button2 = new JButton("撤销");
	SalaryBean sb = new SalaryBean();

	// 定义表格
	private JScrollPane jScrollPanel = null;// 滚动条面板
	private JTable heroTable = null;// 表格组件
	private ListSelectionModel listSelectionModel = null;
	// 表格头部
	private String[] tableColName = { "英雄排名", "英雄姓名", "点券", "金币" };
	private String[][] tableColVaule = null;// 表格的值
	private GridBagLayout gridBag = new GridBagLayout();// 布局管理器
	private GridBagConstraints gridBagCon = null;

	public PriceHero() {
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

	// 上部面板
	private void initPanelUp() {
		SalaryBean sb = new SalaryBean();
		panelUp.setLayout(gridBag);
		labelTitle.setText("修改英雄价格");
		labelTitle.setFont(new Font("微软雅黑", Font.BOLD, 24));
		labelTitle.setForeground(Color.RED);
		labelTitle.setSize(30, 80);
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 0;
		gridBagCon.insets = new Insets(0, 10, 0, 10);
		gridBag.setConstraints(labelTitle, gridBagCon);
		panelUp.add(labelTitle);
		// 获取英雄的所有信息
		List<ItemSalary> list = sb.findAllMessage();
		tableColVaule = new String[list.size()][4];
		for (int row = 0; row < list.size(); row++) {
			tableColVaule[row][0] = list.get(row).getId() + "";
			tableColVaule[row][1] = list.get(row).getHeroname();
			tableColVaule[row][2] = list.get(row).getPrice() + "";
			tableColVaule[row][3] = list.get(row).getGold() + "";
		}
		// 初始化表格中的信息
		heroTable = new JTable(tableColVaule, tableColName);
		heroTable.setFont(new Font("微软雅黑", Font.BOLD, 16));
		heroTable.setRowHeight(30);
		heroTable.setPreferredScrollableViewportSize(new Dimension(450, 300));
		listSelectionModel = heroTable.getSelectionModel();
		// 设置表格选择方式:选择一行
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSelectionModel.addListSelectionListener(this);
		jScrollPanel = new JScrollPane(heroTable);
		jScrollPanel.setPreferredSize(new Dimension(450, 300));
		gridBagCon = new GridBagConstraints();
		gridBagCon.gridx = 0;
		gridBagCon.gridy = 1;
		gridBagCon.insets = new Insets(0, 0, 0, 0);
		gridBag.setConstraints(jScrollPanel, gridBagCon);
		panelUp.add(jScrollPanel);
		this.add(panelUp, BorderLayout.NORTH);
	}

	// 中部面板
	private void initPanelCenter() {
		panelCenter.setLayout(null);
		label.setText("输入要查询的英雄的姓名：");
		label.setFont(new Font("微软雅黑", 0, 12));
		label.setBounds(250, 10, 150, 25);
		nameTxt.setBounds(400, 10, 100, 25);
		Button0.setFont(new Font("微软雅黑", 0, 12));
		Button0.setBounds(520, 10, 70, 25);
		Button0.addActionListener(new NameTxtListener());
		label1.setText("调整前点券：");
		label1.setFont(new Font("微软雅黑", 0, 12));
		label1.setBounds(220, 40, 100, 25);
		txt1.setFont(new Font("微软雅黑", 0, 12));
		txt1.setBounds(320, 40, 100, 25);
		txt1.setEditable(false);
		label11.setText("调整后点券：");
		label11.setFont(new Font("微软雅黑", 0, 12));
		label11.setBounds(450, 40, 100, 25);
		txt11.setFont(new Font("微软雅黑", 0, 12));
		txt11.setBounds(550, 40, 100, 25);
		labe2.setText("调整前金币：");
		labe2.setFont(new Font("微软雅黑", 0, 12));
		labe2.setBounds(220, 70, 100, 25);
		txt2.setFont(new Font("微软雅黑", 0, 12));
		txt2.setBounds(320, 70, 100, 25);
		txt2.setEditable(false);
		labe22.setText("调整后金币：");
		labe22.setFont(new Font("微软雅黑", 0, 12));
		labe22.setBounds(450, 70, 100, 25);
		txt22.setFont(new Font("微软雅黑", 0, 12));
		txt22.setBounds(550, 70, 100, 25);
		panelCenter.add(label);
		panelCenter.add(nameTxt);
		panelCenter.add(Button0);
		panelCenter.add(label1);
		panelCenter.add(txt1);
		panelCenter.add(label11);
		panelCenter.add(txt11);
		panelCenter.add(labe2);
		panelCenter.add(txt2);
		panelCenter.add(labe22);
		panelCenter.add(txt22);
		this.add(panelCenter, BorderLayout.CENTER);
	}

	// 下部面板
	private void initPanelDown() {
		Button1.setFont(new Font("微软雅黑", 0, 12));
		Button1.addActionListener(this);
		panelDown.add(Button1);
		Button2.setFont(new Font("微软雅黑", 0, 12));
		Button2.addActionListener(new Button2Listener());
		panelDown.add(Button2);
		this.add(panelDown, BorderLayout.SOUTH);
	}

	// 当选择表中内容时，在框中显示选中的英雄名称
	public void valueChanged(ListSelectionEvent lse) {
		// 取得选中的行号
		int index = heroTable.getSelectedRow();
		// 在文本框中显示内容
		nameTxt.setText(tableColVaule[index][1]);
		txt1.setText(tableColVaule[index][2]);
		txt2.setText(tableColVaule[index][3]);
		// 设置删除是否可操作
		// delButton.setEnabled(true);
	}

	/**
	 * 内部类监听查询按钮
	 * 
	 * @author HaiSong.Zhang
	 *
	 */
	private class NameTxtListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = nameTxt.getText();
			if (name == "" || name == null) {
				JOptionPane.showMessageDialog(null, "输入为空！请重新输入");
			} else {
				for (int i = 0; i < heroTable.getRowCount(); i++) {
					if (name.equals(heroTable.getValueAt(i, 1).toString())) {
						heroTable.setRowSelectionInterval(i, i);
					}
				}
			}
		}
	}

	// 确定按钮动作响应
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == Button1) {
			int flag = JOptionPane.showConfirmDialog(this, "确定要修改该英雄的价格吗？", "退出", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (flag == JOptionPane.YES_OPTION) {
				String name = nameTxt.getText();
				String price = txt11.getText();
				String gold = txt22.getText();
				sb.modifySalary(name, price, gold);
				PriceHero priceHero = new PriceHero();
				HeroFrame.splitPaneMain.setRightComponent(priceHero);
			}
		}

	}

	/**
	 * 内部类监听撤销按钮
	 * 
	 * @author HaiSong.Zhang
	 *
	 */
	private class Button2Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			txt11.setText(null);
			txt22.setText(null);
		}

	}

}
