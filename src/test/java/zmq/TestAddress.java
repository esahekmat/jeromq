package zmq;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestAddress {

    @Test
    public void ToNotResolvedToString() {
        Address addr = new Address("tcp", "localhost");
        
        String saddr = addr.toString("google.com");
        assertThat(saddr, is("tcp://localhost"));
    }
    
    @Test
    public void testResolvedToString() {
        Address addr = new Address("tcp", "localhost");
        addr.resolved(new TcpAddress());
        
        String saddr = addr.toString("google.com:90");
        assertThat(saddr, is("tcp://google.com:90"));
        
        addr.resolved(new TcpAddress());
        saddr = addr.toString("localhost:90");
        assertThat(saddr, is("tcp://localhost:90"));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvaid() {
        
        Address addr = new Address("tcp", "dummy");
        addr.resolved(new TcpAddress());
        
        addr.toString("ggglocalhostxxx:90");
    }
}
