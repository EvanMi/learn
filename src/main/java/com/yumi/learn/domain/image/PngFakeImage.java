package com.yumi.learn.domain.image;
/**
 * (4) 组合优先于继承
 */
public class PngFakeImage implements ImageWrapper, ImageFile {

    private final Image image;

    public PngFakeImage(Image image) {
        this.image = image;
    }
    
    @Override
    public void save(String path) {
        System.out.println("png save");
    }

    @Override
    public void load(String path) {
        System.out.println("png load");
    }

    @Override
    public Image getImage() {
        return this.image;
    }
}
