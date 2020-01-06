package br.com.produtec.domain.entity;

import br.com.produtec.infrastructure.utils.Server;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


// Test the external services (external IP's)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // Define the order to running tests.
public class ServiceIntegrationTest {

    /**
     * Running away before the testes
     */
    @Before
    public void before() throws InterruptedException {

        // Sleep to start server
        Thread.sleep(100);

        // Start the server to execute the br.com.produtec.integration testes
        Server.start();
    }

    /**
     *
     */
    @Test
    public void testIsConnectedMustReturnTrue() {
        final Service service = new Service("127.0.0.1", 4000,  null);
        service.connect();
        Assert.assertTrue(service.isConnected());
    }

    /**
     *
     */
    @Test
    public void testIsConnectedMustReturnFalse(){
        final Service service = new Service("127.0.0.1", 4001, null);
        service.connect();
        Assert.assertFalse(service.isConnected());
    }

    /**
     *
     */
    @Test
    public void testIsConnectedWithVariousServersMustPass() throws InterruptedException {
        final Service service4000 = new Service("127.0.0.1", 4000, null);
        service4000.connect();
        Assert.assertTrue(service4000.isConnected());

        // Sleep to start server
        Thread.sleep(100);
        Server.start(4001);
        final Service service4001 = new Service("127.0.0.1", 4001,null);
        service4001.connect();
        Assert.assertTrue(service4001.isConnected());

        // Sleep to start server
        Thread.sleep(100);
        Server.start(4002);
        final Service service4002 = new Service("127.0.0.1", 4002,null);
        service4002.connect();
        Assert.assertTrue(service4002.isConnected());
    }

    /**
     *
     */
    @Test
    public void testIsConnectedWithExternalAddressMustPass() {
        Server.stop();
        final Service service = new Service("8.8.8.8", 443, null);
        service.connect();
        Assert.assertTrue(service.isConnected());
    }

    /**
     *
     */
    @Test
    public void testIsConnectedWithExternalAddressMustReturnFalse() {
        Server.stop();
        final Service service = new Service("8.8.8.253", 443, null);
        service.connect();
        Assert.assertFalse(service.isConnected());
    }

}


