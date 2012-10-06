package b;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Callable;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import clojure.lang.RT;
import clojure.lang.Symbol;
import clojure.lang.Var;

public class Activator extends AbstractUIPlugin {

	public class BundleClassLoader extends ClassLoader {
		private Bundle b;
		public BundleClassLoader(Bundle b) {
			this.b = b;
		}
		@Override
		protected Class<?> findClass(String name) throws ClassNotFoundException {
			return b.loadClass(name);
		}
		@Override
		protected URL findResource(String name) {
			return b.getResource(name);
		}
		public <R> R asContextClassLoader(Callable<R> c) throws Exception {
			ClassLoader ccl = Thread.currentThread().getContextClassLoader();
			try {
				Thread.currentThread().setContextClassLoader(this);
				return c.call();
			} finally {
				Thread.currentThread().setContextClassLoader(ccl);
			}
		}
	}
	
	public Activator() {
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		new BundleClassLoader(context.getBundle()).asContextClassLoader(new Callable() {
			@Override
			public Object call() throws Exception {
				Var require = RT.var("clojure.core", "require");
				require.invoke(Symbol.intern("b.transformers-client"));
				return null;
			}
		});
	}

}
