package br.com.produtec.domain.entity;

import br.com.produtec.infrastructure.utils.Server;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.time.LocalDateTime;


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
     * Running away after the testes
     */
    @After
    public void after() throws InterruptedException {

        // Sleep to start server
        Thread.sleep(100);

        // Start the server to execute the br.com.produtec.integration testes
        Server.stop();
    }

    /**
     *
     */
    @Test
    public void testIsConnectedMustReturnTrue() {
        final Service service = new Service("127.0.0.1", 4000, null);
        service.connect();
        Assert.assertTrue(service.isConnected());
    }

    /**
     *
     */
    @Test
    public void testIsConnectedMustReturnFalse() {
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
        final Service service4001 = new Service("127.0.0.1", 4001, null);
        service4001.connect();
        Assert.assertTrue(service4001.isConnected());

        // Sleep to start server
        Thread.sleep(100);
        Server.start(4002);
        final Service service4002 = new Service("127.0.0.1", 4002, null);
        service4002.connect();
        Assert.assertTrue(service4002.isConnected());
    }

    /**
     *
     */
    @Test
    public void testIsConnectedWithExternalAddressMustPass() {
        final Service service = new Service("8.8.8.8", 443, null);
        service.connect();
        Assert.assertTrue(service.isConnected());
    }

    /**
     *
     */
    @Test
    public void testIsConnectedWithExternalAddressMustReturnFalse() {
        final Service service = new Service("8.8.8.253", 443, null);
        service.connect();
        Assert.assertFalse(service.isConnected());
    }

    /**
     *
     */
    @Test
    public void testIsConnectedWithExternalAddressStopThreadMustPass() throws InterruptedException, IOException {

        final int pollingTimeout = 1;
        final int connectionTimeout = 1;

        final LocalDateTime now = LocalDateTime.now();

        final Service service = new Service("8.8.8.8", 443, null, pollingTimeout, connectionTimeout, now, now.plusSeconds(10));
        service.connect();
        service.start();

        // Wait one second
        Thread.sleep(1000);

        service.stop();

        Assert.assertFalse(service.isConnected());
        Assert.assertTrue(service.isDone());
    }

    /**
     *
     */
    @Test
    public void testIsConnectedWithExternalAddressBetweenTimesMustPass() throws InterruptedException, IOException {

        final int pollingTimeout = 1;
        final int connectionTimeout = 1;

        final LocalDateTime now = LocalDateTime.now();

        final Service service = new Service("8.8.8.8", 443, null, pollingTimeout, connectionTimeout, now, now.plusSeconds(10));
        service.connect();
        service.start();

        // Wait 15 seconds
        Thread.sleep(1000 * 15);

        Assert.assertFalse(service.isConnected());
        Assert.assertTrue(service.isDone());

        service.stop();
    }

    /**
     *
     */
    @Test
    public void testIsConnectedWithExternalAddressInfinityThreadMustPass() throws InterruptedException, IOException {

        final int pollingTimeout = 1;
        final int connectionTimeout = 1;

        final LocalDateTime now = LocalDateTime.now();

        final Service service = new Service("8.8.8.8", 443, null, pollingTimeout, connectionTimeout, now, now.plusSeconds(10));
        service.connect();

        service.start();

        // Wait 15 seconds
        Thread.sleep(1000 * 15);

        Assert.assertFalse(service.isConnected());
        Assert.assertTrue(service.isDone());

        service.stop();
    }

    /**
     *
     */
    @Test(expected = RuntimeException.class)
    public void testWithInvalidDatesMustFail() {

        final int pollingTimeout = 1;
        final int connectionTimeout = 1;

        final LocalDateTime now = LocalDateTime.now();

        new Service("8.8.8.8", 443, null, pollingTimeout, connectionTimeout, now.plusSeconds(10), now);
    }

    /**
     *
     */
    @Test(expected = RuntimeException.class)
    public void testWithInvalidTimeoutsMustFail() {

        final int pollingTimeout = 1;
        final int connectionTimeout = 100;

        final LocalDateTime now = LocalDateTime.now();

        new Service("8.8.8.8", 443, null, pollingTimeout, connectionTimeout, now, now.plusSeconds(10));
    }
}


