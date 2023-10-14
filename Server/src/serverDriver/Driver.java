package serverDriver;

import dbmodels.Equipment;
import server.Server;

public class Driver {

	public static void main(String[] args) {
		//Server server = new Server();
		//server.closeConnection();
		Equipment eq1 = new Equipment();
		//eq1.create(1002,"Microphone","Y",9098.9);
		//eq1.update(1002, "N");
		eq1.readAll();
		//eq1.delete(1002);
	}

}
