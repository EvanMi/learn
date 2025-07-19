package com.yumi.learn.acl.clients;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioIoHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import jakarta.validation.constraints.NotBlank;

import java.io.RandomAccessFile;
import java.net.URI;
import java.nio.channels.FileChannel;

public class NettyZeroFileDownloadClient {

    public void downLoadByUrl(@NotBlank String url, @NotBlank String targetPath) throws Exception {
        URI uri = URI.create(url);

        if (uri.getScheme().equals("https")) {
            throw new IllegalArgumentException("零拷贝下载不支持https协议");
        }
        String host = uri.getHost();
        int port = uri.getPort();
        if (port < 0) {
            port = 80;
        }
        String path = uri.getPath();

        EventLoopGroup group = new MultiThreadIoEventLoopGroup( NioIoHandler.newFactory());
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<>() {
                        @Override
                        protected void initChannel(Channel ch) {
                            ch.pipeline().addLast(new HttpClientCodec());
                            ch.pipeline().addLast(new HttpObjectAggregator(8192));
                            ch.pipeline().addLast(new SimpleChannelInboundHandler<FullHttpResponse>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse response) throws Exception {
                                    RandomAccessFile file = new RandomAccessFile(targetPath, "rw");
                                    FileChannel fileChannel = file.getChannel();
                                    ByteBuf content = response.content();
                                    fileChannel.write(content.nioBuffer());
                                    fileChannel.close();
                                    file.close();
                                    ctx.close();
                                }
                            });
                        }
                    });

            Channel ch = b.connect(host, port).sync().channel();

            // 创建HTTP请求
            HttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, path);
            request.headers().set(HttpHeaderNames.HOST, host);
            request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
            request.headers().set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP);

            // 发送请求
            ch.writeAndFlush(request);

            // 等待连接关闭
            ch.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
       // NettyZeroFileDownloadClient nettyZeroFileDownloadClient = new NettyZeroFileDownloadClient();
      //  nettyZeroFileDownloadClient.downLoadByUrl("http://storage.jd.com/erpweb/forum-icon.png", "/Users/mipengcheng3/Downloads/test123.png");
    long x = 1752808460809L;
    String paddedBinaryString = String.format("%64s", Long.toBinaryString(x)).replace(' ', '0');
        System.out.println(Long.toBinaryString(x).length());
        System.out.println(paddedBinaryString.length());
    }
}