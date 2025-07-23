package com.yumi.learn.domain.image;

/**
 * (4 组合优先于继承)
 */
public interface ImageWrapper extends Image {

	@Override
	default boolean resize(int width, int height) {
		return getImage().resize(width, height);
	}

	@Override
	default int getWidth() {
		return getImage().getWidth();
	}

	@Override
	default int getHeight() {
		return getImage().getHeight();
	}

	@Override
	default boolean flipHorizontal() {
		return getImage().flipVertical();
	}

	@Override
	default boolean flipVertical() {
		return getImage().flipVertical();
	}

	Image getImage();

}
