package com.yumi.learn.domain.image;

import java.net.Socket;

public class NetWorkImage implements ImageWrapper {
    private Image image;

    public NetWorkImage(Socket socket) {
    }

    @Override
    public Image getImage() {
        return image;
    }
}
