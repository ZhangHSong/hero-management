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
 * ɾ��Ӣ����Ϣ����
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

	// ������
	private JScrollPane jScrollPane1 = null;// ���������
	private JTable heroTable = null;// ������
	private ListSelectionModel listSelectionModel = null;
	// ���ͷ��
	private String[] tableColName = { "Ӣ������", "Ӣ������", "Ӣ�۳�ν", "������", "ħ������", "��������", "�����Ѷ�", "������Ӫ", "Ӣ�۶�λ" };
	private String[][] tableColVaule = null;// ����ֵ
	private GridBagLayout gridBag = new GridBagLayout();// ���ֹ�����
	private GridBagConstraints gridBagCon = null;
/**
 * ������
 */
	public DelHero() {
		this.setLayout(new BorderLayout());
		this.setSize(750, 500);
		this.setBackground(new Color(240, 240, 240));
		try { // ��Ȼ����Ҫ����쳣��׽�����ǿ��Է�����ϸ��
			initPanelUp();// �ϲ���岼��
			initPanelCenter();// �в���岼��
			initPanelDown();// �²���岼��
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  �ϲ����
	 */
	public void initPanelUp() {

		labelTitle.setText("ɾ��Ӣ����Ϣ");
		labelTitle.setFont(new Font("΢���ź�", Font.BOLD, 24));
		labelTitle.setForeground(Color.RED);
		labelTitle.setSize(30, 80);
		panelUp.add(labelTitle);
		this.add(panelUp, BorderLayout.NORTH);
	}

	/**
	 *  �в����
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
		// ��ʼ������е���Ϣ
		heroTable = new JTable(tableColVaule, tableColName);
		heroTable.setFont(new Font("΢���ź�", Font.BOLD, 16));
		heroTable.setRowHeight(30);
		heroTable.setPreferredScrollableViewportSize(new Dimension(750, 300));
		listSelectionModel = heroTable.getSelectionModel();
		// ���ñ��ѡ��ʽ:ѡ��һ��
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
	 *  �²����
	 */
	public void initPanelDown() {
		label.setText("����");
		label.setFont(new Font("΢���ź�", 0, 12));
		panelDown.add(label);
		panelDown.add(nameTxt);
		delButton.setText("ɾ��");
		delButton.setFont(new Font("΢���ź�", 0, 12));
		delButton.addActionListener(this);
		panelDown.add(delButton);
		this.add(panelDown, BorderLayout.SOUTH);
		delButton.setEnabled(false); // ϸ��
	}

	/**
	 *  ��ѡ���������ʱ���ڿ�����ʾѡ�е�Ӣ������
	 */
	public void valueChanged(ListSelectionEvent lse) {
		// ȡ��ѡ�е��к�
		int index = heroTable.getSelectedRow();
		// ���ı�������ʾ����
		nameTxt.setText(tableColVaule[index][1]);
		// ����ɾ���Ƿ�ɲ���
		delButton.setEnabled(true);
	}

	/**
	 *  ʵ�ְ�ť�ļ����¼�
	 */
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == delButton) {
			int flag = JOptionPane.showConfirmDialog(this, "ȷ��Ҫɾ����Ӣ����", "�˳�", JOptionPane.YES_OPTION,
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
