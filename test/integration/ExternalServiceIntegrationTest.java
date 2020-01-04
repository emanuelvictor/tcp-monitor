package integration;

import br.com.produtec.domain.entity.Service;
import br.com.produtec.infrastructure.suport.Server;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

// TODO COLOCAR DESCRIÇÃO
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // Define the order to running tests.
public class ExternalServiceIntegrationTest {

    /**
     *
     */
    @Test
    public void testIsConnectedWithExternalAddressMustPass() throws IOException {
        final Service service = new Service("8.8.8.8", 443);
        service.connect();
        Assert.assertTrue(service.isConnected());
    }

    /**
     *
     */
    @Test(expected = java.net.SocketTimeoutException.class)
    public void testIsConnectedWithExternalAddressMustFail() throws IOException {
        final Service service = new Service("8.8.8.253", 443);
        service.connect();
    }
}
