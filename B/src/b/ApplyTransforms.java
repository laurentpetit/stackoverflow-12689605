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


public class ApplyTransforms extends AbstractHandler implements IHandler {
	private Var applyTransforms;
	
	public ApplyTransforms() {
		applyTransforms = RT.var("b.transformers-client", "apply-transforms");
	}
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		MessageDialog.openInformation(
				HandlerUtil.getActiveShell(event), "Apply transforms", (String) applyTransforms.invoke("hello"));
		return null;
	}

}
