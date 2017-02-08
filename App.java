package app;

import com.sun.java.swing.plaf.gtk.resources.gtk;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultHighlighter;
import javax.swing.undo.UndoManager;
import javax.swing.JFileChooser;




public class App {

	
	private JFrame frame;
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		System.setProperty("file.encoding","UTF-8");
		java.lang.reflect.Field charset = Charset.class.getDeclaredField("defaultCharset");
		charset.setAccessible(true);
		charset.set(null,null);
		App window = new App();
		window.frame.setVisible(true);
                
        }

	public App() {
		initialize();
                
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 451);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("//App//src//app//np1.ico"));
		final JTextArea textArea = new JTextArea();
		final UndoManager manager = new UndoManager();
	    textArea.getDocument().addUndoableEditListener(manager);
	    textArea.setFont(textArea.getFont().deriveFont(20f));
	    textArea.setSize(textArea.getRows()*2, textArea.getColumns()*2);
	    frame.getContentPane().add(textArea, BorderLayout.CENTER);
		
		JScrollPane scroll = new JScrollPane (textArea, 
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		frame.getContentPane().add(scroll);frame.setTitle("محرر النصوص 1.1 نور الهادي علي محمود");
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("ملف");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);
		menu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                
		JMenuItem newFile = new JMenuItem("جديد");
		KeyStroke ctrlNKeyStroke = KeyStroke.getKeyStroke("control N");
	    newFile.setAccelerator(ctrlNKeyStroke);
		
	    final JFileChooser open = new JFileChooser();
	    final JFileChooser SaveAs = new JFileChooser();
	    
	    
	    String filters[] = {"txt","text"};
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES",filters);
	    open.setFileFilter(filter);
		
		JMenuItem openFile = new JMenuItem("فتح");
		KeyStroke ctrlOKeyStroke = KeyStroke.getKeyStroke("control O");
	    openFile.setAccelerator(ctrlOKeyStroke);
		
	    final JMenuItem saveAs = new JMenuItem("حفظ باسم");
		KeyStroke ctrlWKeyStroke = KeyStroke.getKeyStroke("control W");
	    saveAs.setAccelerator(ctrlWKeyStroke);
		
	    final JMenuItem saveFile = new JMenuItem("حفظ");
		KeyStroke ctrlSKeyStroke = KeyStroke.getKeyStroke("control S");
	    saveFile.setAccelerator(ctrlSKeyStroke);
		
	    JMenuItem closeFile = new JMenuItem("إغلاق");
		KeyStroke ctrlQKeyStroke = KeyStroke.getKeyStroke("control Q");
	    closeFile.setAccelerator(ctrlQKeyStroke);
		
	    JMenuItem selectAll = new JMenuItem("تحديد الكل");
		KeyStroke ctrlAKeyStroke = KeyStroke.getKeyStroke("control A");
	    selectAll.setAccelerator(ctrlAKeyStroke);
		
	    JMenuItem undoOperation = new JMenuItem("تراجع");
		KeyStroke ctrlZKeyStroke = KeyStroke.getKeyStroke("control Z");
	    undoOperation.setAccelerator(ctrlZKeyStroke);
		
	    JMenuItem redoOperation = new JMenuItem("تقدم");
		KeyStroke ctrlYKeyStroke = KeyStroke.getKeyStroke("control Y");
	    redoOperation.setAccelerator(ctrlYKeyStroke);
		
	    JMenuItem cutSelected = new JMenuItem("قص");
		KeyStroke ctrlXKeyStroke = KeyStroke.getKeyStroke("control X");
	    cutSelected.setAccelerator(ctrlXKeyStroke);
	    
	    JMenuItem copySelected = new JMenuItem("نسخ");
		KeyStroke ctrlCKeyStroke = KeyStroke.getKeyStroke("control C");
	    copySelected.setAccelerator(ctrlCKeyStroke);
		
	    JMenuItem pasteSelected = new JMenuItem("لصق");
		KeyStroke ctrlVKeyStroke = KeyStroke.getKeyStroke("control V");
	    pasteSelected.setAccelerator(ctrlVKeyStroke);
		
	    JMenuItem selectFont = new JMenuItem("تحديد الخط");
		
	    JMenu textAlignment = new JMenu("تحديد اتجاه الكتابة");
		
	    JMenuItem r2l = new JMenuItem("من اليمين لليسار");
		KeyStroke ctrlRKeyStroke = KeyStroke.getKeyStroke("control R");
	    r2l.setAccelerator(ctrlRKeyStroke);
		
	    JMenuItem l2r = new JMenuItem("من اليسار لليمين");
		KeyStroke ctrlLKeyStroke = KeyStroke.getKeyStroke("control L");
	    l2r.setAccelerator(ctrlLKeyStroke);
		
	    newFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textArea.getText().equals("")) return;
				if (open.getSelectedFile()==null) saveAs.doClick();
				else saveFile.doClick();
				textArea.setText("");
			}
		});
		menu.add(newFile);
		
		openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textArea.getText().equals("")){
					if (open.isValid()) saveFile.doClick();
					else saveAs.doClick();
				}
				StringBuffer buffer = new StringBuffer();
				   
				   int result= open.showOpenDialog(null);
				    if(result==JFileChooser.APPROVE_OPTION)
				    {
				        try {
				            FileReader reader = null;
				            File file=open.getSelectedFile();
				            reader=new FileReader(file);
				            int i=1;
				            while(i!=-1)
				            {
				                i=reader.read();
				                char ch=(char) i;
				                buffer.append(ch);

				            }
				            reader.close();
				            String x = buffer.toString();
				            x = x.substring(0,x.length()-1);
				            textArea.setText(x);
				        } catch (Exception ex) {
				        	JOptionPane.showMessageDialog(null, ex);
				        }

				    }
			}
		});
		
		menu.add(openFile);
		
		
		saveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SaveAs.setApproveButtonText("Save");
			    String files [] = {"txt","text"};
			    FileNameExtensionFilter filter= new FileNameExtensionFilter("Text Files", files);
			    SaveAs.setFileFilter(filter);
			    SaveAs.showOpenDialog(null);
			    File fileName = new File(SaveAs.getSelectedFile() + ".txt");
			    if (fileName.getName().equals("null.txt")) return;
			    try {
			    	PrintWriter outFile = new PrintWriter(fileName);
			        outFile.println(textArea.getText());
		            outFile.flush();
			        outFile.close();
			        } catch (Exception ex) {
			        	JOptionPane.showMessageDialog(null, ex);
			        }
			}
		});
		
		saveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (open.getSelectedFile()==null&&SaveAs.getSelectedFile()==null) {saveAs.doClick(); return;}
				File f;
				if (open.isValid()) f =new File( open.getSelectedFile() + ".txt");
				else f = new File(SaveAs.getSelectedFile() + ".txt");
				
				PrintWriter out = null;
				try {
					out = new PrintWriter(f);
					out.println(textArea.getText());
				} catch (FileNotFoundException e) {	
					JOptionPane.showMessageDialog(null, e);
				}
				out.flush();
				out.close();
			}
		});
		menu.add(saveFile);
		menu.add(saveAs);
		
		closeFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textArea.getText().equals("")){
					if (open.getSelectedFile()!=null||SaveAs.getSelectedFile()!=null) saveFile.doClick();
					else saveAs.doClick();
				}
				String bye = "شكرا الك على استخدام البرنامج بتمنى يكون عجبك \n إلى اللقاء";
				for (int i=1;i<=12;i++) bye+=" \t ";
				JOptionPane.showMessageDialog(null, bye,"إلى اللقاء",JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		});
		menu.add(closeFile);
		
		JMenu menu_1 = new JMenu("تعديل");
		menu_1.setMnemonic(KeyEvent.VK_E);
		menuBar.add(menu_1);
		
		selectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.select(-1, textArea.getRows());

			}
		});
		
		undoOperation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					manager.undo();				
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		menu_1.add(undoOperation);
		
		redoOperation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					manager.redo();				
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		menu_1.add(redoOperation);
		menu_1.add(selectAll);
		
		cutSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.cut();
			}
		});
		menu_1.add(cutSelected);
		
		copySelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.copy();
			}
		});
		menu_1.add(copySelected);
		
		pasteSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.paste();
			}
		});
		menu_1.add(pasteSelected);
		
		JMenu menu_2 = new JMenu("تخصيص");
		menu_2.setMnemonic(KeyEvent.VK_S);
		menuBar.add(menu_2);
		
		selectFont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFontChooser jfc = new JFontChooser();
				jfc.showDialog(textArea);
				textArea.setFont(jfc.getSelectedFont());
			}
		});
		menu_2.add(selectFont);
		
		
		menu_2.add(textAlignment);
		
		r2l.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setLineWrap(true);
		        textArea.setWrapStyleWord(true);
				textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			}
		});
		
		
		textAlignment.add(r2l);
		
		l2r.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setLineWrap(true);
		        textArea.setWrapStyleWord(true);
				textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			}
		});
		textAlignment.add(l2r);
		
		
		
		JMenu menu_33 = new JMenu("تمييز النص المحدد(Highlight)");
		menu_2.add(menu_33);
		
		JMenuItem menuItem_99 = new JMenuItem("أسود");
		menuItem_99.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultHighlighter highlighter =  (DefaultHighlighter)textArea.getHighlighter();
		        DefaultHighlighter.DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter( Color.BLACK );
		        highlighter.setDrawsLayeredHighlights(false); 

		        try
		        {
		        	int start =  textArea.getSelectionStart();
		            int end =    textArea.getSelectionEnd();
		            highlighter.addHighlight(start, end, painter );
		        }
		        catch(Exception e)
		        {
		            System.out.println(e);
		        }
		        highlighter.setDrawsLayeredHighlights(true);
			}
		});
		menu_33.add(menuItem_99);
		
		JMenuItem menuItem_100 = new JMenuItem("أزرق");
		menuItem_100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultHighlighter highlighter =  (DefaultHighlighter)textArea.getHighlighter();
		        DefaultHighlighter.DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter( Color.BLUE );
		        highlighter.setDrawsLayeredHighlights(false); 

		        try
		        {
		        	int start =  textArea.getSelectionStart();
		            int end =    textArea.getSelectionEnd();
		            highlighter.addHighlight(start, end, painter );
		        }
		        catch(Exception e)
		        {
		            System.out.println(e);
		        }
		        highlighter.setDrawsLayeredHighlights(true);

			}
		});
		menu_33.add(menuItem_100);
		
		JMenuItem menuItem_111 = new JMenuItem("رمادي");
		menuItem_111.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultHighlighter highlighter =  (DefaultHighlighter)textArea.getHighlighter();
		        DefaultHighlighter.DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter( Color.GRAY );
		        highlighter.setDrawsLayeredHighlights(false); 

		        try
		        {
		        	int start =  textArea.getSelectionStart();
		            int end =    textArea.getSelectionEnd();
		            highlighter.addHighlight(start, end, painter );
		        }
		        catch(Exception e)
		        {
		            System.out.println(e);
		        }
		        highlighter.setDrawsLayeredHighlights(true);

			}
		});
		menu_33.add(menuItem_111);
		
		JMenuItem menuItem_122 = new JMenuItem("أخضر");
		menuItem_122.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultHighlighter highlighter =  (DefaultHighlighter)textArea.getHighlighter();
		        DefaultHighlighter.DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter( Color.GREEN );
		        highlighter.setDrawsLayeredHighlights(false); 

		        try
		        {
		        	int start =  textArea.getSelectionStart();
		            int end =    textArea.getSelectionEnd();
		            highlighter.addHighlight(start, end, painter );
		        }
		        catch(Exception e)
		        {
		            System.out.println(e);
		        }
		        highlighter.setDrawsLayeredHighlights(true);

			}
		});
		menu_33.add(menuItem_122);
		
		JMenuItem menuItem_133 = new JMenuItem("برتقالي");
		menuItem_133.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultHighlighter highlighter =  (DefaultHighlighter)textArea.getHighlighter();
		        DefaultHighlighter.DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter( Color.ORANGE );
		        highlighter.setDrawsLayeredHighlights(false); 

		        try
		        {
		        	int start =  textArea.getSelectionStart();
		            int end =    textArea.getSelectionEnd();
		            highlighter.addHighlight(start, end, painter );
		        }
		        catch(Exception e)
		        {
		            System.out.println(e);
		        }
		        highlighter.setDrawsLayeredHighlights(true);

			}
		});
		menu_33.add(menuItem_133);
		
		JMenuItem menuItem_144 = new JMenuItem("زهري");
		menuItem_144.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultHighlighter highlighter =  (DefaultHighlighter)textArea.getHighlighter();
		        DefaultHighlighter.DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter( Color.PINK );
		        highlighter.setDrawsLayeredHighlights(false); 

		        try
		        {
		        	int start =  textArea.getSelectionStart();
		            int end =    textArea.getSelectionEnd();
		            highlighter.addHighlight(start, end, painter );
		        }
		        catch(Exception e)
		        {
		            System.out.println(e);
		        }
		        highlighter.setDrawsLayeredHighlights(true);

			}
		});
		menu_33.add(menuItem_144);
		
		JMenuItem menuItem_155 = new JMenuItem("أحمر");
		menuItem_155.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultHighlighter highlighter =  (DefaultHighlighter)textArea.getHighlighter();
		        DefaultHighlighter.DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter( Color.RED );
		        highlighter.setDrawsLayeredHighlights(false); 

		        try
		        {
		        	int start =  textArea.getSelectionStart();
		            int end =    textArea.getSelectionEnd();
		            highlighter.addHighlight(start, end, painter );
		        }
		        catch(Exception e)
		        {
		            System.out.println(e);
		        }
		        highlighter.setDrawsLayeredHighlights(true);

			}
		});
		menu_33.add(menuItem_155);
		
		JMenuItem menuItem_166 = new JMenuItem("أبيض");
		menuItem_166.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultHighlighter highlighter =  (DefaultHighlighter)textArea.getHighlighter();
		        DefaultHighlighter.DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter( Color.WHITE );
		        highlighter.setDrawsLayeredHighlights(false); 

		        try
		        {
		        	int start =  textArea.getSelectionStart();
		            int end =    textArea.getSelectionEnd();
		            highlighter.addHighlight(start, end, painter );
		        }
		        catch(Exception e)
		        {
		            System.out.println(e);
		        }
		        highlighter.setDrawsLayeredHighlights(true);

			}
		});
		menu_33.add(menuItem_166);
		
		JMenuItem menuItem_177 = new JMenuItem("أصفر");
		menuItem_177.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultHighlighter highlighter =  (DefaultHighlighter)textArea.getHighlighter();
		        DefaultHighlighter.DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter( Color.YELLOW );
		        highlighter.setDrawsLayeredHighlights(false); 

		        try
		        {
		        	int start =  textArea.getSelectionStart();
		            int end =    textArea.getSelectionEnd();
		            highlighter.addHighlight(start, end, painter );
		        }
		        catch(Exception e)
		        {
		            System.out.println(e);
		        }
		        highlighter.setDrawsLayeredHighlights(true);

			}
		});
		menu_33.add(menuItem_177);
		
		
		
		
		JMenu menu_3 = new JMenu("تلوين الخط");
		menu_2.add(menu_3);
		
		JMenuItem menuItem_9 = new JMenuItem("أسود");
		menuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setForeground(Color.BLACK);
			}
		});
		menu_3.add(menuItem_9);
		
		JMenuItem menuItem_10 = new JMenuItem("أزرق");
		menuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setForeground(Color.BLUE);
			}
		});
		menu_3.add(menuItem_10);
		
		JMenuItem menuItem_11 = new JMenuItem("رمادي");
		menuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setForeground(Color.GRAY);
			}
		});
		menu_3.add(menuItem_11);
		
		JMenuItem menuItem_12 = new JMenuItem("أخضر");
		menuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setForeground(Color.GREEN);
			}
		});
		menu_3.add(menuItem_12);
		
		JMenuItem menuItem_13 = new JMenuItem("برتقالي");
		menuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setForeground(Color.ORANGE);
			}
		});
		menu_3.add(menuItem_13);
		
		JMenuItem menuItem_14 = new JMenuItem("زهري");
		menuItem_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setForeground(Color.PINK);
			}
		});
		menu_3.add(menuItem_14);
		
		JMenuItem menuItem_15 = new JMenuItem("أحمر");
		menuItem_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setForeground(Color.RED);
			}
		});
		menu_3.add(menuItem_15);
		
		JMenuItem menuItem_16 = new JMenuItem("أبيض");
		menuItem_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setForeground(Color.WHITE);
			}
		});
		menu_3.add(menuItem_16);
		
		JMenuItem menuItem_17 = new JMenuItem("أصفر");
		menuItem_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setForeground(Color.YELLOW);
			}
		});
		menu_3.add(menuItem_17);
		
		JMenu menu_4 = new JMenu("تلوين الخلفية");
		menu_2.add(menu_4);
		
		JMenuItem menuItem_18 = new JMenuItem("أسود");
		menuItem_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setBackground(Color.BLACK);
			}
		});
		menu_4.add(menuItem_18);
		
		JMenuItem menuItem_19 = new JMenuItem("أزرق");
		menuItem_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setBackground(Color.BLUE);
			}
		});
		menu_4.add(menuItem_19);
		
		JMenuItem menuItem_20 = new JMenuItem("رمادي");
		menuItem_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setBackground(Color.GRAY);
			}
		});
		menu_4.add(menuItem_20);
		
		JMenuItem menuItem_21 = new JMenuItem("أخضر");
		menuItem_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setBackground(Color.GREEN);
			}
		});
		menu_4.add(menuItem_21);
		
		JMenuItem menuItem_22 = new JMenuItem("برتقالي");
		menuItem_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setBackground(Color.ORANGE);
			}
		});
		menu_4.add(menuItem_22);
		
		JMenuItem menuItem_23 = new JMenuItem("زهري");
		menuItem_23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setBackground(Color.PINK);
			}
		});
		menu_4.add(menuItem_23);
		
		JMenuItem menuItem_24 = new JMenuItem("أحمر");
		menuItem_24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setBackground(Color.RED);
			}
		});
		menu_4.add(menuItem_24);
		
		JMenuItem menuItem_25 = new JMenuItem("أبيض");
		menuItem_25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setBackground(Color.WHITE);
			}
		});
		menu_4.add(menuItem_25);
		
		JMenuItem menuItem_26 = new JMenuItem("أصفر");
		menuItem_26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setBackground(Color.YELLOW);
			}
		});
		menu_4.add(menuItem_26);
		
		JMenu menu_5 = new JMenu("معلومات");
		menuBar.add(menu_5);
		menu_5.setMnemonic(KeyEvent.VK_A);
		
		JMenuItem menuItem_27 = new JMenuItem("عن البرنامج");
		menuItem_27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s ="<html><body><div width='200px' align='center'>محرر النصوص هو عبارة عن مشروع صغير بامكانيات مميزة<br> مكتوب بلغة البرمجة Java<br> يتعامل مع الملفات من النوع txt<br> هذا هو الإصدار الأول من البرنامج على أمل التطور والتحسن المستمر<br> R_NotePAD V.1.1 <br> All Rights Reserved 2016 Nour Alhadi Mahmoud</div></body></html>";
				JOptionPane.showMessageDialog(null, s,"محرر النصوص 1.1",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menu_5.add(menuItem_27);
		
		JMenuItem menuItem_28 = new JMenuItem("عن المبرمج");
		menuItem_28.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = "<html><body><div width='200px' align='center'>هذا البرنامج من كتابة وتصميم نور الهادي علي محمود<br> طالب سنة ثانية هندسة معلوماتية<br> لمن يحب ان يشاركني مشاريع قادمة او لديه أفكار لمشاريع قابلة للتنفيذ يتواصل معي من خلال فيسبوك:<br> facebook.com\\nouralhadi.mahmoud.3 <br>وللإبلاغ عن أي اخطاء التواصل معي أيضا</div></body></html>";
				JOptionPane.showMessageDialog(null, s,"محرر النصوص 1.1",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menu_5.add(menuItem_28);
	}

}
