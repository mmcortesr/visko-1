package trustlab.visko.knowledge;

import trustlab.server.CIServer;

public class NickCIServer {
	
	public static CIServer getServer() {		
		return new CIServer("http://rio.cs.utep.edu/ciserver", "visko", "ndel2", "NDel209!");
	}
}