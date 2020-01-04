package br.com.produtec.domain.entity;

import br.com.produtec.infrastructure.suport.Server;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

// Test the external services (external IP's)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // Define the order to running tests.
public class ServiceIntegrationTest {

    /**
     * Running away before the testes
     */
    @Before
    public void before() {

        // Start the server to execute the br.com.produtec.integration testes
        Server.start();
    }

    /**
     *
     */
    @Test
    public void testIsConnectedMustPass() throws IOException {
        final Service service = new Service();
        service.connect();
        Assert.assertTrue(service.isConnected());
    }

    /**
     *
     */
    @Test(expected = java.net.SocketTimeoutException.class)
    public void testIsConnectedMustFail() throws IOException {
        final Service service = new Service(4001);
        service.connect();
    }

    /**
     *
     */
    @Test
    public void testIsConnectedWithVariousServersMustPass() throws IOException {
        final Service service4000 = new Service();
        service4000.connect();
        Assert.assertTrue(service4000.isConnected());

        Server.start(4001);
        final Service service4001 = new Service(4001);
        service4001.connect();
        Assert.assertTrue(service4001.isConnected());

        Server.start(4002);
        final Service service4002 = new Service(4002);
        service4002.connect();
        Assert.assertTrue(service4002.isConnected());
    }

    /**
     *
     */
    @Test(expected = java.net.SocketTimeoutException.class)
    public void testIsConnectedWithVariousServersMustFail() throws IOException {
        final Service service4000 = new Service();
        service4000.connect();
        Assert.assertTrue(service4000.isConnected());

        Server.start(4001);
        final Service service4001 = new Service(4001);
        service4001.connect();
        Assert.assertTrue(service4001.isConnected());

        Server.start(4002);
        final Service service4002 = new Service(4002);
        service4002.connect();
        Assert.assertTrue(service4002.isConnected());

        // Server nonexistent
        final Service service4003 = new Service(4003);
        service4003.connect();
        Assert.assertTrue(service4003.isConnected());
    }

    /**
     *
     */
    @Test
    public void testIsConnectedWithExternalAddressMustPass() throws IOException {
        Server.stop();
        final Service service = new Service("8.8.8.8", 443);
        service.connect();
        Assert.assertTrue(service.isConnected());
    }

    /**
     *
     */
    @Test(expected = java.net.SocketTimeoutException.class)
    public void testIsConnectedWithExternalAddressMustFail() throws IOException {
        Server.stop();
        final Service service = new Service("8.8.8.253", 443);
        service.connect();
    }
}
