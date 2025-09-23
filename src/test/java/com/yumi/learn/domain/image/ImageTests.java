package com.yumi.learn.domain.image;

import org.junit.jupiter.api.Test;

public class ImageTests {
    @Test
    void testLoadImage() {
        Image png = loadImage("/abc/xby.png", "png");
        png.getHeight();
        png.getWidth();
    }

    private Image loadImage(String path, String type) {
        switch (type) {
            case "png" -> {
                PngFakeImage pngFakeImage = new PngFakeImage(path);
                pngFakeImage.load();
                return pngFakeImage;
            }
            case "jpg" -> {
                JpgFakeImage jpgFakeImage = new JpgFakeImage(path);
                jpgFakeImage.load();
                return jpgFakeImage;
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
