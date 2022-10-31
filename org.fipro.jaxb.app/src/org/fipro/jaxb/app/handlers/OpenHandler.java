package org.fipro.jaxb.app.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
public class OpenHandler {

	@Execute
	public void execute(Shell shell, IEventBroker broker) {
		FileDialog dialog = new FileDialog(shell);
		String filename = dialog.open();
		if (filename != null) {
			broker.send("org/fipro/jaxb/LOAD", filename);
		}
	}
}
