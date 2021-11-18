import java.util.Vector;
import java.util.Iterator;

public class DataCenter {
    private Vector<Server> servers;

    public DataCenter() {
        this.servers = new Vector<Server>();
    }

    public void addServer(final Server server) {
        this.servers.add(server);
    }

    public Vector<Server> getServers() {
        return this.servers;
    }

    public void initializtaion() {
        int nServers = 5;
        for(int i = 0; i < nServers; i++) {
            Server server_=new Server(5,5);
            this.addServer(server_);
        }
    }

    public void print(){
        Iterator<Server> iter=this.servers.iterator();
        while(iter.hasNext()){
            Server server=iter.next();
            server.print();
            System.out.println();
        }
    }

}
