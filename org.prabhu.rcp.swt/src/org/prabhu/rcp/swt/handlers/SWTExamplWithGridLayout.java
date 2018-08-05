package org.prabhu.rcp.swt.handlers;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

// use this link to explore  : http://www.eclipse.org/swt/snippets/

public class SWTExamplWithGridLayout {
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);

       
        
        
        // GridLayout with 3 columns 
        GridLayout layout = new GridLayout(3, false);
        shell.setLayout(layout);

      //Demo Layout, label button
        Label label = new Label(shell, SWT.NONE);
        label.setText("User Name");        
        Text text= new Text(shell, SWT.PASSWORD);
        text.setText("Enter your password");
        Button button = new Button(shell, SWT.PUSH);
        button.setText("Login");
        button.addSelectionListener(new SelectionAdapter() {        	
        	@Override
        	public void widgetSelected(SelectionEvent e) {  
        		System.out.println("I am clicked");
        	}
		});
        
                

        //Demo Grid Data:        
        
        label = new Label(shell, SWT.BORDER);
        label.setText("2 Span label");
        GridData data = new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1);
        label.setLayoutData(data);
      
        Button b = new Button(shell, SWT.PUSH);
        b.setText("Test Button");

        
        // demo font
        GridData data2 = new GridData(SWT.FILL, SWT.TOP, true, false, 3, 1);
        
        Label label1 = new Label(shell, SWT.NONE);
        label1.setLayoutData(data2);
        Font font = new Font(label.getDisplay(), new FontData("Arial", 25, SWT.ITALIC));
        label1.setFont(font);
        label1.setText("Test Text");
        
        //Demo Color 
        Color red = display.getSystemColor(SWT.COLOR_DARK_RED);
        label1.setBackground(red);
        
        //Demo Date time
        DateTime calendar = new DateTime(shell, SWT.CALENDAR);
        DateTime date = new DateTime(shell, SWT.DATE);
        //DateTime time = new DateTime(shell, SWT.TIME);
        DateTime dateD = new DateTime(shell, SWT.DATE | SWT.DROP_DOWN);
        
        
        //demo Image
        
        List<Image> imageList = new ArrayList<Image>();

        imageList.add(Display.getDefault().getSystemImage(SWT.ICON_WARNING));
        imageList.add(Display.getDefault().getSystemImage(SWT.ICON_WORKING));
        imageList.add(Display.getDefault().getSystemImage(SWT.ICON_QUESTION));
        imageList.add(Display.getDefault().getSystemImage(SWT.ICON_INFORMATION));
        imageList.add(Display.getDefault().getSystemImage(SWT.ICON_ERROR));
        for (Image image : imageList) {
            Label label2 = new Label(shell, SWT.NONE);
            label2.setImage(image);
            label2.setLayoutData(data2);
        }
        
        
        //demo Listner : key 
                       
        Button button3 = new Button(shell, SWT.CENTER);
        button3.setText("Press  key");
        button3.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                String string = "";
                if (e.keyCode == SWT.BS) {
                    string += "BACKSPACE ";
                }
                if (e.keyCode == SWT.ESC) {
                    string += "ESCAPE ";
                }
              
                if (!string.equals("")) {
                    System.out.println(string);
                }
            }
        });
        
        

        shell.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }

}