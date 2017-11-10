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
 * 主界面
 * @author HaiSong.Zhang
 *
 */
public class HeroFrame extends JFrame {

	// 树形菜单
	private JTree tree;
	private DefaultMutableTreeNode treeRoot;
	private DefaultMutableTreeNode staffBasicInfo;
	private DefaultMutableTreeNode staffSalary;
	private DefaultMutableTreeNode treeLeaf;
	private TreePath treePath;

	// 主界面面板
	public static JSplitPane splitPaneMain;
	private JPanel panelLeft;
	private JPanel panelRight;
	private JLabel labelWelcome = new JLabel();
	private JScrollPane scrollPaneMain;

	public HeroFrame() {
		this.setTitle("英雄信息系统");
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

		// 添加JTree菜单
		treeRoot = new DefaultMutableTreeNode("英雄信息系统");
		staffBasicInfo = new DefaultMutableTreeNode("英雄基本信息管理");
		staffSalary = new DefaultMutableTreeNode("英雄价格信息管理");
		// 英雄基本信息
		treeRoot.add(staffBasicInfo);
		treeLeaf = new DefaultMutableTreeNode("添加英雄信息");
		staffBasicInfo.add(treeLeaf);
		treeLeaf = new DefaultMutableTreeNode("删除英雄信息");
		staffBasicInfo.add(treeLeaf);
		treeLeaf = new DefaultMutableTreeNode("修改英雄信息");
		staffBasicInfo.add(treeLeaf);
		treeLeaf = new DefaultMutableTreeNode("查询英雄信息");
		staffBasicInfo.add(treeLeaf);
		treeRoot.add(staffSalary);
		treeLeaf = new DefaultMutableTreeNode("英雄价格管理");
		staffSalary.add(treeLeaf);

		// 生成左侧的tree
		tree = new JTree(treeRoot);
		scrollPaneMain = new JScrollPane(tree);
		scrollPaneMain.setPreferredSize(new Dimension(150, 500));
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		// 生成JPanel
		panelLeft = new JPanel();
		panelRight = new JPanel();
		panelLeft.add(scrollPaneMain);
		panelLeft.setBackground(new Color(240, 240, 240));
		labelWelcome.setText("欢迎来到英雄信息管理系统");
		labelWelcome.setFont(new Font("微软雅黑", Font.BOLD, 24));
		labelWelcome.setForeground(Color.ORANGE);
		panelRight.add(labelWelcome);
		panelRight.setBackground(new Color(240, 240, 240));

		// 生成JSplitPane并设置参数
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

		/* 设置系统界面风格 */
		// 取得本地系统的界面风格
		String UIFace = UIManager.getSystemLookAndFeelClassName();
		// 设置系统运行界面与本地系统界面一致
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
			if (nodeInfo == "英雄信息系统") {
				splitPaneMain.setRightComponent(panelRight);
			} else if (nodeInfo == "英雄基本信息管理") {
				// 当选中后展开或关闭叶子节点
				treePath = new TreePath(staffBasicInfo.getPath());
				if (tree.isExpanded(treePath))
					tree.collapsePath(treePath);
				else
					tree.expandPath(treePath);
			} else if (nodeInfo == "添加英雄信息") {
				AddHero addHero = new AddHero();
				splitPaneMain.setRightComponent(addHero);
			} else if (nodeInfo == "修改英雄信息") {
				ModifyHero modifyHero = new ModifyHero();
				splitPaneMain.setRightComponent(modifyHero);
			} else if (nodeInfo == "删除英雄信息") {
				DelHero delHero = new DelHero();
				splitPaneMain.setRightComponent(delHero);
			} else if (nodeInfo == "查询英雄信息") {
				QueryHero queryHero = new QueryHero();
				splitPaneMain.setRightComponent(queryHero);
			} else if (nodeInfo == "英雄价格信息管理") {
				// 当选中后展开或关闭叶子节点
				treePath = new TreePath(staffSalary.getPath());
				if (tree.isExpanded(treePath))
					tree.collapsePath(treePath);
				else
					tree.expandPath(treePath);
			} else if (nodeInfo == "英雄价格管理") {
				PriceHero priceHero = new PriceHero();
				splitPaneMain.setRightComponent(priceHero);
			}
		}

	}
}
