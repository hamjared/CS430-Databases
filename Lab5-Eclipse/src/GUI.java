import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class GUI {

	public JFrame frmLibrarySearch;
	private JTextField memberID;
	private JTextField searchField;
	private JTable searchResultTable;
	private JTabbedPane tabbedPane;
	private JLabel idNotFound;
	private JLabel memberGreeting;
	private JPanel newMemberPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	@SuppressWarnings("unused")
	private final Action action = new SwingAction();
	private JTextField newMemberFirstName;
	private JTextField newMemberLastName;
	private JTextField newMemberDOB;
	
	Library library;
	@SuppressWarnings("rawtypes")
	private JComboBox newMemberGender;
	private JLabel newMemberID;
	private JPanel newMemberIDPane;
	private JLabel invalidFirstNameMessage;
	private JLabel invalidLastNameMessage;
	private JLabel invalidDOBMessage;
	private JLabel invalidGenderMessage;
	private JLabel isbnFormatLabel;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
		library = new Library();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frmLibrarySearch = new JFrame();
		frmLibrarySearch.setTitle("Library Search");
		frmLibrarySearch.setBounds(100, 100, 581, 383);
		frmLibrarySearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLibrarySearch.getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tabbedPane.setBounds(0, 0, 565, 344);
		frmLibrarySearch.getContentPane().add(tabbedPane);
		
		JPanel memberIdTab = new JPanel();
		tabbedPane.addTab("Member ID", null, memberIdTab, null);
		memberIdTab.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Your Member ID");
		lblNewLabel.setBounds(6, 10, 209, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		memberIdTab.add(lblNewLabel);
		
		memberID = new JTextField();
		memberID.setBounds(225, 7, 209, 25);
		memberID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		memberIdTab.add(memberID);
		memberID.setColumns(10);
		
		JButton loginWithMemberID = new JButton("Submit");
		loginWithMemberID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		loginWithMemberID.setBounds(444, 6, 92, 27);
		loginWithMemberID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		memberIdTab.add(loginWithMemberID);
		
		idNotFound = new JLabel("Member ID Not Found");
		idNotFound.setFont(new Font("Tahoma", Font.PLAIN, 20));
		idNotFound.setBounds(162, 43, 209, 25);
		idNotFound.setVisible(false);
		memberIdTab.add(idNotFound);
		
		newMemberPane = new JPanel();
		newMemberPane.setVisible(false);
		newMemberPane.setBounds(6, 79, 554, 170);
		memberIdTab.add(newMemberPane);
		newMemberPane.setLayout(null);
		
		newMemberFirstName = new JTextField();
		newMemberFirstName.setBounds(110, 38, 118, 20);
		newMemberFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newMemberPane.add(newMemberFirstName);
		newMemberFirstName.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("First Name");
		lblNewLabel_3.setBounds(0, 41, 96, 14);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newMemberPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Last Name");
		lblNewLabel_4.setBounds(268, 41, 86, 14);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newMemberPane.add(lblNewLabel_4);
		
		newMemberLastName = new JTextField();
		newMemberLastName.setBounds(378, 38, 118, 20);
		newMemberLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newMemberPane.add(newMemberLastName);
		newMemberLastName.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Birthday (m/d/yyyy)");
		lblNewLabel_5.setBounds(0, 85, 156, 17);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newMemberPane.add(lblNewLabel_5);
		
		newMemberDOB = new JTextField();
		newMemberDOB.setBounds(142, 83, 86, 20);
		newMemberDOB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newMemberPane.add(newMemberDOB);
		newMemberDOB.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Gender");
		lblNewLabel_6.setBounds(300, 86, 54, 14);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newMemberPane.add(lblNewLabel_6);
		
		newMemberGender = new JComboBox<Object>();
		newMemberGender.setBounds(380, 82, 54, 22);
		newMemberGender.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newMemberGender.setModel(new DefaultComboBoxModel<Object>(new String[] {"", "M", "F"}));
		newMemberPane.add(newMemberGender);
		
		JButton createNewMember = new JButton("Submit");
		createNewMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewMember();
			}
		});
		createNewMember.setBounds(0, 126, 89, 23);
		newMemberPane.add(createNewMember);
		
		JLabel lblNewLabel_7 = new JLabel("Create new membership using form below");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(111, 0, 323, 14);
		newMemberPane.add(lblNewLabel_7);
		
		invalidFirstNameMessage = new JLabel("Error: cannot be blank or contain non letter characters");
		invalidFirstNameMessage.setFont(new Font("Tahoma", Font.PLAIN, 9));
		invalidFirstNameMessage.setForeground(Color.RED);
		invalidFirstNameMessage.setBounds(0, 57, 306, 14);
		invalidFirstNameMessage.setVisible(false);
		newMemberPane.add(invalidFirstNameMessage);
		
		invalidLastNameMessage = new JLabel("Error: cannot blank or contain non letter characters");
		invalidLastNameMessage.setFont(new Font("Tahoma", Font.PLAIN, 9));
		invalidLastNameMessage.setForeground(Color.RED);
		invalidLastNameMessage.setBounds(268, 57, 276, 14);
		invalidLastNameMessage.setVisible(false);
		newMemberPane.add(invalidLastNameMessage);
		
		invalidDOBMessage = new JLabel("Error: Date must be in form m/d/yyyy");
		invalidDOBMessage.setFont(new Font("Tahoma", Font.PLAIN, 9));
		invalidDOBMessage.setForeground(Color.RED);
		invalidDOBMessage.setBounds(0, 105, 228, 14);
		invalidDOBMessage.setVisible(false);
		newMemberPane.add(invalidDOBMessage);
		
		invalidGenderMessage = new JLabel("Error: Must choose an option");
		invalidGenderMessage.setForeground(Color.RED);
		invalidGenderMessage.setFont(new Font("Tahoma", Font.PLAIN, 9));
		invalidGenderMessage.setBounds(300, 105, 134, 14);
		invalidGenderMessage.setVisible(false);
		newMemberPane.add(invalidGenderMessage);
		
		newMemberIDPane = new JPanel();
		newMemberIDPane.setBounds(6, 260, 463, 40);
		memberIdTab.add(newMemberIDPane);
		newMemberIDPane.setVisible(false);
		newMemberIDPane.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Membership Creation Succesfull, Your Member ID is: ");
		lblNewLabel_8.setBounds(0, 2, 551, 40);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newMemberIDPane.add(lblNewLabel_8);
		
		newMemberID = new JLabel("New label");
		newMemberID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newMemberID.setBounds(358, 15, 95, 14);
		newMemberIDPane.add(newMemberID);
		
		JPanel bookSearchTab = new JPanel();
		tabbedPane.addTab("Book Search", null, bookSearchTab, null);
		tabbedPane.setEnabledAt(1, false);
		bookSearchTab.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 34, 550, 41);
		bookSearchTab.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Search By: ");
		lblNewLabel_1.setBounds(6, 10, 76, 19);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel_1);
		
		JRadioButton searchByISBN = new JRadioButton("ISBN");
		searchByISBN.setActionCommand("ISBN");
		searchByISBN.setSelected(true);
		buttonGroup.add(searchByISBN);
		searchByISBN.setBounds(88, 6, 59, 27);
		searchByISBN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(searchByISBN);
		
		JRadioButton searchByTitle = new JRadioButton("Title Contains");
		searchByTitle.setActionCommand("Title");
		buttonGroup.add(searchByTitle);
		searchByTitle.setBounds(153, 6, 113, 27);
		searchByTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(searchByTitle);
		
		JRadioButton searchByAuthor = new JRadioButton("Author");
		searchByAuthor.setActionCommand("Author");
		buttonGroup.add(searchByAuthor);
		searchByAuthor.setBounds(272, 6, 69, 27);
		searchByAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(searchByAuthor);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 85, 550, 41);
		bookSearchTab.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Search Term");
		lblNewLabel_2.setBounds(6, 8, 85, 19);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_2);
		
		searchField = new JTextField();
		searchField.setBounds(97, 7, 348, 20);
		panel_1.add(searchField);
		searchField.setColumns(10);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		searchButton.setBounds(455, 8, 85, 23);
		panel_1.add(searchButton);
		
		isbnFormatLabel = new JLabel("Error: ISBN Format needs to be ##-#####-#####");
		isbnFormatLabel.setForeground(Color.RED);
		isbnFormatLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		isbnFormatLabel.setBounds(97, 27, 236, 14);
		panel_1.add(isbnFormatLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 151, 540, 149);
		bookSearchTab.add(scrollPane);
		
		searchResultTable = new JTable();
		scrollPane.setViewportView(searchResultTable);
		searchResultTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		searchResultTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ISBN", "Title", "Authors"
			}
		));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.setColumnHeaderView(scrollPane_1);
		searchResultTable.getColumnModel().getColumn(0).setResizable(false);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 550, 41);
		bookSearchTab.add(panel_2);
		panel_2.setLayout(null);
		
		memberGreeting = new JLabel("Hello User!");
		memberGreeting.setBounds(6, 6, 335, 19);
		memberGreeting.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_2.add(memberGreeting);
	}
	


	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	
	public void search() {
		String action = buttonGroup.getSelection().getActionCommand();
		clearTableResults();
		switch(action) {
		case "ISBN":
			searchByISBN();
			break;
		case "Title":
			searchByTitle();
			break;
		case "Author":
			searchByAuthor();
			break;
		}
	}

	private void searchByAuthor() {
		System.out.println("Search by Author");
		
	}

	private void searchByTitle() {
		// TODO Auto-generated method stub
		System.out.println("Search by Title");
		this.updateSearchResultsTable(library.searchByTitle(this.searchField.getText().trim()));
		
		
	}

	private void searchByISBN() {
		// TODO Auto-generated method stub
		
		System.out.println("Search by ISBN");
		String isbn = this.searchField.getText().trim();
		if(!validateISBNFormat(isbn)) {
			this.isbnFormatLabel.setVisible(true);
			return;
		}
		
		String[][] searchResults = library.searchByISBN(isbn);
		DefaultTableModel tableModel = (DefaultTableModel) searchResultTable.getModel();
		for (int i = 0 ; i < searchResults.length; i++)
			tableModel.addRow(searchResults[i]);
		
		
		
		
	}
	
	private void updateSearchResultsTable(String[][] newRows ) {
		DefaultTableModel tableModel = (DefaultTableModel) searchResultTable.getModel();
		for (int i = 0 ; i < newRows.length; i++)
			tableModel.addRow(newRows[i]);
	}
	
	private void clearTableResults() {
		// TODO Auto-generated method stub
		DefaultTableModel tableModel = (DefaultTableModel) searchResultTable.getModel();
		tableModel.setRowCount(0);
		
		
	}

	private boolean validateISBNFormat(String isbn) {
		// TODO Auto-generated method stub
		return isbn.matches("[0-9]{2}-[0-9]{5}-[0-9]{5}");
	}

	protected void login() {
		// TODO Auto-generated method stub
		System.out.println("Login");
		System.out.println("Member ID = " + memberID.getText());
		String memberName = library.memberIDExists(memberID.getText().trim());
		if(memberName != null) {
			System.out.println("Member Exists");
			tabbedPane.setEnabledAt(1, true);
			tabbedPane.setSelectedIndex(1);
			this.memberGreeting.setText("Hello " + memberName + "!");
		}
		else {
			System.out.println("Member Does Not Exists");
			idNotFound.setVisible(true);
			newMemberPane.setVisible(true);
			
		}
		
	}
	
	private void addNewMember() {
		this.hideErrorMessages();
		boolean validEntries = true;
		if(!validateName(this.newMemberFirstName.getText().trim())) {
			validEntries = false;
			this.invalidFirstNameMessage.setVisible(true);
		}
		if(!validateName(this.newMemberLastName.getText().trim())) {
			validEntries = false;
			this.invalidLastNameMessage.setVisible(true);
		}
		if(!this.validateDateFormat(this.newMemberDOB.getText().trim())) {
			validEntries = false;
			this.invalidDOBMessage.setVisible(true);
		}
		if(((String)this.newMemberGender.getSelectedItem()).trim().isEmpty()) {
			this.invalidGenderMessage.setVisible(true);
			validEntries = false;
		}
		
		if(!validEntries) {
			return;
		}
		String memberID = library.addNewMember(this.newMemberFirstName.getText().trim(), this.newMemberLastName.getText().trim(), this.newMemberDOB.getText().trim(), (String)this.newMemberGender.getSelectedItem());
		this.newMemberID.setText(memberID);
		this.newMemberIDPane.setVisible(true);
		
		
	}
	
	private boolean validateDateFormat(String dateString) {
		SimpleDateFormat inFormatter = new SimpleDateFormat("mm/dd/yyyy");
        try {
			Date date = inFormatter.parse(dateString);
			if (date != null) {
				return true;
			}
			
		} catch (ParseException e) {
		}
		return false;
	}
	
	private boolean validateName(String name) {
		if(name.isEmpty()) {
			return false;
		}
		
		if(!name.matches("[A-Za-z]+")) {
			return false;
		}
		return true;
		
	}
	
	private void hideErrorMessages() {
		this.invalidDOBMessage.setVisible(false);
		this.invalidLastNameMessage.setVisible(false);
		this.invalidFirstNameMessage.setVisible(false);
		this.invalidGenderMessage.setVisible(false);
		this.isbnFormatLabel.setVisible(false);
		
	}
}
