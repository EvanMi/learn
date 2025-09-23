package com.yumi.learn.domain.image;

/**
 * (4 组合优先于继承)
 */
public class FakeImage implements Image {

	public FakeImage(byte[] bytes) {

	}

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

	@Override
	public byte[] getData() {
		return new byte[0];
	}

}
