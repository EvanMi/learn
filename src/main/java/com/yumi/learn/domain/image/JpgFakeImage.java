package com.yumi.learn.domain.image;
/**
 * (4) 组合优先于继承
 */
public class JpgFakeImage implements ImageWrapper, ImageFile {

    private final Image image;

    public JpgFakeImage(Image image) {
        this.image = image;
    }

    @Override
    public void save(String path) {
        System.out.println("jpg save");
    }

    @Override
    public void load(String path) {
        System.out.println("jpg load");
    }

    @Override
    public Image getImage() {
        return this.image;
    }
}
