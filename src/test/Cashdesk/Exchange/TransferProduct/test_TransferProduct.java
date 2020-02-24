package test.Cashdesk.Exchange.TransferProduct;

import com.company.functions;
import org.junit.*;
import org.junit.jupiter.api.Order;
import test.Cashdesk.Exchange.TransferProduct.Ref.TransferProduct;

import static com.company.test_settings.TRANSFER_PRODUCT_TEST_SETTING;

@FixMethodOrder
public class test_TransferProduct {
    private TransferProduct given = new TransferProduct();
    private TransferProduct when = new TransferProduct();
    private TransferProduct then = new TransferProduct();
    private TransferProduct start = new TransferProduct();
    private functions check = new functions();
    @After
    public void reset() {
        start.reset();
    }

    @Before
    public void isTestActivated(){
        Assume.assumeTrue(TRANSFER_PRODUCT_TEST_SETTING);
    }

    @Test
    @Order(2)
    public void addTransfer(){
        given
                .TPopen();
        when
                .setProduct("Notes")
                .setCurrency("SEK")
                .setAmount(10)
                .setSource(1)
                .setDestination(1)
                .setComment("This is a comment")
                .confirm();
        then
                .isTransferSuccesful();

    }
}
