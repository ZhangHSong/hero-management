package operationhero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hero.BasicBean;

/**
 * 添加英雄信息界面
 * 
 * @author HaiSong.Zhang
 *
 */
public class AddHero extends JPanel {

	private JLabel labelHeroName = new JLabel("英雄姓名:");
	private JLabel labelAppellation = new JLabel("英雄称谓:");
	private JLabel labelPhysical = new JLabel("物理攻击:");
	private JLabel labelMagic = new JLabel("魔法攻击:");
	private JLabel labelDefense = new JLabel("防御能力:");
	private JLabel labelDifficulty = new JLabel("上手难度:");
	private JLabel labelCamp = new JLabel("所属阵营:");
	private JLabel labelSort = new JLabel("英雄定位:");
	private JLabel labelUp = new JLabel("添加英雄信息");
	String[] str1 = { "", "德玛西亚", "洛克萨斯", "艾欧尼亚", "班德尔城", "弗雷尔卓德", "巨神峰", "比尔吉沃特" };
	private JComboBox<String> boxCamp = new JComboBox<String>(str1);
	String[] str2 = { "", "上单", "打野", "中单", "射手", "辅助" };
	private JComboBox<String> boxSort = new JComboBox<String>(str2);
	private JTextField tfHeroName = new JTextField();
	private JTextField tfAppellation = new JTextField();
	private JTextField tfPhysical = new JTextField();
	private JTextField tfMagic = new JTextField();
	private JTextField tfDefense = new JTextField();
	private JTextField tfDifficulty = new JTextField();
	private JButton addBtn = new JButton("增加");
	private JButton clearBtn = new JButton("清空");
	private JPanel panelUp = new JPanel();
	private JPanel panelCenter = new JPanel();
	private Font font = new Font("微软雅黑", Font.BOLD, 24);
	BasicBean bb = new BasicBean();

	/**
	 * 构造方法
	 */
	public AddHero() {

		this.setSize(750, 500);
		this.setBackground(new Color(240, 240, 240));

		labelUp.setFont(font);
		labelUp.setForeground(Color.RED);
		labelUp.setSize(30, 80);
		panelUp.add(labelUp);

		panelCenter.setLayout(null);
		panelCenter.add(labelHeroName);
		panelCenter.add(tfHeroName);

		panelCenter.add(labelAppellation);
		panelCenter.add(tfAppellation);
		panelCenter.add(labelPhysical);
		panelCenter.add(tfPhysical);
		panelCenter.add(labelMagic);
		panelCenter.add(tfMagic);
		panelCenter.add(labelDefense);
		panelCenter.add(tfDefense);
		panelCenter.add(labelDifficulty);
		panelCenter.add(tfDifficulty);
		panelCenter.add(labelCamp);
		panelCenter.add(boxCamp);
		panelCenter.add(labelSort);
		panelCenter.add(boxSort);
		panelCenter.add(addBtn);
		panelCenter.add(clearBtn);

		labelHeroName.setBounds(240, 80, 60, 25);
		tfHeroName.setBounds(300, 80, 100, 25);
		labelAppellation.setBounds(410, 80, 60, 25);
		tfAppellation.setBounds(470, 80, 100, 25);

		labelPhysical.setBounds(240, 120, 60, 25);
		tfPhysical.setBounds(300, 120, 100, 25);
		labelMagic.setBounds(410, 120, 60, 25);
		tfMagic.setBounds(470, 120, 100, 25);

		labelDefense.setBounds(240, 160, 60, 25);
		tfDefense.setBounds(300, 160, 100, 25);
		labelDifficulty.setBounds(410, 160, 60, 25);
		tfDifficulty.setBounds(470, 160, 100, 25);

		labelCamp.setBounds(240, 200, 60, 25);
		boxCamp.setBounds(300, 200, 100, 25);
		labelSort.setBounds(410, 200, 60, 25);
		boxSort.setBounds(470, 200, 100, 25);

		addBtn.setBounds(350, 240, 60, 25);
		addBtn.setFocusPainted(false);
		addBtn.addActionListener(new AddBtnListener());

		clearBtn.setBounds(430, 240, 60, 25);
		clearBtn.setFocusPainted(false);
		clearBtn.addActionListener(new ClearBtnListener());

		this.setLayout(new BorderLayout());
		this.add(panelUp, BorderLayout.NORTH);
		this.add(panelCenter, BorderLayout.CENTER);
		this.setVisible(true);
		// tfHeroName.requestFocus(); 不行
	}

	/**
	 * 添加按钮动作响应
	 * 
	 * @author HaiSong.Zhang
	 *
	 */
	private class AddBtnListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			String heroname = tfHeroName.getText();
			String appellation = tfAppellation.getText();
			String physical = tfPhysical.getText();
			String magic = tfMagic.getText();
			String defense = tfDefense.getText();
			String difficulty = tfDifficulty.getText();
			String camp = boxCamp.getSelectedItem().toString();
			String sort = boxSort.getSelectedItem().toString();
			bb.insertMessage(heroname, appellation, physical, magic, defense, difficulty, camp, sort);

			tfHeroName.setText(null);
			tfAppellation.setText(null);
			tfPhysical.setText(null);
			tfDefense.setText(null);
			tfDifficulty.setText(null);
			tfMagic.setText(null);
			// 这两个设置没用？
			boxCamp.setSelectedIndex(0);
			boxSort.setSelectedIndex(0);
		}

	}

	/**
	 * 清空按钮动作响应
	 * 
	 * @author HaiSong.Zhang
	 *
	 */
	private class ClearBtnListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			tfHeroName.setText(null);
			tfAppellation.setText(null);
			tfPhysical.setText(null);
			tfDefense.setText(null);
			tfDifficulty.setText(null);
			tfMagic.setText(null);
		}

	}

}