package b;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import clojure.lang.RT;
import clojure.lang.Symbol;
import clojure.lang.Var;


public class LoadATransform extends AbstractHandler implements IHandler {
	private Var loadATransform;
	
	public LoadATransform() {
		loadATransform = RT.var("b.transformers-client", "load-a-transform");
	}
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		MessageDialog.openInformation(
				HandlerUtil.getActiveShell(event), "Loading A Transform", loadATransform.invoke().toString());
		return null;
	}

}
