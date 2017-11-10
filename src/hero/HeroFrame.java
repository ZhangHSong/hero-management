package hero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import operationhero.AddHero;
import operationhero.DelHero;
import operationhero.ModifyHero;
import operationhero.QueryHero;
import operationsalary.PriceHero;
/**
 * ������
 * @author HaiSong.Zhang
 *
 */
public class HeroFrame extends JFrame {

	// ���β˵�
	private JTree tree;
	private DefaultMutableTreeNode treeRoot;
	private DefaultMutableTreeNode staffBasicInfo;
	private DefaultMutableTreeNode staffSalary;
	private DefaultMutableTreeNode treeLeaf;
	private TreePath treePath;

	// ���������
	public static JSplitPane splitPaneMain;
	private JPanel panelLeft;
	private JPanel panelRight;
	private JLabel labelWelcome = new JLabel();
	private JScrollPane scrollPaneMain;

	public HeroFrame() {
		this.setTitle("Ӣ����Ϣϵͳ");
		this.setResizable(false);
		ImageIcon icon = new ImageIcon(getClass().getResource("/icon.jpg"));
		this.setIconImage(icon.getImage());
		this.setBounds(200, 100, 1000, 500);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public void initHeroFrame() {

		// ���JTree�˵�
		treeRoot = new DefaultMutableTreeNode("Ӣ����Ϣϵͳ");
		staffBasicInfo = new DefaultMutableTreeNode("Ӣ�ۻ�����Ϣ����");
		staffSalary = new DefaultMutableTreeNode("Ӣ�ۼ۸���Ϣ����");
		// Ӣ�ۻ�����Ϣ
		treeRoot.add(staffBasicInfo);
		treeLeaf = new DefaultMutableTreeNode("���Ӣ����Ϣ");
		staffBasicInfo.add(treeLeaf);
		treeLeaf = new DefaultMutableTreeNode("ɾ��Ӣ����Ϣ");
		staffBasicInfo.add(treeLeaf);
		treeLeaf = new DefaultMutableTreeNode("�޸�Ӣ����Ϣ");
		staffBasicInfo.add(treeLeaf);
		treeLeaf = new DefaultMutableTreeNode("��ѯӢ����Ϣ");
		staffBasicInfo.add(treeLeaf);
		treeRoot.add(staffSalary);
		treeLeaf = new DefaultMutableTreeNode("Ӣ�ۼ۸����");
		staffSalary.add(treeLeaf);

		// ��������tree
		tree = new JTree(treeRoot);
		scrollPaneMain = new JScrollPane(tree);
		scrollPaneMain.setPreferredSize(new Dimension(150, 500));
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		// ����JPanel
		panelLeft = new JPanel();
		panelRight = new JPanel();
		panelLeft.add(scrollPaneMain);
		panelLeft.setBackground(new Color(240, 240, 240));
		labelWelcome.setText("��ӭ����Ӣ����Ϣ����ϵͳ");
		labelWelcome.setFont(new Font("΢���ź�", Font.BOLD, 24));
		labelWelcome.setForeground(Color.ORANGE);
		panelRight.add(labelWelcome);
		panelRight.setBackground(new Color(240, 240, 240));

		// ����JSplitPane�����ò���
		splitPaneMain = new JSplitPane();
		splitPaneMain.setContinuousLayout(true);
		// splitPaneMain.setPreferredSize(new Dimension(150, 500));
		splitPaneMain.setOneTouchExpandable(false);
		splitPaneMain.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPaneMain.setLeftComponent(panelLeft);
		splitPaneMain.setRightComponent(panelRight);
		splitPaneMain.setDividerSize(2);
		splitPaneMain.setDividerLocation(153);

		this.setContentPane(splitPaneMain);
		this.setVisible(true);
		tree.addTreeSelectionListener(new TreeListener());

	}

	public static void main(String[] args) {

		/* ����ϵͳ������ */
		// ȡ�ñ���ϵͳ�Ľ�����
		String UIFace = UIManager.getSystemLookAndFeelClassName();
		// ����ϵͳ���н����뱾��ϵͳ����һ��
		try {
			UIManager.setLookAndFeel(UIFace);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new HeroFrame().initHeroFrame();
	}

	private class TreeListener implements TreeSelectionListener {

		public void valueChanged(TreeSelectionEvent e) {

			DefaultMutableTreeNode defMTN = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
			String nodeInfo = defMTN.toString();
			if (nodeInfo == "Ӣ����Ϣϵͳ") {
				splitPaneMain.setRightComponent(panelRight);
			} else if (nodeInfo == "Ӣ�ۻ�����Ϣ����") {
				// ��ѡ�к�չ����ر�Ҷ�ӽڵ�
				treePath = new TreePath(staffBasicInfo.getPath());
				if (tree.isExpanded(treePath))
					tree.collapsePath(treePath);
				else
					tree.expandPath(treePath);
			} else if (nodeInfo == "���Ӣ����Ϣ") {
				AddHero addHero = new AddHero();
				splitPaneMain.setRightComponent(addHero);
			} else if (nodeInfo == "�޸�Ӣ����Ϣ") {
				ModifyHero modifyHero = new ModifyHero();
				splitPaneMain.setRightComponent(modifyHero);
			} else if (nodeInfo == "ɾ��Ӣ����Ϣ") {
				DelHero delHero = new DelHero();
				splitPaneMain.setRightComponent(delHero);
			} else if (nodeInfo == "��ѯӢ����Ϣ") {
				QueryHero queryHero = new QueryHero();
				splitPaneMain.setRightComponent(queryHero);
			} else if (nodeInfo == "Ӣ�ۼ۸���Ϣ����") {
				// ��ѡ�к�չ����ر�Ҷ�ӽڵ�
				treePath = new TreePath(staffSalary.getPath());
				if (tree.isExpanded(treePath))
					tree.collapsePath(treePath);
				else
					tree.expandPath(treePath);
			} else if (nodeInfo == "Ӣ�ۼ۸����") {
				PriceHero priceHero = new PriceHero();
				splitPaneMain.setRightComponent(priceHero);
			}
		}

	}
}
