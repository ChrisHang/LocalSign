package gps949;

import java.security.KeyStore;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

public class CertSelector {

	public CertSelector(KeyStore ks, List<String> _aliases, AliasCan ac) {
		AliasCan tempAC = new AliasCan();
		Display display = Display.getDefault();
		CertDlg certDlg = new CertDlg(display.getActiveShell(), ks, _aliases, tempAC);
		certDlg.setBlockOnOpen(true);
		if (certDlg.open() == Dialog.OK) {
			ac.alias = tempAC.alias;
		} else {
			ac.alias = "ERROR";
		}

	}

}
