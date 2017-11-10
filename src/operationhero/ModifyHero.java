package operationhero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hero.BasicBean;

/**
 * �޸�Ӣ����Ϣ����
 * 
 * @author HaiSong.Zhang
 *
 */
public class ModifyHero extends JPanel implements ItemListener {

	private JLabel labelHeroName = new JLabel("Ӣ��������");
	private JLabel labelAppellation = new JLabel("Ӣ�۳�ν��");
	private JLabel labelPhysical = new JLabel("��������");
	private JLabel labelMagic = new JLabel("ħ��������");
	private JLabel labelDefense = new JLabel("����������");
	private JLabel labelDifficulty = new JLabel("�����Ѷȣ�");
	private JLabel labelCamp = new JLabel("������Ӫ");
	private JLabel labelSort = new JLabel("Ӣ�۶�λ");
	private JLabel labelUp = new JLabel("�޸�Ӣ����Ϣ");
	private JLabel labelMES = new JLabel("ѡ��Ӣ����Ϣ��");
	String[] str1 = { "", "��������", "�����˹", "��ŷ����", "��¶���", "���׶�׿��", "�����", "�ȶ�������" };
	private JComboBox<String> boxCamp = new JComboBox<String>(str1);
	String[] str2 = { "", "�ϵ�", "��Ұ", "�е�", "����", "����" };
	private JComboBox<String> boxSort = new JComboBox<String>(str2);
	BasicBean bb = new BasicBean();
	String[] allName = null;
	private JComboBox<String> boxName = null;
	private JTextField tfHeroName = new JTextField();
	private JTextField tfAppellation = new JTextField();
	private JTextField tfPhysical = new JTextField();
	private JTextField tfMagic = new JTextField();
	private JTextField tfDefense = new JTextField();
	private JTextField tfDifficulty = new JTextField();
	private JButton addBtn = new JButton("�޸�");
	private JButton clearBtn = new JButton("����");
	private JPanel panelUp = new JPanel();
	private JPanel panelCenter = new JPanel();

	/**
	 * ������
	 */
	public ModifyHero() {

		this.setSize(750, 500);
		this.setBackground(new Color(240, 240, 240));

		List<String> list = bb.getAllName();
		allName = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			allName[i] = list.get(i);
		}
		boxName = new JComboBox<String>(allName);
		labelUp.setFont(new Font("΢���ź�", Font.BOLD, 24));
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
		panelCenter.add(labelMES);
		panelCenter.add(boxName);
		panelCenter.add(addBtn);
		panelCenter.add(clearBtn);

		labelHeroName.setBounds(240, 80, 60, 25);
		tfHeroName.setBounds(300, 80, 100, 25);
		tfHeroName.setEditable(false);
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

		labelMES.setBounds(320, 240, 100, 25);
		boxName.setBounds(410, 240, 100, 25);
		boxName.addItemListener(this);

		addBtn.setBounds(350, 280, 60, 25);
		addBtn.setFocusPainted(false);
		addBtn.addActionListener(new AddBtnListener());

		clearBtn.setBounds(430, 280, 60, 25);
		clearBtn.setFocusPainted(false);
		clearBtn.addActionListener(new ClearBtnListener());

		this.setLayout(new BorderLayout());
		this.add(panelUp, BorderLayout.NORTH);
		this.add(panelCenter, BorderLayout.CENTER);
		this.setVisible(true);
	}

	/**
	 * ��Ӱ�ť������Ӧ
	 * 
	 * @author HaiSong.Zhang
	 *
	 */
	private class AddBtnListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			String name = tfHeroName.getText();
			String appellation = tfAppellation.getText();
			String physical = tfPhysical.getText();
			String magic = tfMagic.getText();
			String defense = tfDefense.getText();
			String difficulty = tfDifficulty.getText();
			String camp = boxCamp.getSelectedItem().toString();
			String sort = boxSort.getSelectedItem().toString();
			bb.modifyMessage(name, appellation, physical, magic, defense, difficulty, camp, sort);
		}

	}

	/**
	 * ������ť������Ӧ
	 * 
	 * @author HaiSong.Zhang
	 *
	 */
	private class ClearBtnListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			String HeroInfo[] = bb.selectMessage(boxName.getSelectedItem().toString());
			tfHeroName.setText(HeroInfo[1]);
			tfAppellation.setText(HeroInfo[2]);
			tfPhysical.setText(HeroInfo[3]);
			tfMagic.setText(HeroInfo[4]);
			tfDefense.setText(HeroInfo[5]);
			tfDifficulty.setText(HeroInfo[6]);
			boxCamp.setSelectedItem(HeroInfo[7]);
			boxSort.setSelectedItem(HeroInfo[8]);
		}

	}

	/**
	 * ��ѡ��ѡ��Ӣ����Ϣ�б��ʱִ�и÷���
	 */
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String HeroInfo[] = bb.selectMessage(boxName.getSelectedItem().toString());
			tfHeroName.setText(HeroInfo[1]);
			tfAppellation.setText(HeroInfo[2]);
			tfPhysical.setText(HeroInfo[3]);
			tfMagic.setText(HeroInfo[4]);
			tfDefense.setText(HeroInfo[5]);
			tfDifficulty.setText(HeroInfo[6]);
			boxCamp.setSelectedItem(HeroInfo[7]);
			boxSort.setSelectedItem(HeroInfo[8]);
		}
	}

}
