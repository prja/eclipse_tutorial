package org.prabhu.rcp.dnd.listner;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.prabhu.rcp.dnd.model.ContrEmpModelProviderTree;


public class MyDropListener extends ViewerDropAdapter {

	private final Viewer viewer;

	public MyDropListener(Viewer viewer) {
		super(viewer);
		this.viewer = viewer;
	}

	@Override
	public void drop(DropTargetEvent event) {
		int location = this.determineLocation(event);
		String target = (String) determineTarget(event);
		String translatedLocation ="";
		switch (location){
		case 1 :
			translatedLocation = "Dropped before the target ";
			break;
		case 2 :
			translatedLocation = "Dropped after the target ";
			break;
		case 3 :
			translatedLocation = "Dropped on the target ";
			break;
		case 4 :
			translatedLocation = "Dropped into nothing ";
			break;
		}
		System.out.println(translatedLocation);
		System.out.println("The drop was done on the element: " + target );
		super.drop(event);
	}

	// This method performs the actual drop

	@Override
	public boolean performDrop(Object data) {
		ContrEmpModelProviderTree.INSTANCE.getModel().add( data.toString());
		viewer.setInput(ContrEmpModelProviderTree.INSTANCE.getModel());
		return false;
	}

	@Override
	public boolean validateDrop(Object target, int operation,
			TransferData transferType) {
		return true;
		
	}

	

}
