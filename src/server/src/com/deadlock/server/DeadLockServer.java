package com.deadlock.server;
import org.restlet.*;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

public class DeadLockServer extends Application {

    public static void main(String[] args) throws Exception {
       Component server = new Component() ;
        server.getServers().add(Protocol.HTTP, 8080) ;
        server.getDefaultHost().attach(new DeadLockServer()) ;
        server.start();
    }

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext()) ;
        router.attach("/gumball", DeadLockResource.class);
        router.attach("/config",OrientationModelResource.class);
        router.attach("/room", RoomResource.class);
        return router;
    }


}

