package com.yumi.learn.domain.image;

/**
 * (4 组合优先于继承)
 */
public class JpgFakeImage implements ImageWrapper, ImageFile {
	private final String path;
	private Image image;

	public JpgFakeImage(String path) {
        this.path = path;
    }

	@Override
	public void save() {
		byte[] data = this.image.getData();
		String localPath = this.path;
		//编码为jpg
		System.out.println("jpg save");
	}

	@Override
	public void load() {
		System.out.println("jpg load");
		this.image = new FakeImage(new byte[0]);
	}

	@Override
	public Image getImage() {
		return this.image;
	}

}
