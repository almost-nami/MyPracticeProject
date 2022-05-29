package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

class MyOrder extends JFrame implements ActionListener{
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lblImg;
	private JButton btnInput;
	private JButton btnOrder;
	private JButton btnClose;
	private JRadioButton[] sizes;
	private JRadioButton[] coffees;
	private JRadioButton[] cafes;
	private JCheckBox[] customs;
	private String[] arrSize = {"Tall", "Grande", "Venti"};
	private String[] arrCoffee = {"Americano", "Latte", "Cappuccino"};
	private String[] arrCaffeine = {"Caffeine", "Half-Decaf", "Decaf"};
	private String[] arrCustom = {
				"Extra Shot", "Vanilla Syrup", "Hazenul Syrup", "Whip Cream", "Espresso Cream", "Caramel Drizzle"
			};
	
	public MyOrder() {
		init();
		setDisplay();
		addListeners();
		showFrame();
	}
	private void init() {
		Font font = new Font("Franklin Gothic Heavy", Font.PLAIN, 30);
		lbl1 = new JLabel("Choice your tasty.");
		lbl1.setFont(font);
		lbl2 = new JLabel("음료가 나오면 닉네임으로 불러드립니다.");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image logo = kit.getImage("coffee2.png");
		Image newLogo = logo.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		lblImg = new JLabel(new ImageIcon(newLogo));
		
		ButtonGroup group1 = new ButtonGroup();
		sizes = new JRadioButton[arrSize.length];
		for(int idx=0; idx<sizes.length ; idx++) {
			sizes[idx] = new JRadioButton(arrSize[idx]);
			group1.add(sizes[idx]);
		}
		
		ButtonGroup group2 = new ButtonGroup();
		coffees = new JRadioButton[arrCoffee.length];
		for(int idx=0; idx<coffees.length; idx++) {
			coffees[idx] = new JRadioButton(arrCoffee[idx]);
			group2.add(coffees[idx]);
		}
		
		ButtonGroup group3 = new ButtonGroup();
		cafes = new JRadioButton[arrCaffeine.length];
		for(int idx=0; idx<cafes.length; idx++) {
			cafes[idx] = new JRadioButton(arrCaffeine[idx]);
			group3.add(cafes[idx]);
		}
		
		customs = new JCheckBox[arrCustom.length];
		for(int idx=0; idx<customs.length; idx++) {
			customs[idx] = new JCheckBox(arrCustom[idx]);
		}
		
		btnInput = new JButton("닉네임");
		btnOrder = new JButton("주문");
		btnClose = new JButton("취소");
	}
	private void setDisplay() {
		ImageIcon icon = new ImageIcon("coffee.png");
		Image newIcon = icon.getImage();
		setIconImage(newIcon);
		
		JPanel pnlNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlNorth.add(lblImg);
		pnlNorth.add(lbl1);
		
		JPanel pnlSize = getPanel("Size", sizes);
		JPanel pnlCoff = getPanel("Coffee", coffees);
		JPanel pnlCafe = getPanel("Caffeine", cafes);
		JPanel pnlCus = getPanel("Custom", customs);
		
		JPanel pnlCenter = new JPanel(new BorderLayout());
		pnlCenter.add(pnlSize, BorderLayout.NORTH);
		pnlCenter.add(pnlCoff, BorderLayout.CENTER);
		pnlCenter.add(pnlCafe, BorderLayout.SOUTH);
		
		JPanel pnlTotal = new JPanel(new BorderLayout());
		pnlTotal.add(pnlCenter, BorderLayout.CENTER);
		pnlTotal.add(pnlCus, BorderLayout.SOUTH);
		
		JPanel pnlOrder = new JPanel();
		pnlOrder.add(lbl2);
		
		JPanel pnlBtn = new JPanel();
		pnlBtn.add(btnInput);
		pnlBtn.add(btnOrder);
		pnlBtn.add(btnClose);
		
		JPanel pnlSouth = new JPanel(new BorderLayout());
		pnlSouth.add(pnlOrder, BorderLayout.NORTH);
		pnlSouth.add(pnlBtn, BorderLayout.SOUTH);
		
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlTotal, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}
	private JPanel getPanel(String title, JToggleButton... btns) {
		JPanel pnl;
		if(btns.length <= 3) {
			pnl = new JPanel(new GridLayout(1,0));
			for(JToggleButton btn : btns) {
				pnl.add(btn);
			}
			pnl.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1), title));
		} else{
			pnl = new JPanel(new GridLayout(2,0));
			for(JToggleButton btn : btns) {
				pnl.add(btn);
			}
			pnl.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1), title));
		}
		return pnl;
	}

	private void addListeners() {
		btnInput.addActionListener(this);
		btnOrder.addActionListener(this);
		btnClose.addActionListener(this);
		WindowListener wListener = new WindowAdapter(){
			public void windowClosing(WindowEvent we) {
				close();
			}
		};
		addWindowListener(wListener);
	}
	public void actionPerformed(ActionEvent ae) {
		Object src = ae.getSource();
		if(src != btnClose) {
			if(src == btnOrder) {
				int choice = JOptionPane.showConfirmDialog(
					this, "주문하시겠습니까?", "SirenOrder", 
					JOptionPane.YES_NO_OPTION, 
					JOptionPane.INFORMATION_MESSAGE
				);
				if(choice == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(
						this, "주문이 완료되었습니다.", "주문완료", 
						JOptionPane.INFORMATION_MESSAGE
					);
					System.exit(0);
				}
			} else {
				String input = JOptionPane.showInputDialog(
					this, "닉네임을 입력하세요.", "What is your Nickname", 
					JOptionPane.QUESTION_MESSAGE
				);
				lbl2.setText("닉네임 : " + input);
			}
		} else {
			close();
		}
	}
	private void close() {
		int choice = JOptionPane.showConfirmDialog(
			this, 
			"종료하시겠습니까?",
			"Cancel your order.",
			JOptionPane.YES_NO_OPTION
		);
		if(choice == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	private void showFrame() {
		setTitle("Order List");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	public static void main(String[] args) {
		new MyOrder();
	}
}

