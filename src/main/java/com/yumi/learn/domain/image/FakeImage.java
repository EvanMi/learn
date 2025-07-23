package com.yumi.learn.domain.image;

public class FakeImage implements Image{
    @Override
    public boolean resize(int width, int height) {
        return false;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public boolean flipHorizontal() {
        return false;
    }

    @Override
    public boolean flipVertical() {
        return false;
    }
}
