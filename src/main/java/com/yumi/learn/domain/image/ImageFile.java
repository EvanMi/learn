package com.yumi.learn.domain.image;

/**
 * (4 组合优先于继承)
 */
public interface ImageFile {

	void save(String path);

	void load(String path);

}
