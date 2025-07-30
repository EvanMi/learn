package com.yumi.learn.acl.clients;

import org.junit.jupiter.api.Test;

public class NettyZeroCopyFileDownloadClientTest {

    @Test
    void testDownload() throws Exception {
        NettyZeroCopyFileDownloadClient nettyZeroCopyFileDownloadClient = new
                NettyZeroCopyFileDownloadClient();
        nettyZeroCopyFileDownloadClient.downLoadByUrl("http://storage.jd.com/erpweb/forum-icon.png",
                "/Users/mipengcheng3/Downloads/test1233.png");
    }
}
