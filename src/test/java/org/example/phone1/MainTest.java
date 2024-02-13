package org.example.phone1;

import org.testng.annotations.Test;

public class MainTest extends BaseTest {
    private static final Transfer transfer = new Transfer();

    @Test
    public void test() throws InterruptedException {
        transfer.transferMain();
    }
}
