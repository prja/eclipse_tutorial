package com.prabhu.rcp.dialog.wizard;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;

public class MyFirstWizard extends Wizard {

	protected MyFirstPage one;
	protected MySecondPage two;
	protected MyPageModel model;

	public MyFirstWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		model= new MyPageModel();
		one = new MyFirstPage(model);
		two = new MySecondPage(model);
		addPage(one);
		addPage(two);
	}

	@Override
	public boolean performFinish() {
		// Print the result to the console
		System.out.println(one.getText1());
		System.out.println(two.getText1());
		
		System.out.println(model.getPgae1());
		System.out.println(model.getPage2());
try {
	getContainer().run(true, true, new IRunnableWithProgress() {
		
		@Override
		public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
			// TODO Auto-generated method stub
			monitor.beginTask("Validation Security Rule is on: ", 100);
	         for (int i = 0; i < 100; i++) {
	            monitor.subTask("Mainframe Validation triggred count"+Integer.toString(i));
	            //sleep to simulate long running operation
	            Thread.sleep(100);
	            monitor.worked(1);
	         }
	         monitor.done();
		}
	});
} catch (InvocationTargetException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		return true;
	}
}
