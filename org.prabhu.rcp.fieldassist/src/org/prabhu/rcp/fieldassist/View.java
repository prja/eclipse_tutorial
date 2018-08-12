package org.prabhu.rcp.fieldassist;

import java.util.*;

import javax.inject.Inject;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	public static final String ID = "org.prabhu.rcp.fieldassist.view";

	

	@Override
	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);
		Label lblPleaseEnterA = new Label(parent, SWT.NONE);
		lblPleaseEnterA.setText("Enter input:");
		
		Text text = new Text(parent, SWT.BORDER);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_text.horizontalIndent = 8;
		text.setLayoutData(gd_text);
		GridData data = new GridData(SWT.FILL, SWT.TOP, true, false);
		text.setData(data);

		final ControlDecoration deco = new ControlDecoration(text, SWT.TOP
			| SWT.LEFT);

		Image image = FieldDecorationRegistry.getDefault()
			.getFieldDecoration(FieldDecorationRegistry.DEC_REQUIRED)
			.getImage();

		deco.setDescriptionText("Use CNTL + SPACE to see possible values");
		deco.setImage(image);

		deco.setShowOnlyOnFocus(false);

		// hide the decoration if the text  has content
		text.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Text text = (Text) e.getSource();
				if (text.getText().length() > 0) {
				  deco.hide();
				} else {
				  deco.show();
				}
			}
		});

		// "." and "#" activate the content proposals
		char[] autoActivationCharacters = new char[] { '.', '#' };
		try {
			KeyStroke keyStroke = KeyStroke.getInstance("Ctrl+Space");
			ContentProposalAdapter adapter = new ContentProposalAdapter(text,
				new TextContentAdapter(),
				new SimpleContentProposalProvider(new String[] {
					"Input1", "Input2", "Input3","Input4", "Input5" }),
					keyStroke, autoActivationCharacters);
			} catch (ParseException e) {
			e.printStackTrace();
		}

	}


	@Override
	public void setFocus() {
	}
	

}