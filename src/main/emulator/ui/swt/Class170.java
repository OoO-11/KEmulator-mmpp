package emulator.ui.swt;

import emulator.custom.h;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import java.util.Enumeration;

final class Class170 extends SelectionAdapter {
	Class170(final Methods class46) {
		super();
	}

	public final void widgetSelected(final SelectionEvent selectionEvent) {
		final Enumeration<h.MethodInfo> elements = h.aHashtable1061.elements();
		while (elements.hasMoreElements()) {
			final h.MethodInfo methodInfo;
			(methodInfo = elements.nextElement()).anInt1182 = 0;
			methodInfo.aLong1179 = 0L;
			methodInfo.aFloat1175 = 0.0f;
			methodInfo.aFloat1180 = 0.0f;
		}
	}
}
