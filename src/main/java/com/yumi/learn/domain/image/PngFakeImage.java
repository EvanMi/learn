package com.yumi.learn.domain.image;

/**
 * (4 组合优先于继承)
 */
public class PngFakeImage implements ImageWrapper, ImageFile {
	private final String path;
	private Image image;

	public PngFakeImage(String path) {
        this.path = path;
    }

	@Override
	public void save() {
		byte[] data = this.image.getData();
		//编码为png
		System.out.println("png save");
	}

	@Override
	public void load() {
		System.out.println("png load");
		this.image = new FakeImage(new byte[0]);
	}

	@Override
	public Image getImage() {
		return this.image;
	}

}
